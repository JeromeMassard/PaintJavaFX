package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author bilombardy
 */
public class Circle extends Component {
    private int radius;

    public Circle(int posX, int posY, int radius) {
        super(posX, posY);
        setRadius(radius);
    }
    
    private void setRadius(int radius) {
        if (radius < 1)
            radius = 1;
        
        this.radius = radius;
    }
    
    @Override
    public void draw(GraphicsContext g) {
        g.setFill(color);
        g.setLineWidth(thickness);
        
        if (fill)
            g.fillOval(x, y, radius, radius);
        else
            g.strokeOval(x, y, radius, radius);
    }
}
