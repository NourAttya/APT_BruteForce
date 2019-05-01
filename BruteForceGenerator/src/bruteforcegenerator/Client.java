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
import java.net.*;
import java.io.*;

public class Client implements CallBack, Runnable{

	int myID;
	boolean run = true;
	
	Socket socket;
    PrintWriter out;
    BufferedReader in;
    
	
	public Client(int id)
	{
		myID = id;
	}
	
	public boolean CallBackFunction(StringBuilder input)
	{
            if(Main.count<300){
                Main.count=Main.count-1;
            }
            else{
            try{Thread.sleep(5);}catch(InterruptedException e){System.out.println(e);}
            Main.count=100;}
	            try{
                socket =new Socket("localhost",6666);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
                
                out.println(input);
                String receviedMsg = in.readLine();
                 
                


                if (receviedMsg.equals("SUCCESS"))
                {
                      System.out.println("Password found: " + input.toString());
						/* Get Password to be send to server */
						Main.password = input.toString();
						Main.found[myID] = true;
                }
                else
                {
                       Main.found[myID] = false;
                }
                 return Main.found[myID];
            }catch(Exception e){
            System.out.println(e);
            return true; //// to terminate the function in case of an exception
        }  
	}
	
	public void run() {
		
		while (true == run && Main.currentLength <= Main.maxLength)
		{
			/* Search if any thread found the password */ 
			for(int i=0; i< Main.found.length; i++ )
			{
				if( true == Main.found[i])
				{
					/* if yes stop running  and break from for loop*/
					run = false;
					break; 
				}
			}	
			/* then break from while loop */
			if ( false == run)
			{
				break;
			}
			/* Create Generator Object */
			BruteForceGenerator myGenerator = new BruteForceGenerator();
			/* Set "Client" as call back */
			myGenerator.setCallBack(this);
			/* Generate Password at current length */
			myGenerator.crackPassword(Main.currentLength++);
		}
	}
}