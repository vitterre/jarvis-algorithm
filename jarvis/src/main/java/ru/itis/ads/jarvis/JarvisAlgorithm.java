package ru.itis.ads.jarvis;

import java.util.ArrayList;
import java.util.List;

/**
 * The Jarvis algorithm, also known as the gift wrapping algorithm,
 * is a method for computing the convex hull of a set of points in
 * a plane or in higher dimensions.
 * <p>
 * It works by iterating over the
 * points and selecting the next point on the hull by taking the
 * point with the smallest polar angle relative to the previous point.
 * <p>
 * This implementation of the algorithm uses the cross product to
 * determine the orientation of three points and find the next point on the hull.
 */
public final class JarvisAlgorithm {

	/*----- Public methods -----*/

	/**
	 * Creates the convex hull of a list of points using the Jarvis algorithm.
	 *
	 * @param points points the list of points for which to compute the convex hull.
	 * @return the list of points on the convex hull in clockwise order.
	 * @throws IllegalArgumentException if the input list contains less than 3 points.
	 */
	public static List<Point> createConvexHull(final List<Point> points) {
		// Check that the input list contains at least 3 points
		if (points.size() < 3) {
			throw new IllegalArgumentException("At least 3 points are required!");
		}

		// Initialize an empty list to hold the points on the convex hull
		List<Point> hull = new ArrayList<>();

		// Find the point with the smallest x-coordinate as the starting point
		Point startPoint = points.get(0);
		for (Point p : points) {
			if (p.x < startPoint.x) {
				startPoint = p;
			}
		}

		// Set the starting point as the current point on the hull
		Point current = startPoint;

		// Iterate over the points and find the next point on the hull until we return to the
		do {
			// Add the current point to the list of points on the hull
			hull.add(current);

			// Find the next point on the hull
			Point next = points.get(0);

			for (int i = 0; i < points.size(); i++) {
				// Skip the current point
				if (points.get(i).equals(current)) {
					continue;
				}

				// Compute the cross product of the vectors from the current point to the next point
				// and the current point to the i-th point
				final Integer crossProduct = crossProduct(current, next, points.get(i));

				// Update the next point if the cross product is positive or if it is zero and the
				// distance to the i-th point is greater than the distance to the current next point
				if (next.equals(current) || crossProduct > 0
								|| (crossProduct == 0 && distance(current, points.get(i)) > distance(current, next))) {
					next = points.get(i);
				}
			}

			// Set the next point as the current point for the next iteration
			current = next;
		} while (!current.equals(startPoint));

		// Return the list of points on the convex hull in clockwise order
		return new ArrayList<>(hull);
	}


	/*----- Private methods -----*/

	/**
	 * Computes the cross product of the vectors from point {@code p1} to point
	 * {@code p2} and from point {@code p1} to point {@code p3}.
	 *
	 * @param p1 the first point.
	 * @param p2 the second point.
	 * @param p3 the third point.
	 * @return the cross product of the two vectors.
	 */
	private static Integer crossProduct(final Point p1, final Point p2, final Point p3) {
		final Integer ux = p2.x - p1.x;
		final Integer uy = p2.y - p1.y;
		final Integer vx = p3.x - p1.x;
		final Integer vy = p3.y - p1.y;

		return ux * vy - uy * vx;
	}

	/**
	 * Calculates the distance between two points, which is used to compare the
	 * distances between the current point and potential next points on the hull.
	 *
	 * @param p1 the first point.
	 * @param p2 the second point.
	 * @return the squared distance between the two points.
	 */
	private static Integer distance(final Point p1, final Point p2) {
		final Integer dx = p2.x - p1.x;
		final Integer dy = p2.y - p1.y;

		return dx * dx + dy * dy;
	}

}
