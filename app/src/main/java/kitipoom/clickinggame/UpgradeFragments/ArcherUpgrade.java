package kitipoom.clickinggame.UpgradeFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kitipoom.clickinggame.Calculators.Calculator;
import kitipoom.clickinggame.Calculators.Upgradecalculator;
import kitipoom.clickinggame.Models.Game;
import kitipoom.clickinggame.R;


public class ArcherUpgrade extends Fragment {

    private  View view;
    private CardView powerCard,speedCard,stunCard;
    private TextView powerLv,speedLv,stunLv;
    private TextView powerCost,speedCost,stunCost;
    private Calculator upgradecalculator;
    private Game game;

    public ArcherUpgrade() {
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
        view = inflater.inflate(R.layout.fragment_archer_upgrade, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {

        game = Game.getInstance();

        powerCard = (CardView) view.findViewById(R.id.archerpower_card);
        speedCard = (CardView) view.findViewById(R.id.archerspeed_card);
        stunCard = (CardView) view.findViewById(R.id.archerstun_card);

        powerLv = (TextView) view.findViewById(R.id.archerpower_lv);
        speedLv = (TextView) view.findViewById(R.id.archerspeed_lv);
        stunLv = (TextView) view.findViewById(R.id.archerstun_lv);

        powerCost = (TextView) view.findViewById(R.id.archerpower_cost);
        speedCost = (TextView) view.findViewById(R.id.archerspeed_cost);
        stunCost = (TextView) view.findViewById(R.id.archerstun_cost);

        powerLv.setText("Level " + (game.getArcher().getPowerLv()));
        speedLv.setText("Level " + (game.getArcher().getSpeedLv()));
        stunLv.setText("Level " + (game.getArcher().getStunLv()));

        upgradecalculator = new Upgradecalculator();

        powerCost.setText(upgradecalculator.getCost(game.getArcher().getPowerLv()+1)+" $");
        speedCost.setText(upgradecalculator.getCost(game.getArcher().getSpeedLv()+1)+" $");
        stunCost.setText(upgradecalculator.getCost(game.getArcher().getStunLv()+1)+" $");

        powerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getPlayerMoney() >= upgradecalculator.getCost(game.getArcher().getPowerLv() + 1)) {
                    game.playerLossMoney(upgradecalculator.getCost(game.getArcher().getPowerLv() + 1));
                    game.getLvu().powerUp(game.getArcher());
                    powerLv.setText("Level " + (game.getArcher().getPowerLv()));
                    powerCost.setText(upgradecalculator.getCost(game.getArcher().getPowerLv() + 1) + " $");
                }
            }
        });

        speedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getPlayerMoney() >= upgradecalculator.getCost(game.getArcher().getSpeedLv() + 1)) {
                    game.playerLossMoney(upgradecalculator.getCost(game.getArcher().getSpeedLv() + 1));
                    game.getLvu().speedUp(game.getArcher());
                    speedLv.setText("Level " + (game.getArcher().getSpeedLv()));
                    speedCost.setText(upgradecalculator.getCost(game.getArcher().getSpeedLv() + 1) + " $");
                }
            }
        });

        stunCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                if (game.getPlayerMoney() >= upgradecalculator.getCost(game.getArcher().getStunLv() + 1)) {
                    game.playerLossMoney(upgradecalculator.getCost(game.getArcher().getStunLv() + 1));
                    game.getLvu().stunUp(game.getArcher());
                    stunLv.setText("Level " + (game.getArcher().getStunLv()));
                    stunCost.setText(upgradecalculator.getCost(game.getArcher().getStunLv() + 1) + " $");
                }
            }
        });
    }
}
