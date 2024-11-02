package saksbehandler;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;


import no.nav.hovedsystem.Hovedsystem;
import no.nav.sak.Sak;
import no.nav.saksbehandler.Saksbehandler;

public class SaksbehandlerTester {
    @Test
    public void testSaksbehandlereHenterIngenSaker() {
        Hovedsystem hovedsystem = new Hovedsystem();

        Saksbehandler saksbehandler = new Saksbehandler("Mr. kaffepause", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET_MAKS,hovedsystem);
        Saksbehandler saksbehandler2 = new Saksbehandler("Mr. kaffepause", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET, hovedsystem);
        Saksbehandler saksbehandler3 = new Saksbehandler("Mr. kaffepause", Saksbehandler.SaksbehandlerSpesialisering.AVSLAG, hovedsystem);

        Sak sak1 = saksbehandler.hentTilgjengeligSak();
        Sak sak2 = saksbehandler2.hentTilgjengeligSak();
        Sak sak3 = saksbehandler3.hentTilgjengeligSak();

        assertAll(
                () -> assertEquals(null, sak1),
                () -> assertEquals(null, sak2),
                () -> assertEquals(null, sak3),
                () -> assertEquals(0, saksbehandler.hentAntallHentedeSaker()),
                () -> assertEquals(0, saksbehandler2.hentAntallHentedeSaker()),
                () -> assertEquals(0, saksbehandler3.hentAntallHentedeSaker())
            );
    }

    public void testBareSaksbehandlerForAvslagHenterSak() {
        Hovedsystem hovedsystem = new Hovedsystem();
        hovedsystem.registrerPerson("AVSLAG", "0");
        hovedsystem.leggTilÅrslønnForPerson("0", 2022, 100);
        hovedsystem.leggTilÅrslønnForPerson("0", 2023, 100);
        hovedsystem.leggTilÅrslønnForPerson("0", 2024, 100);
        hovedsystem.opprettSak("0");
        Saksbehandler saksbehandler = new Saksbehandler("Mr. kaffepause", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET_MAKS, hovedsystem);
        Saksbehandler saksbehandler2 = new Saksbehandler("Mr. kaffepause", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET, hovedsystem);
        Saksbehandler saksbehandler3 = new Saksbehandler("Skal jobbe", Saksbehandler.SaksbehandlerSpesialisering.AVSLAG, hovedsystem);
    
        Sak sak1 = saksbehandler.hentTilgjengeligSak();
        Sak sak2 = saksbehandler2.hentTilgjengeligSak();
        saksbehandler3.hentTilgjengeligSak();
        assertAll(
            () -> assertEquals(null, sak1),
            () -> assertEquals(null, sak2),
            () -> assertEquals(0, saksbehandler.hentAntallHentedeSaker()),
            () -> assertEquals(0, saksbehandler2.hentAntallHentedeSaker()),
            () -> assertEquals(1, saksbehandler3.hentAntallHentedeSaker())
        );
    }
    
    @Test
    public void testBareSaksbehandlerForInnvilgetHenterSak() {
        Hovedsystem hovedsystem = new Hovedsystem();
        hovedsystem.registrerPerson("INNVILGET", "0");
        hovedsystem.leggTilÅrslønnForPerson("0", 2022, 110000);
        hovedsystem.leggTilÅrslønnForPerson("0", 2023, 24000);
        hovedsystem.leggTilÅrslønnForPerson("0", 2024, 550000);
        hovedsystem.opprettSak("0");
        Saksbehandler saksbehandler = new Saksbehandler("Mr. kaffepause", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET_MAKS, hovedsystem);
        Saksbehandler saksbehandler2 = new Saksbehandler("Mr. kaffepause", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET, hovedsystem);
        Saksbehandler saksbehandler3 = new Saksbehandler("Skal jobbe", Saksbehandler.SaksbehandlerSpesialisering.AVSLAG, hovedsystem);
    
        Sak sak1 = saksbehandler.hentTilgjengeligSak();
        saksbehandler2.hentTilgjengeligSak();
        Sak sak3 = saksbehandler3.hentTilgjengeligSak();
    
        assertAll(
            () -> assertEquals(null, sak1),
            () -> assertEquals(null, sak3),
            () -> assertEquals(0, saksbehandler.hentAntallHentedeSaker()),
            () -> assertEquals(1, saksbehandler2.hentAntallHentedeSaker()),
            () -> assertEquals(0, saksbehandler3.hentAntallHentedeSaker())
        );
    }
    
