package kitipoom.clickinggame.Ally;

import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 29/5/2559.
 */
public class StateHeal extends State{
    @Override
    public void Action(Player player, Enermy enermy) {
        player.Healyourself(caster.heal);
        if(player.getCurrentHp()>=player.getMaxHp()) {
            caster.setState(new StateAttack(caster));
        }
    }

    public StateHeal(Caster caster) {
        super(caster);
    }


}
