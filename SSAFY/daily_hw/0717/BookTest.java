package com.ssafy.ws03.step3;

public class BookTest {
    public static void main(String[] args) {
        IBookManager bm = BookManagerImpl.getInstance();
        bm.add(new Book("21424", "Java Pro", "김싸피", "ssafy.kr", 15000, "Java 기본 문법", 10));
        bm.add(new Book("21425", "Java Pro2", "이싸피", "ssafy.kr", 25000, "Java 응용", 20));
        bm.add(new Book("35355", "분석설계", "최삼성", "ssafy.kr", 30000, "SW 모델링", 30));
        bm.add(new Magazine("45678", "월간 알고리즘", "홍길동", "ssafy.kr", 10000, "1 월 알고리즘", 40, 2021, 1));

        System.out.println("**********************도서 전체 목록**********************");
        Book[] books = bm.getList();
        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("**********************일반 도서 목록**********************");
        books = bm.getBooks();
        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("**********************잡지 목록**********************");
        books = bm.getMagazines();
        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("**********************도서 제목 포함검색:Java**********************");
        books = bm.searchByTitle("Java");
        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("도서 가격 총합: " + bm.getTotalPrice());
        System.out.println("도서 가격 평균: " + bm.getPriceAvg());

        bm.remove("21425");
        System.out.println("**********************삭제 후 도서 전체 목록**********************");
        books = bm.getList();
        for (Book book : books) {
            System.out.println(book);
        }

        Book search = bm.searchByIsbn("21424");
        System.out.println("**********************ISBN 검색**********************");
        System.out.println(search);

        System.out.println("**********************도서판매:21424,11 개**********************");
        try {
            bm.sell("21424", 11);
        } catch (QuantityException e) {
            System.out.println(e.getMessage());
        } catch (ISBNNotFoundException e) {
            System.out.println(e.getMessage() + " : " + e.getIsbn());
        }

        System.out.println("**********************도서구매:21424,10 개**********************");
        try {
            bm.buy("21424", 10);
        } catch (ISBNNotFoundException e) {
            System.out.println(e.getMessage() + " : " + e.getIsbn());
        }

        System.out.println("**********************도서판매:21424,11 개**********************");
        try {
            bm.sell("21424", 11);
        } catch (QuantityException e) {
            System.out.println(e.getMessage());
        } catch (ISBNNotFoundException e) {
            System.out.println(e.getMessage() + " : " + e.getIsbn());
        }

        System.out.println("**********************존재하지 않는 도서 판매**********************");
        try {
            bm.buy("99999", 100);
        } catch (ISBNNotFoundException e) {
            System.out.println(e.getMessage() + " : " + e.getIsbn());
        }
    }
}
