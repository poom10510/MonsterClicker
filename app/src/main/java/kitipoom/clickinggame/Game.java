package kitipoom.clickinggame;

import android.app.Activity;

import java.util.Random;

import kitipoom.clickinggame.Ally.Archer;
import kitipoom.clickinggame.Ally.Caster;
import kitipoom.clickinggame.Ally.Warrior;
import kitipoom.clickinggame.Keyplay.Enermy;
import kitipoom.clickinggame.Keyplay.Player;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Game {

    private Player player;
    private Enermy enermy;

    private Warrior warrior;
    private Caster caster;
    private Archer archer;
    private Random random = new Random();
    private static Game instance;

private boolean stun = false;
    private int floor, count, index;

    private Levelup lvu;

    public Game() {
        lvu = new Levelup();
    }

    public static Game getInstance(){
        if(instance==null)return new Game();
        return instance;
    }

    public void newGame() {
        floor = 1;
        count = 2;
        startGame(floor, floor);
    }

    public void checkEnermydead() {
        if (EnisDead()) {
            player.setMoney(enermy.getLevel() * 5);
            enermy.setLevel(floor);
            if (count == 6) {
                floor++;
                count = 0;
            }
            count++;
        }
    }
    public void setEnermyDecrease(){
        floor--;
        enermy.setLevel(floor);
        enermy.setCurrentHp(enermy.getCurrentHp());

        count = 1 ;
    }

    public boolean EnisDead() {
        return enermy.getCurrentHp() <= 0;

    }
    public boolean playerIsDead() {
        return player.getCurrentHp() <= 0;

    }

    public int getCount() {
        return count;
    }

    public void startGame(int levelp, int levele) {
        player = new Player(levelp);
        enermy = new Enermy(levele);
        warrior = new Warrior();
        caster = new Caster();
        archer = new Archer();
    }

    public void setEnermydamage() {
        enermy.attacked(player.getAtkpower());
        //player.setMoney(player.getAtkpower());
        //checkEnermydead();
    }

    public void playerheal() {
        player.Healyourself(player.getHealpower());
    }

    public void setStun(boolean stun) {
        this.stun = stun;
    }

    public boolean getStun() {
        return stun;
    }

    public void archerAttack() {
        archer.Action(player, enermy);
        stun = checkStun();

        //player.setMoney(1);
        //checkEnermydead();
    }

    public void mageAttack() {
        caster.Action(player, enermy);
        //player.setMoney(1);
        //checkEnermydead();
    }

    public void warriorAttack() {
        warrior.Action(player, enermy);
        //player.setMoney(1);
        //checkEnermydead();
    }

    public void Enermyturn() {
        player.attacked(enermy.getAtkpower() - (int) (enermy.getAtkpower() * (warrior.getDefend() / 100.0)));
    }
    public boolean checkStun(){
        index = random.nextInt(100)+1;
        return  index <= archer.getStun() ;
    }

    public void setPlayerdamage() {
        player.attacked(enermy.getAtkpower());
    }

    public Enermy getEnermy() {
        return enermy;
    }

    public Player getPlayer() {
        return player;
    }
}
