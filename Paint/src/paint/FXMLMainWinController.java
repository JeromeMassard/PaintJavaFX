package paint;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import paint.elements.components.Component;
import paint.elements.components.Group;
import paint.elements.components.Line;
import paint.elements.components.Point;
import paint.elements.components.Rectangle;
import paint.elements.components.Square;

/**
 * FXML Controller class
 *
 * @author jemassard
 */
public class FXMLMainWinController implements Initializable {
    public static int numeroTab = 1;
    public static int numeroLayer = 0;
    private List<Layer> layerList = new ArrayList<>();
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

    public void mergeLayer() {

    }

    public void deleteLayer() {
        layerList.remove(layerZone.getSelectionModel().getSelectedItem());
        observableList.setAll(layerList);
        layerZone.setItems(observableList);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Group carreCol = new Group(0, 0);
        Group spaceCol = new Group(0, 0);
        //Component pt = new Point(20, 50);
        //pt.setThickness(1000);
        //pt.draw(gc);
        
        
        Square sq1 = new Square(30, 30, 50);
        Square sq2 = new Square(30, 55, 50);
        Square sq3 = new Square(55, 30, 50);
        Square sq4 = new Square(55, 55, 50);

        sq1.setColor(Color.rgb(127, 156, 211, 0.5));
        sq2.setColor(Color.rgb(255, 153, 11, 0.5));
        sq3.setColor(Color.rgb(91, 253, 45, 0.5));
        sq4.setColor(Color.rgb(235, 72, 248, 0.5));
        
        carreCol.addToCompound(sq1);
        carreCol.addToCompound(sq2);
        carreCol.addToCompound(sq3);
        carreCol.addToCompound(sq4);
        
        carreCol.setPosition(0,0);
        spaceCol.addToCompound(carreCol);
        carreCol.setPosition(150,0);
        spaceCol.addToCompound(carreCol);
        carreCol.setPosition(50,50);
        spaceCol.addToCompound(carreCol);
        carreCol.setPosition(100,50);
        spaceCol.addToCompound(carreCol);
        carreCol.setPosition(0,0);
        spaceCol.addToCompound(carreCol);
        
        spaceCol.draw(gc);
        
        
    }

    private String url(String resimagesimagepng) {
        return null;
    }
}
