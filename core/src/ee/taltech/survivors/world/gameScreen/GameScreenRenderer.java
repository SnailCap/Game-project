package ee.taltech.survivors.world.gameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import ee.taltech.survivors.world.World;
import ee.taltech.survivors.world.character.GameCharacter;
import ee.taltech.survivors.world.character.MainCharacter;
import ee.taltech.survivors.world.character.PlayerCharacter;
import ee.taltech.survivors.world.projectile.Projectile;

public class GameScreenRenderer {
    private GameScreen gameScreen;
    private float stateTime;

    private SpriteBatch batch;
    private World world;
    private OrthographicCamera camera;
    private MainCharacter mainCharacter;
    private final Box2DDebugRenderer b2DebugRenderer;
    private final OrthogonalTiledMapRenderer mapRenderer;

    public GameScreenRenderer(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        this.batch = gameScreen.getBatch();
        this.camera = gameScreen.getCamera();

        mainCharacter = MainCharacter.getINSTANCE();
        world = World.getINSTANCE();
        b2DebugRenderer = new Box2DDebugRenderer();
        mapRenderer = new OrthogonalTiledMapRenderer(world.getMap());
    }

    public void renderAll(float deltaTime) {
        stateTime += deltaTime;

        clearUtils();
        setProjectionMatrix();

        batch.begin();

        renderMap();
        renderMainCharacter();
        renderPlayers();
        renderEnemies();
        renderProjectiles();

        batch.end();

        renderB2Debug();
    }

    public void renderMap() {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

    public void renderMainCharacter() {
        renderGameCharacter(mainCharacter);
    }

    public void renderPlayers() {
        for (PlayerCharacter playerCharacter : world.getPlayersManager().getPlayersList()) {
            renderGameCharacter(playerCharacter);
        }
    }

    public void renderGameCharacter(GameCharacter gameCharacter) {
        try {
            float positionX = gameCharacter.getPositionX() - gameCharacter.textureManager.getCurrentKeyFrame(stateTime).getRegionWidth() / 2f;
            float positionY = gameCharacter.getPositionY() - gameCharacter.textureManager.getCurrentKeyFrame(stateTime).getRegionHeight() / 2f;

            TextureRegion currentFrame = gameCharacter.getAnimationManager().getCurrentKeyFrame(stateTime);
            batch.draw(
                    currentFrame,
                    positionX,
                    positionY
            );
        } catch (NullPointerException ignore) {
        }
    }

    public void renderProjectiles() {
        for (Projectile projectile : world.getProjectilesManager().getProjectilesList()) {
            projectile.render(batch);
        }
    }

    public void renderEnemies() {
    }

    public void renderB2Debug() {
        b2DebugRenderer.render(world.getBox2dWorld(), camera.combined);
        b2DebugRenderer.setDrawBodies(true);
    }

    public void clearUtils() {
        ScreenUtils.clear(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public void setProjectionMatrix() {
        batch.setProjectionMatrix(camera.combined);
    }

}
