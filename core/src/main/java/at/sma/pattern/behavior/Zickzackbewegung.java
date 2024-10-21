package at.sma.pattern.behavior;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class Zickzackbewegung implements IBewegungsVerhalten {
    private float direction = 1; // Richtung (1 = nach rechts, -1 = nach links)

    @Override
    public void bewegen(Actor actor) {
        actor.setY(actor.getY() - 1); // Langsame Fallgeschwindigkeit
        actor.setX(actor.getX() + direction * 2); // Zickzack-Bewegung mit höherer Geschwindigkeit
        if (actor.getX() > 500 || actor.getX() < 0) { // Grenzen prüfen
            direction *= -1; // Richtung umkehren
        }
    }
}
