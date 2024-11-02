package no.nav;
import java.util.Scanner;
import no.nav.hovedsystem.Hovedsystem;
import no.nav.saksbehandler.Saksbehandler;
public class Main {
    public static void main(String[] args) {
        Hovedsystem hovedsystem = new Hovedsystem();
        leggInnISystem(hovedsystem);
        Saksbehandler saksbehandlerM = new Saksbehandler("Alexander Makssats", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET_MAKS, hovedsystem);
        Saksbehandler saksbehandlerI = new Saksbehandler("Alexander Innvilget", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET, hovedsystem);
        Saksbehandler saksbehandlerA = new Saksbehandler("Alexander Avslag", Saksbehandler.SaksbehandlerSpesialisering.AVSLAG, hovedsystem);
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Ubehandlede saker: " + hovedsystem.hentAntallUbehandledeSaker()+"\n"+ "Behandlede saker: " + hovedsystem.hentAntallBehandledeSaker() + "\n");
        saksbehandlerM.hentTilgjengeligSak();
        saksbehandlerM.avgjørSak(sc);
        saksbehandlerI.hentTilgjengeligSak();
        saksbehandlerI.avgjørSak(sc);
        saksbehandlerA.hentTilgjengeligSak();
        saksbehandlerA.avgjørSak(sc);
        saksbehandlerI.hentTilgjengeligSak();
        saksbehandlerM.hentTilgjengeligSak();
        saksbehandlerA.hentTilgjengeligSak();
        saksbehandlerA.avgjørSak(sc);
        sc.close();
        System.out.println("Ubehandlede saker: " + hovedsystem.hentAntallUbehandledeSaker()+"\n"+ "Behandlede saker: " + hovedsystem.hentAntallBehandledeSaker() + "\n");

    }

    private static void leggInnISystem(Hovedsystem hovedsystem) {
        hovedsystem.registrerPerson("MAKS", "0");
        hovedsystem.leggTilÅrslønnForPerson("0", 2022, 900000);
        hovedsystem.leggTilÅrslønnForPerson("0", 2023, 900000);
        hovedsystem.leggTilÅrslønnForPerson("0", 2024, 999999);
        hovedsystem.opprettSak("0");
        hovedsystem.registrerPerson("INNVILGET", "1");
        hovedsystem.leggTilÅrslønnForPerson("1", 2022, 110000);
        hovedsystem.leggTilÅrslønnForPerson("1", 2023, 24000);
        hovedsystem.leggTilÅrslønnForPerson("1", 2024, 550000);
        hovedsystem.opprettSak("1");
        hovedsystem.registrerPerson("AVSLAG", "2");
        hovedsystem.leggTilÅrslønnForPerson("2", 2022, 100);
        hovedsystem.leggTilÅrslønnForPerson("2", 2023, 100);
        hovedsystem.leggTilÅrslønnForPerson("2", 2024, 100);
        hovedsystem.opprettSak("2");
        hovedsystem.registrerPerson("AVSLAG", "3");
        hovedsystem.leggTilÅrslønnForPerson("3", 2022, 100);
        hovedsystem.leggTilÅrslønnForPerson("3", 2023, 100);
        hovedsystem.leggTilÅrslønnForPerson("3", 2024, 100);
        hovedsystem.opprettSak("3");
        hovedsystem.registrerPerson("AVSLAG", "4");
        hovedsystem.leggTilÅrslønnForPerson("4", 2022, 100);
        hovedsystem.leggTilÅrslønnForPerson("4", 2023, 100);
        hovedsystem.leggTilÅrslønnForPerson("4", 2024, 100);
        hovedsystem.opprettSak("4");
    }
}
