package paint;

import com.sun.javafx.scene.control.skin.ColorPalette;
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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import paint.elements.components.Component;
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
        /*
        if (firstClickX == -1)
            firstClickX = (int) e.getX();
        else if (secondClickX == -1)
            secondClickX = (int) e.getX();

        if (firstClickY == -1)
            firstClickY = (int) e.getY();
        else if (secondClickY == -1)
            secondClickY = (int) e.getY();

        if (firstClickX != -1 && firstClickY != -1 && secondClickX != -1 && secondClickY != -1) {
            if (e.getButton() == MouseButton.PRIMARY) {
                Component rt = new Rectangle(firstClickX, firstClickY, (secondClickX - firstClickX), (secondClickY - firstClickY));
                rt.setPrimaryColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
                rt.draw(canvas.getGraphicsContext2D());
                
                Text t = new Text(firstClickX, firstClickY, "ABCD");
                t.draw(canvas.getGraphicsContext2D());
            }

            firstClickX = -1;
            firstClickY = -1;
            secondClickX = -1;
            secondClickY = -1;
        }
        */
    }
    
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
            Component rt;
        
            /* rt = new Rectangle(x, y, width, height); */                              /* Dessine un rectangle */
            /* rt = new Line(firstClickX, firstClickY, secondClickX, secondClickY); */  /* Dessine une ligne    */
            /* rt = new Oval(x, y, width, height);   */                                 /* Dessine un oval      */
            
            /* List<String> values = Arrays.asList("Fromage", "Jérôme", "Thomas", "Des barres", "Radiateur", "Ça marche", "C'est un miracle"); */
            /* rt = new Text(x, y, values.get(rand.nextInt(values.size()))); */         /* Dessine un texte     */
            
            rt = new Square(x, y, (width + height) / 2);
            
            /* Définit la couleur primaire */
            rt.setPrimaryColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            /* Définit la couleur secondaire (utilisée uniquement si le Mode FILLSTROKE est utilisé) */
            rt.setSecondaryColor(Color.rgb(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
            /* Définit l'épaisseur du composant (le contour si le Mode FILLSTROKE est utilisé) */
            rt.setThickness(1 + rand.nextInt(16));
            /* Définit le mode de dessin du composant */
            rt.setMode(Component.Mode.FILLSTROKE);
            
            rt.draw(g);
        }
        
        //g.setEffect(new GaussianBlur(rand.nextInt(256)));
    }
        
    // ===== TEST ===== //
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createBackgroundLayer();
        GraphicsContext gc = canvas.getGraphicsContext2D();
    }
}
