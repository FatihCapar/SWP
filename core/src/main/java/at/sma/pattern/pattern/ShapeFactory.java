package at.sma.pattern.pattern;

import at.sma.pattern.actors.Circle;
import at.sma.pattern.actors.RectangleLG;
import at.sma.pattern.actors.Triangle;
import at.sma.pattern.behavior.IBewegungsVerhalten;
import com.badlogic.gdx.graphics.Color;

public class ShapeFactory {

    public RectangleLG createRectangle(float x, float y, float width, float height, Color color, IBewegungsVerhalten bewegungsVerhalten) {
        return new RectangleLG((int)x, (int)y, (int)width, (int)height, color, bewegungsVerhalten);
    }

    public Triangle createTriangle(float x, float y, float base, float height, Color color, IBewegungsVerhalten bewegungsVerhalten) {
        return new Triangle((int)x, (int)y, (int)base, (int)height, color, bewegungsVerhalten);
    }

    public Circle createCircle(float x, float y, float radius, Color color, IBewegungsVerhalten bewegungsVerhalten) {
        return new Circle((int)x, (int)y, (int)radius, color, bewegungsVerhalten);
    }
}
