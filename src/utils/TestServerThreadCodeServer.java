package utils;

public class TestServerThreadCodeServer {

	public static void main(String[] args) {
		new TestServerThreadCode(6000).start();//建立物件，傳入Port並執行等待接受連線的動作
	}

}
