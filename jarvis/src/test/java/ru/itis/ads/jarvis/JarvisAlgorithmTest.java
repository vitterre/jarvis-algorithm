package ru.itis.ads.jarvis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class contains tests for the Jarvis algorithm, which is a
 * method for computing the convex hull of a set of points in the plane.
 * <p>
 * The tests read in CSV files containing test data and expected output,
 * and verify that the algorithm produces the correct result.
 * <p>
 * The CSV files containing the test data and expected output are located
 * in the "src/test/resources" directory.
 */
class JarvisAlgorithmTest {

	/*----- Private fields -----*/

	/**
	 * String representing the file path to the directory containing the test
	 * data and expected output CSV files.
	 */
	private final String CSV_FILE_PATH = "src/test/resources/";


	/*----- Private methods -----*/

	/**
	 * Static method that generates a stream of {@code Arguments}, where each
	 * argument is a pair of CSV file names (test data and expected output)
	 * for one test case.
	 * <p>
	 * The method generates 100 test cases, numbered 0 to 99.
	 *
	 * @return a Stream of Arguments, where each argument is a pair of CSV file
	 * names (test data and expected output) for one test case.
	 */
	private static Stream<Arguments> testData() {
		final List<Arguments> arguments = new ArrayList<>();

		for (int i = 0; i < 100; i++) {
			arguments.add(Arguments.of("test_data_" + i + ".csv", "expected_output_" + i + ".csv"));
		}

		return arguments.stream();
	}


	/*----- Public methods -----*/

	/**
	 * Static method that reads in a CSV file containing point data, and returns
	 * a {@code List} of Point objects representing the points in the file.
	 *
	 * @param filePath a String representing the file path to the CSV file containing
	 *                 the point data.
	 * @return a List of Point objects representing the points in the CSV file.
	 * @throws IOException if an I/O error occurs while reading the CSV file.
	 */
	private static List<Point> readCSVFile(final String filePath) throws IOException {
		final BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
		final List<Point> dataList = new ArrayList<>();

		String line = bufferedReader.readLine();

		while ((line = bufferedReader.readLine()) != null) {
			String[] values = line.split(",");
			dataList.add(new Point(Integer.parseInt(values[0]), Integer.parseInt(values[1])));
		}

		return dataList;
	}

	/**
	 * Static method that deletes the log.csv file before running the tests.
	 * This method is annotated with {@code @BeforeAll}, which ensures that
	 * it is executed once before any tests are run.
	 */
	@BeforeAll
	public static void deleteLogFile() {
		new File("log.csv").delete();
	}

	/**
	 * Parameterized test method that takes as input a pair of CSV file names
	 * (test data and expected output), and a TestInfo object representing
	 * metadata about the test.
	 * <p>
	 * The method reads in the test data and expected output from the CSV files,
	 * and calls the {@code JarvisAlgorithm.createConvexHull()} method to compute
	 * the convex hull of the test data. It then verifies that the output of the
	 * algorithm matches the expected output. If the test fails, the method outputs
	 * information about the expected output, actual output, and the difference
	 * between them.
	 *
	 * @param testDataFileName       a String representing the file name of the CSV file
	 *                               containing the test data.
	 * @param expectedOutputFileName a String representing the file name of the CSV
	 *                               file containing the expected output.
	 * @param testInfo               a TestInfo object representing metadata about the test.
	 * @throws IOException if an I/O error occurs while reading the CSV files.
	 */
	@ParameterizedTest
	@MethodSource("testData")
	public void testJarvisAlgorithm(final String testDataFileName, final String expectedOutputFileName, final TestInfo testInfo) throws IOException {
		// Print test name
		System.out.println(testInfo.getDisplayName());

		// Enable profiling and console output
		JarvisAlgorithm.ENABLE_PROFILING = true;
		JarvisAlgorithm.PROFILING_OUTPUT_IN_CONSOLE = true;

		// Read in the test data and expected output from CSV files
		final List<Point> testData = readCSVFile(CSV_FILE_PATH + testDataFileName);
		final List<Point> expectedResults = readCSVFile(CSV_FILE_PATH + expectedOutputFileName);

		// Compute the convex hull using JarvisAlgorithm
		final List<Point> actualResults = JarvisAlgorithm.createConvexHull(testData);

		// Compute the differences between the expected and actual results
		final List<Point> differenceFromExpected = new ArrayList<>(expectedResults);
		differenceFromExpected.removeAll(actualResults);

		final List<Point> differenceFromActual = new ArrayList<>(actualResults);
		differenceFromActual.removeAll(expectedResults);

		final List<Point> difference = new ArrayList<>(differenceFromExpected);
		difference.addAll(differenceFromActual);

		// Verify that the actual results match the expected results, and output information about
		// the differences if the test fails
		Assertions.assertTrue(expectedResults.containsAll(actualResults),
						"\nExpected convex hull: " + expectedResults +
										".\nActual convex hull:   " + actualResults +
										".\nDifference: " + difference);
	}

}