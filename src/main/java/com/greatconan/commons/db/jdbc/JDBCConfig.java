/**
 * 
 */
package com.greatconan.commons.db.jdbc;

import java.io.IOException;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;

/**
 * @author i-jiech JDBC属性配置类
 */
class JDBCConfig {
	private Properties props = new Properties();
	private String configLocation = "jdbc.cfg.properties";

	/**
	 * 使用当前目录下的jdbc.properties配置文件构建JDBCConfig对象
	 * 
	 * @throws IOException
	 */
	public JDBCConfig() throws IOException {
		this("jdbc.cfg.properties");
	}

	/**
	 * 从指定的配置文件路径构建JDBCConfig对象
	 * 
	 * @param configLocation
	 *            配置文件路径
	 * @throws IOException
	 */
	public JDBCConfig(String configLocation) throws IOException {
		this.configLocation = configLocation;
		props.load(JDBCConfig.class.getResourceAsStream(configLocation));
	}

	/**
	 * 获取指定配置名的值
	 * 
	 * @param propertyName
	 *            配置名
	 * @return 配置名对应的值
	 */
	public String getProperty(String propertyName) {
		String value = props.getProperty(propertyName).trim();
		if (StringUtils.isEmpty(value))
			throw new JDBCPropertyNotSetException("配置文件：" + configLocation
					+ "中没有配置" + propertyName + "或者配置的值为空!");
		return value;
	}

	/**
	 * 获取JDBC连接的url
	 * 
	 * @return JDBC连接的url
	 */
	public String getConnectionUrl() {
		return getProperty("connection_url");
	}

	/**
	 * 获取数据库驱动名
	 * 
	 * @return 数据库驱动名称
	 */
	public String getJDBCDriver() {
		return getProperty("jdbc_driver");
	}

	/**
	 * 获取数据库连接用户名
	 * 
	 * @return 数据库连接用户名
	 */
	public String getConnectionUsername() {
		return getProperty("connection_username");
	}

	/**
	 * 获取数据库连接密码
	 * 
	 * @return 数据库连接密码
	 */
	public String getConnectionPassword() {
		return getProperty("connection_password");
	}

	/**
	 * 是否自动提交事务
	 * 
	 * @return 如果是自动提交，返回true，否则返回false
	 */
	public boolean isAutoCommit() {
		return Boolean.valueOf(getProperty("auto_commit"));
	}

	/**
	 * 是否显示SQL语句
	 * 
	 * @return 如果显示SQL语句，则返回true，否则返回false
	 */
	public boolean isShowSQL() {
		return Boolean.valueOf(getProperty("show_sql"));
	}
}
