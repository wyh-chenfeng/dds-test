package common.annotation.datasource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource1 extends AbstractRoutingDataSource {
	
	// 扫描数据源的时间间隔（单位分）
	private int timeSpan = 5;
		
	// 默认数据源
	private DataSource _defaultTargetDataSource;
	
	// 读数据源标记
	private int slaveFlag = 0;
	
	// 读数据源列表
	private List<BasicDataSource> dataSources = new ArrayList<BasicDataSource>();
	
	@Override
    protected Object determineCurrentLookupKey() {
        // TODO Auto-generated method stub
        return DynamicDataSourceHandler.getDataSouce();
    }
	
	// 返回毫秒数
	public long getTimeSpan() {
		return timeSpan * 60 * 100;
	}

	public void setTimeSpan(int timeSpan) {
		if (timeSpan <= 0) {
            throw new IllegalArgumentException("TimeSpan cannot be less than 0");
        }
		
		this.timeSpan = timeSpan;
		// 首次执行定时器
		getDataSources();
	}
	
	@Override
	protected DataSource determineTargetDataSource() {
		Object lookupKey = determineCurrentLookupKey();
		
		DataSource dataSource = null;
		// 返回从数据源
		if (cn.smart.dds.DataSource.SLAVE.equals(lookupKey)) {
			return getDataSource();
		} 
		
		// 返回主数据源
		if (dataSource == null) {
			dataSource = (DataSource) this._defaultTargetDataSource;
		}
		
		if (dataSource == null) {
			throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
		}
		
		return dataSource;
	}
	
	/**
	 * 生成数据源
	 * @param driverClassName 驱动名称
	 * @param url 数据库连接地址
	 * @param username 数据库用户名
	 * @param password 数据库密码
	 * @return BasicDataSource
	 * @author wangyuhao
	 * @date 2015年11月26日 下午1:38:31
	 */
	public BasicDataSource createDataSource(String driverClassName, String url,
			String username, String password) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setTestWhileIdle(true);
		return dataSource;
	}

	/**
	 * @describe 获取数据源属性
	 * @param serverId
	 * @return
	 */
	public DataSource getDataSource() {
		// 标记回滚
		if (slaveFlag == Integer.MAX_VALUE) {
			slaveFlag = 0;
		}
		// 获取读数据源
		synchronized (dataSources) {
			// 没有找到数据源就返回默认数据源
			if (dataSources.isEmpty()) {
				return _defaultTargetDataSource;
			}
			return dataSources.get(slaveFlag++%dataSources.size());
		}
	}

	/**
	 * 获取数据源列表
	 * @return 
	 * @author wangyuhao
	 * @date 2015年11月26日 下午2:04:32
	 */
	public void getDataSources() {
		
        // 得到定时器实例
        Timer timer = new Timer();
        // 使用匿名内方式进行方法覆盖
        timer.schedule(new TimerTask() {
            public void run() {
                logger.debug("重置数据源列表");
            	Connection conn = null;
            	PreparedStatement ps = null;
            	ResultSet rs = null;
            	try {
            		conn = _defaultTargetDataSource.getConnection();
            		ps = conn.prepareStatement("SELECT * FROM base_datasource");
            		rs = ps.executeQuery();
            		
            		synchronized (dataSources) {
            			// 关闭连接池（这里有问题，如果这个连接真在使用，现在关掉连接 是有问题的）
            			for (BasicDataSource basicDataSource : dataSources) {
							basicDataSource.close();
						}
            			// 清空数据源列表
            			dataSources.clear();
            			
            			// 重置数据源列表
            			while (rs.next()) {
            				String driverClassName = rs.getString("DBS_DriverClassName");
            				String url = rs.getString("DBS_URL");
            				String userName = rs.getString("DBS_UserName");
            				String password = rs.getString("DBS_Password");
            				logger.debug("jdbc url:" + url);
            				// 创建数据源
            				BasicDataSource dataSource = createDataSource(driverClassName,
            						url, userName, password);
            				
            				// 将数据源放到列表
            				dataSources.add(dataSource);
            			}
					}
            	} catch (SQLException e) {
            		logger.error(e);
            	} finally {
            		try {
            			if (rs != null) {
            				rs.close();
						}
            			if (ps != null) {
            				ps.close();
						}
            			if (conn != null) {
            				conn.close();
						}
            		} catch (SQLException e) {
            			logger.error(e);
            		}
            	}
            }
        }, new Date(), getTimeSpan());
	}
	
}