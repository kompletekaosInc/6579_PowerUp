package org.usfirst.frc.team6579.robot.subsystem;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is responsible for the drivetrain of the robot
 *
 * The drivetrain consists of two toughbox minis (7.31:1) with two CIM motors on each side. Thr drivetrain may also have an encoder or two to measure distances, and a gyro.
 *
 * PORTS:
 * Motors in port 0,1,2,3 PWM RIO
 * ToDo: check ports for drivetrain
 *
 * The drivetrain will be solely controlled by the driver
 *
 * Created by Jiah Pang on 28/01.2018
 */
public class Drivetrain implements SubSystem {

//    //Define drivetrain toughbox minis
//    private Toughbox toughbox1 = new Toughbox(0,1);
//    private Toughbox toughbox2 = new Toughbox(2,3);

    private VictorSP victorPort0 = new VictorSP(0);
    private VictorSP victorPort1 = new VictorSP(1);


    private SpeedControllerGroup toughbox1 = new SpeedControllerGroup(victorPort0,victorPort1);

    private VictorSP victorPort2 = new VictorSP(2);
    private VictorSP victorPort3 = new VictorSP(3);
    private SpeedControllerGroup toughbox2 = new SpeedControllerGroup(victorPort2,victorPort3);


    //left drive set
    private SpeedControllerGroup leftToughbox = toughbox1;
    //right drive set
    private SpeedControllerGroup rightToughbox = toughbox2;

    //gyro
    private ADXRS450_Gyro gyro = null;

    //encoder
    private Encoder drivetrainEncoder = null;

    //defining the drivetrain type
    private DifferentialDrive robotDrive = new DifferentialDrive(leftToughbox,rightToughbox);

    double distancePerPulse = 0.2493639169;

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

        try{
            drivetrainEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
            SmartDashboard.putBoolean("Encoder installed", true);

            drivetrainEncoder.setMaxPeriod(.1);
            drivetrainEncoder.setMinRate(10);
            drivetrainEncoder.setDistancePerPulse(0.079375);
            drivetrainEncoder.setReverseDirection(true);
            drivetrainEncoder.setSamplesToAverage(7);


            drivetrainEncoder.reset();
        } catch (Exception e){
            System.out.println("Encoder not installed correctly" + e.toString());
            SmartDashboard.putBoolean("Encoder installed", false);
        }
    }

    /**
     * this is the method for moving the robot, could be used by arcade drive and auto
     * @param leftPower
     * @param rightPower
     */
    public void setPower(double leftPower, double rightPower){
        //sets the left toughbox
        leftToughbox.set(-leftPower);
        //sets the right toughbox
        rightToughbox.set(rightPower);
    }

    /**
     * This method is to stop the power going to the robot.
     * This does NOT make the robot do a hard stop unless brake is set on the motor controllers
     */
    public void stop(){
        setPower(0,0);
    }

    /**
     * This method makes the robot stop on the spot.
     */
    public void hardStop(){
        double leftStopPower = 0.1;
        double rightStopPower = 0.1;

        if (leftToughbox.get()>0){
            leftStopPower = -0.1;

        }
        else{
            leftStopPower = 0.1;
        }

        if (rightToughbox.get() > 0){
            rightStopPower = -0.1;
        }
        else{
            rightStopPower = 0.1;
        }
        //SmartDashboard.putNumber("leftStopPower",leftStopPower);
        //SmartDashboard.putNumber("rightStopPower",rightStopPower);
        setPower(leftStopPower,rightStopPower);

    }

    /**
     * Gets the current gyro angle
     */
    public double getGyroAngle(){
        publishStats();
        double gyroAngle = 0;

        //error handling for if there is no gyro
        if (gyro != null){
            gyroAngle = gyro.getAngle();
        }
        return gyroAngle;
    }

    /**
     * Gets the moderated gyro angle (makes values between -360 and 360)
     * @return
     */
    public double getModGyroAngle(){
        double gyroModAngle = getGyroAngle() % 360;
        SmartDashboard.putNumber("getModGyroAngle", gyroModAngle);

        return gyroModAngle;
    }

    /**
     * resets the gyro
     */
    public void resetGyro(){gyro.reset();}

    /**
     * Follows an angle off the gyro at a driver defined speed
     * @param power
     * @param gyroTarget
     */
    public void followGyro(double power, double gyroTarget)
    {
        // ToDo: fill in this method
        //proportionally drives in the direction of a gyro heading, turning to face the right direction
        double currentGyroAngle = getGyroAngle() % 360;
        double gyroPowerAdjustment = 0;
        double gyroGain = 0.05;


        //Calculates how much to turn based on the current heading and the target heading
        gyroPowerAdjustment = currentGyroAngle - (gyroTarget % 360);
        gyroPowerAdjustment = gyroPowerAdjustment * gyroGain;

        double gyroMotorPowerLeft = power - gyroPowerAdjustment;
        double gyroMotorPowerRight = -power - gyroPowerAdjustment; //ToDo: Make adjustment to remove the - from in front when changing to setPower

        //Makes the motors move
        leftToughbox.set(gyroMotorPowerLeft);
        rightToughbox.set(gyroMotorPowerRight);
    }
    /**
     * Turns left if "left" is true otherwise turns right until the robot reaches the target angle.
     * @param targetAngle
     * @param left
     */
    public void gyroTurn(double targetAngle, boolean left)
    {
        double turnPower = 0.25;

        SmartDashboard.putNumber("gyroTurn.targetAngle", targetAngle);
        SmartDashboard.putBoolean("gyroTurn.left", left);

        // reset gyro sensor to zero
        gyro.reset();   // do not calibrate as this will stop the world and make the gyro crazy

        while ( Math.abs(getModGyroAngle()) < Math.abs(targetAngle))
        {
            if (left)
            {
                setPower(-turnPower, turnPower);
            }
            else
            {
                // must want to turn right
                setPower(turnPower, -turnPower);
            }
        }
        hardStop();
        stop();

    }

    /**
     * Arcade drive using the WPILIB Differential Drive methods
     * @param stickX
     * @param stickY
     */
    public void arcadeDiffDrive(double stickX, double stickY){
        //This is counter-intuitive but it's back to front because that's how WPILIB works, we change it for our logical minds
        robotDrive.arcadeDrive(stickY,stickX);
    }

    /**
     * resets the encoder when called
     */
    public void resetEncoder(){
        drivetrainEncoder.reset();
    }

    /**
     * Drives the robot a set distance using the encoder
     * @param distance
     * @param power
     */
    public void driveEncoder(double distance, double power){

        double targetPulses = 0;

        targetPulses = (distance / distancePerPulse);
        SmartDashboard.putNumber("Target Pulses", targetPulses);

        //double buffer = 0.1*targetPulses;

        while (drivetrainEncoder.getRaw()<targetPulses){
            setPower(power,power);
            SmartDashboard.putNumber("Encoder distance", drivetrainEncoder.getDistance());
        }
        if (drivetrainEncoder.getRaw()>=targetPulses){
            hardStop();

        }

    }

    public void drivePulses(int pulses){

        while (drivetrainEncoder.getRaw()<pulses){
            setPower(0.2,0.2);

        }
        if (drivetrainEncoder.getRaw()>=pulses){
            hardStop();
        }

    }

    @Override
    public void publishStats(){
        SmartDashboard.putNumber("Encoder Distance", drivetrainEncoder.getDistance());
        SmartDashboard.putNumber("Encoder Pulses", drivetrainEncoder.getRaw());
    }

    @Override
    public void test() {
        //test sensors

        //test movement
    }
}
