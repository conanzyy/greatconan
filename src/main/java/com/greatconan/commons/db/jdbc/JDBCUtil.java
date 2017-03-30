/**
 * 
 */
package com.greatconan.commons.db.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JDBC操作的工具类
 * 
 * @author i-jiech
 * 
 */
class JDBCUtil {
	private static JDBCConfig config;

	static {
		try {
			config = new JDBCConfig();
			Class.forName(config.getJDBCDriver());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static ThreadLocal<Connection> connections = new ThreadLocal<Connection>();

	/**
	 * 从本地线程中取得一个属于当前线程的数据库连接对象，如果当前线程没有绑定的数据库连接对象，则创建一个并绑定
	 * 
	 * @return 一个数据库连接对象
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection conn = connections.get();
		if (conn == null) {
			conn = DriverManager.getConnection(config.getConnectionUrl(),
					config.getConnectionUsername(),
					config.getConnectionPassword());
			conn.setAutoCommit(config.isAutoCommit());
			connections.set(conn);
		}
		return conn;
	}

	/**
	 * @return the config
	 */
	public static JDBCConfig getConfig() {
		return config;
	}

	/**
	 * 关闭数据库连接
	 * 
	 * @param conn
	 *            数据库连接对象，可以为null
	 * @param st
	 *            数据库操作对象，可以为null
	 * @param rs
	 *            结果集，可以为null
	 */
	public static void close(Connection conn, Statement st, ResultSet... rs) {
		try {
			for (ResultSet r : rs)
				if (r != null && !r.isClosed())
					r.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null && !st.isClosed())
					st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
				if (conn != null && !conn.isClosed())
						if (!conn.getAutoCommit())
							conn.commit();
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					connections.remove();
				}
			}
		}
	}

}
