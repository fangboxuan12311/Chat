package freeTalkServerRead;
import java.io.*;
public class ServerRead extends Thread{
	public int clo=0;
	public PrintWriter os;
	public BufferedReader is1;
	public int clientnum;
	public ServerRead(PrintWriter os,BufferedReader is1,int clientnum) {
		this.os=os;
		this.is1=is1;
		this.clientnum=clientnum;
	}
	public void run() {
		try {
			String line;
			do{
				if(clo==1)
					break;
				line=is1.readLine();
				os.println(line);
				os.flush();
				System.out.println(line);
			}while(!line.equals("bye"));
			clo=1;
		}catch(Exception e) {
			System.out.println("Error:"+e);
		}
	}
}