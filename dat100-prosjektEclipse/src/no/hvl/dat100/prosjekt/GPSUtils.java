package no.hvl.dat100.prosjekt;

import static java.lang.Math.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class GPSUtils {

	public GPSUtils() {

	}

	// konverter sekunder til string på formen hh:mm:ss
	public static String printTime(int secs) {

		// TODO
		// OPPGAVE - START

		String timestr = "";
		String TIMESEP = ":";

		int hr = secs / 3600;
		int mm = (secs % 3600) / 60;
		int ss = secs % 60;

		timestr = String.format("%02d" + TIMESEP + "%02d" + TIMESEP + "%02d", hr, mm, ss);

		// OPPGAVE - SLUTT

		return timestr;
	}

	// beregn maximum av en tabell av doubles med minnst et element
	public static double findMax(double[] da) {

		double max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	// beregn minimum av en tabell av doubles med minnst et element
	public static double findMin(double[] da) {

		double min = da[0];

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;
	}

	private static int R = 6371000; // jordens radius

	// Beregn avstand mellom to gps punkter ved bruk av Haversine formlen
	public static double distance(double latitude1, double longitude1, double latitude2, double longitude2) {

		// TODO:

		// OPPGAVE - START

		double d;
		double dLat = toRadians((latitude2 - latitude1));
		double dLong = toRadians((longitude2 - longitude1));

		latitude1 = toRadians(latitude1);
		latitude2 = toRadians(latitude2);

		double a = pow(sin(dLat / 2), 2)
				+ cos(latitude1) * cos(latitude2) * pow(sin(dLong / 2), 2);
		double c = 2 * atan2(sqrt(a), sqrt(1 - a));

		d = R * c;

		return d;

	}

	// OPPGAVE - SLUTT

	// beregn gjennomsnits hastighet i km/t mellom to gps punkter
	public static double speed(int secs, double latitude1, double longitude1, double latitude2, double longitude2) {

		// distanse delt p� tid og ganget med 3.6 for km/h

		// TODO:
		// OPPGAVE - START

		double speed = (distance(latitude1, longitude1, latitude2, longitude2) / secs) * 3.6;

		return speed;

		// OPPGAVE - SLUTT

	}

	private static int TEXTWIDTH = 10;

	// konverter double til string med 2 decimaler og streng lengde 10
	// eks. 1.346 konverteres til " 1.35" og enhet til slutt
	// Hint: se på String.format metoden

	public static String printDouble(double d) {

		String str = "";

		// TODO
		// OPPGAVE - START;
		DecimalFormat dot = new DecimalFormat("0.00");
		String result = dot.format(d);
		str = "      " + result.replace(",", ".");

		// OPPGAVE - SLUTT

		return str;
	}
}
