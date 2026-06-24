package se.lexicon;

import java.util.ArrayList;
import java.util.List;

import static se.lexicon.ContactApp.repository;
import static se.lexicon.ContactApp.scanner;

public abstract class ContactRepository {

    public final List<Contact> contacts = new ArrayList<>();

    private static void addContactMenu() {

        System.out.print("Name: ");
        String name = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        Contact contact =
                new Contact(nextId++, name, email, phone);            //nextId kommer från/hålls reda på var?

        repository.addContact(contact);
    }

    public abstract List<Contact> displayContactsMenu(); {
        return contacts;
    }

    private static void searchContactMenu() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        List<Contact> results = repository.searchContactsByName(name);
        if (results.isEmpty()) {
            System.out.println("No contacts found.");
        } else {
            results.forEach(System.out::println);
        }
    }

    public Contact findById(int id) {
        // search logic
    }

    public boolean deleteContactMenu(int id) {
        // delete logic
    }

    public boolean updateContactMenu(Contact updatedContact) {
        // update logic
    }
}