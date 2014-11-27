/**
 * Copyright (c) 华南农业大学信息学院蔡超敏2014版权所有
 * 
 * 文件创建时间：2014-11-26
 */
package util;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author 蔡超敏
 * 
 */
public class JsonUtil {
	private static Gson gson = null;

	static {
		if (gson == null) {
			gson = new Gson();
		}
	}

	private JsonUtil() {

	}

	/**
	 * ������ת����json��ʽ
	 * 
	 * @param ts
	 * @return
	 */
	public static String objectToJson(Object ts) {
		String jsonStr = null;
		if (gson != null) {
			jsonStr = gson.toJson(ts);
		}
		return jsonStr;
	}

	/**
	 * ��json��ʽת����list���󣬲�׼ȷָ������
	 * 
	 * @param jsonStr
	 * @param type
	 * @return
	 */
	public static List<?> jsonToList(String jsonStr, Type type) {
		List<?> objList = null;
		if (gson != null) {
			objList = gson.fromJson(jsonStr, type);
		}
		return objList;
	}

	/**
	 * ��json��ʽת����map����
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static HashMap<?, ?> jsonToMap(String jsonStr) {
		HashMap<?, ?> objMap = null;
		if (gson != null) {
			Type type = new TypeToken<HashMap<?, ?>>() {
			}.getType();
			objMap = gson.fromJson(jsonStr, type);
		}
		return objMap;
	}

	/**
	 * ��jsonת����bean����
	 * 
	 * @param jsonStr
	 * @return
	 */
	public static Object jsonToBean(String jsonStr, Class<?> cl) {
		Object obj = null;
		if (gson != null) {
			obj = gson.fromJson(jsonStr, cl);
		}
		return obj;
	}

	/**
	 * ���
	 * 
	 * @param jsonStr
	 * @param key
	 * @return
	 */
	public static Object getJsonValue(String jsonStr, String key) {
		Object rulsObj = null;
		HashMap<?, ?> rulsMap = jsonToMap(jsonStr);
		if (rulsMap != null && rulsMap.size() > 0) {
			rulsObj = rulsMap.get(key);
		}
		return rulsObj;
	}

}
