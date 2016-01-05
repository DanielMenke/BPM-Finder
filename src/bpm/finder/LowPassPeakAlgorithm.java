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

public class LowPassPeakAlgorithm {

    int sampleWindow = 1024;

    private File currentFile;
    private WavFile wavFile;
    private MediaControl mc;
    int peakCount = 0;
    private WavFilter lowpassFilter;
    private long sampleRate;
    private FilterPassType filterPassType;
    private boolean stopped = false;
    private FilterCharacteristicsType filterCharacteristics;

    public LowPassPeakAlgorithm() {
        this.filterPassType = FilterPassType.lowpass;
        this.filterCharacteristics = FilterCharacteristicsType.bessel;
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
        ArrayList<Integer> peaks = new ArrayList<>();
        int length = data.length;
        // get the positions of the peaks, that trepassed threshold
        for (int counter = 0; counter < length;) {
            if (data[counter] > threshold) {
                peaks.add(counter);
                counter += 10000;
            }
            counter++;
        }

        return peaks;
    }

    public HashMap<Integer, Integer> countIntervalsBetweenNearbyPeaks(ArrayList<Integer> peaks) {

        HashMap<Integer, Integer> intervalCounts = new HashMap<>();

        for (int index = 0; index < peaks.size(); index++) {

            int peak = peaks.get(index);

            //Get distances (interval) between this peak and the next 10 peaks
            //and count reoccuring intervals 
            for (int scope = 0; scope < 10; scope++) {

                if ((index + scope) < peaks.size()) {
                    //get interval
                    int interval = peaks.get(index + scope) - peak;

                    //check if interval is already stored
                    if (intervalCounts.containsKey(interval)) {
                        //get the current intervalcount
                        int previousCount = intervalCounts.get(interval);
                        //increment this intervalcount
                        intervalCounts.put(interval, previousCount + 1);

                    } //if the interval is not already stored, store it
                    else {
                        intervalCounts.put(interval, 1);

                    }
                }

            }
            mc.peakAlgorithmProgress.setProgress(mc.peakAlgorithmProgress.getProgress() + 0.1 / peaks.size());
        }

        return intervalCounts;
    }

    public HashMap<Double, Integer> groupNeighborsByTempo(HashMap<Integer, Integer> intervalCounts) {

        HashMap<Double, Integer> tempoCounts = new HashMap<>();
        intervalCounts.forEach((Integer interval, Integer count) -> {
            if (interval != 0) {
                double theoreticalTempo = 60 / (interval / (double) sampleRate);

                while (theoreticalTempo < 80) {
                    theoreticalTempo *= 2;
                }
                while (theoreticalTempo > 200) {
                    theoreticalTempo /= 2;
                }

                theoreticalTempo = Math.round(theoreticalTempo);

                if (tempoCounts.containsKey(theoreticalTempo)) {

                    int previousCount = tempoCounts.get(theoreticalTempo);

                    tempoCounts.put(theoreticalTempo, previousCount + 1);
                } else {
                    tempoCounts.put(theoreticalTempo, 1);
                }

            }
        });

        return tempoCounts;

    }

    public int get_bpm() {
        lowpassFilter = new WavFilter(
                currentFile.getPath(),
                filterPassType,
                filterCharacteristics,
                6, -1, 100, 0
        );
        mc.peakAlgorithmProgress.setProgress(0.2);
        double bpm;
        double[][] wavData = lowpassFilter.getWavData();
        mc.peakAlgorithmProgress.setProgress(0.25);
        double[] rawPCM = wavData[0];
        mc.peakAlgorithmProgress.setProgress(0.3);
        double maxPeak;
        double initialThreshold = 0.9;
        double threshold = initialThreshold;
        double minThreshold = 0.3;

        int minPeaks = 30;

        sampleRate = lowpassFilter.getSampleRate();
        maxPeak = findMaxPeak(rawPCM);
        HashMap<Integer, Integer> intervalCounts;
        HashMap<Double, Integer> tempoCounts;
        ArrayList<Integer> peakPositions = new ArrayList<>();

        double[] buffer = new double[sampleWindow];

        while (peakPositions.size() < minPeaks && threshold >= minThreshold) {
            peakPositions = getPeaksAtThreshold(rawPCM, threshold);
            threshold -= 0.05;
            mc.peakAlgorithmProgress.setProgress(mc.peakAlgorithmProgress.getProgress()+0.4/(double)minPeaks);
        }
        
        intervalCounts = countIntervalsBetweenNearbyPeaks(peakPositions);
        tempoCounts = groupNeighborsByTempo(intervalCounts);
        int maxTempo = Integer.MIN_VALUE;

        bpm = Collections.max(tempoCounts.entrySet(), (entry1, entry2)
                -> entry1.getValue() > entry2.getValue() ? 1 : -1).getKey();

        int bpm_int = (int) Math.round(bpm);
        mc.peakAlgorithmProgress.setProgress(1.0);

        return bpm_int;
    }

    public double findMaxPeak(double[] data) {
        double maxPeak = Double.MIN_VALUE;

        for (double i : data) {
            if (i > maxPeak) {
                maxPeak = i;
            }
        }
        mc.peakAlgorithmProgress.setProgress(mc.peakAlgorithmProgress.getProgress()+0.25/(data.length));
        return maxPeak;
    }

    public void setMediaControl(MediaControl mc) {
        this.mc = mc;
    }
}
