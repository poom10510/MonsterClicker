package kitipoom.clickinggame.UpgradeFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import kitipoom.clickinggame.Calculator.Calculator;
import kitipoom.clickinggame.Calculator.Upgradecalculator;
import kitipoom.clickinggame.Game;
import kitipoom.clickinggame.R;

public class HeroUpgrade extends Fragment {

    private  View view;
    private CardView powerCard,healCard,hpCard;
    private TextView powerLv,healLv,hpLv;
    private TextView powerCost,healCost,hpCost;
    private Game game;
    private Calculator upgradeCalculator;

    public HeroUpgrade() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_hero_upgrade, container, false);
        initComponents();
        return view;
    }

    private void initComponents(){

        game = Game.getInstance();

        powerCard = (CardView) view.findViewById(R.id.heropower_card);
        healCard = (CardView) view.findViewById(R.id.heroheal_card);
        hpCard = (CardView) view.findViewById(R.id.herohp_card);

        powerLv = (TextView)view.findViewById(R.id.heropower_lv);
        healLv = (TextView)view.findViewById(R.id.heroheal_lv);
        hpLv = (TextView)view.findViewById(R.id.herohp_lv);

        powerCost = (TextView)view.findViewById(R.id.heropower_cost);
        healCost = (TextView)view.findViewById(R.id.heroheal_cost);
        hpCost = (TextView)view.findViewById(R.id.herohp_cost);

        powerLv.setText("Level " + (game.getPlayer().getAtkpowerlv()));
        healLv.setText("Level "+(game.getPlayer().getHealpowerlv()));
        hpLv.setText("Level "+(game.getPlayer().getMaxHplv()));

        upgradeCalculator = new Upgradecalculator();

        powerCost.setText(upgradeCalculator.getCost(game.getPlayer().getAtkpowerlv()+1)+" $");
        healCost.setText(upgradeCalculator.getCost(game.getPlayer().getHealpowerlv()+1)+" $");
        hpCost.setText(upgradeCalculator.getCost(game.getPlayer().getMaxHplv() + 1) + " $");


        powerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // game.getPlayer().levelUp();
                if (game.getPlayer().getMoney() >= upgradeCalculator.getCost(game.getPlayer().getAtkpowerlv() + 1)) {
                    game.getPlayer().lossMoney(upgradeCalculator.getCost(game.getPlayer().getAtkpowerlv() + 1));
                    game.getLvu().powerUp(game.getPlayer());
                    powerLv.setText("Level " + (game.getPlayer().getAtkpowerlv()));
                    powerCost.setText(upgradeCalculator.getCost(game.getPlayer().getAtkpowerlv() + 1) + " $");
                }
            }
        });

        healCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getPlayer().getMoney() >= upgradeCalculator.getCost(game.getPlayer().getHealpowerlv() + 1)) {
                    game.getPlayer().lossMoney(upgradeCalculator.getCost(game.getPlayer().getHealpowerlv() + 1));
                    game.getLvu().healUp(game.getPlayer());
                    healLv.setText("Level " + (game.getPlayer().getHealpowerlv()));
                    healCost.setText(upgradeCalculator.getCost(game.getPlayer().getHealpowerlv() + 1) + " $");
                }
            }
        });

            hpCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getPlayer().getMoney() >= upgradeCalculator.getCost(game.getPlayer().getMaxHplv() + 1)) {
                    game.getPlayer().lossMoney(-(upgradeCalculator.getCost(game.getPlayer().getMaxHplv() + 1)));
                    game.getLvu().hpUp(game.getPlayer());
                    hpLv.setText("Level " + (game.getPlayer().getMaxHplv()));
                    hpCost.setText(upgradeCalculator.getCost(game.getPlayer().getMaxHplv() + 1) + " $");
                }
            }
        });



    }

}
