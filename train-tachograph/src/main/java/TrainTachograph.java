import com.google.common.collect.Table;
import com.google.common.collect.TreeBasedTable;

public class TrainTachograph {
    Table<Long, Integer, Integer> table = TreeBasedTable.create();

    public Table<Long, Integer, Integer> getTable() {
        return table;
    }
}
