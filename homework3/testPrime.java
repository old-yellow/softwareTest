package homework3;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class testPrime {
	PrintPrimes primes = new PrintPrimes();
	
	@Test
	public void testPrime(){
		primes.n = 6;
		System.out.println(primes.n);
		Integer[] expects = {2, 3 ,5,7,11,13};
		assertArrayEquals(expects, primes.getResult());
	}

}
