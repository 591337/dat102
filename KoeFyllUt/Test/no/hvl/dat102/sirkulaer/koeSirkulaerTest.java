package no.hvl.dat102.sirkulaer;

import no.hvl.dat102.adt.KoeADT;
import no.hvl.dat102.adt.KoeADTTest;

public class koeSirkulaerTest extends KoeADTTest {

	@Override
	protected KoeADT<Integer> reset() {
		return new SirkulaerKoe<Integer>(STARTKAP);
	}

}
