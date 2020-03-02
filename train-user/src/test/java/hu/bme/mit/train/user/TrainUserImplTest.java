package hu.bme.mit.train.user;


import org.junit.Assert;
import org.junit.Test;

public class TrainUserImplTest {
    TrainUserImpl trainUser = new TrainUserImpl(null);

    @Test
    public void testGetAlarmFlag() {
        Assert.assertFalse(trainUser.getAlarmFlag());
    }
}
