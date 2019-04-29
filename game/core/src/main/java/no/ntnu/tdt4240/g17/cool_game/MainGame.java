package no.ntnu.tdt4240.g17.cool_game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import no.ntnu.tdt4240.g17.cool_game.screens.navigation.Navigator;

/**
 * Main game class.
 */
public class MainGame extends ApplicationAdapter {

    private Navigator navigator;
    @Override
    public final void create() {
        navigator = new Navigator();
    }

    @Override
    public final void render() {
        navigator.getScreen().render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public final void dispose() {
        if (navigator != null) {
            navigator.dispose();
        }
    }
}
