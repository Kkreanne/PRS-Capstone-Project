package util;
import java.util.Scanner;

public class Console {
		
		private static Scanner sc = new Scanner(System.in);
		
		public static String getString(String prompt) {
			String text = "";
			Boolean isValid = false;
			while (!isValid) {
				System.out.print(prompt);
				text = sc.nextLine();
				if (!text.equals("")) {
					isValid = true;
				} else {
					System.out.println("\nError! Must enter a value. Try again.\n");
				}
			}
			return text;
		}

		 public static int getInt(String prompt) {
		        int i = 0;
		        boolean isValid = false;
		        while (!isValid) {
		            System.out.print(prompt);
		            if (sc.hasNextInt()) {
		                i = sc.nextInt();
		                isValid = true;
		            } else {
		                System.out.println("Error! Invalid integer. Try again.");
		            }
		            sc.nextLine();  // discard any other data entered on the line
		        }
		        return i;
		}
		
		public static int getInt(String prompt, int min, int max) {
			int i = 0;
			boolean isValid = false;
			while (!isValid) {
				i = getInt(prompt);
				if (i <= min) {
					System.out.println("Error! Number must be greater than " + min + ".");
				} else if (i >= max) {
					System.out.println("Error! Number be less than " + max + ".");
				} else {
					isValid = true;
				}
			}
			return i;
		}
		
		public static double getDouble(String prompt) {
			double d = 0;
			boolean isValid = false;
			while (!isValid) {
				System.out.print(prompt);
				if (sc.hasNextDouble()) {
					d = sc.nextDouble();
					isValid = true;
				} else {
					System.out.println("Error! Invalid number. Try again.");
				}
				sc.nextLine();
			}
			return d;
		}
		
		public double getDouble(String prompt, double min, double max) {
			double d = 0;
			boolean isValid = false;
			while (!isValid) {
				d = getDouble(prompt);
				if (d <= min) {
					System.out.println("Error! Number must be greater than " + min + ".");
				} else if (d >= max) {
					System.out.println("Error! Number must be less then " + max + ".");
				} else {
					isValid = true;
				}
			}
			return d;
		}
		
		public static boolean askToContinue() {

			boolean isValid = false;

			while (!isValid) {

				System.out.print("Continue? (y/n): ");
				String choice = sc.next();

				if (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) {
					System.out.println("Please enter \"y\" to continue or \"n\" to quit.");
				} else if (choice.equalsIgnoreCase("y")) {
					isValid = true;
					sc.nextLine();
					System.out.println();
				} else {
					break;
				}
			}
			return isValid;
		}
		
		public static String getLine(String prompt) {
		System.out.print(prompt);
        String s = sc.nextLine();  // read user entry
        return s;
		}
		
		public static void print(String s) {
			System.out.print(s);		
		}

		public static void println() {
			System.out.println();
		}

		public static void println(String s) {
			System.out.println(s);
		}

		public static String getDate(String string) {
			System.out.println(string);
			String d = sc.nextLine();
			return d;
		}
	
	}