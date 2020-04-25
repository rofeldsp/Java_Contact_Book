package contacts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String name;
    private String surname;
    private String number;

    Pattern pattern = Pattern.compile("(((((\\+?\\(\\w+\\)([\\h]|[-])?)?(\\w{2,}(\\h|[-])?)?)|((\\+?\\w+([\\h]|[-])?)?(\\(\\w{2,}\\)(\\h|[-])?)?))((\\w{2,}(\\h|[-])?)*\\w{2,})?)" +
            "|((\\+?\\w([\\h]|[-]))?(\\w{2,}([\\h]|[-])?)?" +
            "(\\(\\w{2,}(\\h|[-])?)*\\w{2,}\\)?)" +
            "|(\\+?\\(\\w{2,}\\)))", Pattern.CASE_INSENSITIVE);

    public Contact(String name, String surname, String number) {
        setName(name);
        setSurname(surname);
        setNumber(number);
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        Matcher matcher = pattern.matcher(number);
        if (numberIsValid(number)) {
            this.number = number;
        } else {
            System.out.println("Wrong number format!");
            this.number = "[no number]";
        }
    }

    private boolean numberIsValid(String number) {
        Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
}
