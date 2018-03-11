/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team6579.robot.autonomous.*;
import org.usfirst.frc.team6579.robot.control.DriverControl;
import org.usfirst.frc.team6579.robot.control.OperatorControl;
import org.usfirst.frc.team6579.robot.control.RobotControl;
import org.usfirst.frc.team6579.robot.subsystem.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 *
 * Created 28/01/2018 by Jiah Pang
 */
public class  Robot extends IterativeRobot {

    //subsystems
    private Drivetrain drivetrain = null;
    private Climber climber = null;
    private Lift lift = null;
    private Intake intake = null;

    //manage a collection of all the subsystems for the robot
    private List subSystems = new ArrayList();

    private RobotControl robotControl;
    private RobotControl operatorControl;

//  Holds randomized game data from start of the match
//  private String gameData; - Griff 10 Feb

//	private Timer m_timer = new Timer();
//
//	private Encoder sampleEncoder;
//
//    PowerDistributionPanel pdp = new PowerDistributionPanel();

    private AutoStrategy autoStrategy;
    private SendableChooser autoChooser;



    private void populateAutoSelector(){
        autoChooser = new SendableChooser();

        autoChooser.addDefault("Middle auto", new MiddleSpotAuto());

        autoChooser.addObject("Curve middle auto", new CurveMiddleSpotAuto());

        autoChooser.addObject("Left Spot", new LeftSideAuto());

        autoChooser.addObject("Right Spot", new RightSideAuto());

        autoChooser.addObject("Dumb-Straight Auto", new StraightAuto());

        SmartDashboard.putData("Auto code selector",autoChooser);
    }

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("robotInit: [build=Jiah:compDay1]" + dateFormat.format(date));

        populateAutoSelector();

        try {
            // manage the collection of SubSystems
            drivetrain = new Drivetrain();
            climber = new Climber();
            intake = new Intake();
            lift = new Lift();

            subSystems.add( drivetrain );
            subSystems.add( climber );
            subSystems.add(lift);
            subSystems.add(intake);




        } catch (Exception e) {
            System.out.println("Error loading sub-systems");
            e.printStackTrace();


        }

        try {
            //assume this is USB port 0
            robotControl = new DriverControl();  //assume this is USB port 0
            SmartDashboard.putBoolean("Robot control available", true);
        }
        catch(Exception e){
            System.out.println("Error loading robot controllers");
            e.printStackTrace();
            SmartDashboard.putBoolean("Robot control available", false);
        }

        try {
            //assume this is USB port 1
            operatorControl = new OperatorControl();
            SmartDashboard.putBoolean("Operator control available", true);
        }
        catch(Exception e){
            System.out.println("Error loading operator controllers");
            e.printStackTrace();
            SmartDashboard.putBoolean("Operator control available", false);
        }
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
	    resetSensors();
	    autoStrategy = (AutoStrategy) autoChooser.getSelected();
	    autoStrategy.start(this);
//		m_timer.reset();
//		m_timer.start();

//      Inserts random game data into variable
//      gameData = DriverStation.getInstance().getGameSpecificMessage(); - Griff 10 Feb

	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
//		// Drive for 2 seconds
//		if (m_timer.get() < 2.0) {
//			m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
//		} else {
//			m_robotDrive.stopMotor(); // stop robot
//		}
        publishSubSystemStats();
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit() {
//        if (autoStrategy != null){
//            autoStrategy.cancel();//only seems to work for command based system
//        }

        //autoStrategy.end();

	    resetSensors();

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println("teleopInit: [build=JiahON DAY:21:09]" + dateFormat.format(date));
//        //Encoder sampleEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
//
//
//        sampleEncoder.reset();

        resetSensors();

	}

    private void resetSensors() {
        try {
            drivetrain.resetGyro();
            drivetrain.resetEncoder();
            lift.resetEncoder();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
        if (robotControl != null) {
            robotControl.giveCommands(this);
        }
        if (operatorControl != null) {
            operatorControl.giveCommands ( this);
        }

        publishSubSystemStats();




//		//m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
//
//        //SmartDashboard.putNumber("m_left power", m_left.get());
//
//
//        //Sets up the encoder for getting values
//        //Encoder sampleEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
//        int count = sampleEncoder.get();
//        double raw = sampleEncoder.getRaw();
//        double distance = sampleEncoder.getDistance();
//        double period = sampleEncoder.getPeriod();
//        double rate = sampleEncoder.getRate();
//        boolean direction = sampleEncoder.getDirection();
//        boolean stopped = sampleEncoder.getStopped();
//
//
//        SmartDashboard.putNumber("Encoder count",count );
//        SmartDashboard.putNumber("Encoder distance",distance);
//        SmartDashboard.putNumber("Raw encoder", raw);
//
//        //putting the current values from the left side of the robot (PDP channels 2 & 3)
//        SmartDashboard.putNumber("Left current 2",pdp.getCurrent(2));
//        SmartDashboard.putNumber("Left current 3", pdp.getCurrent(3));
//
//        //putting the current of the right side (channels x & y)
//        SmartDashboard.putNumber("Right current 14",pdp.getCurrent(14));
//        SmartDashboard.putNumber("Right current 15",pdp.getCurrent(15));
//
//        //resets the encoder values
//        if (m_stick.getRawButton(11)){
//            sampleEncoder.reset();
//        }
//        //resets the PDP's sticky faults
//        if (m_stick.getRawButton(12)){
//            pdp.clearStickyFaults();
//        }

       // SmartDashboard.put

	}

	//simple accessor methods
    public Drivetrain getDrivetrain(){return drivetrain;}

    public Climber getClimber(){return climber; }

    public Intake getIntake(){return intake; }

    public Lift getLift(){return lift; }



	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

//		//resets the encoder values
//		if (m_stick.getRawButton(11)){
//			sampleEncoder.reset();
//		}
//		//resets the PDP's sticky faults
//		if (m_stick.getRawButton(12)){
//			pdp.clearStickyFaults();
//		}
//
//        //putting the current of the right side (channels x & y)
//        SmartDashboard.putNumber("Right current 14",pdp.getCurrent(14));
//        SmartDashboard.putNumber("Right current 15",pdp.getCurrent(15));
	}
    public void publishSubSystemStats()
    {
        Iterator i = subSystems.iterator();
        while (i.hasNext())
        {
            SubSystem nextSubSystem = (SubSystem) i.next();
            nextSubSystem.publishStats();
        }


    }
}

