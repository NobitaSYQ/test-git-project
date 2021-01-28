package com.enation.app.javashop.deploy.service.impl;

import com.enation.app.javashop.deploy.enums.ServiceType;
import com.enation.app.javashop.framework.database.StringMapper;
import com.enation.app.javashop.framework.exception.ServiceException;
import com.enation.app.javashop.framework.util.FileUtil;
import com.enation.app.javashop.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.enation.app.javashop.framework.database.DaoSupport;
import com.enation.app.javashop.framework.database.WebPage;
import com.enation.app.javashop.deploy.model.Database;
import com.enation.app.javashop.deploy.service.DatabaseManager;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 * 数据库业务类
 * @author admin
 * @version v1.0
 * @since v1.0
 * 2018-04-24 13:34:30
 */
@Service
public class DatabaseManagerImpl implements DatabaseManager{

	@Autowired
	private	DaoSupport	daoSupport;

	@Override
	public WebPage list(long page, long pageSize){

		String sql = "select * from es_database  ";
		WebPage webPage = this.daoSupport.queryForPage(sql,page, pageSize ,Database.class );

		return webPage;
	}

	@Override
	public List<Database> list(Long deployId) {
		String sql = "select * from es_database where deploy_id=?";
		List<Database>  dbList  =daoSupport.queryForList(sql, Database.class, deployId);
		return dbList;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public	Database  add(Database	database)	{
		this.daoSupport.insert(database);

		return database;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public	Database  edit(Database	database,Long id){
		this.daoSupport.update(database, id);
		return database;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED,rollbackFor=Exception.class)
	public	void delete( Long id)	{
		this.daoSupport.delete(Database.class,	id);
	}

	@Override
	public	Database getModel(Long id)	{
		return this.daoSupport.queryForObject(Database.class, id);
	}

	@Override
	public void initDatabase(Integer deployId) {
		ServiceType[] serviceTypes = ServiceType.values();

		//默认为每个业务创建一个数据库
		for ( ServiceType serviceType: serviceTypes  ) {
			Database	database = new Database();
			database.setDeployId(deployId);
			database.setDbName(serviceType.name());
			database.setServiceType(serviceType.name());
			database.setDbIp("127.0.0.1");
			database.setDbType("mysql");
			database.setDbPort("3306");
			database.setDbUsername("root");
			database.setDbPassword("root");

			this.add(database);
		}

	}

	@Override
	public Connection createConnection(Database database) throws ClassNotFoundException, SQLException {
		String  driver = "com.mysql.jdbc.Driver";
		String  url ="jdbc:mysql://"+ database.getDbIp()+":"+ database.getDbPort()+"/"+ database.getDbName();

		Class.forName(driver);

		Connection conn = DriverManager.getConnection(url,database.getDbUsername(),database.getDbPassword());
		return conn;
	}


	@Override
	public boolean testConnection(Database database){
		try {
			Connection conn = createConnection(database);
			conn.close();
		 	return true;
		} catch (Exception e) {
			 return  false;
		}
	}

	@Override
	public void deleteDatabaseData(Long deployId) {
		List<Database> dbList = this.list(deployId);
		for (Database database : dbList) {
			String serviceType = database.getServiceType().toLowerCase();
			String relativePath = "scheme/delete/" + serviceType + ".sql";
			JdbcTemplate jdbcTemplate = createJdbcTemplate(database);
			executeSql(relativePath,jdbcTemplate);
		}
	}

	@Override
	public void executeSql(String relativePath, JdbcTemplate jdbcTemplate ) {

		//标识是否找到要执行的sql文件
		boolean exist = false;

		String sqlContent = null;

		InputStream inputStream = null;


		//先尝试在fat jar的home目录查找
		//执行方案的目录为 fat jar root path  + scheme
		String homePath = getHomePath();
		String sqlPath = homePath + "/" + relativePath;
		File file = new File(sqlPath);
		try {
			inputStream = new FileInputStream(file);
			exist = true;
			System.out.println(" 在 " + file + "中找到执行sql");
		} catch (FileNotFoundException e) {
			exist = false;
		}

		//如果不存在，尝试在resources下查找
		if (!exist) {

			try {
				Resource resource = new ClassPathResource(relativePath);
				inputStream = resource.getInputStream();
				System.out.println(" 在 resources/" + relativePath + "中找到执行sql");

				exist = true;
			} catch (IOException e) {
				exist = false;
			}
		}

		//如果最终找不到抛出异常给上层
		if (!exist) {
			throw new ServiceException("000", "sql文件【" + relativePath + "】找不到");
		}

		sqlContent = FileUtil.readStreamToString(inputStream);

		String[] sqlArray = sqlContent.split(";\n");


		for (String sql : sqlArray) {
			//跳过为空的sql
			if (StringUtil.isEmpty(sql)) {
				continue;
			}

			jdbcTemplate.execute(sql);
		}

	}

	@Override
	public JdbcTemplate createJdbcTemplate(Database database) {

		String url = "jdbc:mysql://" + database.getDbIp() + ":" + database.getDbPort() + "/" + database.getDbName() + "?useUnicode=true&characterEncoding=utf8&";
		DataSource dataSource = DataSourceBuilder.create()
				.type(com.alibaba.druid.pool.DruidDataSource.class)
				.url(url)
				.password(database.getDbPassword())
				.username(database.getDbUsername()).build();

		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		return jdbcTemplate;
	}

	@Override
	public List<String> dropTableSql(String serviceType, Database database) {
		String url = "jdbc:mysql://" + database.getDbIp() + ":" + database.getDbPort() + "/information_schema?useUnicode=true&characterEncoding=utf8&";
		DataSource dataSource = DataSourceBuilder.create()
				.type(com.alibaba.druid.pool.DruidDataSource.class)
				.url(url)
				.password(database.getDbPassword())
				.username(database.getDbUsername()).build();
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		String sql = "SELECT CONCAT('DROP TABLE IF EXISTS ',TABLE_NAME) as drop_sql FROM information_schema.TABLES WHERE TABLE_SCHEMA=?";
		List<String> result = jdbcTemplate.query(sql,new StringMapper(),serviceType);;
		return result;
	}

	@Override
	public void dropTable(List<String> dropSql, JdbcTemplate jdbcTemplate) {
		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 0;");
		for (String sql:dropSql) {
			jdbcTemplate.execute(sql);
			System.out.println("表删除成功，执行SQL为[" +sql + "]");
		}
		jdbcTemplate.execute("SET FOREIGN_KEY_CHECKS = 1;");
	}

	/**
	 * 获取fat jar所在目录
	 *
	 * @return
	 */
	private String getHomePath() {

		ApplicationHome home = new ApplicationHome(this.getClass());

		File jarDir = home.getDir();
		return jarDir.getPath();

	}
}
