package no.hvl.dat102.adt;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat102.exception.EmptyCollectionException;

public abstract class KoeADTTest {
	
	// startkapasitet (sikulær og tabell)
	public static final int STARTKAP = 6;
	
	// referansekø
	private KoeADT<Integer> koe;
	
	// Testdata
	private Integer e0 = 1;
	private Integer e1 = 2;
	private Integer e2 = 3;
	private Integer e3 = 4;
	private Integer e4 = 5;
	
	protected abstract KoeADT<Integer> reset();
	
	@BeforeEach
	public void setup() {
		koe = reset();
	}
	
	@Test
	public void nyStabelErTom() {
		assertTrue(koe.erTom());
	}
	
	@Test
	public void innKoeUtKoe() {
		try {
			koe.innKoe(e0);
			koe.innKoe(e1);
			koe.innKoe(e2);
			
			assertEquals(e0, koe.utKoe());
			assertEquals(e1, koe.utKoe());
			
			koe.innKoe(e3);
			
			assertEquals(e2, koe.utKoe());
			assertEquals(e3, koe.utKoe());
			
			koe.innKoe(e4);
			
			assertEquals(e4, koe.utKoe());
			
		} catch (EmptyCollectionException e) {
			fail();
		}
	}
	
	@Test
	public void innKoeUtKoeDuplikater() {
		koe.innKoe(e0);
		koe.innKoe(e1);
		koe.innKoe(e1);
		koe.innKoe(e2);

		try {
			assertEquals(e0, koe.utKoe());
			assertEquals(e1, koe.utKoe());
			assertEquals(e1, koe.utKoe());
			assertEquals(e2, koe.utKoe());
		} catch (EmptyCollectionException e) {
			fail();
		}
	}
	
	@Test
	public void ikkeTom() {
		koe.innKoe(e1);
		assertTrue(!koe.erTom());
		
		koe.innKoe(e0);
		assertTrue(!koe.erTom());
	}
	
	@Test
	public void innUtErTom() {
		koe.innKoe(e0);
		koe.innKoe(e1);
		
		try {
			koe.utKoe();
			koe.utKoe();
		} catch (EmptyCollectionException e) {
			fail();
		}
		
		assertTrue(koe.erTom());
	}
	
	@Test
	public void stor() {
		koe.innKoe(e0);
		koe.innKoe(e0);
		koe.innKoe(e1);
		
		assertEquals(3, koe.antall());
	}
	
	@Test
	public void utKoeFraTomKoeException() {
		Assertions.assertThrows(EmptyCollectionException.class, () -> {
			koe.utKoe();
		});
	}
	
	@Test
	public void kapasitet() {
		koe.innKoe(e0);
		for (int i=0; i<6; i++) {
			koe.innKoe(e1);
		}
		koe.innKoe(e2);
		
		assertEquals(8, koe.antall());
		
		try {
			assertEquals(e0, koe.utKoe());
		} catch (EmptyCollectionException e) {
			fail();
		}
	}
	
	
}
