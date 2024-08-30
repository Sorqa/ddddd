package com.web.mb;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface BookMapper {

    List<Book> searchBook(Map<String, String> info);

    List<Book> searchPrice(@Param("low") int low, @Param("high") int high);

    List<Book> searchAuthor(String author);

    List<Book> PubAndPrice(Book book);

    List<Book> bookList();

    int bookAdd(Book book);

    Book getBook(int no);

    int updateBook(Book book);

    int deletedBook(int no);

    List<? extends Book> getList();
}
