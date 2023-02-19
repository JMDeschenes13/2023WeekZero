// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;

/** Add your docs here. */
public class Constants {

    public static class DriveConstants{
        private static final double kTrackWidth = Units.inchesToMeters(19.25);
        // Distance between centers of right and left wheels on robot
        public static final double kWheelBase = Units.inchesToMeters(29);
        // Distance between front and back wheels on robot
        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
            new Translation2d(kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, -kTrackWidth / 2));
        //DriveMotors
        public static final int kFrontLeftDrivePort = 4;
        public static final int kFrontRightDrivePort = 3;
        public static final int kBackLeftDrivePort = 2;
        public static final int kBackRightDrivePort = 1;

        //Truning Motors
        public static final int kFrontLeftTurningPort = 0;// Motor 8
        public static final int kFrontRightTuringPort = 7;//Motor 4
        public static final int kBackLeftTurningPort = 6; // Motor 2
        public static final int kBackRightTurningPort = 5;// Motor 6

        //TurningEncoderChannels
        public static final int kFrontLeftEncoderChannelA = 6;
        public static final int kFrontLeftEncoderChannelB = 7;
        public static final int kFrontRightEncoderChannelA = 4;
        public static final int kFrontRightEncoderChannelB = 5;
        public static final int kBackLeftEncoderChannelA = 2;
        public static final int kBackLeftEncoderChannelB = 3;
        public static final int kBackRightEncoderChannelA = 0;
        public static final int kBackRightEncoderChannelB = 1;

        //Encoders inverted
        public static final boolean kFrontLeftEncoderInverted = false;
        public static final boolean kFrontRightEncoderInverted = false;
        public static final boolean kBackLeftEncoderInverted = false;
        public static final boolean kBackRightEncoderInverted = false;

        //Drive Motors Inverted
        public static final boolean kfrontLeftDriveMotorInverted = false;
        public static final boolean kfrontRightDriveMotorInverted = true;
        public static final boolean kbackLeftDriveMotorInverted = false;
        public static final boolean kbackRightDriveMotorInverted = true;

        //Turning Motors Inverted
        public static final boolean kfrontLeftTurningMotorInverted = false;
        public static final boolean kfrontRightTurningMotorInverted = false;
        public static final boolean kbackLeftTurningMotorInverted = false;
        public static final boolean kbackRightTurningMotorInverted = false;

    }

    public static final class ManipulatorConstants {

        public static final int PCMCANId = 20;

        //Claw
        public static final int kclawClosePort = 1;
        public static final int kclawOpenPort = 0;

        //Arm
        public static final int karmExtendPort = 2;
        public static final int karmRetractPort = 3;

        //Wrist
        public static final int kwristMotor = 11;
        public static final int kwristFollowerMotor = 12;
        public static final int kwristDriveRatio = 60;
        public static final double kwristSpeed = 2200;
        public static final double kwristAcceleration = 1200;
        public static final double kwristMaxOutputPercentage = 1;
        public static final int kwristEncoderThreshold = 15;
        public static final double kdriveEncoderPosition = (45*kwristDriveRatio*2048)/360;
        public static final double kbottomEncoderPosition = (20*kwristDriveRatio*2048)/360; // = (desiredAngle * driveRatio * countsPerRev)/360 = motorEncoderCounts
        public static final double kfeederStationEncoderPosition = (99*kwristDriveRatio*2048)/360;
        public static final double kmiddlePegEncoderPosition = (100*kwristDriveRatio*2048)/360;
        public static final double kmiddleShelfEncoderPosition = (90*kwristDriveRatio*2048)/360;
        public static final double kstartEncoderPosition = (35*kwristDriveRatio*2048)/360;
        public static final double ktopPegEncoderPosition = (120*kwristDriveRatio*2048)/360;
        public static final double ktopShelfEncoderPostion = (115*kwristDriveRatio*2048)/360;
        

       
    }

    public static final class AutoConstants {
        public static int kyInverted = 1;
        public static final double kLastPegFromOuterWall = 4.14975;
        public static final double kSecondToLastPegFromOuterWall = 2.97974;
        public static final double kFirstShelfToOuterWall = 1.04775;
        public static final double kSecondPegToOuterWall = 1.59226;
        public static final double kFirstPegToOuterWall = .422275;
        public static final double kLastGamePieceFromOuterWall = 4.57835;
        public static final double kDistanceBetweenFirstSecondPegs = kSecondPegToOuterWall-kFirstPegToOuterWall;
        public static final double kFirstGamepieceMetersFromWall = .92075;
        public static final double kAutoDriveC1DeltaY = kFirstGamepieceMetersFromWall - kSecondPegToOuterWall;
        public static final double kAutoDriveC2DeltaY = kFirstPegToOuterWall - kFirstGamepieceMetersFromWall;
        public static final double kAutoDriveE1DeltaY = kLastGamePieceFromOuterWall - kSecondToLastPegFromOuterWall;
        public static final double kAutoDriveE2DeltaY = kLastPegFromOuterWall - kLastGamePieceFromOuterWall;
        public static final double kMaxSpeedMetersPerSecond = 2;
        public static final double kMaxAccelerationMetersPerSecondSquared = 2;
        public static final double kMaxAngularSpeedRadiansPerSecond = Math.PI/2;
        public static final double kMaxAngularSpeedRadiansPerSecondSquared = Math.PI;
    
        public static final double kPXController = 4;
        public static final double kPYController = 4;
        public static final double kPThetaController = 1;
    
        // Constraint for the motion profiled robot angle controller
        public static final TrapezoidProfile.Constraints kThetaControllerConstraints = new TrapezoidProfile.Constraints(
            kMaxAngularSpeedRadiansPerSecond, kMaxAngularSpeedRadiansPerSecondSquared);
      }

    public static final class EncoderConstants{

        /**
         * Which PID slot to pull gains from. Starting 2018, you can choose from
         * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
         * configuration.
         */
	    public static final int kSlotIdx = 0;

        /*
        * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
        * now we just want the primary one.
        */
	    public static final int kPIDLoopIdx = 0;

        /*
        * set to zero to skip waiting for confirmation, set to nonzero to wait and
        * report to DS if action fails.
        */
	    public static final int kTimeoutMs = 30;

    }

    //OI Constants
    public static final class OIConstants {

        //Controllers
        public static final int kdriveJoystick = 0;
        public static final int kactuatorJoystick = 1;

    }    

    
}
