/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpm.finder;

import java.io.File;

/**
 *
 * @author Stefan
 */
public class BPMLocalize {
    
    private File currentFile;
    private int locateBPM;
    private WavFile wavFile;
    private double[] wavSamples;
    
    public BPMLocalize() {
        
        System.out.println("TEST");
        
        
        
    }
    
    public void setWAV(File file) {
    
        //System.out.println(file.toURI().toASCIIString());
        
        this.currentFile = file;
        
        try {
            
            this.wavFile = WavFile.openWavFile(currentFile);
            
            //this.wavFile.display();

            // Get the number of audio channels in the wav file
            int numChannels = wavFile.getNumChannels();
            long totalFrames = wavFile.getNumFrames();

            // Create a buffer of 100 frames
            double[] buffer = new double[100 * numChannels];

            int framesRead;

            do
            {
               // Read frames into buffer
               framesRead = wavFile.readFrames(buffer, 100);

               // Loop through frames and look for minimum and maximum value
               //for (int s=0 ; s<framesRead * numChannels ; s++)
               //{
                  //System.out.println(buffer[s]);
               //}
            }
            while (framesRead != 0);

            // Close the wavFile
            wavFile.close();
            
        } catch (Exception e) {
            
            System.out.println(e);
            this.currentFile = null;

        }
        
        
        /*
        
        to do:
        
            - double[][] mit segmentierten samples, vermutlich 1024 oder so
            - 1024 samples langer "impulstrain" (aus bpm testinfo, praktisch "beatloop")
            - alle segmententierten samplegruppen einzeln linear falten mit "impulstrain"
            - overlaps finden und zum ergebnis der faltung zusammensetzen
        
            - in dem ergebnis nach peaks suchen
            - stellen der peaks umrechnen in zugehörigen zeitpunkt => zeitpunkt gibt beginn des BPM an
                                                                      ^ jedenfalls hoffentlich :D
        
        */
            
        
        
        
    }
    
    public boolean isReady() {
        
        if (this.currentFile != null && this.locateBPM != 0) {
            return true;
        } else {
            return false;            
        }
        
    }
    
    
    
}
