package ee.taltech.survivors.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ee.taltech.survivors.helper.enums.ConfigNodeName;

import java.util.HashMap;
import java.util.Map;

public abstract class AnimationManager {
    public Map<ConfigNodeName, Animation<TextureRegion>> animations;
    public Animation<TextureRegion> currentAnimation;
    public ConfigNodeName currentAnimationName;
    public boolean flip;

    public AnimationManager() {
        animations = new HashMap<>();
        flip = false;
    }

    public void update() {
    }

    public void changeCurrentAnimation(ConfigNodeName node) {
        currentAnimationName = node;
        currentAnimation = animations.get(node);
    }

    public static TextureRegion[] getTextureSplitToFrames(Texture texture, int columnWidth, int rowHeight) {
        int columns = texture.getWidth() / columnWidth;
        int rows = texture.getHeight() / rowHeight;

        TextureRegion[][] textureRegion = TextureRegion.split(
                texture,
                columnWidth,
                rowHeight);

        TextureRegion[] splitTexture = new TextureRegion[columns * rows];

        int index = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                splitTexture[index++] = textureRegion[row][col];
            }
        }

        return splitTexture;
    }

    public TextureRegion getCurrentKeyFrame(float stateTime) {
        return currentAnimation.getKeyFrame(stateTime, true);
    }

    public ConfigNodeName getCurrentAnimationName() {
        return currentAnimationName;
    }

    public boolean isFlip() {
        return flip;
    }
}
