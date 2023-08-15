package banking4;

// 보통예금계좌를 의미 -> Account의 자식클래스이다.
public class NormalAccount extends Account {
	
	// 멤버변수 : 이자율
	int interRate;
	
	// 생성자 : 상속받은 확장한 변수(이자비율의정보) 초기화 이율정보를 초기화
	public NormalAccount(String id, String name, int money, int rate) {
		super(id, name, money);
		this.interRate = rate;
	}
	
	// Account 클래스에서 오버라이딩한 메서드
	@Override
	public void showAccInfo() {
		super.showAccInfo();
		
		System.out.println("기본이자: "+ interRate +"%");
		System.out.println("-------------");
	}
	
	@Override
	public boolean plusMoney(int money) {
		/*
		이자계산은 입금시에만(plusMoney) 잔고를 대상으로 계산.
		[이자계산방식]
		일반계좌 : 잔고 + (잔고 * 기본이자) + 입금액
		*/
		
		//Account 클래스의 잔고(get-가져오기)
		int accMoney = getAccMoney();
		
		//이자계산 
		double calMoney = accMoney + (accMoney * (interRate/100.0)) + money;
		
		// Account 클래스의 잔고에 이자계산(set-변경된값을 넣기)
		setAccMoney((int)calMoney);
		
		return true;
	}
}




























