package ru.itis.ads.jarvis;

import java.util.Objects;

/**
 * This class represents a two-dimensional point with integer
 * coordinates.
 * <p>
 * The {@code x} and {@code y} coordinates of the point are
 * immutable, meaning they cannot be changed after the point
 * object is created.
 * <p>
 * Instances of this class are guaranteed to be immutable and
 * thread-safe.
 */
public final class Point {

	/*----- Public fields -----*/

	/**
	 * The x-coordinate of this point.
	 */
	public final Integer x;

	/**
	 * The y-coordinate of this point.
	 */
	public final Integer y;


	/*----- Constructors -----*/

	/**
	 * Constructs a new point with the specified {@code x} and
	 * {@code y} coordinates.
	 *
	 * @param x the x-coordinate of the point.
	 * @param y the y-coordinate of the point.
	 */
	public Point(final Integer x, final Integer y) {
		this.x = x;
		this.y = y;
	}


	/*----- Overrides -----*/

	/**
	 * Returns a string representation of this point in the format
	 * "(x,y)".
	 *
	 * @return a string representation of this point.
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}

	/**
	 * Compares this point to the specified object. The result is
	 * {@code true} if and only if the argument is not null and is
	 * a {@code Point} object that has the same {@code x} and {@code y}
	 * coordinates as this point.
	 *
	 * @param o the object to compare this point against.
	 * @return {@code true} if the given object represents a {@code Point}
	 * with the same coordinates as this point, {@code false} otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof Point)) {
			return false;
		}
		Point other = (Point) o;
		return x.equals(other.x) && y.equals(other.y);
	}

	/**
	 * Returns a hash code value for this point. The hash code is generated
	 * using the {@code x} and {@code y} coordinates of this point.
	 *
	 * @return a hash code value for this point.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

}
