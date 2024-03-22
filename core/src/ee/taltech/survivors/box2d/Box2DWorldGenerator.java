package ee.taltech.survivors.box2d;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import static ee.taltech.survivors.helper.constants.Constants.PPM;

/**
 * The Box2DWorldGenerator class generates Box2D bodies for the TiledMap objects.
 */
public class Box2DWorldGenerator {
    private final com.badlogic.gdx.physics.box2d.World world;
    private final TiledMap map;

    /**
     * Constructs a new Box2DWorldGenerator instance.
     *
     * @param world The Box2D world.
     * @param map   The TiledMap to generate bodies from.
     */
    public Box2DWorldGenerator(com.badlogic.gdx.physics.box2d.World world, TiledMap map) {
        this.world = world;
        this.map = map;
    }

    /**
     * Generates Box2D bodies for the objects in the TiledMap.
     */
    public void generate() {
        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        // Iterate through the objects in the specified layer of the TiledMap
        for (MapObject object : map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rectangle = ((RectangleMapObject) object).getRectangle();

            // Configure body definition
            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rectangle.getX() + rectangle.getWidth() / 2 / PPM), (rectangle.getY() + rectangle.getHeight() / 2 / PPM));

            // Create the body
            body = world.createBody(bdef);

            // Set shape as a box
            shape.setAsBox(rectangle.getWidth() / 2 / PPM, rectangle.getHeight() / 2 / PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }

        // Clean up shape
        shape.dispose();
    }
}
