package hu.bme.mit.train.sensor;

import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.interfaces.TrainSensor;
import hu.bme.mit.train.interfaces.TrainUser;

public class TrainSensorImpl implements TrainSensor {

	private TrainController controller;
	private TrainUser user;
	private int speedLimit = 150;

	public TrainSensorImpl(TrainController controller, TrainUser user) {
		this.controller = controller;
		this.user = user;

		setJoystickPosition();
	}

	@Override
	public int getSpeedLimit() {
		return speedLimit;
	}

	@Override
	public void overrideSpeedLimit(int speedLimit) {
		doAlarm(speedLimit);

		this.speedLimit = speedLimit;
		controller.setSpeedLimit(speedLimit);
	}

	private void setJoystickPosition() {
		controller.setJoystickPosition(user.getJoystickPosition());
	}

	private void doAlarm(int speedLimit) {
		if (speedLimit < 0 || speedLimit > 500) {
			user.setAlarmState(true);
			return;
		}

		double diff = (double ) (speedLimit / (controller.getReferenceSpeed() == 0 ? 1 : controller.getReferenceSpeed()));
		if (diff < 0.5 && diff > 0) {
			user.setAlarmState(true);
			return;
		}

		user.setAlarmState(false);
	}
}
