package diary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Check {
	private Connection conn() throws Exception {					//"jdbc:oracle:thin:@localhost:1521:xe" 
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";			// 1. 데이터베이스 드라이버를 로딩한다.
		Class.forName("oracle.jdbc.driver.OracleDriver");			// 2. 연결한다
		return DriverManager.getConnection(url, "scott", "tiger");	
	}
	Scanner sc = new Scanner(System.in);
	private void connClose(ResultSet rs,PreparedStatement stmt,Connection con) {
		try {if (rs!=null) rs.close();		} catch (Exception e) {}
		try {if (stmt!=null) stmt.close();	} catch (Exception e) {}
		try {if (con!=null) con.close();	} catch (Exception e) {}
	}
	int flag = 0;
	private String mid;
	int mnum,num2;
	

	
	CheckType login(String id, String pw) {	//[로그인]									// DB에 접속수 id,pw가지고 확인해서
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Connection con=null;
		try {
			con=conn ();						
							
//		System.out.println("DB접속 성공");														
			stmt = con.prepareStatement("select id, pw from tbl_user1 where id=?"); 		// 3. 생성된 커넥션 개체를 가지고 Statemnet 객체를 생성한다.
			// 4. Statement 객체를 가지고 작업을 진행한다.
			// 삽입,삭제,갱신시에는 .executeUpate()리턴이 정수(정수가 의미하는 몇개가 처리되었는지
			stmt.setString(1,id);
			rs = stmt.executeQuery();
	
			if (rs.next()) {												
				String dbPw = rs.getString("pw"); 								
				if (pw.equals(dbPw)) {
					mid = id;
					return CheckType.SUCCESS;
				}
				else
					return CheckType.PW_ERROR;
			} else
				return CheckType.ID_ERROR;

		} catch (Exception e) {
			System.out.println("예외발생1 [로그인]");
			e.printStackTrace();
		} finally {
			connClose(rs,stmt,con);
		}
		return CheckType.DB_ERROR; // 1~3까지 값을 return주면 된다.
}
	
		
		List<User> list() {  //  로그인 후 리스트 목록 
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Connection con=null;
		 List<User> list = new ArrayList<>();
		try {
			con=conn ();																							
			stmt = con.prepareStatement("select ID,T2NO,T2DATE,T2HEAD from tbl_user2 where id=?"); 	
			stmt.setString(1,mid);
			rs = stmt.executeQuery();
			System.out.println("일기장을 불러왔습니다.");
			System.out.println("──────────────────────────────────────────────────");
			while(rs.next()) {
				User u = new User();
				u.ID = rs.getString(1);
				u.T2NO = rs.getInt(2);
				u.T2DATE = rs.getString(3);
				u.T2HEAD = rs.getString(4);
				list.add(u);
			}
			
		
			
		}catch (Exception e) {
				System.out.println("예외발생 [일기 리스트목록1]");
				e.printStackTrace();
		}finally {
				connClose(rs,stmt,con);
			} 
		return list;
		}	
		List<User> list2() {  //  로그인 후 리스트 목록 2
			num2 = sc.nextInt();
			mnum = num2;
			ResultSet rs=null;
			PreparedStatement stmt=null;
			Connection con=null;
			 List<User> list2 = new ArrayList<>();
			try {
				con=conn ();																							
				stmt = con.prepareStatement("select ID,T2NO,T2DATE,T2HEAD,T2MAIN from tbl_user2 where id=(?) and T2NO=(?)"); 	
				stmt.setString(1,mid);
				stmt.setInt(2,mnum);
				rs = stmt.executeQuery();
				System.out.println("일기장을 불러왔습니다.");	
				System.out.println("──────────────────────────────────────────────────"
						+ "──────────────────────────────────────────────────");
				while(rs.next()) {
					User u2 = new User();
					u2.ID = rs.getString(1);
					u2.T2NO = rs.getInt(2);
					u2.T2DATE = rs.getString(3);
					u2.T2HEAD = rs.getString(4);
					u2.T2MAIN = rs.getString(5);
					list2.add(u2);
				}
				
			
				
			}catch (Exception e) {
					System.out.println("예외발생 [일기 리스트목록2]");
					e.printStackTrace();
			}finally {
					connClose(rs,stmt,con);
				} 
			return list2;
			}	
		

	void signUp(String id, String pw) {//[회원가입]

		PreparedStatement stmt=null;
		Connection con=null;
		
			try {
				con=conn ();
//				System.out.println("DB접속 성공");											
				stmt = con.prepareStatement("INSERT INTO TBL_USER (ID, PW) VALUES (?, ?)"); // 3. 생성된 커넥션 개체를 가지고 Statemnet 객체를 생성한다.
				// 4. Statement 객체를 가지고 작업을 진행한다.
				// 삽입,삭제,갱신시에는 .executeUpate()리턴이 정수(정수가 의미하는 몇개가 처리되었는지
				
				stmt.setString(1,id); 
				stmt.setString(2,pw);
				stmt.executeUpdate();	 												// sql문 (select)실행하기;

			} catch (Exception e) {
				System.out.println("예외발생2 [회원가입]");
				e.printStackTrace();
			} finally {
				connClose(null,stmt,con);
			}
	}
/*	void delete(String id, String pw) {//[회원탈퇴]									// DB에 접속수 id,pw가지고 확인해서
		
		PreparedStatement stmt=null;
		Connection con=null;
		try {
			con=conn ();											
															
			stmt = con.prepareStatement("DELETE FROM TBL_USER  WHERE ID = (?) and PW = (?)"); 
			// 삽입,삭제,갱신시에는 .executeUpate()리턴이 정수(정수가 의미하는 몇개가 처리되었는지
			
			
			stmt.setString(1,id);
			stmt.setString(2,pw);
			stmt.executeUpdate();	
		

		} catch (Exception e) {
			System.out.println("예외발생3 [회원탈퇴]");
			e.printStackTrace();
		} finally {
			connClose(null,stmt,con);
		} 
	} */
	void reid(String email) {	//[아이디 찾기]
		
		ResultSet rs=null;
		PreparedStatement stmt=null;
		Connection con=null;
		try {
			con=conn ();									
			
			stmt = con.prepareStatement("select id , pw, email from tbl_user where email=(?)"); 		
															
			stmt.setString(1,email);
			rs = stmt.executeQuery(); 			
			
			rs.next();		//가르키면서 행이 있다면 참, 테이블에 진입해서 가르키지 않으면 테이블의 값을 호출할 수 없다.
			reid = rs.getString("id"); 								
			

		} catch (Exception e) {
			System.out.println("예외발생4 [아이디 찾기]");
			e.printStackTrace();
		} finally {
			connClose(rs,stmt,con);
		}
		System.out.println("아이디: "+reid);
	} String reid;
	void repw(String id) {	//[비밀번호 찾기]
			
			ResultSet rs=null;
			PreparedStatement stmt=null;
			Connection con=null;
			try {
				con=conn ();									
				
				stmt = con.prepareStatement("select id, pw from tbl_user where id=(?)"); 		
																
				stmt.setString(1,id);
				rs = stmt.executeQuery(); 			
				
				rs.next();			//가르키면서 행이 있다면 참, 테이블에 진입해서 가르키지 않으면 테이블의 값을 호출할 수 없다.
				repw = rs.getString("pw"); 								
				
	
			} catch (Exception e) {
				System.out.println("예외발생5 [비밀번호 찾기]");
				e.printStackTrace();
			} finally {
				connClose(rs,stmt,con);
			}
			System.out.println("비밀번호: "+repw);
		} String repw;
}



