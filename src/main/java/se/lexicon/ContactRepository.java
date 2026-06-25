package se.lexicon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ContactRepository {

    private final List<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("contact cannot be null");
        }
        contacts.add(contact);
    }

    public List<Contact> findAll() {
        List<Contact> sortedContacts = new ArrayList<>(contacts);
        sortedContacts.sort(Comparator.comparing(
                Contact::getName,
                Comparator.nullsLast(String.CASE_INSENSITIVE_ORDER)
        ));
        return sortedContacts;
    }

    public List<Contact> searchContactsByName(String name) {
        List<Contact> results = new ArrayList<>();

        if (name == null) {
            return results;
        }

        for (Contact contact : contacts) {
            if (contact.getName() != null && contact.getName().equalsIgnoreCase(name)) {
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
        if (updatedContact == null) {
            return false;
        }

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

    public void setAll(List<Contact> newContacts) {
        contacts.clear();
        contacts.addAll(newContacts);
    }

    public int getMaxId() {
        return contacts.stream()
                .mapToInt(Contact::getId)
                .max()
                .orElse(0);
    }

}
