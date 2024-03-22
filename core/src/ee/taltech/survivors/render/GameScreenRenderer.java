package ee.taltech.survivors.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import ee.taltech.survivors.screens.gameScreen.GameScreen;
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

    public void renderAll(float stateTime) {
        this.stateTime = stateTime;

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
            TextureRegion currentFrame = gameCharacter.getAnimationManager().getCurrentKeyFrame(stateTime);
            float positionX = gameCharacter.getPositionX() - currentFrame.getRegionWidth() / 2f;
            float positionY = gameCharacter.getPositionY() - currentFrame.getRegionHeight() / 2f;
            TextureRegion flippedFrame = new TextureRegion(currentFrame);
            flippedFrame.flip(gameCharacter.getAnimationManager().isFlip(), false);

            batch.draw(
                    flippedFrame,
                    positionX,
                    positionY
            );
        } catch (NullPointerException ignore) {
        }
    }

    public void renderProjectiles() {
        for (Projectile projectile : world.getProjectilesManager().getProjectilesList()) {
            renderProjectile(projectile);
        }
    }

    public void renderProjectile(Projectile projectile) {
        TextureRegion currentFrame = projectile.getAnimationManager().getCurrentKeyFrame(stateTime);
        TextureRegion flippedFrame = new TextureRegion(currentFrame);
        flippedFrame.flip(projectile.isFlipTexture(), false);
        Body b2body = projectile.getB2body();

        double angleInRadians = Math.toRadians(projectile.getAngle());

        float textureOffset = projectile.getTextureHeadOffset();

        float positionX = b2body.getPosition().x - currentFrame.getRegionWidth() / 2f;
        float positionY = b2body.getPosition().y - currentFrame.getRegionHeight() / 2f;

        // Shift frame head to be aligned with b2body
        positionX -= (float) Math.cos(angleInRadians) * textureOffset;
        positionY -= (float) Math.sin(angleInRadians) * textureOffset;

        batch.draw(
                flippedFrame,
                positionX,
                positionY,
                flippedFrame.getRegionWidth() / 2f, // originX
                flippedFrame.getRegionHeight() / 2f, // originY
                flippedFrame.getRegionWidth(), // width
                flippedFrame.getRegionHeight(), // height
                1f, // scaleX
                1f, // scaleY
                projectile.getAngle() // rotation
        );
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
