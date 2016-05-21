package kitipoom.clickinggame;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Enermy extends Keyplay{


    public Enermy(int level){
        this.level=level;
        cal = new Enermy1calculator();
        calculate();
        this.currentHp=getMaxHp();
    }

}
