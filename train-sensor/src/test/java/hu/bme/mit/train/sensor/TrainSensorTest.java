package hu.bme.mit.train.sensor;

import hu.bme.mit.train.controller.TrainControllerImpl;
import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {
    TrainController controller;
    TrainUser user;
    TrainSensor sensor;

    @Before
    public void init() {
        controller = mock(TrainControllerImpl.class);
        user = mock(TrainUserImpl.class);
        sensor = new TrainSensorImpl(controller, user);
    }

    @Test
    public void testAlarmOnTooLowSpeedLimit() {
        sensor.overrideSpeedLimit(-60);
        sensor.overrideSpeedLimit(-30);
        sensor.overrideSpeedLimit(-1);
        verify(user, times(3)).setAlarmState(true);
    }

    @Test
    public void testAlarmOnLowSpeedLimitBorder() {
        sensor.overrideSpeedLimit(0);
        verify(user, times(1)).setAlarmState(false);
    }

    @Test
    public void testAlarmBetweenTheBorders() {
        sensor.overrideSpeedLimit(300);
        verify(user, times(1)).setAlarmState(false);
    }

    @Test
    public void testAlarmTooHighChange() {
        when(controller.getReferenceSpeed()).thenReturn(200);
        sensor.overrideSpeedLimit(99);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void testAlarmOnTooHighSpeedLimit() {
        sensor.overrideSpeedLimit(700);
        verify(user, times(1)).setAlarmState(true);
    }

    @Test
    public void testAlarmOnHighSpeedLimitBorder() {
        sensor.overrideSpeedLimit(500);
        verify(user, times(1)).setAlarmState(false);
    }
}
