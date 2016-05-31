package kitipoom.clickinggame.Keyplay;

import kitipoom.clickinggame.Calculator.Playercalculator;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Player extends Keyplay {
    public Player(int level) {
        super();
        this.cal = new Playercalculator();
        //setLevel(level);
        setMaxHplv(1);
        setAtkpowerlv(1);
        setHealpowerlv(1);

        //calculate();
        currentHp = getMaxHp();
    }

    @Override
    public void Action() {

    }
}
