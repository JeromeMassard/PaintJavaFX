package paint.elements.components;

import java.util.LinkedList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/**
 * Represents a Brush
 * Composed of multiple points that defines the layout
 * @author bilombardy
 */
public class Brush extends Component {
    private List<Point> points;
    
    public Brush(int posX, int posY) {
        super(posX, posY);
        this.points = new LinkedList<>();
    }
    
    @Override
    public void draw(GraphicsContext gc) {
        gc.setLineWidth(thickness);
        gc.setFill(primaryColor);
        
        Point pt = new Point(x, y, true);
        pt.setPrimaryColor(primaryColor);
        pt.setThickness(thickness);
        points.add(pt);
        
        points.forEach((p) -> {
            p.draw(gc);
        });
    }
}
