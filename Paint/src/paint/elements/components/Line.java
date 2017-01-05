package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;

public class Line extends Component {
    /** The x position of the second point */
    protected int x2;
    /** The y position of the second point */
    protected int y2;
    
    public Line(int posX, int posY, int posX2,int posY2) {
        super(posX, posY);
        this.x2 = posX2;
        this.y2 = posY2;
    }
    
    @Override
    public void draw(GraphicsContext g)
    {
        g.setStroke(primaryColor);
        g.setLineWidth(thickness);
        g.strokeLine(x, y, x2, y2);
    }
}