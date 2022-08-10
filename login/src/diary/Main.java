package diary;

import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		String id, pw, email;
		Scanner sc = new Scanner(System.in);
		Check check = new Check();
		int num1=0;
		int num2;
		System.out.println("로그인[1]  회원가입[2]  아이디찾기[3]  비밀번호찾기[4] ");
		int num = sc.nextInt();
		sc.nextLine(); // 숫자 입력후 엔터값 없애기 

		if (num == 1) { 										// 로그인(1)
			System.out.println("로그인을 진행합니다.");
			System.out.println("아이디를 입력해주세요.");
			id = sc.nextLine();
			System.out.println("패스워드를 입력해주세요.");
			pw = sc.nextLine();
			// 객체(Check)의 메소드(login(string id,string pw) :int //int절로 가져올꺼임(대입할꺼임) (0: db접속
			// 오류, 1: 아이디가 틀리다.
			// 2: 패스워드 틀리다. 3:로그인 성공)통해서 로그인 여부 확인
			CheckType flag=check.login(id,pw);
//			int flag = check.login(id, pw);

			if (CheckType.DB_ERROR == flag)       
				System.out.println("db접속 오류");
			else if (CheckType.ID_ERROR == flag) 
				System.out.println("아이디가 일치하지 않습니다.");
			else if (CheckType.PW_ERROR == flag) 
				System.out.println("패스워드가 일치하지 않습니다.");
			else if (CheckType.SUCCESS == flag) {
				System.out.println("로그인에 성공하였습니다.");	
				System.out.println();
				num1= 1;
				
			}
		}
			if (num1 == 1) {					//  로그인 후 리스트 목록 
				List<User> list=check.list();
				for (User temp : list) {
					System.out.print("No.("+temp.T2NO+")");
					System.out.print("    ID: "+temp.ID);
					System.out.print("    일자: "+temp.T2DATE);
					System.out.println("    제목: "+temp.T2HEAD);
				}System.out.println("──────────────────────────────────────────────────");
				
				System.out.println("불러올 목록의 번호를 입력해주세요");
				
				List<User> list2=check.list2(); // 로그인 후 리스트 목록2
				for (User temp : list2) {
					System.out.print("No.("+temp.T2NO+")");
					System.out.print("    ID: "+temp.ID);
					System.out.print("    날짜: "+temp.T2DATE);
					System.out.print("    제목: "+temp.T2HEAD);
					System.out.println("    내용: "+temp.T2MAIN);
				}
				System.out.println("──────────────────────────────────────────────────"
						+ "──────────────────────────────────────────────────");
			}	
				
			
		
		 if (num == 2) {									// 회원가입(2)
			System.out.println("회원가입 진행합니다.");
			System.out.println("아이디를 입력해주세요.");
			id = sc.nextLine();
			System.out.println("패스워드를 입력해주세요.");
			pw = sc.nextLine();
			check.signUp(id,pw);
			System.out.println("회원가입 성공.");
		}
//		if (num == 3) {											// 회원탈퇴(3)
//			System.out.println("회원탈퇴를 진행합니다 .");
//			System.out.println("탈퇴할 아이디를 입력하세요.");
//			id = sc.nextLine();
//			System.out.println("탈퇴할 패스워드를 입력해주세요.");
//			pw = sc.nextLine();
//			CheckType flag=check.login(id,pw);
//
//			String Answer;
//			
//			if (CheckType.DB_ERROR == flag) 
//				System.out.println("db접속 오류");
//			else if (CheckType.ID_ERROR == flag) 
//				System.out.println("일치하는 아이디가 없습니다.");
//			else if (CheckType.PW_ERROR == flag) 
//				System.out.println("패스워드가 틀립니다.");
//			else if (CheckType.SUCCESS == flag) {
//				System.out.println("정말로 회원탈퇴를 진행하시겠습니까?  yes / no");
//				Answer = sc.nextLine();
//			
//				if (Answer.equals("yes")) { 
//					System.out.println("회원탈퇴를 진행하였습니다.");
//					check.delete(id,pw);
//				}
//				else if (Answer.equals("no")) {
//					System.out.println("회원탈퇴를 취소합니다.");	
//				}
//			}
//		}
		if (num == 3) {											// 아이디 찾기(3)
			System.out.println("아이디 찾기를 진행합니다.");
			System.out.println("이메일을 입력해주세요.");
			email = sc.nextLine();
		
			check.reid(email);
		}
		if (num == 4) {											// 비밀번호 찾기(4)
			System.out.println("아이디를 입력해주세요.");
			id = sc.nextLine();
		
			check.repw(id);
		}	
	//	if (num == 6) {											// 목록 불러오기(6)
	//		System.out.println("");
	//		List<User> list=check.list();
	//		for (User temp : list) {
	//			System.out.print("No."+temp.T2NO);
		//		System.out.print("    ID: "+temp.ID);
		//		System.out.print("    일자: "+temp.T2DATE);
	//			System.out.println("    제목: "+temp.T2HEAD);
	//		}
	//	}	
		
	}
}
