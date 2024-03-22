package ee.taltech.survivors.box2d;

import com.badlogic.gdx.physics.box2d.*;

public class B2BodyGenerator {

    public static Body generarateCircleB2Body(World box2dWorld,
                                              float positionX,
                                              float positionY,
                                              BodyDef.BodyType bodyType,
                                              float radius,
                                              boolean isSensor,
                                              Object userData) {

        // Create a body definition
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(positionX, positionY);

        // Create the body in the world
        Body b2body = box2dWorld.createBody(bodyDef);

        // Create a fixture definition
        FixtureDef fixtureDef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
        fixtureDef.shape = shape;
        fixtureDef.isSensor = isSensor;

        // Attach the fixture to the body
        b2body.createFixture(fixtureDef);

        // Set user data for the body to identify it during collisions
        if (!(userData == null)) {
            b2body.setUserData(userData);
        }

        // Dispose the shape (after attaching it to the fixture)
        shape.dispose();

        return b2body;
    }
}
