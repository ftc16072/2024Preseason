package org.firstinspires.ftc.teamcode.ftc16072;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.ControlHub;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.QQMechanism;

import java.util.Arrays;
import java.util.List;


public class Robot {
    public ControlHub ControlHub;
    public MecanumDrive MecanumDrive;
    List<QQMechanism> mechanisms;

    public Robot() {
        MecanumDrive = new MecanumDrive();
        ControlHub = new ControlHub();

        mechanisms = Arrays.asList(
                ControlHub,
                MecanumDrive
        );
    }

    public void init(HardwareMap hwMap) {
        for (QQMechanism mechanism : mechanisms) {
            mechanism.init(hwMap);
        }
    }

    public List<QQMechanism> getMechanisms() {
        return mechanisms;
    }
}
