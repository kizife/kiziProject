package com.ssafy.ws03.step3;

import java.util.ArrayList;
import java.util.List;

public class BookManagerImpl implements IBookManager {
    private List<Book> list;
    private static BookManagerImpl instance;
    private final int MAX_SIZE = 100;

    private BookManagerImpl() {
        list = new ArrayList<>();
    }

    public static BookManagerImpl getInstance() {
        if (instance == null) {
            instance = new BookManagerImpl();
        }
        return instance;
    }

    private int indexOf(String isbn) {
        for (int i = 0; i < list.size(); i++) {
            if (isbn.equals(list.get(i).getIsbn())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void add(Book book) {
        if (book != null && book.getIsbn() != null) {
            int index = indexOf(book.getIsbn());
            if (index == -1) {
                if (MAX_SIZE > list.size()) {
                    list.add(book);
                } else {
                    System.out.println("도서 리스트가 가득 찼습니다.");
                }
            } else {
                System.out.println("이미 존재하는 도서입니다.");
            }
        }
    }

    @Override
    public void remove(String isbn) {
        if (isbn != null) {
            int index = indexOf(isbn);
            if (index >= 0) {
                list.remove(index);
            }
        }
    }

    @Override
    public Book[] getBooks() {
        List<Book> result = new ArrayList<>();
        for (Book book : list) {
            if (!(book instanceof Magazine)) {
                result.add(book);
            }
        }
        return result.toArray(new Book[result.size()]);
    }

    @Override
    public Book[] getMagazines() {
        List<Magazine> result = new ArrayList<>();
        for (Book book : list) {
            if (book instanceof Magazine) {
                result.add((Magazine) book);
            }
        }
        return result.toArray(new Book[result.size()]);
    }

    @Override
    public Book searchByIsbn(String isbn) {
        if (isbn != null) {
            for (Book book : list) {
                if (isbn.equals(book.getIsbn())) {
                    return book;
                }
            }
        }
        return null;
    }

    @Override
    public Book[] searchByTitle(String title) {
        if (title != null) {
            List<Book> result = new ArrayList<>();
            for (Book book : list) {
                if (book.getTitle().contains(title)) {
                    result.add(book);
                }
            }
            return result.toArray(new Book[result.size()]);
        }
        return null;
    }

    @Override
    public int getTotalPrice() {
        int totalPrice = 0;
        for (Book book : list) {
            totalPrice += book.getPrice();
        }
        return totalPrice;
    }

    @Override
    public double getPriceAvg() {
        if (list.isEmpty()) {
            return 0;
        }
        return (double) getTotalPrice() / list.size();
    }

    @Override
    public void buy(String isbn, int quantity) throws ISBNNotFoundException {
        Book book = searchByIsbn(isbn);
        if (book == null) {
            throw new ISBNNotFoundException(isbn);
        }
        book.setQuantity(book.getQuantity() + quantity);
        System.out.println(book);
    }

    @Override
    public void sell(String isbn, int quantity) throws ISBNNotFoundException, QuantityException {
        Book book = searchByIsbn(isbn);
        if (book == null) {
            throw new ISBNNotFoundException(isbn);
        }
        if (book.getQuantity() < quantity) {
            throw new QuantityException("재고가 부족합니다.");
        }
        book.setQuantity(book.getQuantity() - quantity);
        System.out.println(book);
    }

    @Override
    public Book[] getList() {
        return list.toArray(new Book[list.size()]);
    }
}

/*
Day2 데일리 실습을 바탕으로 하였지만,
어째서인지 Day3 실습 수행을 위해 코드를 수정할수록 먼 길을 돌아가는 것 같아서
이번 과제는 인터넷의 힘을 많이 빌렸습니다.
따라서 지난번 코드나 제 실력에 비해 부자연스러운 부분, 혹은 과하게 잘 된 부분이 있을지도 모르겠습니다...!
일단 구현은 되지만 온전히 제 힘으로 한 것이 아니라서
주말에 추가적인 학습이 필요할 것 같아... 메모 겸 작성합니다.
 */