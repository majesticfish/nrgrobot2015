package org.usfirst.frc.team948.robot.commands;


/**
*
* Drive straight on a heading under human control (left joystick)
* until the y-value of the joystick is at 0 or the comand gets interrupted.
* 
*/

public class ManualDriveStraight extends CommandBase {
	private double heading;
	
	public ManualDriveStraight() {
		requires(drive);
	}
	
	protected void initialize() {
		heading = drive.driveOnHeadingInit(1);
	}
	
	// Drives on a fixed heading using the left joystick y-value for power
	protected void execute() {
		drive.driveOnHeading(heading, ds.getRightJSY());
	}
	
	// Command runs forever, until it is interrupted or the battery goes dead
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {
		drive.driveOnHeadingEnd();
		drive.rawStop(); 
		drive.setDesiredHeading(heading);
	}
	
	protected void interrupted() {
		end();
	}
}
