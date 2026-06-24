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
        Contact existingContact = findContactById(updatedContact.getId());

        if (existingContact == null) {
            return false;
        }

        existingContact.setName(updatedContact.getName());
        existingContact.setEmail(updatedContact.getEmail());
        existingContact.setPhoneNumber(updatedContact.getPhoneNumber());

        return true;
    }

    public boolean deleteContactById(int id) {
        return contacts.removeIf(contact -> contact.getId() == id);
    }

}