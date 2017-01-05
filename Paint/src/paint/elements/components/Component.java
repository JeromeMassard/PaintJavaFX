package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import paint.elements.Element;

public class Component extends Element {
    protected int x;
    protected int y;
    protected int thickness;
    protected Color color;
    protected boolean fill;
    
    public Component(int posX, int posY)
    {
        this.x = posX; 
        this.y = posY;
        
        setThickness(1);
        setColor(Color.BLACK);
        setFill(true);
    }
    
    public void setThickness(int thickness)
    {
        if(thickness < 1)
            thickness = 1;
        
        this.thickness = thickness;
    }
    
    public void setColor(Color newcolor)
    {
        this.color = newcolor;
    }
  
    public void setFill(boolean fill) {
        this.fill = fill;
    }
    
    @Override
    public void draw(GraphicsContext g) {
        
    }
}
