package kitipoom.clickinggame;

/**
 * Created by kitipoom on 11/5/2559.
 */
public class Game {
    private Player player;
    private Enermy enermy;

    public void newGame(){
        startGame(1,1);
    }
    public void startGame(int levelp,int levele)
    {
        player = new Player(levelp);
        enermy= new Enermy(levele);
    }
    public void setEnermydamage(){
        enermy.attacked(player.getAtkpower());
        player.setMoney(player.getAtkpower());
    }
    public void setEnermydamagebybot(){
        enermy.attacked(1);
        player.setMoney(1);
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
