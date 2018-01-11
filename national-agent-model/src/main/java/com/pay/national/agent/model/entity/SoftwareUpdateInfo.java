/**
 *
 */
package com.pay.national.agent.model.entity;

/**
 * @ClassName:  SoftwareUpdateInfo
 * @Description:软件更新情况信息
 * @author: xiaodi.fu
 * @date:   2017年9月11日 下午6:05:21
 *
 */
public class SoftwareUpdateInfo {
	/**最新版本*/
	private String latestVersion;
	/**可运行的最低版本*/
	private String minVersion;
	/**更新地址*/
	private String updateURL;
	public String getLatestVersion() {
		return latestVersion;
	}
	public void setLatestVersion(String latestVersion) {
		this.latestVersion = latestVersion;
	}
	public String getMinVersion() {
		return minVersion;
	}
	public void setMinVersion(String minVersion) {
		this.minVersion = minVersion;
	}
	public String getUpdateURL() {
		return updateURL;
	}
	public void setUpdateURL(String updateURL) {
		this.updateURL = updateURL;
	}
	@Override
	public String toString() {
		return "SoftwareUpdateInfo [latestVersion=" + latestVersion
				+ ", minVersion=" + minVersion + ", updateURL=" + updateURL
				+ "]";
	}


}
