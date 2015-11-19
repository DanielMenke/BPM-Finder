/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpm.finder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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
    
    public void bpm_detect(int aroundSample) {
        
        // Funktioniert ganz gut mit elektronischer Musik, viertelbetont, ...
        // in aroundSample muss der Startwert in Samples angegeben werden
        // ab diesem werden 5 sekunden aus der Wav geladen und verrechnet

        int fs = 44100;
        
        double[] energyofBPM = new double[190];
        
        double[][] wavdaten = getwavdata(aroundSample, 5*44100);

        fft.transform(wavdaten[0], wavdaten[1]);

        System.out.println("bpm_detect: START!");
        
        for (int BPM = 110; BPM < 140; BPM=BPM+1) {
            
            // Taktsignal generieren
            int td = (int) ((60./BPM) * fs);
            double[][] taktsignal = new double[2][wavdaten[0].length];
            for (int i = 0; i < wavdaten[0].length; i++) {
                    taktsignal[0][i] = 0.;
                    taktsignal[1][i] = 0.;
                if (i%td == 0) {
                    taktsignal[0][i] = 1.;
                    taktsignal[1][i] = 1.;
                }
            }

            fft.transform(taktsignal[0], taktsignal[1]);
            
            // Multiplikation im Frequenzbereich
            double energy = 0;
            for (int i = 0; i < wavdaten[0].length; i++) {
                double ges_real = wavdaten[0][i]*taktsignal[0][i] - wavdaten[1][i]*taktsignal[1][i];
                double ges_imag = wavdaten[0][i]*taktsignal[1][i] + taktsignal[0][i]*wavdaten[1][i];
                energy += Math.sqrt( (ges_real*ges_real) + (ges_imag*ges_imag) );
            }
            
            
            energyofBPM[BPM] = energy;
            System.out.println("bpm_detect: checked "+BPM+"!");

        }
        
        System.out.println("bpm_detect: ENDE!");
            
        
        // in energyofBPM[] stehen für jede getestete BPM der Energiewert
        // meistens ist der Peak hierbei der Treffer...
        
             try {
                FileWriter fw1 = new FileWriter("bpmenergies.txt");
                BufferedWriter bw1 = new BufferedWriter(fw1);

                for (int i = 0; i < energyofBPM.length; i++) {
                    bw1.write(Double.toString(energyofBPM[i])+"\n");
                }
                
                 bw1.close();
                } catch (Exception e) {

                    System.out.println(e);

                } 
                       
                
    }
    
    public double[][] getwavdata(int startSample, int laenge) {
        
        try {
            
            // WAV-Datei öffnen.
            wavFile = WavFile.openWavFile(currentFile);
            
            // Die Gesamtanzahl der Samples in der zu untersuchenden WAV-Datei
            //long totalFrames = wavFile.getNumFrames();
            //int totWAVsamples = (int) totalFrames;
            
            int numChannels = wavFile.getNumChannels();

            double[][] buffer = new double[numChannels][laenge];
            
            wavFile.readFrames(new double[numChannels][startSample], startSample);      

            // Segment in den Buffer laden.
            int samplesRead = wavFile.readFrames(buffer, laenge);

            // WAV-Datei schließen.
            wavFile.close();
            
            return buffer;

        } catch (Exception e) {
            
            System.out.println(e);
        }
        
        return new double[0][0];
        
    }
    
    public void beat_detect() {
        
        try {
            
            // WAV-Datei öffnen.
            wavFile = WavFile.openWavFile(currentFile);
            
            // Die Gesamtanzahl der Samples in der zu untersuchenden WAV-Datei
            long totalFrames = wavFile.getNumFrames();
            int totWAVsamples = (int) totalFrames;
            
            int numChannels = wavFile.getNumChannels();
            
            int samplesStep = 1024;
            int samplesRead;
            
            double[][] buffer = new double[numChannels][samplesStep];
            
            double[] energyBuffer = new double[43];
            for (int i = 0; i < energyBuffer.length; i++) {
                energyBuffer[i] = 0.0;
            }
            
            int[] beats = new int[0];
            
            int segmentposition = 0;
            
            do
            {

               // Segment in den Buffer laden.
               samplesRead = wavFile.readFrames(buffer, samplesStep);
               segmentposition = segmentposition+1;

               // Nur wenn noch mehr als 0 Samples gelesen wurden...
               if (samplesRead > 0) {

                    // Lokale durchschnittliche Energie in den 1024 Samples:
                    double localEnergy = 0;
                    for (int i = 0; i < samplesStep; i++) {
                        localEnergy += ((buffer[0][i]*buffer[0][i]) + (buffer[1][i]*buffer[1][i]));
                    }
 
                    // Durchschnittliche Energie im Energiebuffer (ohne neuen Energiewert):
                    double EnergyInBuffer = 0;
                    for (int i = 0; i < energyBuffer.length; i++) {
                        EnergyInBuffer += (energyBuffer[i]);
                    }
                    EnergyInBuffer *= (1./energyBuffer.length);

                    // Varianz der Energie-Werte im Energiebuffer:
                    double EnergyBufferVarianz = 0;
                    for (int i = 0; i < energyBuffer.length; i++) {
                        double std = energyBuffer[i] - EnergyInBuffer;
                        std *= std;
                        EnergyBufferVarianz += std;
                    }
                    EnergyBufferVarianz *= (1./energyBuffer.length);
                    double EnergyBufferStd = Math.sqrt(EnergyBufferVarianz);

                    // Konstante C:
                    double c = (-0.0025714*EnergyBufferStd) + 1.8142857;
                   
                    // Energie-Buffer nach rechts verschieben
                    for (int i = energyBuffer.length-1; i > 0; i--) {
                        energyBuffer[i] = energyBuffer[i-1];
                    }
                    energyBuffer[0] = localEnergy;

                    // Beat wenn lokale Energie größer ist als c*Durchschnitt im Buffer:
                    if (localEnergy > (c*EnergyInBuffer)) {

                        int[] stelle = new int[1];
                        stelle[0] = segmentposition*samplesStep - (samplesStep/2);
                        beats = ArrayUtils.addAll(beats, stelle);

                    }

               }
               
               
            }
            while (samplesRead != 0);

            // WAV-Datei schließen.
            wavFile.close();
            
            // In Beats stehen nacheinander die Positionen (angegeben in Samples)
            // innerhalb der Wav, an denen Beats gefunden wurden...
            
            
//            double[] samplesbetween = new double[beats.length-1];
//            for (int i = 0; i < beats.length-1; i++) {
//                samplesbetween[i] += (beats[i+1]-beats[i]);
//            }
            
//            
//            double[] samplesbetween = new double[beats.length];
//            for (int i = 0; i < beats.length-1; i++) {
//                samplesbetween[i] = beats[i+1] - beats[i];
//            }
//            
//            double summe = 0;
//            for (int i = 0; i < samplesbetween.length; i++) {
//                summe += samplesbetween[i];
//            }
//            double average = summe/samplesbetween.length;
            
//            boolean foundSth = true;
//            while (foundSth) {
//                foundSth = false;
//                for (int i = 0; i < samplesbetween.length; i++) {
//                    if (samplesbetween[i] == 1024. || samplesbetween[i] > average+1024 || samplesbetween[i] < average-1024) {
//                        samplesbetween = ArrayUtils.remove(samplesbetween, i);
//                        foundSth = true;
//                        break;
//                    }
//                }
//            }
            
//            summe = 0;
//            for (int i = 0; i < samplesbetween.length; i++) {
//                summe += samplesbetween[i];
//            }
//            average = summe/samplesbetween.length;
            
            
//            double[] samplesaddbyadd = new double[samplesbetween.length+1];
//            for (int i = 0; i <= samplesbetween.length; i++) {
//                if (i == 0) {
//                    samplesaddbyadd[i] = 0.;
//                } else {
//                    samplesaddbyadd[i] = samplesaddbyadd[i-1] + samplesbetween[i-1];
//                }
//            }
            
//            double[] doubles = new double[(samplesaddbyadd.length)*2];
//            for(int i=0; i<samplesaddbyadd.length; i++) {
//                int p = 2*(i);
//                doubles[p] = (i);
//                doubles[p+1] = samplesaddbyadd[i];
//            }
            
//            RegressionResult zet = calculateLinearRegression(doubles);
//            System.out.println(zet.formel);
//                        
//            double bpm = (60.*44100.)/zet.b;
            
//            
//            double bpm = (60.*44100.)/average;
//            while (bpm > 180) {
//                bpm /= 2;
//            }
//            
//            System.out.println(bpm);
//            


            
            try{
                FileWriter fw1 = new FileWriter("beats.txt");
                BufferedWriter bw1 = new BufferedWriter(fw1);

                for (int i = 0; i < beats.length; i++) {
                    bw1.write(Double.toString(beats[i])+"\n");
                }
                
                 bw1.close();
            } catch (Exception e) {
            
            System.out.println(e);

             }
            


            
            
        } catch (Exception e) {
            
            System.out.println(e);

        }
        
    }
    
    public void beat_freq_detect() {
        
        /*
        
            Funktioniert nicht so gut.
            Lieber beat_detect() ; also nur Zeitbereich...
        
        */
        
        try {
            
            // WAV-Datei öffnen.
            wavFile = WavFile.openWavFile(currentFile);
            
            // Die Gesamtanzahl der Samples in der zu untersuchenden WAV-Datei
            //long totalFrames = wavFile.getNumFrames();
            //int totWAVsamples = (int) totalFrames;
            
            int numChannels = wavFile.getNumChannels();
            
            int samplesStep = 1024;
            int samplesRead;
            
            double[][] buffer = new double[numChannels][samplesStep];
            
            double[] energyBuffer = new double[1024];
            for (int i = 0; i < energyBuffer.length; i++) {
                energyBuffer[i] = 0.0;
            }
            
            double[][] subband_energy_history = new double[32][43];
            
            int[] beats = new int[0];
            
            int segmentposition = 0;
            
            do
            {

               // Segment in den Buffer laden.
               samplesRead = wavFile.readFrames(buffer, samplesStep);
               segmentposition = segmentposition+1;

               // Nur wenn noch mehr als 0 Samples gelesen wurden...
               if (samplesRead > 0) {

                    fft.transform(buffer[0], buffer[1]);
                    for (int i = 0; i < samplesStep; i++) {
                        energyBuffer[i] = Math.sqrt(buffer[0][i]*buffer[0][i] + buffer[1][i]*buffer[1][i]);
                    }
                    System.out.println("did fft");
                    
                    boolean foundinanothersubband = false;                    
                    for (int i = 0; i < subband_energy_history.length; i++) {
                        
                        double subband_energy = 0;
                        for (int k = (i*subband_energy_history.length); k < ((i+1)*subband_energy_history.length); k++) {
                            subband_energy += energyBuffer[k];
                        }
                        subband_energy *= ((double)subband_energy_history.length/(double)samplesStep);
                        
                        double average_subband_energy = 0;
                        for (int k = 0; k < subband_energy_history[i].length; k++) {
                            average_subband_energy += subband_energy_history[i][k];
                        }
                        average_subband_energy *= 1./subband_energy_history[i].length;
   
                        for (int k = subband_energy_history[i].length-1; k > 0; k--) {
                            subband_energy_history[i][k] = subband_energy_history[i][k-1];
                        }
                        subband_energy_history[i][0] = subband_energy;
                        
                        double c = 5;
                        
                        if ((subband_energy > (c*average_subband_energy)) && foundinanothersubband == false) {
                            int[] stelle = new int[1];
                            stelle[0] = segmentposition*samplesStep - (samplesStep/2);
                            beats = ArrayUtils.addAll(beats, stelle);
                            foundinanothersubband = true;
                        }

                    }

               }
               
               
            }
            while (samplesRead != 0);

            // WAV-Datei schließen.
            wavFile.close();

            
        } catch (Exception e) {
            
            System.out.println(e);

        }
         
    }
    
    private static final int SP = 4;
    static RegressionResult calculateLinearRegression( double[] xyArr )
    {
      if( xyArr == null || xyArr.length < 2 || xyArr.length % 2 != 0 ) return null;

      int    n   = xyArr.length / 2;
      double xs  = 0;
      double ys  = 0;
      double xqs = 0;
      double yqs = 0;
      double xys = 0;

      for( int i=0; i < xyArr.length; i+=2 ) {
         xs  += xyArr[i];
         ys  += xyArr[i+1];
         xqs += xyArr[i]   * xyArr[i];
         yqs += xyArr[i+1] * xyArr[i+1];
         xys += xyArr[i]   * xyArr[i+1];
      }

      RegressionResult abr = new RegressionResult();
      double xm  = xs / n;
      double ym  = ys / n;
      double xv  = xqs / n - (xm * xm);
      double yv  = yqs / n - (ym * ym);
      double kv  = xys / n - (xm * ym);
      abr.rr     = Math.min( (kv * kv) / (xv * yv), 1 );
      abr.b      = kv / xv;
      abr.a      = ym - abr.b * xm;
      abr.titel  = "Lin";
      abr.formel = "y = " + roundSignificant( abr.a, SP ) + " + " + roundSignificant( abr.b, SP ) + " * x";
      abr.approxFunction = new ApproxFunction() {
         public double execute( double a, double b, double x ) {
            return a + b * x;
         }
      };

      return abr;
    }
    static class RegressionResult
    {
      double a;
      double b;
      double rr;
      String titel;
      String formel;
      ApproxFunction approxFunction;
    }
    interface ApproxFunction
    {
      double execute( double a, double b, double x );
    }
    private static double roundSignificant( double d, int significantPrecision )
    {
      if( d == 0 || significantPrecision < 1 || significantPrecision > 14 ) return d;
      double mul10 = 1;
      double minVal = Math.pow( 10, significantPrecision - 1 );
      while( Math.abs( d ) < minVal ) {
         mul10 *= 10;
         d *= 10;
      }
      return Math.round( d ) / mul10;
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

            double[] gefaltet = readwavandconvolve(d1imp, 700000, 710000);
            
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
        Nicht mehr benötigt :( So viel Arbeit...
        */
        
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
    
    public double[] readwavandconvolve(double[][] signal, int sampleStart, int sampleStop) {
        
        /*
        Nicht mehr benötigt :( So viel Arbeit...
        */
        
        /*
        
        signal[][]        
               ^ Kanal (links/rechts)
                 ^ Array mit Samplewerten
        */

        try {
            
            // WAV Ausschnit Länge:
            int ausschnitt_laenge = sampleStop - sampleStart;
            
            // WAV-Datei öffnen.
            wavFile = WavFile.openWavFile(currentFile);
            
            // DEF: Es wird nur der linke/erste Kanal untersucht.
            // Also signal[0] = Array(...)
            
            // 1) Das WavFile wird in Segmente zerlegt. Für
            //    jedes Segment soll gelten, dass es doppelt so lang ist,
            //    wie das stets kurze Signal signal.
            
            int signal_laenge = signal[0].length;
            int segment_laenge = signal_laenge*2;
                        
            // 2) Die Anzahl der Segmente, die aus der WAV gebildet werden können:
            int anzahl_segmente = (int) (Math.ceil(((double) ausschnitt_laenge)/((double) segment_laenge)));
            
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
                    
            int segment_nr = 0;
            
            wavFile.readFrames(new double[numChannels][sampleStart], sampleStart);

            //
            // *** START SEGEMENTIERTE FALTUNG ***
            //
            do
            {
               
               segment_nr++;

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
            while (segment_nr < anzahl_segmente);

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
