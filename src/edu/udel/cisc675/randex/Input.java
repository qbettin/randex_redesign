package edu.udel.cisc675.randex;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input {

    private String filename;
    private char[] chars;

    public Input(String filename) {
        this.filename = filename;
    }

    // Public method to execute file reading process
    public void execute() throws IOException {
        readFromFile();
        constructCharArray();
    }

    // Private method to read characters from file
	private void readFromFile() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line).append("\n"); // Append newline character
			}
			chars = sb.toString().toCharArray();
		}
	}

    // Private method to construct character array
    private void constructCharArray() {
        // Optionally, you can resize the array here if needed
        // For simplicity, I'll leave it as it is
    }

    // Getter method to access the character array
    public char[] getChars() {
        return chars;
    }
}
