package languaje_processor;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		MessageScanner scanner = new MessageScanner("src/data/test.txt");
		//System.out.println(scanner.getMessageList());
		String token;
		do {
			token = scanner.nextToken();
			System.out.println(token);
		} while (token != null);
	}

}
