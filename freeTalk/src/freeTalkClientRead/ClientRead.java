package freeTalkClientRead;
import java.io.*;
public class ClientRead extends Thread{
	public int clo=0;
	public BufferedReader is;
	public ClientRead(BufferedReader is) {
		this.is=is;
	}
	public void run() {
		try {
			do{
				if(clo==1)
					break;
				System.out.println(is.readLine());
			}while(!is.readLine().equals("bye"));
			clo=1;
		}catch(Exception e) {
			System.out.println("Error:"+e);
		}
	}
}