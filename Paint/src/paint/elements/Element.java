package paint.elements;

import javafx.scene.canvas.GraphicsContext;

public abstract class Element {
    /**
     * Draw the component
     *
     * @param g GraphicsContext of the canvas used
     */
    public abstract void draw(GraphicsContext g);
}