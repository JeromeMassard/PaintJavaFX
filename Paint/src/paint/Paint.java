package paint;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jemassard
 */
public class Paint extends Application {
    private static final int SCENE_WIDTH = 1200;
    private static final int SCENE_HEIGHT = 800;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLMainWin.fxml"));
        Scene scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}    