package org.usfirst.frc.team6579.robot.control;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;


/**
 * This class is for the operator to control their components of the robot.
 *
 * Created 28/01/2018 by Jiah Pang
 */
public class OperatorControl extends JoystickControl {


    /**
     * Driver will be using a Joystick from port 1.
     */
    public OperatorControl(){
        super(1);

    }

    @Override
    protected void processButton9(Robot robot) {
        //robot.getLift().resetEncoder();
             //robot.getLift().raiseToHeight(180);
        //System.out.println("in processButton9");
        //robot.getClimber().stopArm();
        //not used practically
    }

    @Override
    protected void processButton11(Robot robot) {
        System.out.println("in processButton11");
//      robot.getClimber().lowerArm();
        robot.getClimber().lowerArm25();
        SmartDashboard.putBoolean("arm motor down",true);
    }

    @Override
    protected void processButton12(Robot robot) {
        System.out.println("in processButton12");
//      robot.getClimber().raiseArm();
        robot.getClimber().raiseArm25();
    }

    @Override
    protected void processButton3(Robot robot){
        robot.getClimber().winchRaise(100);
    }

    @Override
    protected void processButton4(Robot robot){
        //robot.getClimber().winchLower(50);
        robot.getLift().stop(); //added to protect the lift manually in case of accident
    }

    @Override
    protected void processButton5(Robot robot){
        robot.getClimber().winchRaise(75);
    }

    @Override
    protected void processButton1(Robot robot){
        //robot.getIntake().intakeSuckIn(1);
    }

    @Override
    protected void processButton2(Robot robot) {
        //robot.getIntake().intakeSpitOut(1);
        robot.getClimber().completelyLowerArm100();
    }

    @Override
    protected void processButton10(Robot robot){
        //robot.getIntake().intakeSuckIn(0.1);
        //unused button
        //robot.getLift().raiseToHeight(100);
        robot.getLift().liftDown(0.74);
    }

    @Override
    protected void processButton8(Robot robot) {
        robot.getLift().liftUp(1);
    }

    @Override
    protected void processButton7(Robot robot){
        robot.getLift().liftDown(0.81);
    }

    @Override
    protected void processNoButtons(Robot robot){
        //if statement makes sure the lift doesn't stop if raiseToHeight is in use
//        if (!robot.getLift().isMoving()){
//            robot.getLift().stop();
//        }
        System.out.println("Operator no buttons");
        //robot.getLift().stop();//stop then hold
        robot.getLift().hold();
        robot.getClimber().stopWinch();
        robot.getClimber().stopArm();
        SmartDashboard.putBoolean("arm motor down",false);

        //robot.getIntake().stopIntake();
    }

}
