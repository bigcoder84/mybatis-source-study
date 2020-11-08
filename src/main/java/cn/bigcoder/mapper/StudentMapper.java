package cn.bigcoder.mapper;

import cn.bigcoder.pojo.Student;
import org.apache.ibatis.annotations.Param;

/**
 * @Auther: TJD
 * @Date: 2020-03-30
 * @DESCRIPTION:
 **/
public interface StudentMapper {
    Student getStudentByIdToResultType(@Param("id") String id);

    Student getStudentByIdToResultMap(@Param("id") String id);

    Student getStudentByNameAndPassWord(@Param("name") String name, @Param("password") String password);

    Student getStudentByEntity(Student student);
}
