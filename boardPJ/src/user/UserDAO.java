package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO { // DAO: ������ ���̽� ���� ��ü

	private Connection conn; // DB ���� ��ü
	private PreparedStatement pstmt; // �̸� �غ�� ���� ��ü
	private ResultSet rs;

	public UserDAO() { // ������: ���� �� ���� �ڵ����� DB ���� ���
		try {
			String dbURL = "jdbc:mysql://localhost:3306/boardPJ"
					+ "?useUnicode=true&characterEncoding=UTF-8"; //Mysql �ּ� �� ���ڵ�
			String dbID = "root";
			String dbPassword = "1234";
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbID, dbPassword);
		} catch (Exception e) {
			e.printStackTrace(); // � �������� ���
		}
	}

	public int login(String userID, String userPassword) { //�α��� �õ� �޼ҵ�
		String SQL = "SELECT userPassword FROM USER WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			//sql �����ǰ� ���� ��ŷ ����� ���, pstmt�� �̿��� �̸� �ϳ��� ������ �غ�
			//����ǥ�� �ش��ϴ� ������ �������̵�, �Ű������� �̿�/ �������̵� �����ϴ°�? ��й�ȣ�� �����ΰ�?
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery(); //resultset�� ��� ����
			if (rs.next()) { // ����� �����ϸ� ����
				if (rs.getString(1).equals(userPassword)) // �н����� �����ϸ� ����
					return 1; //�α��� ����
				else
					return 0; // ��й�ȣ ����ġ
			}
			return -1; // ���̵� ����
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2; // ������ ���̽� ����
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
			return pstmt.executeUpdate(); //������ ����� 0 �̻��� ���� ��ȯ
		}catch(Exception e) {
			e.printStackTrace();
		}
		return -1; // ������ ���̽� ����
	}
}
