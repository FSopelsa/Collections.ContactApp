package se.lexicon;          //  <- Should this not be here?

import static ContactApp.repository;          //  <- Should this be here?
//"Implicitly declared class of a compact source file 'ContactApp' cannot be referenced"

public class ContactApp {

    public static final Scanner scanner = new Scanner(System.in);
    public static final ContactRepository repository =
            new ContactRepository();

    public static void main(String[] args) {
//"Method 'main()' does not have signature 'public static void main(String[])'
//Method 'main(java.lang.String[])' is never used"


        boolean running = true;

        while (running) {

            showMenu();                     // "Cannot resolve method 'showMenu' in 'ContactApp'"

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
}

private static void addContactMenu() {
    System.out.print("Name: ");
    String name = ContactApp.scanner.nextLine();          //  like this?

    System.out.print("Email: ");
    String email = scanner.nextLine();          //  import static constant`?

    System.out.print("Phone: ");
    String phone = scanner.nextLine();          //  import static constant`?

    Contact contact =
            new Contact(nextId++, name, email, phone);    //Should nextId be a local variable? declared where?

    repository.addContact(contact);     // import static constant?
}

private static void displayContactsMenu() {
    List<Contact> contacts = repository.findAll();     // import static constant?
    if (contacts.isEmpty()) {
        System.out.println("No contacts found.");
    } else {
        contacts.forEach(System.out::println);
    }
}

private static void searchContactMenu() {
    System.out.print("Enter name to search: ");
    String name = ContactApp.scanner.nextLine();
    List<Contact> results = repository.searchContactsByName(name);     // import static constant?
    if (results.isEmpty()) {
        System.out.println("No contacts found.");
    } else {
        results.forEach(System.out::println);
    }
}

private static void updateContactMenu() {
    System.out.print("Enter ID of contact to update: ");
    int id = Integer.parseInt(ContactApp.scanner.nextLine());
    Contact contact = repository.findContactById(id);     // import static constant?
    if (contact == null) {
        System.out.println("Contact not found.");
        return;
    }

    System.out.print("New name: ");
    String name = ContactApp.scanner.nextLine();

    System.out.print("New email: ");
    String email = ContactApp.scanner.nextLine();

    System.out.print("New phone: ");
    String phone = ContactApp.scanner.nextLine();

    repository.updateContact(new Contact(id, name, email, phone));     // import static constant?
}

private static void deleteContactMenu() {
    System.out.print("Enter ID of contact to delete: ");
    int id = Integer.parseInt(ContactApp.scanner.nextLine());
    boolean deleted = repository.deleteContactById(id);     // import static constant?
    if (deleted) {
        System.out.println("Contact deleted.");
    } else {
        System.out.println("Contact not found.");
    }
}