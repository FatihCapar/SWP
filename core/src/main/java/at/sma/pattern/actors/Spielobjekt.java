package at.sma.pattern.actors;

import at.sma.pattern.observer.Subject;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Spielobjekt extends Actor {
    protected Subject subject;
    public abstract void updateObserver();
}
