/**
 * 
 */
package com.greatconan.commons.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC批量查询回调接口
 * 
 * @author i-jiech
 * 
 */
public interface JDBCBatchQueryCallBack {

	/**
	 * 当所有的SQL查询语句执行完成之后被回调。
	 * 
	 * @param results
	 *            每条SQL语句对应的查询结果集数组，实现方法中无需关闭此resultSet,resultSet会在该方法回调完成后自动关闭
	 * @throws SQLException
	 *             回调过程中数据库操作出现异常时抛出
	 */
	public void batchCallBack(ResultSet[] results) throws SQLException;

}
