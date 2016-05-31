package kitipoom.clickinggame.State;

import kitipoom.clickinggame.Ally.Ally;
import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 29/5/2559.
 */
public abstract class State {
    public abstract void Action(Player player, Enermy enermy);

    protected Ally caster;
    protected int st;

    public State(Ally caster) {
        this.caster = caster;
    }

    public int getSt() {
        return st;
    }
}
