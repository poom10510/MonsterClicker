package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 29/5/2559.
 */
public class StateAttack extends State {


    @Override
    public void Action(Player player, Enermy enermy) {
        enermy.attacked(caster.power);
        if(player.getCurrentHp()<player.getMaxHp()/2) {
            caster.setState(new StateHeal(caster));
        }
    }

    public StateAttack(Caster caster) {
        super(caster);
    }


}
