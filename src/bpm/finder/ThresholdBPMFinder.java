package bpm.finder;

/**
 *
 * @author Ele
 */

import biz.source_code.dsp.filter.FilterCharacteristicsType;
import biz.source_code.dsp.filter.FilterPassType;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.sqrt;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jtransforms.fft.DoubleFFT_1D;
import org.apache.commons.io.IOUtils;

public class ThresholdBPMFinder{
    
   
    int sampleWindow = 1024;
    
    private File currentFile;
    private WavFile wavFile;
   
    int peakCount = 0;
    private WavFilter lowpassFilter;
    private long sampleRate; 
    private FilterPassType filterPassType;
    private boolean stopped = false;
    private FilterCharacteristicsType filterCharacteristics;
    
    public ThresholdBPMFinder(){
        this.filterPassType = FilterPassType.lowpass;
        this.filterCharacteristics = FilterCharacteristicsType.bessel; 
    }
    
    public boolean setWAV(File file){
        this.currentFile = file;
        try {
            this.wavFile = WavFile.openWavFile(currentFile);
            wavFile.close();
           
        } catch (IOException | WavFileException ex) {
            Logger.getLogger(ThresholdBPMFinder.class.getName()).log(Level.SEVERE, null, ex);
            this.currentFile = null;
            return false;
        }
         return true;
    }
    
    public double magnitudeToDecibel(double magnitude){
        
        return (20*Math.log10(magnitude));
    }
    
    public ArrayList<Integer> getPeaksAtThreshold(double [] data, double threshold){
        ArrayList<Integer> peaks = new ArrayList<>();
        int length = data.length;
        // get the positions of the peaks, that trepassed threshold
        for(int counter = 0; counter < length;){
            if(data[counter] > threshold){
                peaks.add(counter);
                counter += 10000;
            }
            counter++;
        }
       
        return peaks;
    }
    
    public HashMap <Integer,Integer> countIntervalsBetweenNearbyPeaks(ArrayList<Integer> peaks){
        
        HashMap <Integer, Integer> intervalCounts = new HashMap<>();
        
        for(int index = 0; index < peaks.size(); index++){   
    
            int peak = peaks.get(index);
            
            //Get distances (interval) between this peak and the next 10 peaks
            //and count reoccuring intervals 
            
            for(int scope = 0; scope<10; scope++){
                
                if((index+scope)<peaks.size()){
                    //get interval
                    int interval = peaks.get(index+scope)-peak;
                    
                    //check if interval is already stored
                    if (intervalCounts.containsKey(interval)){
                        //get the current intervalcount
                        int previousCount = intervalCounts.get(interval);
                        //increment this intervalcount
                        intervalCounts.put(interval, previousCount+1);
                      
                    }
                    //if the interval is not already stored, store it
                    else
                    {
                        intervalCounts.put(interval,1);

                    }
                }
                
            }
        }
        
        return intervalCounts;
    }
    
    public HashMap<Double, Integer> groupNeighborsByTempo(HashMap<Integer, Integer> intervalCounts){
       
        HashMap<Double, Integer> tempoCounts = new HashMap<>();
        intervalCounts.forEach((Integer interval, Integer count) -> {
            if (interval != 0){
                double theoreticalTempo = 60 / (interval/(double)sampleRate);
                
                while(theoreticalTempo <60) theoreticalTempo *=2;
                while(theoreticalTempo >200) theoreticalTempo /=2;
                
                theoreticalTempo = Math.round(theoreticalTempo);
                
                if(tempoCounts.containsKey(theoreticalTempo)){
                    
                    int previousCount = tempoCounts.get(theoreticalTempo);
                    
                    tempoCounts.put(theoreticalTempo, previousCount+1);
                }
                else
                {
                    tempoCounts.put(theoreticalTempo,1);
                }
                
            }
        });
        
        return tempoCounts;
        
    }

