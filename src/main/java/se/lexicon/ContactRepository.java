package se.lexicon;

import java.util.ArrayList;
import java.util.List;

public class ContactRepository {

    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public List<Contact> findAll() {
        return contacts;
    }

    public List<Contact> searchContactsByName(String name) {
        List<Contact> results = new ArrayList<>();

        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                results.add(contact);
            }
        }

        return results;
    }

    public Contact findContactById(int id) {

        // Så-länge-kod
        for (Contact contact : contacts) {
            if (contact.getId() == id) {
                return contact;
            }
        }

        return null;
    }

    public boolean updateContact(Contact updatedContact) {
        return true;
    }

    public boolean deleteContactById(int id) {
        return true;
    }

}