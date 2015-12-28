/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpm.finder;

import biz.source_code.dsp.filter.FilterCharacteristicsType;
import biz.source_code.dsp.filter.FilterPassType;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.ArrayUtils;


/**
 *
 * @author Stefan
 */
public class BPMLocalize {
    
    private File currentFile;
    private WavFile wavFile;
    
    public BPMLocalize() {
      
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
            
            //WavFilter lowpassFilter = new WavFilter(
            //        currentFile.getPath(), 
            //        FilterPassType.lowpass, 
            //        FilterCharacteristicsType.bessel,
            //        6, -1,100, 0
            //); 
            
            //double [] [] wavData = lowpassFilter.getWavData();
            
            //return wavData;
            return buffer;

        } catch (Exception e) {
            
            System.out.println(e);
        }
        
        return new double[0][0];
        
    }
    
    private double calculate_energy(double[] audioProcessData, int offset, int length) {
        
        /*
        
                         |<------------------ audioProcessData.length ------------------>|
    audioProcessData[]:  0 1 0 1 0 0 0 1 1 1 1 0 0 0 1 0 1 1 1 0 0 1 0 0 1 0 1 1 1 0 0 0 1
                                             ^ < offset
                                             |<----------- length --------->|

        */
        
        double energy = 0.;
        for (int i = offset; i < (offset+length); i++) {
            if (audioProcessData.length > i) {
                energy += audioProcessData[i]*audioProcessData[i];
            }
        }
        energy = energy/length;
        
        return energy;
        
    }
    
    public void beat_detect() {
        
        double const_c = 1.2;
        int minimum_distance = 5;
        int segmentSize = 1024;
        int segments_one_second = 40;
        
        // Audiodaten laden
        double[][] WavData = getwavdata();
        
        // Audiodaten "Länge", d.h. Anzahl der Samples
        // WavData[0=links / 1=rechts][sample]
        // Es wird nur der erste Kanal bei der Berechnung der BPM verwendet.
        double[] audioProcessData = WavData[0];
        int length = audioProcessData.length;
        
        //Die Audiodaten werden in bspw. 1024-Sample (siehe oben) lange Segmente zerteilt.
        int amountSegments = length/segmentSize;
        
        // Energie in den einzelnen Segmenten
        double[] energy_segments = new double[amountSegments];
        for (int current_segment = 0; current_segment < amountSegments; current_segment++) {
            energy_segments[current_segment] = calculate_energy(audioProcessData, segmentSize*current_segment, segmentSize*4);
        }
        
                 try {
                FileWriter fw1 = new FileWriter("energysegments.txt");
                BufferedWriter bw1 = new BufferedWriter(fw1);

                //for (int i : deltaSegments) {
                //    bw1.write(Integer.toString(i)+"\n");
                //}
                
                for (int i = 0; i < energy_segments.length; i++) {
                    bw1.write(Double.toString(energy_segments[i])+"\n");
                }
                
                
                 bw1.close();
                } catch (Exception e) {

                    System.out.println(e);

                } 
                
        // Energie über 1 Sekunde-Blöcken:
        
        // 1 Sekunde entspricht (bei 1024-Samples-Segmenten) etwa 43 Segmenten
        //int segments_one_second = 43;
        //int segments_one_second = (44100/segmentSize);
                 
        
        double[] energy_onesecondsegments = new double[amountSegments];

        double summe = 0.;
        for (int current_segment = 0; current_segment < segments_one_second; current_segment++) {
            summe += energy_segments[current_segment];
        }
        energy_onesecondsegments[0] = summe/segments_one_second;
        
        for (int current_segment = 1; current_segment < amountSegments; current_segment++) {
            if ((current_segment+segments_one_second-1) < amountSegments) {
                summe = summe - energy_segments[current_segment-1] + energy_segments[current_segment+(segments_one_second-1)];
            }
            energy_onesecondsegments[current_segment] = summe/segments_one_second;
        }
        
        
                 try {
                FileWriter fw1 = new FileWriter("energyonesegments.txt");
                BufferedWriter bw1 = new BufferedWriter(fw1);

                //for (int i : deltaSegments) {
                //    bw1.write(Integer.toString(i)+"\n");
                //}
                
                for (int i = 0; i < energy_onesecondsegments.length; i++) {
                    bw1.write(Double.toString(energy_onesecondsegments[i])+"\n");
                }
                
                
                 bw1.close();
                } catch (Exception e) {

                    System.out.println(e);

                } 

        /*
              |<------------------- segments_one_second -------------------->|
              |------|------|------|------|------|------|------|------|------|------|------|------|
                                              ^ (segments_one_second/2)+0
        
                     |<------------------- segments_one_second -------------------->|
              |------|------|------|------|------|------|------|------|------|------|------|------|
                                                     ^ (segments_one_second/2)+1
        
                            |<------------------- segments_one_second -------------------->|
              |------|------|------|------|------|------|------|------|------|------|------|------|
                                                            ^ (segments_one_second/2)+2
                                                            
                                                            ...
        
        */
        int[] energy_beats = new int[amountSegments+(segments_one_second/2)];
        
        for (int current_segment = (segments_one_second/2); current_segment < amountSegments; current_segment++) {
            
            if (energy_segments[current_segment] > const_c*energy_onesecondsegments[current_segment-(segments_one_second/2)]) {
                energy_beats[current_segment] = 1;
            }
            
        }
        
                 try {
                FileWriter fw1 = new FileWriter("beats.txt");
                BufferedWriter bw1 = new BufferedWriter(fw1);

                //for (int i : deltaSegments) {
                //    bw1.write(Integer.toString(i)+"\n");
                //}
                
                for (int i = 0; i < energy_beats.length; i++) {
                    bw1.write(Integer.toString(energy_beats[i])+"\n");
                }
                
                
                 bw1.close();
                } catch (Exception e) {

                    System.out.println(e);

                } 
        
        // Die Anzahl der Segmente zwischen zwei gefundenen Beats sollen nun gezählt werden:
        List<Integer> deltaSegments = new ArrayList<Integer>();
        
        int last_segment_index = 0;
        for (int current_segment = 0; current_segment < amountSegments; current_segment++) {
            if (energy_beats[current_segment] == 1 && energy_beats[current_segment-1] == 0) {
                int found_delta_segment = current_segment - last_segment_index;
                
                if (found_delta_segment > minimum_distance) {
                    deltaSegments.add(found_delta_segment);
                    last_segment_index = current_segment;
                }
            }
            
        }

        
                 try {
                FileWriter fw1 = new FileWriter("anseg.txt");
                BufferedWriter bw1 = new BufferedWriter(fw1);

                for (int i : deltaSegments) {
                   bw1.write(Integer.toString(i)+"\n");
                }

                
                
                 bw1.close();
                } catch (Exception e) {

                    System.out.println(e);

                } 

        
                 
                 
        
        // Wie oft treten die deltaSegments jeweils auf?
        // Definiert wird: Delta's > 2 Sekunden werden ignoriert. (also BPM<30 gibt's nicht)
        int segments_two_second = segments_one_second*2;
        
        int[] amount_of_deltaSegment = new int[segments_two_second];
        
        for (int current_delta : deltaSegments) {
            
            if (current_delta < segments_two_second) {
                amount_of_deltaSegment[current_delta]++;
            }
            
        }
        
        
                 try {
                FileWriter fw1 = new FileWriter("bpmverteilung.txt");
                BufferedWriter bw1 = new BufferedWriter(fw1);

                for (int i = 0; i < amount_of_deltaSegment.length; i++) {
                    bw1.write(Integer.toString(amount_of_deltaSegment[i])+"\n");
                }
                
                
                 bw1.close();
                } catch (Exception e) {

                    System.out.println(e);

                } 
        
        // Das Maximum finden, also das deltaSegment, was am häufigsten gefunden wurde:
        int deltaSegment_max = 0;
        int max_amoung_deltaSegment = 0;
        for (int current_delta = 0; current_delta < segments_two_second; current_delta++) {
            if (amount_of_deltaSegment[current_delta] > max_amoung_deltaSegment) {
                deltaSegment_max = current_delta;
                max_amoung_deltaSegment = amount_of_deltaSegment[current_delta];
            }
        }

        
        System.out.println("Max:: "+deltaSegment_max);
        
        //int neighbor = deltaSegment_max+1;
        //if (amount_of_deltaSegment[deltaSegment_max+1] < amount_of_deltaSegment[deltaSegment_max-1]) {
        //    neighbor = deltaSegment_max-1;
        //}

        //double gewichtetesMittel = (deltaSegment_max*amount_of_deltaSegment[deltaSegment_max]+neighbor*amount_of_deltaSegment[neighbor]) / (double) (amount_of_deltaSegment[neighbor]+amount_of_deltaSegment[deltaSegment_max]);
        //System.out.println("gewichtetesMittel:: "+gewichtetesMittel);
        
        // Umrechnung Anzahl d. delta-Segmente in delta-Samples:
        double deltaSamples_max = deltaSegment_max*segmentSize;
        
        // Umrechnung in dt:
        double deltaSeconds = deltaSamples_max / 44100.;
        
        double bpm = 60. / deltaSeconds;
        
        while (bpm > 200) {
            bpm /= 2;
        }
    
        int bpm_round = (int) Math.round(bpm);
        
        System.out.println("Found: "+bpm_round+" BPM!");
   
        
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
    
    public boolean isReady() {
        
        //if (this.currentFile != null && this.locateBPM != 0) {
            return true;
        //} else {
        //    return false;            
        //}
        
    }
    
}
