randex_redesign

Critique of Design v1.
exam3.tex is missing \end{problem}

1. Randex.java:

- Issues:
  - The Randex class serves as the main module of the application, but it directly instantiates and executes all other modules within the execute() method. This approach leads to high coupling between Randex and the other modules, making it less modular and reusable.
  - To enhance modularity, the Randex class should delegate the execution of specific tasks to the seperate/corresponding modules rather than directly managing their instantiation and execution.

2. Input.java:

- Issues:
  - The Input class is responsible for reading the original exam file. The class should focus solely on input operations and not perform additional tasks like buffer resizing.
  - To improve modularity, refactoring the Input class to handle only file reading operations, while delegating tasks such as buffer management to separate utility classes could be suitable.

3. Output.java:

- Issues:
  - The Output class is responsible for writing the randomized exam to an output stream. While the class successfully prints the exam with permuted problems and answers, it directly accesses data from multiple modules. This direct interaction increases coupling and reduces modularity.
  - To enhance modularity, restructuring the Output class to receive processed data from other modules as input parameters, rather than directly accessing their fields could help. This change would improve the class's reusability.

4. RandomizeAnswers.java:

- Issues:
  - The RandomizeAnswers class is responsible for constructing random permutations of answers for each problem. However, the class directly manipulates the answerPerms array and the random number generator instance.
  - To enhance modularity, separating the randomization logic within the RandomizeAnswers class and providing clear interfaces for interacting with the randomized answer permutations could help. This approach would improve the class's reusability and maintainability.

Design v2:

## 1 - Module Names:

- Randex
- Input
- ProblemFinder
- FindAnswers
- RandomizeProblems
- RandomizeAnswers
- StringUtils
- PrintUtil

## 2 - Module Descriptions and Responsibilities:

### Randex:

- **Responsibilities**: Orchestrates the entire exam generation process by instantiating and executing all other modules in the appropriate order.
- **Secrets**:
  - Manages the filename and seed for randomness.
  - Coordinates the interaction between different modules.

### Input:

- **Responsibilities**: Reads characters from a file.
- **Secrets**:
  - Accesses the file system.
  - Hides the details of file reading and character conversion.

### ProblemFinder:

- **Responsibilities**: Identifies the start and stop indices of problem sections in the input text.
- **Secrets**:
  - Encapsulates the logic for finding problem boundaries.

### FindAnswers:

- **Responsibilities**: Identifies the start and stop indices of answer sections within each problem.
- **Secrets**:
  - Handles the logic for parsing answer sections within problems.

### RandomizeProblems:

- **Responsibilities**: Generates a random permutation of problem IDs.
- **Secrets**:
  - Implements the Fisher-Yates shuffling algorithm.

### RandomizeAnswers:

- **Responsibilities**: Generates random permutations of answer indices for each problem.
- **Secrets**:
  - Implements the Fisher-Yates shuffling algorithm.

### StringUtils:

- **Responsibilities**: Provides utility functions for string manipulation.
- **Secrets**:
  - Contains generic string manipulation methods.

### PrintUtil:

- **Responsibilities**: Prints exam content, including problems and answers, to an output stream.
- **Secrets**:
  - Formats and prints exam content based on specified rules.

## 3 - USES Relation:

- Randex USES Input, ProblemFinder, FindAnswers, RandomizeProblems, RandomizeAnswers, PrintUtil
- Input USES StringUtils
- ProblemFinder USES StringUtils
- FindAnswers USES StringUtils
- RandomizeProblems: No explicit USES relation
- RandomizeAnswers: No explicit USES relation
- PrintUtil: No explicit USES relation

## 4 - Module Interfaces:

### Randex:

- Constructor: Randex(String filename, long seed)
- Method: execute()

### Input:

- Constructor: Input(String filename)
- Method: getChars()

### ProblemFinder:

- Constructor: ProblemFinder(char[] chars)
- Method: getProbStarts()
- Method: getProbStops()

### FindAnswers:

- Constructor: FindAnswers(char[] chars, int[] probStarts, int[] probStops)
- Method: getAnswerStarts()
- Method: getAnswerStops()

### RandomizeProblems:

- Constructor: RandomizeProblems(int nprob, RandomGenerator rand)
- Method: getProbPerm()

### RandomizeAnswers:

- Constructor: RandomizeAnswers(int[] numAnswers, RandomGenerator rand)
- Method: getAnswerPerms()

### StringUtils:

- Static Method: match(char[] chars, int off, char[] sought)
- Static Method: to2DArray(ArrayList<ArrayList<Integer>> list)
- Static Method: toArray(ArrayList<Integer> list)

### PrintUtil:

- Constructor: PrintUtil(PrintStream out, char[] chars, int[] probStarts, int[] probStops, int[][] answerStarts, int[][] answerStops, int[][] answerPerms)
- Method: printRange(int start, int stop)
- Method: printAnswer(int pid, int aid)
- Method: printProblem(int pid)
