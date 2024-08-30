package com.web.mb;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class BookDAO {
    @Autowired
    private BookMapper bookMapper;

    public List<Book> getAllBooks() {
        List<Book> books = bookMapper.bookList();
        return books;
    }

    public int addBook(Book book) {
        int rows = bookMapper.bookAdd(book);
        return rows;
    }

    public Book getBook(int no) {
       return bookMapper.getBook(no);
    }

    public int updateBook(Book book) {
        int rows = bookMapper.updateBook(book);
        return rows;
    }

    public int deleteBook(int no) {
        int rows = bookMapper.deletedBook(no);
        return rows;
    }
}
