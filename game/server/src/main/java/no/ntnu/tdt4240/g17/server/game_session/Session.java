package no.ntnu.tdt4240.g17.server.game_session;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import no.ntnu.tdt4240.g17.common.network.game_messages.data.Arena;
import no.ntnu.tdt4240.g17.common.network.game_messages.data.GameMode;
import no.ntnu.tdt4240.g17.server.game_engine.GameEngine;
import no.ntnu.tdt4240.g17.server.game_engine.GameEngineFactory;

/**
 * A session of matchmade players in a game.
 *
 * @author Kristian 'krissrex' Rekstad
 */
public class Session {

    @Getter
    private List<Player> players = new ArrayList<>();

    @Getter
    private GameEngine gameEngine;

    @Getter
    private Arena arena;

    @Getter
    private GameMode gameMode;

    /** test method, work in progress.
     * @param players the players in this session.
     * @return the session
     */
    public static Session create(final Player... players) {
        Arena arena = Arena.ARENA_2;

        final Session session = new Session();
        final GameEngineFactory gameEngineFactory = new GameEngineFactory();
        final GameEngine gameEngine = gameEngineFactory.create(arena, session);

        session.gameEngine = gameEngine;
        session.arena = arena;
        session.gameMode = GameMode.HEADHUNTER; // Depends on matchmaking?

        final Thread engineThread = new Thread(gameEngine, "GameEngine");
        engineThread.start();

        return session;
    }

    /** Create a new session.
     * @see #create(Player...) */
    public Session() {
    }
}
