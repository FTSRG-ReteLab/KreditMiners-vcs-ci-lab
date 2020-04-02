package hu.bme.mit.train.controller;

import hu.bme.mit.train.interfaces.TrainController;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class TrainControllerImpl implements TrainController {

	private int step = 0;
	private int referenceSpeed = 0;
	private int speedLimit = 150;

	private final Timer timer = new Timer("PeriodicSpeedControl");
	private final long simulationStartedAt = System.currentTimeMillis();

	public TrainControllerImpl() {
		TimerTask periodicSpeedControlTask = new TimerTask() {
			@Override
			public void run() {

				Random rand = new Random();

				setJoystickPosition(rand.nextInt(20));

				followSpeed();

				// We are simulating the system for 10 seconds
				if ((System.currentTimeMillis() - simulationStartedAt) > 10000) {
					timer.cancel();
				}
			}
		};
		timer.schedule(periodicSpeedControlTask, 100, 1000);
	}

	@Override
	public void followSpeed() {

		referenceSpeed += step;

		enforceLimits();

		System.out.println("Actual speed:" + referenceSpeed);
	}

	private void enforceLimits() {
		if(referenceSpeed < 0){
			referenceSpeed = 0;
		}
		enforceSpeedLimit();
	}

	@Override
	public int getReferenceSpeed() {
		return referenceSpeed;
	}

	@Override
	public void setSpeedLimit(int speedLimit) {
		this.speedLimit = speedLimit;
		enforceSpeedLimit();
		
	}

	private void enforceSpeedLimit() {
		if (referenceSpeed > speedLimit) {
			referenceSpeed = speedLimit;
		}
	}

	@Override
	public void setJoystickPosition(int joystickPosition) {
		this.step = joystickPosition;		
	}
}
