package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp()
public class LokiTeleOp extends QQOpMode{
    static final int INTAKE_POSITION = 0;
    static final int ROW_HEIGHT_IN_TICKS = 500;
    static final int MAX_SCORE_POSITION = 8500;
    static final int MIN_SCORE_POSITION = 6000;
    int desiredPosition = 0;
    int scorePosition = 7500;
    boolean dpadUpWasPressed;
    boolean dpadDownWasPressed;


    @Override
    public void init(){
        super.init();
        if (robot.arm.isArmDown()){
            robot.arm.resetArmPosition();
        }
    }
    @Override
    public void loop() {
        super.loop();
        robot.arm.setDesiredPosition(desiredPosition);
        nav.driveFieldRelative(-gamepad1.left_stick_y,gamepad1.left_stick_x,
                gamepad1.right_stick_x);
        telemetry.addData("score position", scorePosition);
        telemetry.addData("current position", robot.arm.currentPos);
        telemetry.addData("desired position", robot.arm.desiredPosition);
        telemetry.addData("arm speed", robot.arm.armPower);

        if (gamepad1.b){
            desiredPosition = scorePosition;
        } else if (gamepad1.a) {
            desiredPosition = INTAKE_POSITION;
        }
        if (gamepad1.dpad_up && !dpadUpWasPressed){
            scorePosition -= ROW_HEIGHT_IN_TICKS;
            scorePosition = Math.max(scorePosition, MIN_SCORE_POSITION);
            dpadUpWasPressed = true;
            desiredPosition = scorePosition;
        } else if (gamepad1.dpad_down && !dpadDownWasPressed) {
            scorePosition += ROW_HEIGHT_IN_TICKS;
            scorePosition = Math.min(scorePosition, MAX_SCORE_POSITION);
            dpadDownWasPressed = true;
            desiredPosition = scorePosition;
        }
        if (!gamepad1.dpad_up){
            dpadUpWasPressed = false;
        }
        if (!gamepad1.dpad_down){
            dpadDownWasPressed = false;
        }
    }
}
