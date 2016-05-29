package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 29/5/2559.
 */
public abstract class State  {
    public abstract void Action(Player player,Enermy enermy);
    protected Caster caster;

            public State(Caster caster){
                this.caster=caster;
            }
}
