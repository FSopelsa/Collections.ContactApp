package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ContactRepositoryTest {

    private ContactRepository repository;

    @BeforeEach
    void setUp() {
        repository = new ContactRepository();
    }

    @Test
    void addContact_shouldAddContact() {
        Contact contact = contact(1, "Alice", "alice@example.com", "1234567");

        repository.addContact(contact);

        assertEquals(1, repository.findAll().size());
        assertEquals(contact, repository.findAll().get(0));
    }

    @Test
    void addContact_shouldRejectNullContact() {
        assertThrows(IllegalArgumentException.class, () -> repository.addContact(null));
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void searchContactsByName_shouldReturnMatchingContacts() {
        Contact contact = contact(1, "Alice", "alice@example.com", "1234567");
        repository.addContact(contact);

        List<Contact> results = repository.searchContactsByName("alice");

        assertEquals(1, results.size());
        assertEquals(contact, results.get(0));
    }

    @Test
    void searchContactsByName_shouldReturnEmptyListWhenNoMatch() {
        repository.addContact(contact(1, "Alice", "alice@example.com", "1234567"));

        List<Contact> results = repository.searchContactsByName("Bob");

        assertTrue(results.isEmpty());
    }

    @Test
    void deleteContactById_shouldDeleteExistingContact() {
        repository.addContact(contact(1, "Alice", "alice@example.com", "1234567"));

        boolean deleted = repository.deleteContactById(1);

        assertTrue(deleted);
        assertTrue(repository.findAll().isEmpty());
    }

    @Test
    void deleteContactById_shouldReturnFalseForMissingContact() {
        repository.addContact(contact(1, "Alice", "alice@example.com", "1234567"));

        boolean deleted = repository.deleteContactById(2);

        assertFalse(deleted);
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void updateContact_shouldUpdateExistingContact() {
        repository.addContact(contact(1, "Alice", "alice@example.com", "1234567"));

        boolean updated = repository.updateContact(contact(1, "Alicia", "alicia@example.com", "7654321"));

        assertTrue(updated);
        Contact updatedContact = repository.findContactById(1);
        assertNotNull(updatedContact);
        assertEquals("Alicia", updatedContact.getName());
        assertEquals("alicia@example.com", updatedContact.getEmail());
        assertEquals("7654321", updatedContact.getPhoneNumber());
    }

    @Test
    void updateContact_shouldReturnFalseForMissingContact() {
        repository.addContact(contact(1, "Alice", "alice@example.com", "1234567"));

        boolean updated = repository.updateContact(contact(2, "Alicia", "alicia@example.com", "7654321"));

        assertFalse(updated);
        Contact unchangedContact = repository.findContactById(1);
        assertNotNull(unchangedContact);
        assertEquals("Alice", unchangedContact.getName());
    }

    @Test
    void findAll_shouldReturnContactsSortedByName() {
        repository.addContact(contact(2, "Charlie", "charlie@example.com", "2222222"));
        repository.addContact(contact(1, "alice", "alice@example.com", "1111111"));
        repository.addContact(contact(3, "Bob", "bob@example.com", "3333333"));

        List<Contact> contacts = repository.findAll();

        assertEquals(3, contacts.size());
        assertEquals("alice", contacts.get(0).getName());
        assertEquals("Bob", contacts.get(1).getName());
        assertEquals("Charlie", contacts.get(2).getName());
    }

    private static Contact contact(int id, String name, String email, String phoneNumber) {
        return new Contact(id, name, email, phoneNumber);
    }
}
