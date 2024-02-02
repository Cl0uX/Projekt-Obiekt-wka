import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AdressBook adressBook = new AdressBook();

        Osoba osoba = new Osoba(1,"Janusz","Bąk","januszbak123@gmail.com");
        Kontakt kontakt = new Kontakt("123456749","Złota 123");
        adressBook.addKontakt(osoba,kontakt);

        Scanner scanner = new Scanner(System.in);
        boolean czyWylaczyc = true;
        while (czyWylaczyc) {
            System.out.println("1. Dodaj nowa osobe");
            System.out.println("2. Dodaj kontakt do istniejacej osoby");
            System.out.println("3. Edytuj informacje o kontakcie");
            System.out.println("4. Edytuj informacje o osobie");
            System.out.println("5. Usun kontakt");
            System.out.println("6. Usun osobe");
            System.out.println("7. Wylacz program");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    adressBook.addOsobe();
                    break;
                case 2:
                    adressBook.dodajNowyKontakt();
                    break;
                case 3:
                    adressBook.edytujInformacjeKontaktu();
                    break;
                case 4:
                    adressBook.edytujInformacjeOsoby();
                    break;
                case 5:
                    adressBook.usunKontakt();
                    break;
                case 6:
                    adressBook.usunOsobe();
                    break;
                case 7:
                    czyWylaczyc = false;
                    System.out.println("Program został wyłączony.");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");
                    break;
            }
        }
    }
}
