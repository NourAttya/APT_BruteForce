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

public class Server {
    private static String correctPassword = "aaaaa";
    public static void main(String[] args) throws Exception{ 
        ServerSocket listener = new ServerSocket(6666); 
        try{
            Thread t1 = new Thread(new CheckPassword(listener));
            t1.start();
            
        }catch(Exception e){
            System.out.println(e);
        }
}
    private static class CheckPassword implements Runnable {
        
        private Socket socket;
        private ServerSocket listener;
        
        public CheckPassword(ServerSocket listener) {
            try{
                this.listener = listener;
                
            }catch(Exception e){
                System.out.println(e);
            }
        }
        
        @Override
        public void run() {
            while(true){
                try{
                    socket = listener.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                    
                    String receivedMsg = in.readLine();
                    /*for debugging
                    System.out.println("Generated: "+receivedMsg+" Correct: "+correctPassword);
                    System.out.println("Is equal? "+receivedMsg.equals(correctPassword));
                    System.out.println("Generated length: "+receivedMsg.length()+" Correct length: "+correctPassword.length());
                    */
                    if(receivedMsg.equals(correctPassword)){
                        System.out.println("success, the correct password is "+correctPassword);
                        out.println("SUCCESS");
                        out.close();
                        in.close();
                        socket.close();
                        break;
                        
                    }  
                    else
                        out.println("FAIL");
                    
                }catch(Exception e){
                    System.out.println(e);
                }
                
            }
        }
    } 
}
