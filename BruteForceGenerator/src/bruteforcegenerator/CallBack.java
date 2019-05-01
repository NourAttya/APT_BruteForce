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
/* Just a small Interface for CallBack to be used as implemented in TestClass */
public interface CallBack 
{
	/* CallBackFunction Prototype returns boolean and takes
	 * input as string builder object. 
	 * Warning: NOT MODIFYABLE! 
	 * */
	public boolean CallBackFunction(StringBuilder input);
}
