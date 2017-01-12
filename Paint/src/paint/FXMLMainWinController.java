package paint;

import com.sun.javafx.scene.control.skin.ColorPalette;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import paint.elements.components.Circle;
import paint.elements.components.Component;
import paint.elements.components.Line;
import paint.elements.components.Oval;
import paint.elements.components.Point;
import paint.elements.components.Rectangle;
import paint.elements.components.Square;
import paint.elements.components.Text;

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
    @FXML
    private ColorPicker fColor;
    @FXML
    private ColorPicker sColor;
    @FXML
    private GridPane grid;
    @FXML
    private SplitMenuButton shape;

    
    /**
     * Add a tab on the tab list
     */
    public void addTab() {
        Tab newTab = new Tab();
        Canvas cvs = new Canvas(960.0, 650.0);
        newTab.setText("New Draw n°" + numeroTab);
        newTab.setContent(cvs);
        cvs.setId("canvas"+numeroTab);
        grid.add(cvs, 3, 2);
        newTab.setContent(cvs);
        GraphicsContext gc = cvs.getGraphicsContext2D();
        
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

    // ===== ZONE TEST ===== //
    
    private int firstClickX = -1;
    private int firstClickY = -1;
    private int secondClickX = -1;
    private int secondClickY = -1;
    Random rand = new Random();
    
    @FXML
    public void onMousePressed(MouseEvent e) {
        firstClickX = (int) e.getX();
        firstClickY = (int) e.getY();
    }
    
    @FXML
    public void onMouseReleased(MouseEvent e) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        
        secondClickX = (int) e.getX();
        secondClickY = (int) e.getY();

        int x = 0, y = 0, width = 0, height = 0;

        if (firstClickX > secondClickX) {
            x = secondClickX;
            width = firstClickX - secondClickX;
        } else {
            x = firstClickX;
            width = secondClickX - firstClickX;
        }

        if (firstClickY > secondClickY) {
            y = secondClickY;
            height = firstClickY - secondClickY;
        } else {
            y = firstClickY;
            height = secondClickY - firstClickY;
        }
        
        if (e.getButton() == MouseButton.PRIMARY) {
            String test = "sfd";
            Component rt = null;
        
            switch (test.toLowerCase()) {
                case "circle":
                    rt = new Circle(x, y, (width + height) / 2);
                    break;
                case "line":
                    rt = new Line(firstClickX, firstClickY, secondClickX, secondClickY);
                    break;
                case "oval":
                    rt = new Oval(x, y, width, height);
                    break;
                case "point":
                    rt = new Point(x, y, rand.nextBoolean());
                    break;
                case "square":
                    rt = new Square(x, y, (width + height) / 2);
                    break;
                case "text":
                    List<String> values = Arrays.asList("Fromage", "Jérôme", "Thomas", "Des barres", "Radiateur", "Ça marche", "C'est un miracle");
                    rt = new Text(x, y, values.get(rand.nextInt(values.size())));
                    break;
                default:
                    rt = new Rectangle(x, y, width, height);
                    break;
            }
            
            /* Définit la couleur primaire */
            rt.setPrimaryColor(fColor.getValue());
            /* Définit la couleur secondaire (utilisée uniquement si le Mode FILLSTROKE est utilisé) */
            rt.setSecondaryColor(sColor.getValue());
            /* Définit l'épaisseur du composant (le contour si le Mode FILLSTROKE est utilisé) */
            rt.setThickness(5);
            /* Définit le mode de dessin du composant */
            rt.setMode(Component.Mode.FILLSTROKE);
            
            rt.draw(g);
        }
        
        //g.setEffect(new GaussianBlur(rand.nextInt(256)));
    }
    
    // ===== ZONE TEST ===== //
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createBackgroundLayer();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        List<MenuItem> menu = new ArrayList<MenuItem>();
        
        sColor.setValue(Color.rgb(0, 0, 0, 1.0D));
        
        menu.add(new MenuItem("Rectangle"));
        menu.add(new MenuItem("Circle"));
        menu.add(new MenuItem("Line"));
        menu.add(new MenuItem("Oval"));
        menu.add(new MenuItem("Point"));
        menu.add(new MenuItem("Square"));
        
        
        for(MenuItem mi : menu)
        {
           shape.getItems().add(mi);
        } 
         
    }
    
    
    public void setEnabledSecondaryColor(){
        if(sColor.disableProperty().getValue() != Boolean.TRUE)
        {
           sColor.disableProperty().setValue(Boolean.TRUE);
        } 
        else {
            sColor.disableProperty().setValue(Boolean.FALSE);
        }
    }
    
}
