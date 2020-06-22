package Shrishti_new_package;

import java.util.ArrayList;
import java.util.List;

public class First100PrimeNumbers {
	public static void main(String[] args) {
	
		List<Integer> primeNo = new ArrayList<Integer>();
		primeNo.add(2);
		
		int num = 3;
		while(primeNo.size() < 100) {
			boolean flag = true;
			for(int i=0;i<primeNo.size();i++)
			{
				if((num % primeNo.get(i)) == 0 )
				{
					flag = false;
					break;
				}
				
			}
			
			if(flag) {
				primeNo.add(num);
			}
			num++;
		}
		System.out.println(primeNo);
		
	}
}
