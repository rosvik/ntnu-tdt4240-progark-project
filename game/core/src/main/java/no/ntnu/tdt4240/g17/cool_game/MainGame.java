package no.ntnu.tdt4240.g17.cool_game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import no.ntnu.tdt4240.g17.cool_game.character.GameCharacter;
import no.ntnu.tdt4240.g17.cool_game.game_arena.Arena;
import no.ntnu.tdt4240.g17.cool_game.projectile.Projectile;

/**
 * Main game class.
 */
public class MainGame extends ApplicationAdapter {
    float stateTime;

    /** Batch to render. */
    SpriteBatch batch;

    /** Arena. */
    Arena arena;

    /** GameCharacter */
    GameCharacter character1;

    /* Textureatlas */
    TextureAtlas dungeonTilset;
    TextureAtlas projectiles;

    // Projectile
    Projectile projectile;

    @Override
    public final void create() {
        batch = new SpriteBatch();
        // arena = new Arena("map2.tmx", 16f, 32f, 20f, batch);
        //arena.setBackground("background.png");
        dungeonTilset = new TextureAtlas("./Assets/TextureAtlas/Characters/DungeonTileset.atlas");
        projectiles = new TextureAtlas("Assets/TextureAtlas/Projectiles/Projectiles.atlas");
        character1 = new GameCharacter("knight_f", 100, 100, dungeonTilset);
        stateTime = 0;
        projectile = new Projectile("arrow", 0, 0, 135, projectiles);
    }

    @Override
    public final void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /** API. */

        /* character */
        //character1.render(api1.getxValue(), api1.getyValue());
        //projectile.render(Gdx.input.getX(), -Gdx.input.getY() + 570);
        //projectile.render(arrowApi.getxValue(), arrowApi.getyValue());

        batch.begin();

        // Render the arena
        //arena.render();

        // Render charcter
        //projectile.draw(batch);
        //character1.draw(batch, stateTime);

        //batch.draw(img, 0, 0);
        batch.end();
        stateTime += Gdx.graphics.getDeltaTime();
    }

    @Override
    public final void dispose() {
        batch.dispose();
    }
}
