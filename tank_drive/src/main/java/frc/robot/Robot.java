/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.commands.speedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
//iniciales es T = talon, segunda letra si es "front" o "rear" y tercera letra es "right" o "left"
  public static double speedMultiplier = 0.7;

  int id_control = 0,
    id_Tfr = 7,
    id_Trr  = 6,
    id_Tfl = 9,
    id_Trl = 8;

  PWMVictorSPX frontRight = new PWMVictorSPX(id_Tfr),
   rearRight = new PWMVictorSPX(id_Trr),
 frontleft = new PWMVictorSPX(id_Tfl),
rearLeft = new PWMVictorSPX(id_Trl);

  SpeedControllerGroup rigthtMotors = new SpeedControllerGroup(frontRight, rearRight);
  SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontleft, rearLeft);
  
  private DifferentialDrive drive;
  public static Joystick Control;
  Button triangleB = new JoystickButton(Control, 1 );
  Button SquareB= new JoystickButton(Control, 2 );
  
  @Override
  public void robotInit() {
    drive = new DifferentialDrive (rigthtMotors, leftMotors);
    Control = new Joystick(id_control);
  

  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousPeriodic() {
    }
  
    // return true; || Robot.speedMultiplier = valor deseado

  /**
   * This function is called periodically du
   * ring operator control.
   */
  public void teleopPeriodic() {
    drive.tankDrive(Control.getRawAxis(1) * (-speedMultiplier*.5), Control.getRawAxis(3) * speedMultiplier);
    triangleB.whenPressed(speedController.increment());
    SquareB.whenPressed(speedController.decrease());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }


}
