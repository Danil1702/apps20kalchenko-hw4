package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;

import java.util.ArrayList;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie newTrie) {
        this.trie = newTrie;
    }

    public int load(String... strings) {
        for (String str: strings) {
            String[] splitted = str.split(" ");
            for (String splittedStr: splitted) {
                Tuple tuple = new Tuple(splittedStr, splittedStr.length());
                trie.add(tuple);
            }
        }
        return 0;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word) ;
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        if (pref.length() < 2) {
            throw  new IllegalArgumentException();
        }
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        if (pref.length() < 2 || k < 0) {
            throw  new IllegalArgumentException();
        }
        Iterable<String> wordsByPref = trie.wordsWithPrefix(pref);
        ArrayList<String> wordsNeeded = new ArrayList<>();
        for (String st: wordsByPref) {
            if (st.length() <= 2 + k) {
                wordsNeeded.add(st);
            }
        }
        return wordsNeeded;
    }

    public int size() {
        return trie.size();
    }
}
