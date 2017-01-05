package paint.elements.components;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

public class Group extends Component {
    private List<Component> compound;

    public Group(int posX, int posY) {
        super(posX, posY);
        this.compound = new ArrayList<>();
    }

    public void addToCompound(Component c) {
        compound.add(c);
    }
    
    public void removeFromCompound(Component c) {
        compound.remove(c);
    }
    
    public void setPosition(int newX, int newY)
    {
        x = newX;
        y = newY;
    }

    @Override
    public void draw(GraphicsContext g) {
        compound.forEach((Component c) -> {
            c.draw(g);
        });
    }
}