import java.util.Scanner;

public class In {
    private static final Scanner scanner = new Scanner(System.in);
    
    private In() {}

	/**
	 * Read the next line of text.
	 *
	 * @return the line as a String
	 */
	public static String nextLine() {
		return scanner.nextLine();
	}

	/**
	 * Read the next line as an integer.
	 *
	 * @return the integer that was read
	 */
	public static int nextInt() {
		int value = scanner.nextInt();
		scanner.nextLine(); // read the "\n" as well
		return value;
	}

	/**
	 * Read the next line as a double.
	 *
	 * @return the double that was read
	 */
	public static double nextDouble() {
		double value = scanner.nextDouble();
		scanner.nextLine();
		return value;
	}

	/**
	 * Read the first character of the next line of text.
	 *
	 * @return the character that was read
	 */
	public static char nextChar() {
		return scanner.nextLine().charAt(0);
	}
}
