/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpm.finder;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 *
 * @author Ele
 */
public class WavPlotter extends Path{
    
    private File currentFile;
    private WavFile wavFile;
    private int width;
    private int height;
    private MoveTo mt;
    public WavPlotter(){
    
    }
    public WavPlotter( File wav, int width, int height){
        
        this.currentFile = wav;
        this.width = width;
        this.height = height;
        MoveTo mt = new MoveTo();
        getElements().add(mt);
        
    }
    
    private boolean setWav(){
      
        try {
            this.wavFile = WavFile.openWavFile(currentFile);
            wavFile.close();
           
        } catch (IOException | WavFileException ex) {
            Logger.getLogger(ThresholdBPMFinder.class.getName()).log(Level.SEVERE, null, ex);
            this.currentFile = null;
            return false;
        }
         return true;
    }
     private double[] getWavData() {
        if(setWav()){
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
                double [] data = buffer[0];
                System.out.println("DataSIze "+data.length);
                return data;

            } catch (Exception e) {

                System.out.println(e);
                return null;
            }
        }else{
            System.out.println("No .wav to plot");
            return null;
        }

    }
    
    public Path plot(int precision, int width){
        
        setStroke(Color.BLACK);
        System.out.println("A");
        setWav();
        double [] data = getWavData();
        double plotWidth = width;
        while(!(data.length%precision == 0)){
            precision--;
            System.out.println("B");
        }
        for (double i = 0; i<data.length; i += (data.length/precision)){
            LineTo lt = new LineTo();
            lt.setX(plotWidth*(i/data.length));
            lt.setY(data[(int)i]*100);
            getElements().add(lt);
            System.out.println("x: "+lt.getX()+(" y: "+lt.getY()));
        }
        return this;

//            LineTo lt = new LineTo();
//            lt.setX(50);
//            lt.setY(60);
//            getElements().add(lt);
//            return this;
    }
}
    
