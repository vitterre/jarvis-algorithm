import csv
import random
from scipy.spatial import ConvexHull

# Генерация тестовых данных
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


# Вычисление правильных результатов
def convex_hull_algorithm(points):
    hull = ConvexHull(points)
    return hull.vertices.tolist()


# Запись тестовых наборов данных и правильных результатов в CSV файлы
for i, points in enumerate(test_sets):
    # Запись тестовых данных
    with open(f'test_data_{i}.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(['x', 'y'])
        for point in points:
            writer.writerow(point)

    # Запись правильных результатов
    with open(f'expected_result_{i}.csv', 'w', newline='') as csvfile:
        writer = csv.writer(csvfile)
        writer.writerow(['index'])
        hull_indices = convex_hull_algorithm(points)
        for index in hull_indices:
            writer.writerow([index])
