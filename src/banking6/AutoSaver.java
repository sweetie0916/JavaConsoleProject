package banking6;

// 쓰레드로 정의할 AutoSaver 클래스 (Thread를 상속받음)
public class AutoSaver extends Thread {
	
	// AccountManager타입의 멤버변수 생성
	AccountManager accMgr;

	// 생성자를 통해 AccountManager타입의 멤버변수를 초기화
	public AutoSaver(AccountManager accMgr) {
		this.accMgr = accMgr;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
				// 5초마다 자동저장
				accMgr.autoSaveTxt();
				sleep(5000);
			}
		}
		catch(InterruptedException e) {
			
		}
	}
	

}
