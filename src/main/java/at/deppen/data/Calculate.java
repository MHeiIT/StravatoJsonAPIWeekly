package at.deppen.data;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import at.deppn.types.AthleteActivities;

public class Calculate {
	final double ridePerKm = 2.1426273;
	final double runPerKm = 9.15274322;
	final double swimPerKm = 100;
	final double elevationPerM = 0.125;
	final double lukiFactor = 25;

	public double getPoints(AthleteActivities aa) {
		aa.getType();
		switch (aa.getType()) {
		case "Ride":
			return calculateRide(aa.getDistance(), aa.getElev_high(), aa.getMoving_time());
			
		case "Workout":
			return calculateRide(aa.getDistance(), aa.getElev_high(), aa.getMoving_time());
			
		case "VirtualRide":
			return calculateRide(aa.getDistance(), aa.getElev_high(), aa.getMoving_time());

		case "Run":
			return calculateRun(aa.getDistance(), aa.getTotal_elevation_gain());

		case "Swim":
			return calculateSwim(aa.getDistance());
			
		case "Walk":
			return calulateElevation(aa.getTotal_elevation_gain());
			
		case "Hike":
			return calulateElevation(aa.getTotal_elevation_gain());
			
		default:
			System.out.println(aa.getType());
			return 0;

		}
	}

	private double calculateRide(double distance, double elev_high, double moving_time) {
		if (distance == 0) {
			double minute = moving_time/60;
			double hour = minute/60;
			double distancepoints = hour *lukiFactor*ridePerKm;
			return distancepoints;
		} else {
			double distanceinkm = (distance / 1000);
			double distancepoints = distanceinkm * ridePerKm;
			double elevationpoints = calulateElevation(elev_high);
			return distancepoints + elevationpoints;
		}
	}

	private double calculateRun(double distance, double elev_high) {
		double distanceinkm = (distance / 1000);
		double distancepoints = distanceinkm * runPerKm;
		double elevationpoints = calulateElevation(elev_high);
		return distancepoints + elevationpoints;
	}

	private double calculateSwim(double distance) {
		double distanceinkm = (distance / 1000);
		double distancepoints = distanceinkm * swimPerKm;
		return distancepoints;
	}

	private double calulateElevation(double elev_high) {
		return elev_high * elevationPerM;
	}
	

	public long getAfter() {
		ZonedDateTime after1 = ZonedDateTime.of(1970, 1, 8, 0, 0, 0, 0, ZoneOffset.UTC);
		ZonedDateTime after = ZonedDateTime.now();
		ZonedDateTime after2 = ZonedDateTime.of(after.getYear(), after.getMonthValue(), after.getDayOfMonth(), 0, 0, 0, 0, ZoneOffset.UTC);
		return after2.toEpochSecond()-after1.toEpochSecond();
	}

	public long getBefore() {
		ZonedDateTime before = ZonedDateTime.now();
		ZonedDateTime before1 = ZonedDateTime.of(before.getYear(), before.getMonthValue(), before.getDayOfMonth(), 0, 0, 0, 0, ZoneOffset.UTC);
		return before1.toEpochSecond();
	}
}
