package bpm.finder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import jwave.Transform;
import jwave.transforms.FastWaveletTransform;
import jwave.transforms.wavelets.daubechies.Daubechies4;

/**
 *
 * @author Stefan
 */
public class WaveletAlgorithm {
    
    private File currentFile;
    private WavFile wavFile;
       
    private double[] process_coefficients(double[] detection_function, double[] decomposition, int start, int end) {

        // Tiefpassfilter und Unterabtastung in einer Array
        int amount_of_samples = end-start;
        int total_samples = detection_function.length;
        
        // Vorbereiten der Array für das unterabgetastete Signal
        double[] undersamplearray = new double[total_samples];
        
        // interval beinhaltet die Ordnung der Unterabtastung
        // bspw. 2 => 2-fach Unterabtastung: jedes zweite Sample
        //       4 => 4-fach Unterabtastung: jedes vierte Sample
        int interval = amount_of_samples / total_samples;

        // Hilfszeiger für die Array für das unterabgetastete Signal
        int pointer = 0;
        
        // lasty speichert den letzten Ausgangswert des TP
        // (z^-1 für den Tiefpassfilter)
        double lasty = 0;
        
        // Koeffizientenfolge wird Element für Element durchgegangen
        for (int i = start; i < end; i++) {
            
            // Tiefpassfilterung
            decomposition[i] = (1-0.7)*decomposition[i] - 0.7*lasty;
            lasty = decomposition[i];
            
            // jedes x. Sample (interval)
            if (i%interval == 0) {
                // In das Array für das unterabgetastete Signal einsetzen
                undersamplearray[pointer] = decomposition[i];
                // Zeiger weitersetzen
                pointer++;
            }
            
        }

        // Normalisierung
        double mean = 0;
        // für jedes Element der Array mit den unterabgetasteten Werten
        for (int i = 0; i < undersamplearray.length; i++) {
            
            // Vollwellengleichrichtung (Beträge bilden)
            undersamplearray[i] = Math.abs(undersamplearray[i]);
            
            // Erst aufaddieren...
            mean += undersamplearray[i];
        }
        amount_of_samples = undersamplearray.length;
        // ... dann durch die Anzahl an Elementen teilen (Durchschnitt)
        mean /= amount_of_samples;

        // Mittelwert von allen Werten in der Array mit den unterabgetasteten Werten
        // subtrahieren.
        for (int i = 0; i < undersamplearray.length; i++) {
            undersamplearray[i] -= mean;
        }
        
        // Die Elemente der Array mit den unterabgetasteten Werten
        // zu der "Detection function" hinuzaddieren.
        for (int i = 0; i < detection_function.length; i++) {
            detection_function[i] += undersamplearray[i];
        }
        
        // und zurückgeben
        return detection_function;
        
    }
    
