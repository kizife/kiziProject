package com.ssafy.ws02.step3;

public class BookTest {

    public static void main(String[] args) {
        BookManager bm = new BookManager();
        bm.add(new Book("21424", "Java Pro", "김싸피", "ssafy.kr", 15000, "Java 기본 문법"));
        bm.add(new Book("21425", "Java Pro2", "이싸피", "ssafy.kr", 25000, "Java 응용"));
        bm.add(new Book("35355", "분석설계", "최삼성", "ssafy.kr", 30000, "SW 모델링"));
        bm.add(new Magazine("45678", "월간 알고리즘", "홍길동", "ssafy.kr", 10000, "1 월 알고리즘", 2021, 1));

        System.out.println("**********************도서 전체 목록**********************");
        printBooks(bm.getBooks());
        printMagazines(bm.getMagazines());

        System.out.println("**********************일반 도서 목록**********************");
        printBooks(bm.getBooks());
        

        System.out.println("**********************잡지 목록**********************");
        printMagazines(bm.getMagazines());

        System.out.println("**********************도서 제목 포함검색: Java **********************");
        printBooks(bm.searchByTitle("Java"));
        printMagazines(bm.searchByTitle("Java"));

        System.out.println("도서 가격 총합: " + bm.getTotalPrice()); 
        System.out.println("도서 가격 평균: " + bm.getPriceAvg());

        bm.remove("21425");

        System.out.println("**********************삭제 후 도서 전체 목록**********************");
        printBooks(bm.getBooks());
        printMagazines(bm.getMagazines());

        Book search = bm.searchByIsbn("21424");

        System.out.println("**********************ISBN 검색**********************");
        if (search != null) {
            System.out.println(search);
        } else {
            System.out.println("해당 도서를 찾을 수 없습니다.");
        }
    }

    public static void printAllBooks(BookManager bm) {
        Book[] allBooks = bm.getBooks();
        for (Book book : allBooks) {
            System.out.println(book.toString());
        }
    }

    public static void printBooks(Book[] books) {
        if (books != null) {
            for (Book book : books) {
                System.out.println(book.toString());
            }
        }
    }

    public static void printMagazines(Book[] books) {
        if (books != null) {
            for (Book book : books) {
                if (book instanceof Magazine) {
                    System.out.println(book.toString());
                }
            }
        }
    }
}
