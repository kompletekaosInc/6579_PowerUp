package org.usfirst.frc.team6579.robot.control;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team6579.robot.Robot;
import org.usfirst.frc.team6579.robot.subsystem.Climber;
import org.usfirst.frc.team6579.robot.subsystem.Intake;
import org.usfirst.frc.team6579.robot.subsystem.Lift;


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
        System.out.println("in processButton9");
        robot.getClimber().stopArm();
    }

    @Override
    protected void processButton11(Robot robot) {
        System.out.println("in processButton11");
       robot.getClimber().lowerArm();
    }

    @Override
    protected void processButton12(Robot robot) {
        System.out.println("in processButton12");
        robot.getClimber().raiseArm();
    }


}
