/*
 * Copyright 2015-2025 APP TEAM. All rights reserved.
 * Support: @Support
 * License: @License
 */
package com.app.buzz.weixin.util;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;

/**
 * Utils - XML
 * 
 * @author APP TEAM
 * @version 1.0
 */
public class XmlUtils {

	protected static String PREFIX_CDATA = "<![CDATA[";
	protected static String SUFFIX_CDATA = "]]>";

	/**
	 * 获取只有一层次的xml文档，无属性。 转换为map。
	 * */
	public static Map<String, String> xml2Map(InputStream in) {
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(in);
			Element root = document.getRootElement();
			List<Element> elements = root.elements();
			for (Element e : elements) {
				map.put(e.getName(), e.getText());
			}
			return map;

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String object2Xml(Object object) {
		try {
			XStream xstream = new XStream(new Dom4JDriver() {
				@Override
				public HierarchicalStreamWriter createWriter(Writer out) {
					return new PrettyPrintWriter(out) {
						@Override
						protected void writeText(QuickWriter writer, String text) {
							if (text.startsWith(PREFIX_CDATA) && text.endsWith(SUFFIX_CDATA)) {
								writer.write(text);
							} else {
								super.writeText(writer, text);
							}
						}
					};
				}
			});
			xstream.processAnnotations(object.getClass());
			return xstream.toXML(object);
		} catch (Exception e) {
		}
		return null;
	}

	public static String object2Xml(Object object, String alias) {
		try {
			XStream xstream = new XStream(new Dom4JDriver() {
				@Override
				public HierarchicalStreamWriter createWriter(Writer out) {
					return new PrettyPrintWriter(out) {
						@Override
						protected void writeText(QuickWriter writer, String text) {
							if (text.startsWith(PREFIX_CDATA) && text.endsWith(SUFFIX_CDATA)) {
								writer.write(text);
							} else {
								super.writeText(writer, text);
							}
						}
					};
				}
			});
			if (alias != null)
				xstream.alias(alias, object.getClass());
			else
				xstream.alias(object.getClass().getSimpleName(), object.getClass());

			xstream.processAnnotations(object.getClass());

			return xstream.toXML(object);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/***
	 * 如果alias不为空，由调用者决定别名。 否则，默认只为object对象指定一个别名为其类的简单名字。
	 */
	public static String object2Xml(Object object, Map<String, Class<?>> alias) {
		try {
			XStream xstream = new XStream(new Dom4JDriver() {
				@Override
				public HierarchicalStreamWriter createWriter(Writer out) {
					return new PrettyPrintWriter(out) {
						@Override
						protected void writeText(QuickWriter writer, String text) {
							if (text.startsWith(PREFIX_CDATA) && text.endsWith(SUFFIX_CDATA)) {
								writer.write(text);
							} else {
								super.writeText(writer, text);
							}
						}
					};
				}
			});

			if (alias != null) {
				for (Map.Entry<String, Class<?>> entry : alias.entrySet()) {
					xstream.alias(entry.getKey(), entry.getValue());
				}
			} else {
				xstream.alias(object.getClass().getSimpleName(), object.getClass());
			}
			xstream.processAnnotations(object.getClass());
			return xstream.toXML(object);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T xml2Object(String xml) {
		try {
			XStream xstream = new XStream(new Dom4JDriver());
			xstream.ignoreUnknownElements();
			return (T) xstream.fromXML(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T xml2Object(String xml, Class<?> clazz) {
		try {
			XStream xstream = new XStream(new Dom4JDriver());
			xstream.alias(clazz.getSimpleName(), clazz);
			xstream.ignoreUnknownElements();
			return (T) xstream.fromXML(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T xml2Object(String xml, Class<?> clazz, String alias) {
		try {
			XStream xstream = new XStream(new Dom4JDriver());
			xstream.alias(alias, clazz);
			xstream.ignoreUnknownElements();
			return (T) xstream.fromXML(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static <T> T xml2Object(String xml, Class<?> clazz, String alias, List<Converter> converters) {
		try {
			XStream xstream = new XStream(new Dom4JDriver());
			xstream.alias(alias, clazz);
			xstream.ignoreUnknownElements();

			if (converters != null && converters.size() > 0) {
				for (Converter converter : converters) {
					xstream.registerConverter(converter);
				}
			}

			return (T) xstream.fromXML(xml);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
