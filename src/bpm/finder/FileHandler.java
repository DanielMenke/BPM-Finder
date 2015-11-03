
package bpm.finder;

import java.io.File;
import java.io.IOException;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.BorderPane;


public class FileHandler extends BorderPane {

    private FileChooser fc;
    private MediaPlayer mp;
    private Media media;
    private File file;

    public FileHandler( ) {



    }

    public MediaPlayer openFile() {
        
        fc = new FileChooser();
        file = fc.showOpenDialog(new Stage());

        if (file != null) {
         
            media = new Media(file.toURI().toASCIIString());

            mp = new MediaPlayer(media);
           
            return mp;
          
            
        } else {
            return null;
        }

    }
}
