import org.junit.Assert;
import org.junit.Test;

public class TrainTacographTest {

    @Test
    public void testTrainTachoTable() {
        TrainTachograph trainTachograph =   new TrainTachograph();

        trainTachograph.getTable().put(System.currentTimeMillis(), 55, 26);

        Assert.assertTrue(!trainTachograph.table.isEmpty());
    }
}
