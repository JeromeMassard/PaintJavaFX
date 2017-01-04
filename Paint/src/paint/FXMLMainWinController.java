/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paint;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.*;

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
   
    public void addTab()
    {
       Tab newTab = new Tab();
       newTab.setText("New Draw n°"+numeroTab);
       
       tabContainer.getTabs().add(newTab);
       ++numeroTab;
    }
    
    public void rmTab()
    {
        tabContainer.getTabs().remove(tabContainer.getSelectionModel().getSelectedItem());
    }
    
    
    public void addLayer()
    {
       Layer newLayer = new Layer();
       Layer.rootWidth.set(layerZone.getWidth());
       layerList.add(newLayer);
       newLayer.setName("Layer n°"+ layerList.indexOf(newLayer));
       observableList.setAll(layerList);
       layerZone.setItems(observableList);
        
    }
    
    public void mergeLayer()
    {
        
        //
    }
    
    public void delLayer()
    {
        layerList.remove(layerZone.getSelectionModel().getSelectedItem());
        observableList.setAll(layerList);
        layerZone.setItems(observableList);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private String url(String resimagesimagepng) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}