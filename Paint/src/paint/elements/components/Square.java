package paint.elements.components;

import java.awt.Graphics;

/**
 * 
 * @author bilombardy
 */
public class Square extends Component {
    private int length;
    
    public Square(int posX, int posY, int length) {
        super(posX, posY);
        setLength(length);
    }
    
    private void setLength(int length) {
        if (length < 1)
            length = 1;
        
        this.length = length;
    }
    
    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        
        if (fill)
            g.fillRect(x, y, length, length);
        else
            g.drawRect(x, y, length, length);
    }
}