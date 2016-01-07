package bpm.finder;

import biz.source_code.dsp.filter.FilterCharacteristicsType;
import biz.source_code.dsp.filter.FilterPassType;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LowPassPeakAlgorithm {

    private File currentFile;
    private WavFile wavFile;
    private MediaControl mc;
    int peakCount = 0;
    private WavFilter lowpassFilter;
    private long sampleRate;
    private final FilterPassType filterPassType;
    private final FilterCharacteristicsType filterCharacteristics;

    public LowPassPeakAlgorithm() {
        this.filterPassType = FilterPassType.lowpass;
        this.filterCharacteristics = FilterCharacteristicsType.butterworth;
    }

    public boolean setWAV(File file) {

        // Mit dieser Funktion wird der Klasse mitgeteilt,
        // welche WAV-Datei geladen wurde.
        this.currentFile = file;

        try {
            // Versuche, die Wav-Datei zu öffnen.
            this.wavFile = WavFile.openWavFile(currentFile);
            wavFile.close();
        } catch (IOException | WavFileException ex) {
            // Exception: WAV-Datei konnte nicht geöffnet werden.
            // return false!
            Logger.getLogger(LowPassPeakAlgorithm.class.getName()).log(Level.SEVERE, null, ex);
            this.currentFile = null;
            return false;
        }

        return true;

    }

    public ArrayList<Integer> getPeaksAtThreshold(double[] data, double threshold) {

        // Anlegen einer Arrayliste um die Peaks über Threshold zu speichern
        ArrayList<Integer> peaks = new ArrayList<>();
        int length = data.length;

        // Speichern aller Sample-Positionen, deren PCM-Werte sich über dem Threshold-Wert befinden
        for (int counter = 0; counter < length;) {
            if (data[counter] > threshold) {
                peaks.add(counter);
                // Erhöhung des Index um 10000, wenn ein Wert gespeichert wird
                counter += 10000;
            }
            counter++;
        }
        // Rückgabe der Peak-Liste
        return peaks;
    }

    public HashMap<Integer, Integer> countIntervalsBetweenNearbyPeaks(ArrayList<Integer> peaks) {

        // Hashmap zum Speichern der Intervalle und deren Häufigkeit
        HashMap<Integer, Integer> intervalCounts = new HashMap<>();

        // Für jeden gefundenen Peak-Wert...
        for (int index = 0; index < peaks.size(); index++) {

            int peak = peaks.get(index);

            // ... werden die Positions-Abstände (Intervalle) zwischen diesem Peak und den 
            // nächsten 10 Peaks gespeichert. Wird ein Intervall mehrfach erkannt, wird 
            // dessen Zähler pro Auftreten um 1 erhöht
            for (int scope = 0; scope < 10; scope++) {

                if ((index + scope) < peaks.size()) {
                    //Berechnung des Abstands
                    int interval = peaks.get(index + scope) - peak;

                    // Wenn Intervall bereits vorhanden ist...
                    if (intervalCounts.containsKey(interval)) {
                        // ...erhöhe dessen Zähler...
                        int previousCount = intervalCounts.get(interval);
                        // ...um eins
                        intervalCounts.put(interval, previousCount + 1);

                    } // Wenn ein Intervall noch nicht gespeichert ist, wird er 
                    // neu angelegt
                    else {
                        intervalCounts.put(interval, 1);

                    }
                }

            }
            //GUI-Update
            mc.lowpassPeakAlgorithmProgress.setProgress(mc.lowpassPeakAlgorithmProgress.getProgress() + 0.1 / peaks.size());
        }
        //Rückgabe der Intervall-Zähler-Wertepaare
        return intervalCounts;
    }

    public HashMap<Double, Integer> groupNeighborsByTempo(HashMap<Integer, Integer> intervalCounts) {
        // Hashmap um Tempo-Zähler-Wertepaare zu speichern
        HashMap<Double, Integer> tempoCounts = new HashMap<>();

        //Berechnen der BPM-Werte mit den ermittelten Intervall-Daten
        intervalCounts.forEach((Integer interval, Integer count) -> {
            if (interval != 0) {

                // Berechnen des theoritschen Tempos pro Intervall
                double theoreticalTempo = 60 / (interval / (double) sampleRate);

                // Verdoppeln, bzw. halbieren der des Tempos, falls dieses nicht im angenommenen
                // Wertebereich liegen, bis sie im angenommenen Wertebereich liegen
                while (theoreticalTempo < 100) {
                    theoreticalTempo *= 2;
                }
                while (theoreticalTempo > 200) {
                    theoreticalTempo /= 2;
                }
                // Runden des gefundenen Wertes
                theoreticalTempo = Math.round(theoreticalTempo);

                // Falls das gefundene Tempo bereits erkannt wurde, wird dessen Zähler...
                if (tempoCounts.containsKey(theoreticalTempo)) {

                    int previousCount = tempoCounts.get(theoreticalTempo);

                    // ... um 1 erhöht
                    tempoCounts.put(theoreticalTempo, previousCount + 1);
                } else {
                    // Falls das Tempo noch nicht gespeichert wurde, wird es
                    // neu angelegt
                    tempoCounts.put(theoreticalTempo, 1);
                }

            }
        });
        // Rückgabe der Tempo-Zähler-Wertepaare
        return tempoCounts;

    }

    public int get_bpm() {
        // Erstellen eines Butterworth-Tiefpassfilters 4. Ordnung, Cutoff-Frequenz: 100 Hz
        lowpassFilter = new WavFilter(
                currentFile.getPath(),
                filterPassType,
                filterCharacteristics,
                4, -1, 100, 0
        );

        // GUI-Update
        mc.lowpassPeakAlgorithmProgress.setProgress(0.2);

        double bpm;

        // Audiodaten laden
        double[][] wavData = lowpassFilter.getWavData();
        double[] rawPCM = wavData[0];

        // GUI-Update
        mc.lowpassPeakAlgorithmProgress.setProgress(0.3);

        // Festlegen der Threshold-Werte: Start-Threshold, End-Threshold
        double initialThreshold = 0.9;
        double threshold = initialThreshold;
        double minThreshold = 0.3;

        // Minimale Anzahl zu findendener Peaks
        int minPeaks = 30;

        // Speichern der Samplerate
        sampleRate = lowpassFilter.getSampleRate();

        // Anlegen der Datenstrukturen zum Speichern 
        // der Peak-Positionen
        ArrayList<Integer> peakPositions = new ArrayList<>();

        // ...der Intervall-Zähler-Wertepaare...
        HashMap<Integer, Integer> intervalCounts;

        // ...und der Tempo-Zähler-Wertepaare...
        HashMap<Double, Integer> tempoCounts;

        // Ermitteln der Peak-Positionen, bis 30 Positionen gefunden wurden...
        while (peakPositions.size() < minPeaks
                //... oder der minimale Threshold-Wert erreicht wurde
                && threshold >= minThreshold) {

            // Speichern der Positionen
            peakPositions = getPeaksAtThreshold(rawPCM, threshold);

            // Verringern des Threshold-Wertes
            threshold -= 0.05;

            // GUI-Update
            mc.lowpassPeakAlgorithmProgress.setProgress(mc.lowpassPeakAlgorithmProgress.getProgress() + 0.4 / (double) minPeaks);
        }

        // Speichern und zählen der emittelten Intervalle
        intervalCounts = countIntervalsBetweenNearbyPeaks(peakPositions);

        // Speichern und zählen der ermittelten Tempi
        tempoCounts = groupNeighborsByTempo(intervalCounts);

        // Ermitteln des am häufigten aufgetretenem Tempos
        bpm = Collections.max(tempoCounts.entrySet(), (entry1, entry2)
                -> entry1.getValue() > entry2.getValue() ? 1 : -1).getKey();

        int bpm_int = (int) (bpm);
        
        //GUI-Update
        mc.lowpassPeakAlgorithmProgress.setProgress(1.0);

        // Rückgabe des ermittelten BPM-Werts
        return bpm_int;
    }
    
    //Einstellen der GUI
    public void setMediaControl(MediaControl mc) {
        this.mc = mc;
    }
}
