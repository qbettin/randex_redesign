randex_redesign

# Critique of Design v1.

When analyzing the original randex software, there are a couple things that make it 'bad'. First off, many of the match() and print() methods are defined and used in multiple modules, although they are the same method. These should be separated into seperated util files.

Another issue we noticed is that the Randex class calls the 'execute' method for all the other modules. First off, using the same name 'execute' is not specific in what the module is actually doing. Renaming these would help people better understand how the code works. Also, having each corresponding module call its own 'execute' method on instantiation would make more sense rather than having Randex call them. Also, this allows for changability in the future, as 'execute' methods could be instantiaed at necessary times. 

Also, in Randex we are accessing the public variables for many of the modules. Implementing getters is a more secure and reliable way to get the values we want.

#### Parnas says, input format is determined to be a "design decision which [is] questionable and likely to change under many circumstances"
- having one print util allows for reuse and the ability to easily add new methods to print on varrying input types
- removing the main() part of Randex and making that the driver module allows for more separation of concerns, and can be made more flexible to varrying types of input
- separating the call of execution methods in randex could allow for changes in input in the future

#### Parnas also says the following for Software Decision Module Decomposition: "the software utility module, which hides algorithms that are used in several other modules
- separating the match() and print() methods in to their own utilities is good practice which is used by basically every software company ever
- there is no reason to define the same utility in multiple modules, it is redundent, takes up space, and is non-modular

# Design v2:

## 1 - Module Names:
- Main
- Randex
- Input
- ProblemFinder
- FindAnswers
- RandomizeProblems
- RandomizeAnswers
- StringUtils
- PrintUtil

## 2 - Module Descriptions and Responsibilities:

### Main:

- **Responsibilities**: Calls Randex to instantiate and execute other modules.
- **Secrets**:
  - Manages the filename and seed for randomness.

### Randex:

- **Responsibilities**: Orchestrates the exam generation process by instantiating and executing all other modules in the appropriate order.
- **Secrets**:
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
  - The shuffling algorithm to randomize problems.

### RandomizeAnswers:

- **Responsibilities**: Generates random permutations of answer indices for each problem.
- **Secrets**:
  - The shuffling algorithm to randomize answers.

### StringUtils:

- **Responsibilities**: Provides utility functions for string manipulation, specifically matching
- **Secrets**:
  - the algorithm to find certain matches in the file

### PrintUtil:

- **Responsibilities**: Prints exam content, including problems and answers, to an output stream.
- **Secrets**:
  - Formats and prints exam content based on specified rules. Hides 

## 3 - USES Relation:

- Main USES Randex
- Randex USES Input, ProblemFinder, FindAnswers, RandomizeProblems, RandomizeAnswers, PrintUtil
- Input USES StringUtils
- ProblemFinder USES StringUtils
- FindAnswers USES StringUtils
- RandomizeProblems: No explicit USES relation
- RandomizeAnswers: No explicit USES relation
- PrintUtil: No explicit USES relation
- StringUtils: No explicit USES relation

## 4 - Module Interfaces:

### Main:

- Method: main(String[] args)

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

# Anticipation of Change

Choose 3 such ways and explain (1) how design v1 would have to change to accommodate the change, and (2) how your design v2 would have to change

## Change 1.
### Allow users to produce n exams rather than just one
#### Design v1
- We would need another argument that the user could type in the main() function of the Randex module
  - We would then need to loop the instantiation of Randex() n amounts of times
  - on each iteration we should change the seed
#### Design v2
- We would need another argument that the user could type in the main() function of the Main module
  - We would then need to loop the instantiation of Randex() n amounts of times
  - on each iteration we should change the seed

## Change 2.
### Ignore text in LaTeX comments
#### Design v1
- Modify each use of the match() method to ignore text within LaTeX commants idicated by %.
- Implement a mechanism to detect LaTeX comments (%).
- Adjust the logic of the match() method to skip characters enclosed within LaTeX comments.
#### Design v2
- Modify the StringUtils.match() method to ignore text within LaTeX comments indicated by %.
- Implement a mechanism within StringUtils.match() to identify and skip characters enclosed within LaTeX comments.
- Update the usage of StringUtils.match() throughout the codebase to account for the new behavior.

## Change 3.
### Provide a way for the user to indicate correct answers
#### Design v1
- Extend the functionality of Randex to keep track of correct answers and produce an answer key at the end. This would involve additional data structures to store correct answers and modifications to the output generation logic.
#### Design v2
- Extend the functionality of Randex to keep track of correct answers and produce an answer key at the end. This would involve additional data structures to store correct answers and modifications to the output generation logic.
- Implement a more user-friendly interface for specifying correct answers, such as a separate input file or interactive prompts.
- Enhance error handling to notify the user of any discrepancies between specified correct answers and generated exams.