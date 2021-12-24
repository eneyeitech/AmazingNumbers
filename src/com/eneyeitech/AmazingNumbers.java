package com.eneyeitech;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AmazingNumbers {

    private static StringBuilder stringBuilder = new StringBuilder();
    public static void main(String[] args) {
//       public static void main(String[] args) {
//        write your code here
        Scanner scanner = new Scanner(System.in);
        //testBuzz(scanner);
        //testDuck(scanner);

        request(scanner);


    }

    public static void request(Scanner scanner){
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println(" * the first parameter represents a starting number;");
        System.out.println(" * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        processRequest(scanner);
    }

    public static void processRequest(Scanner scanner){
        long number = 8;
        do {
            System.out.print("\nEnter a request: ");
            try {
                String input = scanner.nextLine();
                String[] entry = input.split(" ");
                if (entry.length == 2){
                    number = Long.parseLong(entry[0]);
                    int i = Integer.parseInt(entry[1]);
                    properties(number, i);
                } else if (entry.length == 1){
                    number = Long.parseLong(entry[0]);
                    properties(number);
                } else if (entry.length == 3){
                    number = Long.parseLong(entry[0]);
                    int i = Integer.parseInt(entry[1]);
                    String property = entry[2].toLowerCase();
                    filterProperties(number, i, property);
                } else if (entry.length == 4){
                    number = Long.parseLong(entry[0]);
                    int i = Integer.parseInt(entry[1]);
                    String property1 = entry[2].toLowerCase();
                    String property2 = entry[3].toLowerCase();
                    if(property1.equals(property2)) {
                        filterProperties(number, i, property1);
                    } else if (!isValidRequest(property1, property2)) {
                        //filterProperties(number, i, property1, property2);
                    } else {
                        filterProperties(number, i, property1, property2 );
                    }
                } else if (entry.length > 4){
                    number = Long.parseLong(entry[0]);
                    int i = Integer.parseInt(entry[1]);
                    boolean cont = true;
                    for (int m = 2; m < entry.length; m++){

                       if(!checkValidityR(entry[m])){
                           cont = false;
                       }
                    }
                    System.out.println(stringBuilder.toString());
                    System.out.println(cont);
                    stringBuilder = new StringBuilder();

                }
            } catch (NumberFormatException e) {
                System.out.println("The first parameter should be a natural number or zero.");
            }
        } while (number != 0);

    }

    public static void properties(long number, int i) {
        if (i > 0) {
            long n = number;
            for (int j = 0; j < i; j++) {
                System.out.println(checkProperties(n));
                n++;
            }
        } else {
            System.out.println("The second parameter should be a natural number.");
        }
    }

    public static void filterProperties(long number, int i, String property) {
        switch (property) {
            case "buzz":
            case "duck":
            case "palindromic":
            case "gapful":
            case "spy":
            case "even":
            case "odd":
            case "sunny":
            case "square":
            case "jumping":
                break;
            default:
                System.out.printf("The property [%s] is wrong.\n" +
                        "Available properties: [BUZZ, JUMPING, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY, EVEN, ODD]\n", property.toUpperCase());
                return;
        }
        if (i > 0) {

            int c = 0;
            long n = number;
            while (c < i) {

                if (properties(n, property)) {
                    System.out.println(checkProperties(n));
                    c++;
                }
                n++;

            }
        } else {
            System.out.println("The second parameter should be a natural number.");
        }
    }

    public static boolean checkValidity(String p) {
        switch (p) {
            case "buzz":
            case "duck":
            case "palindromic":
            case "gapful":
            case "spy":
            case "even":
            case "odd":
            case "sunny":
            case "square":
            case "jumping":
                return true;
            default:
                return false;
        }
    }

    public static boolean checkValidityR(String p) {

        switch (p) {
            case "buzz":
            case "duck":
            case "palindromic":
            case "gapful":
            case "spy":
            case "even":
            case "odd":
            case "sunny":
            case "square":
            case "jumping":
                return true;
            default:
                stringBuilder.append(" "+p+", ");
                return false;
        }
    }

    public static boolean isJumping(long number){
        String numStr = String.valueOf(number);
        int len = numStr.length();
        if(len == 1){
            return true;
        }
        long temp = number;
        while (number != 0) {
            long digit1 = number % 10;
            number = number / 10;
            if(number != 0) {
                long digit2 = number % 10;
                if (Math.abs((digit1 - digit2)) != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void filterProperties(long number, int m, String property, String otherProperty) {
        if(!checkValidity(property)){
            if(!checkValidity(otherProperty)){
                System.out.printf("The properties [%s, %s] are wrong.\n" +
                        "Available properties: [EVEN, JUMPING, ODD, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]", property.toUpperCase(), otherProperty.toUpperCase());
                return;
            }
        }
      if(!checkValidity(property)){
          System.out.printf("The property [%s] is wrong.\n" +
                  "Available properties: [EVEN, ODD, JUMPING, BUZZ, DUCK, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]", property.toUpperCase());
          return;
      }
        if(!checkValidity(otherProperty)){
            System.out.printf("The property [%s] is wrong.\n" +
                    "Available properties: [EVEN, ODD, BUZZ, DUCK, JUMPING, PALINDROMIC, GAPFUL, SPY, SQUARE, SUNNY]", otherProperty.toUpperCase());
            return;
        }



        if (m > 0) {

            int c = 0;
            long n = number;
            while (c < m) {

                if (twoProperties(n, property, otherProperty)) {
                    System.out.println(checkProperties(n));
                    c++;
                }
                n++;

            }
        } else {
            System.out.println("The second parameter should be a natural number.");
        }

    }

    public static boolean properties(long number, String property) {

        switch (property) {
            case "even":
                if (isEven(number)){
                    return true;
                }
                return false;
            case "odd":
                if (!isEven(number)){
                    return true;
                }
                return false;
            case "buzz":
                if (isBuzz(number)){
                    return true;
                }
                return false;
            case "duck":
                if (isDuck(number)){
                    return true;
                }
                return false;
            case "palindromic":
                if (isPalindromic(number)){
                    return true;
                }
                return false;
            case "gapful":
                if (isGapful(number)){
                    return true;
                }
                return false;
            case "spy":
                if (isSpy(number)){
                    return true;
                }
                return false;
            case "sunny":
                if (isSunny(number)){
                    return true;
                }
                return false;
            case "square":
                if (isPerfectSquare(number)){
                    return true;
                }
                return false;
            case "jumping":
                if (isJumping(number)){
                    return true;
                }
                return false;
            default:

                return false;

        }


    }

    public static boolean isValidRequest(String p, String q) {
        if(p.equals("even") || p.equals("odd")) {
            if(q.equals("even") || q.equals("odd")) {
                System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                        "There are no numbers with these properties.", p.toUpperCase(), q.toUpperCase());
                return false;

            }
        }

        if(p.equals("duck") || p.equals("spy")) {
            if(q.equals("duck") || q.equals("spy")) {
                System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                        "There are no numbers with these properties.", p.toUpperCase(), q.toUpperCase());
                return false;

            }
        }

        if(p.equals("sunny") || p.equals("square")) {
            if(q.equals("sunny") || q.equals("square")) {
                System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n" +
                        "There are no numbers with these properties.", p.toUpperCase(), q.toUpperCase());
                return false;

            }
        }

        return true;
    }



    public static boolean twoProperties(long number, String property, String otherProperty) {
        boolean p1 = false;
        boolean p2 = false;
        switch (property) {
            case "even":
                if (isEven(number)){
                    p1 = true;
                }
                break;
            case "odd":
                if (!isEven(number)){
                    p1 = true;
                }
                break;
            case "buzz":
                if (isBuzz(number)){
                    p1 = true;
                }
                break;
            case "duck":
                if (isDuck(number)){
                    p1 = true;
                }
                break;
            case "palindromic":
                if (isPalindromic(number)){
                    p1 = true;
                }
                break;
            case "gapful":
                if (isGapful(number)){
                    p1 = true;
                }
                break;
            case "spy":
                if (isSpy(number)){
                    p1 = true;
                }
                break;
            case "sunny":
                if (isSunny(number)) {
                    p1 = true;
                }
                break;
            case "square":
                if (isPerfectSquare(number)){
                    p1 = true;
                }
                break;
            case "jumping":
                if (isJumping(number)){
                    p1 = true;
                }
                break;
            default:
                p1 = false;

        }

        switch (otherProperty) {
            case "even":
                if (isEven(number)){
                    p2 = true;
                }
                break;
            case "odd":
                if (!isEven(number)){
                    p2 = true;
                }
                break;
            case "buzz":
                if (isBuzz(number)){
                    p2 = true;
                }
                break;
            case "duck":
                if (isDuck(number)){
                    p2 = true;
                }
                break;
            case "palindromic":
                if (isPalindromic(number)){
                    p2 = true;
                }
                break;
            case "gapful":
                if (isGapful(number)){
                    p2 = true;
                }
                break;
            case "spy":
                if (isSpy(number)){
                    p2 = true;
                }
                break;
            case "sunny":
                if (isSunny(number)) {
                    p2 = true;
                }
                break;
            case "square":
                if (isPerfectSquare(number)){
                    p2 = true;
                }
                break;
            case "jumping":
                if (isJumping(number)){
                    p2 = true;
                }
                break;
            default:
                p2 = false;

        }

        return p1 && p2;
    }

    public static String checkProperties(long number) {
        String msg = "             " + number + " is";
        if (isEven(number)) {
            msg += " even,";
        }
        if (isGapful(number)) {
            msg += " gapful,";
        }
        if (isBuzz(number)) {
            msg += " buzz,";
        }
        if (isPalindromic(number)) {
            msg += " palindromic,";
        }
        if (!isEven(number)) {
            msg += " odd,";
        }
        if (isDuck(number)) {
            msg += " duck,";
        }
        if (isSpy(number)) {
            msg += " spy,";
        }
        if (isSunny(number)) {
            msg += " sunny,";
        }
        if (isPerfectSquare(number)) {
            msg += " square,";
        }
        if (isJumping(number)) {
            msg += " jumping,";
        }

        return msg;
    }

    public static boolean isSpy(long number) {
        String num = String.valueOf(number);
        int len = num.length();
        long sum = 0;
        long mult = 1;
        if (len > 1) {
            for (int i = 0; i < len; i++) {
                long v = Long.parseLong(String.valueOf(num.charAt(i)));
                sum += v;
                mult *= v;
                //System.out.printf("\n%s : %s : %s : %s\n", sum, mult, v, num);
            }
        } else {
            return true;
        }

        if (sum == mult) {
            return true;
        }
        return false;
    }

    public static boolean isPerfectSquare(long x)
    {
        if (x >= 0) {

            // Find floating point value of
            // square root of x.
            long sr = (long)Math.sqrt(x);

            // if product of square root
            // is equal, then
            // return T/F

            return ((sr * sr) == x);
        }
        return false;
    }

    public static boolean isSunny(long num) {
        return isPerfectSquare(num + 1);
    }

    public static void properties(long number) {
        if (number > 0) {
            System.out.printf("Properties of %d\n", number);
            System.out.printf("      even: %b\n", isEven(number));
            System.out.printf("      odd: %b\n", !isEven(number));
            System.out.printf("      buzz: %b\n", isBuzz(number));
            System.out.printf("      duck: %b\n", isDuck(number));
            System.out.printf("      palindromic: %b\n", isPalindromic(number));
            System.out.printf("      gapful: %b\n", isGapful(number));
            System.out.printf("      spy: %b\n", isSpy(number));
            System.out.printf("      sunny: %b\n", isSunny(number));
            System.out.printf("      square: %b\n", isPerfectSquare(number));
            System.out.printf("      jumping: %b\n", isJumping(number));
        } else if (number < 0) {
            System.out.println("The first parameter should be a natural number or zero.");
        } else {
            System.out.println("\nGoodbye!");
        }
    }

    public static void executeRequest(Scanner scanner){
        long number = 0;
        do {
            System.out.print("\nEnter a request: ");
            try {
                number = scanner.nextLong();
                if (number > 0) {
                    System.out.printf("Properties of %d\n", number);
                    System.out.printf("      even: %b\n", isEven(number));
                    System.out.printf("      odd: %b\n", !isEven(number));
                    System.out.printf("      buzz: %b\n", isBuzz(number));
                    System.out.printf("      duck: %b\n", isDuck(number));
                    System.out.printf("      palindromic: %b\n", isPalindromic(number));
                    System.out.printf("      gapful: %b", isGapful(number));
                } else if (number < 0) {
                    System.out.println("The first parameter should be a natural number or zero.");
                } else {
                    System.out.println("\nGoodbye!");
                }
            } catch (NumberFormatException e) {

            }
        } while (number != 0);

    }

    public static void testDuck(Scanner scanner) {
        System.out.println("Enter a natural number:");
        try {
            long number = scanner.nextLong();
            if (number > 0) {
                System.out.printf("Properties of %d\n", number);
                System.out.printf("      even: %b\n", isEven(number));
                System.out.printf("      odd: %b\n", !isEven(number));
                System.out.printf("      buzz: %b\n", isBuzz(number));
                System.out.printf("      duck: %b\n", isDuck(number));
                System.out.printf("      gapful: %b", isGapful(number));
            } else {
                System.out.println("This number is not natural!");
            }
        } catch (NumberFormatException e) {

        }
    }

    public static boolean isPalindromic(long number) {
        String num = String.valueOf(number);
        int i = 0, j = num.length() - 1;

        while (i < j) {
            if (num.charAt(i) != num.charAt(j)){
                return false;
            }
            i++;
            j--;
        }
        return  true;
    }

    public static boolean isBuzz(long n) {
        return isDivisileBy7(n) || endsWith7(n);
    }

    public static boolean isDuck(long n) {
        return withTrailing0(n);
    }

    public static boolean isLeading0WithoutTrailing0(long number){
        String num = String.valueOf(number);
        if (num.startsWith("0")){
            StringBuilder nstr = new StringBuilder(num);
            nstr.deleteCharAt(0);
            num = nstr.toString();

            if (num.contains("0")){
                return false;
            } else {
                return true;
            }

        }
        return false;
    }

    public static boolean withTrailing0(long number) {
        String num = String.valueOf(number);
        if (num.length() > 1) {
            StringBuilder nstr = new StringBuilder(num);
            nstr.deleteCharAt(0);
            num = nstr.toString();
            if (num.contains("0")){
                return true;
            } else {
                return false;
            }
        }else if (num.equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    public static void testBuzz(Scanner scanner) {
        System.out.println("Enter a natural number:");
        try{
            long number = scanner.nextLong();
            if (number > 0) {
                String m = isEven(number) ? "This number is Even." : "This number is Odd.";
                System.out.println(m);
                m = isDivisileBy7(number) || endsWith7(number) ? "It is a Buzz number." : "It is not a Buzz number.";
                System.out.println(m);
                System.out.println("Explanation:");
                String exp = "";
                if (isDivisileBy7(number) && endsWith7(number)){
                    System.out.printf("%d is divisible by 7 and ends with 7.", number);
                } else if (isDivisileBy7(number)) {
                    System.out.printf("%d is divisible by 7.", number);
                } else if (endsWith7(number)) {
                    System.out.printf("%d ends with 7.", number);
                } else {
                    System.out.printf("%d is neither divisible by 7 nor does it end with 7.", number);
                }
            } else {
                System.out.printf("This number is not natural!");
            }

        }catch(NumberFormatException e){

        }
    }

    public static boolean isEven(long n){
        if(n % 2 == 0){
            return true;
        }
        if(n == 0){
            return true;
        }
        return false;
    }

    public static boolean isGapful(long n) {
        String num = String.valueOf(n);
        if (num.length() == 2) {
            return false;
        }
        String d1 = String.valueOf(num.charAt(0));
        String d2 = String.valueOf(num.charAt(num.length() - 1));
        String dd = d1 + "" + d2;

        long d = Long.parseLong(dd);

        if (n % d == 0) {
            return true;
        }
        return false;
    }

    public static boolean isDivisileBy7(long n){
        if(n % 7 == 0){
            return true;
        }
        return false;
    }

    public static boolean endsWith7(long n){
        String num = String.valueOf(n);
        if(num.endsWith("7")){
            return true;
        }
        return false;
    }
}
