package paint.elements.components;

import java.awt.Graphics;

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
    public void draw(Graphics g) {
        g.setColor(color);
        
        if (fill)
            g.fillOval(x, y, radius, radius);
        else
            g.drawOval(x, y, radius, radius);
    }
}
