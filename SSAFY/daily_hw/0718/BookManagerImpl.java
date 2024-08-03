package com.ssafy.ws04.step3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookManagerImpl implements IBookManager, Serializable {
	private List<Book> list;
	private static BookManagerImpl instance;
	private final int MAX_SIZE = 100;
	private static final String DATA_FILE = "book.dat";

	private BookManagerImpl() {
		list = new ArrayList<>();
		loadData(); // 객체 생성 시 데이터 파일에서 데이터를 읽어옴
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
					saveData(); // 추가. 도서 추가시 저장
				} else {
					System.out.println("100권이 꽉 찼습니다.");
				}
			} else {
				System.out.println("이미 존재하는 도서입니다.");
			}
		}
		saveData();
	}

	@Override
	public void remove(String isbn) {
		if (isbn != null) {
			int index = indexOf(isbn);
			if (index >= 0) {
				list.remove(index);
				saveData(); // 도서 삭제시 저장
			} else {
				System.out.println("해당 도서를 찾을 수 없습니다.");
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
		saveData(); // 재고 변경시 저장
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
		saveData(); // 재고 변경시 저장
	}

	///////*****
	@Override
	public Book[] getList() {
		return list.toArray(new Book[list.size()]);
	}

	// loadData 메서드 추가(별도의 Thread 활용하여)

	// book.dat 파일에서 도서리스트 읽어오는 메서드 (loadData)
//	public void loadData() { //싱크로라이즈드??
//		Thread thread = new Thread(() -> { //데이터 로드하는 스레드 생성
//			File dataFile = new File("book.dat");
//			if (dataFile.exists()) { //파일 존재하는지 확인
//				try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataFile))) {
//					//파일에서 객체 읽어오기 위한 입력 스트림. try문 사용해서 자동 닫힘
//					list = (List<Book>) ois.readObject(); //파일에서 읽어온 객체를 list에 할당 + List<Book> 타입으로 캐스팅
//				} catch (IOException | ClassNotFoundException e) {
//					e.printStackTrace();
//				} //예외 발생시 스택 추적 정보 출력
//			} else {
////            	System.out.println("**********************불러온 도서 전체 목록**********************");
//				System.out.println("등록된 도서가 없습니다."); //데이터 파일 없으면 출력
//			}
//		});
//
//		thread.start(); //앞에서 정의한 스레드 시작
//
//
//		try {
//			thread.join(); //스레드가 작업을 마칠 때까지 기다리기
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
//
//	// 도서리스트를 book.dat파일에 저장하는 메서드 (saveData)
//	public void saveData() {
//		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("book.dat"))) {
//			oos.writeObject(list); //객체를 직렬화해서 리스트에 저장
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//	}

	public synchronized void loadData() {
		Thread loadThread = new Thread(() -> {
			synchronized (list) {
				File file = new File(DATA_FILE);
				if (file.exists()) {
					try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
						list = (List<Book>) ois.readObject();
					} catch (IOException | ClassNotFoundException e) {
						System.out.println("오류가 발생했습니다: " + e.getMessage());
					}
				} else {
					System.out.println("**********************불러온 도서 전체 목록**********************");
					System.out.println("등록된 도서가 없습니다.");
					list = new ArrayList<>();  // 파일이 없을 경우 빈 리스트로 초기화
				}
			}
		});
		loadThread.start();
	}

	public synchronized void saveData() {
		Thread saveThread = new Thread(() -> {
			synchronized (list) {
				try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
					oos.writeObject(list);
				} catch (IOException e) {
					System.out.println("데이터 파일 저장 중 오류가 발생했습니다: " + e.getMessage());
				}
			}
		});
		saveThread.start();
	}
}
