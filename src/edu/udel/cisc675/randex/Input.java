package edu.udel.cisc675.randex;
import java.io.FileReader;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.IOException;

/* Module Input: reads the file and stores the sequence of characters
   read in an array of char.  To use this class: instantiate it with
   the filename, then call method execute(), then you can read the
   field chars. */
public class Input {

    /* The name of the original file containing the exam (in) */
    String filename;

    /* The contents of the file, as an array of char (out) */
    char[] chars;

    /* Constructs a new instance with given filename.  Sets the
       filename field and does nothing else */
    public Input(String filename) {
	this.filename = filename;
    }

    /* Opens the file, reads it, and constructs chars. */
    public void execute() throws FileNotFoundException, IOException {
	FileReader fr = new FileReader(filename);
	int len = 2, off = 0;
	chars = new char[len];
	while (true) {
	    int nread = fr.read(chars, off, len-off);
	    if (nread < 0) break;
	    off += nread;
	    if (off == len) { // double buffer size
		len *= 2;
		chars = Arrays.copyOf(chars, 2*len);
	    }
	}
	fr.close();
	chars = Arrays.copyOf(chars, chars.length); // trim to length
    }
}
