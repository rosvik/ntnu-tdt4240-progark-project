package no.ntnu.tdt4240.g17.cool_game.screens.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import no.ntnu.tdt4240.g17.cool_game.character.GameCharacter;
import no.ntnu.tdt4240.g17.cool_game.character.InputToMovementOutput;
import no.ntnu.tdt4240.g17.cool_game.game_arena.Arena;
import no.ntnu.tdt4240.g17.cool_game.screens.game.controller.InputProcessor;
import no.ntnu.tdt4240.g17.cool_game.screens.game.controller.MovementFormat;
import no.ntnu.tdt4240.g17.cool_game.screens.game.controller.TouchInput;
import no.ntnu.tdt4240.g17.cool_game.screens.game.controller.UserInputButtons;

/**
 * Main game class.
 */
public class UserInputTestApplication extends ApplicationAdapter {
    // FIXME: 4/30/2019 Should this file be deleted? -Kristian

    /** Batch to render. */
    SpriteBatch batch;

    /** Arena. */
    Arena arena;

    TextureAtlas atlas;

    GameCharacter character;

    UserInputButtons userInputButtons;

    MovementFormat movementFormat;

    InputToMovementOutput inputToMovementOutput;

    Vector2 oldPosition;

    int screenHeigth, screenWidth;

    ShapeRenderer shapeRenderer;

    BitmapFont font;

    InputProcessor inputProcessor;

    TouchInput firstFinger, secondFinger, thirdFinger;

    @Override
    public final void create() {
        font = new BitmapFont();
        screenHeigth = Gdx.graphics.getHeight();
        screenWidth = Gdx.graphics.getWidth();
        batch = new SpriteBatch();
        atlas = new TextureAtlas("./Assets/TextureAtlas/Characters/DungeonTileset.atlas");
        character = new GameCharacter("big_zombie", 100,  100, atlas);
        userInputButtons = new UserInputButtons(screenHeigth, screenWidth);
        inputToMovementOutput = new InputToMovementOutput();
        oldPosition = new Vector2(screenWidth / 2, screenHeigth / 2);
        shapeRenderer = new ShapeRenderer();
        inputProcessor = new InputProcessor(screenHeigth, screenWidth);
    }

    @Override
    public final void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.arc(userInputButtons.getJoystick().x, userInputButtons.getJoystick().y, userInputButtons.getJoystick().radius, 0, 360);
        shapeRenderer.line(0, screenHeigth / 2, screenWidth, screenHeigth / 2);
        shapeRenderer.rect(userInputButtons.getJump().x, userInputButtons.getJump().y, userInputButtons.getJump().width, userInputButtons.getJump().height);
        shapeRenderer.rect(userInputButtons.getShoot().x, userInputButtons.getShoot().y, userInputButtons.getShoot().width, userInputButtons.getShoot().height);
        shapeRenderer.rect(userInputButtons.getPlace().x, userInputButtons.getPlace().y, userInputButtons.getPlace().width, userInputButtons.getPlace().height);
        shapeRenderer.end();
        batch.begin();
        firstFinger = new TouchInput(Gdx.input.isTouched(0), Gdx.input.getX(0), Gdx.input.getY(0));
        secondFinger = new TouchInput(Gdx.input.isTouched(1), Gdx.input.getX(1), Gdx.input.getY(1));
        thirdFinger = new TouchInput(Gdx.input.isTouched(2), Gdx.input.getX(2), Gdx.input.getY(2));
        movementFormat = inputProcessor.processInput(firstFinger, secondFinger, thirdFinger);
        font.draw(batch, "button: " + movementFormat.getButtonsPressed() + ", value: " + movementFormat.getJoystickInput(), 300, 500);
        character.render(200, 500);
        character.update(Gdx.graphics.getDeltaTime());
        character.draw(batch);
        batch.end();
    }

    @Override
    public final void dispose() {
        batch.dispose();
    }
}
