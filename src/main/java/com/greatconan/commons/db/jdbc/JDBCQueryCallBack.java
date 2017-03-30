/**
 * 
 */
package com.greatconan.commons.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC查询回调接口
 * 
 * @author i-jiech
 * 
 */
public interface JDBCQueryCallBack {
	/**
	 * 执行完查询操作后被回调
	 * 
	 * @param resultSet
	 *            查询的结果集，实现方法中无需关闭此resultSet,resultSet会在该方法回调完成后自动关闭
	 * @throws SQLException
	 *             回调过程中数据库操作出现异常时抛出
	 */
	public void queryCall(ResultSet resultSet) throws SQLException;
}
