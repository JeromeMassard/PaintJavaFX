package paint.elements.effects.deformations;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DropShadow;

public class Shadow {
    
    public void draw(GraphicsContext g) {
        g.applyEffect(new DropShadow());
    }
}
