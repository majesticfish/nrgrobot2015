package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;

// Create a command that runs the acquirer. Have the constructor pass in a boolean that determines whether the acquiring will be fast or slow.
public class AcquireFastOrSlow extends CommandBase {
	private boolean fast;

	public AcquireFastOrSlow(boolean fast) {
		requires(acquirer);
		this.fast = fast;
	}

	protected void initialize() {
		System.out.println("Acquire Started");
	}

	protected void execute() {
		if (fast) {
			acquirer.fastAcquire();
		} else {
			acquirer.slowAcquire();
		}
	}

	protected boolean isFinished() {
		//tests if tote is fully sucked into aquirer
		return acquirer.isNextToteDetected();
	}

	protected void end() {
		System.out.println("Acquire Ended");
		acquirer.stop();
	}

	protected void interrupted() {
		end();
	}

}
