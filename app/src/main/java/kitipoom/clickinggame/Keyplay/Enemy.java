package kitipoom.clickinggame.Keyplay;

import kitipoom.clickinggame.Calculator.Enemycalculator;
import kitipoom.clickinggame.Memento.Memento;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Enemy extends Keyplay {


    public Enemy(int level) {
        super();
        cal = new Enemycalculator();
        setLevel(level);

        //calculate();
        this.currentHp = getMaxHp();
    }


    @Override
    public void Action() {

    }

    public Memento saveState(){
        return  new EnemyMemento(level);
    }

    public void loadState(Memento memento){
        if(memento == null)return;
        if(memento.getClass() != EnemyMemento.class)return;
        EnemyMemento enemyMemento = (EnemyMemento)memento;
        this.level = enemyMemento.level;
    }

    public static class EnemyMemento extends Memento{
        private int level;

        private EnemyMemento(int level){
            super("Enemy");
            this.level = level;
        }
    }
}
