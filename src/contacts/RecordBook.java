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
}
