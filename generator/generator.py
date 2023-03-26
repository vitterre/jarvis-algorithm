import numpy as np
from scipy.spatial import ConvexHull
import csv

JAVA_TEST_RESOURCES_PATH = "../jarvis/src/test/resources/"

for i in range(10):
    points = np.random.randint(-100, 100, size=(100, 2))

    hull = ConvexHull(points)
    convex_hull_points = points[hull.vertices]

    with open(JAVA_TEST_RESOURCES_PATH + f"test_data_{i}.csv", "w", newline="") as f:
        writer = csv.writer(f)
        writer.writerow(["x", "y"])
        writer.writerows(points)

    with open(JAVA_TEST_RESOURCES_PATH + f"expected_output_{i}.csv", "w", newline="") as f:
        writer = csv.writer(f)
        writer.writerow(["x", "y"])
        writer.writerows(convex_hull_points)
