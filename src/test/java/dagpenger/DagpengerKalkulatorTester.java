package dagpenger;

import no.nav.dagpenger.DagpengerKalkulator;
import org.junit.jupiter.api.Test;
import no.nav.person.Person;
import static org.junit.jupiter.api.Assertions.*;

public class DagpengerKalkulatorTester {

    @Test
    public void testSkalHaRettigheterTilDagpengerUtifraSisteTreÅrslønner()  {
        Person person = new Person();
        DagpengerKalkulator kalkulator = new DagpengerKalkulator(); 
        person.leggTilÅrslønn(2022, 445000);
        person.leggTilÅrslønn(2024, 465000);
        person.leggTilÅrslønn(2023, 300000);
        assertTrue(kalkulator.harRettigheterTilDagpenger(person));
    }

    @Test
    public void testSkalHaRetigheterTilDagpengerSisteÅrslønn() {
        Person person = new Person();
        DagpengerKalkulator kalkulator = new DagpengerKalkulator(); 
        person.leggTilÅrslønn(2022, 0);
        person.leggTilÅrslønn(2023, 0);
        person.leggTilÅrslønn(2024, 467000);
        assertTrue(kalkulator.harRettigheterTilDagpenger(person));
    }

    @Test
    public void testSkalIkkeHaRettigheterTilDagpengerSisteTreÅrslønner()  {
        Person person = new Person();
        DagpengerKalkulator kalkulator = new DagpengerKalkulator(); 
        person.leggTilÅrslønn(2022, 44000);
        person.leggTilÅrslønn(2024, 52000);
        person.leggTilÅrslønn(2023, 100000);
        assertFalse(kalkulator.harRettigheterTilDagpenger(person));
    }

    @Test
    public void testSkalIkkeHaRettigheterTilDagpengerSisteÅrslønn()  {
        Person person = new Person();
        DagpengerKalkulator kalkulator = new DagpengerKalkulator(); 
        person.leggTilÅrslønn(2022, 0);
        person.leggTilÅrslønn(2024, 130000);
        person.leggTilÅrslønn(2023, 0);
        assertFalse(kalkulator.harRettigheterTilDagpenger(person));
    }

    @Test
    public void testBeregningsMetodeBlirSattTilSisteÅrslønn() {
        Person person = new Person();
        DagpengerKalkulator kalkulator = new DagpengerKalkulator(); 
        person.leggTilÅrslønn(2024, 550000);
        person.leggTilÅrslønn(2022, 110000);
        person.leggTilÅrslønn(2023, 24000);
        assertEquals(DagpengerKalkulator.BeregningsMetode.SISTE_ÅRSLØNN, kalkulator.velgBeregningsMetode(person));
    }

    @Test
    public void testBeregningsMetodeBlirSattTilMaksÅrslønnGrunnbeløp() {
        Person person = new Person();
        DagpengerKalkulator kalkulator = new DagpengerKalkulator(); 
        person.leggTilÅrslønn(2024, 830000);
        person.leggTilÅrslønn(2022, 110000);
        person.leggTilÅrslønn(2023, 24000);
        assertEquals(DagpengerKalkulator.BeregningsMetode.MAKS_ÅRLIG_DAGPENGERGRUNNLAG, kalkulator.velgBeregningsMetode(person));
    }

    @Test
    public void testBeregningsMetodeBlirSattTilGjennomsnittetAvTreÅr() {
        Person person = new Person();
        DagpengerKalkulator kalkulator = new DagpengerKalkulator(); 
        person.leggTilÅrslønn(2024, 330000);
        person.leggTilÅrslønn(2022, 400000);
        person.leggTilÅrslønn(2023, 334000);
        assertEquals(DagpengerKalkulator.BeregningsMetode.GJENNOMSNITTET_AV_TRE_ÅR, kalkulator.velgBeregningsMetode(person));
    }

    @Test
    public void testDagsatsKalkulertUtifraSisteÅrslønn() {
        Person person = new Person();
        DagpengerKalkulator kalkulator = new DagpengerKalkulator(); 
        person.leggTilÅrslønn(2024, 550000);
        person.leggTilÅrslønn(2022, 110000);
        person.leggTilÅrslønn(2023, 24000);
        assertEquals(2116, kalkulator.kalkulerDagsats(person));
    }

    @Test
    public void testDagsatsKalkulertUtifraMaksÅrligGrunnbeløp() {
        Person person = new Person();
        DagpengerKalkulator kalkulator = new DagpengerKalkulator(); 
        person.leggTilÅrslønn(2024, 830000);
        person.leggTilÅrslønn(2023, 24000);
        person.leggTilÅrslønn(2022, 110000);
        assertEquals(2863, kalkulator.kalkulerDagsats(person));
    }

    @Test
    public void testDagsatsKalkulertUtifraTreÅrsGjennomsnitt() {
        Person person = new Person();
        DagpengerKalkulator kalkulator = new DagpengerKalkulator(); 
        person.leggTilÅrslønn(2024, 330000);
        person.leggTilÅrslønn(2023, 334000);
        person.leggTilÅrslønn(2022, 400000);
        assertEquals(1365, kalkulator.kalkulerDagsats(person));
    }

    @Test
    public void testDagsatsKalkulertIkkeRettPåDagpenger() {
        Person person = new Person();
        DagpengerKalkulator kalkulator = new DagpengerKalkulator(); 
        person.leggTilÅrslønn(2024, 80000);
        person.leggTilÅrslønn(2023, 100000);
        person.leggTilÅrslønn(2022, 70000);
        assertEquals(0, kalkulator.kalkulerDagsats(person));
    }
}
