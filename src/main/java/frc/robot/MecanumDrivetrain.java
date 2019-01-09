/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * Add your docs here.
 */
public class MecanumDrivetrain {

    RobotDrive mecanumDrive;

    public MecanumDrivetrain(int talonFR, int talonFL, int talonBR, int talonBL) {
        this.mecanumDrive = new RobotDrive(talonFR, talonFL, talonBR, talonBL);
    }

    public void drive(Joystick stick) {
        mecanumDrive.mecanumDrive_Cartesian(stick.getX(), stick.getY(), stick.getZ(), 0);
    }
    
}
