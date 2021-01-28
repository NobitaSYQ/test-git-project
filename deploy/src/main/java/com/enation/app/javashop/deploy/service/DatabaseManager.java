package com.enation.app.javashop.deploy.service;

import com.enation.app.javashop.framework.database.WebPage;
import com.enation.app.javashop.deploy.model.Database;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库业务层
 * @author admin
 * @version v1.0
 * @since v1.0
 * 2018-04-24 13:34:30
 */
public interface DatabaseManager	{

	/**
	 * 查询数据库列表
	 * @param page 页码
	 * @param pageSize 每页数量
	 * @return WebPage
	 */
	WebPage list(long page, long pageSize);

	/**
	 * 获取某个部署的数据库列表
	 * @param deployId 部署id
	 * @return 数据库列表
	 */
	List<Database> list(Long deployId);

	/**
	 * 添加数据库
	 * @param database 数据库
	 * @return Database 数据库
	 */
	Database add(Database database);

	/**
	* 修改数据库
	* @param database 数据库
	* @param id 数据库主键
	* @return Database 数据库
	*/
	Database edit(Database database, Long id);

	/**
	 * 删除数据库
	 * @param id 数据库主键
	 */
	void delete(Long id);

	/**
	 * 获取数据库
	 * @param id 数据库主键
	 * @return Database  数据库
	 */
	Database getModel(Long id);


	/**
	 * 初始始化数据库
	 * @param deployId 部署id
	 */
	void initDatabase(Integer deployId);

	/**
	 * 根据数据库信息创建连接
	 * @param database 数据库配置信息
	 * @return 数据库连接
	 * @throws ClassNotFoundException
	 * @throws  SQLException
	 */
	Connection createConnection(Database database) throws ClassNotFoundException, SQLException;


	/**
	 * 检测数据库连接
	 * @param database
	 * @return 如果参数正确返回真，否则返回假
	 */
	boolean testConnection(Database database);

	/**
	 * 执行删除数据库示例数据操作
	 * @param deployId 部署id
	 */
	void deleteDatabaseData(Long deployId);

	/**
	 * 执行sql脚本
	 * @param relativePath sql的相对位置(相对resource）
	 * @param jdbcTemplate jdbc执行器
	 */
	void executeSql(String relativePath, JdbcTemplate jdbcTemplate);

	/**
	 * 根据数据库配置创建 JdbcTemplate
	 *
	 * @param database 据库配置
	 * @return daoSupport
	 */
	JdbcTemplate createJdbcTemplate(Database database);

	/**
	 * 获取删除库中所有表sql
	 * @param serviceType
	 * @param database
	 * @return
	 */
	List<String> dropTableSql(String serviceType, Database database);

	/**
	 * 执行删除表
	 * @param dropSql
	 * @param jdbcTemplate
	 */
	void dropTable(List<String> dropSql,JdbcTemplate jdbcTemplate);
}
