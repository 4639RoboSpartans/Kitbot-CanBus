// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import javax.swing.plaf.TreeUI;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This is a demo program showing the use of the DifferentialDrive class. Runs the motors with
 * arcade steering.
 */
public class Robot extends TimedRobot {
  private final WPI_VictorSPX m_frontleftMotor = new WPI_VictorSPX(1);
  private final WPI_VictorSPX m_rearleftMotor = new WPI_VictorSPX(2);
  private final WPI_VictorSPX m_frontrightMotor = new WPI_VictorSPX(4);
  private final WPI_VictorSPX m_rearrightMotor = new WPI_VictorSPX(3);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_frontleftMotor, m_frontrightMotor);
  private final Joystick m_stick = new Joystick(0);
	public final Encoder m_LeftEncoder = new Encoder(1,0);
	public final Encoder m_RightEncoder = new Encoder(4,5);
	private static final double diameter = 6.00;

   /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    //m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    //m_chooser.addOption("My Auto", kCustomAuto);
    //SmartDashboard.putData("Auto choices", m_chooser);
    
    /* Ensure motor output is neutral during init  */
		m_frontleftMotor.set(ControlMode.PercentOutput, 0);
    m_rearleftMotor.set(ControlMode.PercentOutput, 0);
    m_frontrightMotor.set(ControlMode.PercentOutput, 0);
    m_rearrightMotor.set(ControlMode.PercentOutput, 0);
		
		/* Factory Default all hardware to prevent unexpected behaviour */
		m_frontleftMotor.configFactoryDefault();
		m_rearleftMotor.configFactoryDefault();
		m_frontrightMotor.configFactoryDefault();
		m_rearrightMotor.configFactoryDefault();
		
		/* Set Neutral mode */
		m_frontleftMotor.setNeutralMode(NeutralMode.Brake);
		m_rearleftMotor.setNeutralMode(NeutralMode.Brake);
		m_frontrightMotor.setNeutralMode(NeutralMode.Brake);
		m_rearrightMotor.setNeutralMode(NeutralMode.Brake);
				
		/* Configure output direction */
    m_frontleftMotor.setInverted(true);
		m_rearleftMotor.setInverted(true);
		m_frontrightMotor.setInverted(true);
		m_rearrightMotor.setInverted(true);

    m_rearleftMotor.follow(m_frontleftMotor);
    m_rearrightMotor.follow(m_frontrightMotor);

    m_LeftEncoder.setDistancePerPulse(diameter * Math.PI / 2048.0);
    m_RightEncoder.setDistancePerPulse(diameter * Math.PI / 2048.0);
    m_LeftEncoder.reset();
		m_RightEncoder.reset();
  }

  @Override
  public void teleopPeriodic() {
    // Drive with arcade drive.
    // That means that the Y axis drives forward
    // and backward, and the X turns left and right.

    m_robotDrive.arcadeDrive(m_stick.getY(), m_stick.getX());
    SmartDashboard.putNumber("LEncoder ", m_LeftEncoder.getDistance());
		SmartDashboard.putNumber("REncoder ", m_RightEncoder.getDistance());
	
  }
}
