package ee.taltech.survivors.screens.menuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ee.taltech.survivors.screens.selectionScreen.CharacterSelectionScreen;

public class MenuScreen implements Screen {
    private final Stage stage;
    private final TextureAtlas atlas;
    private final Skin skin;
    private Table table;
    private TextButton playButton, exitButton;
    private Label heading;
    private final SpriteBatch spriteBatch;
    private Music backgroundMusic;
    private final Sound clickSound;

    public MenuScreen() {
        atlas = new TextureAtlas("buttons/button.atlas");
        skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);
        Image background = new Image(new Texture("backgrounds/Wallpaper.png"));
        background.setSize(2000, 1100);
        spriteBatch = new SpriteBatch();
        stage = new Stage(new ScreenViewport(), spriteBatch);
        stage.addActor(background);
        clickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/UI/button_click.ogg"));
    }

    @Override
    public void show() {
        setupInputProcessor();
        setupBackgroundMusic();
        createUI();
    }

    private void setupInputProcessor() {
        Gdx.input.setInputProcessor(stage);
    }

    private void setupBackgroundMusic() {
        backgroundMusic = Gdx.audio.newMusic(Gdx.files.internal("music/background_music.ogg"));
        backgroundMusic.setVolume(0.3f);
        backgroundMusic.setLooping(true);
        backgroundMusic.play();
    }

    private void createUI() {
        createTable();
        createButtons();
        createHeading();
        addUIElementsToTable();
        addTableToStage();
    }

    private void createTable() {
        table = new Table(skin);
        table.setFillParent(true);
        table.center();
    }

    private void createButtons() {
        createPlayButton();
        createExitButton();
    }

    private void createPlayButton() {
        playButton = new TextButton("Play", skin);
        playButton.pad(15);
        setupButtonListeners(playButton, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                ((Game) Gdx.app.getApplicationListener()).setScreen(new CharacterSelectionScreen());
            }
        });
    }

    private void createExitButton() {
        exitButton = new TextButton("Exit", skin);
        exitButton.pad(15);
        setupButtonListeners(exitButton, new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                clickSound.play();
                Gdx.app.exit();
            }
        });
    }

    private void setupButtonListeners(TextButton button, ClickListener listener) {
        button.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if (button == exitButton) {
                    button.setColor(Color.RED);
                } else {
                    button.setColor(Color.BLUE);
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                button.setColor(Color.WHITE);
            }
        });
        button.addListener(listener);
    }

    private void createHeading() {
        heading = new Label("Project Survivors", skin);
        heading.setFontScale(2, 2);
    }

    private void addUIElementsToTable() {
        table.add(heading).spaceBottom(100);
        table.row();
        table.add(playButton).spaceBottom(20);
        table.row();
        table.add(exitButton);
    }

    private void addTableToStage() {
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        backgroundMusic.stop();
    }

    @Override
    public void dispose() {
        stage.dispose();
        atlas.dispose();
        skin.dispose();
        spriteBatch.dispose();
        backgroundMusic.dispose();
        clickSound.dispose();
    }
}
