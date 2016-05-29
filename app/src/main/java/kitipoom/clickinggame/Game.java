package kitipoom.clickinggame;

import android.app.Activity;

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
    private MainActivity main;

    private int floor,count;

    private Levelup lvu;
    public Game(MainActivity main){
        this.main=main;
        lvu=new Levelup();
    }
    public void newGame(){
        floor = 1;
        count=1;
        startGame(floor,floor);
    }
    public void checkEnermydead(){
        if(EnisDead()){
            player.setMoney(enermy.getLevel() * 5);
            enermy.setLevel(floor);
            if(count==6) {
                floor++;
                count=0;
            }
            count++;
        }
    }
    public boolean EnisDead(){
        return enermy.getCurrentHp()<=0;

    }

    public int getCount() {
        return count;
    }

    public void  levelPUp(){
        if(player.getMoney()>=500){
            player.setMoney(-500);
            lvu.levelup(player);
        }
    }
    public void startGame(int levelp,int levele)
    {
        player = new Player(levelp);
        enermy= new Enermy(levele);
        warrior = new Warrior();
        caster = new Caster();
        archer = new Archer();
    }
    public void setEnermydamage() {
        enermy.attacked(player.getAtkpower());
        //player.setMoney(player.getAtkpower());
        //checkEnermydead();
    }
    public void playerheal(){
        player.Healyourself(player.getHealpower());
    }
    public void Allyturn(){
        enermy.attacked(1);
        //player.setMoney(1);
        //checkEnermydead();
    }
    public void archerAttack(){
        archer.Action(player,enermy);
        //player.setMoney(1);
        //checkEnermydead();
    }
    public void mageAttack(){
        caster.Action(player,enermy);
        //player.setMoney(1);
        //checkEnermydead();
    }
    public void warriorAttack(){
        warrior.Action(player,enermy);
        //player.setMoney(1);
        //checkEnermydead();
    }


    public void Enermyturn(){
        player.attacked(enermy.getAtkpower());
    }
    public void setPlayerdamage(){
        player.attacked(enermy.getAtkpower());
    }

    public Enermy getEnermy() {
        return enermy;
    }

    public Player getPlayer() {
        return player;
    }
}
