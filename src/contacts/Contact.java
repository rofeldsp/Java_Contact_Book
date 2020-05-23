package contacts;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class Record implements Serializable{
    private String name;
    private String number;
    private LocalDateTime timeCreated;
    private LocalDateTime timeLastEdit;

    Record() {
        setTimeCreated();
        setTimeLastEdit(timeCreated);
    }

    abstract String[] getFields();

    abstract void changeField(String fieldName, String value);

    abstract String getFieldValue(String fieldName);

    abstract void getInfo();

    Pattern pattern = Pattern.compile("(((((\\+?\\(\\w+\\)([\\h]|[-])?)?(\\w{2,}(\\h|[-])?)?)|((\\+?\\w+([\\h]|[-])?)?(\\(\\w{2,}\\)(\\h|[-])?)?))((\\w{2,}(\\h|[-])?)*\\w{2,})?)" +
            "|((\\+?\\w([\\h]|[-]))?(\\w{2,}([\\h]|[-])?)?" +
            "(\\(\\w{2,}(\\h|[-])?)*\\w{2,}\\)?)" +
            "|(\\+?\\(\\w{2,}\\)))", Pattern.CASE_INSENSITIVE);

    String getNumber() {
        return this.number;
    }

    void setNumber(String number) {
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

    String getName() {
        return this.name;
    }

    void setName(String name) {
        this.name = name;
    }

    void setTimeCreated() {
        timeCreated = LocalDateTime.now();
    }

    void setTimeLastEdit(LocalDateTime time) {
        timeLastEdit = time;
    }

    LocalDateTime getTimeCreated() {
        return timeCreated.withNano(0);
    }

    LocalDateTime getTimeLastEdit() {
        return  timeLastEdit.withNano(0);
    }

    void editRecord() {
        String[] fields = getFields();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Select a field (");
            for (int i = 0; i < fields.length; i++) {
                System.out.print(fields[i]);
                if (i + 1 == fields.length) {
                    System.out.print("): ");
                } else {
                    System.out.print(", ");
                }
            }
            String inputField = scanner.nextLine();
            for (String field : fields) {
                if (field.contentEquals(inputField)) {
                    System.out.print("Enter " + field + ": ");
                    String value = scanner.nextLine();
                    changeField(field, value);
                    System.out.println("Saved");
                    return;
                }
            }
            System.out.println("There is no such field");
        }
    }
}

class Organization extends Record implements Serializable {
    private String address;

    Organization(String name, String number, String address) {
        super();
        setName(name);
        setNumber(number);
        setAddress(address);
    }

    void setAddress(String address) {
        this.address = address;
    }

    String getAddress() {
        return address;
    }

    @Override
    String[] getFields() {
        ArrayList<String> fields = new ArrayList<String>();
        fields.add("name");
        fields.add("number");
        fields.add("address");
        return fields.toArray(new String[0]);
    }

    @Override
    void changeField(String fieldName, String value) {
        if (fieldName.contentEquals("name")) {
           setName(value);
        } else if (fieldName.contentEquals("number")) {
            setNumber(value);
        } else if (fieldName.contentEquals("address")) {
           setAddress(value);
        }
    }

    @Override
    String getFieldValue(String fieldName) {
        if (fieldName.contentEquals("name")) {
            return getName();
        } else if (fieldName.contentEquals("number")) {
            return getNumber();
        } else if (fieldName.contentEquals("address")) {
            return getAddress();
        }
        return null;
    }

    @Override
    void getInfo() {
        System.out.println("Organization name: " + getName());
        System.out.println("Address: " + getAddress());
        System.out.println("Number: " + getNumber());
        System.out.println("Time created: " + getTimeCreated());
        System.out.println("Time last edit: " + getTimeLastEdit() + "\n");
    }
}

class Contact extends Record implements Serializable {
    private String surname;
    private String birthDate;
    private String gender;

    Contact(String name, String surname, String number, String birthDate, String gender) {
        super();
        setName(name);
        setSurname(surname);
        setNumber(number);
        setBirthDate(birthDate);
        setGender(gender);
    }

    String getSurname() {
        return this.surname;
    }

    void setSurname(String surname) {
        this.surname = surname;
    }

    void setBirthDate(String birthDate) {
        try {
            this.birthDate = LocalDate.parse(birthDate).toString();
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("Bad birth date!");
            this.birthDate = "[no data]";
        }
    }

    String getBirthday() {
        return birthDate;
    }

    void setGender(String gender) {
        if (gender.contentEquals("M") || gender.contentEquals("F")) {
            this.gender = gender;
        } else {
            System.out.println("Bad gender!");
            this.gender = "[no data]";
        }
    }

    String getGender() {
        return gender;
    }

    @Override
    String[] getFields() {
        ArrayList<String> fields = new ArrayList<String>();
        fields.add("name");
        fields.add("surname");
        fields.add("number");
        fields.add("birthDate");
        fields.add("gender");
        return fields.toArray(new String[0]);
    }

    @Override
    void changeField(String fieldName, String value) {
        if (fieldName.contentEquals("name")) {
            setName(value);
        } else if (fieldName.contentEquals("surname")) {
            setSurname(value);
        } else if (fieldName.contentEquals("number")) {
            setNumber(value);
        } else if (fieldName.contentEquals("birthDate")) {
            setBirthDate(value);
        } else if (fieldName.contentEquals("gender")) {
            setGender(value);
        }
    }

    @Override
    String getFieldValue(String fieldName) {
        if (fieldName.contentEquals("name")) {
            return getName();
        } else if (fieldName.contentEquals("surname")) {
            return getSurname();
        } else if (fieldName.contentEquals("number")) {
            return getNumber();
        } else if (fieldName.contentEquals("birthDate")) {
            return getBirthday();
        } else if (fieldName.contentEquals("gender")) {
            return getGender();
        }
        return null;
    }

    void getInfo() {
        System.out.println("Name: " + getName());
        System.out.println("Surname: " + getSurname());
        System.out.println("Birth date: " + getBirthday());
        System.out.println("Gender: " + getGender());
        System.out.println("Number: " + getNumber());
        System.out.println("Time created: " + getTimeCreated());
        System.out.println("Time last edit: " + getTimeLastEdit() + "\n");
    }
}
