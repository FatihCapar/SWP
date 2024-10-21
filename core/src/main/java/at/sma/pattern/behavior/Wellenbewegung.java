package at.sma.pattern.behavior;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Wellenbewegung implements IBewegungsVerhalten {
    private float amplitude = 18; // Erhöhte Höhe der Welle
    private float frequency = 10.8f; // Höhere Frequenz für intensivere Bewegung
    private float elapsedTime = 8;

    @Override
    public void bewegen(Actor actor) {
        elapsedTime += Gdx.graphics.getDeltaTime(); // Zeit seit dem letzten Frame
        actor.setY(actor.getY() - 2); // Etwas schnellere Fallgeschwindigkeit
        actor.setX(actor.getX() + amplitude * (float)Math.sin(frequency * elapsedTime)); // Stärkere Wellenbewegung
    }
}
