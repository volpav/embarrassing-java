import java.util.Scanner;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

class Location {
    public int i;
    public int j;

    public Location(int i, int j) {
        this.i = i;
        this.j = j;
    }
}

class Vertex {
    public int id;
    public char value;
    public List<Vertex> edges;

    public Vertex(int id, char value) {
        this.id = id;
        this.value = value;
        this.edges = new ArrayList<Vertex>();
    }
}

class Graph {
    public List<Vertex> vertices;
    public Map<Integer, Vertex> vertexMap;
    public Map<Character, List<Vertex>> vertexValueMap;

    public Graph() {
        this.vertices = new ArrayList<Vertex>();
        this.vertexMap = new HashMap<Integer, Vertex>();
        this.vertexValueMap = new HashMap<Character, List<Vertex>>();
    }
}

public class WordSearch2 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int w = s.nextInt(), h = s.nextInt(), wc = s.nextInt();
        char[][] board = new char[w][h];

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                board[i][j] = s.next().charAt(0);
            }
        }

        String[] words = new String[wc];
        int index = 0;

        while ((wc--) > 0) {
            words[index++] = s.next();
        }

        List<String> found = new WordSearch2().findWords(board, words);

        System.out.print("[ ");

        for (String word : found) {
            System.out.print(String.format("'%s' ", word));
        }

        System.out.println("]");
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> ret = new ArrayList<String>();
        Graph g = getGraph(board);
        Set<String> foundWords = new HashSet<String>();

        for (String w : words) {
            if (!foundWords.contains(w)) {
                foundWords.add(w);

                char[] word = reverse(w.toCharArray());
                List<Vertex> start = g.vertexValueMap.get(word[0]);

                if (start != null) {
                    if (word.length == 1) {
                        ret.add(w);
                    } else {
                        for (Vertex s : start) {
                            Set<Integer> visited = new HashSet<Integer>();
                            visited.add(s.id);
                            
                            if (isMatch(s, word, 1, visited)) {
                                ret.add(w);
                                break;
                            }
                        }
                    }
                }
            }
        }

        return ret;
    }

    private boolean isMatch(Vertex current, char[] word, int index, Set<Integer> visited) {
        if (index >= word.length) {
            return true;
        }

        char next = word[index];
        List<Vertex> edges = current.edges;

        boolean found = false;

        for (Vertex v : edges) {
            if (!visited.contains(v.id) && next == v.value) {
                visited.add(v.id);
                found = isMatch(v, word, index + 1, visited);

                if (found) {
                    break;
                } else {
                    visited.remove(v.id);
                }
            }
        }

        return found;
    }

    private char[] reverse(char[] arr) {
        for(int i = 0; i < arr.length / 2; i++) {
            char temp = arr[i];
            arr[i] = arr[arr.length - i - 1];
            arr[arr.length - i - 1] = temp;
        }

        return arr;
    }

    private Graph getGraph(char[][] board) {
        Graph g = new Graph();
        
        int w = board.length, h = board[0].length;

        Map<Integer, Location> rowColumnData = new HashMap<Integer, Location>();

        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int id = getId(i, j);
                char value = board[i][j];

                Vertex v = new Vertex(id, value);

                g.vertices.add(v);
                g.vertexMap.put(v.id, v);
                rowColumnData.put(v.id, new Location(i, j));

                if (!g.vertexValueMap.containsKey(value)) {
                    g.vertexValueMap.put(value, new ArrayList<Vertex>());
                }

                g.vertexValueMap.get(value).add(v);
            }
        }

        for (Vertex v : g.vertices) {
            Location rowColumn = rowColumnData.get(v.id);
            int i = rowColumn.i, j = rowColumn.j;

            Location left = new Location(i - 1, j);
            Location right = new Location(i + 1, j);
            Location top = new Location(i, j - 1);
            Location bottom = new Location(i, j + 1);

            if (isValidLocation(left, w, h)) {
                v.edges.add(g.vertexMap.get(getId(left.i, left.j)));
            }

            if (isValidLocation(right, w, h)) {
                v.edges.add(g.vertexMap.get(getId(right.i, right.j)));
            }

            if (isValidLocation(top, w, h)) {
                v.edges.add(g.vertexMap.get(getId(top.i, top.j)));
            }

            if (isValidLocation(bottom, w, h)) {
                v.edges.add(g.vertexMap.get(getId(bottom.i, bottom.j)));
            }
        }

        return g;
    }

    private boolean isValidLocation(Location loc, int w, int h) {
        return loc.i >= 0 && loc.i < w && loc.j >= 0 && loc.j < h;
    }

    private int getId(int i, int j) {
        return (10 * i) + j;
    }
}