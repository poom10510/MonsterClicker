package kitipoom.clickinggame;

/**
 * Created by kitipoom on 19/5/2559.
 */
public class Enermy1calculator extends Calculator {
    @Override
    public int getHp(int level) {
        return level*1000;
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
