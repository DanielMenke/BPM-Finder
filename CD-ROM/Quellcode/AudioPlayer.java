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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.media.AudioSpectrumListener;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.media.*;

public class AudioPlayer extends Application {
    private final static String DEFAULT_MEDIA =  
            "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv";
    Media media = new Media(DEFAULT_MEDIA);
    
    
    public void setMedia(Media media){
         this.media = media;
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        
      try {

            
            Parent root = FXMLLoader.load(getClass().getResource("MediaControl.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("mediacontrol.css").toExternalForm());

            primaryStage.setTitle("BPM-Finder");
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}