    public int get_bpm() {
        
        // Audiodaten laden
        double[][] WavData = getWavData();
        double[] audioProcessData = WavData[0];
        
        // Gesamtlänge des Audiosignals in Samples
        int length = audioProcessData.length;
        
        // Das Audiosignal wird in Fenster der Größe 131072 Samples
        // eingeteilt. Das entspricht etwa 3 Sekunden bei fs=44,1kHz
        int N_samples = 131072;
        
        // Wie viele Fenster gibt es also insgesamt?
        int amount_of_windows = (int) length/131072;
        
        // Array für die Fenster anlegen.
        // 1. Dimension der Array: Fensterauswahl
        // 2. Dimension der Array: einzelne Samplewerte des jeweiligen Fensters
        double[][] Windows = new double[amount_of_windows][131072];
        
        // Fenster Array wird aufgefüllt
        for (int k = 0; k < amount_of_windows; k++) {
            for (int i = 0; i < N_samples; i++) {
                if (k*N_samples+i < length) {
                    Windows[k][i] = audioProcessData[k*N_samples+i];
                }

            }
        }
        
        // In jedem Fenster wird EIN BPM-Wert ermittelt und einer Liste 
        // angefügt:
        // Liste für die gefundenen BPMs:
        List<Double> BPMs = new ArrayList<Double>();
        
        // For-Schleife, die durch alle Fenster wandert.
        for (int cur_window = 0; cur_window < Windows.length; cur_window++) {
            
            // Für jedes Fenster werden folgende Operationen durchgeführt:
            // 1) DWT (Diskrete Wavelet-Transformation)
            //    -> aus allen Koeffizientenfolgen wird die "Detection function" gebildet:
            // 2) process_coefficients(): (für jede Koeffizientenfolge)
            //      - Tiefpassfilterung
            //      - Unterabtastung
            //      - Vollwellengleichrichtung (Beträge bilden)
            //      - Normalisierung (Mittelwert subtrahieren)
            // 3) Autokorrelation der "Detection function"
            // 4) Peak finden (im Intervall 60...200 BPM)
            // 5) Peak einer Liste anhängen (List<Double> BPMs)
            
            // Wavelet: Daubechies4
            Daubechies4 wavelet = new Daubechies4();
            // DWT vorbereiten.
            Transform transform = new Transform(new FastWaveletTransform(wavelet));

            // Dekomposition des aktuellen Fensters
            double[][] decomposeAll = transform.decompose(Windows[cur_window]);
            
            // Es wird das level 5 der Dekomposition untersucht.
            int level = 5;
            
            // In die Array decomposition wird die zum level zugehörige
            // Dekomposition eingetragen:
            double[] decomposition = decomposeAll[level];

            // Die "Detection function" soll so viele Werte haben, wie die
            // Approximationskoeffizientenfolge cA5. Diese Anzahl wird hier berechnet:
            int cA5_amount_of_elements = N_samples/(int) Math.pow(2, level);
            // Und die Array für die detection_function wird angelegt:
            double[] detection_function = new double[cA5_amount_of_elements];

            // Für alle Koeffizientenfolgen:
            for (int index = 0; index <= level; index++) {

                // index = 0: cD1
                // index = 1: cD2
                // index = 2: cD3
                // index = 3: cD4
                // index = 4: cD5
                // index = 5: cA5
                
                // Startpunkt aus der Dekompositions-Array
                // Standard: 0 für Approximations-Koeffizientenfolge cA5
                int start = 0;
                
                // Wenn index != 5, also wenn es sich um eine 
                // Detail-Koeffizientenfolge handelt:
                if (index != level) {
                    // ... dann ist der Start versetzt:
                    start = N_samples/((int) Math.pow(2, index+1));
                }

                // Der Endpunkt wird berechnet:
                int end = N_samples/((int) Math.pow(2, index));
                
                // Von start bis end können nun aus der Dekompositionsarray decomposition
                // die jeweilige aktuelle Koeffizientenfolge gelesen werden.

                // Die "Detection function" ergibt sich aus der Zusammensetzung (Summe)
                // der einzeln verarbeiteten Koeffizientenfolgen:
                detection_function = process_coefficients(detection_function, decomposition, start, end);

            }

            // Werte der "Detection function" < 0 sollen 0 sein:
            for (int i = 0; i < detection_function.length; i++) {
                if (detection_function[i] < 0) {
                    detection_function[i] = 0;
                }
            }

            
            // 3) Autokorrelation
            int n = detection_function.length;

            // Arrays für die Autokorrelation vorbereiten
            double[] AKF_real = new double[n];
            double[] AKF_imag = new double[n];
            
            // Array auffüllen
            for (int i = 0; i < n; i++) {
                AKF_real[i] = detection_function[i];
            }
            
            // FFT
            fft.transform(AKF_real, AKF_imag);
            
            // Autokorrelation (Multiplikation im Frequenzbereich:
            // (!) aber mit konjugiert komplexem Paar
            for (int i = 0; i < AKF_real.length; i++) {
                AKF_real[i] = AKF_real[i]*AKF_real[i] + AKF_imag[i]*AKF_imag[i];
                AKF_imag[i] = 0;
            }
            
            // IFFT
            fft.inverseTransform(AKF_real, AKF_imag);
            

            // 4) Peak suchen
            
            // Aus der AKF sollen in dem Bereich von min_c bis max_c 
            // der Maximalwert gesucht werden:
            int min_c = (int) ( 60./200 * 44100./Math.pow(2,level) );
            int max_c = (int) ( 60./60 * 44100./Math.pow(2,level) );

            // Peakwert anlegen
            double peak = 0;
            // Index (Sample), bei dem der Peak gefunden wurde
            int peak_index = 0;
            
            // Bereich von min_c bis max_c durchgehen
            for (int i = min_c; i < max_c; i++) {
                // Wenn der aktuelle Wert größer ist, als der
                // bisher größte gefundene Wert
                if (AKF_real[i] > peak) {
                    // dann setze neuen Maximalwert
                    peak = AKF_real[i];
                    // und merke den korrekten Index
                    peak_index = i;
                }
            }

            // 5) Peak einer Liste anhängen
            // aber nur, wenn ein Peak überhaupt gefunden wurde. (division by zero verhindern)
            if (peak_index != 0) {
                // BPM wird berechnet
                double windowBPM = 60./peak_index * (44100./Math.pow(2,level));
                // und der Liste angehängt.
                BPMs.add(windowBPM);
                
            }



        }
        // Ende der For-Schleife für die einzelnen Fenster
        
        
        // Array anlegen, mit der die Häufigkeit der BPM-Werte
        // gezählt werden soll:
        int[] countBPMs = new int[200]; // Über 200 BPM wird nicht ermittelt 
        
        // Alle gefundenen BPM-Werte durchgehen:
        for (double current_BPM : BPMs) {
            
            // BPM-Wert runden, es sollen ganze Werte ausgegeben werden.
            int round_BPM = (int) Math.round(current_BPM);

            // Wenn der BPM-Wert kleiner ist als 200
            if (round_BPM < 200) {
                // Dann wird er gezählt
                countBPMs[round_BPM]++;
            }
            
        }
        
        // Es wird der am häufigsten gefundene BPM-Wert gesucht:
        int maximumBPM = 0;
        int maximumBPM_count = 0;
        for (int BPM = 60; BPM < countBPMs.length; BPM++) {
            // Ist die aktuelle BPM-Wert häufiger gefunden worden
            // als der bisher am häufigsten gemerkte?
            if (countBPMs[BPM] > maximumBPM_count) {
                // ... dann merke diesen Wert
                maximumBPM = BPM;
                // ... und die Anzahl
                maximumBPM_count = countBPMs[BPM];
            }
        }
        
        // BPM als Ergebnis zurückgeben
        return maximumBPM;
        
                 
    }    
    
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
