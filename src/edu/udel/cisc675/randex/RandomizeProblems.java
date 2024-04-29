package edu.udel.cisc675.randex;
import java.util.random.RandomGenerator;

/* Module RandomizeProblems.  Given the number of problems nprob, and
   a random number generator, constructs an array of length nprob
   which is a permutation of the integers 0..nprob-1.  Uses the
   Fisher-Yates algorithms.  See
   https://en.wikipedia.org/wiki/Fisher–Yates_shuffle. */
public class RandomizeProblems {

    /* The number of problems (in) */
    private int nprob;

    /* The random number generator (in) */
    RandomGenerator rand;

    /* Permutation of problem IDs (out) */
    private int[] probPerm;

    public int getNprob() {
        return nprob;
    }

    public int[] getProbPerm() {
        return probPerm;
    }

    /* Constructs new instance from given fields.  Sets fields only,
       does nothing else. */
    public RandomizeProblems(int nprob, RandomGenerator rand) {
        this.nprob = nprob;
        this.rand = rand;
        createRandomProblems();
    }

    /* Constructs the probPerm. */
    public void createRandomProblems() {
        this.probPerm = new int[nprob];
        for (int i=0; i<nprob; i++)
            probPerm[i] = i;
        //System.out.print("rands = ");
        for (int i=nprob-1; i>=0; i--) {
            int j = rand.nextInt(i+1);
            //System.out.print(" "+j);
            if (i!=j) {
            int t = probPerm[i];
            probPerm[i] = probPerm[j];
            probPerm[j] = t;
            }
        }
	//System.out.println();
    }
}
