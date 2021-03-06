package no.ntnu.tdt4240.g17.common.network.game_messages.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by Morten 'bujordet' Bujordet on 3/25/2019.
 *
 * @author Morten 'bujordet' Bujordet
 */
@SuppressWarnings("VisibilityModifier")
@AllArgsConstructor @NoArgsConstructor @ToString
public class Position {
    /** Position of the player in x-direction. */
    public float x;
    /** Position of the player in y-direction. */
    public float y;
}
