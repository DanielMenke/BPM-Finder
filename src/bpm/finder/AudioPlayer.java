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
    
    
    public void setMedia(Media media){
         this.media = media;
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        
        Group root = new Group();
        Scene scene = new Scene(root, 540 , 500);
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaControl mediaControl = new MediaControl(mediaPlayer);
        scene.setRoot(mediaControl);
        primaryStage.setTitle("BPM-Finder");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        //mediaPlayer.play();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
