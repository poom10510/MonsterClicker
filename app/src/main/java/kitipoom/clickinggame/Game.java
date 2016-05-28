package kitipoom.clickinggame;

import android.app.Activity;

import kitipoom.clickinggame.Ally.Archer;
import kitipoom.clickinggame.Ally.Mage;
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
    private Mage mage;
    private Archer archer;
    private Activity main;

    private int floor;

    private Levelup lvu;
    public Game(Activity main){
        this.main=main;
        lvu=new Levelup();
    }
    public void newGame(){
        floor = 1;
        startGame(floor,floor);
    }
    public void checkEnermydead(){
        if(enermy.getCurrentHp()==0){
            floor++;
            player.setMoney(500);
            enermy = new Enermy(floor);
        }
    }public void  levelPUp(){
        if(player.getMoney()>=500){
            player.setMoney(-500);
            lvu.levelup(player);
        }
    }
    public void startGame(int levelp,int levele)
    {
        player = new Player(levelp);
        enermy= new Enermy(levele);
    }
    public void setEnermydamage() {
        enermy.attacked(player.getAtkpower());
        //player.setMoney(player.getAtkpower());
        checkEnermydead();
    }
    public void playerheal(){
        player.Healyourself(player.getHealpower());
    }
    public void Allyturn(){
        enermy.attacked(1);
        //player.setMoney(1);
        checkEnermydead();
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
