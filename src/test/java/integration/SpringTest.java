package integration;

import cn.bigcoder.mapper.TestMapper;
import cn.bigcoder.pojo.Student;
import cn.bigcoder.service.TestService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: TJD
 * @Date: 2020-05-05
 * @DESCRIPTION:
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:integration-config/applicationContext.xml")
public class SpringTest {
    @Autowired
    private TestMapper testMapper;

    @Autowired
    private TestService testService;

    @Test
    @Transactional
    public void test(){
        Student student = testService.getStudentByIdToResultMap("00000b373502481baa1a5f5229507cf8");
        System.out.println(student);
        Student student2 = testService.getStudentByIdToResultMap("00000b373502481baa1a5f5229507cf8");
        System.out.println(student2);
    }
}
