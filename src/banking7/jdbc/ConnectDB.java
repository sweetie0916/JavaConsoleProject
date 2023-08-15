package banking7.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/* 상속을 목적으로 생성하는 추상클래스. 오라클 DB연결과 자원반납을
담당한다. 추상클래스는 객체를 생성할수는 없지만 참조변수선언, 상속
등의 기능은 사용할 수 있다. */
public abstract class ConnectDB {
	//멤버변수 : 상속관계에서 접근가능한 접근지정자로 지정함
	protected Connection con; //DB연결
	protected Statement stmt; //정적쿼리문 실행
	protected ResultSet rs; //select실행 후 결과를 반환받음
	
	//생성자1 : 계정아이디,패스워드를 매개변수로 받아 사용
	public ConnectDB(String id, String pw) {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			//연결정보에 매개변수를 사용한다. 
			con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:xe", 
				id, pw);
			System.out.println("오라클 DB 연결성공");
		}
		catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 로딩 실패");
			e.printStackTrace();
		}
		catch (SQLException e) {
			System.out.println("DB 연결 실패");
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println("알수 없는 예외발생");
			e.printStackTrace();
		}
	}
	/* 생성자2 : 앞에서 선언한 생성자를 복사하여 선언된 생성자로
	고정된 값으로 DB연결을 진행한다. 오버로딩으로 정의되었다. */
	public ConnectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", 
					"education", "1234");
			System.out.println("오라클 DB 연결성공");
		}
		catch (ClassNotFoundException e) {
			System.out.println("오라클 드라이버 로딩 실패");
		}
		catch (SQLException e) {
			System.out.println("DB 연결 실패");
		}
		catch (Exception e) {
			System.out.println("알수 없는 예외발생");
			e.printStackTrace();
		}
	}
	//자원반납
	public void close() {
		try {
			/* Statement, Connection, ResultSet 객체에
			대한 자원반납 처리*/
			if(stmt!=null) stmt.close();
			if(con!=null) con.close();
			if(rs!=null) rs.close();
			System.out.println("DB자원반납완료");
		}
		catch(SQLException e) {
			System.out.println("자원반납시 오류가 발생하였습니다.");
			e.printStackTrace();
		}
	}//end of close
	
	/*
	상속관계에서 오버라이딩을 목적으로 생성한 추상메서드로 하위클래스
	에서 각 업무(update, select 등)에 따라 재정의 하게 될것이다. 
	
	추상메서드를 포함한 클래스는 반드시 추상클래스로 선언해야 에러가 
	발생하지 않는다. 따라서 해당 클래스는 abstract로 선언해야한다.
	*/
	abstract void execute();
}
