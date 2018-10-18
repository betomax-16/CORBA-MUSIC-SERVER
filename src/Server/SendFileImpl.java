package Server;

import java.nio.file.Paths;

import org.omg.CORBA.ORB;
import SendFileServer.SendFilePOA;

public class SendFileImpl extends SendFilePOA {
	private ORB orb;
	private FileStreamer MP3 = new FileStreamer();
	private String currentDirectory = Paths.get(".").toAbsolutePath().normalize().toString();
	
	  public void setORB(ORB orb_val) {
	    orb = orb_val; 
	  }
	    
	  public byte[] download(String ID) {
		  FileStreamer MP3 = new FileStreamer();
		  System.out.println("Antes de buffer >> "+MP3.getBuffer().length);
		  System.out.println(currentDirectory+"\\cancionesRepo\\"+ID+".mp3");
		  MP3.bufferFile(currentDirectory+"\\cancionesRepo\\"+ID+".mp3");
		  System.out.println("Despues de buffer >> "+MP3.getBuffer().length);
		  return MP3.getBuffer();
	  }
	    
	  // implement shutdown() method
	  public void shutdown() {
	    orb.shutdown(false);
	  }
}
