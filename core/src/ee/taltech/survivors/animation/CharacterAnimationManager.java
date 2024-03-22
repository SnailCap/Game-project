package ee.taltech.survivors.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import ee.taltech.survivors.helper.config.ConfigReader;
import ee.taltech.survivors.helper.enums.ConfigNodeName;
import ee.taltech.survivors.input.InputManager;
import ee.taltech.survivors.world.character.GameCharacter;

import java.util.Map;

import static ee.taltech.survivors.helper.enums.ConfigNodeName.*;

public class CharacterAnimationManager extends AnimationManager {
    GameCharacter gameCharacter;

    public CharacterAnimationManager(GameCharacter gameCharacter) {
        super();
        this.gameCharacter = gameCharacter;
    }

    @Override
    public void update() {
        Body b2body = gameCharacter.getB2bodyManager().getB2body();
        try {
            if (!InputManager.getINSTANCE().anyPositionKeyPressed() || b2body.getLinearVelocity().isZero()) {
                changeCurrentAnimation(STAND);
                return;
            }

            if (b2body.getLinearVelocity().x < 0) {
                flip = true;
                changeCurrentAnimation(RUN);
            } else if (b2body.getLinearVelocity().x > 0) {
                flip = false;
                changeCurrentAnimation(RUN);
            }
        } catch (NullPointerException ignore) {
        }
    }

    public void initializeAnimations() {
        initializeStaticAnimations();
        initializeFramedAnimations();
        currentAnimation = animations.get(STAND);
    }

    private void initializeFramedAnimations() {
        Map<String, Map<String, String>> textures = ConfigReader.getCharacterClassFramedTextures(
                gameCharacter.getCharacterClassName()
        );

        for (Map.Entry<String, Map<String, String>> entry : textures.entrySet()) {
            // Retrieve name of JSON node
            ConfigNodeName jsonNodeName = ConfigNodeName.valueOf(entry.getKey());

            // Retrieve texture properties
            Map<String, String> textureProperties = entry.getValue();

            // Retrieve path of the texture
            String texturePath = textureProperties.get(
                    String.valueOf(PATH)
            );

            int columnWidth = Integer.parseInt(textureProperties.get(String.valueOf(COLUMN_WIDTH)));
            int rowHeight = Integer.parseInt(textureProperties.get(String.valueOf(ROW_HEIGHT)));

            // Split texture to frames for an animation object
            TextureRegion[] splitTexture = getTextureSplitToFrames(
                    new Texture(texturePath),
                    columnWidth,
                    rowHeight
            );

            // Retrieve frame duration and parse to float value
            float frameDuration = Float.parseFloat(textureProperties.get(
                    String.valueOf(ConfigNodeName.FRAME_DURATION))
            );

            // Put a new animation based of obtained texture path and frame duration
            // With a JSON node key associated to it
            animations.put(
                    jsonNodeName,
                    new Animation<>(frameDuration, splitTexture)
            );
        }
    }

    private void initializeStaticAnimations() {
        Map<String, Map<String, String>> textures = ConfigReader.getCharacterClassStaticTextures(
                gameCharacter.getCharacterClassName()
        );

        for (Map.Entry<String, Map<String, String>> entry : textures.entrySet()) {

            // Retrieve name of JSON node
            ConfigNodeName jsonNodeName = ConfigNodeName.valueOf(entry.getKey());

            // Retrieve texture properties
            Map<String, String> textureProperties = entry.getValue();

            // Retrieve path of the texture
            String texturePath = textureProperties.get(
                    String.valueOf(PATH)
            );

            int columnWidth = Integer.parseInt(textureProperties.get(String.valueOf(COLUMN_WIDTH)));
            int rowHeight = Integer.parseInt(textureProperties.get(String.valueOf(ROW_HEIGHT)));

            // Split texture to frames for an animation object
            TextureRegion[] splitTexture = getTextureSplitToFrames(
                    new Texture(texturePath),
                    columnWidth,
                    rowHeight
            );

            // Put a new animation based of obtained texture path and frame duration
            // With a JSON node key associated to it
            animations.put(
                    jsonNodeName,
                    new Animation<>(1f, splitTexture)
            );
        }
    }
}
