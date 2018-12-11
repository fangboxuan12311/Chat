package freeMultiTalkServer;
import java.io.*;
import java.net.*;
import java.util.*;
import freeTalkServerThread.ServerThread;
public class freeMultiTalkServer {
	public static Socket socket[]=new Socket[2];
	public static int clientnum=0;
	public static void main(String args[]) {
		ServerSocket serverSocket=null;
		boolean listening=true;
		try {
			serverSocket=new ServerSocket(4700);
		}catch(IOException e) {
			System.out.println("Could not listen on port:4700.");
			System.exit(-1);
		}
		while(listening) {
			try {
				socket[clientnum]=serverSocket.accept();
				new ServerThread(socket[clientnum],clientnum).start();
			} catch (IOException e) {
				System.out.println("Error:"+e);
			}
			clientnum++;
		}
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.out.println("Error:"+e);
		}
	}
}
