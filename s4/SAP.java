package s4;

import edu.princeton.cs.algs4.*;
import java.util.*;

/**
 * Created by raquelitarosaguilar on 03/10/2016.
 */
public class SAP {

    private Digraph digraph;

    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Diagraph G){
        this.digraph = new Digraph(G);
    }

    // length of shortest ancestral path between v and w; -1 if no such path
    public int length(int v, int w){

    }

    // a common ancestor of v and w that participates in a shortest ancestral path
    // -1 if no such path
    public int ancestor(int v, int w){
        int shortestPath = Integer.MAX_VALUE;

        return;
    }

    // length of shortest ancestral path between any vertex in v and any vertex in w
    // -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w){
        return;
    }

    // a common ancestor that participates in shortest ancestral path
    // -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w){
        return;
    }

    /**
     * The following test client takes the name of a diagraph input file as a command line argument,
     * constructs the digraph, reads in vertex paris from standard input, and prints out the length
     * of the shortest ancestral path between the two vertices and a common ancestor that participates in that path
     * This test client was recieved from http://coursera.cs.princeton.edu/algs4/assignments/wordnet.html
     */
    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        SAP sap = new SAP(G);

        while(!StdIn.isEmpty()){
            int v = StdIn.readInt();
            int w = StdIn.readInt();
            int length = sap.length(v, w);
            int ancestor = sap.ancestor(v, w);
            System.out.println("length = %d, ancestor = %d\n", length, ancestor);
        }

    }

}
