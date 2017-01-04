package paint.elements.components;

import java.awt.Color;
import java.awt.Graphics;
import paint.elements.Element;

public class Component extends Element {
    protected int x;
    protected int y;
    protected int thickness;
    protected Color color;
    
    public Component(int posX,int posY)
    {
        x = posX; 
        y = posY;
        color= Color.black; 
    }
    
    public void setThickness(int thickness)
    {
        if(thickness < 1)
            thickness = 1;
        this.thickness = thickness;
    }
    
    public void setColor(Color newcolor)
    {
        color = newcolor;
    }
  
    @Override
    public void draw(Graphics g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
