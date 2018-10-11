public class Autonomous extends CommandGroup {
    public Autonomous () {
        CommandGroup driveForward = new CommandGroup();
            driveForward.addSequential(new DriveWithVelocityTimed(1, 2));
            driveForward.addSequential(new TurnRobotTimed(-1, 3));
        this.addParallel(driveForward);
        
        this.addParallel(new MoveArmPosition(RobotMap.ARM_UP));
    }
}
