package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestOTOS;

import java.util.Arrays;
import java.util.List;

public class OpticalTrackingOdometrySensor extends QQMechanism{
    SparkFunOTOS otos;
    @Override
    public void init(HardwareMap hwMap) {
        otos = hwMap.get(SparkFunOTOS.class,"otos");
        otos.setAngularUnit(AngleUnit.DEGREES);
        otos.setLinearUnit(DistanceUnit.MM);

    }

    @Override
    public List<QQTest> getTests() {
        return Arrays.asList(
                new TestOTOS("otos position", otos)
        );
    }
}
