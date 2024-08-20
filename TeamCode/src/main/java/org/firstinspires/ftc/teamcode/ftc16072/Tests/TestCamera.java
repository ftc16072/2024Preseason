package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;

public class TestCamera extends QQTest{
    WebcamName camera;
    public TestCamera(String name, WebcamName camera){
        super(name);
        this.camera = camera;
    }

    @Override
    public void run(boolean on, Telemetry telemetry) {
        telemetry.addData(this.getName() + " is attached", camera.isAttached());
    }
}
