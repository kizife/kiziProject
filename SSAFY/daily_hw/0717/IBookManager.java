package com.ssafy.ws03.step3;

public interface IBookManager {
    void add(Book book);
    void remove(String isbn);
    Book[] getBooks();
    Book[] getMagazines();
    Book searchByIsbn(String isbn);
    Book[] searchByTitle(String title);
    int getTotalPrice();
    double getPriceAvg();
    void buy(String isbn, int quantity) throws ISBNNotFoundException;
    void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException;
    Book[] getList();
}
