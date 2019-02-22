package njit.tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
				//System.out.println(fieldName);
				BeanUtils.setProperty(rs, fieldName, entity.getValue());
			}
			
			
//			Field[] fields = clazz.getDeclaredFields();
//			Object value = null;
//			String fileName = null;
//			for(Field field : fields) {
//				fileName = field.getName();
//				value = map.get(fileName);
//				if(value != null)
//					BeanUtils.setProperty(rs, fileName, value);
//				else {
//					
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static<T> T mapToEntity(Map<Object,Object> map,Class<T> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<String> fieldNameList = new ArrayList<>();
		String fieldName;
		String setMethodName;
		Method setMethod = null;
		Map<String, Method> setMethodMap = new HashMap<>();
		for(Field field : fields) {
			field.setAccessible(true);
			fieldName = field.getName();
			fieldNameList.add(fieldName);
			setMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				setMethod = clazz.getDeclaredMethod(setMethodName, field.getType());
				setMethodMap.put(fieldName, setMethod);
			} catch (NoSuchMethodException | SecurityException e) {
				e.printStackTrace();
			}
		}
		T enitity = null;
		try {
			enitity = clazz.newInstance();
			Class<?>[] parameterTypes = null;
			for(String fieldName1 : fieldNameList) {
				Object value = map.get(fieldName1);
				if(value == null) continue;
				Method method = setMethodMap.get(fieldName1);
				if(method == null) continue;
				parameterTypes = method.getParameterTypes();
				if(parameterTypes == null || parameterTypes.length > 1) continue;
				if(parameterTypes[0].isAssignableFrom(value.getClass())) {
					method.invoke(enitity, value);
				}
			}
			
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return enitity;
	}
	public static void main(String[] args) {
		String a = "ssadasdAsadsad";
		String b[] = a.split("[A-Z]");
		System.out.println();
		
	}
}
