package com.github.tsiangleo.bookstore.book.service.impl;

import com.github.tsiangleo.bookstore.book.domain.Book;
import com.github.tsiangleo.bookstore.book.service.BookService;
import com.github.tsiangleo.bookstore.book.service.ServiceException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by tsiang on 2017/6/15.
 */
public class BookServiceImpl implements BookService {
    private static List<Book> bookList = new ArrayList<Book>();

    static {
        for(int i = 100;i<=110;i++){
            Book book = new Book();
            book.setAuthor("Autor-"+i);
            book.setId((long) i);
            book.setIsbn("isbn-" + i);
            book.setName("name-" + i);
            book.setPages(i - 20);
            book.setPrice(i + 20.5);
            book.setPress("press-" + i);
            book.setPublicationDate(new Date());
            book.setOriginalAuthor("originalAuthor-" + i);
            book.setOriginalName("originalName-" + i);
            book.setInventory(1000+i);

            int picid = 29405000+i;
            book.setPicURL("https://img3.doubanio.com/lpic/s"+picid+".jpg");
            bookList.add(book);
        }
    }

    @Override
    public void save(Book book) throws ServiceException {
        bookList.add(book);
    }

    @Override
    public List<Book> list() throws ServiceException {
        return bookList;
    }
}
