/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bruteforcegenerator;

/**
 *
 * @author nour attya
 */
public class BruteForceGenerator 
{
	/* ********************************************************************* */
	/*                             PRIVATE VARIABLES                         */
	/* ********************************************************************* */
	/* Variable of type CallBackInterface to store user's custom callback    */
	private CallBack callBack;
	/* Variable to store generated password in it at each step               */
	private StringBuilder string = new StringBuilder("");
	/* Variable to store the Stack Depth of recursive calling to know the end*/
	private long stackDepthCounter = 0;
	/* Stores the CallBack Function result to stop generating if it's true   */
	private boolean passwordFound = false;
	/* Dictionary Array that has all possible characters to be used          */
	private char dictionarySet[] = 
	{
		'0',		'1',		'2',		'3',		'4',		'5',
		'6',		'7',		'8',		'9',		'a',    	'b',
		'c',		'd',		'e',		'f',		'g',		'h',
		'i',		'j',		'k',		'l',		'm',		'n',
		'o',		'p',		'q',		'r',		's',		't',
		'u',		'v',		'w',		'x',		'y',		'z'
	};
	
	/* ********************************************************************* */
	/*                             PRIVATE FUNCTIONS                         */
	/* ********************************************************************* */
	/* This function is mainly responsible for generating the brute force
	 * password in a recursive way and internally it calls the callBackFunction
	 * that should be passed using the method "setCallBack" and checks if it
	 * returns true, it will stop the recursive calls and end the generating
	 * process, else it will continue until it either be true or hit the end
	 * point where it generates all possible passwords within the given length!   
	 */
	private void crackPassword_Internally(int index, int MaxLength)
	{
		/* If password is found, end this function call */
		if (passwordFound) return;
		/* Incrementing Depth Counter each time this function is called */
		stackDepthCounter++;
		/* For each variable in the given dictionary array, it will call the
		 * same function and concatenates in the same string builder object
		 * so that it can generate all possible set of characters each time
		 * in each column (index) in the password string 
		 */
		for(int i = 0; i < dictionarySet.length && !passwordFound; i++) 
		{
			if (index > string.length() - 1)
			{
				string.append(dictionarySet[i]);
			}
			else
			{
				string.setCharAt(index, dictionarySet[i]);				
			}
			if(index < MaxLength)
			{
				crackPassword_Internally(index + 1, MaxLength);
			}
			
			if (i < dictionarySet.length - 1)
			{
				/* Step Result Here */
				if (!passwordFound)
				{
					System.out.println(string);
					passwordFound = callBack.CallBackFunction(string);					
				}
				//For Debugging
				//System.out.println("Depth = " + StackDepthCounter);
			}
		}
		stackDepthCounter--;
		if (stackDepthCounter == 0)
		{
			/* Last Step Result Here */
			if (!passwordFound)
			{
				string.setCharAt(index, dictionarySet[dictionarySet.length-1]);
				System.out.println(string);
				passwordFound = callBack.CallBackFunction(string);				
			}
		}
	}
	/* ********************************************************************* */
	/*                             PUBLIC FUNCTIONS                          */
	/* ********************************************************************* */
	/* Function to set (like  pointer) to the class that has the CallBackFunc*/
	public void setCallBack(CallBack classThatImplementsCallBackInterfaceObject)
	{
		callBack = classThatImplementsCallBackInterfaceObject;
	}
	
	/* This function should be used by End user to start generating his brute
	 * force password using the exact given password length in the parameters
	 * for example if PasswordLength = 4,
	 * it will start from 0000,0001,0002,.... etc until ZZZZ
	 */
	public void crackPassword(int PasswordLength) 
	{
		/* Resetting Module States' Variable */
		passwordFound = false;
		stackDepthCounter = 0;
		/* Calling the Main Cracking Function */
		crackPassword_Internally(0, PasswordLength);
	}

}
