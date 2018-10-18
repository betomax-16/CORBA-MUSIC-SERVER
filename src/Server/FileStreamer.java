package Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileStreamer 
{
	private File file;
	private FileInputStream fis;
	private FileOutputStream fos;
	private int noOfBytes;
	private byte[] buffer;
	
	public FileStreamer()
	{
		this.file = null;
		this.fis = null;
		this.fos = null;
		this.buffer = new byte[1024];
		this.noOfBytes = 0;
	}
  public void bufferFile(String sourceFile) 
  {
	  try{
		  //Ingresar ruta absoluta de archivo.
		  this.file = new File(sourceFile);
		  //Preparamos archivo para ser buffereado
		  this.fis = new FileInputStream(file);
		  //Obtenemos la informacion de buffer... aun en memoria
		  ByteArrayOutputStream baos = new ByteArrayOutputStream();
		  while ((this.noOfBytes = this.fis.read(this.buffer, 0, this.buffer.length)) != -1) {
			 baos.write(this.buffer, 0, this.noOfBytes);
		  }
		  baos.flush();
		  //Guardamos buffer
		  this.buffer = baos.toByteArray();  
	  }catch (FileNotFoundException e) 
	  {
		  System.out.println("Archivo no encontrado! >> buffer");
	  }catch (IOException ioe)
	  {
		  System.out.println("Error al cargar archivo >> buffer");
	  }
	  finally {
			try {
				if (this.fis != null) {
					this.fis.close();
				}
			}
			catch (IOException ioe) {
				System.out.println("Error al guardar archivo MP3 >> buffer");
			}
		} 
	  System.out.println("Archivo MP3 correctamente en buffer >> "+this.buffer.length);
  }
  
  public void saveFile(String destinyFile)
  {
	  try{
		  this.fos = new FileOutputStream(destinyFile);
		  this.fos.write(this.buffer);
		  this.fos.flush();
	  }catch (FileNotFoundException e) {
		  System.out.println("Archivo no encontrado! >> guardar");
	  }catch (IOException ioe) {
			System.out.println("Error al cargar archivo MP3 >> guardar");
		}
		finally {
			try {
				if (this.fos != null) {
					this.fos.close();
				}
			}
			catch (IOException ioe) {
				System.out.println("Error al guardar archivo MP3 >> guardar");
			}
		} 
		System.out.println("Archivo MP3 copiado correctamente >> "+this.buffer.length);

  }
  
  public void setBuffer(byte[] buffer)
  {
	  this.buffer = buffer;
  }
  
  public byte[] getBuffer()
  {
	  return this.buffer;
  }
}
