package paint;

import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
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
    public static int numeroTab = 1;
    public static int numeroLayer = 0;

    private List<Layer> layerList = new ArrayList<>();
    private ObservableList observableList = FXCollections.observableArrayList();

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
    private ChoiceBox shape;
    @FXML
    private Spinner size;
    @FXML
    private MenuItem saveBtn;

    private int thickness;
    private Component selectedComponent;
    private String selectedMode;

    /* First X coord of the mouse */
    private int firstClickX = -1;
    /* First Y coord of the mouse */
    private int firstClickY = -1;
    /* Second X coord of the mouse */
    private int secondClickX = -1;
    /* Second Y coord of the mouse */
    private int secondClickY = -1;
    
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

    /**
     * Create background layer, cannot be deleted
     */
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
     * Merge a layer with the lower layer
     */
    public void mergeLayer() {

    }

    /**
     * Delete the selected layer
     */
    public void deleteLayer() {
        layerList.remove(layerZone.getSelectionModel().getSelectedItem());
        observableList.setAll(layerList);
        layerZone.setItems(observableList);
    }

    /**
     * Event called when mouse is pressed
     * @param e 
     */
    @FXML
    public void onMousePressed(MouseEvent e) {
        firstClickX = (int) e.getX();
        firstClickY = (int) e.getY();
    }

    /**
     * Event called when mouse is released
     * @param e 
     */
    @FXML
    public void onMouseReleased(MouseEvent e) {
        GraphicsContext g = canvas.getGraphicsContext2D();

        secondClickX = (int) e.getX();
        secondClickY = (int) e.getY();

        int x, y, width, height;

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
            switch (selectedMode.toLowerCase()) {
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
                    selectedComponent = new Point(x, y);
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

            /* Define primary color for the component */
            selectedComponent.setPrimaryColor(fColor.getValue());
            /* Define secondary color for the component (only used if Mode is FILLSTROKE) */
            selectedComponent.setSecondaryColor(sColor.getValue());
            /* Define thickness of the component (only used if Mode is FILLSTROKE) */
            selectedComponent.setThickness(thickness);

            /* Define drawing mode for component */
            if (sColor.isDisable()) {
                selectedComponent.setMode(Component.Mode.FILL);
            } else {
                selectedComponent.setMode(Component.Mode.FILLSTROKE);
            }

            selectedComponent.draw(g);
        }

        //g.setEffect(new GaussianBlur(rand.nextInt(256)));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createBackgroundLayer();
        GraphicsContext gc = canvas.getGraphicsContext2D();

        fColor.setValue(Color.rgb(0, 0, 0, 1.0D));
        sColor.setValue(Color.rgb(255, 255, 255, 1.0D));
        shape.setValue("Brush");
        thickness = 8;
        
        
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent t) {
                FileChooser fileChooser = new FileChooser();
                 
                //Set extension filter
                FileChooser.ExtensionFilter extFilter = 
                        new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
                fileChooser.getExtensionFilters().add(extFilter);
               
                //Show save file dialog
                File file = fileChooser.showSaveDialog(new Stage());
                 
                if(file != null){
                    try {
                        WritableImage writableImage = new WritableImage(1200, 800);
                        canvas.snapshot(null, writableImage);
                        RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                        ImageIO.write(renderedImage, "png", file);
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                }
            }
             
        });
    }

    public void setEnableSecondaryColor() {
        if (!sColor.disableProperty().getValue()) {
            sColor.disableProperty().setValue(true);
        } else {
            sColor.disableProperty().setValue(false);
        }
    }

    /**
     * Event called when size spinner value is modified
     */
    @FXML
    public void onSizeChanged() {
        SpinnerValueFactory<Integer> valueFactory = size.getValueFactory();
        thickness = valueFactory.getValue();
    }
    
    /**
     * Event called when mouse is dragged over the canvas
     * @param e 
     */
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
    
    /**
     * 
     */
    @FXML
    public void onSelectedMode()
    {
        selectedMode = shape.getValue().toString();
    }
}
