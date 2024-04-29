package edu.udel.cisc675.randex;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Input {

    private String filename;
    private char[] chars;

    public Input(String filename) throws IOException {
        this.filename = filename;
		readFromFile();
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

    // Getter method to access the character array
    public char[] getChars() {
        return chars;
    }
}
