package se.lexicon;

import java.util.Scanner;
import java.util.List;


public class ContactApp {

    public static final Scanner scanner = new Scanner(System.in);
    public static final ContactRepository repository =
            new ContactRepository();

    private static int nextId = 1;

    public static void main(String[] args) {

        System.out.println("--------------------------------");
        System.out.println("Welcome to Contact Manager");

        boolean running = true;
        while (running) {

            showMenu();

            switch (scanner.nextLine()) {
                case "1" -> addContactMenu();
                case "2" -> displayContactsMenu();
                case "3" -> searchContactMenu();
                case "4" -> updateContactMenu();
                case "5" -> deleteContactMenu();
                case "6" -> running = false;
            }
        }
    }

    private static void showMenu() {
        System.out.println("1. Add contact");
        System.out.println("2. Display contacts");
        System.out.println("3. Search contact");
        System.out.println("4. Update contact");
        System.out.println("5. Delete contact");
        System.out.println("6. Exit");
    }

    private static void addContactMenu() {
        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        if (!ValidationUtil.isValidContact(name, email, phone)) {
            System.out.println("Invalid contact details.");
            System.out.println("--------------------------------");
            return;
        }

        Contact contact =
                new Contact(nextId++, name, email, phone);

        repository.addContact(contact);
    }

    private static void displayContactsMenu() {
        List<Contact> contacts = repository.findAll();
        if (contacts.isEmpty()) {
            System.out.println("No contacts found.");
            System.out.println("--------------------------------");
        } else {
            contacts.forEach(System.out::println);
            System.out.println("Total contacts: " + contacts.size());
            System.out.println("--------------------------------");
        }
    }

    private static void searchContactMenu() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        List<Contact> results = repository.searchContactsByName(name);
        if (results.isEmpty()) {
            System.out.println("No contacts found.");
            System.out.println("--------------------------------");
        } else {
            results.forEach(System.out::println);
            System.out.println("--------------------------------");
        }
    }

    private static void updateContactMenu() {
        System.out.print("Enter ID of contact to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Contact contact = repository.findContactById(id);
        if (contact == null) {
            System.out.println("Contact not found.");
            System.out.println("--------------------------------");
            return;
        }

        System.out.print("New name: ");
        String name = scanner.nextLine();

        System.out.print("New email: ");
        String email = scanner.nextLine();

        System.out.print("New phone: ");
        String phone = scanner.nextLine();

        if (!ValidationUtil.isValidContact(name, email, phone)) {
            System.out.println("Invalid contact details.");
            System.out.println("--------------------------------");
            return;
        }

        repository.updateContact(new Contact(id, name, email, phone));
    }

    private static void deleteContactMenu() {
        System.out.print("Enter ID of contact to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean deleted = repository.deleteContactById(id);
        if (deleted) {
            System.out.println("Contact deleted.");
            System.out.println("--------------------------------");
        } else {
            System.out.println("Contact not found.");
            System.out.println("--------------------------------");
        }
    }
}
