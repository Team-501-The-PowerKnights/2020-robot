/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.sensors.vision;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.properties.PKProperties;
import frc.robot.properties.PropertiesManager;
import frc.robot.sensors.SensorNames;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.RioLogger;

/**
 * 
 */
public class VisionFactory {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(VisionFactory.class.getName());

    /** Singleton instance of class for all to use **/
    private static IVisionSensor ourInstance;
    /** Name of our subsystem **/
    private static final String myName = SensorNames.visionName;

    /**
     * Constructs instance of the subsystem. Assumed to be called before any usage
     * of the sensor; and verifies only called once. Allows controlled startup
     * sequencing of the robot and all it's sensors.
     **/
    public static synchronized void constructInstance() {
        SmartDashboard.putNumber(TelemetryNames.Vision.status, PKStatus.inProgress.tlmValue);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " Already Constructed");
        }

        PKProperties props = PropertiesManager.getInstance().getProperties(myName);
        props.listProperties();

        loadImplementationClass(props.getString("className"));
    }

    private static void loadImplementationClass(String myClassName) {
        String myPkgName = VisionFactory.class.getPackage().getName();
        String classToLoad = new StringBuilder().append(myPkgName).append(".").append(myClassName).toString();
        logger.debug("class to load {}", classToLoad);

        logger.info("constructing {} for {} sensor", myClassName, myName);
        try {
            @SuppressWarnings("rawtypes")
            Class myClass = Class.forName(classToLoad);
            @SuppressWarnings("deprecation")
            Object myObject = myClass.newInstance();
            ourInstance = (IVisionSensor) myObject;
            SmartDashboard.putNumber(TelemetryNames.Vision.status, PKStatus.success.tlmValue);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("failed to load class; instantiating default stub for {}", myName);
            ourInstance = new StubVisionSensor();
            SmartDashboard.putNumber(TelemetryNames.Vision.status, PKStatus.degraded.tlmValue);
        }
    }

    /**
     * Returns the singleton instance of the sensor in the form of the
     * <i>Interface</i> that is defined for it. If it hasn't been constructed yet,
     * throws an <code>IllegalStateException</code>.
     *
     * @return singleton instance of sensor
     **/
    public synchronized static IVisionSensor getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " Not Constructed Yet");
        }

        return ourInstance;
    }

}
