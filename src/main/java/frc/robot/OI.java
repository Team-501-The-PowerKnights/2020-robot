/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.ITelemetryProvider;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class OI implements ITelemetryProvider {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(OI.class.getName());

    /** Singleton instance of class for all to use **/
    private static OI ourInstance;
    /** Name of our subsystem **/
    private final static String myName = TelemetryNames.OI.name;

    public static synchronized void constructInstance() {
        SmartDashboard.putBoolean(TelemetryNames.OI.status, false);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        ourInstance = new OI();

        SmartDashboard.putBoolean(TelemetryNames.OI.status, true);
    }

    public static OI getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }
        return ourInstance;
    }

    private Joystick driverStick;

    private Joystick operatorStick;

    private OI() {
        logger.info("constructing {}", myName);

        driverStick = new Joystick(0);

        operatorStick = new Joystick(1);

        configureButtonBindings();

        logger.info("constructed");
    }

    private void configureButtonBindings() {

    }

    public double getHopperSpeed() {
        return getDriverBumperAxis();
    }

    public double getIntakeSpeed() {
        return getDriverLeftBumper();
    }

    public double getDriveSpeed() {
        return getDriverLeftYAxis();
    }

    public double getDriveTurn() {
        return getDriverRightXAxis();
    }

    public double getBallevatorSpeed() {
        return getOperatorRightYAxis();
    }

    public double getTurretIncrement() {
        return getOperatorBumperAxis();
    }

    private double getDriverLeftYAxis() {
        return -driverStick.getRawAxis(1);
    }

    private double getDriverRightYAxis() {
        return -driverStick.getRawAxis(5);
    }

    private double getDriverLeftXAxis() {
        return driverStick.getRawAxis(0);
    }

    private double getDriverRightXAxis() {
        return driverStick.getRawAxis(4);
    }

    private double getDriverLeftBumper() {
        return driverStick.getRawAxis(2);
    }

    private double getDriverRightBumper() {
        return driverStick.getRawAxis(3);
    }

    private double getDriverBumperAxis() {
        if (driverStick.getRawAxis(2) > 0.05) {
            return driverStick.getRawAxis(2);
        } else if (driverStick.getRawAxis(3) > 0.05) {
            return -driverStick.getRawAxis(3);
        } else {
            return 0;
        }
    }

    private double getOperatorLeftYAxis() {
        return -operatorStick.getRawAxis(1);
    }

    private double getOperatorRightYAxis() {
        return -operatorStick.getRawAxis(5);
    }

    private double getOperatorLeftXAxis() {
        return operatorStick.getRawAxis(0);
    }

    private double getOperatorRightXAxis() {
        return operatorStick.getRawAxis(4);
    }

    private double getOperatorLeftBumper() {
        return operatorStick.getRawAxis(2);
    }

    private double getOperatorRightBumper() {
        return operatorStick.getRawAxis(3);
    }

    private double getOperatorBumperAxis() {
        if (operatorStick.getRawAxis(2) > 0.05) {
            return operatorStick.getRawAxis(2);
        } else if (operatorStick.getRawAxis(3) > 0.05) {
            return -operatorStick.getRawAxis(3);
        } else {
            return 0;
        }
    }

    @Override
    public void updateTelemetry() {
        // TODO Auto-generated method stub

    }

}