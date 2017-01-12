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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import paint.elements.components.Brush;
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
    @FXML
    private Spinner size;

    private int thickness;
    private Component selectedComponent;

    /**
     * Add a tab on the tab list
     */
    public void addTab() {
        Tab newTab = new Tab();
        Canvas cvs = new Canvas(960.0, 650.0);
        newTab.setText("New Draw n°" + numeroTab);
        newTab.setContent(cvs);
        cvs.setId("canvas" + numeroTab);
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
            String test = "brush";

            switch (test.toLowerCase()) {
                case "circle":
                    selectedComponent = new Circle(x, y, (width + height) / 2);
                    break;
                case "line":
                    selectedComponent = new Line(firstClickX, firstClickY, secondClickX, secondClickY);
                    break;
                case "oval":
                    selectedComponent = new Oval(x, y, width, height);
                    break;
                case "point":
                    selectedComponent = new Point(x, y, rand.nextBoolean());
                    break;
                case "square":
                    selectedComponent = new Square(x, y, (width + height) / 2);
                    break;
                case "text":
                    selectedComponent = new Text(x, y, "...");
                    break;
                case "brush":
                    selectedComponent = new Brush(x, y);
                    return;
                default:
                    selectedComponent = new Rectangle(x, y, width, height);
                    break;
            }

            /* Définit la couleur primaire */
            selectedComponent.setPrimaryColor(fColor.getValue());
            /* Définit la couleur secondaire (utilisée uniquement si le Mode FILLSTROKE est utilisé) */
            selectedComponent.setSecondaryColor(sColor.getValue());
            /* Définit l'épaisseur du composant (le contour si le Mode FILLSTROKE est utilisé) */
            selectedComponent.setThickness(thickness);
            /* Définit le mode de dessin du composant */

            if (sColor.isDisable()) {
                selectedComponent.setMode(Component.Mode.FILL);
            } else {
                selectedComponent.setMode(Component.Mode.FILLSTROKE);
            }

            selectedComponent.draw(g);
        }

        //g.setEffect(new GaussianBlur(rand.nextInt(256)));
    }

    // ===== ZONE TEST ===== //
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createBackgroundLayer();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        fColor.setValue(Color.rgb(0, 0, 0, 1.0D));
        sColor.setValue(Color.rgb(255, 255, 255, 1.0D));
        thickness = 8;
    }

    public void setEnableSecondaryColor() {
        if (!sColor.disableProperty().getValue()) {
            sColor.disableProperty().setValue(true);
        } else {
            sColor.disableProperty().setValue(false);
        }
    }

    @FXML
    public void onSizeChanged() {
        SpinnerValueFactory<Double> valueFactory = size.getValueFactory();
        thickness = valueFactory.getValue().intValue();
    }
    
    @FXML
    public void onMouseDragged(MouseEvent e) {
        if (selectedComponent instanceof Brush) {
            selectedComponent.setPrimaryColor(fColor.getValue());
            selectedComponent.setSecondaryColor(sColor.getValue());
            selectedComponent.setThickness(thickness);
            
            selectedComponent.setPosition((int)e.getX(), (int)e.getY());
            selectedComponent.draw(canvas.getGraphicsContext2D());
        }
    }
}
