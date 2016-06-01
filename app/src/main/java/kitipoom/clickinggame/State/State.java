package kitipoom.clickinggame.State;

import kitipoom.clickinggame.Allies.Ally;
import kitipoom.clickinggame.Keyplay.Enemy;
import kitipoom.clickinggame.Keyplay.Player;

public abstract class State {
    public abstract void Action(Player player, Enemy enemy);

    protected Ally caster;
    protected int st;

    public State(Ally caster) {
        this.caster = caster;
    }

    public int getSt() {
        return st;
    }
}
