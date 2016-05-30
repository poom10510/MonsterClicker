package kitipoom.clickinggame.UpgradeFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import kitipoom.clickinggame.R;

public class HeroUpgrade extends Fragment {

    private  View view;
    private CardView powerCard,healCard,hpCard;
    private TextView powerLv,healLv,hpLv;
    private TextView powerCost,healCost,hpCost;
    private int level =1;


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

    public void initComponents(){
        powerCard = (CardView) view.findViewById(R.id.heropower_card);
        healCard = (CardView) view.findViewById(R.id.heroheal_card);
        hpCard = (CardView) view.findViewById(R.id.herohp_card);

        powerLv = (TextView)view.findViewById(R.id.heropower_lv);
        healLv = (TextView)view.findViewById(R.id.heroheal_lv);
        hpLv = (TextView)view.findViewById(R.id.herohp_lv);

        powerCost = (TextView)view.findViewById(R.id.heropower_cost);
        healCost = (TextView)view.findViewById(R.id.heroheal_cost);
        hpCost = (TextView)view.findViewById(R.id.herohp_cost);

        powerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerLv.setText("Level "+(++level));
            }
        });

        healCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                healLv.setText("Level "+(++level));
            }
        });

        hpCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hpLv.setText("Level "+(++level));
            }
        });



    }

}
