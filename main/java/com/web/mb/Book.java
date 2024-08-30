package com.web.mb;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;

@Data
@NoArgsConstructor
public class Book {
    private int no;
    private String title;
    private String author;
    private String publisher;
    private String pubdate;
    private int pages;
    private int price;
    private String cover;

    /*
    public void setSDate(java.sql.Date publishdate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            java.util.Date udate = sdf.parse(sDate);
            this.publishdate = new java.sql.Date(udate.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
