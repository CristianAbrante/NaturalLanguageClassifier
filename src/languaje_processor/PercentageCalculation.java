package languaje_processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PercentageCalculation {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		if (args.length == 2) {
			int numberOfDocuments = 0;
			int numberOfGoals = 0;
			
			BufferedReader f1 = new BufferedReader(new FileReader(args[0]));
			BufferedReader f2 = new BufferedReader(new FileReader(args[1]));
			
			String line1;
			String line2;
			
			while(((line1 = f1.readLine()) != null) && ((line2 = f2.readLine()) != null)) {
				numberOfDocuments += 1;
				System.out.println(line1 + " " + line2);
				if (line1.equals(line2)) {
					numberOfGoals += 1;
				}
			}
			
			System.out.println((double) numberOfGoals / (double) numberOfDocuments);
		}
	}

}
