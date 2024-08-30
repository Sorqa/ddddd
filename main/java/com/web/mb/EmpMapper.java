package com.web.mb;

import com.web.emp.Emp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpMapper {    //sql연결ㄷ할때 사용하는 인터페이스
    public List<Emp> getList();

    Emp getEmpByNo(int empno);

    int updateEmp(Emp emp);

    int delEmp(int empno);
}
