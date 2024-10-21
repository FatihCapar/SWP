package at.sma.pattern.behavior;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class ZufallsBewegung implements IBewegungsVerhalten {
    private final int screenWidth = 800; // Breite des Bildschirms
    private int counter = 0; // Zähler für die Bewegung

    @Override
    public void bewegen(Actor actor) {
        // Y-Bewegung, fällt langsamer nach unten
        actor.setY(actor.getY() - 0.5f); // Verringerte Fallgeschwindigkeit

        // Alle 30 Frames (oder Durchläufe) eine zufällige Bewegung berechnen
        if (counter % 30 == 0) {
            float newX = actor.getX() + (float) (Math.random() * 500 - 250);

            // Prüfen, ob der Actor den rechten oder linken Bildschirmrand berührt
            if (newX + actor.getWidth() > screenWidth) {
                // Teleportiere zum linken Rand
                newX = 0;
            } else if (newX < 0) {
                // Teleportiere zum rechten Rand
                newX = screenWidth - actor.getWidth();
            }

            actor.setX(newX);
        }

        // Zähler erhöhen
        counter++;
    }
}
