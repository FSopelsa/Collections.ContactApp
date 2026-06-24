package se.lexicon;

public class ContactApp {

    public static final Scanner scanner = new Scanner(System.in);
    public static final ContactRepository repository =
            new ContactRepository();

    public static void main(String[] args) {

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
}