package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "HelloWorldOpMode")
public class HelloWorld extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Main loop
        while (opModeIsActive()) {
            // Display Hello World on the Driver Station
            telemetry.addData("Message", "Hello World!");
            telemetry.update();

            // Optional: Add a small delay to make the loop more manageable
            sleep(100); // Sleep for 100 milliseconds
        }
    }
}
