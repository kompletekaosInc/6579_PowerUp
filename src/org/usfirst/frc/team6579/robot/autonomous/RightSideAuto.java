package org.usfirst.frc.team6579.robot.autonomous;

import org.usfirst.frc.team6579.robot.Robot;

public class RightSideAuto extends AutoStrategy {

    public RightSideAuto() {
        //update logger class
        super.logger.getLogger(this.getClass().getName());
    }

    //Assuming robot is lined up with very far corner of base plate

    @Override
    public void doStrategy(Robot robot) {

        //Assuming robot is lined up with very far corner of base plate

        if (isSwitchLeft()==false){
            logger.info("Switch is to the right");
            robot.getDrivetrain().driveEncoderGyro(360,40);//drives to side of switch //ToDo: find good power that works
            robot.getDrivetrain().gyroTurn(90,true);//rotates to face switch
            robot.getDrivetrain().driveEncoderGyro(35.5, 40);//drives closer to switch //ToDo: find good power that works
            robot.getLift().lift2(100);//lifts lift 1m
            robot.getIntake().intakeSpitOut(1);//spits out cube
            sleep(1000);
            robot.getIntake().stopIntake();//stops intake
        }
        else if (isScaleLeft()==false) {
            logger.info("Switch is to the left and scale is to the right");
            robot.getDrivetrain().driveEncoderGyro(360,40);//drives to side of switch //ToDo: find good power that works
            robot.getDrivetrain().gyroTurn(90,true);//rotates to face switch
            robot.getDrivetrain().driveEncoderGyro(8.5, 40);//drives closer to switch //ToDo: find good power that works
            robot.getLift().lift2(200);//lifts lift 2m
            robot.getIntake().intakeSpitOut(1);//spits out cube
            sleep(1000);
            robot.getIntake().stopIntake();//stops intake
        }
        else{
            //drive past baseline
            logger.info("Switch & Scale are to the left... doing baseline");
            robot.getDrivetrain().driveEncoderGyro(415,40);//ToDo: find good power that works
            robot.getDrivetrain().gyroTurn(90,true);
            robot.getDrivetrain().driveEncoderGyro(523.5, 40);
            robot.getDrivetrain().gyroTurn(90, true);
            robot.getDrivetrain().driveEncoderGyro(140.5, 40);
            robot.getLift().lift2(100);
            robot.getIntake().intakeSpitOut(1);
            sleep(1000);
            robot.getIntake().stopIntake();
        }

        System.out.println("Right Side Auto complete...");
    }
}

