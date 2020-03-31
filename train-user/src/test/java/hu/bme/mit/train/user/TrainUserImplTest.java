package hu.bme.mit.train.user;


import hu.bme.mit.train.controller.TrainControllerImpl;
import org.junit.Assert;
import org.junit.Test;

public class TrainUserImplTest {
    TrainUserImpl trainUser = new TrainUserImpl(new TrainControllerImpl());

    @Test
    public void testGetAlarmFlag() {
        Assert.assertFalse(trainUser.getAlarmFlag());
    }

    @Test
    public void testGetReferenceSpeed() throws InterruptedException {
        trainUser.overrideJoystickPosition(5);
        Thread.sleep(5000);

        trainUser.overrideJoystickPosition(0);
        Thread.sleep(2000);

        trainUser.overrideJoystickPosition(-2);
        Thread.sleep(20000);
    }
}
