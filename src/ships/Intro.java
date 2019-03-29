package ships;

import java.util.Arrays;
import java.util.List;

public class Intro {

    public static class Person {
        String firstName;
        String lastName;
        Address address;
        List<Account> accounts;

        public Person(String firstName, String lastName, Address address, List<Account> accounts) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.address = address;
            this.accounts = accounts;
        }
    }

    public static class Address {
        String street;
        String city;
        Integer zip;

        public Address(String street, String city, Integer zip) {
            this.street = street;
            this.city = city;
            this.zip = zip;
        }
    }

    public static class Account {
        String iban;

        public Account(String iban) {
            this.iban = iban;
        }
    }

    public static void main(String[] args) {

        Person peter = new Person(
                "Peter",
                "Lustig",
                new Address("Street A", "City A", 12345),
                Arrays.asList(new Account("DE1234567890"), new Account("US1234567890"), new Account("DE9876543210"))
        );

        Person petra = new Person(
                "Petra",
                "Lustig",
                new Address("Street B", "City A", 12345),
                Arrays.asList(new Account("DE1234567890"), new Account("US1234567891"))
        );
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
             if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

/*    public static int indexOfAny(final CharSequence cs, final char... searchChars) {
        if (isEmpty(cs) || ArrayUtils.isEmpty(searchChars)) {
            return -1;
        }
        final int csLen = cs.length();
        final int csLast = csLen - 1;
        final int searchLen = searchChars.length;
        final int searchLast = searchLen - 1;
        for (int i = 0; i < csLen; i++) {
            final char ch = cs.charAt(i);
            for (int j = 0; j < searchLen; j++) {
                if (searchChars[j] == ch) {
                    if (i < csLast && j < searchLast && Character.isHighSurrogate(ch)) {
                        if (searchChars[j + 1] == cs.charAt(i + 1)) {
                            return i;
                        }
                    } else {
                        return i;
                    }
                }
            }
        }
        return -1;
    }*/
}

