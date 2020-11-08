package cn.bigcoder.service;

import cn.bigcoder.mapper.TestMapper;
import cn.bigcoder.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: TJD
 * @Date: 2020-05-18
 * @DESCRIPTION:
 **/
@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;

    public Student getStudentByIdToResultMap(String id){
        return testMapper.getStudentByIdToResultMap(id);
    }
}
