package paint.elements.components;

import java.awt.Graphics;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author bilombardy
 */
public class Oval extends Component {

    private int width;
    private int height;

    public Oval(int posX, int posY, int width, int height) {
        super(posX, posY);
        setWidth(width);
        setHeight(height);
    }

    private void setWidth(int width) {
        if (width < 1) {
            width = 1;
        }

        this.width = width;
    }

    private void setHeight(int height) {
        if (height < 1) {
            height = 1;
        }

        this.height = height;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.setFill(color);
        g.fillOval(x, y, width, height);
    }
}
