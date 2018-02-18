package org.usfirst.frc.team6579.robot.autonomous;

import edu.wpi.first.wpilibj.DriverStation;
import org.usfirst.frc.team6579.robot.Robot;

public class MiddleSpotAuto extends AutoStrategy {

    public MiddleSpotAuto() {
        // update logger class
        super.logger.getLogger( this.getClass().getName() );
    }

    @Override
    public void doStrategy(Robot robot) {

        logger.info("Starting MiddleSpotAuto strategy");
        //get switch
        //isSwitchLeft();

        //drive forwards 1m

        //robot.getDrivetrain().driveEncoderGyro(15,0.5);
        //robot.getDrivetrain().stop();
        //sleep(1000);
        //robot.getLift().lift2(10);
        robot.getDrivetrain().setPower(0.4,0.4);
        sleep(500);
        robot.getDrivetrain().setPower(-0.2,0.2);
        sleep(200);
        robot.getDrivetrain().stop();
        sleep(750);

        robot.getIntake().intakeSuckIn(0.15);

        robot.getDrivetrain().driveEncoderGyro(60,0.4);

        logger.info("moved forward, start raising lift");
        robot.getLift().lift2(80);
        logger.info("lift up");

        //sleep(1000);
        //turn 45 degrees to the side with the switch

        if (isSwitchLeft()==true){
            logger.info("Switch is LEFT turning left");
            robot.getDrivetrain().gyroTurn(50,true);
            logger.info("turn done. go forward a bit");
            //sleep(1000);
            robot.getDrivetrain().driveEncoderGyro(150,0.4);//ToDo: find good power that works
            logger.info("to back to the right");

            //sleep( 1000);
            robot.getDrivetrain().gyroTurn(50,false);

        }
        else{
            logger.info("Switch is RIGHT! turning right");
            robot.getDrivetrain().gyroTurn(50,false);
            logger.info("turn done. go forward a bit");
            robot.getDrivetrain().driveEncoderGyro(150,0.4);//ToDo: find good power that works
            logger.info("to back to the left");
            robot.getDrivetrain().gyroTurn(50,true);

        }

        //robot.getLift().lift2(100);

        //sleep(1000);
        logger.info("Should be facing swith, drive forward a bit");
        // drive into the wall slowly for a few seconds
        //robot.getDrivetrain().driveEncoderGyro(20,0.25);
        robot.getDrivetrain().setPower(0.5,0.5);
        sleep(1500);

        logger.info("Deliver cube");
        // shoot for 1 second

        robot.getIntake().intakeSpitOut(1);
        sleep(1000);
        robot.getIntake().stopIntake();
        robot.getDrivetrain().stop();

        //move forwards towards the switch side

        //turn another 45 degrees to square up with the side of the switch

        //raise lift 1m

        //move forwards to the switch

        //stop

        //release the cube from the lift/intake

        //end the strategy

        System.out.println("Ended MiddleSpotAuto");

    }
}
