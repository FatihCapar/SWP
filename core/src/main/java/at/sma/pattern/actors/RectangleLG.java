package at.sma.pattern.actors;

import at.sma.pattern.behavior.IBewegungsVerhalten;
import at.sma.pattern.observer.Observer;
import at.sma.pattern.observer.Subject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;

public class RectangleLG extends Spielobjekt implements Subject {
    private Texture texture;
    private IBewegungsVerhalten bewegungsVerhalten;
    private List<Observer> observers = new ArrayList<>();
    private boolean hasReachedGround = false;

    public RectangleLG(float x, float y, float width, float height, Color color, IBewegungsVerhalten bewegungsVerhalten) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
        setColor(color);
        this.bewegungsVerhalten = bewegungsVerhalten;
        createTexture();
    }


    private void createTexture() {
        Pixmap pixmap = new Pixmap((int) getWidth(), (int) getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(getColor());
        pixmap.fillRectangle(0, 0, (int) getWidth(), (int) getHeight());
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

    public void updateBewegung() {
        bewegungsVerhalten.bewegen(this);
        if (getY() <= 0 && !hasReachedGround) {
            notifyObservers(); // Nur einmal benachrichtigen
            hasReachedGround = true; // Flag setzen, um weitere Benachrichtigungen zu verhindern
        }
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(getX(), getY()); // Position an Beobachter weitergeben
        }
    }

    @Override
    public void updateObserver() {
        // Implementierung für den Observer
    }
}
