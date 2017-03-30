package com.greatconan.commons.db.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.greatconan.commons.db.jdbc.JDBCBatchQueryCallBack;
import com.greatconan.commons.db.jdbc.JDBCQueryCallBack;
import com.greatconan.commons.db.jdbc.JDBCTemplate;
import com.greatconan.commons.db.jdbc.JDBCUpdateCallBack;

/**
 * 对封装的JDBCTemplate类的测试类
 * 
 * @author Administrator
 * 
 */
public class JDBCTemplateTest {

	private JDBCTemplate template = new JDBCTemplate();

	/**
	 * 测试JDBCTemplate类的更新方法
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteUpdate() throws SQLException {
		template.executeUpdate(new JDBCUpdateCallBack() {

			public void updateCall(int rows) {
				System.out.println("受影响的行数：" + rows);
			}
		}, "update TB_MAKE_TYPE set TYPE_NAME = ? where TYPE_ID = ?", "其他", 18);
	}

	/**
	 * 测试JDBCTemplate类的查询方法
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteQuery() throws SQLException {
		template.executeQuery(new JDBCQueryCallBack() {

			public void queryCall(ResultSet resultSet) {
				try {
					while (resultSet.next()) {
						System.out.print(resultSet.getObject(1) + ":");
						System.out.println(resultSet.getObject(2));
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}, "select * from TB_MAKE_TYPE", new Object[0]);
	}

	/**
	 * 测试JDBCTemplate类的批量更新方法
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteBatchUpdate() throws SQLException {
		String[] sqls = {
				"update TB_MAKE_TYPE set TYPE_NAME = ? where TYPE_ID = ?",// 更新一行
				"insert TB_MAKE_TYPE(TYPE_NAME) values(?)",// 插入一行
				"delete from TB_MAKE_TYPE where TYPE_ID = ?" };// 删除一行
		Object[][] params = { { "typeaaa", 1 }, { "批量更新" }, { 1 } };
		template.executeBatchUpdate(new JDBCUpdateCallBack() {

			public void updateCall(int rows) {
				System.out.println("受影响的行数：" + rows);
			}
		}, sqls, params);
	}

	/**
	 * 测试JDBCTemplate类的批量查询方法
	 * 
	 * @throws SQLException
	 */
	@Test
	public void testExecuteBatchQuery() throws SQLException {
		String[] sqls = { "select * from TB_MAKE_TYPE",// 更新一行
				"select TYPE_ID from TB_MAKE_TYPE",// 插入一行
				"select TYPE_NAME from TB_MAKE_TYPE where TYPE_ID = ?" };// 删除一行
		Object[][] params = { {}, {}, { 2 } };
		template.executeBatchQuery(new JDBCBatchQueryCallBack() {

			public void batchCallBack(ResultSet[] results) {
				for (ResultSet rs : results) {
					try {
						while (rs.next()) {
							System.out.println(rs.getObject(1));
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}, sqls, params);
	}
}
