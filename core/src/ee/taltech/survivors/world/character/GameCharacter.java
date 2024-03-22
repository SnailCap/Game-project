package ee.taltech.survivors.world.character;

import com.badlogic.gdx.math.Vector2;
import ee.taltech.survivors.animation.CharacterAnimationManager;
import ee.taltech.survivors.helper.Helper;
import ee.taltech.survivors.helper.config.ConfigReader;
import ee.taltech.survivors.helper.enums.CharacterClassName;
import ee.taltech.survivors.world.World;
import ee.taltech.survivors.world.abilities.Ability;

import java.util.Map;

import static ee.taltech.survivors.helper.constants.Constants.INITIAL_SPAWN_POSITION_X;
import static ee.taltech.survivors.helper.constants.Constants.INITIAL_SPAWN_POSITION_Y;


/**
 * The GameCharacter class represents a generic game character.
 */
public abstract class GameCharacter {
    public CharacterClassName characterClassName;

    public CharacterAnimationManager animationManager;

    public CharacterB2bodyManager b2bodyManager;

    public Vector2 position;
    public Vector2 velocity;
    public float health;
    public Ability mainAbility;
    public float movementSpeed;

    // Box2D physics attributes
    public com.badlogic.gdx.physics.box2d.World box2dWorld;

    public GameCharacter() {
        position = new Vector2(INITIAL_SPAWN_POSITION_X, INITIAL_SPAWN_POSITION_Y);
        velocity = new Vector2(0, 0);

        b2bodyManager = new CharacterB2bodyManager(this);
        animationManager = new CharacterAnimationManager(this);

        box2dWorld = World.getINSTANCE().getBox2dWorld();
    }

    public GameCharacter(CharacterClassName characterClassName) {
        this();
        setCharacterClassName(characterClassName);
        initializeCharacterDataFromCharacterClass();

        b2bodyManager.initializeCharacterB2body();
        animationManager.initializeAnimations();
    }

    public void update() {
        updateAnimation();
        updatePosition();
    }

    public void updatePosition() {
        b2bodyManager.setB2BodyPosition(position.x, position.y);
    }

    public void updateAnimation() {
        animationManager.update();
    }

    public void initializeCharacterDataFromCharacterClass() {
        Map<String, String> classConfig = ConfigReader.getCharacterClassInfoMap(characterClassName);

        health = Integer.parseInt(classConfig.get("HP"));
        movementSpeed = Integer.parseInt(classConfig.get("MOVEMENT_SPEED"));
        mainAbility = Helper.getAbilityObjectByClassPath(classConfig.get("MAIN_ABILITY_CLASS_PATH"));
    }

    public CharacterClassName getCharacterClassName() {
        return characterClassName;
    }

    public void setCharacterClassName(CharacterClassName characterClassName) {
        this.characterClassName = characterClassName;
    }

    public float getPositionX() {
        return position.x;
    }

    public float getPositionY() {
        return position.y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPositionX(float value) {
        position.x = value;
    }

    public void setPositionY(float value) {
        position.y = value;
    }

    public void setPosition(float X, float Y) {
        position.x = X;
        position.y = Y;
    }

    public void setPosition(Vector2 vector2) {
        position = vector2;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public float getVelocityX() {
        return velocity.x;
    }

    public float getVelocityY() {
        return velocity.y;
    }

    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(float X, float Y) {
        velocity.x = X;
        velocity.y = Y;
    }

    public void setVelocityX(float X) {
        velocity.x = X;
    }

    public void setVelocityY(float Y) {
        velocity.y = Y;
    }

    public Ability getMainAbility() {
        return mainAbility;
    }

    public void setMainAbility(Ability mainAbility) {
        this.mainAbility = mainAbility;
    }

    public com.badlogic.gdx.physics.box2d.World getBox2dWorld() {
        return box2dWorld;
    }

    public CharacterAnimationManager getAnimationManager() {
        return animationManager;
    }

    public CharacterB2bodyManager getB2bodyManager() {
        return b2bodyManager;
    }
}
