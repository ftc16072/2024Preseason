package org.firstinspires.ftc.teamcode.ftc16072;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.ftc16072.Mechanisms.QQMechanism;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Robot {
    public List<QQMechanism> mechanisms = Arrays.asList(

    );

    public void init(HardwareMap hwMap){
        for (QQMechanism mechanism: mechanisms){
            mechanism.init(hwMap);
        }
    }

    public List<QQMechanism> getMechanisms(){
        return mechanisms;
    }

    }
