package freeTalkClient;
import java.io.*;
import java.net.*;

import freeTalkClientRead.ClientRead;
public class freeTalkClient2{
	public static void main(String args[]) {
		try {
			Socket socket=new Socket("127.0.0.1",4700);
			BufferedReader sin=new BufferedReader(new InputStreamReader(System.in));
			PrintWriter os=new PrintWriter(socket.getOutputStream());
			BufferedReader is=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String readline;
			ClientRead r=new ClientRead(is);
			r.start();
			do{
				if(r.clo==1)
					break;
				readline=sin.readLine();
				os.println(readline);
				os.flush();
				System.out.println(readline);
			}while(!readline.equals("bye")&&!readline.equals("exit"));
			r.clo=1;
			os.close();
			is.close();
			socket.close();
		}catch(Exception e) {
			System.out.println("Error:"+e);
		}
	}
}
