package at.sma.pattern.pattern;

public class Singleton {
    private static Singleton oSingletonInstance = new Singleton();
    private ShapeFactory shapeFactory = new ShapeFactory(); // Instanziierung der ShapeFactory

    private Singleton() {
        // Dieser Konstruktor kann nicht verwendet werden
    }

    public static Singleton getInstance() {
        return oSingletonInstance;
    }

    public ShapeFactory getShapeFactory() {
        return shapeFactory; // Rückgabe der ShapeFactory-Instanz
    }

    public static String writeText() {
        return "Oje";
    }
}
