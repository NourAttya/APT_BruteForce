package bruteforcegenerator;


import bruteforcegenerator.TestClass;
import mpi.MPIException;
import java.util.Scanner;
/* Main Class */
/* Main Class */
public class Main {
	
	/* Fields need to be public for Threading */
	static public boolean[] found ;
	static public String password = "";
	/*********** Inputs From user ******************/
	/* Current Length starting from minimum length */
         
        static public int count=300;
	static public int currentLength = 0;
	/* Max length of passwords to stop at*/
	static public int maxLength = 0;
	/* Number of threads to search */
	static int numberOfClients = 1;

	public static void main(String[] args) throws InterruptedException 
	{
              Scanner scan = new Scanner(System.in);
            /*inputs from user*/
            boolean done=false;
            while(!done){
             System.out.println("Enter the starting length ");
             currentLength = scan.nextInt();
             System.out.println("Enter the maximum length ");
             maxLength = scan.nextInt();
             System.out.println("Enter the number of clients ");
             numberOfClients = scan.nextInt();
             if(maxLength-currentLength<0){
                 System.out.println("Starting length should be smaller than maximum length! ");
             }
             else if(numberOfClients<=0){
                 System.out.println("Number of clients should be greater than 0 ");
             }
             else{
                 done=true;
             }
            }
             if((maxLength-currentLength+1)<numberOfClients){
                    numberOfClients=maxLength-currentLength+1;
                }
             
             maxLength=maxLength-1;
             currentLength=currentLength-1;
		/* Initialize  the found Array */
		found = new boolean[numberOfClients];
		
		for (int i=0 ; i< numberOfClients; i ++)
		{
			/* initialize it's found boolean by false */
			found[i] = false;
			/* Create new Client Object */
			Client client = new Client(i);
			/* Create thread with client object */
			Thread T = new Thread(client);
			/* Set Thread Name */
			T.setName(String.valueOf(i));
			/* Start the Thread */
			T.start();
			
			
		}
		
	}
}