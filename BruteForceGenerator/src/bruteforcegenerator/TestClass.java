package bruteforcegenerator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nour attya
 */
/* This test class is an example for any class that will use the BruteForceGenerator Class,
 * Since, it should implement "CallBack interface" and its "CallBackFunction" as below
 * to be passed to BruteForceGenerator Object as in the Main Class 
 * */
public class TestClass implements CallBack {
	
	/* Your logic function that will send generated password string "input" to server
	 * and try to crack its password, and returns true if it's correct and false if not.
	 */
	public boolean CallBackFunction(StringBuilder input)
	{
		if (input.toString().equals("1111"))
		{
			System.out.println("Found!! 1111");
			return true;
		}
		else
		{
			return false;
		}
	}
/* Rest of Your Functions as any normal class */
}

