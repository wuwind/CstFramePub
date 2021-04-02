package com.make.serial.utils;

public interface Constant {

    //密码最小长度
    public interface SysTemPwd{
        public int PWD_LENGTH_MIN = 6;
    }

    //管理员
    public interface SysManager{
        public int MANAGER_TYPE = 4;
        public int MANAGER_NUM_MAX = 10;//最大管理员数量
    }

    //用户
    public interface SysUser{
        public int USER_TYPE = 2;
        public int FIXED_USER_TYPE = 5;//固定用户
        public int TEMP_USER_TYPE = 6;//临时用户
        public int USER_NUM_MAX = 10000;//最大用户数量
    }

    public interface DeviceConfig{

    }

    //指纹模组
    public interface SerialPortConfig{
        public int BAUDRATE = 57600;
        public byte DATABIT = 8;
        public byte STOPBIT = 2;
        public byte PARITY = 0;
        public byte FLOWCONTROL = 0;
    }

    //锁控板
    public interface SerialPortLockConfig{
        public int BAUDRATE = 9600;
        public byte DATABIT = 8;
        public byte STOPBIT = 1;
        public byte PARITY = 0;
        public byte FLOWCONTROL = 0;

        public String SENDCMD = "8a0101119b";
        public String SENDCMD_ALL_OPEN = "8a0100119a";//全开板地址=01
        public String SENDCMD_ALL_OPEN_TWO = "8a02001199";//全开板地址=02
        public String LOCK_RESULTS = "8a0101008a";//8a0101e8
        public String LOCK_RESULTS_FAIL = "8a0101119b";
        public String LOCK_RESULTS_OPEN_SUCCESS = "8a0100119a";//全开响应
    }

    public interface ReadCardCmd{
        //轮询读卡
        public String READ_CARD_CMD = "01c80001c8";

        //无刷卡上报
        public String READ_CARD_NULL = "02c80000ca";//02c80000ca

        //有刷卡上报
        public String READ_CARD_DATA = "02c80002";//02c800020b4c418d43
    }

    public interface FingerprintCmd{
        public String SHAKE_HANDS = "ef01ffffffff010003350039";//握手指令
        public String TAKING_PICTURES = "ef01ffffffff010003010005";//采集图像、录入指纹
        public String FINGERPRINT_CHARACTERISTICS = "ef01ffffffff01000402";//生成指纹特征
        public String SYNTHETIC = "ef01ffffffff010003050009";//合成模板
        public String STORAGE = "ef01ffffffff0100060601";//存储模板

        public String CLEAN = "ef01ffffffff0100030d0011";//清空指纹库

        public String DUIBI = "ef01ffffffff010003030007";//精确比对两枚指纹特征

        public interface ValiFingerprintCmd{
            public String VALI_ONE = "ef01ffffffff010003350039";//握手指令
            public String VALI_TWO = "ef01ffffffff010003010005";//第二次采集图像
            public String VALI_THREE = "ef01ffffffff01000402010008";//bufferID为1
            public String VALI_FOUR = "ef01ffffffff0100080401000000630071";//搜索，bufferID为1
        }

        public interface getFingerprintImg{
            public String FINGERPRINT_IMG = "ef01ffffffff0100030b000f";//获取指纹图片
        }
    }

    public interface FingerprintResults{
        public String SHAKE_HANDS_RESULTS = "ef01ffffffff07000300000a";

        //传感器上无手指
        public String FINGERPRINT_NO = "ef01ffffffff07000302000c";
    }

    //0人脸;1指纹;2IC卡;3密码
    public interface IdentifyType{
        public int FACE_TYPE = 0;
        public int FINGERPRINT_TYPE = 1;
        public int IC_TYPE = 2;
        public int PASSWORD_TYPE = 3;
    }

}
