package no.hvl.dat100.prosjekt;

public class GPSComputer {

	public GPSComputer(GPSData gpsdata) {

		GPSDataConverter converter = new GPSDataConverter(gpsdata);
		converter.convert();

		this.times = converter.times;
		this.latitudes = converter.latitudes;
		this.longitudes = converter.longitudes;
		this.elevations = converter.elevations;
	}

	// tabeller for GPS datapunkter
	public int[] times;
	public double[] latitudes;
	public double[] longitudes;
	public double[] elevations;

	// beregn total distances (i meter)
	public double totalDistance() {

		double distance = 0.0;

		// TODO
		// OPPGAVE - START

		for (int i = 0; i < times.length - 1; i++) {
			distance = distance + (GPSUtils.distance(latitudes[i], longitudes[i], latitudes[i + 1], longitudes[i + 1]));

			// Hint: bruk distance-metoden fra GPSUtils.

			// OPPGAVE - SLUTT
		}
		return distance;
	}

	// beregn totale høydemeter (i meter)
	public double totalElevation() {

		double elevation = 0;

		// TODO
		// OPPGAVE - START
		for (int i = 0; i < elevations.length - 1; i++) {
			if (elevations[i + 1] - elevations[i] >= 0) {
				elevation = elevation + (elevations[i + 1] - elevations[i]);
			}
		}
		// OPPGAVE - SLUTT
		return elevation;
	}

	// beregn total tiden for hele turen (i sekunder)
	public int totalTime() {

		int totaltime = 0;

		// TODO
		// OPPGAVE START
		totaltime = times[times.length - 1] - times[0];

		// OPPGAVE SLUTT

		return totaltime;
	}

	// beregn gjennomsnitshastighets mellom hver av gps punktene
	public double[] speeds() {

		double[] speeds = new double[times.length - 1];

		// TODO
		// OPPGAVE - START
		int secs;
		for (int i = 0; i < times.length - 1; i++) {
			secs = times[i + 1] - times[i];
			speeds[i] = GPSUtils.speed(secs, latitudes[i], longitudes[i], latitudes[i + 1], longitudes[i + 1]);

		}
		// OPPGAVE - SLUTT
		return speeds;
	}

	// beregn maximum hastighet km/t
	public double maxSpeed() {

		double maxspeed = 0;

		// TODO
		// OPPGAVE - START
		double[] speeds = speeds();
		maxspeed = speeds[0];
		for (int i = 1; i < speeds.length; i++) {
			maxspeed = Math.max(speeds[i], maxspeed);
		}

		// OPPGAVE - SLUTT

		return maxspeed;
	}

	// beregn gjennomsnittshasitiget for hele turen km/t
	public double averageSpeed() {

		double average = 0;

		// TODO
		// OPPGAVE - START
		double totalTid = totalTime();
		double totalDistanse = totalDistance();
		average = (totalDistanse / totalTid) * 3.6;

		// OPPGAVE - SLUTT

		return average;
	}

	// conversion factor kph (km/t) to miles per hour (mph)
	public static double MS = 0.62;

	// beregn kcal gitt weight og tid der kjøres med en gitt hastighet
	public double kcal(double weight, int secs, double speed) {

		double kcal = 0;

		// MET: Metabolic equivalent of task angir (kcal x kg-1 x h-1)
		double met = 0;
		double speedmph = speed * MS;

		// TODO
		// OPPGAVE START

		if (speedmph < 10.0) {
			met = 4.0;
			kcal = met * weight * (secs / 3600.0);
		}

		else if (speedmph <= 12.0 && speedmph >= 10.0) {
			met = 6.0;
			kcal = met * weight * (secs / 3600.0);
		}

		else if (speedmph <= 14.0 && speedmph >= 12.0) {
			met = 8.0;
			kcal = met * weight * (secs / 3600.0);
		}

		else if (speedmph <= 15.0 && speedmph >= 14.0) {
			met = 10.0;
			kcal = met * weight * (secs / 3600.0);
		}

		else if (speedmph <= 20.0 && speedmph >= 16.0) {
			met = 12.0;
			kcal = met * weight * (secs / 3600.0);
		}

		else if (speedmph > 20.0) {
			met = 16.0;
			kcal = met * weight * (secs / 3600.0);
		}

		// Energy Expended (kcal) = MET x Body Weight (kg) x Time (h)

		// OPPGAVE SLUTT

		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		// TODO
		// OPPGAVE - START
		double[] speeds = speeds();
		for (int i = 0; i < times.length - 1; i++) {
			totalkcal = totalkcal + kcal(weight, times[i], speeds[i]);
		}
		// Hint: hent hastigheter i speeds tabellen og tider i timestabellen
		// disse er definer i toppen av klassen og lese automatisk inn

		// OPPGAVE - SLUTT

		return totalkcal;
	}

	private static double WEIGHT = 80.0;

	// skriv ut statistikk for turen
	public void print() {

		// TODO
		// OPPGAVE - START
		int tid = totalTime();
		double distance = totalDistance();
		double elevation = totalElevation();
		double maxspeed = maxSpeed();
		double averageSpeed = averageSpeed();
		double totalKcal = totalKcal(WEIGHT);
		System.out.format("Total Time     :    %02d:%02d:%02d%n", (tid / 3600), ((tid % 3600) / 60), (tid % 60));
		System.out.format("Total distance :    %.2f km%n", distance / 1000);
		System.out.format("Total elevation:   %.2f m%n", elevation);
		System.out.format("Max speed      :    %.2f km/t%n", maxspeed);
		System.out.format("Average speed  :    %.2f km/t%n", averageSpeed);
		System.out.format("Energy         :   %.2f kcal%n", (totalKcal / 1000));
		// OPPGAVE - SLUT
	}

	// ekstraoppgaver
	public void climbs() {

	}

	public void maxClimb() {

	}
}
