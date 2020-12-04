package ua.edu.ucu.tries;

import ua.edu.ucu.queue.Queue;
import java.util.*;

public class RWayTrie implements Trie {
    static class Node {
        protected Map<Character, Node> children;
        protected String value = "";
        protected boolean isWord;

        public Node() {
            children = new HashMap<>();
            isWord = false;
        }
    }

    private Node root;
    private int size;

    public RWayTrie() {
        root = new Node();
        size = 0;
    }

    @Override
    public void add(Tuple t) {
        Node current = root;
        for (Character ch: t.term.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                Node newNode = new Node();
                newNode.value = current.value.concat(String.valueOf(ch));
                current.children.put(ch, newNode);
            }
            current = current.children.get(ch);
        }
        if (!current.isWord) {
            current.isWord = true;
            size++;
        }
    }

    @Override
    public boolean delete(String word) {
        Node current = root;
        for (Character ch: word.toCharArray()) {
            current = current.children.get(ch);
        }
        current.isWord = false;
        size--;
        return true;
    }

    @Override
    public boolean contains(String word) {
        Node current = root;
        for (Character ch: word.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                return false;
            }
            current = current.children.get(ch);
        }
        return current.isWord;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        Node current = root;
        for (Character ch: s.toCharArray()) {
            current = current.children.get(ch);
        }
        Queue queue = new Queue();
        List<String> wordsFound = new ArrayList<>();
        queue.enqueue(current);
        while (!queue.isEmpty()) {
            Node elem = (Node) queue.dequeue();
            if (elem.isWord) {
                wordsFound.add(elem.value);
            }
            for (Node node: elem.children.values()) {
                queue.enqueue(node);
            }
        }
        return wordsFound;
    }

    @Override
    public int size() {
        return size;
    }

}
