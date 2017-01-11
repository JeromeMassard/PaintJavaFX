package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author bilombardy
 */
public class Text extends Component {
    private String value;
    
    public Text(int posX, int posY, String value) {
        super(posX, posY);
        this.value = value;
    }

    @Override
    public void draw(GraphicsContext g) {
        g.fillText(value, x, y);
    }
}