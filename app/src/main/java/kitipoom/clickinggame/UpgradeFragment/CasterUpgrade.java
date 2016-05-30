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


public class CasterUpgrade extends Fragment {

    private  View view;
    private CardView powerCard,speedCard,healCard;
    private TextView powerLv,speedLv,healLv;
    private TextView powerCost,speedCost,healCost;
    private int level =1;
    private Game game;

    public CasterUpgrade() {
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
        view = inflater.inflate(R.layout.fragment_caster_upgrade, container, false);
        initComponents();
        return view;
    }

    public void initComponents() {

        game = Game.getInstance();

        powerCard = (CardView) view.findViewById(R.id.casterpower_card);
        speedCard = (CardView) view.findViewById(R.id.casterspeed_card);
        healCard = (CardView) view.findViewById(R.id.casterheal_card);

        powerLv = (TextView) view.findViewById(R.id.casterpower_lv);
        speedLv = (TextView) view.findViewById(R.id.casterspeed_lv);
        healLv = (TextView) view.findViewById(R.id.casterheal_lv);

        powerCost = (TextView) view.findViewById(R.id.casterpower_cost);
        speedCost = (TextView) view.findViewById(R.id.casterspeed_cost);
        healCost = (TextView) view.findViewById(R.id.casterheal_cost);

        powerLv.setText("Level " + (game.getCaster().getPowerLv()));
        speedLv.setText("Level " + (game.getCaster().getSpeedLv()));
        healLv.setText("Level " + (game.getCaster().getHealLv()));

        powerCost.setText(game.getMoney().getoutcome(game.getCaster().getPowerLv()+1)+" $");
        speedCost.setText(game.getMoney().getoutcome(game.getCaster().getSpeedLv()+1)+" $");
        healCost.setText(game.getMoney().getoutcome(game.getCaster().getHealLv()+1)+" $");

        powerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getMoney().getCash() >= game.getMoney().getoutcome(game.getCaster().getPowerLv() + 1))
                {
                    game.getMoney().setCash(-(game.getMoney().getoutcome(game.getCaster().getPowerLv() + 1)));
                    game.getLvu().powerUp(game.getCaster());
                    powerLv.setText("Level " + (game.getCaster().getPowerLv()));
                    powerCost.setText(game.getMoney().getoutcome(game.getCaster().getPowerLv() + 1) + " $");
                }
            }
        });

        speedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getMoney().getCash() >= game.getMoney().getoutcome(game.getCaster().getSpeedLv() + 1))
                {
                    game.getMoney().setCash(-(game.getMoney().getoutcome(game.getCaster().getSpeedLv() + 1)));
                    game.getLvu().speedUp(game.getCaster());
                    speedLv.setText("Level " + (game.getCaster().getSpeedLv()));
                    speedCost.setText(game.getMoney().getoutcome(game.getCaster().getSpeedLv() + 1) + " $");
                }
            }
        });

        healCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getMoney().getCash() >= game.getMoney().getoutcome(game.getCaster().getHealLv() + 1)) {
                    game.getMoney().setCash(-(game.getMoney().getoutcome(game.getCaster().getHealLv() + 1)));
                    game.getLvu().healUp(game.getCaster());
                    healLv.setText("Level " + (game.getCaster().getHealLv()));
                    healCost.setText(game.getMoney().getoutcome(game.getCaster().getHealLv() + 1) + " $");
                }
            }
        });
    }
}
