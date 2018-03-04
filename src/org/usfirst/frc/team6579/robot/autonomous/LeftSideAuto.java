package org.usfirst.frc.team6579.robot.autonomous;

import org.usfirst.frc.team6579.robot.Robot;

public class LeftSideAuto extends AutoStrategy {

    public LeftSideAuto() {
        super.logger.getLogger(this.getClass().getName());
    }

    @Override
    public void doStrategy(Robot robot) {

        //Assuming robot is lined up with very far corner of base plate


        if (isScaleLeft()) {
            logger.info("Scale is to the left");
            robot.getDrivetrain().driveStraight(500,0.40);//drives to side of switch //ToDo: find distances
            robot.getDrivetrain().turn(90,false);//rotates to face switch
            robot.getDrivetrain().driveStraight(8.5, 0.40);//drives closer to switch //ToDo: find good power that works
            robot.getLift().raiseToHeight(200);//lifts lift 2m
            robot.getIntake().intakeSpitOut(1);//spits out cube
            sleep(1000);
            robot.getIntake().stopIntake();//stops intake
        }
        else if (isSwitchLeft()){
            logger.info("Scale is to the right and Switch is to the left");
            robot.getDrivetrain().driveStraight(360,0.40);//drives to side of switch //ToDo: find good power that works
            robot.getDrivetrain().turn(90,false);//rotates to face switch
            robot.getDrivetrain().driveStraight(35.5, 0.40);//drives closer to switch //ToDo: find good power that works
            robot.getLift().raiseToHeight(100);//lifts lift 1m
            robot.getIntake().intakeSpitOut(1);//spits out cube
            sleep(1000);
            robot.getIntake().stopIntake();//stops intake
        }
        else{
            //drive past baseline
            logger.info("Switch & Scale are to the left... doing baseline");
            robot.getDrivetrain().driveStraight(415,0.40);//ToDo: find good power that works
            robot.getDrivetrain().turn(90,false);
            robot.getDrivetrain().driveStraight(523.5, 0.40);
            robot.getDrivetrain().turn(90, false);
            robot.getDrivetrain().driveStraight(140.5, 0.40);
            robot.getLift().raiseToHeight(100);
            robot.getIntake().intakeSpitOut(1);
            sleep(1000);
            robot.getIntake().stopIntake();

        }

        System.out.println("Right Side Auto complete...");

    }
}

