package bpm.finder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import javafx.scene.layout.AnchorPane;

public class MediaControl extends AnchorPane implements Initializable {

    private LowPassPeakAlgorithm BPM_PEAK_ALGORITHM;
    private SoundEnergyAlgorithm BPM_SOUNDENERGY_ALGORITHM;
    private WaveletAlgorithm BPM_WAVELET_ALGORITHM;
    private MediaPlayer mp;
    private MediaPlayer track;
    private FileHandler fileHandler;
    private FileChooser fc;
    private Media media;
    @FXML
    private MediaView mediaView;
    private final boolean repeat = false;
    private boolean stopRequested = false;
    private boolean atEndOfMedia = false;
    private Duration duration;
    @FXML
    private Slider timeSlider;
    @FXML
    private Label playTime;
    @FXML
    private TextField soundEnergyAlgorithmTime;
    @FXML
    private TextField lowpassPeakAlgorithmTime;
    @FXML
    private TextField waveLetAlgorithmTime;
    @FXML
    private Slider volumeSlider;
    @FXML
    private Button playPauseButton;
    @FXML
    private Button openFileButton = new Button("OpenFile");

    @FXML
    private Button runBenchmarkButton = new Button("Benchmark");
    @FXML
    private Button analyzeTempoButton = new Button();
    @FXML
    public ProgressBar soundEnergyAlgorithmProgress = new ProgressBar();
    @FXML
    public ProgressBar lowpassPeakAlgorithmProgress = new ProgressBar();
    @FXML
    public ProgressBar waveletAlgorithmProgress = new ProgressBar();
    @FXML
    private TextField soundEnergyAlgorithmBPMField = new TextField();
    @FXML
    private TextField lowpassPeakAlgorithmBPMField = new TextField();
    @FXML
    private TextField waveletAlgorithmBPMField = new TextField();
    @FXML
    private TextField tracknameField = new TextField();

