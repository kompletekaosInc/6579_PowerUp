package org.usfirst.frc.team6579.robot.subsystem;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is responsible for the intake mechanism on the robot
 * The intake is used to obtain power cubes from the field into the robot
 *
 * The intake has x motors, and y limits/sensors
 * PORTS:
 * motorA = PWM port b roboRIO, port c PDP
 * sensorX = Analog port y & z roboRIO
 * ToDo: put actual ports for intake here
 *
 * This mechanism will be mostly controlled by the Operator
 *
 * Created by Jiah Pang on 28/01/2018
 */
public class Intake implements SubSystem {


    //Left motor controller group
    private VictorSP intakeLeft4 = new VictorSP(3);
    private VictorSP intakeLeft5 = new VictorSP(4);
    private SpeedControllerGroup leftIntakeMotors = new SpeedControllerGroup(intakeLeft4,intakeLeft5);

    //Right motor controller group
    private VictorSP intakeRight8 = new VictorSP(5);
    private VictorSP intakeRight9 = new VictorSP(6);
    private SpeedControllerGroup rightIntakeMotors = new SpeedControllerGroup(intakeRight8,intakeRight9);


    /**
     * This method sucks in a cube
     * the right motor power is inverted
     * @param power
     */
    public void intakeSuckIn(double power){

       //This sets the powers for the motor controllers
       leftIntakeMotors.set(power);
       rightIntakeMotors.set(-power);
    }

    /**
     * This method spits out the cube
     * The left motor power is inverted
     * @param power
     */
    public void intakeSpitOut(double power){

        //this sets the power
        leftIntakeMotors.set(-power);
        rightIntakeMotors.set(power);
    }

    public void stopIntake(){

        leftIntakeMotors.set(0);
        rightIntakeMotors.set(0);
    }









    @Override
    public void publishStats(){

    }

    @Override
    public void test() {
        //test in

        //test out

        //test hold
    }
}
