package njit.service;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import njit.dao.BaseDao;
import njit.tools.MapToEntityTool;

@SuppressWarnings("all")
public abstract class BaseServiceImp<T> implements BaseService<T>{
	public abstract BaseDao getBaseDao();
	
	protected Class<?> clazz;
	protected String tableName; //表的名称 为model类小写
	
	public BaseServiceImp() {
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) type;
		Type[]  types = pt.getActualTypeArguments();
		clazz = (Class<?>) types[0];
		tableName = mapCamelCaseToUnderscore(clazz.getSimpleName()); //clazz.getSimpleName().toLowerCase();
	}
	
	public String mapCamelCaseToUnderscore(String a){
		String regex = "([A-Z])";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(a);
		int i = 0;
		String re = "";
		while(m.find()) {
			int c = m.start();
			if(c != 0) {
				re += a.substring(i + 1,c) + "_" + m.group(1).toLowerCase();
				i = c;
			}else {
				re +=	a.substring(0,1).toLowerCase();
			}
			
		}
		return re += a.substring(i + 1);
	}
	
	
	@Deprecated
	@Override
	public void add(T t) {
		//获取数据表的名字
		List<Object> list = new ArrayList<>();
		for(Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				list.add(field.get(t));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		getBaseDao().add(tableName, list.toArray());
	}

	@Override
	public void delete(int id) {
		getBaseDao().delete(tableName, id);
	}

	/**
	 * 不修改为空的字符串
	 */
	@Override
	public void update(T t) {
		int id = 0;
		List<Object> list = new ArrayList<>(); 
		for(Field field : t.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			try {
				if(field.get(t) == null) {
					continue;
				}
				if("id".equals(field.getName())) {
					id = (int) field.get(t);
					continue;
				}
				list.add(field.getName() + "='" +  field.get(t) +"'");
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		getBaseDao().update(tableName, id, list.toArray());
	}

	
	@Override
	public T select(int id) {
		Map<Object,Object> map = getBaseDao().select(tableName, id);
		return (T) MapToEntityTool.myMapToEntity(map, clazz);
	}

	@Override
	public List<T> selectAll() {
		List<Map<Object,Object>> rslist = getBaseDao().selectAll(tableName);
		List<T> list = new ArrayList<>();
		T t = null;
		for(Map<Object,Object> map : rslist) {
			t = (T) MapToEntityTool.myMapToEntity(map, clazz);
			list.add(t);
		}
		return list;
	}
	
	@Override
	public int addForNotMatch(Object[] fieldNames, Object[] fieldValues) {
		return getBaseDao().addForNotMatch(tableName,fieldNames, fieldValues);
	}

}
