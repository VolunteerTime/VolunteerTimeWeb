/**
 * Copyright (c) ����ũҵ��ѧ��ϢѧԺ�̳���2014��Ȩ����
 * 
 * �ļ�����ʱ�䣺2014-11-26
 */
package scau.info.volunteertime.vo;

/**
 * @author �̳���
 * 
 */
public class Version {
	private String versionName;// �汾����,�����ȶ԰汾,/WEB-INF/classes/
	// latest_version.properties�ļ��е�version_name��
	private int versionCode;// �汾�ţ������ȶ԰汾,/WEB-INF/classes/
	// latest_version.properties�ļ��е�version_number��
	private String versionTip;// �ð汾��ʾ������ܣ�,/WEB-INF/classes/
	// latest_version.properties�ļ��е�version_tip��
	private String downloadUrl;// �ð汾���ص�ַ, /WEB-INF/classes/

	// latest_version.properties�ļ��е�download_url��,��
	// ȡֵΪhttp://115.28.243.203/update/ding9android.apk
	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public int getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(int versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionTip() {
		return versionTip;
	}

	public void setVersionTip(String versionTip) {
		this.versionTip = versionTip;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
}
