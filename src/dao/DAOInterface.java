package dao;

import java.util.ArrayList;

public interface DAOInterface<T> {
	public int insert(T t);
	
	public int update(T t);
	
	public int delete(T t);
	
	public ArrayList<T> selectALL();
	
	public T selectByID(T t);
	
	public ArrayList<T> selectByCondition(String Conition);
	
	public ArrayList<T> search(String keyword);
	
	public boolean exists(T t);
	
	public int count();
	
	public ArrayList<Object[]> countGroupByTwoFields(String field1, String field2);
}
