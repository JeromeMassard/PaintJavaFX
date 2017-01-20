package paint.elements.effects.colorations;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.ColorAdjust;
import paint.elements.effects.Coloration;

public class BlackAndWhite extends Coloration {
    @Override
    public void draw(GraphicsContext g) {
        g.applyEffect(new ColorAdjust());
    }
}