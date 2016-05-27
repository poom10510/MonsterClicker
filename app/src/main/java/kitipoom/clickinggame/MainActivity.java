package kitipoom.clickinggame;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView moneybar, enhp,plhp;
    private RelativeLayout healLayout,attackLayout;
    protected ImageView enpic;
    private Threadruntime aa;
    Game game;
    boolean o=false;
    int i = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        init();
    }
    public void init(){
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        game = new Game(this);
        game.newGame();
        enpic =(ImageView)findViewById(R.id.Enermyimage);
        enpic.setImageResource(R.drawable.enermypic);
        moneybar = (TextView)findViewById(R.id.moneytab);
        moneybar.setText("M: " + game.getPlayer().getMoney() + "");
        enhp = (TextView)findViewById(R.id.enermyhp);
        enhp.setText("Lv. "+game.getEnermy().getLevel()+" HP: " + game.getEnermy().getCurrentHp() + "");
        plhp = (TextView)findViewById(R.id.playerhp);
        plhp.setText("Lv. "+game.getPlayer().getLevel()+" HP: "+game.getPlayer().getCurrentHp()+"");

        healLayout = (RelativeLayout) findViewById(R.id.HealLayout);
        healLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.playerheal();
                plhp.setText("Lv. " + game.getPlayer().getLevel() + " HP: " + game.getPlayer().getCurrentHp() + "");
            }
        });
        attackLayout = (RelativeLayout) findViewById(R.id.AttackLayout);
        attackLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game.setEnermydamage();
                moneybar.setText("M: " + game.getPlayer().getMoney() + "");
                enhp.setText("Lv. " + game.getEnermy().getLevel() + " HP: " + game.getEnermy().getCurrentHp() + "");
            }
        });

        aa= new Threadruntime(this);
        aa.start();

    }
    public void updatetime(){
        game.Allyturn();
        moneybar.setText("M: " + game.getPlayer().getMoney() + "");
        enhp.setText("Lv. "+game.getEnermy().getLevel()+" HP: " + game.getEnermy().getCurrentHp() + "");
        game.Enermyturn();
        plhp.setText("Lv. "+game.getPlayer().getLevel()+" HP: "+game.getPlayer().getCurrentHp()+"");
    }
}
