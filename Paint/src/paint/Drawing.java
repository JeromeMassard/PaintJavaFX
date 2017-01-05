/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import paint.elements.components.*;

/**
 *
 * @author jemassard
 */
public class Drawing extends Application{
    
    private static double Height;
    private static double Width;
    
    public Drawing(double H,double W)
    {
        Height = H;
        Width = W;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Drawing Operations Test");
        Group root = new Group();
        Canvas canvas = new Canvas(Width, Height);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Line l = new Line(125,21,185,21);
        l.draw(gc);
        
        drawShapes(gc);
        
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawShapes(GraphicsContext gc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
