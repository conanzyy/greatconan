/**
 * 
 */
package com.greatconan.commons.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 主要的JDBC功能类
 * 
 * @author i-jiech
 * 
 */
public class JDBCTemplate {

	/**
	 * 对使用JDBC的数据库涉及更新数据的操作（插入、更新、删除这3种操作）的封装
	 * 
	 * @param callBack
	 *            完成数据更新后用于回调的接口实例
	 * @param sql
	 *            执行的sql语句，支持占位符
	 * @param params
	 *            sql占位符对应的参数
	 * @throws SQLException
	 *             数据库操作出错时抛出
	 */
	public void executeUpdate(JDBCUpdateCallBack callBack, String sql,
			Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			preparedParams(ps, params);
			int rows = ps.executeUpdate();
			if (JDBCUtil.getConfig().isShowSQL())
				System.out.println(sql);
			if (callBack != null)
				callBack.updateCall(rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtil.close(conn, ps, rs);
		}
	}

	/**
	 * 批量更新操作，对使用JDBC的数据库涉及更新数据的操作（插入、更新、删除这3种操作）的封装
	 * 
	 * @param callBack
	 *            完成数据更新后用于回调的接口实例
	 * @param sqls
	 *            批量更新的SQL语句数组，sql语句支持"?"占位符
	 * @param params
	 *            对应sql语句的参数值，顺序必须与sql中的占位符一致
	 * @throws SQLException
	 *             数据库操作出错时抛出
	 */
	public void executeBatchUpdate(JDBCUpdateCallBack callBack, String[] sqls,
			Object[][] params) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			int rows = 0;
			for (int i = 0, length = sqls.length; i < length; i++) {
				ps = conn.prepareStatement(sqls[i]);
				preparedParams(ps, params[i]);
				rows += ps.executeUpdate();
				if (JDBCUtil.getConfig().isShowSQL())
					System.out.println(sqls[i]);
			}
			if (callBack != null)
				callBack.updateCall(rows);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtil.close(conn, ps, rs);
		}
	}

	/**
	 * 对JDBC查询操作的封装
	 * 
	 * @param callBack
	 *            查询出结果集后的回调接口实例
	 * @param sql
	 *            查询的sql语句，支持占位符
	 * @param params
	 *            sql占位符对应的参数
	 * @throws SQLException
	 */
	public void executeQuery(JDBCQueryCallBack callBack, String sql,
			Object... params) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			preparedParams(ps, params);
			rs = ps.executeQuery();
			if (JDBCUtil.getConfig().isShowSQL())
				System.out.println(sql);
			if (callBack != null)
				callBack.queryCall(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtil.close(conn, ps, rs);
		}
	}

	/**
	 * 对JDBC批量查询操作的封装
	 * 
	 * @param callBack
	 *            批量查询回调接口实例
	 * @param sqls
	 *            批量查询的SQL语句数组，sql语句支持"?"占位符
	 * @param params
	 *            对应sql语句的参数值，顺序必须与sql中的占位符一致
	 * @throws SQLException
	 *             数据库操作出错时抛出
	 */
	public void executeBatchQuery(JDBCBatchQueryCallBack callBack,
			String[] sqls, Object[][] params) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet[] rs = new ResultSet[sqls.length];
		try {
			conn = JDBCUtil.getConnection();
			for (int i = 0, length = sqls.length; i < length; i++) {
				ps = conn.prepareStatement(sqls[i]);
				preparedParams(ps, params[i]);
				rs[i] = ps.executeQuery();
				if (JDBCUtil.getConfig().isShowSQL())
					System.out.println(sqls[i]);
			}
			if (callBack != null)
				callBack.batchCallBack(rs);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			JDBCUtil.close(conn, ps, rs);
		}
	}

	/**
	 * 为绑定了带占位符的SQL语句预处理对象填充参数
	 * 
	 * @param ps
	 *            绑定了带占位符的SQL语句预处理对象
	 * @param params
	 *            参数列表
	 * @throws SQLException
	 */
	private void preparedParams(PreparedStatement ps, Object[] params)
			throws SQLException {
		if (ps != null && params != null && params.length > 0)
			for (int i = 0, length = params.length; i < length; i++) {
				ps.setObject(i + 1, params[i]);
			}
	}

}
