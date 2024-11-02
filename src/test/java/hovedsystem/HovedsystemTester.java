package hovedsystem;
import org.junit.jupiter.api.Test;
import no.nav.hovedsystem.Hovedsystem;
import no.nav.sak.Sak;
import no.nav.saksbehandler.Saksbehandler.SaksbehandlerSpesialisering;
import static org.junit.jupiter.api.Assertions.*;
public class HovedsystemTester {
    
    @Test
    public void testRegistreringAvPerson() {
        Hovedsystem hovedsystem = new Hovedsystem();
        hovedsystem.registrerPerson("TESTPERSON", "0");
        assertEquals(1, hovedsystem.hentAntallPersoner());
    }

    @Test
    public void testOpprettingAvSak() {
        Hovedsystem hovedsystem = new Hovedsystem();
        hovedsystem.registrerPerson("INNVILGET_MAKS", "0");
        hovedsystem.leggTilÅrslønnForPerson("0", 2022, 900000);
        hovedsystem.leggTilÅrslønnForPerson("0", 2023, 900000);
        hovedsystem.leggTilÅrslønnForPerson("0", 2024, 999999);
        hovedsystem.opprettSak("0");
        assertEquals(1, hovedsystem.hentAntallPersoner());
    }

    @Test
    public void testHentingOgInnsettingAvBehandletSak() {
        Hovedsystem hovedsystem = new Hovedsystem();
        hovedsystem.registrerPerson("INNVILGET_MAKS", "0");
        hovedsystem.leggTilÅrslønnForPerson("0", 2022, 900000);
        hovedsystem.leggTilÅrslønnForPerson("0", 2023, 900000);
        hovedsystem.leggTilÅrslønnForPerson("0", 2024, 999999);
        hovedsystem.opprettSak("0");
        Sak sak = hovedsystem.hentSak(SaksbehandlerSpesialisering.INNVILGET_MAKS);
        sak.avslåSak("Avslag");
        hovedsystem.leggTilBehandletSak(sak);

        assertAll(
            () -> assertEquals(1, hovedsystem.hentAntallBehandledeSaker()),
            () -> assertEquals(0, hovedsystem.hentAntallUbehandledeSaker())
        );
    }
}
