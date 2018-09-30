package cn.jasgroup.jasframework.app.appversion.service;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * description: App版本实体 + BO
 *
 * @author xiefayang
 * 2018/9/30 10:39
 */
public class AppVersionBo {

    /** ID */
    private String oid;

    /** 版本号 */
    private String versionNumber;

    /** 版本描述 */
    private String versionDesc;

    /** 发型时间 */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date versionTime;

    /** AppCan version */
    private String appcanVersion;

    /** 产品标识 */
    private String productId;

    /** 产品名称 */
    private String productName;

    /** 更新模式 */
    private Integer updateModel;

    /** 下载地址 */
    private String url;

    /** 客户端类型 */
    private String clientType;

    /** 有效标识 */
    private Integer active;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }

    public String getVersionDesc() {
        return versionDesc;
    }

    public void setVersionDesc(String versionDesc) {
        this.versionDesc = versionDesc;
    }

    public Date getVersionTime() {
        return versionTime;
    }

    public void setVersionTime(Date versionTime) {
        this.versionTime = versionTime;
    }

    public String getAppcanVersion() {
        return appcanVersion;
    }

    public void setAppcanVersion(String appcanVersion) {
        this.appcanVersion = appcanVersion;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getUpdateModel() {
        return updateModel;
    }

    public void setUpdateModel(Integer updateModel) {
        this.updateModel = updateModel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public Integer getActive() {
        return active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }
}
