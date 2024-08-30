package com.web.mb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Repository
public class BookService {
    @Autowired
    private BookDAO bookDAO;

    public int addBook(Book book) {
        return bookDAO.addBook(book);
    }

    public Book getBook(int no) {
        return bookDAO.getBook(no);
    }


}
