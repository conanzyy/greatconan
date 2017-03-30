/**
 * 
 */
package com.greatconan.commons.db;

import java.sql.SQLException;

/**
 * 
 * JDBC更新操作回调接口
 * 
 * @author i-jiech
 * 
 */
public interface JDBCUpdateCallBack {
	/**
	 * 执行完可能对数据产生更新的操作（如增加、更新、删除）后被回调
	 * 
	 * @param rows
	 *            数据库受影响的行数
	 * @throws SQLException
	 *             回调过程中数据库操作出现异常时抛出
	 */
	public void updateCall(int rows) throws SQLException;
}
