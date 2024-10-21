package at.sma.pattern;

import at.sma.pattern.actors.Circle;
import at.sma.pattern.actors.RectangleLG;
import at.sma.pattern.actors.Triangle;
import at.sma.pattern.behavior.IBewegungsVerhalten;
import at.sma.pattern.behavior.Zickzackbewegung;
import at.sma.pattern.behavior.Wellenbewegung;
import at.sma.pattern.behavior.ZufallsBewegung;
import at.sma.pattern.observer.MovementObserver;
import at.sma.pattern.pattern.ShapeComposite;
import at.sma.pattern.pattern.ShapeFactory;
import at.sma.pattern.pattern.Singleton;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private BitmapFont font;
    private Sprite backGroundSprite;

    // Listen für die Actor-Objekte
    private List<RectangleLG> rectangles;
    private List<Triangle> triangles;
    private List<Circle> circles;

    private ShapeComposite shapeComposite;

    // Zufallszahlengenerator
    private Random random;

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture("libgdx.png");
        font = new BitmapFont();
        backGroundSprite = new Sprite(new Texture("background_800.jpg"));
        backGroundSprite.setPosition(0, 0);

        // Listen initialisieren
        rectangles = new ArrayList<>();
        triangles = new ArrayList<>();
        circles = new ArrayList<>();

        // Composite initialisieren
        shapeComposite = new ShapeComposite();

        // Zufallszahlengenerator initialisieren
        random = new Random();

        // Bewegungsverhalten
        IBewegungsVerhalten wellenbewegung = new Wellenbewegung();
        IBewegungsVerhalten zickzackbewegung = new Zickzackbewegung();
        IBewegungsVerhalten zufallsbewegung = new ZufallsBewegung();

        // Beispiel für die Erstellung von Objekten mit Factory
        ShapeFactory shapeFactory = Singleton.getInstance().getShapeFactory();

        // Generiere zufällige Formen und hänge Observer an
        generateRandomShapes(shapeFactory, 10, zickzackbewegung, wellenbewegung, zufallsbewegung);
    }

    private void generateRandomShapes(ShapeFactory shapeFactory, int count, IBewegungsVerhalten zickzackbewegung,
                                      IBewegungsVerhalten wellenbewegung, IBewegungsVerhalten zufallsbewegung) {
        for (int i = 0; i < count; i++) {
            float x = random.nextFloat() * 800; // Zufällige x-Position
            float y = random.nextFloat() * 600; // Zufällige y-Position

            // Erzeuge verschiedene Formen zufällig
            int shapeType = random.nextInt(3); // 0 = Kreis, 1 = Rechteck, 2 = Dreieck
            MovementObserver observer = new MovementObserver("ShapeObserver " + i);

            switch (shapeType) {
                case 0:
                    Circle circle = shapeFactory.createCircle(x, y, random.nextFloat() * 50 + 10, Color.BLUE, zufallsbewegung);
                    circle.attach(observer);  // Observer an Circle anhängen
                    circles.add(circle);
                    break;
                case 1:
                    RectangleLG rectangle = shapeFactory.createRectangle(x, y, random.nextFloat() * 100 + 50, random.nextFloat() * 50 + 20, Color.RED, zickzackbewegung);
                    rectangle.attach(observer);  // Observer an Rectangle anhängen
                    rectangles.add(rectangle);
                    break;
                case 2:
                    Triangle triangle = shapeFactory.createTriangle(x, y, random.nextFloat() * 50 + 20, random.nextFloat() * 40 + 10, Color.GREEN, wellenbewegung);
                    triangle.attach(observer);  // Observer an Triangle anhängen
                    triangles.add(triangle);
                    break;
            }
        }
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.LIGHT_GRAY);
        batch.begin();
        backGroundSprite.draw(batch);

        // Aktualisiere Bewegung und benachrichtige Observer, wenn der Boden erreicht ist
        for (RectangleLG rectangle : rectangles) {
            rectangle.updateBewegung();
            rectangle.draw(batch, 1);
        }
        for (Triangle triangle : triangles) {
            triangle.updateBewegung();
            triangle.draw(batch, 1);
        }
        for (Circle circle : circles) {
            circle.updateBewegung();
            circle.draw(batch, 1);
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
        font.dispose();
        backGroundSprite.getTexture().dispose();
    }
}
