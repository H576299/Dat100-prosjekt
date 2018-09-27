package no.hvl.dat100.prosjekt;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;

public class ShowSpeed extends EasyGraphics {
	
	private static int[] times;
	private static double[] latitudes;
	private static double[] longitudes;
	private static double[] elevations;
		
	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t
	
	private static GPSComputer gpscomputer; 
	
	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		
		GPSData gpsdata = GPSDataReaderWriter.readGPSFile(filename);
		 
		gpscomputer = new GPSComputer(gpsdata);
		
		times = gpscomputer.times;
		latitudes = gpscomputer.latitudes;
		longitudes = gpscomputer.longitudes;
		elevations = gpscomputer.elevations;
	}
	
	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	public void run() {

		int N = times.length-1; // number of data points
		
		makeWindow("Speed profile", 2*MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);
		
		showSpeedProfile(MARGIN + BARHEIGHT,N);
	}
	
	public void showSpeedProfile(int ybase, int N) {
		
		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));
		
		// hent hastigheter på de ulike deler av ruten
		double[] speeds = gpscomputer.speeds();
		
		// TODO:
		int x1, y1, x2, y2;
		int sum=0;
		setColor(0,0,255);
		// OPPGAVE - START	
		for(int i=0;i<speeds.length-1;i++) {
		y1 = ybase;
		y2 = ybase - ((int)speeds[i])*3;
		x1 = i+i*1;
		x2 = i+i*1;
		drawLine(x1, y1, x2, y2);
		sum+=(int)speeds[i];
		while(i==((speeds.length-1)-2)) {
		int gSpeed = sum/(N);
		setColor(0, 255, 0);
		drawLine(0, ybase-gSpeed*3, ((speeds.length-1)-1)+1*((speeds.length-1)-1), ybase-gSpeed*3);
		}
		}
		
		// OPPGAVE - SLUTT
	}
}
