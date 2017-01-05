package paint;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import paint.elements.components.Component;
import paint.elements.components.Square;

/**
 * FXML Controller class
 *
 * @author jemassard
 */
public class FXMLMainWinController implements Initializable {

    /**
     *
     */
    public static int numeroTab = 1;
    /**
     *
     */
    public static int numeroLayer = 0;
    /**
     *
     */
    private List<Layer> layerList = new ArrayList<>();
    /**
     *
     */
    ObservableList observableList = FXCollections.observableArrayList();

    @FXML
    private ListView layerZone;
    @FXML
    private TabPane tabContainer;
    @FXML
    private Canvas canvas;

    /**
     * Add a tab on the tab list
     */
    public void addTab() {
        Tab newTab = new Tab();
        Canvas cvs = new Canvas();
        newTab.setText("New Draw n°" + numeroTab);
        newTab.contentProperty().set(cvs);

        tabContainer.getTabs().add(newTab);
        ++numeroTab;
    }

    /**
     * Remove a tab on the tab list
     */
    public void removeTab() {
        tabContainer.getTabs().remove(tabContainer.getSelectionModel().getSelectedItem());
    }

    /**
     * Add a new Layer on the ListView
     */
    public void addLayer() {
        Layer newLayer = new Layer();
        Layer.setRootWidth(layerZone.getWidth());

        layerList.add(newLayer);
        newLayer.setName("Layer n°" + layerList.indexOf(newLayer));

        observableList.setAll(layerList);
        layerZone.setItems(observableList);
    }

    public void addLayer(String name) {
        Layer newLayer = new Layer();
        Layer.setRootWidth(layerZone.getWidth());

        layerList.add(newLayer);
        newLayer.setName(name);

        observableList.setAll(layerList);
        layerZone.setItems(observableList);
    }

    private void createBackgroundLayer() {
        Layer background = new Layer();
        background.setLocked(true);
        Layer.setRootWidth(layerZone.getWidth());

        layerList.add(background);
        background.setName("Background");

        observableList.setAll(layerList);
        layerZone.setItems(observableList);
    }

    /**
     *
     */
    public void mergeLayer() {

    }

    /**
     *
     */
    public void deleteLayer() {
        layerList.remove(layerZone.getSelectionModel().getSelectedItem());
        observableList.setAll(layerList);
        layerZone.setItems(observableList);
    }

    // ===== TEST ===== //
    
    private int firstClickX = -1;
    private int firstClickY = -1;
    private int secondClickX = -1;
    private int secondClickY = -1;
    Random rand = new Random();

    @FXML
    public void onMouseClicked(MouseEvent e) {
        if (firstClickX == -1) {
            firstClickX = (int) e.getX();
        } else if (secondClickX == -1) {
            secondClickX = (int) e.getX();
        }

        if (firstClickY == -1) {
            firstClickY = (int) e.getY();
        } else if (secondClickY == -1) {
            secondClickY = (int) e.getY();
        }

        if (firstClickX != -1 && firstClickY != -1 && secondClickX != -1 && secondClickY != -1) {
            if (e.getButton() == MouseButton.PRIMARY) {
                Component sq = new Square(firstClickX, firstClickY, (secondClickX - firstClickX));
                sq.setPrimaryColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));

                sq.draw(canvas.getGraphicsContext2D());
            }

            firstClickX = -1;
            firstClickY = -1;
            secondClickX = -1;
            secondClickY = -1;
        }
    }

    // ===== TEST ===== //
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createBackgroundLayer();
        GraphicsContext gc = canvas.getGraphicsContext2D();
    }
}
