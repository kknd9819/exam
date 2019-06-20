package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.util.Properties;


public class JdbcUtil {

	private static DataSource ds;

	static{
		Properties prop = new Properties();
		try {
			prop.load(JdbcUtil.class.getResourceAsStream("/druid.properties"));
			ds = DruidDataSourceFactory.createDataSource(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static DataSource getDataSource(){
		return ds;
	}


	

}
