package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 11/5/2559.
 */
public interface Ally {
    public void Action(Player player,Enermy enermy);
    public void Levelup();
    public void setLevel(int level);
    public void calculate();
}
