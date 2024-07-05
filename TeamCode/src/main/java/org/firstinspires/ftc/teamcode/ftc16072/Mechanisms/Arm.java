package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestMotor;

import java.util.Arrays;
import java.util.List;

public class Arm extends QQMechanism{
    DcMotor armMotor;
    @Override
    public void init(HardwareMap hwMap) {
        armMotor = hwMap.get(DcMotor.class,"arm_motor");

    }

    @Override
    public List<QQTest> getTests() {
        return Arrays.asList(
                new TestMotor("arm up", 0.2, armMotor),
                new TestMotor("arm down", -0.2, armMotor));
    }
}
