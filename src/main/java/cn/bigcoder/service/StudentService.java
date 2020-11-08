package cn.bigcoder.service;

import cn.bigcoder.mapper.StudentMapper;
import cn.bigcoder.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: TJD
 * @Date: 2020-05-18
 * @DESCRIPTION:
 **/
@Service
public class StudentService {
    @Autowired
    private StudentMapper studentMapper;

    public Student getStudentByIdToResultMap(String id){
        return studentMapper.getStudentByIdToResultMap(id);
    }
}
