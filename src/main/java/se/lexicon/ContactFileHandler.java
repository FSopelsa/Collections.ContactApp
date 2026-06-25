package se.lexicon;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ContactFileHandler {

    private static final String CSV_SEPARATOR = ",";

    public void saveContacts(List<Contact> contacts, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Contact contact : contacts) {
                writer.println(contact.getId() + CSV_SEPARATOR +
                        contact.getName() + CSV_SEPARATOR +
                        contact.getEmail() + CSV_SEPARATOR +
                        contact.getPhoneNumber());
            }
        }
    }

    public List<Contact> loadContacts(String filePath) throws IOException {
        List<Contact> contacts = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return contacts;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(CSV_SEPARATOR);
                if (parts.length == 4) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        String email = parts[2];
                        String phoneNumber = parts[3];
                        contacts.add(new Contact(id, name, email, phoneNumber));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid contact line: " + line);
                    }
                }
            }
        }
        return contacts;
    }
}
