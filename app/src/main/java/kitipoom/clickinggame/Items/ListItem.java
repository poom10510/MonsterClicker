package kitipoom.clickinggame.Items;

import java.util.ArrayList;
import java.util.List;

public class ListItem {
    private List<Item> itemList;

    public ListItem() {
        itemList = new ArrayList<>();
        itemList.add(new M150());
        itemList.add(new Potion());
        itemList.add(new Death());
    }

    public List<Item> getItemList() {
        return itemList;
    }

}
