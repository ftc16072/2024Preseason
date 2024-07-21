package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class LokiTeleOp extends QQOpMode{

    boolean dpadUpWasPressed;
    boolean dpadDownWasPressed;
    boolean leftBumperWasPressed;
    boolean rightBumperWasPressed;
    int desiredPosition;
    double wristPosition;



    @Override
    public void init(){
        super.init();
        if (robot.arm.isArmDown()){
            robot.arm.resetArmPosition();
        }
        robot.arm.wristServo.setPosition(robot.arm.WRIST_TRANSFER_POS);
    }
    @Override
    public void init_loop(){
        robot.arm.armToGround();
    }

    @Override
    public void loop() {
        super.loop();
        robot.arm.setDesiredPosition(desiredPosition);
        nav.driveFieldRelative(-gamepad1.left_stick_y,gamepad1.left_stick_x,
                gamepad1.right_stick_x);
        robot.arm.wristServo.setPosition(wristPosition);
        telemetry.addData("score position", robot.arm.scorePosition);
        telemetry.addData("current position", robot.arm.currentPos);
        telemetry.addData("desired position", robot.arm.desiredPosition);
        telemetry.addData("arm speed", robot.arm.armPower);
        telemetry.addData("wrist position", wristPosition);
        telemetry.addData("current servo pos",robot.arm.wristServo.getPosition());

        if (gamepad1.b){
            desiredPosition = robot.arm.scorePosition;
        } else if (gamepad1.a) {
            desiredPosition = robot.arm.INTAKE_POSITION;
        }
        if (gamepad1.dpad_up && !dpadUpWasPressed){
            robot.arm.pixelRowUp();
            desiredPosition = robot.arm.scorePosition;
            dpadUpWasPressed = true;

        } else if (gamepad1.dpad_down && !dpadDownWasPressed) {
            robot.arm.pixelRowDown();
            desiredPosition = robot.arm.scorePosition;
            dpadDownWasPressed = true;
        }
        if(gamepad1.left_bumper && !leftBumperWasPressed){
            wristPosition += 0.05;
            leftBumperWasPressed = true;
        }else if (gamepad1.right_bumper && !rightBumperWasPressed){
            wristPosition -= 0.05;
            rightBumperWasPressed = true;
        }

//        if (desiredPosition == robot.arm.scorePosition){
//            robot.arm.setWristToScorePos();
//        }
        if (!gamepad1.dpad_up){
            dpadUpWasPressed = false;
        }
        if (!gamepad1.dpad_down){
            dpadDownWasPressed = false;
        }
        if (!gamepad1.right_bumper){
            rightBumperWasPressed = false;
        }
        if(!gamepad1.left_bumper){
            leftBumperWasPressed = false;
        }
    }
}
