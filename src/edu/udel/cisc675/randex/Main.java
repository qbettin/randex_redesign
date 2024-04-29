package edu.udel.cisc675.randex;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
    public final static void main(String[] args)
	throws FileNotFoundException, IOException {
		if (args.length != 2) {
			System.out.println
			("Usage: java edu.udel.cisc675.randex.Test <filename> <seed>");
			System.exit(1);
		}
		String filename = args[0];
		long seed = Long.decode(args[1]);
		//System.out.println("Seed = "+seed);
		Randex randex = new Randex(filename, seed);
		randex.execute();
    }
    
}
