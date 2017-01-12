package paint.elements.effects.deformations;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.GaussianBlur;
import paint.elements.effects.Deformation;

public class Blur extends Deformation {
    private float radius;
    
    public Blur(float radius) {
        this.radius = radius;
    }
    
    @Override
    public void draw(GraphicsContext g) {
        g.applyEffect(new GaussianBlur(radius));
    }
}
