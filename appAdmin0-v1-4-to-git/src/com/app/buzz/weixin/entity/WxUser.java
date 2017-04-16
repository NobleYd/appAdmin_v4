/*
 * Copyright 2015-2025. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.StringUtils;

import com.app.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微信关注者
 * 
 * @author APP TEAM
 * @version 1.0
 */
@Entity
@Table(name = "app_wx_user")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "app_wx_user_seq")
public class WxUser extends BaseEntity {

	private static final long serialVersionUID = -7285044558241754392L;

	public enum SubscribeStatus {
		unsubscribed(0, "未关注"), subscribed(1, "已关注");

		private int value;
		private String label;

		private SubscribeStatus(int value, String label) {
			this.value = value;
			this.label = label;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public static SubscribeStatus fromWx(String value) {
			if (value != null && "1".equals(value.trim())) {
				return SubscribeStatus.subscribed;
			} else {
				return SubscribeStatus.unsubscribed;
			}
		}

	}

	/** 是否关注 */
	private SubscribeStatus subscribeStatus;

	/** 标识ID */
	private String openid;

	/** 昵称 */
	private String nickname;

	public enum Sex {
		unknow(0, "未知"), male(1, "男"), female(2, "女");

		private int value;
		private String label;

		private Sex(int value, String label) {
			this.value = value;
			this.label = label;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getLabel() {
			return label;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public static Sex fromWx(String value) {
			if (value != null && "1".equals(value.trim())) {
				return Sex.male;
			} else if (value != null && "2".equals(value.trim())) {
				return Sex.female;
			} else {
				return Sex.unknow;
			}
		}

	}

	/** 性别 */
	private Sex sex;

	/** 语言 */
	private String language;

	/** 国家 */
	private String country;

	/** 省份 */
	private String province;

	/** 城市 */
	private String city;

	/** 头像URL */
	private String headimgurl;

	/** 关注时间 */
	private Long subscribe_time;

	/** 统一ID */
	private String unionid;

	/** 粉丝备注 */
	private String remark;

	/** 分组ID */
	private Long groupid;

	/** subscribe get */
	@Enumerated(EnumType.STRING)
	public SubscribeStatus getSubscribeStatus() {
		return subscribeStatus;
	}

	/** subscribe set */
	public void setSubscribeStatus(SubscribeStatus subscribeStatus) {
		this.subscribeStatus = subscribeStatus;
	}

	/** openid get */
	@JsonProperty
	public String getOpenid() {
		return openid;
	}

	/** openid set */
	public void setOpenid(String openid) {
		this.openid = openid;
	}

	/** nickname get */
	public String getNickname() {
		return nickname;
	}

	/** nickname set */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/** sex get */
	@Enumerated(EnumType.STRING)
	public Sex getSex() {
		return sex;
	}

	/** sex set */
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	/** language get */
	public String getLanguage() {
		return language;
	}

	/** language set */
	public void setLanguage(String language) {
		this.language = language;
	}

	/** country get */
	public String getCountry() {
		return country;
	}

	/** country set */
	public void setCountry(String country) {
		this.country = country;
	}

	/** province get */
	public String getProvince() {
		return province;
	}

	/** province set */
	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/** headimgurl get */
	public String getHeadimgurl() {
		return headimgurl;
	}

	@Transient
	public String getHeadimgurl_640x640() {
		return headimgurl;
	}

	@Transient
	public String getHeadimgurl_46x46() {
		return StringUtils.removeEnd(headimgurl, "/0") + "/46";
	}

	@Transient
	public String getHeadimgurl_64x64() {
		return StringUtils.removeEnd(headimgurl, "/0") + "/64";
	}

	@Transient
	public String getHeadimgurl_96x96() {
		return StringUtils.removeEnd(headimgurl, "/0") + "/96";
	}

	@Transient
	public String getHeadimgurl_132x132() {
		return StringUtils.removeEnd(headimgurl, "/0") + "/132";
	}

	/** headimgurl set */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	/** subscribe_time get */
	public Long getSubscribe_time() {
		return subscribe_time;
	}

	/** subscribe_time set */
	public void setSubscribe_time(Long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	/** unionid get */
	public String getUnionid() {
		return unionid;
	}

	/** unionid set */
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	/** remark get */
	public String getRemark() {
		return remark;
	}

	/** remark set */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/** groupid get */
	public Long getGroupid() {
		return groupid;
	}

	/** groupid set */
	public void setGroupid(Long groupid) {
		this.groupid = groupid;
	}

}
