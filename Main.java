package contacts;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactBook book = new ContactBook();
        parseCommands(book);
//        addContact(book);
//        addContact(book);
    }

    public static void addContact(ContactBook book) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the person: > ");
        String name = sc.nextLine();
        System.out.print("Enter the surname of the person: > ");
        String surname = sc.nextLine();
        System.out.print("Enter the number: > ");
        String number = sc.nextLine();
        Contact newContact = new Contact(name, surname, number);
        book.addRecord(newContact);
//        sc.close();
    }

    public static void removeContact(ContactBook book) {
        if (book.countRecords() == 0) {
            System.out.println("No records to remove!");
        } else {
            Scanner scanner = new Scanner(System.in);
            book.listRecords();
            while (true) {
                System.out.print("Select a record: > ");
                String record = scanner.nextLine();
                if (!(record.matches("\\d"))) {
                    System.out.println("Wrong input. Please enter the record number instead.");
                    continue;
                }
                int index = Integer.parseInt(record);
                book.removeRecord(index);
                System.out.println("The record removed!");
                return;
            }
        }
    }

    public static void editContactRecord(ContactBook book) {
        if (book.countRecords() == 0) {
            System.out.println("No records to edit!");
        } else {
            Scanner scanner = new Scanner(System.in);
            book.listRecords();
            System.out.print("Select a record: > ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            while (true) {
                System.out.print("Select a field (name, surname, number): > ");
                String field = scanner.nextLine();
                if (book.editRecord(index, field)) {
                    System.out.println("The record updated!");
                    return;
                } else {
                    System.out.println("There is no such field");
                }
            }
        }
    }

   public static void parseCommands(ContactBook book) {
       Scanner scanCommand = new Scanner(System.in);
       while (true) {
           System.out.print("Enter action (add, remove, edit, count, list, exit): > ");
           String command = scanCommand.nextLine();

           if (command.matches("exit")) {
               return;
           } else if (command.matches("add")) {
               addContact(book);
           } else if (command.matches("remove")) {
               removeContact(book);
           } else if (command.matches("count")) {
               System.out.printf("The Phone Book has %d records.\n", book.countRecords());
           } else if (command.matches("list")) {
               book.listRecords();
           } else if (command.matches("edit")) {
                editContactRecord(book);
           } else {
               System.out.println("Command not found!");
           }
       }
   }
}

