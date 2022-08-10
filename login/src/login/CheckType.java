package login;    // enum 기초

public enum CheckType {
	//0:db 접속오류,1:아이디가틀리다 2:패스워드 틀리다. 3.로그인성공

	DB_ERROR (0),ID_ERROR(1),PW_ERROR(2),SUCCESS(3);
	
	private int num;
	
	CheckType(int num){
		this.num=num;
	}
	
	public int value() {
		return num;
	}
}
