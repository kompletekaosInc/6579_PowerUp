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
            robot.getDrivetrain().driveEncoderGyro(500,0.40);//drives to side of switch //ToDo: find distances
            robot.getDrivetrain().gyroTurn(90,false);//rotates to face switch
            robot.getDrivetrain().driveEncoderGyro(8.5, 0.40);//drives closer to switch //ToDo: find good power that works
            robot.getLift().lift2(200);//lifts lift 2m
            robot.getIntake().intakeSpitOut(1);//spits out cube
            sleep(1000);
            robot.getIntake().stopIntake();//stops intake
        }
        else if (isSwitchLeft()){
            logger.info("Scale is to the right and Switch is to the left");
            robot.getDrivetrain().driveEncoderGyro(360,0.40);//drives to side of switch //ToDo: find good power that works
            robot.getDrivetrain().gyroTurn(90,false);//rotates to face switch
            robot.getDrivetrain().driveEncoderGyro(35.5, 0.40);//drives closer to switch //ToDo: find good power that works
            robot.getLift().lift2(100);//lifts lift 1m
            robot.getIntake().intakeSpitOut(1);//spits out cube
            sleep(1000);
            robot.getIntake().stopIntake();//stops intake
        }
        else{
            //drive past baseline
            logger.info("Switch & Scale are to the left... doing baseline");
            robot.getDrivetrain().driveEncoderGyro(415,0.40);//ToDo: find good power that works
            robot.getDrivetrain().gyroTurn(90,false);
            robot.getDrivetrain().driveEncoderGyro(523.5, 0.40);
            robot.getDrivetrain().gyroTurn(90, false);
            robot.getDrivetrain().driveEncoderGyro(140.5, 0.40);
            robot.getLift().lift2(100);
            robot.getIntake().intakeSpitOut(1);
            sleep(1000);
            robot.getIntake().stopIntake();

        }

        System.out.println("Right Side Auto complete...");

    }
}

