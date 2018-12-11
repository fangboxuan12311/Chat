package freeTalkServerThread;
import java.io.*;
import java.net.*;

import freeMultiTalkServer.freeMultiTalkServer;
import freeTalkServerRead.ServerRead;
public class ServerThread extends Thread{
	public int clientnum;
	BufferedReader is1;
	PrintWriter os1;
	BufferedReader is;
	PrintWriter os;
	public ServerThread(Socket socket,int num) {
		clientnum=num;
	}
	public void run() {
		try {
			String line;
			is=new BufferedReader(new InputStreamReader(freeMultiTalkServer.socket[clientnum].getInputStream()));
			os=new PrintWriter(freeMultiTalkServer.socket[clientnum].getOutputStream());
			
			do {
				line=is.readLine();
				System.out.println(line);
			}while(!line.equals("start"));
			
			
			if(clientnum==0) {
			is1=new BufferedReader(new InputStreamReader(freeMultiTalkServer.socket[clientnum+1].getInputStream()));
			os1=new PrintWriter(freeMultiTalkServer.socket[clientnum+1].getOutputStream());
			}else {
			is1=new BufferedReader(new InputStreamReader(freeMultiTalkServer.socket[clientnum-1].getInputStream()));
			os1=new PrintWriter(freeMultiTalkServer.socket[clientnum-1].getOutputStream());
			}
			
			ServerRead r=new ServerRead(os,is1,clientnum);
			r.start();
			do {
				if(r.clo==1)
					break;
				line=is.readLine();
				os1.println(line);
				os1.flush();
				System.out.println(line);
			}while(!line.equals("bye"));
			r.clo=1;	
			os1.close();
			is1.close();
			os.close();
			is.close();
			
		}catch(Exception e) {
			System.out.println("Error:"+e);
		}
	}
}