
package com.pay.national.agent.model.entity;

import java.io.Serializable;
import java.util.Date;

import com.pay.national.agent.model.enums.AppStatus;
import com.pay.national.agent.model.enums.AppType;
import com.pay.national.agent.model.enums.OperSys;


/**
 * @ClassName:  AppVersionInfo
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午5:08:46
 *
 */
public class AppVersionInfo implements Serializable{

	private static final long serialVersionUID = 8940490554823137540L;
	/*app版本号 */
	private String versionNo;
	/*此版本号及低于此版本号的应用需要更新 */
	private String minAppVersion;
	/*app操作系统 */
	private OperSys operSys;
	/*应用兼容的最低操作系统版本号 */
	private String minOsVersion;
	/*app下载页面 或 苹果商店地址 */
	private String appDownloadUrl;
	/*版本创建时间 */
	private Date createTime;
	/*版本修改时间 */
	private Date changeTime;
	/*版本升级的描述 */
	private  String description;
	/*安装包的大小 （Ｍ）*/
	private  String appSize;
	/*app版本状态  */
	private AppStatus appStatus;
	/*app类别  */
	private AppType appType;
	/*app下载次数  */
	private Integer downloadNum;
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public String getMinAppVersion() {
		return minAppVersion;
	}
	public void setMinAppVersion(String minAppVersion) {
		this.minAppVersion = minAppVersion;
	}
	public OperSys getOperSys() {
		return operSys;
	}
	public void setOperSys(OperSys operSys) {
		this.operSys = operSys;
	}
	public String getMinOsVersion() {
		return minOsVersion;
	}
	public void setMinOsVersion(String minOsVersion) {
		this.minOsVersion = minOsVersion;
	}
	public String getAppDownloadUrl() {
		return appDownloadUrl;
	}
	public void setAppDownloadUrl(String appDownloadUrl) {
		this.appDownloadUrl = appDownloadUrl;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getChangeTime() {
		return changeTime;
	}
	public void setChangeTime(Date changeTime) {
		this.changeTime = changeTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAppSize() {
		return appSize;
	}
	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}
	public AppStatus getAppStatus() {
		return appStatus;
	}
	public void setAppStatus(AppStatus appStatus) {
		this.appStatus = appStatus;
	}
	public AppType getAppType() {
		return appType;
	}
	public void setAppType(AppType appType) {
		this.appType = appType;
	}
	public Integer getDownloadNum() {
		return downloadNum;
	}
	public void setDownloadNum(Integer downloadNum) {
		this.downloadNum = downloadNum;
	}
	@Override
	public String toString() {
		return "AppVersionInfo [versionNo=" + versionNo + ", minAppVersion="
				+ minAppVersion + ", operSys=" + operSys + ", minOsVersion="
				+ minOsVersion + ", appDownloadUrl=" + appDownloadUrl
				+ ", createTime=" + createTime + ", changeTime=" + changeTime
				+ ", description=" + description + ", appSize=" + appSize
				+ ", appStatus=" + appStatus + ", appType=" + appType
				+ ", downloadNum=" + downloadNum + "]";
	}



}
