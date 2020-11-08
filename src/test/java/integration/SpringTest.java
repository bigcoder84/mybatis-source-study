package integration;

import cn.bigcoder.mapper.StudentMapper;
import cn.bigcoder.pojo.Student;
import cn.bigcoder.service.StudentService;
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
    private StudentMapper studentMapper;

    @Autowired
    private StudentService studentService;

    @Test
    public void test(){
        Student student = studentService.getStudentByIdToResultMap("00000b373502481baa1a5f5229507cf8");
        System.out.println(student);
        Student student2 = studentService.getStudentByIdToResultMap("00000b373502481baa1a5f5229507cf8");
        System.out.println(student2);
    }
}
