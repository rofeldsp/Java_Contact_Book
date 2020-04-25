package contacts;


import java.util.ArrayList;
import java.util.Scanner;

public class ContactBook {
    private ArrayList<Contact> book;
    private int numberOfRecords;

    public ContactBook() {
        this.book = new ArrayList<>();
        this.numberOfRecords = 0;
    }

    public void addRecord(Contact addedContact) {
       book.add(addedContact);
       System.out.println("A record created!");
       numberOfRecords++;
    }

    public int countRecords() {
        return numberOfRecords;
    }

    public void removeRecord(int index) {
        book.remove(index - 1);
        numberOfRecords--;
    }

    public void listRecords() {
        int index = 0;
        if (numberOfRecords == 0) {
            System.out.println("The Phone Book has 0 records.");
            return;
        } else {
            for (Contact contact : book) {
                System.out.println(++index + ". " + contact.getName() + " "
                        + contact.getSurname() + ", " + contact.getNumber());
            }
        }
    }

    public boolean editRecord (int index, String field) {
        Scanner scanner = new Scanner(System.in);
        String name = book.get(index).getName();
        String surname = book.get(index).getSurname();
        String number = book.get(index).getNumber();
        if (field.matches("name")) {
            System.out.print("Enter name: > ");
            String newName = scanner.nextLine();
            book.set(index, new Contact(newName, surname, number));
            return true;
        } else if (field.matches("surname")) {
            System.out.print("Enter surname: > ");
            String newSurname = scanner.nextLine();
            book.set(index, new Contact(name, newSurname, number));
            return true;
        } else if (field.matches("number")) {
            System.out.print("Enter number: > ");
            String newNumber = scanner.nextLine();
            book.set(index, new Contact(name, surname, newNumber));
            return true;
        } else {
            return false;
        }
    }
}
