package com.web.emp;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
public class Emp {
    private int empno;
    private String ename;
    private String job;
    private int sal;
    private java.sql.Date hiredate;

    public void setHiredate(String sDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date udate = sdf.parse(sDate);
            this.hiredate = new java.sql.Date(udate.getTime());
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
