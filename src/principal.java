import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;



import com.google.gson.Gson;


import modelo.GenesisParticulas;
import processing.core.PApplet;

public class principal extends PApplet {

	public static void main(String[] args) {
		PApplet.main("principal");
	}

	// bolita
	private String nombreGrupo;
	private int x, y;
	private int r, g, b, cantidad;
	float movimietos;
	boolean mostrarNombre, moviendose;
	
	private Socket socket;
	BufferedReader bfr;
    BufferedWriter bfw;

	private ArrayList <GenesisParticulas> arregloDeParticulas;
	GenesisParticulas genesisParticulas;
	

	@Override
	public void settings() {
		size(600, 600);
	}

	@Override
	public void setup() {
		
		mostrarNombre=false;
		arregloDeParticulas = new ArrayList <GenesisParticulas>(); 
		initServer();
		moviendose = true;
		
	}
		
	@Override
	public void draw() {
		background(255);
		
		for (int i = 0; i < arregloDeParticulas.size(); i++) {
			
			nombreGrupo = arregloDeParticulas.get(i).getGrupo();
			cantidad = arregloDeParticulas.get(i).getCantidad();
			
			r= arregloDeParticulas.get(i).getR();
			g= arregloDeParticulas.get(i).getG();
			b= arregloDeParticulas.get(i).getB();
			
			fill (r, g, b);
			ellipse(arregloDeParticulas.get(i).getPosXE(), arregloDeParticulas.get(i).getPosYE(),20,20);
			
			movimientoSexy(); 
			
			}
		}
	
	public void initServer() {
		
		new Thread(		
			() ->	{
						
		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			System.out.println("Starting port on ip " + inetAddress.getHostAddress());
							
			ServerSocket server = new ServerSocket(4000);
			System.out.println("Awaiting connection...");
			socket = server.accept();
			System.out.println("Se conecto con el cliente");
						
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			bfr = new BufferedReader(isr);
							
			OutputStream os = socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			bfw = new BufferedWriter(osw);
							
			while(true)	{
				System.out.println("Awaiting message...");
				String line = bfr.readLine();
				System.out.println("Recibido"+ line);
						
				Gson gson= new Gson();
						
				genesisParticulas = gson.fromJson(line, GenesisParticulas.class);
								
				for (int i = 0; i < genesisParticulas.getCantidad(); i ++) {
							
				nombreGrupo = genesisParticulas.getGrupo();
							
				cantidad = genesisParticulas.getCantidad();
							
				x = genesisParticulas.getPosXE();
				y = genesisParticulas.getPosYE();
						
				r = genesisParticulas.getR();
				g = genesisParticulas.getG();
				b = genesisParticulas.getB();
							
		System.out.println(nombreGrupo + " " + cantidad + " " + x + " " + y + " " + r + " " + g + " " + b );
			
		arregloDeParticulas.add(new GenesisParticulas (nombreGrupo, cantidad, x, y,r,  g,  b));
		
		//recibirParticulas();
				}
			}
							
			} catch (IOException e) {
			// TODO Auto-generated catch block
					e.printStackTrace();
		}
	}
	).start();
}
	
	private void movimientoSexy() {
		
		moviendose=true;
		mostrarNombre=false;	
		
		for (int i = 0; i < arregloDeParticulas.size(); i++) {
		
		movimietos = (int)random(4);
		
		if(movimietos == 0){
			
			arregloDeParticulas.get(i).derechaParticula();	
			arregloDeParticulas.get(i).detectorBorde();
		}
		
		else if(movimietos == 1){
			
			arregloDeParticulas.get(i).izquierdaParticula();
			arregloDeParticulas.get(i).detectorBorde();
			
		}
		
		else if(movimietos == 2){
			
			arregloDeParticulas.get(i).arribaParticula();
			arregloDeParticulas.get(i).detectorBorde();
			
		}
		
		else if(movimietos == 3){
			arregloDeParticulas.get(i).abajoParticula();
			arregloDeParticulas.get(i).detectorBorde();
			
			}
		}
	}
	
	public void mostrarNombre() {
		for (int i = 0; i < arregloDeParticulas.size(); i++) {
			if(dist(mouseX, mouseY, x, y)<20) {
				
			mostrarNombre=true;	
			moviendose=false;
			
			nombreGrupo = arregloDeParticulas.get(i).getGrupo();
			
			fill(0);
			text(nombreGrupo, x,y-20);
						
			}
		}
	}
}