package mybatis.base;

import cn.bigcoder.mapper.TestMapper;
import cn.bigcoder.pojo.Student;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * @Auther: TJD
 * @Date: 2020-03-30
 * @DESCRIPTION:
 **/
public class MyBatisTest {
    @Test
    public void testMyBatisBuild() throws IOException {
        InputStream input = Resources.getResourceAsStream("mybatis-only-config/SqlSessionConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(input);
        SqlSession sqlSession = sessionFactory.openSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        Student student = mapper.getStudentByIdToResultType("00000b373502481baa1a5f5229507cf8");
        Student student2 = mapper.getStudentByIdToResultType("00000b373502481baa1a5f5229507cf8");
        //TestMapper mapper2 = sqlSession.getMapper(TestMapper.class);
        //Student student3 = mapper2.getStudentByIdToResultType("00000b373502481baa1a5f5229507cf8");
        //Student student4 = mapper2.getStudentByIdToResultType("00000b373502481baa1a5f5229507cf8");
        System.out.println(student);
    }

    @Test
    /**
     * 多参数调用，为了分析MapperMethod::execute方法中调用method.convertArgsToSqlCommandParam(args);方法解析参数的过程
     */
    public void testManyParameter() throws IOException {
        InputStream input = Resources.getResourceAsStream("mybatis-only-config/SqlSessionConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(input);
        SqlSession sqlSession = sessionFactory.openSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        Student student = mapper.getStudentByNameAndPassWord("83196", "123456");
        System.out.println(student);
    }

    @Test
    /**
     * 单个实体封装多个参数调用，为了分析MapperMethod::execute方法中调用method.convertArgsToSqlCommandParam(args);方法解析参数的过程
     */
    public void testEntity() throws IOException {
        InputStream input = Resources.getResourceAsStream("mybatis-only-config/SqlSessionConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(input);
        SqlSession sqlSession = sessionFactory.openSession();
        Student student = new Student();
        student.setName("83196");
        student.setPassword("123456");
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        mapper.getStudentByEntity(student);
    }

    /**
     *
     */
    @Test
    public void testSqlSessinSingleThread() throws IOException {
        InputStream input = Resources.getResourceAsStream("mybatis-only-config/SqlSessionConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(input);
        SqlSession sqlSession1 = sessionFactory.openSession();
        SqlSession sqlSession2 = sessionFactory.openSession();
        System.out.println(sqlSession1 == sqlSession2);
    }

    /**
     * 测试自动映射
     */
    @Test
    public void testAutoMapping() throws IOException {
        InputStream input = Resources.getResourceAsStream("mybatis-only-config/SqlSessionConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(input);
        SqlSession sqlSession = sessionFactory.openSession();
        TestMapper mapper = sqlSession.getMapper(TestMapper.class);
        Student student = mapper.getStudentByIdToResultMap("00000b373502481baa1a5f5229507cf8");
        System.out.println(student);
    }

    /**
     * 测试自动映射
     */
    @Test
    public void testSqlSession() throws IOException {
        InputStream input = Resources.getResourceAsStream("mybatis-only-config/SqlSessionConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(input);
        SqlSession sqlSession = sessionFactory.openSession();
        Object obj = sqlSession.selectOne("cn.tjd.mapper.TestMapper.getStudentByIdToResultMap",
                "00000b373502481baa1a5f5229507cf8");
        System.out.println(obj);
    }

    /**
     * 测试getMapper中SqlSession单例问题
     */
    @Test
    public void testSqlSessionSingle() throws IOException {
        InputStream input = Resources.getResourceAsStream("mybatis-only-config/SqlSessionConfig.xml");
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(input);
        SqlSession sqlSession = sessionFactory.openSession();
        TestMapper testMapper = sqlSession.getMapper(TestMapper.class);
        System.out.println(testMapper.getStudentByIdToResultMap("dsa"));
        TestMapper testMapper2 = sqlSession.getMapper(TestMapper.class);
        System.out.println(testMapper2.getStudentByIdToResultMap("dsa"));
    }
}
