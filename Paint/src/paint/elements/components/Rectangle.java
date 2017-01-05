package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author bilombardy
 */
public class Rectangle extends Component {
    /** The width of the rectangle */
    private int width;
    /** The height of the rectangle */
    private int height;

    public Rectangle(int posX, int posY, int width, int height) {
        super(posX, posY);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Sets the width of the rectangle
     * @param width 
     */
    private void setWidth(int width) {
        if (width < 1) {
            width = 1;
        }

        this.width = width;
    }

    /**
     * Sets the height of the rectangle
     * @param height 
     */
    private void setHeight(int height) {
        if (height < 1) {
            height = 1;
        }

        this.height = height;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setLineWidth(thickness);

        switch (mode) {
            case STROKE:
                g.setStroke(primaryColor);
                g.strokeRect(x, y, width, height);
                break;
            case FILLSTROKE:
                g.setFill(primaryColor);
                g.fillRect(x, y, width, height);
                g.setStroke(secondaryColor);
                g.strokeRect(x, y, width, height);
                break;
            default:
                g.setFill(primaryColor);
                g.fillRect(x, y, width, height);
                break;
        }
    }
}
