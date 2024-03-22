package ee.taltech.survivors.world.character;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import ee.taltech.survivors.box2d.B2BodyGenerator;
import ee.taltech.survivors.helper.config.ConfigReader;
import ee.taltech.survivors.helper.enums.ConfigNodeName;

import java.util.Map;

public class CharacterB2bodyManager {
    private GameCharacter gameCharacter;

    private Body b2body;
    private float b2bodyRadius;
    private float b2bodySizeMultiplier;


    public CharacterB2bodyManager(GameCharacter gameCharacter) {
        this.gameCharacter = gameCharacter;
    }

    public void setB2BodyPosition(float positionX, float positionY) {
        b2body.setTransform(
                positionX,
                positionY,
                b2body.getAngle());
    }

    public void initializeCharacterB2body() {
        BodyDef.BodyType bodyType = BodyDef.BodyType.StaticBody;
        boolean isSensor = true;

        if (gameCharacter instanceof MainCharacter) {
            bodyType = BodyDef.BodyType.DynamicBody;
            isSensor = false;
        }

        Map<String, String> classConfig = ConfigReader.getCharacterClassInfoMap(
                gameCharacter.getCharacterClassName()
        );

        b2bodySizeMultiplier = Float.parseFloat(classConfig.get("B2BODY_SIZE_MULTIPLIER"));
        float textureScale = Float.parseFloat(classConfig.get(
                String.valueOf(ConfigNodeName.TEXTURE_SCALE)
        ));
        b2bodyRadius = textureScale * b2bodySizeMultiplier;


        b2body = B2BodyGenerator.generarateCircleB2Body(
                gameCharacter.getBox2dWorld(),
                gameCharacter.getPositionX(),
                gameCharacter.getVelocityY(),
                bodyType,
                b2bodyRadius,
                isSensor,
                this
        );
        setB2BodyPosition(gameCharacter.getPositionX(), gameCharacter.getPositionY());
    }

    public void setB2bodyLinearVelocity(float velocityX, float velocityY) {
        b2body.setLinearVelocity(velocityX, velocityY);
    }

    public Body getB2body() {
        return b2body;
    }

    public void setB2body(Body b2body) {
        this.b2body = b2body;
    }

    public float getB2bodyRadius() {
        return b2bodyRadius;
    }

    public void setB2bodyRadius(float b2bodyRadius) {
        this.b2bodyRadius = b2bodyRadius;
    }

    public float getB2bodySizeMultiplier() {
        return b2bodySizeMultiplier;
    }

    public void setB2bodySizeMultiplier(float b2bodySizeMultiplier) {
        this.b2bodySizeMultiplier = b2bodySizeMultiplier;
    }
}
