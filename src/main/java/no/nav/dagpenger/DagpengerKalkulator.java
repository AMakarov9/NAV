package no.nav.dagpenger;

import no.nav.grunnbeløp.GrunnbeløpVerktøy;
import no.nav.person.Person; 

/**
 * Kalkulator for å beregne hvor mye dagpenger en person har rett på i Norge basert på dagens grunnbeløp (1G).
 * For at en person skal ha rett på dagpenger, må en av de to følgene kravene være møtt:
 *      De siste 3 årene må gjennomsnitslønnen være høyere enn 3G.
 *      Tjent mer det siste året enn 1.5G.
 * Hvis en person har rett på dagpenger, må følgende ting vurderes for å kalkulere dagsatsen:
 *      Hva er størst av gjennomsnittlig årslønn de 3 siste årene og siste årslønn.
 *      Hvis siste årslønn er størst, er årslønnen høyere enn 6G.
 * Antall årlige arbeidsdager i Norge er satt til å være 260, så ved beregning av dagsats må 260 dager
 * brukes og ikke 365.
 *
 * @author Emil Elton Nilsen 
 * @author Alexander Gran Makarov
 * @version 2.0
 */
public class DagpengerKalkulator {

    private final GrunnbeløpVerktøy grunnbeløpVerktøy;
    public DagpengerKalkulator() {
        this.grunnbeløpVerktøy = new GrunnbeløpVerktøy();
    }

    public enum BeregningsMetode {
        SISTE_ÅRSLØNN, 
        GJENNOMSNITTET_AV_TRE_ÅR, 
        MAKS_ÅRLIG_DAGPENGERGRUNNLAG
    }

    /**
     * Hvis en person har rett på dagpenger, vil den kalkulere dagsatsen en person har rett på.
     * Hvis ikke en person har rett på dagpenger, vil metoden returnere 0kr som dagsats, som en antagelse på at det
     * er det samme som å ikke ha rett på dagpenger.
     * @param person man skal beregne for blir gitt som argument
     * @return dagsatsen en person har rett på.
     */
    public double kalkulerDagsats(Person person) {
        double dagsats = 0;

        int arbeidsdagerIÅret = 260;
        if(harRettigheterTilDagpenger(person)) {
            BeregningsMetode beregningsMetode = velgBeregningsMetode(person); 
            switch (beregningsMetode) {
                case SISTE_ÅRSLØNN: 
                    dagsats = Math.ceil(person.hentNyesteÅrslønn() / arbeidsdagerIÅret); 
                    break; 
                
                case GJENNOMSNITTET_AV_TRE_ÅR: 
                    dagsats = Math.ceil(person.hentGjennomSnittAvLønninger() / arbeidsdagerIÅret);   
                    break; 
                    
                case MAKS_ÅRLIG_DAGPENGERGRUNNLAG: 
                    dagsats = Math.ceil(grunnbeløpVerktøy.hentMaksÅrligDagpengegrunnlag() / arbeidsdagerIÅret); 
                    break;  
            }
        }

        return dagsats;
    }

    /**
     * Henter maks dagssats en person kan få.
     * @return maks dagssats en person kan få.
     */
    public double hentMaksDagssats() {
        return Math.ceil(grunnbeløpVerktøy.hentMaksÅrligDagpengegrunnlag() / 260);
    }

    /**
     * Sjekker om en person har rettighet til dagpenger eller ikke.
     * @param person man skal beregne for blir gitt som argument
     * @return om personen har rett på dagpenger.
     */
    public boolean harRettigheterTilDagpenger(Person person) {
        boolean harRettigheter = false;

        if(person.hentSumAvLønninger() >= grunnbeløpVerktøy.hentTotaltGrunnbeløpForGittAntallÅr(3)) {
            harRettigheter = true; 
        } else if (person.hentNyesteÅrslønn() >= grunnbeløpVerktøy.hentMinimumÅrslønnForRettPåDagpenger()) {
            harRettigheter = true; 
        } 
        return harRettigheter;
    }

    /**
     * Velger hva som skal være beregnings metode for dagsats ut ifra en person sine årslønner.
     * @param person man skal beregne for blir gitt som argument
     * @return beregningsmetode for dagsats.
     */
    public BeregningsMetode velgBeregningsMetode(Person person) {
        BeregningsMetode beregningsMetode;

    
        double sistÅrslønn = person.hentNyesteÅrslønn(); 

        if (sistÅrslønn > person.hentSumAvLønninger() / 3) {
           beregningsMetode = BeregningsMetode.SISTE_ÅRSLØNN;
           if (sistÅrslønn > grunnbeløpVerktøy.hentMaksÅrligDagpengegrunnlag()) {
               beregningsMetode = BeregningsMetode.MAKS_ÅRLIG_DAGPENGERGRUNNLAG;
           }
        } else {
            beregningsMetode = BeregningsMetode.GJENNOMSNITTET_AV_TRE_ÅR;
        }

        return beregningsMetode;
    }
}
