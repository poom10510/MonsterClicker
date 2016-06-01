package kitipoom.clickinggame.Item;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import kitipoom.clickinggame.Game;
import kitipoom.clickinggame.R;

public class ItemFragment extends Fragment {

    private View view;
    private CardView item1_card, item2_card, item3_card;
    private TextView item1_cost, item2_cost, item3_cost;
    private Item item1, item2, item3;
    private Game game;

    public ItemFragment() {
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
        view = inflater.inflate(R.layout.fragment_item, container, false);
        initComponents();
        return view;
    }

    private void initComponents() {
        game = Game.getInstance();

        item1_card = (CardView) view.findViewById(R.id.item1_card);
        item2_card = (CardView) view.findViewById(R.id.item2_card);
        item3_card = (CardView) view.findViewById(R.id.item3_card);

        item1_cost = (TextView) view.findViewById(R.id.item1_cost);
        item2_cost = (TextView) view.findViewById(R.id.item2_cost);
        item3_cost = (TextView) view.findViewById(R.id.item3_cost);

        ListItem items = new ListItem();

        item1 = items.getItemList().get(0);
        item2 = items.getItemList().get(1);
        item3 = items.getItemList().get(2);

        item1_cost.setText(item1.getPrice() + " $");
        item2_cost.setText(item2.getPrice() + " $");
        item3_cost.setText(item3.getPrice() + " $");

        item1_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getPlayer().getMoney() >= item1.getPrice()) {
                    game.getPlayer().lossMoney(item1.getPrice());
                    item1.Action(game);
                }
            }
        });

        item2_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getPlayer().getMoney() >= item2.getPrice()) {
                    game.getPlayer().lossMoney(item2.getPrice());
                    item2.Action(game);
                }
            }
        });

        item3_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (game.getPlayer().getMoney() >= item3.getPrice()) {
                    game.getPlayer().lossMoney(item3.getPrice());
                    item3.Action(game);
                }
            }
        });
    }
}
