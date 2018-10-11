public class Autonomous extends CommandGroup {
    public AutonomousCommand () {
        CommandGroup driveForward = new CommandGroup();
            driveForward.addSequential(new DriveWithVelocityTimed(1, 2));
            driveForward.addSequential(new TurnRobotTimed(-1, 3));
        this.addParallel(driveForward);
        
        CommandGroup raiseArmOpenClaw = new CommandGroup();
            raiseArmOpenClaw.addSequential(new MoveArmPosition(RobotMap.ARM_UP));
            raiseArmOpenClaw.addSequential(new MoveClawPosition(RobotMap.CLAW_OPEN));
        this.addParallel(raiseArmOpenClaw);
    }
}
