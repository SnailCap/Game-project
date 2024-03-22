package ee.taltech.survivors.animation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ee.taltech.survivors.helper.config.ConfigReader;
import ee.taltech.survivors.helper.enums.ProjectileName;

import java.util.Map;

import static ee.taltech.survivors.helper.enums.ConfigNodeName.*;

public class ProjectileAnimationManager extends AnimationManager {
    ProjectileName projectileName;

    public ProjectileAnimationManager(ProjectileName projectileName) {
        this.projectileName = projectileName;
        initializeFramedAnimations();
    }

    private void initializeFramedAnimations() {
        Map<String, String> textures = ConfigReader.getProjectileTextureMap(projectileName);

        // Retrieve texture properties
        String texturePath = textures.get(String.valueOf(PATH));

        // Retrieve frame duration and parse to float value
        float frameDuration = Float.parseFloat(textures.get(String.valueOf(FRAME_DURATION)));
        int columnWidth = Integer.parseInt(textures.get(String.valueOf(COLUMN_WIDTH)));
        int rowHeight = Integer.parseInt(textures.get(String.valueOf(ROW_HEIGHT)));
        
        // Split texture to frames for an animation object
        TextureRegion[] splitTexture = getTextureSplitToFrames(
                new Texture(texturePath),
                columnWidth,
                rowHeight
        );

//         Put a new animation based of obtained texture path and frame duration
        currentAnimation = new Animation<>(frameDuration, splitTexture);
    }
}
