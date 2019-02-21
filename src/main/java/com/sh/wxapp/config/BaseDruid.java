package com.sh.wxapp.config;

/**
 * @author zhangjianjun
 * @date 2018年7月6日
 *  用于映射properties文件中druid的配置数据
 */
public class BaseDruid {
	
	private String url;
	
	private String userName;
    
    private String password;
    
    private String driverClass;

    private Integer maxActive;
    
    private Integer minIdle;
    
    private Integer initialSize;
    
    private Boolean testOnBorrow;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public Integer getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(Integer maxActive) {
		this.maxActive = maxActive;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	public Integer getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(Integer initialSize) {
		this.initialSize = initialSize;
	}

	public Boolean getTestOnBorrow() {
		return testOnBorrow;
	}

	public void setTestOnBorrow(Boolean testOnBorrow) {
		this.testOnBorrow = testOnBorrow;
	}

	@Override
	public String toString() {
		return String.format(
				"DruidMeta [url=%s, userName=%s, password=%s, driverClass=%s, maxActive=%s, minIdle=%s, initialSize=%s, testOnBorrow=%s]",
				url, userName, password, driverClass, maxActive, minIdle, initialSize, testOnBorrow);
	}

}
