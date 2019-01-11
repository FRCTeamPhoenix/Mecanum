/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends IterativeRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  Joystick stick;
  WPI_TalonSRX talonFR;
  WPI_TalonSRX talonFL;
  WPI_TalonSRX talonBR;
  WPI_TalonSRX talonBL;

  public Robot() {
    // this.drive = new MecanumDrivetrain(1, 2, 3, 4);
    talonBL = new WPI_TalonSRX(1);
    talonBR = new WPI_TalonSRX(2);
    talonFL = new WPI_TalonSRX(3);
    talonFR = new WPI_TalonSRX(4);
    this.stick = new Joystick(0);
  }

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    SmartDashboard.putData("Auto choices", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString line to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure below with additional strings. If using the SendableChooser
   * make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
    case kCustomAuto:
      // Put custom auto code here
      break;
    case kDefaultAuto:
    default:
      // Put default auto code here
      break;
    }
  }

  @Override
  public void teleopInit() {
    talonFL.setSelectedSensorPosition(0);
    talonFR.setSelectedSensorPosition(0);
    talonBR.setSelectedSensorPosition(0);
    talonBL.setSelectedSensorPosition(0);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    // drive.drive(stick);
    double inX = stick.getRawAxis(0);
    double inY = -stick.getRawAxis(1);
    double rotation = -stick.getRawAxis(4);

    double speedDenominator = 1.0;
    talonFL.set(ControlMode.PercentOutput,  -(inX + inY + rotation) / speedDenominator);
    talonBR.set(ControlMode.PercentOutput,  (inX + inY - rotation) / speedDenominator);
    talonFR.set(ControlMode.PercentOutput,  (inY - inX - rotation) / speedDenominator);
    talonBL.set(ControlMode.PercentOutput,  -(inY - inX + rotation) / speedDenominator);
    /*talonFL.set(ControlMode.PercentOutput,  0.5);
    talonBR.set(ControlMode.PercentOutput,  -0.5);
    talonFR.set(ControlMode.PercentOutput,  -0.5);
    talonBL.set(ControlMode.PercentOutput,  0.5);*/

    SmartDashboard.putString("DB/String 0", ""+(inX + inY + rotation) / 4.0);
    SmartDashboard.putString("DB/String 1", ""+ -(inX + inY - rotation) / 4.0);
    SmartDashboard.putString("DB/String 2", ""+ -(inY - inX - rotation) / 4.0);
    SmartDashboard.putString("DB/String 3", ""+(inY - inX + rotation) / 4.0);

    SmartDashboard.putString("DB/String 5", ""+inX);
    SmartDashboard.putString("DB/String 6", ""+inY);
    SmartDashboard.putString("DB/String 7", ""+rotation);
    // talonFR.set(ControlMode.PercentOutput, stick.getRawAxis(4));
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
