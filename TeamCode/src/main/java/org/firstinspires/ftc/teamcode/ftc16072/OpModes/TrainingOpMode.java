package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Config
public class TrainingOpMode extends OpMode {
    public static double B_POWER = 1.0;
    public static double X_POWER = 0;
    Servo servo;
    DcMotor motor;
    public static double CLAW_OPEN = 0.5;
    public static double CLAW_CLOSE = 0.9;

    @Override
    public void init() {
        telemetry.addData("Programmer", "Kavya");
        servo = hardwareMap.get(Servo.class, "servo");
        motor = hardwareMap.get(DcMotor.class, "motor");
    }

    @Override
    public void loop() {
        double yStick = -gamepad1.left_stick_y;
        double speed = yStick / 2;
        telemetry.addData("Y", yStick);
        telemetry.addData("speed", speed);
        if (gamepad1.a) {
            servo.setPosition(CLAW_OPEN);
        } else {
            servo.setPosition(CLAW_CLOSE);
        }
        if (gamepad1.b) {
            motor.setPower(B_POWER);
        } else if (gamepad1.x) {
            motor.setPower(X_POWER);
        }
    }
}