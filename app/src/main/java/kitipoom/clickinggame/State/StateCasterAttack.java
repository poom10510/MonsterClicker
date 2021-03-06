package kitipoom.clickinggame.State;

import kitipoom.clickinggame.Allies.Ally;
import kitipoom.clickinggame.Keyplay.Enemy;
import kitipoom.clickinggame.Keyplay.Player;

public class StateCasterAttack extends State {


    @Override
    public void Action(Player player, Enemy enemy) {
        enemy.attacked(caster.getPower());
        if (player.getCurrentHp() < player.getMaxHp() / 2) {
            caster.setState(new StateCasterHeal(caster));
        }
    }

    public StateCasterAttack(Ally caster) {
        super(caster);
        st = 0;
    }
}
