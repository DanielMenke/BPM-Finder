package bpm.finder;

import biz.source_code.dsp.filter.FilterCharacteristicsType;
import biz.source_code.dsp.filter.FilterPassType;
import biz.source_code.dsp.sound.IirFilterAudioInputStreamFisher;
import bpm.finder.WavFile;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;





public class WavFilter {
    WavFile wavFile;
    String inputFileName;
    FilterPassType filterPassType;
    FilterCharacteristicsType filterCharacteristicsType;
    int filterOrder;
    double ripple,
            fcf1,
            fcf2
    ;
    

    public WavFilter(String inputFileName, FilterPassType filterPassType, FilterCharacteristicsType filterCharacteristicsType,
      int filterOrder, double ripple, double fcf1, double fcf2)
    {
        this.inputFileName = inputFileName;
        this.filterPassType = filterPassType;
        this.filterCharacteristicsType = filterCharacteristicsType;
        this.filterOrder = filterOrder;
        this.ripple = ripple;
        this.fcf1 = fcf1;
        this.fcf2 = fcf2;
    }


protected File filterWavFile ()
{
		  
   AudioInputStream inputStream = null;
        try {
            inputStream = AudioSystem.getAudioInputStream(new File(inputFileName));
            AudioInputStream filterStream = IirFilterAudioInputStreamFisher.getAudioInputStream(inputStream, filterPassType, filterCharacteristicsType, filterOrder, ripple, fcf1, fcf2);
            
            File lowpassFilteredWav = new File("output.wav");
            
            AudioSystem.write(filterStream, AudioFileFormat.Type.WAVE, new File("output.wav"));
            //System.out.println("Length of WAV: "+lowpassFilteredWav.length());
            return lowpassFilteredWav;
            
                    
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(WavFilter.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            
        }
        
   
   
   
 }

    public double[][] getWavData() {
        
        try {
            File currentFile = filterWavFile();
            
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
       
    
    public long getSampleRate(){
       
        
        return  wavFile.getSampleRate();
    }
    
}

    
