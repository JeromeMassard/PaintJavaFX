package paint.elements.components;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import paint.elements.Element;

public class Component extends Element {
    /**
     * Defines the mode used to draw the component
     */
    public enum Mode {
        /** Fill the component */
        FILL,
        /** Stroke the component */
        STROKE,
        /** Fill and then stroke the component */
        FILLSTROKE
    }
    
    /** The x position of the component */
    protected int x;
    /** The y position of the component */
    protected int y;
    /** The thickness position of the component */
    protected int thickness;
    /** The primary color position of the component */
    protected Color primaryColor;
    /** The secondary position of the component */
    protected Color secondaryColor;
    /** The mode position of the component */
    protected Mode mode;
    
    public Component(int posX, int posY)
    {
        this.setPosition(posX, posY);
        this.setThickness(1);
        this.setPrimaryColor(Color.BLACK);
        this.setSecondaryColor(Color.BLACK);
        this.setMode(Mode.FILL);
    }
    
    /**
     * Sets the new position of the component
     * 
     * @param x The new x position
     * @param y The new y position 
     */
    public final void setPosition(int x, int y) {
        if (x < 0)
            x = 0;
        
        if (y < 0)
            y = 0;
                
        this.x = x;
        this.y = y;
    }
    
    /**
     * Sets the thickness of the component
     * @param thickness Width of borders
     */
    public final void setThickness(int thickness)
    {
        if(thickness < 1)
            thickness = 1;
        
        this.thickness = thickness;
    }
    
    /**
     * Sets the fill color for the inner part of the drew component
     * @param primaryColor The inner color
     */
    public final void setPrimaryColor(Color primaryColor)
    {
        this.primaryColor = primaryColor;
    }
    
    /**
     * Sets the stroke color for the outline of the drew component
     * @param secondaryColor The outline color
     */
    public final void setSecondaryColor(Color secondaryColor)
    {
        this.secondaryColor = secondaryColor;
    }
  
    /**
     * Switches the primaryColor with the secondaryColor
     */
    public final void switchColors() {
        Color color = Color.rgb((int) primaryColor.getRed(), (int) primaryColor.getGreen(), (int) primaryColor.getBlue(), primaryColor.getOpacity());
        
        this.secondaryColor = this.primaryColor;
        this.primaryColor = color;
    }
    
    /**
     * Sets the mode to draw the component
     * @param mode The mode used to draw
     */
    public final void setMode(Mode mode) {
        this.mode = mode;
    }
    
    @Override
    public void draw(GraphicsContext g) {
        
    }
}
