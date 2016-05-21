package kitipoom.clickinggame;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView moneybar, enhp;
    private Button attackbtn,autobtn;
    Threadruntime aa;
    Game game;
    boolean o=false;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){
        game = new Game();
        game.newGame();
        moneybar = (TextView)findViewById(R.id.moneytab);
        moneybar.setText("M: "+game.getPlayer().getMoney()+ "");
        enhp = (TextView)findViewById(R.id.enermyhp);
       // enhp.setText(game.getEnermy().getCurrentHp());
        attackbtn = (Button)findViewById(R.id.attk);
        attackbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                game.setEnermydamage();
                moneybar.setText("M: "+game.getPlayer().getMoney() + "");
                enhp.setText(game.getEnermy().getCurrentHp()+"");
            }
        });
        autobtn=(Button)findViewById(R.id.auto);
        autobtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                aa.requestStop();
            }
        });
        aa= new Threadruntime(this);
        aa.start();
    }
    public void updatetime(){
        game.setEnermydamagebybot();
        moneybar.setText("M: "+game.getPlayer().getMoney()+ "");
        enhp.setText(game.getEnermy().getCurrentHp()+"");
    }
}
