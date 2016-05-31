package kitipoom.clickinggame.State;

import kitipoom.clickinggame.Ally.Ally;
import kitipoom.clickinggame.Keyplay.Enemy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 29/5/2559.
 */
public class StateCasterHeal extends State {
    @Override
    public void Action(Player player, Enemy enemy) {
        player.Healyourself(caster.getHeal());
        if (player.getCurrentHp() >= player.getMaxHp()) {
            caster.setState(new StateCasterAttack(caster));
        }
    }

    public StateCasterHeal(Ally caster) {
        super(caster);
        st = 1;
    }


}
