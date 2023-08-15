package banking3;

import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManager {

	// 키보드 입력
	public static Scanner scan = new Scanner(System.in);
	// 인스턴스(객체) 배열 생성 - 계좌정보 최대 50개(차후 변경됨)
	static Account[] accountArr = new Account[50];
	// 개설된 계좌 카운트 변수
	static int accCount = 0;
	
	// 메뉴출력
	public static void showMenu() {
		System.out.println("-----Menu------");
		System.out.println("1.계좌개설");
		System.out.println("2.입	금");
		System.out.println("3.출	금");
		System.out.println("4.계좌정보출력");
		System.out.println("5.프로그램종료");
		System.out.print("선택:");
	}
	
	// 계좌개설을 위한 함수
	public static void makeAccount() {
		
		// 계좌개설 메뉴선택 계좌선택메뉴 출력
		System.out.println("-----계좌선택------");
		System.out.println("1.보통계좌");
		System.out.println("2.신용신뢰계좌");
		System.out.print("선택: ");
		
		int keyInput = scan.nextInt();
		scan.nextLine();
		
		System.out.print("계좌번호: ");
		String accID = scan.nextLine();
		System.out.print("고객이름: ");
		String cusName = scan.nextLine();
		System.out.print("잔   고: ");
		int accMoney = scan.nextInt();
		
		// 신규정보를 통한 객체생성
		Account addAcc = null;
		
		// 조건식 - 1.보통계좌 메뉴 선택시 
		if(keyInput == 1) {
			// 기본이자%(정수형태로입력): 출력 -> sysout
			System.out.print("기본이자%(정수형태로입력): ");
			// 기본이자 입력 -> scanner
			int interest = scan.nextInt();
			
			addAcc = new NormalAccount(accID, cusName, accMoney, interest);
			
		}
		// 조건식 - 2.신용신뢰계좌 메뉴 선택시 
		else if(keyInput == 2) {
			// 기본이자%(정수형태로입력): 출력 -> sysout
			System.out.print("기본이자%(정수형태로입력): ");
			// 기본이자 입력 -> scanner
			int interest = scan.nextInt();
			scan.nextLine();
			// 신용등급(A,B,C등급): 출력 -> sysout
			System.out.print("신용등급(A,B,C등급):");
			// 신용등급 입력 -> scanner
			String credit = scan.nextLine();
			
			addAcc = new HighCreditAccount(accID, cusName, accMoney, interest, credit);
					
		}
		
		// 객체배열에 저장후 카운트 변수 1 증가 (후위증가)
		accountArr[accCount++] = addAcc;
		
		/*
		// ↑ 위에 간략버전
		// 반복할때마다 객체배열의 자리만큼 반복해라
		for(int i=0; i<accountArr.length; i++) {
			// 객체배열에 빈값을 확인하고 내가 입력한 값들 넣어라
			if(accountArr[i] == null) {
				accountArr[i] = new Account(accID, cusName, accMoney);
			}
		}
		*/
		
		System.out.println("계좌개설이 완료되었습니다.\n");
		
	}
	
	// 입    금
	public static void depositMoney() {
		System.out.println("계좌번호와 입금할 금액을 입력하세요");
		
		System.out.print("계좌번호: ");
		String accID = scan.nextLine();
		
		try {
			System.out.print("입금액: ");
			int money = scan.nextInt();
			
			// 조건식 - 음수를 입금 할 수 없다. => 내가 입력한 금액이 0보다 작으면
			if(money < 0) {
				System.out.println("음수를 입금할 수 없다.");
				return;
			}
			
			// 조건식 - 입금액은 500원단위로 가능하다.
			// => 내가 입력한 금액이 500으로 나눴을때 나머지가 0으로 떨어지지않으면
			if(money%500 != 0) {
				System.out.println("입금액은 500원 단위로 가능하다.");
				return;
			}
			
			// 개설된 계좌만큼 반복
			for(int i=0; i<accCount; i++) {
				// 조건식 - 입력한 계좌번호와 개설된 계좌 번호가 같다면
				if(accID.compareTo(accountArr[i].getAccountNumber())==0) {
					// 개설된 계좌에 입금 하자
					accountArr[i].plusMoney(money);
				}
			}
			System.out.println("입금이 완료되었습니다.\n");
		}
		catch(InputMismatchException e) {
			System.out.println("입금액 입력시 문자를 입력할 수 없다.");
			scan.nextLine();
		}
		
	}
	
	// 출    금
	public static void withdrawMoney() {
		System.out.println("계좌번호와 출금할 금액을 입력하세요");
				
		System.out.print("계좌번호 : ");
		String accID = scan.next();
		
		try {
			System.out.print("출금액 : ");
			int money = scan.nextInt();
			
			// 음수를 출금할 수 없다.
			if(money<=0) {
				System.out.println("음수는 출금할 수 없습니다.");
				return;
			}
			// 출금은 1000원 단위로만 출금이 가능하다. Ex)2000원 출금가능, 1100원을 출금불가.
			if(money%1000 != 0) {
				System.out.println("출금액은 1000원단위로 가능합니다.");
				return;
			}
			
			// 개설된 계좌만큼 반복
			for(int i=0; i<accCount; i++) {
				// 조건식 - 입력한 계좌번호와 개설된 계좌 번호가 같다면
				if(accID.compareTo(accountArr[i].getAccountNumber())==0) {
					// 개설된 계좌에 출금 하자
					accountArr[i].minusMoney(money);
				}
			}
			System.out.println("출금이 완료되었습니다.");
		}
		catch(InputMismatchException e) {
			System.out.println("출금액 입력시 숫자만 입력 가능합니다.");
			scan.nextLine();
		}
		
		
	}
	
	// 전체계좌정보출력
	public static void showAccInfo() {
		// 개설된 계좌만큼 반복해라
		for(int i=0; i<accCount; i++) {
			//저장된 객체 배열의 계좌 정보출력
			accountArr[i].showAccInfo();
		}
		System.out.println("-------------");
		System.out.println("전체계좌정보 출력이 완료되었습니다.\n");
	}

}
