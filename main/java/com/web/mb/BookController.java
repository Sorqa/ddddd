package com.web.mb;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.web.emp.EmpDAO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private BookService bookService;
    @GetMapping("/search")
    public String searchForm(){
        return "th/mb/bookSearch";
    }

    @PostMapping("/search")
    @ResponseBody
    public List<Book> searchBook(@RequestParam Map<String,String> info){
        log.info("search form : {}", info);
        List<Book> books = bookMapper.searchBook(info);
        return books;
    }

    @GetMapping("/search/{low}/{high}")
    @ResponseBody
    public List<Book> searchPrice(@PathVariable int low, @PathVariable int high){
        log.info("search range : {}/{}", low,high);
        List<Book> books = bookMapper.searchPrice(low,high);
        return books;
    }

    @GetMapping({"/search/author","/search/author/{author}"})
    @ResponseBody
    public List<Book> searchAuthor(@PathVariable (required=false) String author){
        log.info("search range : {}", author);
        List<Book> books = bookMapper.searchAuthor(author);
        return books;
    }


//dfsf
    @GetMapping({"/search/pubAndPrice"})
    @ResponseBody
    public List<Book> searchByPubAndPrice(Book book){
        log.info("search range : {}/{}", book.getPublisher(),book.getPrice());

        List<Book> books = bookMapper.PubAndPrice(book);
        return books;
    }

    @GetMapping("/list")
    public String list(Model  model){

        List<Book> books = bookDAO.getAllBooks();
        model.addAttribute("books",books);
        return "th/mb/bookList";

    }

    @GetMapping("/addForm")
    public String addForm(){
        return "th/mb/bookInput";
    }

    @PostMapping("/bookAdd")
    public String addBook(@ModelAttribute("book") Book book){
        log.info("add book : {}", book);
        int rows = bookService.addBook(book);
        log.info("Inserted book no: {}", book.getNo()); // 책의 no 출력
        if (rows > 0) {
            return "redirect:/book/bookDetail/" + book.getNo(); // book.getNo()로 리디렉션
        }
        return "redirect:/book/addForm";
    }


    @GetMapping("/bookDetail/{no}")
    public String bookDetail(@PathVariable("no") int no, Model model) {
        Book book = bookService.getBook(no);
        model.addAttribute("book", book);
        return "th/mb/bookDetail";
    }

    @GetMapping("/update/{no}")
    public String updateForm(@PathVariable("no") int no, Model model){
        Book book = bookDAO.getBook(no);
        model.addAttribute("book", book);
        return "th/mb/bookUpdateForm";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") Book book){
        log.info("update book : {}", book);
        int rows = bookDAO.updateBook(book);
        log.info("Inserted book no: {}", book.getNo()); // 책의 no 출력

        if (rows > 0) {
            return "redirect:/book/bookDetail/" + book.getNo(); // book.getNo()로 리디렉션
        }
        return "redirect:/book/update/" + book.getNo();
    }

    @GetMapping("/delete/{no}")
    @ResponseBody
    public Map<String,Boolean> delete(@PathVariable("no") int no){
        int rows = bookDAO.deleteBook(no);
        Map<String,Boolean> map = new HashMap<>();
        map.put("deleted",rows>0);
        return map;
    }

    @GetMapping("/list/page/{page}")
    public String getPage(@PathVariable int page, Model model){
        log.info("getPage : {}", page);
        PageHelper.startPage(page,3);  // 한 화면에 3개 아이템
        PageInfo<Book> pageInfo = new PageInfo<>(bookMapper.getList(), 10); //밑에 3페이지와 관련된 10개?

        model.addAttribute("pageInfo", pageInfo);
        return "th/mb/bookList";
    }

}
