package paint.elements.effects.colorations;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.SepiaTone;
import paint.elements.effects.Coloration;

public class Sepia extends Coloration {
    @Override
    public void draw(GraphicsContext g) {
        g.applyEffect(new SepiaTone());
    }
}