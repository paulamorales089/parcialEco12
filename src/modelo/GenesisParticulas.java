package modelo;

import processing.core.PApplet;

public class GenesisParticulas {
	
	private String grupo;
	private int cantidad;
	
	private int posXE, posYE;
	
	private int r,g,b;
	
	private int movimiento;
	
	

	public GenesisParticulas (String grupo, int cantidad,  int posXE, int posYE, int r, int g, int b) {
		
		this.grupo = grupo;
	    this.cantidad = cantidad;
	    
		this.posXE = posXE;
		this.posYE = posYE;
		
	    this.r = r;
	    this.g = g;
	    this.b = b;
	    
	    
	    this.movimiento= movimiento;
	     
	}
	
	public void arribaParticula()
	{
		posYE -= 1;
	}
	
	public void abajoParticula()
	{
		posYE += 1;
	}
	
	public void derechaParticula()
	{
		posXE += 1;
	}
	
	public void izquierdaParticula()
	{
		posXE -= 1;
	}
	
	
	public void detectorBorde()
	{
		if(posXE >= 600)
		{
			posXE = 589;
		}
		if(posXE <= 0)
		{
			posXE = 1;
		}
		if(posYE >= 600)
		{
			posYE = 589;
		}
		if(posYE < 0)
		{
			posYE = 1;
		}
	}
	
	public void altoAhiParticula(PApplet app) {
		if (app.dist(app.mouseX, app.mouseY, posXE, posYE)<20) {
			posXE = 0;
			posYE = 1;
			
		}
	}



	public String getGrupo() {
		return grupo;
	}


	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}


	public int getCantidad() {
		return cantidad;
	}


	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}


	public int getPosXE() {
		return posXE;
	}


	public void setPosXE(int posXE) {
		this.posXE = posXE;
	}


	public int getPosYE() {
		return posYE;
	}


	public void setPosYE(int posYE) {
		this.posYE = posYE;
	}


	public int getR() {
		return r;
	}


	public void setR(int r) {
		this.r = r;
	}


	public int getG() {
		return g;
	}


	public void setG(int g) {
		this.g = g;
	}


	public int getB() {
		return b;
	}


	public void setB(int b) {
		this.b = b;
	}


	public int getMovimiento() {
		return movimiento;
	}


	public void setMovimiento(int movimiento) {
		this.movimiento = movimiento;
	}
	
	
}

