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

/**
 *
 * @author Stefan
 */
public class BPMLocalize {
    
    private File currentFile;
    private int locateBPM;
    private WavFile wavFile;

    public BPMLocalize() {
      
    }
    
    public void localize(int BPM) {
        
        // 2 Sekunden Taktsignal generieren
        int fs = 44100;
        int td = (int) ((60./BPM) * fs);
        //int taktlaenge_s = 1;
        double[][] taktsignal = new double[1][td*2+1];
        for (int i = 0; i < td*2+1; i++) {
            if (i%td == 0) {
                taktsignal[0][i] = 1;
            }
        }
        
        double[] falten = readwavandconvolve(taktsignal);
        
        saveWAV("test.wav", falten);
        
                try {
                FileWriter fw1 = new FileWriter("taktsignal.txt");
                BufferedWriter bw1 = new BufferedWriter(fw1);

                for (int i = 0; i < taktsignal[0].length; i++) {
                    bw1.write(Double.toString(taktsignal[0][i])+"\n");
                }
                
                 bw1.close();
                } catch (Exception e) {

                    System.out.println(e);

                } 
                
                
    }
    
    public void hall() {
        
        // Zum Faltung testen :)
        
        File impAnt = new File("impantwort.wav");
        
        try {
            
            WavFile impWAV = WavFile.openWavFile(impAnt);
            
            //impWAV.display();

            int numChannels = impWAV.getNumChannels();
            long totalFrames = impWAV.getNumFrames();
            int samplelng = (int) totalFrames;

            double[][] d1imp = new double[numChannels][samplelng];

            int framesRead;

            do
            {
               // Read frames into buffer
               framesRead = impWAV.readFrames(d1imp, samplelng);
            }
            while (framesRead != 0);
        

            double[] gefaltet = readwavandconvolve(d1imp);
            
            saveWAV("hall.wav", gefaltet);
               
            
        } catch (Exception e) {
            
            System.out.println(e);

        }   
                
                
    }
    
    public void saveWAV(String filename, double[] data) {
        
        try {
               
            int sampleRate = 44100;    // Samples per second
            int numFrames = data.length;
            WavFile wrwavFile = WavFile.newWavFile(new File(filename), 1, numFrames, 16, sampleRate);

            double[] wrbuffer = new double[100];
            
            int frameCounter = 0;
                           
            int lng = data.length;
            
            // Loop until all frames written
            while (frameCounter < numFrames)
            {
               // Determine how many frames to write, up to a maximum of the buffer size
               long remaining = wrwavFile.getFramesRemaining();
               int toWrite = (remaining > 100) ? 100 : (int) remaining;

               // Fill the buffer, one tone per channel
               for (int s=0 ; s<toWrite ; s++, frameCounter++)
               {
                  wrbuffer[s] = data[frameCounter]*(1./(lng*8));
               }

               // Write the buffer
               wrwavFile.writeFrames(wrbuffer, toWrite);
            }
            
            wrwavFile.close();
            
        } catch (Exception e) {
            
            System.out.println(e);

        }
        
    }
    
