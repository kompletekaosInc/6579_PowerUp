package org.usfirst.frc.team6579.robot.subsystem;

import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.Robot;

/**
 * This class is responsible for the lift mechanism on the robot. The lift mechanism is used to lift the intake from floor level to scale height.
 *
 * The lift mechanism has one toughbox mini with a single miniCIM motor, an encoder and a magnetic limit switch
 *
 * PORTS:
 * ToDo: list ports for lift subsystem here
 *
 * The lift mechanism will be solely controlled by the operator
 *
 * Created by Jiah Pang on 28/01/2018
 */
public class Lift implements SubSystem {

    private VictorSP liftMotor = new VictorSP(7);//should be port nine for testing on 2.2.18 Jiah Pang //changed to port 7 8.2.18 Jiah


    private Encoder liftEncoder = null;

    public boolean getLift2 = false;


    public Lift(){

        try {
            liftEncoder = new Encoder(2,3,false,Encoder.EncodingType.k4X);
            SmartDashboard.putBoolean("Lift encoder installed", true);
        }
        catch (Exception e){
            System.out.println("Encoder not installed correctly" + e.toString());
            SmartDashboard.putBoolean("Lift encoder installed", false);
        }
    }

    /**
     * Lifts the mechanism
     * @param power
     */
    public void liftUp(double power){
        liftMotor.set(power);
    }

    /**
     * Lowers the lift mechanism.
     *
     * Always use a positive power, the code will make the power negative.
     * @param power
     */
    public void liftDown(double power){
        liftMotor.set(-power);
    }

    public void stop(){
        liftMotor.set(0);
    }

    public void resetEncoder(){
        liftEncoder.reset();
    }

    /**
     * Lifts the lift mechanism for a certain height specified in cm
     * @param targetDistance
     */
    public void lift2(double targetDistance){

        getLift2 = true;
        while(getDistance() < targetDistance)
        {
            liftMotor.set(1);
        }
        liftMotor.set(0);
        getLift2 = false;


        //new Thread(worker).start();

    }

    public boolean isMoving(){
        return getLift2;
    }

    public double getDistance(){
        return liftEncoder.getRaw()*0.027; //lift travels 0.027cm per pulse calculated by practical experiment where lift raised 68cm in 2510 pulses
    }

    @Override
    public void publishStats() {
        SmartDashboard.putNumber("Lift encoder pulses", liftEncoder.getRaw());
        SmartDashboard.putNumber("Lift encoder distance", liftEncoder.getDistance()); //comparing the encoder's getDistance to our own calculation
        SmartDashboard.putNumber("Lift distance", getDistance()); //this is the accurate calculation
    }

    @Override
    public void test() {
        //up

        //test down

        //test limits
    }



//    private Runnable worker = new Runnable()
//    {
//        getLift2 = true;
//            while(getDistance() < targetDistance)
//        {
//            liftMotor.set(1);
//        }
//            liftMotor.set(0);
//        getLift2 = false;
//    }
}
