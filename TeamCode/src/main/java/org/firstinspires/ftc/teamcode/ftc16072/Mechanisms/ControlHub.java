package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;


import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestGyro;


import java.util.Collections;
import java.util.List;

public class ControlHub extends QQMechanism{
    IMU gyro;
    @Override
    public void init(HardwareMap hwMap) {
        gyro = hwMap.get(IMU.class,"imu");

        final double xRotationDegrees = 0;
        final double yRotationDegrees = 0;
        final double zRotationDegrees = 0;

        Orientation hubRotation =
                RevHubOrientationOnRobot.xyzOrientation(xRotationDegrees,yRotationDegrees,zRotationDegrees);
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(hubRotation);

        //gyro.initialize();


        }

    @Override
    public List<QQTest> getTests() {
        return Collections.singletonList(new TestGyro("Gyro", gyro));
    }

}