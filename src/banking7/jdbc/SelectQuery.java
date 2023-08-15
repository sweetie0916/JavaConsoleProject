package banking7.jdbc;

import java.sql.SQLException;

public class SelectQuery extends ConnectDB {

	/* 매개변수가 없는 부모의 기본생성자를 호출해서 DB연결. 
	여기서는 아래 생성자를 주석처리해도 문제없이 실행된다. */
	public SelectQuery() {
		super();
	}
	
	/*
	ResultSet 인터페이스
	: select문 실행시 쿼리의 실행결과를 ResultSet객체를 통해 
	받는다. 결과로 반환된 레코드의 처음부터 끝까지 next()메서드를 
	통해 확인 후 반복 인출한다.
	
	컬럼의 자료형이
		number타입 : getInt()
		char/varchar2타입 : getString()
		date타입 : getDate() 메서드를 통해 출력한다. 
	해당 메서드의 인수는 인덱스(1부터시작) 혹은 컬럼명(or 별칭)을 
	사용할 수 있다. 
	오라클 자료형에 상관없이 getString()으로 모두 출력할 수 있다. 
	*/
	@Override
	public void execute() {
		try {
			stmt = con.createStatement();	
			
			String query = "SELECT * FROM banking_tb";
			
			/*
			쿼리문 실행시 사용하는 메서드
			executeUpdate()
				: insert, update, delete와 같이 기존 레코드
				에 변화가 생기는 경우에 사용한다. 실행후 적용된 
				행의 갯수가 int형으로 반환된다. 			
			executeQuery()
				: select 쿼리문을 실행할때 사용하는 메서드로
				레코드에 영향을 미치지않고, 단순히 조회만 하는경우
				사용한다. 조회한 레코드를 반환값으로 받아야 하므로 
				ResultSet객체가 반환타입으로 정의되어있다. 
			*/
			rs = stmt.executeQuery(query);
			while(rs.next()) {
				String accountid = rs.getString("accountid");//id컬럼
				String name = rs.getString("name");//계좌명컬럼
				int money = rs.getInt("money");//잔액	
				
				System.out.println("계좌번호: "+ accountid +"고객이름: "+ name +"잔액 :"+ money);
			}
		}	
		catch(SQLException e) {
			System.out.println("쿼리오류발생");
			e.printStackTrace();
		}
		finally {
			close();//DB 자원반납
		}
	}

}
