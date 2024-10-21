package at.sma.pattern.pattern;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public class ShapeComposite extends ShapeComponent {
    private List<ShapeComponent> components = new ArrayList<>();

    public void add(ShapeComponent component) {
        components.add(component);
    }

    public void remove(ShapeComponent component) {
        components.remove(component);
    }

    @Override
    public void draw(SpriteBatch batch) {
        for (ShapeComponent component : components) {
            component.draw(batch);  // Zeichne alle untergeordneten Komponenten
        }
    }

    @Override
    public void updateBewegung() {
        for (ShapeComponent component : components) {
            component.updateBewegung();  // Aktualisiere die Bewegung aller untergeordneten Komponenten
        }
    }
}
