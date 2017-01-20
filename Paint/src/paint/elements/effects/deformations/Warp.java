package paint.elements.effects.deformations;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.DisplacementMap;
import paint.elements.effects.Deformation;

public class Warp extends Deformation {
    @Override
    public void draw(GraphicsContext g) {
        g.applyEffect(new DisplacementMap());
    }
}
