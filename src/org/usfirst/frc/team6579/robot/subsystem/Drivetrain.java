package org.usfirst.frc.team6579.robot.subsystem;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is responsible for the drivetrain of the robot
 *
 * The drivetrain consists of two toughbox minis (7.31:1) with two CIM motors on each side. Thr drivetrain may also have an encoder or two to measure distances, and a gyro.
 *
 * PORTS:
 * ToDo: fill ports for drivetrain
 *
 * The drivetrain will be solely controlled by the driver
 *
 * Created by Jiah Pang on 28/01.2018
 */
public class Drivetrain implements SubSystem {

    //Define drivetrain toughbox minis
    private Toughbox toughbox1 = new Toughbox(0,1);
    private Toughbox toughbox2 = new Toughbox(2,3);

    //left drive set
    private Toughbox leftToughbox = toughbox1;
    //right drive set
    private Toughbox rightToughbox = toughbox2;

    //gyro
    private ADXRS450_Gyro gyro = null;

    /**
     * Declaration of class, tries for gyro and sets the gear side as the default front of robot
     */
    public Drivetrain() {

        try {
            gyro = new ADXRS450_Gyro();
            SmartDashboard.putBoolean("Gyro Installed", true);

            //calibrates and resets the gyro to 0
            gyro.reset(); // Reset the angle the gyro is pointing towards to 0
            gyro.calibrate(); //Takes a long time, will have to test if necessary
        }
        catch (Exception e)
        {
            System.out.println("Gyro not installed correctly" + e.toString());
            SmartDashboard.putBoolean("Gyro Installed", false);
        }
    }

    /**
     * this is the method for moving the robot, could be used by arcade drive and auto
     * @param leftPower
     * @param rightPower
     */
    public void setPower(double leftPower, double rightPower){
        //sets the left toughbox
        leftToughbox.set(leftPower);
        //sets the right toughbox
        rightToughbox.set(rightPower);
    }



    @Override
    public void publishStats() {

    }

    @Override
    public void test() {
        //test sensors

        //test movement
    }
}
