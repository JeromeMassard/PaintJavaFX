package paint;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableArray;
import javafx.fxml.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;

public class Layer extends BorderPane {

private ObjectProperty<Image> image = new SimpleObjectProperty<>();
    
    public static ObservableValue<Double> rootHeight;
    public static ObservableValue<Double> rootWidth;

    private boolean active = false;
    
    @FXML
    private ImageView imageView;
    
    @FXML
    private CheckBox visible;
    
    @FXML
    private CheckBox locked;
    
    @FXML
    private TextField name;
    
    @FXML
    private Button edit;
        
    public Layer() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/layer.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
    
    /* ===== ACTIONS ===== */
    public void editName() {
        active = !active;
        name.setEditable(active);
        
        if (!active) {
            
        }
    }
    
    
    /* ===== PROPERTIES ===== */
    /**
     * 
     * @return 
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
        
    /* ===== GETTERS ===== */
    /**
     * 
     * @return 
     */
    public Image getImage() {
        return imageProperty().get();
    }
    
    /**
     * Get visibility the Layer
     * @return true if the Layer is visible <br /> false if not
     */
    public boolean getVisibility() {
        return visibilityProperty().get();
    }
    
    /**
     * Get locking of the Layer
     * @return true if the Layer is locked <br /> false if not
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

    /* ===== SETTERS ===== */
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