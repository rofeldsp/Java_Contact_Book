package contacts;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class RecordBook implements Serializable {
    private ArrayList<Record> book;
    private int numberOfRecords;

    public RecordBook() {
        this.book = new ArrayList<>();
        this.numberOfRecords = 0;
    }

    public void addRecord(Record addedRecord) {
       book.add(addedRecord);
       System.out.println("A record created!\n");
       numberOfRecords++;
    }

    public void addSearchRecord(Record addedRecord) {
        book.add(addedRecord);
        numberOfRecords++;
    }

    public int countRecords() {
        return numberOfRecords;
    }

    void getAction(int index, RecordBook targetBook) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\n[record] Enter action (edit, delete, menu): ");
            String action = scanner.nextLine();
            if (action.contentEquals("edit")) {
                book.get(index).editRecord();
            } else if (action.contentEquals("delete")) {
                removeRecordByReference(book.get(index), targetBook);
                return;
            } else if (action.contentEquals("menu")) {
                return;
            } else {
                System.out.println("There is no such action!");
            }
        }
    }

//    void searchBook() {
//
//    }

    public Record get(int index) {
        return book.get(index);
    }

    public void removeRecord(int index) {
        book.remove(index); // or -1?
        numberOfRecords--;
    }

    void removeRecordByReference(Record record, RecordBook targetBook) {
        for (int i = 0; i < countRecords(); i++) {
            if (book.get(i).equals(record)) {
                targetBook.removeRecord(i);
                return;
            }
        }
    }

    public void listRecords() {
        int index = 0;
        if (numberOfRecords == 0) {
            System.out.println("The Phone Book has 0 records.");
            return;
        } else {
            for (Record record : book) {
                if (record.getClass() == Organization.class) {
                    System.out.println(++index + ". " + record.getName());
                } else {
                    System.out.println(++index + ". " + record.getName() + " " + ((Contact)record).getSurname());
                }
            }
        }
    }

//    public boolean editRecord_Organization(int index, String field) {
//        Scanner scanner = new Scanner(System.in);
//        if (field.matches("name")) {
//            System.out.print("Enter name: > ");
//            String newName = scanner.nextLine();
//            book.get(index).setName(newName);
//            return true;
//        } else if (field.matches("address")) {
//            System.out.print("Enter surname: > ");
//            String newAddress = scanner.nextLine();
//            ((Organization)book.get(index)).setAddress(newAddress);
//            return true;
//        } else if (field.matches("number")) {
//            System.out.print("Enter number: > ");
//            String newNumber = scanner.nextLine();
//            book.get(index).setNumber(newNumber);
//            return true;
//        } else {
//            return false;
//        }
//    }

//    public boolean editRecord_Contact (int index, String field) {
//        Scanner scanner = new Scanner(System.in);
////        String name = book.get(index).getName();
////        String surname = ((Contact)book.get(index)).getSurname();
////        String number = book.get(index).getNumber();
////        String gender = ((Contact)book.get(index)).getGender();
////        String birtDate = ((Contact)book.get(index)).getBirthday().toString();
//        if (field.matches("name")) {
//            System.out.print("Enter name: > ");
//            String newName = scanner.nextLine();
////            book.set(index, new Contact(newName, surname, number, birtDate, gender));
//            book.get(index).setName(newName);
//            return true;
//        } else if (field.matches("surname")) {
//            System.out.print("Enter surname: > ");
//            String newSurname = scanner.nextLine();
////            book.set(index, new Contact(name, newSurname, number, birtDate, gender));
//            ((Contact)book.get(index)).setSurname(newSurname);
//            return true;
//        } else if (field.matches("number")) {
//            System.out.print("Enter number: > ");
//            String newNumber = scanner.nextLine();
////            book.set(index, new Contact(name, surname, newNumber, birtDate, gender));
//            book.get(index).setNumber(newNumber);
//            return true;
//        } else if (field.matches("gender")) {
//            System.out.print("Enter gender: > ");
//            String newGender = scanner.nextLine();
////            book.set(index, new Contact(name, surname, newNumber, birtDate, gender));
//            ((Contact)book.get(index)).setGender(newGender);
//            return true;
//        } else if (field.matches("birth date")) {
//            System.out.print("Enter gender: > ");
//            String newBirthDate = scanner.nextLine();
////            book.set(index, new Contact(name, surname, newNumber, birtDate, gender));
//            ((Contact)book.get(index)).setBirthDate(newBirthDate);
//            return true;
//        } else {
//            return false;
//        }
//    }
}