    public double detectBPM(){
        lowpassFilter = new WavFilter(
                currentFile.getPath(), 
                filterPassType, 
                filterCharacteristics,
                6, -1,100, 0
        ); 
        
        double bpm;
        double [] [] wavData = lowpassFilter.getWavData();
        double [] rawPCM = wavData [0];
        double [] dataFromFFT = new double[sampleWindow/2 -1];
        
        double maxPeak;
        double initialThreshold = 0.9;
        double threshold = initialThreshold;
        double minThreshold = 0.3;
        
        int minPeaks = 30;
       
        sampleRate = lowpassFilter.getSampleRate();
        maxPeak = findMaxPeak(rawPCM);
        //System.out.println(sampleWindow);
        HashMap<Integer, Integer> intervalCounts;
        HashMap<Double, Integer> tempoCounts;
        ArrayList <Integer> peakPositions = new ArrayList<>();
        
        double [] buffer = new double [sampleWindow];
     

        System.out.println("---------------------------------------");
 
        
        while (peakPositions.size() < minPeaks && threshold >= minThreshold){
            peakPositions = getPeaksAtThreshold(rawPCM, threshold);
            threshold -= 0.05;
        }
        intervalCounts = countIntervalsBetweenNearbyPeaks(peakPositions);
        tempoCounts = groupNeighborsByTempo(intervalCounts);
        int maxTempo = Integer.MIN_VALUE;
        
     
        //int maxCount = Collections.max(tempoCounts.values());
       
        bpm = Collections.max(tempoCounts.entrySet(), (entry1, entry2) 
                -> entry1.getValue() > entry2.getValue() ? 1 : -1).getKey();
        
        System.out.println("Guessed tempo: "+ bpm+" Beats per Minute");
 
        
        System.out.println("---------------------------------------");
         // int samplesAnalyzed = 0;
        
//        while(samplesAnalyzed < dataToFFT.length){
//        
//            for (int i = samplesAnalyzed; i<samplesAnalyzed + sampleWindow; i++){
//
//                
//                //As long as the remaining samples left to analyze are bigger than
//                if(!((dataToFFT.length - samplesAnalyzed) <= sampleWindow)){
//                    //System.out.println("B");
//                    //System.out.print("["+data[i]+"]");
//                    buffer[i-samplesAnalyzed] = dataToFFT[i];
//                }
//            }
//            setThreshold(maxPeak);
//            samplesAnalyzed += sampleWindow;
//            dataFromFFT = performFFT(buffer);
//            for(double x : dataFromFFT) filteredPeaks.add(x);
//        }

        return bpm;
    }
    
    
    
    public double findMaxPeak(double[] data){
        double maxPeak = Double.MIN_VALUE;
        
        for(double i : data){
            if (i>maxPeak) maxPeak = i;
        }
        
        return maxPeak;
    }
    
    
    
    
    public double[] hanningWindow(double[] data) {

    // iterate until the last line of the data buffer
    for (int i = 1; i < data.length; i++) {
        // reduce unnecessarily performed frequency part of each and every frequency
        data[i] *= 0.5 * (1 - Math.cos((2 * Math.PI * i)
                / (data.length - 1)));
    }
    // return modified buffer to the FFT function
    return data;
    }
    
    
    
    
    
    public double [] performFFT (double []data){
        
       
        //sampleWindow = 1024;
        double [] fftData = new double[sampleWindow*2];
        float Fs = 44100f;
        double real;
        double imaginary;
        double [] magnitudes = new double [sampleWindow/2-1];
       
        //Perform Hanning Window function on audio data
        data = hanningWindow(data);
        
        for(int i = 0; i<sampleWindow-1;i++){
            fftData[2*i] = data[i];
            fftData[2*i+1] = 0;
        }
        
        DoubleFFT_1D fft = new DoubleFFT_1D(sampleWindow);
        fft.complexForward(fftData);
        
        //Transform FFT Values to magnitudes  
        for (int i = 0; i<(sampleWindow/2)-1; i++){
            real = fftData[2*i];
            imaginary = fftData[2*i+1];
            magnitudes[i] = sqrt((real*real)) + (imaginary*imaginary);
           // System.out.println(magnitudes[i]);
        }
     
        
        return magnitudes;
    }
}

