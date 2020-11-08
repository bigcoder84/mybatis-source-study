package mybatis.logging;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.Test;

/**
 * @Auther: TJD
 * @Date: 2020-04-02
 * @DESCRIPTION:
 **/
public class LoggingTest {
    @Test
    public void testLogFactory() throws ClassNotFoundException {
        Class<?> aClass = Class.forName("org.apache.ibatis.logging.LogFactory");
    }

    @Test
    public void testLogFactory2() throws ClassNotFoundException {
        Log log = LogFactory.getLog(this.getClass());
    }
}
