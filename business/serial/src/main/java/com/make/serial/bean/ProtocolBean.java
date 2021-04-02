package com.make.serial.bean;

public class ProtocolBean {
//    命令字	锁控板地址	锁编号	数据N	功能码	校验字节

    private int cmd;
    private int address;
    private int number;
    private int dataN;
    private int function;
    private int checkCode;

    public ProtocolBean() {
    }

    public ProtocolBean(byte[] data) {
        if (null != data && data.length >= 5) {
            cmd = data[0];
            address = data[1];
            number = data[2];
            function = data[3];
            checkCode = data[4];
        }
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getDataN() {
        return dataN;
    }

    public void setDataN(int dataN) {
        this.dataN = dataN;
    }

    public int getFunction() {
        return function;
    }

    public void setFunction(int function) {
        this.function = function;
    }

    public int getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(int checkCode) {
        this.checkCode = checkCode;
    }

    public byte[] getDataArray() {
        byte[] data = new byte[5];
        data[0] = (byte) cmd;
        data[1] = (byte) address;
        data[2] = (byte) number;
        data[3] = (byte) function;
        data[4] = (byte) (data[0] ^ data[1] ^ data[2] ^ data[3]);
        return data;
    }

    @Override
    public String toString() {
        return "ProtocalBean{" +
                "cmd=" + cmd +
                ", address=" + address +
                ", number=" + number +
                ", dataN=" + dataN +
                ", function=" + function +
                ", checkCode=" + checkCode +
                '}';
    }
}
