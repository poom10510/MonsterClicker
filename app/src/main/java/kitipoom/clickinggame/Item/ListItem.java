package kitipoom.clickinggame.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kitipoom on 27/5/2559.
 */
public class ListItem {
    private List<Item> itemList;
    public ListItem(){
        itemList = new ArrayList<Item>();
        itemList.add(new M150());
        itemList.add(new Potion());
        itemList.add(new Death());
    }

    public List<Item> getItemList(){
        return itemList;
    }

}
