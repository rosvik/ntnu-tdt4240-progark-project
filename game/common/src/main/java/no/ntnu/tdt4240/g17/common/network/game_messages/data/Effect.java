package no.ntnu.tdt4240.g17.common.network.game_messages.data;

/**
 * Created by Morten 'bujordet' Bujordet on 3/25/2019.
 *
 * @author Morten 'bujordet' Bujordet
 */
@SuppressWarnings("VisibilityModifier")
public class Effect {
    /** Id of the effect. */
    public String effectId;
    /** Type of effect. */
    public EffectType effectType;
    /** Position of the effect. */
    public Position effectPosition;
}
