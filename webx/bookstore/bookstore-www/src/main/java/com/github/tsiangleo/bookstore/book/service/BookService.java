package com.github.tsiangleo.bookstore.book.service;

import com.github.tsiangleo.bookstore.book.domain.Book;

import java.util.List;

/**
 * Created by tsiang on 2017/6/15.
 */
public interface BookService {
    void save(Book book) throws ServiceException;
    List<Book> list() throws ServiceException;
}
