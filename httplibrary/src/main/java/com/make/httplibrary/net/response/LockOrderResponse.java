package com.make.httplibrary.net.response;

import com.make.httplibrary.net.Response;

import java.util.List;

/**
 * Created by wuhongfeng
 * data: 2021/4/7
 * desc:
 */
public class LockOrderResponse extends Response<List<LockOrderResponse.DataDTO>> {

    public static class DataDTO {
        private Integer id;
        private Integer userId;
        private Object projectId;
        private String deviceId;
        private String deviceName;
        private String productId;
        private Object productName;
        private Object deviceCode;
        private String deviceMac;
        private Object deviceModel;
        private Integer deviceType;
        private Integer status;
        private Object deviceChannel;
        private String manufacturerName;
        private String deviceLl;
        private String deviceAddress;
        private String createBy;
        private String createTime;
        private Integer delFlag;
        private Object remark;
        private Integer updateVersion;
        private Object username;
        private Object projectName;
        private Integer deviceNum;
        private Object createTimeFrom;
        private Object createTimeTo;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Object getProjectId() {
            return projectId;
        }

        public void setProjectId(Object projectId) {
            this.projectId = projectId;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public Object getProductName() {
            return productName;
        }

        public void setProductName(Object productName) {
            this.productName = productName;
        }

        public Object getDeviceCode() {
            return deviceCode;
        }

        public void setDeviceCode(Object deviceCode) {
            this.deviceCode = deviceCode;
        }

        public String getDeviceMac() {
            return deviceMac;
        }

        public void setDeviceMac(String deviceMac) {
            this.deviceMac = deviceMac;
        }

        public Object getDeviceModel() {
            return deviceModel;
        }

        public void setDeviceModel(Object deviceModel) {
            this.deviceModel = deviceModel;
        }

        public Integer getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(Integer deviceType) {
            this.deviceType = deviceType;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public Object getDeviceChannel() {
            return deviceChannel;
        }

        public void setDeviceChannel(Object deviceChannel) {
            this.deviceChannel = deviceChannel;
        }

        public String getManufacturerName() {
            return manufacturerName;
        }

        public void setManufacturerName(String manufacturerName) {
            this.manufacturerName = manufacturerName;
        }

        public String getDeviceLl() {
            return deviceLl;
        }

        public void setDeviceLl(String deviceLl) {
            this.deviceLl = deviceLl;
        }

        public String getDeviceAddress() {
            return deviceAddress;
        }

        public void setDeviceAddress(String deviceAddress) {
            this.deviceAddress = deviceAddress;
        }

        public String getCreateBy() {
            return createBy;
        }

        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public Integer getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(Integer delFlag) {
            this.delFlag = delFlag;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public Integer getUpdateVersion() {
            return updateVersion;
        }

        public void setUpdateVersion(Integer updateVersion) {
            this.updateVersion = updateVersion;
        }

        public Object getUsername() {
            return username;
        }

        public void setUsername(Object username) {
            this.username = username;
        }

        public Object getProjectName() {
            return projectName;
        }

        public void setProjectName(Object projectName) {
            this.projectName = projectName;
        }

        public Integer getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(Integer deviceNum) {
            this.deviceNum = deviceNum;
        }

        public Object getCreateTimeFrom() {
            return createTimeFrom;
        }

        public void setCreateTimeFrom(Object createTimeFrom) {
            this.createTimeFrom = createTimeFrom;
        }

        public Object getCreateTimeTo() {
            return createTimeTo;
        }

        public void setCreateTimeTo(Object createTimeTo) {
            this.createTimeTo = createTimeTo;
        }
    }
}
