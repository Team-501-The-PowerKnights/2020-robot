/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.ballevator;

import org.slf4j.Logger;

import riolog.RioLogger;

public class BallevatorDoNothing extends BallevatorCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BallevatorDoNothing.class.getName());

    public BallevatorDoNothing() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

}
