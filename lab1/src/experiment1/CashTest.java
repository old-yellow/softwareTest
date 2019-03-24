import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class CashTest {
	
	Cash cash = new Cash();
	

	@Test
	public void test1() {
		int[] list = {46, 100, 32, -6};
		cash.payTest(list);
		Boolean[] expects = {false, false, true,false};
		assertArrayEquals(expects, cash.getResult()); 
	}
	
	@Test
	public void test2() {
		int[] list = {46, 100};
		cash.payTest(list);
		Boolean[] expects = {false, false};
		assertArrayEquals(expects, cash.getResult()); 
	}
	

}
