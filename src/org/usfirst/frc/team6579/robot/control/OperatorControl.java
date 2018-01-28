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
public class OperatorControl implements RobotControl {


    private Joystick operatorStick = new Joystick(1);



    /**
     * This is the method that controls the robot.
     * This method needs a robot and should only be used in teleop
     * @param robot
     */
    @Override
    public void giveCommands(Robot robot) {

    }
}
