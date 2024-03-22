package ee.taltech.survivors.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import ee.taltech.survivors.box2d.Box2DWorldGenerator;
import ee.taltech.survivors.world.worldManagers.PlayersManager;
import ee.taltech.survivors.world.worldManagers.ProjectilesManager;

import static ee.taltech.survivors.helper.constants.AssetsFilepaths.LOBBY_MAP_FILEPATH;

/**
 * The WorldManager class handles the game world including rendering, physics, and player management.
 */
public class World {
    private static World INSTANCE;
    private final PlayersManager playersManager;
    private final ProjectilesManager projectilesManager;

    private final TmxMapLoader mapLoader;
    private String mapName = LOBBY_MAP_FILEPATH;
    private TiledMap map;

    private com.badlogic.gdx.physics.box2d.World box2dWorld;

    /**
     * Constructs a new instance of WorldManager.
     * Initializes the game world, including the map, Box2D physics world, and player management.
     */
    private World() {
        playersManager = new PlayersManager();
        projectilesManager = new ProjectilesManager();

        // Initialize Box2D world
        box2dWorld = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);

        // Set up map, map loader, and map renderer
        mapLoader = new TmxMapLoader();
        map = mapLoader.load(mapName);

        // Generate Box2D world
        Box2DWorldGenerator worldGenerator = new Box2DWorldGenerator(box2dWorld, map);
        worldGenerator.generate();
    }

    /**
     * Retrieves the singleton instance of WorldManager.
     *
     * @return The singleton instance of WorldManager.
     */
    public static synchronized World getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new World();
        }
        return INSTANCE;
    }

    /**
     * Updates the game world.
     */
    public void update(float deltaTime) {
        try {
            box2dWorld.step(1 / 60f, 6, 2);
            playersManager.update(deltaTime);
            projectilesManager.update(deltaTime);
        } catch (NullPointerException ignore) {
        }
    }

    public com.badlogic.gdx.physics.box2d.World getBox2dWorld() {
        return box2dWorld;
    }

    public PlayersManager getPlayersManager() {
        return playersManager;
    }

    public ProjectilesManager getProjectilesManager() {
        return projectilesManager;
    }

    public void setBox2dWorld(com.badlogic.gdx.physics.box2d.World box2dWorld) {
        this.box2dWorld = box2dWorld;
    }

    public void setMap(String mapName) {
        this.mapName = mapName;
        map = mapLoader.load("maps/" + mapName);
    }

    public TiledMap getMap() {
        return map;
    }
}
