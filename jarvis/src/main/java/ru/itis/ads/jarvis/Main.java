package ru.itis.ads.jarvis;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		System.out.println("Jarvis algorithm!");

		final List<Point> points = List.of(new Point(0, 3), new Point(2, 2), new Point(1, 1), new Point(2, 1),
						new Point(3, 0), new Point(0, 0), new Point(3, 3));
		final List<Point> hull = JarvisAlgorithm.createConvexHull(points);
		System.out.println(hull);
	}
}