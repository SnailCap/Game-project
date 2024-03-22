package ee.taltech.survivors.world.worldManagers;

import ee.taltech.survivors.world.projectile.Projectile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProjectilesManager implements WorldManager {
    private final Map<Integer, Projectile> projectiles;

    public ProjectilesManager() {
        projectiles = new HashMap<>();
    }

    @Override
    public void update(float deltaTime) {
        if (!projectiles.isEmpty()) {
            for (Projectile projectile : getProjectilesList()) {
                projectile.update(deltaTime);
            }
        }
    }

    public void addProjectile(Projectile projectile) {
        assignNewID(projectile);
        projectiles.put(projectile.getID(), projectile);
    }

    public void removeProjectile(Projectile projectile) {
        projectiles.remove(projectile.getID());
    }

    private void assignNewID(Projectile projectile) {
        if (getProjectilesList().isEmpty()) {
            projectile.setID(0);
            return;
        }

        int lastProjectileID = getProjectilesList().
                get(getProjectilesList().size() - 1).
                getID();
        projectile.setID(lastProjectileID + 1);
    }


    public List<Projectile> getProjectilesList() {
        return new ArrayList<>(projectiles.values());
    }
}
