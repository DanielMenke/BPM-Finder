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
    private double[] wavSamples;
    private double[][] d1imp;
    private double[] d1imag;
    int arraysize;
    int samplelng;

    public BPMLocalize() {
        
        System.out.println("TEST");
        
        //File impAnt = new File("impantwort.wav");
        
//        try {
//            
//            WavFile impWAV = WavFile.openWavFile(impAnt);
//            
//            impWAV.display();
//
//            int numChannels = impWAV.getNumChannels();
//            long totalFrames = impWAV.getNumFrames();
//            
//            long gesamtblocklng = totalFrames*2+(totalFrames-1);
//            arraysize = (int) gesamtblocklng;
//            samplelng = (int) totalFrames;
//
//
//            d1imp = new double[numChannels][arraysize];
//
//            
//            int framesRead;
//
//            do
//            {
//               // Read frames into buffer
//               framesRead = impWAV.readFrames(d1imp, samplelng);
//
//
//            }
//            while (framesRead != 0);
            
           
           
            
//                FileWriter fw1 = new FileWriter("linkkanalimpa.txt");
//                BufferedWriter bw1 = new BufferedWriter(fw1);
//
//                for (int i = 0; i < d1imp.length; i++) {
//                    bw1.write(Double.toString(d1imp[i])+"\n");
//                }
//                
//                 bw1.close();
            
            
            
            
            
            samplelng = 20*10280;
            arraysize = samplelng*2+(samplelng-1);
            
            d1imp = new double[1][arraysize];
            
            for (int i = 0; i < d1imp[0].length; i++) {
                
                d1imp[0][i] = 0.0;
                
                if (i%30767 == 0) {
                    
                    d1imp[0][i] = 1.0;
                    
                }
                
            }
            
            try {
                FileWriter fw22 = new FileWriter("impulstrain.txt");
                BufferedWriter bw22 = new BufferedWriter(fw22);

                for (int i = 0; i < d1imp[0].length; i++) {
                    bw22.write(Double.toString(d1imp[0][i])+"\n");
                }
               
                bw22.close();
            } catch (Exception e) {
                
            }
            
            
            d1imag = new double[d1imp[0].length];
            
//                FileWriter fw = new FileWriter("thiscorig.txt");
//                BufferedWriter bw = new BufferedWriter(fw);
//
//                for (int i = 0; i < d1imp[0].length; i++) {
//                    bw.write(Double.toString(d1imp[0][i])+"\n");
//                }
//               
//                bw.close();
            
            
            long start = System.currentTimeMillis();
            
            fft.transform(d1imp[0], d1imag);
            
//            double[] betr = new double[d1imp[0].length];
//            double normalisieren = 1/Math.sqrt(2*44100);
//            for (int i = 0; i < d1imp[0].length; i++) {
//                betr[i] = normalisieren*Math.sqrt(Math.pow(d1imp[0][i], 2)+Math.pow(d1imag[i], 2));
//            }
            
            long runningTime = System.currentTimeMillis() - start; 
            System.out.println("FFT Performance [ms]: "+runningTime);   
            
            
//                FileWriter fw12 = new FileWriter("testsss.txt");
//                BufferedWriter bw12 = new BufferedWriter(fw12);
//
//                for (int i = 0; i < d1imp[0].length; i++) {
//                    double wert = d1imp[0][i] * (1./d1imp[0].length);
//                    bw12.write(Double.toString(wert)+"\n");
//                }
//               
//
//                bw12.close();

            // Close the wavFile
//            impWAV.close();
            
//        } catch (Exception e) {
//            
//            System.out.println(e);
//
//        }
        
        
    }
    
    public void setWAV(File file) {
    
        //System.out.println(file.toURI().toASCIIString());
        
        this.currentFile = file;
        
        try {
            
            this.wavFile = WavFile.openWavFile(currentFile);
            
            this.wavFile.display();

            // Get the number of audio channels in the wav file
            int numChannels = wavFile.getNumChannels();
            long totalFrames = wavFile.getNumFrames();
            int totframes = (int) totalFrames;

            System.out.println(totframes);
            
            // Create a buffer of 100 frames
            double[][] buffer = new double[numChannels][d1imp[0].length];
            //List<Double> samplesList = new ArrayList<Double>();

                    for (int i = 0; i < buffer[0].length; i++) {
                      buffer[0][i] = 0.0;
                    }
            
            int framesRead;

            double faltungkomplettlange = totframes/(samplelng*2.);
            int arraysize_kompl = (int) (Math.ceil(faltungkomplettlange)) * (samplelng*2) + (samplelng-1);

            double[] faltungkomplettreal = new double[arraysize_kompl];
            double[] faltungkomplettimag = new double[arraysize_kompl];
            //double[] faltungkomplettreal = new double[264599];
            //double[] faltungkomplettimag = new double[264599];
            int geszeiger = 0;
            
                    for (int i = 0; i < faltungkomplettreal.length; i++) {
                      faltungkomplettreal[i] = 0.0;
                      faltungkomplettimag[i] = 0.0;
                    }

            do
            {
               // Read frames into buffer
               // buffer = new double[numChannels][d1imp[0].length];
               framesRead = wavFile.readFrames(buffer, (samplelng*2));
              
               if (framesRead != 0) {
                   for (int i = framesRead-1; i < buffer[0].length; i++) {
                      buffer[0][i] = 0.0;
                    }
                   
                    double[] bufferimg = new double[d1imp[0].length];
                    for (int i = 0; i < bufferimg.length; i++) {
                      bufferimg[i] = 0.0;
                    }

                    fft.transform(buffer[0], bufferimg);
                     for (int i = 0; i < d1imp[0].length; i++) {
                         double ges_real = buffer[0][i]*d1imp[0][i] - bufferimg[i]*d1imag[i];
                         double ges_imag = buffer[0][i]*d1imag[i] + d1imp[0][i]*bufferimg[i];
                         buffer[0][i] = ges_real;
                         bufferimg[i] = ges_imag;
                     }
                     fft.inverseTransform(buffer[0], bufferimg);
                     
                    System.out.println("did fft");
                     //System.out.println("read: "+framesRead);
                     //System.out.println("into: "+d1imp[0].length);
                    //System.out.println("into: "+buffer[0].length);
                    
                     for (int i = 0; i < d1imp[0].length; i++) {
                         // overlap add
                         if ((i < samplelng) && (geszeiger > samplelng)) {
                          //   System.out.println("overlap @ "+(geszeiger-1-(samplelng-1)+i));
                             faltungkomplettreal[geszeiger-1-(samplelng-1)+i] += buffer[0][i];
                             faltungkomplettimag[geszeiger-1-(samplelng-1)+i] += bufferimg[i];
                         } else {
                          //   System.out.println("add @ "+(geszeiger));
                             //System.out.println(geszeiger);
                             faltungkomplettreal[geszeiger] = buffer[0][i];
                             faltungkomplettimag[geszeiger] = bufferimg[i];
                             geszeiger++;
                         }
                         
                     }
                       System.out.println(geszeiger);
                    // Loop through frames and look for minimum and maximum value
                    //for (int s=0 ; s<framesRead; s++)
                    //{
                       //System.out.println(buffer[s]);
                        //samplesList.add(buffer[s]);
                        //d1[s] = buffer[0][s];
                    //}
                    
                    bufferimg = null;
               }
               
               
            }
            while (framesRead != 0);
            
           // Double[] d = new Double[samplesList.size()];
            //samplesList.toArray(d);
            // = toPrimitive(d);
               // fft.inverseTransform(faltungkomplettreal, faltungkomplettimag);
            
                FileWriter fw = new FileWriter("gefaltet.txt");
                BufferedWriter bw = new BufferedWriter(fw);
                
                //double normalisieren = Math.pow((1/Math.sqrt(2*44100)), 2);
                int lng = faltungkomplettreal.length;
                
                for (int i = 0; i < faltungkomplettreal.length; i++) {
                    faltungkomplettreal[i] *= (1./(lng*5));
                    bw.write(Double.toString(faltungkomplettimag[i])+"\n");
                }
               

                bw.close();

            // Close the wavFile
            wavFile.close();
            
            
            
            int sampleRate = 44100;    // Samples per second
            int numFrames = faltungkomplettreal.length;
            WavFile wrwavFile = WavFile.newWavFile(new File("test.wav"), 1, numFrames, 16, sampleRate);

            double[] wrbuffer = new double[100];
            
            int frameCounter = 0;
            
            // Loop until all frames written
            while (frameCounter < numFrames)
            {
               // Determine how many frames to write, up to a maximum of the buffer size
               long remaining = wrwavFile.getFramesRemaining();
               int toWrite = (remaining > 100) ? 100 : (int) remaining;

               // Fill the buffer, one tone per channel
               for (int s=0 ; s<toWrite ; s++, frameCounter++)
               {
                  wrbuffer[s] = faltungkomplettreal[frameCounter];
               }

               // Write the buffer
               wrwavFile.writeFrames(wrbuffer, toWrite);
            }
            
            wrwavFile.close();
   
            
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
    
    public static double[] toPrimitive(Double[] array) {
      if (array == null) {
        return null;
      } else if (array.length == 0) {

      }
      final double[] result = new double[array.length];
      for (int i = 0; i < array.length; i++) {
        result[i] = array[i].doubleValue();
      }
      return result;
    }
    
}