    public double[] readwavandconvolve(double[][] signal) {
        
        /*
        
        signal[][]        
               ^ Kanal (links/rechts)
                 ^ Array mit Samplewerten
        */

        try {
            
            // WAV-Datei öffnen.
            wavFile = WavFile.openWavFile(currentFile);
            
            // Die Gesamtanzahl der Samples in der zu untersuchenden WAV-Datei
            long totalFrames = wavFile.getNumFrames();
            int totWAVsamples = (int) totalFrames;
            
            // DEF: Es wird nur der linke/erste Kanal untersucht.
            // Also signal[0] = Array(...)
            
            // 1) Das WavFile wird in Segmente zerlegt. Für
            //    jedes Segment soll gelten, dass es doppelt so lang ist,
            //    wie das stets kurze Signal signal.
            
            int signal_laenge = signal[0].length;
            int segment_laenge = signal_laenge*2;
                        
            // 2) Die Anzahl der Segmente, die aus der WAV gebildet werden können:
            int anzahl_segmente = (int) (Math.ceil(((double) totWAVsamples)/((double) segment_laenge)));
            
            // 3) Das Ergebnis der linearen Faltung ist um (signal_laenge-1) Samples länger
            //    als die Anzahl der Samples der WAV Datei.
            int faltung_anzahl_samples = anzahl_segmente*segment_laenge + (signal_laenge-1);
            //    Erstelle Array für das Ergebnis der Faltung:
            double[] faltung_komplett_real = new double[faltung_anzahl_samples];
            //double[] faltung_komplett_imag = new double[faltung_anzahl_samples];
            
            // Mit 0 initialisieren.
            for (int i = 0; i < faltung_anzahl_samples; i++) {
                faltung_komplett_real[i] = 0.0;
                faltung_komplett_real[i] = 0.0;
            }
            
            // 4) Das Ergebnis jeder linearen Teilfaltung des einzelnen Segments mit dem Signal
            //    signal hat eine Länge von signal_laenge*3-1
            int segment_faltung_laenge = signal_laenge*3 - 1;
            
            // 5) Das Signal signal muss auf die Länge segment_faltung_laenge gebracht werden,
            //    damit die schnelle Faltung der linearen Faltung entspricht.
            //    zero-padding: signal mit 0 bis zur erreichten Länge auffüllen
            double[] zeropadded_signal = new double[segment_faltung_laenge];
            for (int i = 0; i < segment_faltung_laenge; i++) {
                zeropadded_signal[i] = 0.0;
                if (i < signal_laenge) {
                    zeropadded_signal[i] = signal[0][i];
                }
            }

            // 6) Buffer vorbereiten, in das nacheinander die Segmente aus der WAV geladen
            //    werden. Länge muss identisch sein mit dem zero-padded signal!
            int numChannels = wavFile.getNumChannels();
            double[][] aktuelles_segment = new double[numChannels][segment_faltung_laenge];
            for (int i = 0; i < segment_faltung_laenge; i++) {
                aktuelles_segment[0][i] = 0.0;
            }
            
            // 7) Imaginärteil-Array für das Signal signal anlegen und signal in den Frequenzbereich
            //    transformieren.
            double[] zeropadded_signal_imag = new double[segment_faltung_laenge];
            fft.transform(zeropadded_signal, zeropadded_signal_imag);


            // Variable für die Anzahl der Samples, die aus der WAV gelesen wurden.
            int samplesRead;

            // Zeiger auf die Position innerhalb der Array für das Faltungsergebnis
            int faltung_index = 0;
            
            // Variablen für das ermittelte Ergebnis 
            double[] segmentierte_faltung_ergebnis_real = new double[segment_faltung_laenge];
            double[] segmentierte_faltung_ergebnis_imag = new double[segment_faltung_laenge];
                    
            //
            // *** START SEGEMENTIERTE FALTUNG ***
            //
            do
            {
               
               // Segment laden.
               samplesRead = wavFile.readFrames(aktuelles_segment, segment_laenge);
              
               
               // Nur wenn noch mehr als 0 Samples gelesen wurden...
               if (samplesRead > 0) {
                   
                    // Wenn weniger Samples gelesen worden sind, als das Segment lang ist,
                    // werden die überstehenden Stellen mit 0 aufgefüllt. (Für das letzte Segment)
                    for (int i = samplesRead-1; i < segment_faltung_laenge; i++) {
                        aktuelles_segment[0][i] = 0.0;
                    }
                   
                    // Temporäre Array für den Imaginärteil der Teil-Faltungsergebnisse
                    // -> mit 0 initialisieren
                    double[] aktuelles_segment_imag = new double[segment_faltung_laenge];
                    for (int i = 0; i < segment_faltung_laenge; i++) {
                        aktuelles_segment_imag[i] = 0.0;
                    }

                    // Aktuelles Segment in den Frequenzbereich transformieren:
                    fft.transform(aktuelles_segment[0], aktuelles_segment_imag);
                    
                    // Multiplikation im Frequenzbereich
                    for (int i = 0; i < segment_faltung_laenge; i++) {
                        segmentierte_faltung_ergebnis_real[i] = aktuelles_segment[0][i]*zeropadded_signal[i] - aktuelles_segment_imag[i]*zeropadded_signal_imag[i];
                        segmentierte_faltung_ergebnis_imag[i] = aktuelles_segment[0][i]*zeropadded_signal_imag[i] + zeropadded_signal[i]*aktuelles_segment_imag[i];
                    }
                    
                    // Ergebnis der Multiplikation der Spektren (= Ergebnis der Teilfaltung)
                    // wieder in den Zeitbereich transformieren:
                    fft.inverseTransform(segmentierte_faltung_ergebnis_real, segmentierte_faltung_ergebnis_imag);

                    // Overlap Add
                    // Die Bereiche, die segment_faltung_laenge länger ist als segment_laenge
                    // werden aufaddiert.
                    for (int i = 0; i < segment_faltung_laenge; i++) {
                        // hier overlap add!
                        if ((i < signal_laenge) && (faltung_index > signal_laenge)) {
                            faltung_komplett_real[faltung_index-1-(signal_laenge-1)+i] += segmentierte_faltung_ergebnis_real[i];
                            //faltung_komplett_imag[faltung_index-1-(signal_laenge-1)+i] += segmentierte_faltung_ergebnis_imag[i];
                        } else {
                            faltung_komplett_real[faltung_index] = segmentierte_faltung_ergebnis_real[i];
                            //faltung_komplett_imag[faltung_index] = segmentierte_faltung_ergebnis_imag[i];
                            faltung_index++;
                        }

                    }
                    
                    // Temporärer Imaginärteil aufräumen...
                    aktuelles_segment_imag = null;
                    
                    double fortschritt = ((double) faltung_index/faltung_anzahl_samples)*100;
                    System.out.println(fortschritt+"%");
               }
               
               
            }
            while (samplesRead != 0);

            // WAV-Datei schließen.
            wavFile.close();

            return faltung_komplett_real;
            
        } catch (Exception e) {
            
            System.out.println(e);

        }
        
        return new double[0];
        
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
