package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.RoboConstants;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.DriveToXY;
import org.usfirst.frc.team948.robot.commands.EjectTote;
import org.usfirst.frc.team948.robot.commands.LiftToHeight;
import org.usfirst.frc.team948.robot.commands.Pinch;
import org.usfirst.frc.team948.robot.commands.ResetSensors;
import org.usfirst.frc.team948.robot.commands.SetPosition;
import org.usfirst.frc.team948.robot.commands.TurnAngle;
import org.usfirst.frc.team948.robot.subsystems.Pincher;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;
import org.usfirst.frc.team948.robot.subsystems.Pincher.Position;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class UpdatedAutonomousThreeTotes extends CommandGroup implements RoboConstants {
	final Double deflectSpeed = 0.8;
	final Double deflectTime = 1.0;
	Double driveSpeed = 0.8; // REVIEW: was 0.9 but jammed RC;
	Double driveDistance = 5.0; // 4.85
	public UpdatedAutonomousThreeTotes(int totes, boolean[] bins){
		addParallel(new SetPosition(3.42, 5.58)); //TODO verify x and y coordinates for the starting position of the robot
		addSequential (new ResetSensors());
		addSequential(new PinchAndAcquire());
		
		for(int tote = 1; tote < totes; tote ++){
			if(tote == 1) {
				DelayAndDrive driveCommand = new DelayAndDrive(0, driveSpeed, driveDistance, HEIGHT_TO_CLEAR_ACQUIRER);
				addParallel(new DeflectAndLift(deflectSpeed, deflectTime, tote, bins[tote-1], driveCommand));
				addSequential(driveCommand);
			}else if(tote == 2) {
				driveSpeed = 0.73; // 0.7;
				driveDistance = 6.85; // 6.7;
				DelayAndDrive driveCommand = new DelayAndDrive(1, driveSpeed, driveDistance, 0.3);
				addParallel(new DeflectAndLift(deflectSpeed, deflectTime, tote, bins[tote-1], driveCommand));
				addSequential(driveCommand);
			}
			addSequential(new PinchAndAcquire());
		}
		
		addParallel(new Pinch(Pincher.Position.Close));
		//Turn to face the auto zone
		addParallel(new LiftToHeight(ScissorLift.Level.Step));
		addSequential(new TurnAngle(-80, 0.7, 5.0));
		
		//Drive into the auto zone
		addSequential(new DriveStraightDistance(-1, 7.4));
		//addSequential(new DriveToXY(12, 17, -1));
		
		addParallel(new Pinch(Position.Open));
		//Place stack of totes down
		Command backAwayDriveCommand = new DriveStraightDistance(-1, BACK_AWAY_DISTANCE);
		addParallel(new EjectTote(backAwayDriveCommand));
		// Back away from stack
		addSequential(backAwayDriveCommand);
	}
}
