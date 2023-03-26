package ru.itis.ads.jarvis;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class JarvisAlgorithmTest {

	private final String CSV_FILE_PATH = "src/test/resources/";

	@ParameterizedTest
	@MethodSource("testData")
	public void testJarvisAlgorithm(final String testDataFileName, final String expectedOutputFileName) throws IOException {
		final List<Point> testData = readCSVFile(CSV_FILE_PATH + testDataFileName);
		final List<Point> expectedResults = readCSVFile(CSV_FILE_PATH + expectedOutputFileName);

		final List<Point> actualResults = JarvisAlgorithm.createConvexHull(testData);

		final List<Point> differenceFromExpected = new ArrayList<>(expectedResults);
		differenceFromExpected.removeAll(actualResults);

		final List<Point> differenceFromActual = new ArrayList<>(actualResults);
		differenceFromActual.removeAll(expectedResults);

		final List<Point> difference = new ArrayList<>(differenceFromExpected);
		difference.addAll(differenceFromActual);

		Assertions.assertTrue(expectedResults.containsAll(actualResults),
						"\nExpected convex hull: " + expectedResults +
										 ".\nActual convex hull:   " + actualResults +
										 ".\nDifference: " + difference);
	}

	private static Stream<Arguments> testData() {
		final List<Arguments> arguments = new ArrayList<>();

		for (int i = 0; i < 100; i ++) {
			arguments.add(Arguments.of("test_data_" + i + ".csv", "expected_output_" + i + ".csv"));
		}

		return arguments.stream();
	}

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

}