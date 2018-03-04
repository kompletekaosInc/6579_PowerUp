package org.usfirst.frc.team6579.robot.autonomous;

import org.usfirst.frc.team6579.robot.Robot;
import org.usfirst.frc.team6579.robot.subsystem.Drivetrain;
import org.usfirst.frc.team6579.robot.subsystem.Intake;
import org.usfirst.frc.team6579.robot.subsystem.Lift;

/**
 * This strategy delivers the cube to the switch when starting from the middle position.  It can
 * read from the field system the layout and position of the Switch and Scale and it make the decision based
 * on these field position.
 */
public class CurveMiddleSpotAuto extends AutoStrategy {

    public CurveMiddleSpotAuto() {
        // update logger class
        super.logger.getLogger( this.getClass().getName() );
    }

    @Override
    public void doStrategy(Robot robot) {

        logger.info("Starting MiddleSpotAuto strategy");

        double autoSpeed=0.5;

        // grab the main subsystems needed for auto
        Drivetrain drivetrain = robot.getDrivetrain();
        Intake intake = robot.getIntake();
        Lift lift = robot.getLift();

        //This is the knocking down of the intake, the wait is to make sure the cube falls before we drive off again
        drivetrain.driveStraight(15, autoSpeed);
        drivetrain.hardStop();
        sleep(750);  // to give the cube time to drop


        intake.intakeSuckIn(0.15); //this is to make sure the cube stays in the robot, runs until we spit the cube back out

        //Drive to just in front of the cube stack
        drivetrain.driveStraight(30, 0.5);
        drivetrain.hardStop();


        lift.raiseToHeight(100);

        // now turn towards the side that has our switch plate
        if (isSwitchLeft()){
            drivetrain.curveTurn(60,true);
            drivetrain.driveStraight(120,autoSpeed);
            drivetrain.curveTurn(60,false);
        }
        else{
            // switch is to the right hand side
            drivetrain.curveTurn(60,false);
            drivetrain.driveStraight(105,autoSpeed);
            //drivetrain.hardStop();
            drivetrain.curveTurn(60,true);
        }


        // drive for a time period to push robot up to the switch side wall (distance might get stalled and never reach target
        long beginTimeHardStop = System.currentTimeMillis();
        while (System.currentTimeMillis()-beginTimeHardStop < 1000) {

            drivetrain.setPower(0.35,0.35);
        }

        // should have cube above switch plate. deliver cube
        intake.intakeSpitOut(1);
        sleep(1000);
        intake.stopIntake();
        drivetrain.stop();

        System.out.println("Ended MiddleSpotAuto");

    }
}
