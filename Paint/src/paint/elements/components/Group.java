package paint.elements.components;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

public class Group extends Component {
    
    private List<Component> compound = new ArrayList<>();
    
    public Group(int posX, int posY) {
        super(posX, posY);
        
    }
    
    public void addToCompound(Component c)
    {
        compound.add(c);
    }
    
    @Override
    public void draw(GraphicsContext g) {

        for (Component c : compound)
        {
            c.draw(g);
        }
    }
    
}