/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.util;

import java.io.File;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.app.buzz.weixin.config.WeiXinConfig;
import com.app.buzz.weixin.constants.WeiXinURL;
import com.app.buzz.weixin.entity.Material;
import com.app.buzz.weixin.entity.WxUser;
import com.app.buzz.weixin.entity.WxUser.Sex;
import com.app.buzz.weixin.entity.WxUser.SubscribeStatus;
import com.app.buzz.weixin.entity.WxUserGroup;
import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageBody;
import com.app.buzz.weixin.message.event.ClickEventMessage;
import com.app.buzz.weixin.message.event.LocationEventMessage;
import com.app.buzz.weixin.message.event.ScanEventMessage;
import com.app.buzz.weixin.message.event.SubscribeEventMessage;
import com.app.buzz.weixin.message.event.SubscribeScanEventMessage;
import com.app.buzz.weixin.message.event.UnSubscribeEventMessage;
import com.app.buzz.weixin.message.event.ViewEventMessage;
import com.app.buzz.weixin.message.normal.ImageMessage;
import com.app.buzz.weixin.message.normal.LinkMessage;
import com.app.buzz.weixin.message.normal.LocationMessage;
import com.app.buzz.weixin.message.normal.ShortVideoMessage;
import com.app.buzz.weixin.message.normal.TextMessage;
import com.app.buzz.weixin.message.normal.VideoMessage;
import com.app.buzz.weixin.message.normal.VoiceMessage;
import com.app.util.JacksonUtils;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

/**
 * Utils - WEI XIN
 * 
 * @author APP TEAM
 * @version 1.0
 */
public final class WeiXinUtils {

	private static Log log = LogFactory.getLog(WeiXinUtils.class);

