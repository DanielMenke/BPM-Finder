/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpm.finder;

import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.Background;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.paint.Color;

/**
 *
 * @author Ele
 */
public class AudioAnalyzer {
    
    private XYChart.Data<Number,Number>[] series1Data;
    protected AudioSpectrumListener audioSpectrumListener;
    float[] magnitudes2;
   
     public AudioAnalyzer(){
        audioSpectrumListener = new AudioSpectrumListener (){
            
         @Override public void spectrumDataUpdate(double timestamp, double duration,
                    float[] magnitudes, float[] phases) {
                
                magnitudes2 = magnitudes;
                for (int i = 0; i < series1Data.length; i++) {
                    
                    series1Data[i].setYValue(magnitudes[i] + 60); 
                    
                }
              
            }
        };
    }

     protected AreaChart<Number,Number> createChart() {
        final NumberAxis xAxis = new NumberAxis(0,22,8);
        final NumberAxis yAxis = new NumberAxis(0,50,10);
        final AreaChart<Number,Number> ac = new AreaChart<Number,Number>(xAxis,yAxis);
        // setup chart
        ac.setId("Spectrum");
        ac.setLegendVisible(false);
        ac.setTitle("Live Audio Spectrum Data");
        ac.setAnimated(false);
        ac.setBackground(Background.EMPTY);
        xAxis.setLabel("Frequency Bands");
        yAxis.setLabel("Magnitudes");
        yAxis.setTickLabelFormatter(new NumberAxis.DefaultFormatter(yAxis,null,"dB"));
        // add starting data
        XYChart.Series<Number,Number> series = new XYChart.Series<Number,Number>();
        series.setName("Audio Spectrum");
        //noinspection unchecked
        series1Data = new XYChart.Data[(int)xAxis.getUpperBound()];
        for (int i=0; i<series1Data.length; i++) {
            series1Data[i] = new XYChart.Data<Number,Number>(i,50);
            series.getData().add(series1Data[i]);
        }
        ac.getData().add(series);
        return ac;
    }
    public AudioSpectrumListener getAudioSpectrumListener(){
        return audioSpectrumListener;
    }

    
    
}
