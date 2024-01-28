import java.util.*;

public class AdressBook {

    Map<Osoba, List<Kontakt>> mapaKontaktow;
    Scanner scanner;

    public AdressBook() {
        this.mapaKontaktow = new HashMap<>();
        this.scanner = new Scanner(System.in);

    }

    public void addKontakt(Osoba osoba, Kontakt kontakt) {
        if (mapaKontaktow.containsKey(osoba)) {
            mapaKontaktow.get(osoba).add(kontakt);
        } else {
            List<Kontakt> nowaListaKontaktow = new ArrayList<>();
            nowaListaKontaktow.add(kontakt);
            mapaKontaktow.put(osoba, nowaListaKontaktow);
        }
    }

    public void addOsobe() {
        System.out.println("Podaj ID osoby:");
        int id = scanner.nextInt();

        System.out.println("Podaj imię:");
        String imie = scanner.next();

        System.out.println("Podaj nazwisko:");
        String nazwisko = scanner.next();

        System.out.println("Podaj email:");
        String email = scanner.next();

        Osoba nowaOsoba = new Osoba(id, imie, nazwisko, email);

        if (!mapaKontaktow.containsKey(nowaOsoba)) {
            mapaKontaktow.put(nowaOsoba, new ArrayList<>());
            System.out.println("Osoba została dodana do mapy bez przypisanego kontaktu.");
        } else {
            System.out.println("Osoba o podanym ID już istnieje w mapie.");
        }
    }

    private Osoba wybierzOsobeZListy() {
        System.out.println("Lista osób:");

        int i = 1;
        for (Osoba osoba : mapaKontaktow.keySet()) {
            System.out.println(i + ". " + osoba.toString());
            i++;
        }

        System.out.println("Wybierz osobę podając numer: ");
        int numerWybranejOsoby = scanner.nextInt();

        List<Osoba> listaOsob = new ArrayList<>(mapaKontaktow.keySet());

        if (numerWybranejOsoby >= 1 && numerWybranejOsoby <= listaOsob.size()) {
            return listaOsob.get(numerWybranejOsoby - 1);
        } else {
            System.out.println("Nieprawidłowy numer osoby.");
            return null;
        }
    }

    public void dodajNowyKontakt() {
        Osoba wybranaOsoba = wybierzOsobeZListy();

        if (wybranaOsoba != null) {
            System.out.println("Podaj numer telefonu:");
            String numerTelefonu = scanner.next();

            System.out.println("Podaj adres:");
            String adres = scanner.next();

            Kontakt nowyKontakt = new Kontakt(numerTelefonu, adres);
            addKontakt(wybranaOsoba, nowyKontakt);

            System.out.println("Nowy kontakt został dodany do osoby.");
        }
    }

    public void edytujInformacjeOsoby() {
        Osoba wybranaOsoba = wybierzOsobeZListy();

        if (wybranaOsoba != null) {
            System.out.println("Wybrana osoba do edycji: " + wybranaOsoba.toString());
            System.out.println("Co chcesz edytowac?");
            System.out.println("1. Imie");
            System.out.println("2. Nazwisko");
            System.out.println("3. Email");

            int wybor = scanner.nextInt();

            switch (wybor) {
                case 1:
                    System.out.println("Podaj nowe imię:");
                    String noweImie = scanner.next();
                    wybranaOsoba.setImie(noweImie);
                    break;
                case 2:
                    System.out.println("Podaj nowe nazwisko:");
                    String noweNazwisko = scanner.next();
                    wybranaOsoba.setNazwisko(noweNazwisko);
                    break;
                case 3:
                    System.out.println("Podaj nowy email:");
                    String nowyEmail = scanner.next();
                    wybranaOsoba.setEmail(nowyEmail);
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór.");
            }

            System.out.println("Informacje zostały zaktualizowane.");
        }
    }

    public void edytujInformacjeKontaktu() {
        Osoba wybranaOsoba = wybierzOsobeZListy();

        if (wybranaOsoba != null) {
            List<Kontakt> listaKontaktowOsoby = mapaKontaktow.get(wybranaOsoba);

            if (listaKontaktowOsoby != null && !listaKontaktowOsoby.isEmpty()) {
                System.out.println("Lista kontaktów osoby " + wybranaOsoba.getImie() + " " + wybranaOsoba.getNazwisko() + ":");
                for (int i = 0; i < listaKontaktowOsoby.size(); i++) {
                    System.out.println((i + 1) + ". " + listaKontaktowOsoby.get(i).toString());
                }

                System.out.println("Wybierz numer kontaktu do edycji:");
                int numerWybranegoKontaktu = scanner.nextInt();

                if (numerWybranegoKontaktu >= 1 && numerWybranegoKontaktu <= listaKontaktowOsoby.size()) {
                    Kontakt wybranyKontakt = listaKontaktowOsoby.get(numerWybranegoKontaktu - 1);

                    System.out.println("Wybrany kontakt do edycji: " + wybranyKontakt.toString());
                    System.out.println("Co chcesz edytować?");
                    System.out.println("1. Numer telefonu");
                    System.out.println("2. Adres");

                    int wybor = scanner.nextInt();

                    switch (wybor) {
                        case 1:
                            System.out.println("Podaj nowy numer telefonu:");
                            String nowyNumerTelefonu = scanner.next();
                            wybranyKontakt.setNumer(nowyNumerTelefonu);
                            break;
                        case 2:
                            System.out.println("Podaj nowy adres:");
                            String nowyAdres = scanner.next();
                            wybranyKontakt.setAdres(nowyAdres);
                            break;
                        default:
                            System.out.println("Nieprawidłowy wybór.");
                    }

                    System.out.println("Informacje kontaktu zostały zaktualizowane.");
                } else {
                    System.out.println("Nieprawidłowy numer kontaktu.");
                }
            } else {
                System.out.println("Osoba nie ma przypisanych kontaktów.");
            }
        }
    }

    public void usunOsobe() {
        Osoba wybranaOsoba = wybierzOsobeZListy();

        if (wybranaOsoba != null) {
            mapaKontaktow.remove(wybranaOsoba);
            System.out.println("Osoba została usunięta z mapy.");
        } else {
            System.out.println("Osoba nie została znaleziona.");
        }
    }

    public void usunKontakt() {
        Osoba wybranaOsoba = wybierzOsobeZListy();

        if (wybranaOsoba != null) {
            List<Kontakt> listaKontaktowOsoby = mapaKontaktow.get(wybranaOsoba);

            if (listaKontaktowOsoby != null && !listaKontaktowOsoby.isEmpty()) {
                System.out.println("Lista kontaktów osoby " + wybranaOsoba.getImie() + " " + wybranaOsoba.getNazwisko() + ":");
                for (int i = 0; i < listaKontaktowOsoby.size(); i++) {
                    System.out.println((i + 1) + ". " + listaKontaktowOsoby.get(i).toString());
                }

                System.out.println("Wybierz numer kontaktu do usunięcia:");
                int numerWybranegoKontaktu = scanner.nextInt();

                if (numerWybranegoKontaktu >= 1 && numerWybranegoKontaktu <= listaKontaktowOsoby.size()) {
                    listaKontaktowOsoby.remove(numerWybranegoKontaktu - 1);
                    System.out.println("Kontakt został usunięty.");
                } else {
                    System.out.println("Nieprawidłowy numer kontaktu.");
                }
            } else {
                System.out.println("Osoba nie ma przypisanych kontaktów.");
            }
        }
    }

}