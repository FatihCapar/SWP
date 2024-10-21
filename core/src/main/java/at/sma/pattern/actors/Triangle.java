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

import static java.awt.Color.getColor;

public class Triangle extends Spielobjekt implements Subject {
    private Texture texture;
    private IBewegungsVerhalten bewegungsVerhalten;
    private List<Observer> observers = new ArrayList<>();
    private boolean hasReachedGround = false;

    public Triangle(float x, float y, float base, float height, Color color, IBewegungsVerhalten bewegungsVerhalten) {
        setX(x);
        setY(y);
        setWidth(base);
        setHeight(height);
        setColor(color);
        this.bewegungsVerhalten = bewegungsVerhalten;
        createTexture();
    }


    private void createTexture() {
        Pixmap pixmap = new Pixmap((int) getWidth(), (int) getHeight(), Pixmap.Format.RGBA8888);
        pixmap.setColor(getColor());

        // Zeichne das gefüllte Dreieck
        int[] xPoints = {0, (int) (getWidth() / 2), (int) getWidth()};
        int[] yPoints = {0, (int) getHeight(), 0};

        // Füllmethode für das Dreieck
        fillTriangle(pixmap, xPoints, yPoints);

        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    private void fillTriangle(Pixmap pixmap, int[] xPoints, int[] yPoints) {
        int minX = Math.min(Math.min(xPoints[0], xPoints[1]), xPoints[2]);
        int maxX = Math.max(Math.max(xPoints[0], xPoints[1]), xPoints[2]);
        int minY = Math.min(Math.min(yPoints[0], yPoints[1]), yPoints[2]);
        int maxY = Math.max(Math.max(yPoints[0], yPoints[1]), yPoints[2]);

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                if (isInsideTriangle(x, y, xPoints, yPoints)) {
                    pixmap.setColor(getColor());
                    pixmap.drawPixel(x, y);
                }
            }
        }
    }

    private boolean isInsideTriangle(int x, int y, int[] xPoints, int[] yPoints) {
        boolean a = ((xPoints[1] - xPoints[0]) * (y - yPoints[0]) - (x - xPoints[0]) * (yPoints[1] - yPoints[0])) < 0;
        boolean b = ((xPoints[2] - xPoints[1]) * (y - yPoints[1]) - (x - xPoints[1]) * (yPoints[2] - yPoints[1])) < 0;
        boolean c = ((xPoints[0] - xPoints[2]) * (y - yPoints[2]) - (x - xPoints[2]) * (yPoints[0] - yPoints[2])) < 0;

        return (a == b) && (b == c);
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
