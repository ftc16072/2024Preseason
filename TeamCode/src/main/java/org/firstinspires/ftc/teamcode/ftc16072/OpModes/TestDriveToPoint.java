package org.firstinspires.ftc.teamcode.ftc16072.OpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous
public class TestDriveToPoint extends QQOpMode {
    public void loop(){
        super.loop();
        boolean done = nav.driveToPositionIN(24,0,0);
        telemetry.addData("isDone",done);
    }
}
