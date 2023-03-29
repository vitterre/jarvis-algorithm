import matplotlib.pyplot as plt

array_sizes = []
execution_times = []
iteration_counts = []
solution_sizes = []

with open('jarvis/log.csv') as f:
    for line in f:
        values = tuple(map(int, line.strip().split(',')))
        array_size, execution_time, iteration_count, solution_size = values[2], values[0], values[1], values[3]

        array_sizes.append(array_size)
        execution_times.append(execution_time)
        iteration_counts.append(iteration_count)
        solution_sizes.append(solution_size)

fig, ax = plt.subplots()
ax.plot(array_sizes, execution_times, label='Execution Time')
ax.plot(array_sizes, iteration_counts, label='Iteration Count')
ax.set_xlabel('Array Size')
ax.set_ylabel('Time/Iterations')
ax.legend()
plt.title("Execution time and iteration count depending on array size")
plt.show()

plt.plot(array_sizes, solution_sizes)
plt.yscale('log')
plt.xlabel('Array size')
plt.ylabel('Solution size')
plt.title("Solution size depending on array size")
plt.show()

