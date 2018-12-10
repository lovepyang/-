package com.postal.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class MapUtil {
	private static Logger log = LogManager.getLogger(MapUtil.class);
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {  
        // 参数Map  
        Map<?, ?> properties = request.getParameterMap();  
        // 返回值Map  
        Map<String, Object> returnMap = new HashMap<String, Object>();  
        Iterator<?> entries = properties.entrySet().iterator();  
        Map.Entry<?,?> entry;  
        String name = "";  
        String value = "";  
        while (entries.hasNext()) {  
            entry = (Map.Entry<?,?>) entries.next();  
            name = (String) entry.getKey();  
            Object valueObj = entry.getValue();  
            if(null == valueObj){  
                value = "";  
            }else if(valueObj instanceof String[]){  
                String[] values = (String[])valueObj;  
                for(int i=0;i<values.length;i++){  
                    value = values[i] + ",";  
                }  
                value = value.substring(0, value.length()-1);  
            }else{  
                value = valueObj.toString();  
            }  
            returnMap.put(name, value);  
        }  
        return returnMap;  
    } 
	
	/**
	 * 返回MAP结果对象
	 * @param code
	 * @param msg
	 * @return
	 */
	public static Map<String, Object> returnMap(String code, Object msg) {
		Map<String, Object> nem = new HashMap<String, Object>(2);
		nem.put("code", code);
		nem.put("msg", msg);
		return nem;
	}
	/**
	 * 返回MAP结果对象
	 * @param isPrintEnd  是否打印结束标志
	 * @param code
	 * @param msg
	 * @return
	 */
	public static Map<String, Object> returnMap(String code, Object msg,Boolean isPrintEnd) {
		if (isPrintEnd) {
			log.info("-----------------结束-----------------   \r\n   ");
		}
		Map<String, Object> nem = new HashMap<String, Object>(2);
		nem.put("code", code);
		nem.put("msg", msg);
		return nem;
	}

	public static Object analysisData(String comm, String result) {
		try {
			JSONObject jobs= new JSONObject(result);
			String returnCode = jobs.getJSONObject(comm).getString("return_code");
			if ("0".equals(returnCode)) {
				 return MapUtil.returnMap("0", jobs.getJSONObject(comm).getString("return_msg"));
			}else {
				return MapUtil.returnMap("-1", jobs.getJSONObject(comm).getString("return_msg"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return MapUtil.returnMap("-1", result);
			
		}
	}
	
	/**
	 * 获取JSON对象中的STRING数据
	 * @param field  字段名
	 * @param jsonObj JSON对象
	 */
	public static String getJsonString(String field,JSONObject jsonObj) {
		String result = "";
		if (null == jsonObj) {
			return result;
		}
		if (!jsonObj.isNull(field)) {
			try {
				result = jsonObj.getString(field);
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return result;
		
	}
}
