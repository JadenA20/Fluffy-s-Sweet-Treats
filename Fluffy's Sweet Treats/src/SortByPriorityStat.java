import java.util.Comparator;

public class SortByPriorityStat implements Comparator<Inventory> {
    //Sorts inventory items by priority status

    public int compare(Inventory i1, Inventory i2){
        return Integer.compare(i1.getPriorityStatus(), i2.getPriorityStatus());
    }

}
