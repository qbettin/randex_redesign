# randex_redesign

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