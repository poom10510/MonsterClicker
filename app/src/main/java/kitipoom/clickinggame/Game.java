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
}
