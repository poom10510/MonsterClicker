package kitipoom.clickinggame.UpgradeFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kitipoom.clickinggame.Game;
import kitipoom.clickinggame.R;


public class ArcherUpgrade extends Fragment {

    private  View view;
    private CardView powerCard,speedCard,stunCard;
    private TextView powerLv,speedLv,stunLv;
    private TextView powerCost,speedCost,stunCost;
    private int level =1;
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

    public void initComponents() {

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

        powerCost.setText(game.getMoney().getoutcome(game.getArcher().getPowerLv()+1)+" $");
        speedCost.setText(game.getMoney().getoutcome(game.getArcher().getSpeedLv()+1)+" $");
        stunCost.setText(game.getMoney().getoutcome(game.getArcher().getStunLv()+1)+" $");

        powerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getMoney().getCash() >= game.getMoney().getoutcome(game.getArcher().getPowerLv() + 1)) {
                    game.getMoney().setCash(-(game.getMoney().getoutcome(game.getArcher().getPowerLv() + 1)));
                    game.getLvu().powerUp(game.getArcher());
                    powerLv.setText("Level " + (game.getArcher().getPowerLv()));
                    powerCost.setText(game.getMoney().getoutcome(game.getArcher().getPowerLv() + 1) + " $");
                }
            }
        });

        speedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getMoney().getCash() >= game.getMoney().getoutcome(game.getArcher().getSpeedLv() + 1)) {
                    game.getMoney().setCash(-(game.getMoney().getoutcome(game.getArcher().getSpeedLv() + 1)));
                    game.getLvu().speedUp(game.getArcher());
                    speedLv.setText("Level " + (game.getArcher().getSpeedLv()));
                    speedCost.setText(game.getMoney().getoutcome(game.getArcher().getSpeedLv() + 1) + " $");
                }
            }
        });

        stunCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                if (game.getMoney().getCash() >= game.getMoney().getoutcome(game.getArcher().getStunLv() + 1)) {
                    game.getMoney().setCash(-(game.getMoney().getoutcome(game.getArcher().getStunLv() + 1)));
                    game.getLvu().stunUp(game.getArcher());
                    stunLv.setText("Level " + (game.getArcher().getStunLv()));
                    stunCost.setText(game.getMoney().getoutcome(game.getArcher().getStunLv() + 1) + " $");
                }
            }
        });
    }
}
