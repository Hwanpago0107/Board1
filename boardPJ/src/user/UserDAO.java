package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO { // DAO: 데이터 베이스 접근 객체

	private Connection conn; // DB 연결 객체
	private PreparedStatement pstmt; // 미리 준비된 문장 객체
	private ResultSet rs;

	public UserDAO() { // 생성자: 실행 시 마다 자동으로 DB 연결 기능
		try {
			String dbURL = "jdbc:mysql://localhost:3306/boardPJ"
					+ "?useUnicode=true&characterEncoding=UTF-8"; //Mysql 주소 및 인코딩
			String dbID = "root";
			String dbPassword = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace(); // 어떤 오류인지 출력
		}
	}

	public int login(String userID, String userPassword) { //로그인 시도 메소드
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			//sql 인젝션과 같은 해킹 기법을 방어, pstmt를 이용해 미리 하나의 문장을 준비
			//물음표에 해당하는 내용을 유저아이디, 매개변수로 이용/ 유저아이디가 존재하는가? 비밀번호가 무엇인가?
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //resultset에 결과 보관
			if (rs.next()) { // 결과가 존재하면 실행
				if (rs.getString(1).equals(userPassword)) // 패스워드 존재하면 실행
					return 1; //로그인 성공
				else
					return 0; // 비밀번호 불일치
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터 베이스 오류
	}
	
	public int join(User user) {
		String SQL = "INSERT INTO USER VALUES (?, ?, ?, ?, ?)";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, user.getUserID());
			pstmt.setString(2, user.getUserPassword());
			pstmt.setString(3, user.getUserName());
			pstmt.setString(4, user.getUserGender());
			pstmt.setString(5, user.getUserEmail());
			return pstmt.executeUpdate(); //성공적 수행시 0 이상의 값이 반환
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; // 데이터 베이스 오류
	}
}
