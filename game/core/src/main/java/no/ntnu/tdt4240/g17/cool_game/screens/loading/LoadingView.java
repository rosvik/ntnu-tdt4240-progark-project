package no.ntnu.tdt4240.g17.cool_game.screens.loading;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.esotericsoftware.kryonet.Client;

import no.ntnu.tdt4240.g17.cool_game.network.GameClient;


/**
 * A view for loading / matchmaking.
 */
public final class LoadingView implements Screen {

    private final SpriteBatch batch;
    private final LoadingModel model;
    private final LoadingController controller;
    private Client networkClient;
    private BitmapFont bitmapFont;

    /**
     * @param batch spritebatch
     * @param controller controller for this view
     * @param model model for this view to poll from
     */
    public LoadingView(final SpriteBatch batch,
                       final LoadingModel model, final LoadingController controller) {
        this.batch = batch;
        this.model = model;
        this.controller = controller;
    }

    @Override
    public void show() {
        networkClient = GameClient.getNetworkClientInstance();
        bitmapFont = new BitmapFont(Gdx.files.internal("assets/skin/font-export.fnt"));
    }

    /**
     * @param deltaTime
     */
    @Override
    public void render(final float deltaTime) {
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
        Gdx.gl.glClear(GL20.GL_DEPTH_BUFFER_BIT);
        batch.begin();
        if (!model.isConnected()) {
            bitmapFont.draw(batch, "Not connected...", 0, 0);
        } else {
            if (model.isMatchmaking()) {
                bitmapFont.draw(batch, "Matchmaking...", 0, 0);
            } else if (model.hasBeenMatchmade()) {
                bitmapFont.draw(batch, "Game starting!", 0, 0);
            }
        }
        batch.end();

        if (model.hasBeenMatchmade()) {
            controller.startGame();
        }
    }

    @Override
    public void resize(final int width, final int height) {

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
        batch.dispose();
        model.dispose();
    }
}