    private static final int HOUR_IN_MINUTES = 60;
    private static final int MINUTE_IN_SECONDS = 60;
    private static final int HOUR_IN_SECONDS = HOUR_IN_MINUTES * MINUTE_IN_SECONDS;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        volumeSlider.valueProperty().addListener(new InvalidationListener() {
            public void invalidated(Observable ov) {
                if (volumeSlider.isValueChanging()) {
                    mediaView.getMediaPlayer().setVolume(volumeSlider.getValue() / 100);
                }
            }
        });
        openFileButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                track = fileHandler.openFile();
                ClearDisplays();
                if (track != null) {
                    mediaView.getMediaPlayer().stop();
                    mediaView.getMediaPlayer().dispose();
                    mediaView.setMediaPlayer(track);
                    applyListeners();
                    analyzeTempoButton.setDisable(false);
                    BPM_SOUNDENERGY_ALGORITHM.setWAV(fileHandler.getFile());
                    BPM_PEAK_ALGORITHM.setWAV(fileHandler.getFile());
                    BPM_WAVELET_ALGORITHM.setWAV(fileHandler.getFile());
                    tracknameField.setText(fileHandler.getFileName());
                    updateValues();
                }
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

                }
            }
        });
        //Set playButton text when paused
        mediaView.getMediaPlayer().setOnPaused(new Runnable() {
            @Override
            public void run() {

            }
        });
        //When ready get Duration and update UI
        mediaView.getMediaPlayer().setOnReady(new Runnable() {
            @Override
            public void run() {
                duration = mediaView.getMediaPlayer().getMedia().getDuration();
                updateValues();
            }
        });

        playPauseButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override

            public void handle(ActionEvent event) {
                Status status = mediaView.getMediaPlayer().getStatus();

                switch (status) {
                    case UNKNOWN:
                        break;
                    case HALTED:
                        break;
                    case PAUSED:
                        mediaView.getMediaPlayer().play();
                        break;
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

    }

    public MediaControl() {
        media = new Media("http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv");
        mp = new MediaPlayer(media);
        setStyle("-fx-background-color: #22c7;");
        mediaView = new MediaView(mp);
        fileHandler = new FileHandler();

        // 3 Algorithmen
        BPM_SOUNDENERGY_ALGORITHM = new SoundEnergyAlgorithm();
        BPM_SOUNDENERGY_ALGORITHM.setMediaControl(this);
        BPM_WAVELET_ALGORITHM = new WaveletAlgorithm();
        BPM_WAVELET_ALGORITHM.setMediaControl(this);
        BPM_PEAK_ALGORITHM = new LowPassPeakAlgorithm();
        BPM_PEAK_ALGORITHM.setMediaControl(this);

        updateValues();

        playPauseButton = new Button();

        Label timeLabel = new Label("Time: ");

        timeSlider = new Slider();
        HBox.setHgrow(timeSlider, Priority.ALWAYS);
        timeSlider.setMinWidth(50);
        timeSlider.setMaxWidth(Double.MAX_VALUE);

        timeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging()) {

                    mediaView.getMediaPlayer().seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });

        playTime = new Label();
        playTime.setPrefWidth(130);
        playTime.setMinWidth(50);

        Label volumeLabel = new Label("Vol: ");

        volumeSlider = new Slider();
        volumeSlider.setPrefWidth(70);
        volumeSlider.setMaxWidth(Region.USE_PREF_SIZE);
        volumeSlider.setMinWidth(30);

        playPauseButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override

            public void handle(ActionEvent event) {
                Status status = mediaView.getMediaPlayer().getStatus();

                switch (status) {
                    case UNKNOWN:
                        break;
                    case HALTED:
                        break;
                    case PAUSED:

                        break;
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

        runBenchmarkButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {

                    FileWriter fw = new FileWriter("Algorithmen Vergleich.txt");
                    BufferedWriter bw = new BufferedWriter(fw);

                    File[] files = new File("").listFiles();
                    long start;
                    long time_needed;
                    for (File file : files) {

                        System.out.println("Now coming: " + file.getName());

                        // ALGORITHMUS A1
                        BPM_SOUNDENERGY_ALGORITHM.setWAV(file);

                        start = System.currentTimeMillis();
                        int BPM_A1 = BPM_SOUNDENERGY_ALGORITHM.get_bpm();
                        time_needed = System.currentTimeMillis() - start;
                        System.out.println("Soundenergy-Algorithm: " + BPM_A1 + " BPM in " + time_needed + " ms");
                        bw.write("A1: " + file.getName() + ": " + BPM_A1 + " in " + Long.toString(time_needed) + " ms \r\n");

                        // ALGORITHMUS A2
                        BPM_PEAK_ALGORITHM.setWAV(file);

                        start = System.currentTimeMillis();
                        int BPM_A2 = BPM_PEAK_ALGORITHM.get_bpm();
                        time_needed = System.currentTimeMillis() - start;
                        System.out.println("Threshold-Algorithm: " + BPM_A2 + " BPM in " + time_needed + " ms");
                        bw.write("A2: " + file.getName() + ": " + BPM_A2 + " in " + Long.toString(time_needed) + " ms \r\n");

                        // ALGORITHMUS A3
                        BPM_WAVELET_ALGORITHM.setWAV(file);

                        start = System.currentTimeMillis();
                        int BPM_A3 = BPM_WAVELET_ALGORITHM.get_bpm();
                        time_needed = System.currentTimeMillis() - start;
                        System.out.println("Wavelet-Algorithm: " + BPM_A3 + " BPM in " + time_needed + " ms");
                        bw.write("A3: " + file.getName() + ": " + BPM_A3 + " in " + Long.toString(time_needed) + " ms \r\n");

                    }

                    bw.close();

                } catch (Exception e) {

                    System.out.println(e);

                }

            }
        });

        applyListeners();

    }

    @FXML
    protected void analyzeTempo() {
        
        //Anwenden des Signalenergie-Algorithmus
        if (BPM_SOUNDENERGY_ALGORITHM.isReady()) {

            Thread analyzingThread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.nanoTime();
                    String BPM_1 = Integer.toString(BPM_SOUNDENERGY_ALGORITHM.get_bpm());
                    long time_needed=TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
                    soundEnergyAlgorithmBPMField.setText(BPM_1 + " BPM");
                    soundEnergyAlgorithmTime.setText(Long.toString(time_needed) + " ms");
                }
            });
            analyzingThread1.start();

        } else {
            System.out.println("not ready. (Missing WAV?)");
        }
        
        // Anwenden des Tiefpass-Peak-Algorithmus
        Thread analyzingThread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.nanoTime();
                String BPM_2 = Integer.toString(BPM_PEAK_ALGORITHM.get_bpm());
                long time_needed=TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
                lowpassPeakAlgorithmBPMField.setText(BPM_2 + " BPM");
                lowpassPeakAlgorithmTime.setText(Long.toString(time_needed)+ " ms");
            }
        });
        analyzingThread2.start();
        
        // Anwenden des Wavelet-Algorithmus
        if (BPM_WAVELET_ALGORITHM.isReady()) {

            Thread analyzingThread3 = new Thread(new Runnable() {
                @Override
                public void run() {
                    long start = System.nanoTime();
                    String BPM_3 = Integer.toString(BPM_WAVELET_ALGORITHM.get_bpm());
                    long time_needed=TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - start);
                    waveletAlgorithmBPMField.setText(BPM_3 + " BPM");
                    waveLetAlgorithmTime.setText(Long.toString(time_needed) + " ms");
                }
            });
            analyzingThread3.start();

        } else {
            System.out.println("not ready. (Missing WAV?)");
        }

    }

    protected void applyListeners() {

        mediaView.getMediaPlayer().currentTimeProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable ov) {
                updateValues();
            }
        });

        mediaView.getMediaPlayer().setOnReady(new Runnable() {
            @Override
            public void run() {
                duration = mediaView.getMediaPlayer().getMedia().getDuration();
                updateValues();
            }
        });

        mediaView.getMediaPlayer().setOnEndOfMedia(new Runnable() {
            public void run() {
                if (!repeat) {
                    playPauseButton.setText(">");
                    stopRequested = true;
                    atEndOfMedia = true;

                }
            }
        });
    }

    @FXML

    protected void updateValues() {

        if (playTime != null && timeSlider != null && volumeSlider != null) {

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    Duration currentTime = mediaView.getMediaPlayer().getCurrentTime();

                    playTime.setText(formatTime(currentTime, duration));
                    timeSlider.setDisable(duration.isUnknown());

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

    @FXML
    public void playButtonPressed() {
        playPauseButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override

            public void handle(ActionEvent event) {
                Status status = mediaView.getMediaPlayer().getStatus();

                switch (status) {
                    case UNKNOWN:
                        break;
                    case HALTED:
                        break;
                    case PAUSED:

                        break;
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
    }

    @FXML
    public void timeSliderMoved() {

        timeSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable ov) {
                if (timeSlider.isValueChanging() || timeSlider.isPressed()) {
                    mediaView.getMediaPlayer().seek(duration.multiply(timeSlider.getValue() / 100.0));
                }
            }
        });
    }
    @FXML
    public void ClearDisplays(){
        
        soundEnergyAlgorithmBPMField.setText("");
        lowpassPeakAlgorithmBPMField.setText("");
        waveletAlgorithmBPMField.setText("");
        
        soundEnergyAlgorithmTime.setText("");
        lowpassPeakAlgorithmTime.setText("");
        waveLetAlgorithmTime.setText("");
        
        soundEnergyAlgorithmProgress.setProgress(0);
        lowpassPeakAlgorithmProgress.setProgress(0);
        waveletAlgorithmProgress.setProgress(0);
        
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
        } else if (elapsedHours > 0) {
            return String.format("%d:%02d:%02d",
                    elapsedHours, elapsedMinutes, elapsedSeconds);
        } else {
            return String.format("%02d:%02d",
                    elapsedMinutes, elapsedSeconds);
        }
    }

}
