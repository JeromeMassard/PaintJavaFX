package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;

/**
 * Square class
 * Can draw a filled square, a stroked square and square with outline
 * @author bilombardy
 */
public class Square extends Component {
    private int length;
    
    public Square(int posX, int posY, int length) {
        super(posX, posY);
        setLength(length);
    }
    
    /**
     * Sets the length of width and height for the square
     * @param length The width and height
     */
    private void setLength(int length) {
        if (length < 1)
            length = 1;
        
        this.length = length;
    }
    
    @Override
    public void draw(GraphicsContext g) {
        g.setLineWidth(thickness);
        
        switch (mode) {
            case STROKE:
                g.setStroke(primaryColor);
                g.strokeRect(x, y, length, length);
                break;
            case FILLSTROKE:
                g.setFill(primaryColor);
                g.fillRect(x, y, length, length);
                g.setStroke(secondaryColor);
                g.strokeRect(x, y, length, length);
                break;
            default:
                g.setFill(primaryColor);
                g.fillRect(x, y, length, length);
                break;
        }
    }
}
