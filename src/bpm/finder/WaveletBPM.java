/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpm.finder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import jwave.Transform;
import jwave.transforms.FastWaveletTransform;
import jwave.transforms.wavelets.daubechies.Daubechies2;
import jwave.transforms.wavelets.daubechies.Daubechies4;

/**
 *
 * @author Stefan
 */
public class WaveletBPM {
    
    private File currentFile;
    private WavFile wavFile;
    
    public WaveletBPM() {
        

    }

    
    private double[] envelope(double[] resampleAndAdd, double[] decomposition, int start, int end) {

        // Downsampling
        int amount_of_samples = end-start;
        int total_samples = resampleAndAdd.length;
        
        double[] undersamplearray = new double[total_samples];
        
        int interval = amount_of_samples / total_samples;
        
        int pointer = 0;
        double lasty = 0;
        
        for (int i = start; i < end; i++) {
            
            if (i%interval == 0) {
                
                decomposition[i] = (1-0.8)*decomposition[i] - 0.8*lasty;
                lasty = decomposition[i];
                
                undersamplearray[pointer] = decomposition[i];
                pointer++;
            }
            
        }
        
        // Calculate mean
        double mean = 0;
        for (int i = 0; i < undersamplearray.length; i++) {
            undersamplearray[i] = Math.abs(undersamplearray[i]);
            mean += undersamplearray[i];
        }
        amount_of_samples = undersamplearray.length;
        mean /= amount_of_samples;

        // Subtract mean
        for (int i = 0; i < undersamplearray.length; i++) {
            undersamplearray[i] -= mean;
        }

        // add
        for (int i = 0; i < resampleAndAdd.length; i++) {
            resampleAndAdd[i] += undersamplearray[i];
        }
        
        return resampleAndAdd;
        
    }
    
    public void getbpm() {
        
        // Audiodaten laden
        double[][] WavData = getwavdata();
        
        double[] audioProcessData = WavData[0];
        
        int length = audioProcessData.length;
        
        int N_samples = 131072;
        
        int amount_of_windows = (int) length/131072;
        
        double[][] Windows = new double[amount_of_windows][131072];
        
        for (int k = 0; k < amount_of_windows; k++) {
            for (int i = 0; i < N_samples; i++) {
                if (k*N_samples+i < length) {
                    Windows[k][i] = audioProcessData[k*N_samples+i];
                }

            }
        }
        
        List<Double> BPMs = new ArrayList<Double>();
        
        for (int cur_window = 0; cur_window < Windows.length; cur_window++) {
            
                Daubechies4 wavelet = new Daubechies4();
                Transform transform = new Transform(new FastWaveletTransform(wavelet));


                    double[][] decomposeAll = transform.decompose(Windows[cur_window]);
                    int depth = 4;
                    double[] decomposition = decomposeAll[depth];

                    int maxDecimation = N_samples/(int) Math.pow(2, depth);
                    double[] resampleAndAdd = new double[maxDecimation];

                    for (int index = 0; index <= depth; index++) {

                        int start = 0;
                        if (index != depth) {
                            start = N_samples/((int) Math.pow(2, index+1));
                        }

                        int end = N_samples/((int) Math.pow(2, index));

                        resampleAndAdd = envelope(resampleAndAdd, decomposition, start, end);

                    }


            // Autokorrelation
            int n = resampleAndAdd.length;
            double[] autocor = new double[n];
            for (int k = 0; k < n; k++) {
                for (int i = 0; i < n; i++) {
                    if (k+i < n) {
                        autocor[k] += resampleAndAdd[i] * resampleAndAdd[k+i];
                    }
                }
            }
                                
            
            // Find Peak
            int min_c = (int) ( 60./220 * 44100./Math.pow(2,depth-1) );
            int max_c = (int) ( 60./50 * 44100./Math.pow(2,depth-1) );
            
            //System.out.println("min_index: "+min_c);
            //System.out.println("max_index: "+max_c);

            double peak = 0;
            int peak_index = 0;
            for (int i = min_c; i < max_c; i++) {
                //autocor[i] = Math.abs(autocor[i]);
                if (autocor[i] > peak) {
                    peak = autocor[i];
                    peak_index = i;
                }
            }


            //System.out.println("max @ "+peak_index);


            double windowBPM = 60./peak_index * (44100./Math.pow(2,depth-1));

            //System.out.println("BPM?: "+windowBPM);
            BPMs.add(windowBPM);

        }
        
        
        int[] countBPMs = new int[220]; // Über 220 BPM wird nicht ermittelt 
        for (double current_BPM : BPMs) {
            
            int round_BPM = (int) Math.round(current_BPM);
            if (round_BPM < 220) {
                countBPMs[round_BPM]++;
            }
            
        }
        
        // Find Maximum BPM
        int maximumBPM = 0;
        int maximumBPM_count = 0;
        for (int BPM = 50; BPM < countBPMs.length; BPM++) {
            if (countBPMs[BPM] > maximumBPM_count) {
                maximumBPM = BPM;
                maximumBPM_count = countBPMs[BPM];
            }
        }
        
        
        
        System.out.println("final BPM:: "+maximumBPM);
        
                 
    }    
    
    public double[][] getwavdata() {
        
        try {
            
            // WAV-Datei öffnen.
            wavFile = WavFile.openWavFile(currentFile);
            
            // Die Gesamtanzahl der Samples in der zu untersuchenden WAV-Datei
            long totalFrames = wavFile.getNumFrames();
            int totWAVsamples = (int) totalFrames;
            
            int numChannels = wavFile.getNumChannels();

            double[][] buffer = new double[numChannels][totWAVsamples];
            
            // ev. Offset-Fix
            //wavFile.readFrames(new double[numChannels][startSample], startSample);      

            // Segment in den Buffer laden.
            int samplesRead = wavFile.readFrames(buffer, totWAVsamples);

            // WAV-Datei schließen.
            wavFile.close();

            return buffer;

        } catch (Exception e) {
            
            System.out.println(e);
        }
        
        return new double[0][0];
        
    }
    
    public boolean setWAV(File file) {
    
        //System.out.println(file.toURI().toASCIIString());
        this.currentFile = file;
        
        try {
            
            // Versuche, die Wav-Datei zu öffnen.
            this.wavFile = WavFile.openWavFile(currentFile);
            wavFile.close();
            //this.wavFile.display();

        } catch (Exception e) {
            
            System.out.println(e);
            this.currentFile = null;
            return false;
            
        }
        
        return true;

    }
    
    
}