	// 签名验证
	public static Boolean checkSignature(String token, String signature, String timestamp, String nonce) {
		if (signature == null || timestamp == null || nonce == null)
			return false;
		// sort
		String[] arr = new String[] { token, timestamp, nonce };
		Arrays.sort(arr);
		StringBuffer sf = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sf.append(arr[i]);
		}
		// sha1加密
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA");
			// 执行摘要方法
			byte[] digest = md.digest(sf.toString().getBytes());
			String encriptStr = new HexBinaryAdapter().marshal(digest);
			if (encriptStr.equalsIgnoreCase(signature)) {
				return true;
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 消息解析步骤1：
	 * 
	 * @param xmlContent
	 *            要解析的xml字符串
	 */
	public static Message parseMessage(String xmlContent) {
		if (xmlContent == null)
			return null;
		List<Converter> converters = new ArrayList<Converter>();
		converters.add(new Converter() {
			@Override
			public boolean canConvert(Class clazz) {
				if (clazz.equals(Double.class))
					return true;
				return false;
			}

			@Override
			public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
				if (reader.getValue() != null && !reader.getValue().isEmpty()) {
					/** 如果不满足条件直接返回null，如果转换过程出现异常也直接返回null。 */
					/** 注意这里这么做的原因是，Double类型转换的时候如果是空字符串会报错，导致整个xml转换出错。 */
					try {
						Double ret = Double.parseDouble(reader.getValue());
						return ret;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return null;
			}

			@Override
			public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context) {

			}
		});
		converters.add(new Converter() {
			@Override
			public boolean canConvert(Class clazz) {
				if (clazz.equals(Long.class))
					return true;
				return false;
			}

			@Override
			public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
				if (reader.getValue() != null && !reader.getValue().isEmpty()) {
					/** 如果不满足条件直接返回null，如果转换过程出现异常也直接返回null。 */
					/** 注意这里这么做的原因是，Double类型转换的时候如果是空字符串会报错，导致整个xml转换出错。 */
					try {
						Long ret = Long.parseLong(reader.getValue());
						return ret;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return null;
			}

			@Override
			public void marshal(Object obj, HierarchicalStreamWriter writer, MarshallingContext context) {

			}
		});
		MessageBody messageBody = XmlUtils.xml2Object(xmlContent, MessageBody.class, "xml", converters);
		if (messageBody == null)
			return null;
		return parseMessage(messageBody, xmlContent);
	}

	/***
	 * 消息解析步骤2： 如果能够识别类型，则返回一个具体的类型。 否则返回原始Message即可。
	 * 
	 * @param messageBody
	 *            待识别的message。
	 * @param xmlContent
	 *            原始xml数据。
	 * @return 如果能够识别类型，则返回一个具体的类型。 否则返回原始Message即可。
	 */
	public static Message parseMessage(MessageBody messageBody, String xmlContent) {
		/** 文本消息 */
		if (messageBody.isTextMessage()) {
			return new TextMessage(messageBody);
		} else if (messageBody.isImageMessage()) {
			return new ImageMessage(messageBody);
		} else if (messageBody.isVoiceMessage()) {
			return new VoiceMessage(messageBody);
		} else if (messageBody.isVideoMessage()) {
			return new VideoMessage(messageBody);
		} else if (messageBody.isShortVideoMessage()) {
			return new ShortVideoMessage(messageBody);
		} else if (messageBody.isLocationMessage()) {
			return new LocationMessage(messageBody);
		} else if (messageBody.isLinkMessage()) {
			return new LinkMessage(messageBody);
		} else if (messageBody.isSubscribeEvent()) {
			return new SubscribeEventMessage(messageBody);
		} else if (messageBody.isUnSubscribeEvent()) {
			return new UnSubscribeEventMessage(messageBody);
		} else if (messageBody.isSubscribeScanEvent()) {
			return new SubscribeScanEventMessage(messageBody);
		} else if (messageBody.isScanEvent()) {
			return new ScanEventMessage(messageBody);
		} else if (messageBody.isLocationEvent()) {
			return new LocationEventMessage(messageBody);
		} else if (messageBody.isClickEvent()) {
			return new ClickEventMessage(messageBody);
		} else if (messageBody.isViewEvent()) {
			return new ViewEventMessage(messageBody);
		}

		/** 如果不符合任何的类型，则直接构造一个Message返回。 */
		return new Message(messageBody);
	}

	/***
	 * 新增素材
	 * 
	 * @param weiXinConfig
	 * @param material
	 * @return 是否新增成功
	 */
	// 请仔细观察微信返回数据的格式，不要随便猜想。
	// 临时图片素材
	// {media_id=MEDIA_ID, created_at=1461936897, type=image}
	// 临时语音素材
	// {media_id=MEDIA_ID, created_at=1461936972, type=voice}
	// 临时视频素材
	// {media_id=MEDIA_ID, created_at=1461937059, type=video}
	// 临时缩略图素材
	// {thumb_media_id=MEDIA_ID, created_at=1461937109, type=thumb}
	// 永久图片素材
	// {media_id=MEDIA_ID, url=URL}
	// 永久语音素材
	// {media_id=MEDIA_ID}
	// 永久视频素材
	// {media_id=MEDIA_ID}
	// 永久缩略图素材
	// {media_id=MEDIA_ID, url=URL}
	public static boolean addMaterial(final WeiXinConfig weiXinConfig, final Material material) {
		if (material == null || material.getType() == null || material.getMaterialType() == null || material.getMedia() == null) {
			return false;
		}
		// 构建需要上传的文件
		final File mediaFile = new File(System.getProperty("java.io.tmpdir") + "/upload_" + UUID.randomUUID() + "." + FilenameUtils.getExtension(material.getMedia()));
		try {
			try {
				FileUtils.copyURLToFile(new URL(material.getMedia()), mediaFile);
			} catch (Exception e) {
				log.error("WeiXinUtils.addMaterial() error when copy url [ " + material.getMedia() + " ] to tmpFile. ");
				return false;
			}
			// 验证是否成功拿到文件
			if (!mediaFile.exists()) {
				log.error("WeiXinUtils.addMaterial() error mediaFile is not exist.");
				return false;
			}
			// 存在，继续。
			if (Material.Type.temporary.equals(material.getType())) {
				// 新增临时素材
				Map<String, Object> jsonMap = WeiXinHttpUtils.postForJson(WeiXinURL.post_add_temporary_material, weiXinConfig, new HashMap<String, String>() {
					{
						put(WeiXinURL.TYPE, material.getMaterialType().toString());
					}
				}, null, new HashMap<String, File>() {
					{
						put("media", mediaFile);
					}
				});
				if (jsonMap.containsKey("media_id")) {
					// example: {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
					log.info("WeiXinUtils.addMaterial() info. jsonMap = " + jsonMap.toString());
					material.setMedia_id(jsonMap.get("media_id").toString().trim());
					return true;
				} else if (jsonMap.containsKey("thumb_media_id") && Material.MaterialType.thumb.equals(material.getMaterialType())) {
					// 临时缩略图素材返回的是thumb_media_id
					// example: {"type":"TYPE","thumb_media_id":"MEDIA_ID","created_at":123456789}
					log.info("WeiXinUtils.addMaterial() info. jsonMap = " + jsonMap.toString());
					material.setMedia_id(jsonMap.get("thumb_media_id").toString().trim());
					return true;
				}

				if (jsonMap.containsKey("errcode")) {
					// example: {"errcode":40004,"errmsg":"invalid media type"}
					log.error("WeiXinUtils.addMaterial() error. jsonMap = " + jsonMap.toString());
				} else {
					// unknown
					log.error("WeiXinUtils.addMaterial() unknown error. jsonMap = " + jsonMap.toString());
				}
			} else if (Material.Type.permanent.equals(material.getType()) && (!Material.MaterialType.video.equals(material.getMaterialType()))) {
				// 新增非视频永久素材
				Map<String, Object> jsonMap = WeiXinHttpUtils.postForJson(WeiXinURL.post_add_permanent_material, weiXinConfig, new HashMap<String, String>() {
					{
						put(WeiXinURL.TYPE, material.getMaterialType().toString());
					}
				}, null, new HashMap<String, File>() {
					{
						put("media", mediaFile);
					}
				});
				if (jsonMap.containsKey("media_id")) {
					// example: {"media_id":"MEDIA_ID"}
					log.info("WeiXinUtils.addMaterial() info. jsonMap = " + jsonMap.toString());
					material.setMedia_id(jsonMap.get("media_id").toString().trim());
					// 永久图片素材和永久缩略图素材 example: {"media_id":"MEDIA_ID", url=URL}
					if (jsonMap.containsKey("url")) {
						// 这个是图片类型相比其他类型的特殊返回数据
						material.setUrl(jsonMap.get("url").toString().trim());
					}
					return true;
				}

				if (jsonMap.containsKey("errcode")) {
					// example: {"errcode":40004,"errmsg":"invalid media type"}
					log.error("WeiXinUtils.addMaterial() error. jsonMap = " + jsonMap.toString());
				} else {
					// unknown
					log.error("WeiXinUtils.addMaterial() unknown error. jsonMap = " + jsonMap.toString());
				}
			} else if (Material.Type.permanent.equals(material.getType()) && Material.MaterialType.video.equals(material.getMaterialType())) {
				// 新增视频永久素材{ 上传有特殊参数 }
				Map<String, Object> jsonMap = WeiXinHttpUtils.postForJson(WeiXinURL.post_add_permanent_material, weiXinConfig, new HashMap<String, String>() {
					{
						put(WeiXinURL.TYPE, material.getMaterialType().toString());
					}
				}, new HashMap<String, Object>() {
					{
						// 这个是视频上传相比其他类型的特殊参数
						put("description", JacksonUtils.toJson(new HashMap<String, String>() {
							{
								put("title", material.getTitle() != null ? material.getTitle() : "");
								put("introduction", material.getIntroduction() != null ? material.getIntroduction() : "");
							}
						}));
					}
				}, new HashMap<String, File>() {
					{
						put("media", mediaFile);
					}
				});
				if (jsonMap.containsKey("media_id")) {
					// example: {"media_id":"MEDIA_ID"}
					log.info("WeiXinUtils.addMaterial() info. jsonMap = " + jsonMap.toString());
					material.setMedia_id(jsonMap.get("media_id").toString().trim());
					return true;
				} else if (jsonMap.containsKey("errcode")) {
					// example: {"errcode":40004,"errmsg":"invalid media type"}
					log.error("WeiXinUtils.addMaterial() error. jsonMap = " + jsonMap.toString());
				} else {
					// unknown
					log.error("WeiXinUtils.addMaterial() unknown error. jsonMap = " + jsonMap.toString());
				}
			}
		} finally {
			FileUtils.deleteQuietly(mediaFile);
		}

		return false;
	}

	// 创建用户分组
	public static boolean addWxUserGroup(final WeiXinConfig weiXinConfig, final WxUserGroup wxUserGroup) {
		Map<String, Object> jsonMap = WeiXinHttpUtils.postForJson(WeiXinURL.post_create_group, weiXinConfig, null, //
				JacksonUtils.toJson(new HashMap<String, Object>() {
					{
						put("group", (new HashMap<String, Object>() {
							{
								put("name", wxUserGroup.getName());
							}
						}));
					}
				})//
		);
		if (jsonMap.containsKey("group")) {
			log.info("WeiXinUtils.addWxUserGroup() info. jsonMap = " + jsonMap.toString());
			wxUserGroup.setGroupId(Long.valueOf(((HashMap<String, Object>) jsonMap.get("group")).get("id").toString()));
			return true;
		} else if (jsonMap.containsKey("errcode")) {
			// example: {"errcode":40013,"errmsg":"invalid appid"}
			log.error("WeiXinUtils.addWxUserGroup() error. jsonMap = " + jsonMap.toString());
		} else {
			// unknown
			log.error("WeiXinUtils.addWxUserGroup() unknown error. jsonMap = " + jsonMap.toString());
		}
		return false;
	}

	// 更新用户分组（名）
	public static boolean updateWxUserGroup(final WeiXinConfig weiXinConfig, final WxUserGroup wxUserGroup) {
		Map<String, Object> jsonMap = WeiXinHttpUtils.postForJson(WeiXinURL.post_update_group, weiXinConfig, null, //
				JacksonUtils.toJson(new HashMap<String, Object>() {
					{
						put("group", (new HashMap<String, Object>() {
							{
								put("id", wxUserGroup.getGroupId());
								put("name", wxUserGroup.getName());
							}
						}));
					}
				})//
		);
		if (jsonMap.containsKey("errcode")) {
			if ("0".equals(jsonMap.get("errcode").toString().trim())) {
				// 成功
				log.info("WeiXinUtils.updateWxUserGroup() info. jsonMap = " + jsonMap.toString());
				return true;
			} else {
				// 失败
				log.error("WeiXinUtils.updateWxUserGroup() error. jsonMap = " + jsonMap.toString());
				return false;
			}
		} else {
			// unknown
			log.error("WeiXinUtils.updateWxUserGroup() unknown error. jsonMap = " + jsonMap.toString());
		}
		return false;
	}

	// 删除用户分组
	public static boolean deleteWxUserGroup(final WeiXinConfig weiXinConfig, final Long wxUserGroupId) {
		Map<String, Object> jsonMap = WeiXinHttpUtils.postForJson(WeiXinURL.post_delete_group, weiXinConfig, null, //
				JacksonUtils.toJson(new HashMap<String, Object>() {
					{
						put("group", (new HashMap<String, Object>() {
							{
								put("id", wxUserGroupId);
							}
						}));
					}
				})//
		);
		if (jsonMap.containsKey("errcode")) {
			if ("0".equals(jsonMap.get("errcode").toString().trim())) {
				// 成功
				log.info("WeiXinUtils.deleteWxUserGroup() info. jsonMap = " + jsonMap.toString());
				return true;
			} else {
				// 失败
				log.error("WeiXinUtils.deleteWxUserGroup() error. jsonMap = " + jsonMap.toString());
				return false;
			}
		} else {
			// unknown
			log.error("WeiXinUtils.deleteWxUserGroup() unknown error. jsonMap = " + jsonMap.toString());
		}
		return false;
	}

	public static boolean moveWxUser2Group(WeiXinConfig weiXinConfig, final WxUser wxUser) {
		Map<String, Object> jsonMap = WeiXinHttpUtils.postForJson(WeiXinURL.post_move_user2group, weiXinConfig, null, //
				JacksonUtils.toJson(new HashMap<String, Object>() {
					{
						put("openid", wxUser.getOpenid());
						put("to_groupid", wxUser.getGroupid());
					}
				}));
		if (jsonMap.containsKey("errcode")) {
			if ("0".equals(jsonMap.get("errcode").toString().trim())) {
				// 成功
				log.info("WeiXinUtils.moveWxUser2Group() info. jsonMap = " + jsonMap.toString());
				return true;
			} else {
				// 失败
				log.error("WeiXinUtils.moveWxUser2Group() error. jsonMap = " + jsonMap.toString());
				return false;
			}
		} else {
			// unknown
			log.error("WeiXinUtils.moveWxUser2Group() unknown error. jsonMap = " + jsonMap.toString());
		}
		return false;
	}

	public static boolean remarkWxUser(WeiXinConfig weiXinConfig, final WxUser wxUser) {
		Map<String, Object> jsonMap = WeiXinHttpUtils.postForJson(WeiXinURL.post_remark_wxuser, weiXinConfig, null, //
				JacksonUtils.toJson(new HashMap<String, Object>() {
					{
						put("openid", wxUser.getOpenid());
						put("remark", wxUser.getRemark());
					}
				}));
		if (jsonMap.containsKey("errcode")) {
			if ("0".equals(jsonMap.get("errcode").toString().trim())) {
				// 成功
				log.info("WeiXinUtils.remarkWxUser() info. jsonMap = " + jsonMap.toString());
				return true;
			} else {
				// 失败
				log.error("WeiXinUtils.remarkWxUser() error. jsonMap = " + jsonMap.toString());
				return false;
			}
		} else {
			// unknown
			log.error("WeiXinUtils.remarkWxUser() unknown error. jsonMap = " + jsonMap.toString());
		}
		return false;
	}

	public static boolean refreshWxUserInfo(WeiXinConfig weiXinConfig, final WxUser wxUser) {
		Map<String, Object> jsonMap = WeiXinHttpUtils.getJson(WeiXinURL.get_wxuser_info, weiXinConfig, //
				new HashMap<String, String>() {
					{
						put("OPENID", wxUser.getOpenid());
					}
				}, null);
		if (jsonMap.containsKey("errcode")) {
			// 失败
			log.error("WeiXinUtils.refreshWxUserInfo() error. jsonMap = " + jsonMap.toString());
			return false;
		} else {
			// 成功
			if (jsonMap.containsKey("subscribe")) {
				wxUser.setSubscribeStatus(SubscribeStatus.fromWx(jsonMap.get("subscribe").toString().trim()));
			}
			if (jsonMap.containsKey("nickname")) {
				wxUser.setNickname(jsonMap.get("nickname").toString().trim());
			}
			if (jsonMap.containsKey("sex")) {
				wxUser.setSex(Sex.fromWx(jsonMap.get("sex").toString().trim()));
			}
			if (jsonMap.containsKey("language")) {
				wxUser.setLanguage(jsonMap.get("language").toString().trim());
			}
			if (jsonMap.containsKey("city")) {
				wxUser.setCity(jsonMap.get("city").toString().trim());
			}
			if (jsonMap.containsKey("province")) {
				wxUser.setProvince(jsonMap.get("province").toString().trim());
			}
			if (jsonMap.containsKey("country")) {
				wxUser.setCountry(jsonMap.get("country").toString().trim());
			}
			if (jsonMap.containsKey("headimgurl")) {
				wxUser.setHeadimgurl(jsonMap.get("headimgurl").toString().trim());
			}
			if (jsonMap.containsKey("subscribe_time")) {
				wxUser.setSubscribe_time(Long.valueOf(jsonMap.get("subscribe_time").toString().trim()));
			}
			if (jsonMap.containsKey("unionid")) {
				wxUser.setUnionid(jsonMap.get("unionid").toString().trim());
			}
			if (jsonMap.containsKey("remark")) {
				wxUser.setRemark(jsonMap.get("remark").toString().trim());
			}
			if (jsonMap.containsKey("groupid")) {
				wxUser.setGroupid(Long.valueOf(jsonMap.get("groupid").toString().trim()));
			}
			return true;
		}
	}

	public static boolean batchMoveWxUser2Group(WeiXinConfig weiXinConfig, final List<WxUser> wxUsers, final WxUserGroup wxUserGroup) {
		final List<String> wxUserOpenIds = new ArrayList<String>();
		for (WxUser wxUser : wxUsers) {
			wxUserOpenIds.add(wxUser.getOpenid());
		}
		Map<String, Object> jsonMap = WeiXinHttpUtils.postForJson(WeiXinURL.post_batch_move_user2group, weiXinConfig, null, //
				JacksonUtils.toJson(new HashMap<String, Object>() {
					{
						put("openid_list", wxUserOpenIds);
						put("to_groupid", wxUserGroup.getGroupId());
					}
				}));
		if (jsonMap.containsKey("errcode")) {
			if ("0".equals(jsonMap.get("errcode").toString().trim())) {
				// 成功
				log.info("WeiXinUtils.batchMoveWxUser2Group() info. jsonMap = " + jsonMap.toString());
				return true;
			} else {
				// 失败
				log.error("WeiXinUtils.batchMoveWxUser2Group() error. jsonMap = " + jsonMap.toString());
				return false;
			}
		} else {
			// unknown
			log.error("WeiXinUtils.batchMoveWxUser2Group() unknown error. jsonMap = " + jsonMap.toString());
		}
		return false;
	}

}