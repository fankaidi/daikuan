package com.kensure.mycom.user.model;

/**
 * 渠道定义枚举
 * 
 * @author fankd created on 2018-5-8
 * @since
 */
public class ChannelDefine {

	
	public final static String F_WEIXIN_APPID = "weixin_appid_";
	public final static String F_WEIXIN_SECRET = "weixin_secret_";

	private int id;

	private String appid;
	private String secret;

	// 构造方法
	public ChannelDefine(int id, String appid, String secret) {
		this.id = id;
		this.appid = appid;
		this.secret = secret;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}
	
}
