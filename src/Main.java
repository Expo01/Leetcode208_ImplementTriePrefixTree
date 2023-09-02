public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

class Node{ // each Node contains an array of 26 possibilities for 26 character options
    Node children[] = new Node[26];  // array field of Nodes
    boolean isWord = false; // contains boolean to indicate that a word is complete at that letter
    // but does not prohibit other words to continue in same branch. For example
    // Do is a word. once on the letter 'o' is the root, we can state that yes do is a word but leave its
    // array field empty. Can then input 'dog' where we reach 'o' again but not fill in index 3 for 'd', then
    // 'd's boolean field = true
}


class Trie {
    // root
    Node root; // trie starts with a root which contains the two above fields

    public Trie() { // constructor to initialize new object
        root = new Node();
    }

    public void insert(String word) {
        Node temp = root; // temp node to iterate through trie
        for(char c : word.toCharArray()){
            int idx = c-'a'; // index int value for the Node's array field
            if(temp.children[idx] == null){ // if the array field of this node in the trie/branch is empty
                temp.children[idx] = new Node(); // then make a node at this index
            }
            temp = temp.children[idx]; // reassign temp to this latest node that was either just added or
            // already exists
        }
        temp.isWord = true; // since we are inserting not querrying, will always return true. if word
        // already exists, temp will just be updated without any additions then return true
    }

    public boolean search(String word) { // same concept but
        Node temp = root;
        for(char c : word.toCharArray()){
            int idx = c-'a';
            if(temp.children[idx] == null){ // returns false if node not found and sequentially found for all
                // chars in chain of Node[] field of each node
                return false;
            }
            temp = temp.children[idx];
        }
        return temp.isWord; // suppose we searched 'dog'. we already input dog and assigned 'g's boolean
        // field to true, so saying 'return temp.isWord = true' is unnecessary
    }

    public boolean startsWith(String prefix) { // exactly the same as 'search' method except return
        Node temp = root;
        for(char c : prefix.toCharArray()){
            int idx = c-'a';
            if(temp.children[idx] == null){
                return false;
            }
            temp = temp.children[idx];
        }
        return true; // we are not returning a node's field here. if we search for prefix= 'sma' and
        // the trie contains 'smart', the 'a' node boolean field is still false since 'sma' is not a word
        // so we return true, not the boolean field at 'a'
    }
}