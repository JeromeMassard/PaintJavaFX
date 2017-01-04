package paint.elements.components;

import java.awt.Graphics;
import javafx.scene.canvas.GraphicsContext;

public class Point extends Component {
    
    public Point(int posX, int posY) {
        super(posX, posY);
    }
    
    public void draw(Graphics g)
    {
        g.setColor(color);
        g.fillOval(x, y, thickness, thickness);
    }
    
    
}