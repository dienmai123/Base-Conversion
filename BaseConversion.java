//Dien Mai
//OCCC Spring 2023
//Advanced Java
// Base Conversion

import java.util.Scanner; 
import java.math.BigInteger;

public class BaseConversion {
	public static void main(String[] args) throws InterruptedException { 
		//Prompting user from command line
		String valueConvert;
		String newNumb;
		String sInitialBase;
		String sDesiredBase;
		int initialBase;
		int desiredBase;
		
		Scanner input = new Scanner(System.in);
		
		if (args.length == 0) {
			//Display Intro message
			introGretting();
			
			//Prompting user for info
			System.out.print("Please, enter a value to convert: ");
			valueConvert = input.nextLine();
			valueConvert = valueConvert.toUpperCase();
			
			System.out.print("Please, enter the initial base: ");
			initialBase = input.nextInt();
			System.out.print("Please, enter the value of the desired base output: ");
			desiredBase = input.nextInt();
			
			// Validate number and base
			isABase(desiredBase);
			isValidInteger(valueConvert, initialBase);
			
			//Do converting 
			newNumb = convertInteger(valueConvert, initialBase, desiredBase);
			
			displayResult(valueConvert,newNumb,initialBase,desiredBase);
			
		}	

		if(args.length == 1) {
			valueConvert = args[0];
			System.out.println("Please, enter the initial base and the desired base number.");
			initialBase = input.nextInt();
			desiredBase =input.nextInt();
			isABase(desiredBase);
			isValidInteger(valueConvert, initialBase);
			newNumb = convertInteger(valueConvert, initialBase, desiredBase);
			displayResult(valueConvert,newNumb,initialBase,desiredBase);
		}
		
		if(args.length == 2) {
			valueConvert = args[0];
			sInitialBase = args[1];
			initialBase = Integer.parseInt(sInitialBase);
			isValidInteger(valueConvert, initialBase);
			System.out.println("Please, enter the desired base number.");
			desiredBase = input.nextInt();
			isABase(desiredBase);
			newNumb = convertInteger(valueConvert, initialBase, desiredBase);
			displayResult(valueConvert,newNumb,initialBase,desiredBase);
		}
		
		if(args.length == 3 ) {
			valueConvert = args[0];
			sInitialBase = args[1];
			initialBase = Integer.parseInt(sInitialBase);
			isValidInteger(valueConvert, initialBase);
			sDesiredBase = args[2];
			desiredBase = Integer.parseInt(sDesiredBase);
			isABase(desiredBase);
			newNumb = convertInteger(valueConvert, initialBase, desiredBase);
			displayResult(valueConvert,newNumb,initialBase,desiredBase);
		}
		
		if(args.length > 3) {
			valueConvert = args[0];
			sInitialBase = args[1];
			initialBase = Integer.parseInt(sInitialBase);
			isValidInteger(valueConvert, initialBase);
			sDesiredBase = args[2];
			desiredBase = Integer.parseInt(sDesiredBase);
			isABase(desiredBase);
			System.out.print("Extra parameters ignored.");
			newNumb = convertInteger(valueConvert, initialBase, desiredBase);
			displayResult(valueConvert,newNumb,initialBase,desiredBase);
		}
	}

	
	
	
	
	
	
	//Method Display result
	public static void displayResult(String oldNumb, String newNumb, int initialBase, int desiredBase) {
		System.out.println("\n*****************************Result Below************************************\n");
		System.out.println("Original Number : " + oldNumb + "  :: " + "Base: " + initialBase);
		System.out.println("Converted Number: " + newNumb + " :: " + "Base: " + desiredBase);
	}
	
	//Method display intro message for user
	public static void introGretting() {
		System.out.println("**********************WELCOME TO OUR BASE CONVERSION APP**********************\n");
		System.out.println("We hope you are doing well, now just sit back and we will do the conversion.\n");
		System.out.println("******************************************************************************\n");
	}
	
	//Method validate Desired Based
	public static boolean isABase(int theBase) throws InterruptedException {
		if(theBase < 2 || theBase > 36 ) {
			System.out.println("Error. Desired Base integer have to be between 2-36.");
			System.out.println("Program will exit in 5 seconds.");
			for(int i = 0; i <5; i++) {
				System.out.print(".");
				Thread.sleep(1000);
			}
			System.exit(1);
		} 
		return true;
	}
	
	//Method validate 
	public static boolean isValidInteger(String number, int base) {
		char chDigit;
		for(int i = 0; i < number.length(); i++) {
			chDigit = number.toUpperCase().charAt(i);
			if(Character.isDigit(chDigit)&& (chDigit -'0') >= base) {
				System.out.print("Cannot have digit " + chDigit + " in base " + base);
				System.exit(1);
			} else if(Character.isLetter(chDigit) && (chDigit -'A') + 10 >= base) {
				System.out.print("Cannot have digit " + chDigit + " in base " + base);
				System.exit(1);
			} else if(!Character.isDigit(chDigit) && !Character.isLetter(chDigit)) {
				System.out.print("Invalid digit character.");
				System.exit(1);
			}
		}
		return true;
	}
	
	//Digit in char
	public static char digitInChar(int digit) {
        if (digit >= 0 && digit <= 9) {
            return (char) (digit + '0');
        } else {
            return (char) (digit - 10 + 'A');
        }
    }
	
	//Convert char value to decimal
	public static int charValueInDecimal(char ch) {
        if (ch >= '0' && ch <= '9') {
            return ch - '0';
        } else if (ch >= 'A' && ch <= 'Z') {
            return ch - 'A' + 10;
        } else if (ch >= 'a' && ch <= 'z') {
            return ch - 'a' + 10;
        } else {
            return -1;
        }
    }
	
	//Method Convert to new number
    public static String convertInteger(String theValue, int initialBase, int finalBase) {
    	BigInteger originalNumb = BigInteger.ZERO;
        BigInteger power = BigInteger.ONE;
        
        for (int i = theValue.length() - 1; i >= 0; i--) {
            int digit = charValueInDecimal(theValue.charAt(i));
            originalNumb = originalNumb.add(power.multiply(BigInteger.valueOf(digit)));
            power = power.multiply(BigInteger.valueOf(initialBase));
        }
        
        String newNumb = "";
        // Finishing up the convert to base 10
        //Now convert from base 10 to other desire base
        
        BigInteger desired = new BigInteger(String.valueOf(finalBase));
        
        while (!originalNumb.equals(BigInteger.ZERO)) {
            BigInteger remainder = originalNumb.mod(desired);
            newNumb = digitInChar(remainder.intValue()) + newNumb;
            originalNumb = originalNumb.divide(desired);
        }
        return newNumb;
    }
}

