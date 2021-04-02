package com.make.serial.utils;

import com.make.serial.SerialHelper;

public class Constants {
    public static int select_screen_rotate_rgbcamera = 0;
    public static int select_screen_rotate_ircamera = 0;

    public static boolean FACE_SDK_RUN_STATE = false;

    /**
     * -1代表Auto
     */
    public static final int[] SCREEN_ROTATE = {-1, 0, 90, 180, 270};

    //public static int select_recognition_orientation = 0;
    //public static final int[] RECOGNITION_ORIENTATION = {-1, 0, 90, 180, 270};

    /**
     * Camera1识别方向翻转180
     */
    public static boolean recognition_overturn_rgbcamera = false;
    /**
     * Camera2识别方向翻转180
     */
    public static boolean recognition_overturn_ircamera = false;
    /**
     * 人脸框左右镜像
     */
    public static boolean face_frame_mirror = false;
    /**
     * RectF.dRectLeft 与 RectF.dRectRight 颠倒
     */
    public static boolean face_frame_reverse = false;

    public static String DEVICEPWD = "";

    public static String FIXED_PWD = "#29384756#";

    public static int SELF_INSPECTION_UNLOCK = 0;

    public static boolean HAS_MANAGEMENT = false;

    /**
     * 临时用户名前缀
     */
    public static String TEMP_USER_PREFIX = "temp";

    /**
     * 柜子空闲
     */
    public static int CABINETFREESTATE = 0;

    /**
     * 柜子占用
     */
    public static int CABINETUSESTATE = 1;

    public static String cabinetId = "";

    public static int Fid = 0;

    //---------指纹操作标识----------
    public static int FingerprintCmd;

    //--------------停止读流标识------
    public static boolean stopReadSream = false;

    public static String lockSerial = "/dev/ttyUSB0";
    public static String fingerSerial = "/dev/ttyUSB1";

    public static boolean SerialConfirm = false;

    public static SerialHelper serialHelperLock;
    public static SerialHelper serialHelperFinger;

    public static boolean NoReadThread = false;

    public static boolean openS = false;

    public static boolean fristConfigSerial = false;
    public static boolean fristConfigSerialR = false;

}
