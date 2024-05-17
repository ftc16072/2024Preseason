package org.firstinspires.ftc.teamcode.ftc16072.Tests;

import org.firstinspires.ftc.robotcore.external.Telemetry;

abstract public class QQTest {
    private String description;

    QQTest(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public abstract void run(boolean on, Telemetry telemetry);

}
