/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpm.finder;

import java.io.File;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.media.AudioSpectrumListener;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.*;

/**
 *
 * @author Ele
 */
public class AudioPlayer extends Application {
    private final static String DEFAULT_MEDIA =  
            "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
    Media media = new Media(DEFAULT_MEDIA);
    private XYChart.Data<Number,Number>[] series1Data;
    public AudioSpectrumListener audioSpectrumListener;
    private static final boolean PLAY_AUDIO = Boolean.parseBoolean(System.getProperty("demo.play.audio","true"));
    public AudioPlayer(){
        audioSpectrumListener = new AudioSpectrumListener (){
            
         @Override public void spectrumDataUpdate(double timestamp, double duration,
                    float[] magnitudes, float[] phases) {
                for (int i = 0; i < series1Data.length; i++) {
                    series1Data[i].setYValue(magnitudes[i] + 60);
                }
            }
        };
    }
    
    public void setMedia(Media media){
         this.media = media;
    }
    
    protected AreaChart<Number,Number> createChart() {
        final NumberAxis xAxis = new NumberAxis(0,128,8);
        final NumberAxis yAxis = new NumberAxis(0,50,10);
        final AreaChart<Number,Number> ac = new AreaChart<Number,Number>(xAxis,yAxis);
        // setup chart
        ac.setId("Spectrum");
        ac.setLegendVisible(false);
        ac.setTitle("Live Audio Spectrum Data");
        ac.setAnimated(false);
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
    
    @Override
    public void start(Stage primaryStage) {
        AreaChart ac = createChart();
        Group root = new Group();
        Scene scene = new Scene(root, 500 , 500);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        
        MediaControl mediaControl = new MediaControl(mediaPlayer, ac);
        scene.setRoot(mediaControl);
        primaryStage.setTitle("BPM-Finder");
        primaryStage.setScene(scene);
        primaryStage.show();
        mediaPlayer.setAudioSpectrumListener(audioSpectrumListener);
        
        //mediaPlayer.play();
    }
    public void changeAudioSpectrumListener(AudioSpectrumListener asl){
        audioSpectrumListener = asl;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
