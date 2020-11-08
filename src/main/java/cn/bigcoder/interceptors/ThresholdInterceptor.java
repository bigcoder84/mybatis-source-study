package cn.bigcoder.interceptors;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.logging.jdbc.PreparedStatementLogger;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;

import java.sql.Statement;
import java.util.Properties;

/**
 * 获取SQL执行时间，当执行时间达到阈值后输出日志
 */
@Intercepts({
	@Signature(type=StatementHandler.class,method="query",args={Statement.class, ResultHandler.class})
})
public class ThresholdInterceptor implements Interceptor {
	
	private long threshold;

	public long getThreshold() {
		return threshold;
	}

	public void setThreshold(long threshold) {
		this.threshold = threshold;
	}

	public Object intercept(Invocation invocation) throws Throwable {
		long begin = System.currentTimeMillis();
		Object ret = invocation.proceed();
		long end=System.currentTimeMillis();
		long runTime = end - begin;
		if(runTime>=threshold){
			Object[] args = invocation.getArgs();
			Statement stat = (Statement) args[0];
			MetaObject metaObjectStat = SystemMetaObject.forObject(stat);
			PreparedStatementLogger statementLogger = (PreparedStatementLogger)metaObjectStat.getValue("h");
			Statement statement = statementLogger.getPreparedStatement();
			System.out.println("sql语句：\n"+statement.toString()+"\n执行时间为："+runTime+"毫秒，已经超过阈值！");
		}
		return ret;
	}

	public void setProperties(Properties properties) {
		this.threshold = Long.valueOf(properties.getProperty("threshold"));
	}
}
