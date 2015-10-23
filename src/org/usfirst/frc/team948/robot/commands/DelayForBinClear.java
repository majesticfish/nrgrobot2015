package org.usfirst.frc.team948.robot.commands;

import org.usfirst.frc.team948.robot.RobotMap;
import org.usfirst.frc.team948.robot.subsystems.ScissorLift;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class DelayForBinClear extends CommandBase{
	boolean goneDown = false;
	double pot;
	public DelayForBinClear(double i){
		pot = i;
	}
	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		if(!goneDown &&  RobotMap.scissorLiftPotentiometer.get() < ScissorLift.Level.TwoInch.voltage- 0.1) goneDown = true;
	}
	@Override
	protected boolean isFinished() {
		//Practice Base: 0.505
		return (goneDown && RobotMap.scissorLiftPotentiometer.get() > pot);
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {
		
	}
}
