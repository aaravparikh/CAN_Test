// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import com.ctre.phoenix.motorcontrol.can.*;


/**
 * This is a demo program showing the use of the DifferentialDrive class, specifically it contains
 * the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private DifferentialDrive m_myRobot;
  private Joystick m_leftStick;
  private Joystick m_rightStick;

  private JoystickButton b_break1;
  private JoystickButton b_break2;
  private JoystickButton b_boost1;
  private JoystickButton b_boost2;

  private final WPI_TalonSRX m_rightRearMotor = new WPI_TalonSRX(05);
  private final WPI_TalonSRX m_rightFrontMotor = new WPI_TalonSRX(01);
  private final WPI_TalonSRX m_leftRearMotor = new WPI_TalonSRX(07);
  private final WPI_TalonSRX m_leftFrontMotor = new WPI_TalonSRX(0);

  private final MotorControllerGroup g_rightGroup = new MotorControllerGroup(m_rightFrontMotor, m_rightRearMotor);
  private final MotorControllerGroup g_leftGroup = new MotorControllerGroup(m_leftFrontMotor, m_leftRearMotor);

  @Override
  public void robotInit() {
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    g_leftGroup.setInverted(true);

  

    m_myRobot = new DifferentialDrive(g_rightGroup, g_leftGroup);
    m_leftStick = new Joystick(1);
    m_rightStick = new Joystick(0);
    b_break1 = new JoystickButton(m_leftStick, 1);
    b_break2 = new JoystickButton(m_rightStick, 1);
    b_boost1 = new JoystickButton(m_leftStick, 2);
    b_boost2 = new JoystickButton(m_rightStick,2);


  }

  @Override
  public void teleopPeriodic() {
    if(b_break1.get()==true || b_break2.get()==true){
    m_myRobot.arcadeDrive(0,0);

    } else if(b_boost1.get()==true || b_boost2.get()==true){
      m_myRobot.arcadeDrive(m_leftStick.getY(), m_leftStick.getZ());

    } else {
      m_myRobot.arcadeDrive(m_leftStick.getY()*0.8, m_leftStick.getZ()*0.8);

    }
  }
}
