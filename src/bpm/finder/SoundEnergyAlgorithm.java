package bpm.finder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Stefan
 */

public class SoundEnergyAlgorithm {
    
    private File currentFile;
    private WavFile wavFile;
    
    private double[][] getWavData() {
        
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
        
        // Energieberechnung:
        // Summe der Quadrate
        double energy = 0.;
        for (int i = offset; i < (offset+length); i++) {
            if (audioProcessData.length > i) {
                energy += audioProcessData[i]*audioProcessData[i];
            }
        }

        return energy;

    }
    
    public int get_bpm() {
        
        // Einteilung des gesamten Audiosignals in Segmente folgender Größe:
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
        
        // Energie in den einzelnen Segmenten berechnen
        double[] energy_segments = new double[amountSegments];
        for (int i = 0; i < amountSegments; i++) {
            energy_segments[i] = calculate_energy(audioProcessData, segmentSize*i, segmentSize*4);
        }
               
        // Um jedes Segment herum soll ein durchschnittliches Energieniveau
        // berechnet werden. Die Array, die diese Energiewerte beinhaltet
        // wird hiermit vorbereitet. Sie hat die selbe Länge, wie die Array,
        // welche die Energiewerte für die einzelnen Segmente trägt.
        double[] energy_averageniveau_segments = new double[amountSegments];

        // Durchschnittliche Energieniveaus werden berechnet
        // => Durchschnitt: 50 Segmente werden zusammenaddiert und die Summe
        //                  wird durch 50 geteilt.
        
        // Hier werden zunächst die ersten 50 Segmente aufaddiert und durch die Anzahl
        // 50 geteilt. Damit ergibt sich das Energieniveau um das Segment 0.
        double summe = 0.;
        for (int i = 0; i < average_energy_level_segments; i++) {
            summe += energy_segments[i];
        }
        energy_averageniveau_segments[0] = summe/average_energy_level_segments;
        
        // Für alle anderen Werte:
        // Es wird immer das vorderste Element von der bisherigen Summe abgezogen
        // und das nächste hinzuaddiert.
        for (int i = 1; i < amountSegments; i++) {
            if ((i+average_energy_level_segments-1) < amountSegments) {
                summe = summe - energy_segments[i-1] + energy_segments[i+(average_energy_level_segments-1)];
            }
            energy_averageniveau_segments[i] = summe/average_energy_level_segments;
        }
        
        
        // BEAT ERKENNUNG
        // Ab jetzt wird immer ein Segment x betrachtet.
        // Das zugehörige durchschnittliche Energieniveau für dieses Segment befindet
        // sich in der energy_averageniveau_segments-Array 25 Stellen weiter links,
        // da so ein durchschnittliches Energieniveau ausgewählt wird, das mittig um das 
        // aktuelle Segment liegt:
        
        /*
        average_energy_level_segments = 50
        
                 |<--------------- average_energy_level_segments -------------->|
              ...|------|------|------|------|------|------|------|------|------|------|------|------|...
                                              ^ (average_energy_level_segments/2)+0 = 25
        
                        |<--------------- average_energy_level_segments -------------->|
              ...|------|------|------|------|------|------|------|------|------|------|------|------|...
                                                     ^ (average_energy_level_segments/2)+1 = 26
        
                               |<--------------- average_energy_level_segments -------------->|
              ...|------|------|------|------|------|------|------|------|------|------|------|------|...
                                                            ^ (average_energy_level_segments/2)+2 = 27
                                                            
                                                            ...
        
        */
        // In der Array energy_beats wird 1 für BEAT und 0 für KEIN BEAT gespeichert.
        int[] energy_beats = new int[amountSegments+(average_energy_level_segments/2)];
        
        for (int i = (average_energy_level_segments/2); i < amountSegments; i++) {
            
            // Wenn die Energie im Segment größer ist als das durchschnittliche Energieniveau
            // in 50 Segmenten um das Segment herum, dann wurde ein Beat gefunden!
            if (energy_segments[i] > energy_averageniveau_segments[i-(average_energy_level_segments/2)]) {
                energy_beats[i] = 1;
            }
            
        }

        // Die Anzahl der Segmente zwischen zwei gefundenen Beats sollen nun gezählt werden:
        List<Integer> deltaSegments = new ArrayList<Integer>();
        int minimum_distance = 5; // Mindesabstand der Beats,
                                  // um kleine Störungen zu filtern...

        // In last_segment_index steht die letzte gefundene Segment-Nr. gespeichert, bei
        // der ein Beat gefunden wurde...
        int last_segment_index = 0;
        for (int i = 0; i < amountSegments; i++) {
            
            // Linke Intervallgrenze von einem gefundenen "Beat-Paket"
            if (energy_beats[i] == 1 && energy_beats[i-1] == 0) {
                
                // Berechne Intervall in Segmenten zwischen dem aktuellen Segment
                // und dem letzten gefundenen Beat.
                int found_delta_segment = i - last_segment_index;
                
                // Um kleine Störungen zu Filtern, wenn innerhalb eines
                // "Beat-Pakets" kurz ein Wert auf 0 sinkt.
                if (found_delta_segment > minimum_distance) {
                    
                    // Das gefundene Intervall an die Liste anhängen
                    deltaSegments.add(found_delta_segment);
                    
                    // Das letzte Index speichern.
                    last_segment_index = i;
                }
                
            }
            
        }

        
        // Wie oft treten die deltaSegments jeweils auf?
        // Definiert wird: Delta's > 2 Sekunden werden ignoriert. (also BPM<30 gibt's nicht)
        int max_deltasegment = average_energy_level_segments*4;
        
        int[] amount_of_deltaSegment = new int[max_deltasegment];
        
        // Durch alle deltaSegments laufen
        for (int current_delta : deltaSegments) {
            
            // Wenn das aktuelle Intervall an Segmenten nicht den
            // Maximalwert übersteigt:
            if (current_delta < max_deltasegment) {
                
                // Dann zähle das gefundene Intervall
                amount_of_deltaSegment[current_delta]++;
            }
            
        }
                
        // Das Maximum finden, also das deltaSegment, welches am Häufigsten gefunden wurde:
        int deltaSegment_max = 0;
        int max_amount_deltaSegment = 0;
        for (int current_delta = 0; current_delta < max_deltasegment; current_delta++) {
            if (amount_of_deltaSegment[current_delta] > max_amount_deltaSegment) {
                deltaSegment_max = current_delta;
                max_amount_deltaSegment = amount_of_deltaSegment[current_delta];
            }
        }

        // Umrechnung Anzahl d. delta-Segmente in delta-Samples:
        double deltaSamples_max = deltaSegment_max*segmentSize;
        
        // Umrechnung in dt:
        double deltaSeconds = deltaSamples_max / 44100.;
        
        double bpm = 60. / deltaSeconds;
        
        // Solange durch 2 teilen, bis BPM<200
        while (bpm > 200) {
            bpm /= 2;
        }
    
        // BPM runden
        int bpm_round = (int) Math.round(bpm);
        
        // BPM ausgeben.
        return bpm_round;

    }
    
    public boolean setWAV(File file) {
    
        // Mit dieser Funktion wird der Klasse mitgeteilt,
        // welche WAV-Datei geladen wurde.
        this.currentFile = file;
        
        try {
            // Versuche, die Wav-Datei zu öffnen.
            this.wavFile = WavFile.openWavFile(currentFile);
            wavFile.close();
        } catch (Exception e) {
            // Exception: WAV-Datei konnte nicht geöffnet werden.
            // return false!
            System.out.println(e);
            this.currentFile = null;
            return false;
        }
        
        return true;

    }
 
    public boolean isReady() {
        
        // Ist der Algorithmus bereit, d.h. hat er alle nötigen Werte
        // mitgeteilt bekommen, um starten zu können?
        
        // Wenn keine Datei gesetzt wurde, dann ist er nicht bereit,
        // -> return false;
        if (this.currentFile != null) {
            return true;
        } else {
            return false;            
        }
        
    }
    
}