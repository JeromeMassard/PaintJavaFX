package paint;

import java.io.*;
import java.net.*;
import java.util.*;
import javafx.beans.property.*;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;

public class Layer extends BorderPane implements Initializable {
    public static SimpleDoubleProperty rootHeight = new SimpleDoubleProperty(100);
    public static SimpleDoubleProperty rootWidth = new SimpleDoubleProperty(200);
    
    @FXML
    private ImageView imageView;
    private ObjectProperty<Image> image = new SimpleObjectProperty<>();
    
    @FXML
    private CheckBox visible;
    
    @FXML
    private CheckBox locked;
    
    @FXML
    private TextField name;
    
    @FXML
    private Button edit;
    /** Is edit Button active*/
    private boolean active = false;
        
    public Layer() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/layer.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load();

            this.prefHeightProperty().addListener((obs, oldVal, newVal) -> this.setHeight(rootHeight.getValue()));
            this.prefWidthProperty().addListener((obs, oldVal, newVal) -> this.setHeight(rootWidth.getValue()));

            imageView.fitHeightProperty().bind(rootHeight);
            imageView.fitWidthProperty().bind(rootWidth);
        } catch (IOException | RuntimeException e) {
            System.err.println(e.getMessage());
        }
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        edit.setBackground(new Background(Images.EDIT_FALSE));
    }
    
    /**
     * Event called when pushing edit button
     */
    @FXML
    public void editName() {
        active = !active;
        name.setEditable(active);
        name.setDisable(!active);
        
        if (active)
            edit.setBackground(new Background(Images.EDIT_TRUE));
        else {
            edit.setBackground(new Background(Images.EDIT_FALSE));
            System.out.println("Changed name for " + toString() + " to " + getName());
        }
    }
    
    /**
     * Event called when textarea field is changed
     * @param key 
     */
    @FXML
    public void editText(KeyEvent key) {
        if (key.getCode() == KeyCode.ENTER)
            editName();
    }
    
    /**
     * Get the Property of the Layer's image imageView
     * @return ObjectProperty Image
     */
    public ObjectProperty<Image> imageProperty() {
        return imageView.imageProperty();
    }

    /**
     * Get the Property of the Layer's visible checkbox
     * @return BooleanProperty
     */
    public BooleanProperty visibilityProperty() {
        return visible.selectedProperty();
    }

    /**
     * Get the Property of the Layer's locked checkbox
     * @return BooleanProperty
     */
    public BooleanProperty lockedProperty() {
        return locked.selectedProperty();
    }

    /**
     * Get the Property of the Layer's name
     * @return StringProperty
     */
    public StringProperty nameProperty() {
        return name.textProperty();
    }
    
    /**
     * Get image of the Layer
     * @return the layer's image
     */
    public Image getImage() {
        return imageProperty().get();
    }
    
    /**
     * Get visibility of the Layer
     * @return true if the Layer is visible, false if not
     */
    public boolean getVisibility() {
        return visibilityProperty().get();
    }
    
    /**
     * Get locking of the Layer
     * @return true if the Layer is locked, false if not
     */
    public boolean isLocked() {
        return lockedProperty().get();
    }
    
    /**
     * Get the name of the Layer
     * @return the layer's name
     */
    public String getName() {
        return nameProperty().get();
    }
    
    /**
     * Set the path for the imageview
     * @param img
     * @throws java.net.URISyntaxException 
     */
    public void setImage(Image img) throws URISyntaxException {        
        imageProperty().set(img);
    }

    /**
     * Set the visible property of the Layer
     * @param visible Visibility property
     */
    public void setVisibility(boolean visible) {
        visibilityProperty().set(visible);
    }

    /**
     * Set the locked property of the Layer
     * @param locked Locked property
     */
    public void setLocked(boolean locked) {
        lockedProperty().set(locked);
    }

    /**
     * Set the name of the Layer
     * @param name Name 
     */
    public void setName(String name) {
        nameProperty().set(name);
    }
}

class Images {
    static final BackgroundImage EDIT_TRUE =  new BackgroundImage(new Image("images/edit_true.png"),  BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(75, 75, false, false, true, false));
    static final BackgroundImage EDIT_FALSE = new BackgroundImage(new Image("images/edit_false.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(75, 75, false, false, true, false));
/*
    static final BackgroundImage VISIBLE_TRUE =  new BackgroundImage(new Image("images/visible_true.png"),  BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(75, 75, false, false, true, false));
    static final BackgroundImage VISIBLE_FALSE = new BackgroundImage(new Image("images/visible_false.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(75, 75, false, false, true, false));

    static final BackgroundImage LOCKED_TRUE =  new BackgroundImage(new Image("images/locked_true.png"),  BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(75, 75, false, false, true, false));
    static final BackgroundImage LOCKED_FALSE = new BackgroundImage(new Image("images/locked_false.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(75, 75, false, false, true, false));
*/
}