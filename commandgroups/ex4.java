public class Autonomous extends CommandGroup {
	public AutonomousCommand () {
		this.addSequential(new MoveArmPosition(RobotMap.ARM_DOWN));

		CommandGroup grabCubeBackUp = new CommandGroup();
			grabCubeBackUp.addParallel(new DriveWithVelocityTimed(-1, 2));
			grabCubeBackUp.addParallel (new MoveClawPosition(RobotMap.CLAW_CLOSED));
		this.addSequential(grabCubeBackUp);


		CommandGroup dropCube = new CommandGroup();
			dropCube.addParallel(new TurnRobotTimed(-1, 2));
			dropCube.addParallel(new MoveArmPosition(RobotMap.ARM_UP));
			dropCube.addParallel(new MoveClawPosition(RobotMap.CLAW_OPEN));
		this.addSequential(dropCube);


		CommandGroup grabNextCube = new CommandGroup();
			grabNextCube.addParallel(new DriveWithVelocityTimed(1, 3));
			CommandGroup lowerArmPrintCloseClaw = new CommandGroup();
				CommandGroup lowerArmPrintEncoderCounts = new CommandGroup();
					lowerArmPrintEncoderCounts.addParallel(new MoveArmPosition(RobotMap.ARM_DOWN));
					CommandGroup printEncoderCounts = new CommandGroup();
						printEncoderCounts.addSequential (new PrintCommand(Robot.getDrivetrain().getLeftTalon().getSelectedSensorPosition(RobotMap.PID_PRIMARY)));
						printEncoderCounts.addSequential (new PrintCommand(Robot.getDrivetrain().getRightTalon().getSelectedSensorPosition(RobotMap.PID_PRIMARY)));
					lowerArmPrintEncoderCounts.addParallel(printEncoderCounts);
				lowerArmPrintCloseClaw.addSequential(lowerArmPrintEncoderCounts);
				lowerArmCloseClaw.addSequential(new MoveClawPosition(RobotMap.CLAW_CLOSED));
			grabNextCube.addParallel(lowerArmPrintCloseClaw);
		this.addSequential(grabNextCube);
	}
}
