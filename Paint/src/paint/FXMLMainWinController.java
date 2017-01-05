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
import paint.elements.components.Line;

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
    
    @FXML private ListView layerZone;
    @FXML private TabPane tabContainer; 
    @FXML private Canvas canvas;
    
    /**
     * Add a tab on the tab list
     */
    public void addTab()
    {
       Tab newTab = new Tab();
       newTab.setText("New Draw n°"+numeroTab);
       
       tabContainer.getTabs().add(newTab);
       ++numeroTab;
    }
    
    /**
     * Remove a tab on the tab list
     */
    public void removeTab()
    {
        tabContainer.getTabs().remove(tabContainer.getSelectionModel().getSelectedItem());
    }
    
    /**
     * Add a new Layer on the ListView
     */
    public void addLayer()
    {
       Layer newLayer = new Layer();
       Layer.setRootWidth(layerZone.getWidth());
       
       layerList.add(newLayer);
       newLayer.setName("Layer n°" + layerList.indexOf(newLayer));
       
       observableList.setAll(layerList);
       layerZone.setItems(observableList);
    }
    
    public void mergeLayer()
    {
        
    }
    
    public void deleteLayer()
    {
        layerList.remove(layerZone.getSelectionModel().getSelectedItem());
        observableList.setAll(layerList);
        layerZone.setItems(observableList);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Line l = new Line(125,21,185,21);
        l.draw(gc);
    }    

    private String url(String resimagesimagepng) {
        return null;
    }
}