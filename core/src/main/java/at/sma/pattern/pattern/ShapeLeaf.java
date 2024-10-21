package at.sma.pattern.pattern;

import at.sma.pattern.actors.Circle;
import at.sma.pattern.actors.RectangleLG;
import at.sma.pattern.actors.Triangle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ShapeLeaf extends ShapeComponent {
    private Object shape; // Halte ein generisches Objekt für Circle, RectangleLG, oder Triangle

    public ShapeLeaf(Object shape) {
        this.shape = shape;
    }

    @Override
    public void draw(SpriteBatch batch) {
        if (shape instanceof Circle) {
            ((Circle) shape).draw(batch, 1); // Zeichne einen Circle
        } else if (shape instanceof RectangleLG) {
            ((RectangleLG) shape).draw(batch, 1); // Zeichne ein RectangleLG
        } else if (shape instanceof Triangle) {
            ((Triangle) shape).draw(batch, 1); // Zeichne ein Triangle
        }
    }

    @Override
    public void updateBewegung() {
        if (shape instanceof Circle) {
            ((Circle) shape).updateBewegung(); // Aktualisiere die Bewegung von Circle
        } else if (shape instanceof RectangleLG) {
            ((RectangleLG) shape).updateBewegung(); // Aktualisiere die Bewegung von RectangleLG
        } else if (shape instanceof Triangle) {
            ((Triangle) shape).updateBewegung(); // Aktualisiere die Bewegung von Triangle
        }
    }
}
