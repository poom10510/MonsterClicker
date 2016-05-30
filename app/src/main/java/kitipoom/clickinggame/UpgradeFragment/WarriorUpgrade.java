package kitipoom.clickinggame.UpgradeFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import kitipoom.clickinggame.Game;
import kitipoom.clickinggame.R;

public class WarriorUpgrade extends Fragment {

    private  View view;
    private CardView powerCard,speedCard,defendCard;
    private TextView powerLv,speedLv,defendLv;
    private TextView powerCost,speedCost,defendCost;
    private int level =1;
    private Game game;

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

    public void initComponents() {

        game = Game.getInstance();

        powerCard = (CardView) view.findViewById(R.id.warriorpower_card);
        speedCard = (CardView) view.findViewById(R.id.warriorspeed_card);
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

        powerCost.setText(game.getMoney().getoutcome(game.getWarrior().getPowerLv()+1)+" $");
        speedCost.setText(game.getMoney().getoutcome(game.getWarrior().getSpeedLv()+1)+" $");
        defendCost.setText(game.getMoney().getoutcome(game.getWarrior().getDefendLv()+1)+" $");

        powerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (game.getMoney().getCash() >= game.getMoney().getoutcome(game.getWarrior().getPowerLv() + 1)) {
                    game.getMoney().setCash(-(game.getMoney().getoutcome(game.getWarrior().getPowerLv() + 1)));
                    game.getLvu().powerUp(game.getWarrior());
                    powerLv.setText("Level " + (game.getWarrior().getPowerLv()));
                    powerCost.setText(game.getMoney().getoutcome(game.getWarrior().getPowerLv() + 1) + " $");
                }
            }
        });

        speedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getMoney().getCash() >= game.getMoney().getoutcome(game.getWarrior().getSpeedLv() + 1)) {
                    game.getMoney().setCash(-(game.getMoney().getoutcome(game.getWarrior().getSpeedLv() + 1)));
                    game.getLvu().speedUp(game.getWarrior());
                    speedLv.setText("Level " + (game.getWarrior().getSpeedLv()));
                    speedCost.setText(game.getMoney().getoutcome(game.getWarrior().getSpeedLv() + 1) + " $");
                }
            }
        });

            defendCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getMoney().getCash() >= game.getMoney().getoutcome(game.getWarrior().getDefendLv() + 1)) {
                    game.getMoney().setCash(-(game.getMoney().getoutcome(game.getWarrior().getDefendLv() + 1)));
                    game.getLvu().defendUp(game.getWarrior());
                    defendLv.setText("Level " + (game.getWarrior().getDefendLv()));
                    defendCost.setText(game.getMoney().getoutcome(game.getWarrior().getDefendLv() + 1) + " $");
                }
            }
        });
    }
}
