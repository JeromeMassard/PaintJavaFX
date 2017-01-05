package paint.elements.components;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

public class Group extends Component {
    /** The list of Component to be drew */
    private List<Component> compound;

    public Group(int posX, int posY) {
        super(posX, posY);
        resetCompound();
    }

    /**
     * Add the component to the list of component to be draw
     * @param c 
     */
    public void addToCompound(Component c) {
        compound.add(c);
    }
    
    /**
     * Remove the component to the list of component to be draw
     * @param c 
     */
    public void removeFromCompound(Component c) {
        compound.remove(c);
    }
    
    /**
     * Resets the compound list
     */
    public final void resetCompound() {
        this.compound = new ArrayList<>();
    }

    @Override
    public void draw(GraphicsContext g) {
        compound.forEach((Component c) -> {
            c.draw(g);
        });
    }
}