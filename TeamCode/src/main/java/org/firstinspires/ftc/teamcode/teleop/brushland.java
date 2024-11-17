package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

@TeleOp
@Config
public class brushland extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the ColorRangefinder sensor
        RevColorSensorV3 sensor = hardwareMap.get(RevColorSensorV3.class, "Color");

        waitForStart();
        while (opModeIsActive()) {
            // read all 3 color channels in one I2C transmission:
            NormalizedRGBA colors = sensor.getNormalizedColors();
            telemetry.addData("rgb: ", colors.red + " " + colors.blue + " " + colors.green);
            telemetry.update();
        }
    }
}
