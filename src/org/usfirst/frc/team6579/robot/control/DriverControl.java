package org.usfirst.frc.team6579.robot.control;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;

/**
 * This class is for the driver to control their subsystems.
 * Subsystems: drivetrain & intake
 *
 * Created 28/01/2018 by Jiah Pang
 */
public class DriverControl extends JoystickControl  {

    //private Joystick stick = new Joystick(0);

    /**
     * Driver will be using a Joystick from port 0.
     */
    public DriverControl(){
        super(0);
    }

//    private double throttleX(Robot robot){
//
//        double throttleValue = -stick.getThrottle();
//
//        double newX = stick.getX() * throttleValue;
//        double newY = stick.getY() * throttleValue;
//
//        return newX;
//
//
//    }

    /**
     * Arcade drive method used for driving the robot around. Only used by this class
     *
     * Now has some throttle code, ToDo: test throttle code on moving robot
     * @param drivetrain
     */
    private void arcadeDrive(Drivetrain drivetrain){

        double throttleValue = (stick.getThrottle()-1)/-2;

        double newX = stick.getX() * throttleValue;
        double newY = stick.getY() * throttleValue;

        //drivetrain.arcadeDiffDrive(stick.getX(),stick.getY());

        drivetrain.arcadeDiffDrive(newX,newY);

    }

    /**
     * This controls the robot and should only be used during TeleOp
     * @param robot
     */
    @Override
    public void giveCommands(Robot robot) {
        super.giveCommands(robot);

        arcadeDrive(robot.getDrivetrain());

    }

    /**
     * These following methods are all for overriding the buttons on the joystick
     * @param robot
     */
    protected void processButton1(Robot robot ){

    }
    protected void processButton2(Robot robot ) {

    }
    protected void processButton3(Robot robot ) {
        robot.getClimber().lowerArm();
    }
    protected void processButton4(Robot robot ) {
        robot.getClimber().raiseArm();
    }
    protected void processButton5(Robot robot ) {
        robot.getLift().liftDown(100);
    }
    protected void processButton6(Robot robot ) {
        robot.getLift().liftUp(100);

    }
    protected void processButton7(Robot robot ) {
        //robot.getIntake().intakeSpitOut(0.5);
        robot.getIntake().intakeSuckIn(0.05);

    }
    protected void processButton8(Robot robot ) {

        robot.getIntake().intakeSuckIn(1);

    }
    protected void processButton9(Robot robot ) {
        robot.getIntake().intakeSpitOut(1);

    }
    protected void processButton10(Robot robot ) {
        //robot.getDrivetrain().drivePulses(192);
        robot.getIntake().intakeSuckIn(0.25);

    }
    protected void processButton11(Robot robot) {
        //robot.getDrivetrain().resetEncoder();
        robot.getIntake().intakeSuckIn(0.5);

    }
    protected void processButton12(Robot robot ) {
        //robot.getDrivetrain().driveEncoder(200,0.2);
        robot.getIntake().intakeSuckIn(0.75);

    }
    protected void processNoButtons(Robot robot ) {
        robot.getLift().stop();
        robot.getClimber().stopArm();
        robot.getIntake().stopIntake();
        SmartDashboard.putNumber("Throttle",stick.getThrottle());
        SmartDashboard.putNumber("AdjustedThrottle",((stick.getThrottle()-1)/-2));

    }

    //@Override
    protected void processThrottle(Robot robot) {
        //super.processThrottle(robot);


    }

}
