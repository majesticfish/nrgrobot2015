package org.usfirst.frc.team948.robot.commands;

public class ManualAcquireBase extends CommandBase {

	private boolean dualJoystickControl;

	public ManualAcquireBase(boolean dualJoystickControl) {
		requires(acquirer);
		this.dualJoystickControl = dualJoystickControl;
	}

	@Override
	protected void initialize() {

	}

	@Override
	protected void execute() {
		if (dualJoystickControl) {
			acquirer.runLeft(ds.getXboxLY());
			acquirer.runRight(ds.getXboxRY());
		} else {
			acquirer.runLeft(ds.getXboxLY());
			acquirer.runRight(ds.getXboxLY());
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
	
	@Override
	protected void end() {
		acquirer.stop();
	}
	
	@Override
	protected void interrupted() {
		end();
	}
}
