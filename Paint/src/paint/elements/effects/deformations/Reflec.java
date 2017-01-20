package paint.elements.effects.deformations;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.Reflection;

public class Reflec {

    public void draw(GraphicsContext g) {
        g.applyEffect(new Reflection());
    }
}