    @Test
    public void testBareSaksbehandlerForMakssatsHenterSak() {
        Hovedsystem hovedsystem = new Hovedsystem();
        hovedsystem.registrerPerson("INNVILGET_MAKS", "0");
        hovedsystem.leggTilÅrslønnForPerson("0", 2022, 9999999);
        hovedsystem.leggTilÅrslønnForPerson("0", 2023, 9999999);
        hovedsystem.leggTilÅrslønnForPerson("0", 2024, 9999999);
        hovedsystem.opprettSak("0");
        Saksbehandler saksbehandler = new Saksbehandler("Skal jobbe", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET_MAKS, hovedsystem);
        Saksbehandler saksbehandler2 = new Saksbehandler("Mr. kaffepause", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET, hovedsystem);
        Saksbehandler saksbehandler3 = new Saksbehandler("Mr. kaffepause", Saksbehandler.SaksbehandlerSpesialisering.AVSLAG, hovedsystem);
    
        saksbehandler.hentTilgjengeligSak();   
        Sak sak2 = saksbehandler2.hentTilgjengeligSak();
        Sak sak3 = saksbehandler3.hentTilgjengeligSak();
    
        assertAll(
            () -> assertEquals(null, sak2),
            () -> assertEquals(null, sak3),
            () -> assertEquals(1, saksbehandler.hentAntallHentedeSaker()),
            () -> assertEquals(0, saksbehandler2.hentAntallHentedeSaker()),
            () -> assertEquals(0, saksbehandler3.hentAntallHentedeSaker())
        );
    }
    
    @Test
    public void testSaksbehandlerHenterOgGodkjennerSak() {
        String bekreftelse = "j";
        System.setIn(new ByteArrayInputStream(bekreftelse.getBytes()));
        Hovedsystem hovedsystem = new Hovedsystem();
        hovedsystem.registrerPerson("INNVILGET_MAKS", "0");
        hovedsystem.leggTilÅrslønnForPerson("0", 2022, 9999999);
        hovedsystem.leggTilÅrslønnForPerson("0", 2023, 9999999);
        hovedsystem.leggTilÅrslønnForPerson("0", 2024, 9999999);
        hovedsystem.opprettSak("0");
        Saksbehandler saksbehandler = new Saksbehandler("Skal jobbe", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET_MAKS, hovedsystem);
        saksbehandler.hentTilgjengeligSak();
        Scanner sc = new Scanner(System.in);
        Sak sak = saksbehandler.avgjørSak(sc);
        assertEquals(Sak.Status.GODKJENT, sak.hentStatus());
    } 
    
    @Test
    public void testSaksbehandlerHenterOgAvslårSak() {
        String bekreftelse = "n";
        System.setIn(new ByteArrayInputStream(bekreftelse.getBytes()));
        Hovedsystem hovedsystem = new Hovedsystem();
        hovedsystem.registrerPerson("INNVILGET_MAKS", "0");
        hovedsystem.leggTilÅrslønnForPerson("0", 2022, 9999999);
        hovedsystem.leggTilÅrslønnForPerson("0", 2023, 9999999);
        hovedsystem.leggTilÅrslønnForPerson("0", 2024, 9999999);
        hovedsystem.opprettSak("0");
        Saksbehandler saksbehandler = new Saksbehandler("Skal jobbe", Saksbehandler.SaksbehandlerSpesialisering.INNVILGET_MAKS, hovedsystem);
        saksbehandler.hentTilgjengeligSak();
        Scanner sc = new Scanner(System.in);
        Sak sak = saksbehandler.avgjørSak(sc);
        assertEquals(Sak.Status.AVSLÅTT, sak.hentStatus());
    }
}

