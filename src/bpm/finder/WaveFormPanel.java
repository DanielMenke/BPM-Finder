/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bpm.finder;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Ele
 */
public class wavFilePlotter  {
    Canvas canvas = new Canvas(300,250);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    
 
    public void plotData (Point2D point){
        gc.setFill(Color.WHITE);
        gc.strokeLine(40, 10, 10, 40);
        
    }

    public Canvas getPanel(){
        return this.canvas;
    }
    
}
