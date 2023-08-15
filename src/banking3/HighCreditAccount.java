package banking3;

public class HighCreditAccount extends Account {
	
	// 멤버변수 : 이자율, 신용등급이자
	int interRate;
	String creditGrade;
	
	// 생성자 : 상속받은 확장한 변수(이자비율의정보) 초기화 이율정보를 초기화
	public HighCreditAccount(String id, String name, int money, int rate, String credit) {
		super(id, name, money);
		this.interRate = rate;
		this.creditGrade = credit;
		
	}

	@Override
	public void showAccInfo() {
		super.showAccInfo();
		
		System.out.println("기본이자: "+ interRate +"%");
		System.out.println("신용등급(A,B,C등급): "+ creditGrade);
		System.out.println("-------------");
		
	}
	
	// 입금처리
	@Override
	public boolean plusMoney(int money) {
		/*
		[ 이자계산방식 ]
		- 이자계산은 입금시에만(plusAccMoney) 잔고를 대상으로 계산
		- 고객의 신용등급을 A, B, C로 나누고 계좌개설시 이 정보를 등록한다.
		- A,B,C 등급별로 각각 기본이율에 7%, 4%, 2%의 이율을 추가로 제공
		신용계좌 : 잔고 + (잔고 * 기본이자) + (잔고 * 추가이자) + 입금액
		*/
		
		int accMoney = getAccMoney();
		
		// 이자계산
		double calMoney = 0;
		
		// 각각의 신용등급에따라 이자계산을 해줘라
		// 신용등급이 A일 경우
		if(creditGrade.equalsIgnoreCase("A")) {
			calMoney = accMoney + (accMoney * (interRate/100.0)) + (accMoney * (ICustomDefine.A/100.0)) + money;
		}
		// 신용등급이 B일 경우
		else if(creditGrade.equalsIgnoreCase("B")) {
			calMoney = accMoney + (accMoney * (interRate/100.0)) + (accMoney * (ICustomDefine.B/100.0)) + money;
		}
		// 신용등급이 C일 경우
		else if(creditGrade.equalsIgnoreCase("C")) {
			calMoney = accMoney + (accMoney * (interRate/100.0)) + (accMoney * (ICustomDefine.C/100.0)) + money;
		}
				
		// Account 클래스의 잔고에 이자계산	
		setAccMoney((int)calMoney);
				
		return true;
	}
}


























