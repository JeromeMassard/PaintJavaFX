package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;

public class Line extends Component {
    
    protected int x2;
    protected int y2;
    
    public Line(int posX, int posY,int pos2Y,int pos2X) {
        super(posX, posY);
        x2 = pos2X;
        y2 = pos2Y;
        
    }
    public void draw(GraphicsContext g)
    {
        g.setFill(color);
        g.setLineWidth(5);
        g.moveTo(x, y);
        g.lineTo(x2, y2);
        
        
    }
    
}