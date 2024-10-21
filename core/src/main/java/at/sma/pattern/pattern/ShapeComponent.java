package at.sma.pattern.pattern;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class ShapeComponent {
    public abstract void draw(SpriteBatch batch);  // Abstrakte Methode zum Zeichnen
    public abstract void updateBewegung();  // Abstrakte Methode zum Aktualisieren der Bewegung
}
