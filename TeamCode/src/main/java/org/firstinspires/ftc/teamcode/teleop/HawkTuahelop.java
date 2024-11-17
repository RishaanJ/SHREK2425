package org.firstinspires.ftc.teamcode.teleop;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@TeleOp(name = "HawkTuaheleop")
@Config
public class HawkTuahelop extends LinearOpMode {
    private Limelight3A limelight;

    // Constants for distance calculation dafasfdeee
    private static final double MAX_DISTANCE = 100.0; // Maximum distance for calculation (in inches)
    private static final double MIN_DISTANCE = 10.0;  // Minimum distance for calculation (in inches)
    private static final double MAX_PERCENTAGE = 100.0; // 100% area of the screen

    @Override
    public void runOpMode() throws InterruptedException {
        limelight = hardwareMap.get(Limelight3A.class, "limelight");
        telemetry.setMsTransmissionInterval(11);
        limelight.pipelineSwitch(0);

        // Wait for the Start button to be pressed
        waitForStart();

        RevColorSensorV3 sensor = hardwareMap.get(RevColorSensorV3.class, "Color");

        while (opModeIsActive()) {
            // read all 3 color channels in one I2C transmission:
            NormalizedRGBA colors = sensor.getNormalizedColors();
            telemetry.addData("rgb: ", colors.red + " " + colors.blue + " " + colors.green);
            telemetry.update();
        }


        limelight.start();
        while (opModeIsActive()) {
            LLResult result = limelight.getLatestResult();
            if (result != null) {
                if (result.isValid()) {
                    Pose3D botpose = result.getBotpose();

                    // Retrieve and display the area of the detected object
                    double area = result.getTa();  // Assuming getTa() provides the target area as a percentage

                    // Calculate the distance from the camera based on the area percentage
                    double distance = calculateDistance(area);

                    telemetry.addData("tx", result.getTx());
                    telemetry.addData("ty", result.getTy());
                    telemetry.addData("Botpose", botpose.toString());
                    telemetry.addData("Area (ta)", area);  // Display area
                    telemetry.addData("Distance", distance);  // Display calculated distance
                }
            }
            telemetry.update();
        }
    }

    // Method to calculate distance based on area percentage
    private double calculateDistance(double areaPercentage) {
        // Ensure area percentage is within valid bounds
        areaPercentage = Math.max(0, Math.min(areaPercentage, MAX_PERCENTAGE));

        // Linear mapping from area percentage to distance (inches)
        // Assuming the area is inversely proportional to the distance
        double distance = MAX_DISTANCE - ((areaPercentage / MAX_PERCENTAGE) * (MAX_DISTANCE - MIN_DISTANCE));

        return distance - 90;
    }
}
