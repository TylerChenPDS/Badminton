package njit.service;

import java.util.List;

import njit.model.User;

public interface BaseService<T> {
	
	/**
	 * 这个方法有缺陷，只能是entity类的属性个数和表格的列数，他们是一一对应关系的情况下才能使用
	 * @param t
	 */
	@Deprecated
	public void add(T t);
	
	public void addForNotMatch(Object[] fieldNames, Object[] fieldValues);
	
	public void delete(int id);
	
	public void update(T t);
	
	public T select(int id);
	
	public List<T> selectAll();

}
