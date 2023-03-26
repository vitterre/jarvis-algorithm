import csv
import random
from scipy.spatial import ConvexHull

JAVA_TEST_RESOURCES_PATH = "../jarvis/src/test/resources/"

# Generate test data
def generate_test_data(n_points):
    points = set()
    while len(points) < n_points:
        x = random.randint(-100, 100)
        y = random.randint(-100, 100)
        points.add((x, y))
    return list(points)


test_sets = []
for i in range(10):
    n_points = random.randint(10, 100)
    points = generate_test_data(n_points)
    test_sets.append(points)


# Calculate expected results
def convex_hull_algorithm(points):
    hull = ConvexHull(points)
    return hull.vertices.tolist()


# Write test data and expected results
for i, points in enumerate(test_sets):
    # Write test results
    with open(JAVA_TEST_RESOURCES_PATH + f'test_data_{i}.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(['x', 'y'])
        for point in points:
            writer.writerow(point)

    # Write expected results
    with open(JAVA_TEST_RESOURCES_PATH + f'expected_result_{i}.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(['index'])
        hull_indices = convex_hull_algorithm(points)
        for index in hull_indices:
            writer.writerow([index])
