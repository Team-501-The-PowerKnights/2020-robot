/*----------------------------------------------------------------------------*/
/* Copyright (c) 2020 Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the 2020 Team 501 - The PowerKnights BSD license    */
/* file in the root directory of the project.                                 */
/*----------------------------------------------------------------------------*/

package frc.robot.modules.pdp;

import frc.robot.modules.IModule;

public interface IPDPModule extends IModule {

    public double getBusVoltage();

    public double getTotalCurrent();

    public double getTotalEnergy();

    public double getCurrent(int deviceID);

}
