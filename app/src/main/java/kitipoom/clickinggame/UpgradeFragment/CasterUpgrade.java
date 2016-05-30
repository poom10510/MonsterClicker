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

import kitipoom.clickinggame.R;


public class CasterUpgrade extends Fragment {

    private  View view;
    private CardView powerCard,speedCard,healCard;
    private TextView powerLv,speedLv,healLv;
    private TextView powerCost,speedCost,healCost;
    private int level =1;

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
        powerCard = (CardView) view.findViewById(R.id.casterpower_card);
        speedCard = (CardView) view.findViewById(R.id.casterspeed_card);
        healCard = (CardView) view.findViewById(R.id.casterheal_card);

        powerLv = (TextView) view.findViewById(R.id.casterpower_lv);
        speedLv = (TextView) view.findViewById(R.id.casterspeed_lv);
        healLv = (TextView) view.findViewById(R.id.casterheal_lv);

        powerCost = (TextView) view.findViewById(R.id.casterpower_cost);
        speedCost = (TextView) view.findViewById(R.id.casterspeed_cost);
        healCost = (TextView) view.findViewById(R.id.casterheal_cost);

        powerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerLv.setText("Level " + (++level));
            }
        });

        speedCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speedLv.setText("Level " + (++level));
            }
        });

        healCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                healLv.setText("Level " + (++level));
            }
        });
    }
}
