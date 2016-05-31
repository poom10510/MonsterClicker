package kitipoom.clickinggame.UpgradeFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kitipoom.clickinggame.Calculator.Calculator;
import kitipoom.clickinggame.Calculator.Upgradecalculator;
import kitipoom.clickinggame.Game;
import kitipoom.clickinggame.R;

public class WarriorUpgrade extends Fragment {

    private  View view;
    private CardView powerCard,speedCard,defendCard;
    private TextView powerLv,speedLv,defendLv;
    private TextView powerCost,speedCost,defendCost;
    private Game game;
    private Calculator upgradeCalculator;

    public WarriorUpgrade() {
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
        view = inflater.inflate(R.layout.fragment_warrior_upgrade, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {

        game = Game.getInstance();

        powerCard = (CardView) view.findViewById(R.id.item1_card);
        speedCard = (CardView) view.findViewById(R.id.item2_card);
        defendCard = (CardView) view.findViewById(R.id.warriordefend_card);

        powerLv = (TextView) view.findViewById(R.id.warriorpower_lv);
        speedLv = (TextView) view.findViewById(R.id.warriorspeed_lv);
        defendLv = (TextView) view.findViewById(R.id.warriordefend_lv);

        powerCost = (TextView) view.findViewById(R.id.warriorpower_cost);
        speedCost = (TextView) view.findViewById(R.id.warriorspeed_cost);
        defendCost = (TextView) view.findViewById(R.id.warriordefend_cost);

        powerLv.setText("Level " + (game.getWarrior().getPowerLv()));
        speedLv.setText("Level " + (game.getWarrior().getSpeedLv()));
        defendLv.setText("Level " + (game.getWarrior().getDefendLv()));
        
        upgradeCalculator = new Upgradecalculator();

        powerCost.setText(upgradeCalculator.getCost(game.getWarrior().getPowerLv()+1)+" $");
        speedCost.setText(upgradeCalculator.getCost(game.getWarrior().getSpeedLv()+1)+" $");
        defendCost.setText(upgradeCalculator.getCost(game.getWarrior().getDefendLv()+1)+" $");

        powerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (game.getPlayer().getMoney() >= upgradeCalculator.getCost(game.getWarrior().getPowerLv() + 1)) {
                    game.getPlayer().lossMoney(upgradeCalculator.getCost(game.getWarrior().getPowerLv() + 1));
                    game.getLvu().powerUp(game.getWarrior());
                    powerLv.setText("Level " + (game.getWarrior().getPowerLv()));
                    powerCost.setText(upgradeCalculator.getCost(game.getWarrior().getPowerLv() + 1) + " $");
                }
            }
        });

        speedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getPlayer().getMoney() >= upgradeCalculator.getCost(game.getWarrior().getSpeedLv() + 1)) {
                    game.getPlayer().lossMoney(upgradeCalculator.getCost(game.getWarrior().getSpeedLv() + 1));
                    game.getLvu().speedUp(game.getWarrior());
                    speedLv.setText("Level " + (game.getWarrior().getSpeedLv()));
                    speedCost.setText(upgradeCalculator.getCost(game.getWarrior().getSpeedLv() + 1) + " $");
                }
            }
        });

            defendCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getPlayer().getMoney() >= upgradeCalculator.getCost(game.getWarrior().getDefendLv() + 1)) {
                    game.getPlayer().lossMoney(upgradeCalculator.getCost(game.getWarrior().getDefendLv() + 1));
                    game.getLvu().defendUp(game.getWarrior());
                    defendLv.setText("Level " + (game.getWarrior().getDefendLv()));
                    defendCost.setText(upgradeCalculator.getCost(game.getWarrior().getDefendLv() + 1) + " $");
                }
            }
        });
    }
}
