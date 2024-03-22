package ee.taltech.survivors.screens.selectionScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import ee.taltech.survivors.helper.enums.CharacterClassName;
import ee.taltech.survivors.screens.gameScreen.GameScreen;

import static ee.taltech.survivors.helper.constants.AssetsFilepaths.DEFAULT_CHARACTER_TEXTURE_FILEPATH;

public class CharacterSelectionScreen extends InputAdapter implements Screen {
    private final Stage stage;
    private final BitmapFont customFont;
    private final Music ambience;

    public CharacterSelectionScreen() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        ambience = Gdx.audio.newMusic(Gdx.files.internal("sounds/game/ambience/Forest_Ambience_Loop.ogg"));
        ambience.setVolume(0.3f);
        ambience.setLooping(true);
        ambience.play();

        customFont = new BitmapFont(Gdx.files.internal("fonts/white.fnt"));
        Image backgroundImage = new Image(new Texture(Gdx.files.internal("backgrounds/background.jpg")));
        backgroundImage.setSize(1920, 1080);
        stage.addActor(backgroundImage);

        Table table = new Table();
        table.defaults().space(70);
        table.setFillParent(true);
        stage.addActor(table);

        Label.LabelStyle titleLabelStyle = new Label.LabelStyle();
        titleLabelStyle.font = customFont;
        titleLabelStyle.fontColor = Color.WHITE;
        titleLabelStyle.font.getData().setScale(4f);

        Label titleLabel = new Label("Choose a Class", titleLabelStyle);
        table.add(titleLabel).colspan(4).padBottom(20).row();

        addCharacter(table, CharacterClassName.KNIGHT);
        addCharacter(table, CharacterClassName.MAGE);
        addCharacter(table, CharacterClassName.ARCHER);
        addCharacter(table, CharacterClassName.ASSASSIN);
    }

    private void addCharacter(Table table, CharacterClassName className) {
        Table characterTable = new Table();
        characterTable.pad(20);

        characterTable.setBackground(new TextureRegionDrawable(createColorTexture(Color.GRAY)));

        Texture characterTexture = new Texture(DEFAULT_CHARACTER_TEXTURE_FILEPATH);
        Image characterImage = new Image(characterTexture);
        characterImage.setOrigin(characterImage.getWidth() / 2, characterImage.getHeight() / 2);
        characterImage.setScale(3f, 3f);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = customFont;
        labelStyle.font.getData().setScale(1.0f);

        Label classNameLabel = new Label(String.valueOf(className), labelStyle);
        classNameLabel.setFontScale(0.5f);

        characterTable.add(characterImage).padBottom(50).center().row();
        characterTable.add(classNameLabel).center();

        characterTable.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new GameScreen(className));
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor fromActor) {
                characterTable.setBackground(new TextureRegionDrawable(createColorTexture(Color.DARK_GRAY)));
                Gdx.audio.newSound(Gdx.files.internal("sounds/UI/hover_sound.ogg")).play();
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, com.badlogic.gdx.scenes.scene2d.Actor toActor) {
                characterTable.setBackground(new TextureRegionDrawable(createColorTexture(Color.GRAY)));
            }
        });

        table.add(characterTable).size(225, 225).pad(20).center();
    }

    private Texture createColorTexture(Color color) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        Texture texture = new Texture(pixmap);
        pixmap.dispose();
        return texture;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
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
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}