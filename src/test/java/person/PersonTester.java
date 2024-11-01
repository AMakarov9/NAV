package person;
import org.junit.jupiter.api.Test;
import no.nav.person.Person;
import static org.junit.jupiter.api.Assertions.*;

public class PersonTester {

    @Test 
    public void testOppdateringAvÅrslønn() {
        Person person = new Person(); 
        person.leggTilÅrslønn(2021, 100000);
        person.leggTilÅrslønn(2021, 100001);
        assertEquals(100001, person.hentNyesteÅrslønn());
    }

    @Test 
    public void testOppdateringAvNyesteÅr() {
        Person person = new Person(); 
        person.leggTilÅrslønn(2021, 100000);
        person.leggTilÅrslønn(2022, 100000);
        person.leggTilÅrslønn(2023, 100000);
        person.leggTilÅrslønn(2024, 999999);
        assertEquals(999999, person.hentNyesteÅrslønn());
    }

    @Test 
    public void testSumAvLønninger() {
        Person person = new Person(); 
        person.leggTilÅrslønn(2021, 100000);
        person.leggTilÅrslønn(2022, 100000);
        person.leggTilÅrslønn(2023, 100000);
        assertEquals(300000, person.hentSumAvLønninger());
    }

    @Test
    public void testHentGjennomsnittAvLønn() {
        double tall = 75000; 
        Person person = new Person(); 
        person.leggTilÅrslønn(2021, tall);
        person.leggTilÅrslønn(2023, tall);
        person.leggTilÅrslønn(2022, tall);
        assertEquals(tall, person.hentGjennomSnittAvLønninger());
    }

    @Test 
    public void testSumAvLønningerMedFlereKalenderår() {
        Person person = new Person(); 
        person.leggTilÅrslønn(2021, 100000);
        person.leggTilÅrslønn(2022, 100000);
        person.leggTilÅrslønn(2023, 100000);
        person.leggTilÅrslønn(2024, 150000);
        assertEquals(350000, person.hentSumAvLønninger());
    }

    @Test
    public void testHentGjennomsnittAvLønnMedFlereKalenderår() {
        double tall = 75000; 
        Person person = new Person(); 
        person.leggTilÅrslønn(2021, tall);
        person.leggTilÅrslønn(2022, tall);
        person.leggTilÅrslønn(2023, tall);
        person.leggTilÅrslønn(2024, 150000);
        assertEquals(100000, person.hentGjennomSnittAvLønninger());
    }
}
