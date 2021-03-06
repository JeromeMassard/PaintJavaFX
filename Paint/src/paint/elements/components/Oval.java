package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;

/**
 * Reprensents an Oval on the canvas
 * @author bilombardy
 */
public class Oval extends Component {
    /** The width of the oval */
    private int width;
    /** The height of the oval */
    private int height;

    public Oval(int posX, int posY, int width, int height) {
        super(posX, posY);
        setWidth(width);
        setHeight(height);
    }
    
    /**
     * Sets the width of the oval
     * @param width 
     */
    private void setWidth(int width) {
        if (width < 1) {
            width = 1;
        }

        this.width = width;
    }

    /**
     * Sets the height of the oval
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
                g.strokeOval(x, y, width, height);
                break;
            case FILLSTROKE:
                g.setFill(primaryColor);
                g.fillOval(x, y, width, height);
                g.setStroke(secondaryColor);
                g.strokeOval(x, y, width, height);
                break;
            default:
                g.setFill(primaryColor);
                g.fillOval(x, y, width, height);
                break;
        }
    }
}
