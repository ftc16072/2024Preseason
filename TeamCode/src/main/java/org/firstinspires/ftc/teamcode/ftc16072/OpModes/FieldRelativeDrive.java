package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp()
public class FieldRelativeDrive extends QQOpMode{

    @Override
    public void loop() {
        telemetry.addData("hall", robot.arm.isArmDown());
        nav.driveFieldRelative(-gamepad1.left_stick_y,gamepad1.left_stick_x,gamepad1.right_stick_x);
    }
}
