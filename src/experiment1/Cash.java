

import java.util.ArrayList;
import java.util.List;

public class Cash {
	
	final int[] money = {50, 20, 5, 5, 1 ,1, 1};
	
	
	
	private List<Boolean> result = new ArrayList<>();
	
	public void payTest(int[] list) {
		for(int i = 0; i < list.length; i++) {
			result.add(pay(list[i]));
			System.out.println(list);
		}
	}
	
	private Boolean pay(int x) {
		for(int i = 0; i < money.length; i++) {
			if (x >= money[i]) {
				x -= money[i];
			}
		}
		return (x != 0)? false:true;
	}
	
	public Boolean[] getResult() {
		Boolean[] results = new Boolean[result.size()] ;
		return result.toArray(results);
	}

}
