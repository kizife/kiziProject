package com.ssafy.ws02.step3;

import java.util.Iterator;

import java.util.ArrayList;
import java.util.List;

public class BookManager {

	private Book[] list; // 책 정보를 저장하는 배열
	private int bookIndex; // 배열에 책을 저장할 위치	
	private int MAX_SIZE = 100;

	public BookManager() {
		list = new Book[100];
	}

	// 고유번호에 해당하는 책이 등록된 index
	// return = 찾은 도서의 index, 찾지 못한 경우 -1
	private int indexOf(String isbn) {
		for (int i = 0; i < bookIndex; i++) {
			if (isbn.equals(list[i].getIsbn())) {
				return i;
			}
		}
		return -1;
	}

	/* 
	도서 등록하기 
	*/
	// bk = 등록할 도서 정보
	public void add(Book bk) {
		if (bk != null) { // 책 존재 여부 확인
			String isbn = bk.getIsbn();
			if (isbn != null) { // null 아닌 경우만
				int index = indexOf(isbn);
				if (index == -1) { // 새로운 도서임을 확인한 후에는, 배열 크기 확인해야
					if (MAX_SIZE <= bookIndex) { 
						System.out.println("100권이 꽉 찼습니다!");
					} else {
						list[bookIndex] = bk;
						bookIndex++;
					}
				} else {
					System.out.println("이미 존재하는 도서입니다!");
				}
			}
		}
	}

	/* 
	 * 고유번호로 도서를 삭제하기
	 */
	public void remove(String isbn) {
		if (isbn != null) {
			int index = indexOf(isbn);
			if (index >= 0) {
				bookIndex--;
				list[index] = list[bookIndex]; // 빠진 자리로 제일 마지막 도서 옮기기
				list[bookIndex] = null; // 마지막 책 자리 비우기
			}
		}
	}

	/* 
	 * 도서리스트 반환하는 getList 
	*/
	public Book[] getBooks() { // 일반 도서만 반환
		Book[] books = new Book[bookIndex];
		int count = 0;
		for (int i = 0; i < bookIndex; i++) {
			if (list[i] instanceof Book && !(list[i] instanceof Magazine)) {
				books[count++] = (Book) list[i];
			}
		}
		// 배열의 길이를 count로 조정하여 반환
		Book[] result = new Book[count];
		System.arraycopy(books, 0, result, 0, count);
		return result;
	}

	public Magazine[] getMagazines() { // 잡지만 반환
		Magazine[] magazines = new Magazine[bookIndex];
		int count = 0;
		for (int i = 0; i < bookIndex; i++) {
			if (list[i] instanceof Magazine) {
				magazines[count++] = (Magazine) list[i];
			}
		}
		// 배열의 길이를 count로 조정하여 반환
		Magazine[] result = new Magazine[count];
		System.arraycopy(magazines, 0, result, 0, count);
		return result;
	}

	/*
	 * 고유번호로 도서 정보 찾기
	*/
	public Book searchByIsbn(String isbn) {
		if (isbn != null) {
			int index = indexOf(isbn);
			if (index >= 0) {
				return list[index];
			}
		}
		return null;
	}

	/*
	제목 일부 포함 검색
	*/
	public Book[] searchByTitle(String title) {
	    if (title != null) {
	        List<Book> result = new ArrayList<>(); //검색결과 저장할 ArrayList
	        for (int i = 0; i < bookIndex; i++) { //0부터 bookIndex-1까지 반복
	            if (list[i].getTitle().contains(title)) { //각 도서 제목(list[i].getTitle())이 title을 포함하는지 확인
	                result.add(list[i]); //title 포함한다면 result 리스트에 도서 추가
	            }
	        }
	        return result.toArray(new Book[result.size()]); //result리스트를 배열로 변환하여 반환
	    }
	    return null;
	}
	

	/* 
	 */
	
	public int getTotalPrice() {
        int totalPrice = 0;
        for (int i = 0; i < bookIndex; i++) {
            totalPrice += list[i].getPrice();
        }
        return totalPrice;
    }

    public double getPriceAvg() {
        if (bookIndex == 0) {
            return 0;
        }

        int totalPrice = getTotalPrice();
        return (double) totalPrice / bookIndex;
    }
}
