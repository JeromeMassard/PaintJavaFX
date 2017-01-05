package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;

public class Line extends Component {
    
    protected int x2;
    protected int y2;
    
    public Line(int posX, int posY, int posX2,int posY2) {
        super(posX, posY);
        x2 = posX2;
        y2 = posY2;
    }
    
    public void draw(GraphicsContext g)
    {
        g.setFill(color);
        g.setLineWidth(thickness);
        
        g.strokeLine(x, y, x2, y2);
    }
}