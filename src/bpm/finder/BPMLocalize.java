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
    
    public double[][] getWavData() {
        
        try {
            
            // WAV-Datei öffnen.
            wavFile = WavFile.openWavFile(currentFile);
            
            // Die Gesamtanzahl der Samples in der zu untersuchenden WAV-Datei
            long totalFrames = wavFile.getNumFrames();
            int totWAVsamples = (int) totalFrames;
            
            // Anzahl der Kanäle (1=Mono, 2=Stereo)
            int numChannels = wavFile.getNumChannels();
            
            // Buffer der entsprechenden Größe anlegen:
            // -> erste Dimension der Array: Kanalauswahl
            // -> zeite Dimension der Array: einzelne Sample-Werte
            double[][] buffer = new double[numChannels][totWAVsamples];
            
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

        return energy;
        
    }
    
    public void beat_detect() {
        
        int segmentSize = 441;
        int average_energy_level_segments = 50; // 50 Segmente werden zusammengefasst
                                                // zur Berechnung eines durchschnittlichen
                                                // Energieniveaus
        
        // Audiodaten laden
        double[][] WavData = getWavData();
        
        // Audiodaten "Länge", d.h. Anzahl der Samples
        // WavData[0=links / 1=rechts][sample]
        // Es wird nur der erste Kanal BPM verwendet.
        double[] audioProcessData = WavData[0];
        int length = audioProcessData.length;
        
        // Die Audiodaten werden in 441-Sample (siehe oben) lange Segmente zerteilt.
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
                
        
        double[] energy_averageniveau_segments = new double[amountSegments];

        double summe = 0.;
        for (int current_segment = 0; current_segment < average_energy_level_segments; current_segment++) {
            summe += energy_segments[current_segment];
        }
        energy_averageniveau_segments[0] = summe/average_energy_level_segments;
        
        for (int current_segment = 1; current_segment < amountSegments; current_segment++) {
            if ((current_segment+average_energy_level_segments-1) < amountSegments) {
                summe = summe - energy_segments[current_segment-1] + energy_segments[current_segment+(average_energy_level_segments-1)];
            }
            energy_averageniveau_segments[current_segment] = summe/average_energy_level_segments;
        }
        
        
                 try {
                FileWriter fw1 = new FileWriter("energyonesegments.txt");
                BufferedWriter bw1 = new BufferedWriter(fw1);

                //for (int i : deltaSegments) {
                //    bw1.write(Integer.toString(i)+"\n");
                //}
                
                for (int i = 0; i < energy_averageniveau_segments.length; i++) {
                    bw1.write(Double.toString(energy_averageniveau_segments[i])+"\n");
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
        int[] energy_beats = new int[amountSegments+(average_energy_level_segments/2)];
        
        for (int current_segment = (average_energy_level_segments/2); current_segment < amountSegments; current_segment++) {
            
            if (energy_segments[current_segment] > energy_averageniveau_segments[current_segment-(average_energy_level_segments/2)]) {
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
        int minimum_distance = 5; // Mindesabstand der Beats,
                                  // um kleine Störungen zu filtern...
                
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
        int segments_two_second = average_energy_level_segments*4;
        
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
