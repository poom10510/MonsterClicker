package kitipoom.clickinggame.Keyplay;

import kitipoom.clickinggame.Calculator.Enermy1calculator;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Enermy extends Keyplay{


    public Enermy(int level){
        cal = new Enermy1calculator();
        setLevel(level);

        //calculate();
        this.currentHp=getMaxHp();
    }


    @Override
    public void Action() {

    }
}
