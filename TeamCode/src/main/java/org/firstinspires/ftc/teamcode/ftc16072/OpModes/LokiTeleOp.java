package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp()
public class LokiTeleOp extends QQOpMode{

    boolean dpadUpWasPressed;
    boolean dpadDownWasPressed;
    int desiredPosition;



    @Override
    public void init(){
        super.init();
        if (robot.arm.isArmDown()){
            robot.arm.resetArmPosition();
        }
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
        telemetry.addData("score position", robot.arm.scorePosition);
        telemetry.addData("current position", robot.arm.currentPos);
        telemetry.addData("desired position", robot.arm.desiredPosition);
        telemetry.addData("arm speed", robot.arm.armPower);

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
        if (desiredPosition == robot.arm.scorePosition){
            robot.arm.setWristToScorePos();
        }
        if (!gamepad1.dpad_up){
            dpadUpWasPressed = false;
        }
        if (!gamepad1.dpad_down){
            dpadDownWasPressed = false;
        }
    }
}
