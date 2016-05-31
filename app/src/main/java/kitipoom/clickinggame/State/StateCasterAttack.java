package kitipoom.clickinggame.State;

import kitipoom.clickinggame.Ally.Ally;
import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 29/5/2559.
 */
public class StateCasterAttack extends State {


    @Override
    public void Action(Player player, Enermy enermy) {
        enermy.attacked(caster.getPower());
        if (player.getCurrentHp() < player.getMaxHp() / 2) {
            caster.setState(new StateCasterHeal(caster));
        }
    }

    public StateCasterAttack(Ally caster) {
        super(caster);
        st = 0;
    }


}
