package ee.taltech.survivors.world.projectile;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import ee.taltech.survivors.animation.ProjectileAnimationManager;
import ee.taltech.survivors.box2d.B2BodyGenerator;
import ee.taltech.survivors.helper.config.ConfigReader;
import ee.taltech.survivors.helper.enums.ProjectileName;
import ee.taltech.survivors.world.World;

import java.util.Map;

import static ee.taltech.survivors.helper.constants.Constants.PPM;
import static ee.taltech.survivors.helper.enums.ConfigNodeName.*;

public class Projectile {
    private int ID;

    private final ProjectileName name;

    private final ProjectileAnimationManager animationManager;

    private float textureHeadOffset, spawnDistanceFromOrigin;
    private int textureScale;
    private boolean flipTexture;

    private Body b2body;
    private String b2bodyShapeName;
    private float b2bodyRadius, b2BodySizeMultiplier;

    private Vector2 position, direction;
    private float angle;

    private float damage, speed;

    public Projectile(ProjectileName projectileName, Vector2 originPosition, Vector2 targetPosition) {
        // Set projectile name
        name = projectileName;

        animationManager = new ProjectileAnimationManager(projectileName);

        // Calculate direction vector from origin to target and normalize it
        direction = targetPosition.sub(originPosition).nor();

        // Load projectile configuration data
        loadConfigurationData();

        // Set initial position and add distance from where to spawn accordingly to origin point
        position = new Vector2(originPosition).add(new Vector2(
                        direction.x * spawnDistanceFromOrigin,
                        direction.y * spawnDistanceFromOrigin
                )
        );

        // Define Box2D body for the projectile
        b2body = B2BodyGenerator.generarateCircleB2Body(
                World.getINSTANCE().getBox2dWorld(),
                position.x,
                position.y,
                BodyDef.BodyType.DynamicBody,
                b2bodyRadius,
                true,
                this
        );

        // Set linear velocity for the Box2D body based on direction and speed
        this.direction = direction.scl(speed);

        // Rotate sprite accordingly to the velocity vector
        angle = this.direction.angleDeg();
    }

    public void update(float deltaTime) {
        System.out.println(direction);
        position.x += direction.x * deltaTime;
        position.y += direction.y * deltaTime;
        b2body.setTransform(
                new Vector2(position.x, position.y),
                b2body.getAngle()
        );
    }

    private void loadConfigurationData() {

        // Load projectile configuration from a configuration file
        Map<String, String> projectileConfig = ConfigReader.getProjectileInfoMap(name);
        Map<String, String> projectileTexture = ConfigReader.getProjectileTextureMap(name);

        textureScale = Integer.parseInt(projectileTexture.get(String.valueOf(TEXTURE_SCALE)));
        textureHeadOffset = Float.parseFloat(projectileTexture.get(String.valueOf(HEAD_OFFSET)));
        flipTexture = Boolean.parseBoolean(projectileTexture.get(String.valueOf(FLIP)));
        spawnDistanceFromOrigin = Float.parseFloat(projectileConfig.get(String.valueOf(SPAWN_DISTANCE_FROM_ORIGIN)));

        // Load Box2D body shape and size multiplier
        b2bodyShapeName = projectileConfig.get(String.valueOf(B2BODY_SHAPE));
        b2BodySizeMultiplier = Float.parseFloat(projectileConfig.get(String.valueOf(B2BODY_SIZE_MULTIPLIER)));
        b2bodyRadius = (textureScale * b2BodySizeMultiplier) / PPM;

        // Load damage and speed of the projectile
        damage = Integer.parseInt(projectileConfig.get("DAMAGE"));
        speed = Float.parseFloat(projectileConfig.get("SPEED"));
    }

    public ProjectileAnimationManager getAnimationManager() {
        return animationManager;
    }

    public float getB2bodyRadius() {
        return b2bodyRadius;
    }

    public Body getB2body() {
        return b2body;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getDamage() {
        return damage;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public float getPositionX() {
        return position.x;
    }

    public float getPositionY() {
        return position.y;
    }

    public float getAngle() {
        return angle;
    }

    public float getTextureHeadOffset() {
        return textureHeadOffset;
    }

    public boolean isFlipTexture() {
        return flipTexture;
    }

    public float textureHeadOffset() {
        return textureHeadOffset;
    }

    public float getSpawnDistanceFromOrigin() {
        return spawnDistanceFromOrigin;
    }

    public int getTextureScale() {
        return textureScale;
    }
}

