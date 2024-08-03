
package com.ssafy.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.ssafy.jdbc.dto.SsafyMemberDto;
import com.ssafy.jdbc.util.DBUtil2;

//DBUtil2를 이용해서 ssafydb에 붙어서 작업합니다. 
public class JDBC5CRUDSsafyMember {

	public static void main(String[] args) {
		JDBC5CRUDSsafyMember test = new JDBC5CRUDSsafyMember();

		// #1
		test.selectAllUser();
		System.out.println("========================");

		//#2
		SsafyMemberDto dto = new SsafyMemberDto("kimssafy101", "김싸피101", "1234", "kimssafy101", "ssafy.com");
		test.insertUser(dto);
		System.out.println("========================");

		//#3
		System.out.println(test.selectUser("1"));
		System.out.println("========================");

//		//#4
		test.updateUser("1", "abcd");
		System.out.println(test.selectUser("1"));
		System.out.println("========================");

//		//#5
		test.deleteUser("kimssafy101");
		System.out.println(test.selectAllUser());
		System.out.println("========================");

	}

	
	
	// #1
	private List<SsafyMemberDto> selectAllUser() {
		ArrayList<SsafyMemberDto> list = new ArrayList<>();

		// 구현해 봅시다.
		String sql = "select * from ssafy_member"; // 실행할 sql쿼리 정의

		// try-with-resources로 자동 닫기
		try (Connection con = DBUtil2.getConnection(); // DB연결 설정
				PreparedStatement pstmt = con.prepareStatement(sql); // sql쿼리 준비
				ResultSet rs = pstmt.executeQuery()) { // 쿼리 실행

			// 각 행 반복하기
			while (rs.next()) {
				System.out.println("idx : " + rs.getString(1) + ", userid : " + rs.getString(2) + ", username" + rs.getString(3) + ", userpwd : "
						+ rs.getString(4) + ", emailid : " + rs.getString(5) + ", emaildomain : " + rs.getString(6)
						+ ", joindate : " + rs.getString(7));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list; // 결과 반환
	}

	
	//#2
	private int insertUser(SsafyMemberDto dto) {
		int result = -1;

		// 구현해 봅시다.
		// sql쿼리 정의
		String sql = "insert into ssafy_member (idx, userid, username, userpwd, emailid, emaildomain) values (?, ?, ?, ?, ?, ?)";

		try (Connection con = DBUtil2.getConnection(); // DB연결 설정
				PreparedStatement pstmt = con.prepareStatement(sql)) { // 쿼리 준비

			// 데이터 추출하고 저장
			pstmt.setInt(1, dto.getIdx());
			pstmt.setString(2, dto.getUserid());
			pstmt.setString(3, dto.getUsername());
			pstmt.setString(4, dto.getUserpwd());
			pstmt.setString(5, dto.getEmailid());
			pstmt.setString(6, dto.getEmaildomain());
			// 특정 자리 '?'에 값을 넣어서 실제 쿼리를 생성함
			// (n번째 자리 표시, 그곳에 설정할 값)

			result = pstmt.executeUpdate(); // 쿼리 실행
			
			System.out.println(result + "건이 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	
	//#3
	private SsafyMemberDto selectUser(String idx) {
		SsafyMemberDto dto = null;

		// 구현해 봅시다.
		String sql = "select * from ssafy_member where idx = ?";

		try (PreparedStatement pstmt = DBUtil2.getConnection().prepareStatement(sql);) {

			pstmt.setString(1, idx);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) { // 하나밖에 없으니까 if
				dto = new SsafyMemberDto();

				dto.setIdx(rs.getInt(1));
				dto.setUserid(rs.getString(2));
				dto.setUsername(rs.getString(3));
				dto.setUserpwd(rs.getString(4));
				dto.setEmailid(rs.getString(5));
				dto.setEmaildomain(rs.getString(6));
				dto.setJoindate(rs.getTimestamp(7));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dto;
	}

	
	//#4
	private int updateUser(String id, String password) {
		int result = -1;

		// 구현해 봅시다.
		String sql = "update ssafy_member set userpwd = ? where idx = ?";

		try (Connection con = DBUtil2.getConnection(); // DB 연결 설정
				PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setString(1, password); // 첫번째자리에 비밀번호 설정
			pstmt.setString(2, id); // 두번째자리에 아이디 설정

			result = pstmt.executeUpdate(); // 수정된 건수 반환
			System.out.println(result + "건이 수정되었습니다.");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;

	}

	
	//#5
	private int deleteUser(String id) {
		int result = -1;

		// 구현해 봅시다.
		String sql = "delete from ssafy_member where userid = ?";
		try (Connection conn = DBUtil2.getConnection(); // DB 연결 설정
				PreparedStatement pstmt = conn.prepareStatement(sql)) { // sql 쿼리 준비

			// PreparedStatement에 데이터 설정
			pstmt.setString(1, id);
			// 첫째자리 ?를 id값으로 대체하는데, 이는 삭제하고자 하는 특정 사용자의 데이터임

			result = pstmt.executeUpdate(); // 삭제된 건수 반환
			System.out.println(result + "건이 삭제되었습니다.");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

}
