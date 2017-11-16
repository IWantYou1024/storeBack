package cn.itcast.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class FastJsonUtils {

	public static String toJsonString(Object object) {
		return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
	}

	public static void writeJson(HttpServletResponse response, String jsonString) {
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		try {
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void writeJson(HttpServletResponse response, Object object) {
		String jsonString = toJsonString(object);
		response.setContentType("application/json;charset=utf-8");
		response.setHeader("cache-control", "no-cache");
		try {
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String toJsonString(Object object, String...includes) {
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(includes);
		return JSON.toJSONString(object, filter, SerializerFeature.DisableCircularReferenceDetect);
	}

}
