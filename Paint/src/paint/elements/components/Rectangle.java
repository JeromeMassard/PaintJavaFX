package paint.elements.components;

import java.awt.Graphics;

/**
 * 
 * @author bilombardy
 */
public class Rectangle extends Component {
    private int width;
    private int height;
    
    public Rectangle(int posX, int posY, int width, int height) {
        super(posX, posY);
        setWidth(width);
        setHeight(height);
    }
    
    private void setWidth(int width) {
        if (width < 1)
            width = 1;
        
        this.width = width;
    }
    
    private void setHeight(int height) {
        if (height < 1)
            height = 1;
        
        this.height = height;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        
        if (fill)
            g.fillRect(x, y, width, height);
        else
            g.drawRect(x, y, width, height);
    }
}