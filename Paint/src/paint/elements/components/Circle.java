package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author bilombardy
 */
public class Circle extends Component {
    /** The radius of the circle */
    private int radius;

    public Circle(int posX, int posY, int radius) {
        super(posX, posY);
        setRadius(radius);
    }
    
    /**
     * Sets the radius of the circle
     * @param radius 
     */
    private void setRadius(int radius) {
        if (radius < 1)
            radius = 1;
        
        this.radius = radius;
    }
    
    @Override
    public void draw(GraphicsContext g) {
        g.setLineWidth(thickness);
        
        switch (mode) {
            case STROKE:
                g.setStroke(primaryColor);
                g.strokeOval(x, y, radius, radius);
                break;
            case FILLSTROKE:
                g.setFill(primaryColor);
                g.fillOval(x, y, radius, radius);
                g.setStroke(secondaryColor);
                g.strokeOval(x, y, radius, radius);
                break;
            default:
                g.setFill(primaryColor);
                g.fillOval(x, y, radius, radius);
                break;
        }
    }
}
