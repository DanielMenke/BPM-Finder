/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpm.finder;

import static javafx.application.ConditionalFeature.FXML;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.media.AudioSpectrumListener;

public class MediaControl extends BorderPane {

    private ThresholdBPMFinder thresholdBPMFinderTest;
    private BPMLocalize bpmlocalizetest;
    private MediaPlayer mp;
    private MediaPlayer track;
    private FileHandler fileHandler;
    private FileChooser fc;
    private Media media;
    private AudioAnalyzer audioAnalyzer = new AudioAnalyzer();
    @FXML private MediaView mediaView;
    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    @FXML private Slider timeSlider;
    @FXML private Label playTime;
    @FXML private Slider volumeSlider;
    @FXML private HBox mediaBar;
    @FXML private Button playButton;
    @FXML private Button openFileButton = new Button("OpenFile");
    @FXML private Button runBPMLocalizeButton = new Button("BPM-Localize");
    @FXML private Button runThresholdMethodButton = new Button("BPM-Threshold-Method");
    private static final int HOUR_IN_MINUTES = 60;
    private static final int MINUTE_IN_SECONDS = 60;
    private static final int HOUR_IN_SECONDS = HOUR_IN_MINUTES * MINUTE_IN_SECONDS;

    public MediaControl(MediaPlayer mp) {
        
        
        AreaChart<Number, Number> ac = audioAnalyzer.createChart();
        AudioSpectrumListener as = audioAnalyzer.audioSpectrumListener;
        this.mp = mp;
        setStyle("-fx-background-color: #22c7;");
        mediaView = new MediaView(mp);
        fileHandler = new FileHandler();
        bpmlocalizetest = new BPMLocalize();
        thresholdBPMFinderTest = new ThresholdBPMFinder();
        Pane mvPane = new Pane() {
            
        };
        mvPane.getChildren().add(ac);
        mvPane.setStyle("-fx-background-color: black;");
       
        setCenter(mvPane);
        updateValues();
        mediaBar = new HBox();
        mediaBar.setAlignment(Pos.CENTER);
        mediaBar.setPadding(new Insets(5, 10, 5, 10));
        mediaBar.setMinHeight(100);
        BorderPane.setAlignment(mediaBar, Pos.CENTER);
        
        //Add Open File Button
        mediaBar.getChildren().add(openFileButton);
        
        // Add BPM Localize Button (for testing)
        mediaBar.getChildren().add(runBPMLocalizeButton);
        // Add ThresholdMethod Button
        mediaBar.getChildren().add(runThresholdMethodButton);
        
        //Add play Button
        playButton = new Button(">");
        mediaBar.getChildren().add(playButton);
        setBottom(mediaBar);

        //Add spacer
        Label spacer = new Label("  ");
        mediaBar.getChildren().add(spacer);

        //Add time label 
        Label timeLabel = new Label("Time: ");
        mediaBar.getChildren().add(timeLabel);

        //Add time slider
        timeSlider = new Slider();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);

        timeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {
                    //multiply duration by percentage calculated by slider position
                    mediaView.getMediaPlayer().seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });

        mediaBar.getChildren().add(timeSlider);

        //Add play label
        playTime = new Label();
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);
        mediaBar.getChildren().add(playTime);

        //Add the volume label
        Label volumeLabel = new Label("Vol: ");
        mediaBar.getChildren().add(volumeLabel);

        //Add volume slider
        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    mediaView.getMediaPlayer().setVolume(volumeSlider.getValue() / 100);
                }
            }
        });
        mediaBar.getChildren().add(volumeSlider);

        //EventHandling
        
        //Run BPM Localize Button
        runBPMLocalizeButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               
                if (bpmlocalizetest.isReady()) {
                    

                    //bpmlocalizetest.bpm_detect(9700000);
                    bpmlocalizetest.beat_detect();
                    
                    
                    
                } else {
                    
                    System.out.println("BPM Localize not ready. (Missing WAV or BPM Data?)");
                    
                }

            }
        });
        
        // Threshold Method Button
        
        runThresholdMethodButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
              Thread analyzingThread = new Thread (new Runnable(){
                 public void run(){
                    double bpm = thresholdBPMFinderTest.detectBPM();
                 } 
              });
                analyzingThread.start();
            }
        });
        
        //Play button event
        playButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Status status = mediaView.getMediaPlayer().getStatus();
                mediaView.getMediaPlayer().setAudioSpectrumListener(as);
                
                switch (status) {
                    case UNKNOWN:
                        break;
                    case HALTED:
                        break;
                    case PAUSED:
                    case READY:
                    case STOPPED:
                        if (atEndOfMedia) {
                            mediaView.getMediaPlayer().seek(mediaView.getMediaPlayer().getStartTime());

                        }
                        mediaView.getMediaPlayer().play();
                        break;
                    default:
                        mediaView.getMediaPlayer().pause();

                }
           
            }
        });
        //Open File Button Handling

        openFileButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
               
                track = fileHandler.openFile();
              
                if (track != null) {
                    mediaView.getMediaPlayer().stop();
                    mediaView.getMediaPlayer().dispose();
                    mediaView.setMediaPlayer(track);
                    applyListeners();
                    System.out.println(track.getCurrentTime());
                    
                    bpmlocalizetest.setWAV(fileHandler.getFile());
                    thresholdBPMFinderTest.setWAV(fileHandler.getFile());
                    updateValues();
                }
            }
        });
        applyListeners();
        
    }
    

    protected void applyListeners(){
        
        mediaView.getMediaPlayer().currentTimeProperty().addListener(new InvalidationListener() {
      
                 @Override
            public void invalidated(Observable ov) {
                updateValues();
            }
        });
        //Set playButton text when running
        mediaView.getMediaPlayer().setOnPlaying(new Runnable() {
            @Override
            public void run() {
                if (stopRequested) {
                    mediaView.getMediaPlayer().pause();
                    stopRequested = false;
                } else {
                    playButton.setText("||");
                }
            }
        });
        //Set playButton text when paused
        mediaView.getMediaPlayer().setOnPaused(new Runnable() {
            @Override
            public void run() {
                System.out.println("onPaused");
                playButton.setText(">");
                System.out.println("Update paused");
            }
        });
        //When ready get Duration and update UI
        mediaView.getMediaPlayer().setOnReady(new Runnable() {
            @Override
            public void run() {
                duration = mediaView.getMediaPlayer().getMedia().getDuration();
                updateValues();
                System.out.println("Update ready");
            }
        });

        //Repeat Mode for testing, can be removed later
        mediaView.getMediaPlayer().setCycleCount(repeat ? MediaPlayer.INDEFINITE : 1);

        //
        mediaView.getMediaPlayer().setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    playButton.setText(">");
                    stopRequested = true;
                    atEndOfMedia = true;
                    System.out.println("Update ready");
                }
            }
        });
    }
    protected void updateValues() {
        
       
        if (playTime != null && timeSlider != null && volumeSlider != null) {
             
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Duration currentTime = mediaView.getMediaPlayer().getCurrentTime();
                    
                    
                    
                    playTime.setText(formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());
                    //System.out.println("UpdateValues");

                    if (!timeSlider.isDisabled()
                            && duration.greaterThan(Duration.ZERO)
                            && !timeSlider.isValueChanging()) {
                        timeSlider.setValue(currentTime.divide(duration).toMillis() * 100);
                    }
                    if (!volumeSlider.isValueChanging()) {
                        volumeSlider.setValue((int) Math.round(mediaView.getMediaPlayer().getVolume() * 100));

                    }
                }
            });
        }
    }
    
    private String formatTime(Duration elapsed, Duration duration) {
        int intElapsed = (int) Math.floor(elapsed.toSeconds());

        int elapsedHours = intElapsed / HOUR_IN_SECONDS;

        if (elapsedHours > 0) {
            intElapsed -= elapsedHours * HOUR_IN_SECONDS;
        }

        int elapsedMinutes = intElapsed / MINUTE_IN_SECONDS;
        int elapsedSeconds = intElapsed - elapsedHours * HOUR_IN_SECONDS
                - elapsedMinutes * MINUTE_IN_SECONDS;

        if (duration.greaterThan(Duration.ZERO)) {
            int intDuration = (int) Math.floor(duration.toSeconds());
            int durationHours = intDuration / (HOUR_IN_SECONDS);
            if (durationHours > 0) {
                intDuration -= durationHours * HOUR_IN_SECONDS;
            }
            int durationMinutes = intDuration / MINUTE_IN_SECONDS;
            int durationSeconds = intDuration - durationHours * HOUR_IN_SECONDS
                    - durationMinutes * MINUTE_IN_SECONDS;

            if (durationHours > 0) {
                return String.format("%d:%02d:02d/%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds,
                        durationHours, durationMinutes, durationSeconds);
            } else {
                return String.format("%02d:%02d/%02d:%02d",
                        elapsedMinutes, elapsedSeconds, durationMinutes,
                        durationSeconds);
            }
        } else {
            if (elapsedHours > 0) {
                return String.format("%d:%02d:%02d",
                        elapsedHours, elapsedMinutes, elapsedSeconds);
            } else {
                return String.format("%02d:%02d",
                        elapsedMinutes, elapsedSeconds);
            }
        }
    }
}
