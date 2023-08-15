package banking7.jdbc;

public class Account {
	// 멤버변수 : 계좌번호(String형), 이름(String형), 잔액(int형)
	private String accountNumber;//계좌번호
	private String customName;//이름
	private int accMoney;//잔액 
	
	// 생성자
	public Account(String id, String name, int money) {
		this.accountNumber = id;
		this.customName = name;
		this.accMoney = money;
	}
	
	// 멤버메소드 : 전체계좌정보출력
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
	
	// 출금처리
	public boolean minusMoney(int money) {
		accMoney -= money;
		return true;
	}
}
