/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.message.reply;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.buzz.weixin.message.common.Message;
import com.app.buzz.weixin.message.common.MessageType;
import com.app.buzz.weixin.message.common.Reply;
import com.app.buzz.weixin.util.XmlUtils;

/**
 * 回复图文消息
 * 
 * @param ToUserName
 *            是 接收方帐号（收到的OpenID）
 * @param FromUserName
 *            是 开发者微信号
 * @param CreateTime
 *            是 消息创建时间 （整型）
 * @param MsgType
 *            是 news
 * @param ArticleCount
 *            是 图文消息个数，限制为10条以内
 * @param Articles
 *            是 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
 * @param Title
 *            否 图文消息标题
 * @param Description
 *            否 图文消息描述
 * @param PicUrl
 *            否 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
 * @param Url
 *            否 点击图文消息跳转链接
 * @author APP TEAM
 * @version 1.0
 */
public class NewsReply extends Reply {

	private Long ArticleCount;
	private List<NewsItem> Articles;

	public NewsReply(Message message) {
		super(message, MessageType.news.toString());
		this.Articles = new ArrayList<NewsItem>();
		this.ArticleCount = 0L;
	}

	public NewsReply(List<NewsItem> articles) {
		setMsgType(MessageType.news.toString());
		if (articles != null) {
			//注意此处利用了ArrayList使用Collection作为参数这种构造方法，articles本身并不是ArrayList（可能Hibernate本身就没映射成ArrayList，也可能是由于代理导致）。
			this.Articles = new ArrayList<NewsItem>(articles);
			this.ArticleCount = new Long(articles.size());
		} else {
			this.Articles = new ArrayList<NewsItem>();
			this.ArticleCount = 0L;
		}
	}

	public void addNewsItem(NewsItem item) {
		Articles.add(item);
		ArticleCount++;
	}

	/**
	 * @param Title
	 *            否 图文消息标题
	 * @param Description
	 *            否 图文消息描述
	 * @param PicUrl
	 *            否 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 * @param Url
	 *            否 点击图文消息跳转链接
	 */
	public void addNewsItem(String title, String description, String picUrl, String url) {
		addNewsItem(new NewsItem(title, description, picUrl, url));
	}

	public Long getArticleCount() {

		return ArticleCount;
	}

	public void setArticleCount(Long articleCount) {
		ArticleCount = articleCount;
	}

	public List<NewsItem> getArticles() {
		return Articles;
	}

	public void setArticles(List<NewsItem> articles) {
		Articles = articles;
	}

	@Override
	public String toString() {
		Map<String, Class<?>> alias = new HashMap<String, Class<?>>();
		alias.put("xml", this.getClass());
		alias.put("item", NewsItem.class);
		return XmlUtils.object2Xml(this, alias);
	}

}
