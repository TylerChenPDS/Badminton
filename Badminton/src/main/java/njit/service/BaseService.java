package njit.service;

import java.util.List;

public interface BaseService<T> {
	@Deprecated
	public void add(T t);
	
	public void addForNotMatch(Object[] fieldNames, Object[] fieldValues);
	
	public void delete(int id);
	
	public void update(T t);
	
	public T select(int id);
	
	public List<T> selectAll();

}
