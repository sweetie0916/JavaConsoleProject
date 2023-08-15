package banking3;
/*
최상위 클래스인 Account 를 추상클래스로 정의
*/
public abstract class Account {
	//멤버변수 : 계좌번호(String형), 이름(String형), 잔액(int형)
	private String accountNumber;//계좌번호
	private String customName;//이름
	private int accMoney;//잔액 
	
	//생성자
	public Account(String id, String name, int money) {
		this.accountNumber = id;
		this.customName = name;
		this.accMoney = money;
	}
	
	//멤버메소드 : 전체계좌정보출력
	public void showAccInfo() {
		System.out.println("-------------");
		System.out.println("계좌번호: " + accountNumber);
		System.out.println("고객이름: " + customName);
		System.out.println("잔   고: " + accMoney);
	}
	
	// getter/setter정의
	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getCustomName() {
		return customName;
	}

	public void setCustomName(String customName) {
		this.customName = customName;
	}

	public int getAccMoney() {
		return accMoney;
	}

	public void setAccMoney(int accMoney) {
		this.accMoney = accMoney;
	}
	
	// 입금처리
	public boolean plusMoney(int money) {
		accMoney += money;
		return true;
	}
	
	//출금처리
	public boolean minusMoney(int money) {
		
		// 조건식 - 잔고보다 출금요청금액이 많을 경우 
		if(accMoney < money) {
			System.out.println("잔고가 부족합니다. 어떻게할까요?");
			System.out.println("YES.금액전체 출금처리 NO.출금요청취소");
			AccountManager.scan.nextLine();
			String keyinput = AccountManager.scan.nextLine();
			
			// 조건식 - YES를 눌렀을 경우 - 금액전체를 출금해라
			if(keyinput.equalsIgnoreCase("YES")) {
				// 금액전체를 출금해라
				accMoney = 0;
			}
			// 조건식 - NO를 눌렀을 경우
			else if(keyinput.equalsIgnoreCase("NO")) {
				System.out.println("출금요청이 취소되었습니다.");
				return false;
			}
			// YES, NO를 제외한 메뉴를 입력한 경우 - 예외처리
			else {
				System.out.println("메뉴를 정확하게 입려해주세요");
			}
		}
		
		// 잔고보다 출금요청금액이 적을 경우 => 정상적인 출금
		else {
			// 잔고에서 출금해라 
			accMoney -= money;
		}
		return true;
	}
}
