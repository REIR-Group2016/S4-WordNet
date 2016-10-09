package s4.src;

import edu.princeton.cs.algs4.*;

/**
 * Created by raquelitarosaguilar on 03/10/2016.
 * Your implementation must verify that the given
 * digraph is actually a rooted DAG. All methods
 * should throw a java.lang.IndexOutOfBoundsException
 * if one (or more) of the input arguments is not between 0 and G.V()-1.
 */

public class SAP {

    private Digraph digraph;
    private BreadthFirstDirectedPaths a, b;
    private int shortestPath = Integer.MAX_VALUE;

    /*****************************************************************
     * constructor takes a digraph (not necessarily a DAG)
     *****************************************************************/
    public SAP(Digraph G){
        digraph = new Digraph(G);
    }

    /*****************************************************************
     * length of shortest ancestral path between v and w;
     * -1 if no such path
     *****************************************************************/
    public int length(int v, int w){
        isOutOfBoundaries(v);
        isOutOfBoundaries(w);

        a = new BreadthFirstDirectedPaths(digraph, v);
        b = new BreadthFirstDirectedPaths(digraph, w);

        for (int i = 0; i < digraph.V(); i++){
            if (a.hasPathTo(i) && b.hasPathTo(i)){
                shortestPath = Math.min(shortestPath, a.distTo(i) + b.distTo(i));
            }
        }
        return -1;
    }

    /*****************************************************************
     * a common ancestor of v and w that participates in a shortest
     * ancestral path -1 if no such path
     *****************************************************************/
    public int ancestor(int v, int w){
        isOutOfBoundaries(v);
        isOutOfBoundaries(w);

        a = new BreadthFirstDirectedPaths(digraph, v);
        b = new BreadthFirstDirectedPaths(digraph, w);
        int thisAncestor = -1;


        for (int i = 0; i < digraph.V(); i++) {
            if (a.hasPathTo(i) && b.hasPathTo(i)) {
                int vertexLength = a.distTo(i) + b.distTo(i);

                if (shortestPath > vertexLength) {
                    shortestPath = vertexLength;
                    thisAncestor = i;
                }
            }
        }
        return thisAncestor;
    }

    /*****************************************************************
     * length of shortest ancestral path between any vertex in v and
     * any vertex in w -1 if no such path
     *****************************************************************/
    public int length(Iterable<Integer> v, Iterable<Integer> w){

        isOutOfBoundaries(v);
        isOutOfBoundaries(w);

        a = new BreadthFirstDirectedPaths(digraph, v);
        b = new BreadthFirstDirectedPaths(digraph, w);

        for (int i = 0; i < digraph.V(); i++){
            if (a.hasPathTo(i) && b.hasPathTo(i)){
                shortestPath = Math.min(shortestPath, a.distTo(i) + b.distTo(i));
            }
        }
        return -1;
    }


    /*****************************************************************
     * a common ancestor that participates in shortest ancestral path
     * -1 if no such path
     *****************************************************************/
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        isOutOfBoundaries(v);
        isOutOfBoundaries(w);

        a = new BreadthFirstDirectedPaths(digraph, v);
        b = new BreadthFirstDirectedPaths(digraph, w);
        int thisAncestor = -1;


        for (int i = 0; i < digraph.V(); i++) {
            if (a.hasPathTo(i) && b.hasPathTo(i)) {
                int vertexLength = a.distTo(i) + b.distTo(i);

                if (shortestPath > vertexLength) {
                    shortestPath = vertexLength;
                    thisAncestor = i;
                }
            }
        }
        return thisAncestor;
    }

    /**********************************************************************
     * Since it was a requirement in the project to throw an Exceptions in
     * every method a helper functions was created that:
     *  - checks if one or more input is between 0 and G.V()-1
     *  - if they are not then we throw IndexOutOfBoundsException
     *********************************************************************/
    private void isOutOfBoundaries(int input){
        if(input < 0 || input > digraph.V() - 1){
            throw new IndexOutOfBoundsException();
        }
    }

    private void isOutOfBoundaries(Iterable<Integer> input){
        for(int i : input){
            isOutOfBoundaries(i);
        }
    }

    /**************************************************************************************************************
     * The following test client takes the name of a diagraph input file as a command line argument,
     * constructs the digraph, reads in vertex paris from standard input, and prints out the length
     * of the shortest ancestral path between the two vertices and a common ancestor that participates in that path
     * This test client was received from http://coursera.cs.princeton.edu/algs4/assignments/wordnet.html
     *************************************************************************************************************/
    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        while(!StdIn.isEmpty()){
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        }

    }

}
