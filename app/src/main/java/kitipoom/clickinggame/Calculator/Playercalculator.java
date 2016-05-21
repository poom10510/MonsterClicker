package kitipoom.clickinggame.Calculator;

/**
 * Created by kitipoom on 19/5/2559.
 */
public class Playercalculator extends Calculator {
    @Override
    public int getHp(int level) {
        return level*100;
    }

    @Override
    public int getAtk(int level) {
        return level*10;
    }

    @Override
    public int getHeal(int level) {
        return level*10;
    }
}
