package at.sma.pattern.observer;

public class MovementObserver implements Observer {
    private String name;

    public MovementObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(float x, float y) {
        System.out.println(name + " has been notified: New Position -> (" + x + ", " + y + ")");
    }
}

