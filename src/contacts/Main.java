package contacts;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Main implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        File contactBook = new File("./contact_book.ser")
        String filename;
        if (args.length == 0) {
           filename = "contact_book.ser";
        } else {
            filename = "./" + args[0];
        }
        File file = new File(filename);
        RecordBook book;
        if (!(file.exists())) {
            file.createNewFile();
            book = new RecordBook();
        } else {
            book = (RecordBook) deserialize(filename);
        }
        parseCommands(book);
        serialize(filename, book);
    }

    public static Object deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        Object obj = ois.readObject();
        ois.close();
        return obj;
    }

    public static void serialize(String fileName, Object obj) throws IOException, ClassNotFoundException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    static void addRecord(RecordBook book) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the type (person, organization): > ");
            String type = scanner.nextLine();
            if (type.contentEquals("person")) {
               addContact(book);
               return;
            } else if (type.contentEquals("organization")) {
               addOrganization(book);
               return;
            }
            System.out.println("You entered wrong record type, try once more.");
        }
    }

    private static void addOrganization(RecordBook book) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the organization name: > ");
        String name = scanner.nextLine();
        System.out.print("Enter the address: > ");
        String address = scanner.nextLine();
        System.out.print("Enter the number: > ");
        String number = scanner.nextLine();
        Organization organization = new Organization(name, number, address);
        book.addRecord(organization);
    }

    public static void addContact(RecordBook book) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name: > ");
        String name = sc.nextLine();
        System.out.print("Enter the surname > ");
        String surname = sc.nextLine();
        System.out.print("Enter the birth date > ");
        String birthDate = sc.nextLine();
        System.out.print("Enter the gender (M, F) > ");
        String gender = sc.nextLine();
        System.out.print("Enter the number: > ");
        String number = sc.nextLine();
        Contact newContact = new Contact(name, surname, number, birthDate, gender);
        book.addRecord(newContact);
    }

    public static void listBook(RecordBook book) {
        if (book.countRecords() == 0) {
            System.out.println("There is 0 records in contact book!");
        } else {
            Scanner scanner = new Scanner(System.in);
            book.listRecords();
            while (true) {
                System.out.print("\n[list] Enter action ([number], back): ");
                String scanned = scanner.nextLine();
                if (scanned.contentEquals("back")) {
                    return;
                } else if (scanned.matches("\\d")) {
                    int index = Integer.parseInt(scanned);
                    if (index > 0 && index <= book.countRecords()) {
                        index--;
                        book.get(index).getInfo();
                        book.getAction(index, book);
                        return;
                    }
                }
                System.out.println("You entered the wrong action");
            }
        }
    }

    static void searchBook(RecordBook book) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        Pattern pattern = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
        RecordBook searchResults = new RecordBook();
        for (int i = 0; i < book.countRecords(); i++) {
           for (String field : book.get(i).getFields()) {
               if (pattern.matcher(book.get(i).getFieldValue(field)).find()) {
                   searchResults.addSearchRecord(book.get(i));
               }
           }
        }
        if (searchResults.countRecords() == 0) {
            System.out.println("Nothing found.");
        } else {
            System.out.printf("Found %d records:\n", searchResults.countRecords());
            searchResults.listRecords();
            while (true) {
                System.out.print("\n[list] Enter action ([number], back, again): ");
                String scanned = scanner.nextLine();
                if (scanned.contentEquals("back")) {
                    return;
                } else if (scanned.contentEquals("again")) {
                    searchBook(book);
                    return;
                } else if (scanned.matches("\\d")) {
                    int index = Integer.parseInt(scanned);
                    if (index > 0 && index <= searchResults.countRecords()) {
                        index--;
                        searchResults.get(index).getInfo();
                        searchResults.getAction(index, book);
                        return;
                    }
                }
                System.out.println("You entered a wrong action");
            }
        }
    }

   public static void parseCommands(RecordBook book) {
       Scanner scanCommand = new Scanner(System.in);
       while (true) {
           System.out.print("Enter action (add, list, search, count, exit): > ");
           String command = scanCommand.nextLine();

           if (command.matches("exit")) {
               return;
           } else if (command.matches("add")) {
               addRecord(book);
           } else if (command.matches("list")) {
               listBook(book);
           } else if (command.matches("count")) {
               int a = 0;
               a = book.countRecords();
               System.out.printf("The Phone Book has %d records.\n", a);
           } else if (command.matches("search")) {
               searchBook(book);
           } else {
               System.out.println("Command not found!");
           }
       }
   }
}

