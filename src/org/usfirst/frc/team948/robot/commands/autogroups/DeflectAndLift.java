package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.commands.Delay;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DeflectAndLift extends CommandGroup{
	public DeflectAndLift(double deflectSpeed, double deflectTime, int tote, boolean bin, DelayAndDrive driveCommand){
		addParallel(new UnpinchDelayDeflectBinWithoutDrive(deflectSpeed, deflectTime, bin, driveCommand));
	    addSequential(new LiftNextTote(tote));
	}
	public DeflectAndLift(double deflectSpeed, double deflectTime, int tote, boolean bin, DriveStraightDistance driveCommand){
		addParallel(new UnpinchDelayDeflectBinWithoutDrive(deflectSpeed, deflectTime, bin, driveCommand));
	    addSequential(new LiftNextTote(tote));
	}
}
