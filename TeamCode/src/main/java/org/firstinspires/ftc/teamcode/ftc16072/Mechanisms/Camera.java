package org.firstinspires.ftc.teamcode.ftc16072.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.QQTest;
import org.firstinspires.ftc.teamcode.ftc16072.Tests.TestCamera;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

import java.util.Collections;
import java.util.List;


public class Camera extends QQMechanism{
    private AprilTagProcessor aprilTagProcessor;
    private WebcamName camera;
    private VisionPortal visionPortal;
    @Override
    public void init(HardwareMap hwMap) {
       camera = hwMap.get(WebcamName.class, "Camera");
       aprilTagProcessor = AprilTagProcessor.easyCreateWithDefaults();
       visionPortal = VisionPortal.easyCreateWithDefaults(camera, aprilTagProcessor);
    }

    @Override
    public List<QQTest> getTests() {
        return Collections.singletonList(new TestCamera("Camera", camera));
    }

    public AprilTagDetection findAprilTag(){
        List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();
        if(currentDetections.size() > 0){
            return currentDetections.get(0);
        }
        return null;
    }
}
