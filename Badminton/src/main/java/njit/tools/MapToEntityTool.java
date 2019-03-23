package njit.tools;

//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class MapToEntityTool {
	public static<T> T myMapToEntity(Map<Object,Object> map,Class<T> clazz) {
		System.out.println(map);
		T rs = null;
		try {
			rs = clazz.newInstance();
			String fieldName = null;
			for(Map.Entry<Object,Object> entity : map.entrySet()) {
				fieldName = entity.getKey().toString();
				if(fieldName.contains("_")) {
					String strs[] = fieldName.split("_");
					fieldName = strs[0];
					for(int i = 1; i < strs.length; i ++) {
						fieldName += strs[i].substring(0, 1).toUpperCase() + strs[i].substring(1);
					}
				}
				BeanUtils.setProperty(rs, fieldName, entity.getValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
}
