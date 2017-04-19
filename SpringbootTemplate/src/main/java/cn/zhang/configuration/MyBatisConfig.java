/**
 * 
 */
package cn.zhang.configuration;


import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * @author hiynn
 * @Date 2016年6月22日上午9:48:42
 */

@Configuration 
@EnableTransactionManagement 
public class MyBatisConfig implements TransactionManagementConfigurer {

	@Autowired
	OracleDataSoruceSetting dataSourceSetting;

	@Bean 
	public DataSource dataSource() {
		//阿里巴巴数据源
		DruidDataSource ds = new DruidDataSource(); 
		
 		ds.setDriverClassName(dataSourceSetting.getDriver());
 		
		ds.setUsername(dataSourceSetting.getUsername()); 
		
 		ds.setPassword(dataSourceSetting.getPassword()); 
 		
 		ds.setUrl(dataSourceSetting.getUrl()); 
 		
		ds.setMaxActive(dataSourceSetting.getMaxActive()); 
		
 		return ds; 
 	} 

	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactoryBean() {
		
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		
		bean.setDataSource(dataSource());
		
		bean.setTypeAliasesPackage("cn.zhang.model");


		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver
					.getResources("classpath:mapper/*.xml"));
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	  @Bean 
	  public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) { 
		  
          return new SqlSessionTemplate(sqlSessionFactory); 
          
	  } 
	   
	   
	@Bean  
	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		
		return  new DataSourceTransactionManager(dataSource());
	}

}
