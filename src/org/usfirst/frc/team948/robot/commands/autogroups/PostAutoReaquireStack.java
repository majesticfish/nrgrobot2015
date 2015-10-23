package org.usfirst.frc.team948.robot.commands.autogroups;

import org.usfirst.frc.team948.robot.RoboConstants;
import org.usfirst.frc.team948.robot.commands.DriveStraightDistance;
import org.usfirst.frc.team948.robot.commands.LiftToHeight;
import org.usfirst.frc.team948.robot.commands.TurnAngle;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PostAutoReaquireStack extends CommandGroup {
    
	private final double DRIVE_POWER = 0.5;
	private final double TURN_ANGLE = 90;
	private final double TURN_POWER = 0.5;
	
    public  PostAutoReaquireStack() {
    	addParallel(new LiftToHeight(ScissorLift.Level.Ground.voltage));
        addSequential(new DriveStraightDistance(DRIVE_POWER, RoboConstants.BACK_AWAY_DISTANCE));
        addSequential(new PinchAndAcquire());
        addSequential(new TurnAngle(TURN_ANGLE, TURN_POWER));
    }
}
