package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;

public class Point extends Component {
    /** Is sets to true of the point is drew as a circle, if false the point is drew as a square*/
    private boolean circle;
    
    public Point(int posX, int posY, boolean circle) {
        super(posX, posY);
        this.circle = circle;
    }
    
    public Point(int posX, int posY) {
        this(posX, posY, true);
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(primaryColor);
        
        if (circle)
            g.fillOval(x, y, thickness, thickness);
        else
            g.fillRect(x, y, thickness, thickness);
    }
}