package com.web.emp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository //db다룰거임
public class EmpDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public List<Emp> empList(){
        String sql = "SELECT * FROM emp2";
        List<Emp> list = jdbcTemplate.query(sql, (rs,i)->{
            Emp emp = new Emp();
            emp.setEname(rs.getString("ENAME"));
            emp.setEmpno(rs.getInt("EMPNO"));
            emp.setSal(rs.getInt("SAL"));
            emp.setJob(rs.getString("JOB"));
            emp.setHiredate(rs.getString("HIREDATE"));
            return emp;
        });
        return list;
    }

    public Emp getEmpByNo(int empno) {
        String sql = "SELECT * FROM emp2 WHERE empno = ?";

        Emp emp = jdbcTemplate.queryForObject(sql,
                new Object[]{empno},
                (rs, rowNum) -> {
                    Emp e = new Emp();
                    e.setEmpno(rs.getInt("EMPNO"));
                    e.setEname(rs.getString("ENAME"));
                    e.setSal(rs.getInt("SAL"));
                    e.setJob(rs.getString("JOB"));
                    e.setHiredate(rs.getString("HIREDATE"));
                    return e;
                }
        );
        return emp;
    }


    public Boolean updateEmp(Emp emp) {
        String sql = "UPDATE emp2 SET job=?, sal=? WHERE empno=?";
        log.info("updatedao왔음");
        int rows = jdbcTemplate.update(sql, emp.getJob(), emp.getSal(), emp.getEmpno());
        log.info("rows지남");
        return rows>0;
    }

    public boolean deleteEmp(int empno) {
        String sql = "DELETE FROM emp2 WHERE empno=?";
        int rows = jdbcTemplate.update(sql,empno);
        return rows>0;
    }
}
