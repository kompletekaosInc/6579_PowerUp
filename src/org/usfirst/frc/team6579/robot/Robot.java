/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6579.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class  Robot extends IterativeRobot {
	private VictorSP m_frontLeft = new VictorSP(0);
	private VictorSP m_rearLeft = new VictorSP(1);
	private SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
	
	private VictorSP m_frontRight = new VictorSP(2);
	private VictorSP m_rearRight = new VictorSP(3);
	private SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
	
	private DifferentialDrive m_robotDrive = new DifferentialDrive(m_left, m_right);
	
	// replacing with Speed groups
	//private DifferentialDrive m_robotDrive = new DifferentialDrive(new Spark(0), new Spark(1));
	
	private Joystick m_stick = new Joystick(0);
	private Timer m_timer = new Timer();

	private Encoder sampleEncoder;

    PowerDistributionPanel pdp = new PowerDistributionPanel();



	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

	    sampleEncoder = new Encoder(0,1,false,Encoder.EncodingType.k4X);

        //pdp.clearStickyFaults();
	}

	/**
	 * This function is run once each time the robot enters autonomous mode.
	 */
	@Override
	public void autonomousInit() {
		m_timer.reset();
		m_timer.start();
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		// Drive for 2 seconds
		if (m_timer.get() < 2.0) {
			m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
		} else {
			m_robotDrive.stopMotor(); // stop robot
		}
	}

	/**
	 * This function is called once each time the robot enters teleoperated mode.
	 */
	@Override
	public void teleopInit() {
        //Encoder sampleEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
        sampleEncoder.setMaxPeriod(.1);
        sampleEncoder.setMinRate(10);
        sampleEncoder.setDistancePerPulse(5);
        sampleEncoder.setReverseDirection(true);
        sampleEncoder.setSamplesToAverage(7);

        sampleEncoder.reset();
	}

	/**
	 * This function is called periodically during teleoperated mode.
	 */
	@Override
	public void teleopPeriodic() {
		m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());

        SmartDashboard.putNumber("m_left power", m_left.get());


        //Sets up the encoder for getting values
        //Encoder sampleEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
        int count = sampleEncoder.get();
        double raw = sampleEncoder.getRaw();
        double distance = sampleEncoder.getDistance();
        double period = sampleEncoder.getPeriod();
        double rate = sampleEncoder.getRate();
        boolean direction = sampleEncoder.getDirection();
        boolean stopped = sampleEncoder.getStopped();


        SmartDashboard.putNumber("Encoder count",count );
        SmartDashboard.putNumber("Encoder distance",distance);
        SmartDashboard.putNumber("Raw encoder", raw);

        //putting the current values from the left side of the robot (PDP channels 2 & 3)
        SmartDashboard.putNumber("Left current 2",pdp.getCurrent(2));
        SmartDashboard.putNumber("Left current 3", pdp.getCurrent(3));

        //putting the current of the right side (channels x & y)
        SmartDashboard.putNumber("Right current 14",pdp.getCurrent(14));
        SmartDashboard.putNumber("Right current 15",pdp.getCurrent(15));

        //resets the encoder values
        if (m_stick.getRawButton(11)){
            sampleEncoder.reset();
        }
        //resets the PDP's sticky faults
        if (m_stick.getRawButton(12)){
            pdp.clearStickyFaults();
        }

	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
