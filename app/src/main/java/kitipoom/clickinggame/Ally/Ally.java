package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 11/5/2559.
 */
public abstract class Ally {
    protected int level;
    protected int healpower;
    public abstract void Action(Player player,Enermy enermy);
    public void levelup(){
        level++;
        calculate();
    }
    public void setLevel(int level){
        this.level=level;
    }
    public abstract void calculate();
}
