package ee.taltech.survivors.world.abilities;

import com.badlogic.gdx.math.Vector2;
import ee.taltech.survivors.helper.config.ConfigReader;
import ee.taltech.survivors.helper.enums.ProjectileName;
import ee.taltech.survivors.world.World;
import ee.taltech.survivors.world.projectile.Projectile;

import java.util.Map;

import static ee.taltech.survivors.world.abilities.Ability.AbilityName.ARROW_SHOT;

public class ArrowShot extends Ability {
    private ProjectileName projectileName;

    public ArrowShot() {
        Map<String, String> abilityConfig = ConfigReader.getAbilityInfoMap(String.valueOf(ARROW_SHOT));

        cooldown = Float.parseFloat(abilityConfig.get("COOLDOWN"));
        projectileName = ProjectileName.valueOf(abilityConfig.get("PROJECTILE_NAME"));
    }

    @Override
    public void execute(Vector2 originPosition, Vector2 targetPosition) {
        World.getINSTANCE().getProjectilesManager().addProjectile(
                new Projectile(
                        projectileName,
                        originPosition,
                        targetPosition)
        );
    }
}
