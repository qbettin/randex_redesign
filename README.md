# randex_redesign
Critique of Design v1.
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

Module Structure:

1. InputModule:

- Responsibilities:
    - Read the input exam file in LaTeX format.
    - Parse the input file to extract problems and answers.
- Module Interface:
    - readInputFile(String filePath): List<Problem>
- Secrets:
    - Implementation details of parsing the input file and extracting problems and answers.

2. ProblemModule:

- Responsibilities:
    - Represent a single problem in the exam.
    - Store the problem statement and a list of answers.
- Module Interface:
    - getProblemStatement(): String
    - getAnswers(): List<String>
- Secrets:
    - Internal data structure for storing problem information.

3. RandomizationModule:

- Responsibilities:
    - Randomly permute the order of problems and answers in the exam.
- Module Interface:
    - randomizeExam(List<Problem> problems): List<Problem>
- Secrets:
    - Randomization algorithms and logic.

4. OutputModule:

- Responsibilities:
    - Generate the randomized exam in LaTeX format.
    - Write the randomized exam to an output file.
- Module Interface:
    - generateOutputFile(List<Problem> randomizedExam, String outputFilePath): void
- Secrets:
    - Formatting rules for generating the output file.

5. ValidationModule:

- Responsibilities:
    - Validate the input exam file and the randomized exam.
    - Ensure the correctness and integrity of the exam structure.
- Module Interface:
    - validateExam(List<Problem> exam): boolean
- Secrets:
    - Validation rules and checks.

6. ConfigurationModule:

- Responsibilities:
    - Manage configuration settings for the application.
    - Provide options for customization, such as randomization parameters.
- Module Interface:
    - setRandomizationOptions(Map<String, Object> options): void
    - getRandomizationOptions(): Map<String, Object>
- Secrets:
    - Configuration data and settings storage.

7. ReportingModule:

- Responsibilities:
    - Generate reports and logs related to the randomization process.
    - Provide insights into the randomization results.
- Module Interface:
    - generateReport(List<Problem> originalExam, List<Problem> randomizedExam): void
- Secrets:
    - Report formatting and content details.

8. MainDriverModule:

- Responsibilities:
    - Initialize the application.
    - Coordinate the interaction between different modules.
    - Handle user input and output.
- Module Interface:
    - main(String[] args): void
- Secrets:
    - Application initialization and flow control.
    
2. Interactions:

- The InputModule reads the input file and creates a list of Problem objects.
- The RandomizationModule receives the list of Problem objects, randomizes the order of problems and answers, and returns the randomized exam.
- The OutputModule generates the randomized exam in LaTeX format and writes it to an output file.
- The ValidationModule validates the structure and integrity of the exam.
- The ConfigurationModule manages configuration settings for randomization.
- The ReportingModule generates reports based on the original and randomized exams.