package ee.taltech.survivors.world.abilities;

import com.badlogic.gdx.math.Vector2;

public abstract class Ability {
    public float cooldown;

    public void execute(Vector2 originPosition, Vector2 targetPosition) {
    }

    public enum AbilityName {
        FIREBALL_LAUNCH,
        ARROW_SHOT
    }
}
