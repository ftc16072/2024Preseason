package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.ftc16072.Robot;
import org.firstinspires.ftc.teamcode.ftc16072.Util.Navigation;

public abstract class QQOpMode extends OpMode {
    public Robot robot = new Robot();
    Navigation nav = new Navigation(robot);

    @Override
    public void init() {
        robot.init(hardwareMap);
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    }

}
