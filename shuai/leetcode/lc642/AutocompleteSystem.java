import java.util.*;
import java.util.Map.Entry;
class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord = false;
    // only count word
    int count;

}
class Trie {
    TrieNode root;
    public Trie(){
        root = new TrieNode();
    }
    public void insertWord(String str, int time){
        TrieNode cur = root;
        for(char c : str.toCharArray()){
            if(! cur.children.containsKey(c)){
                cur.children.put(c, new TrieNode());
            }
            cur = cur.children.get(c);
        }
        cur.isWord = true;
        cur.count += time;
    }

    public Map<String, Integer> getAllPrefix(String str){
        Map<String, Integer> map = new HashMap<>();
        TrieNode cur = root;
        for(char c : str.toCharArray()){
            if(! cur.children.containsKey(c)){
                return map;
            }
            cur = cur.children.get(c);
        }
        getAllPrefix(cur, str, map);
        return map;

    }
    public void getAllPrefix(TrieNode node, String str, Map<String, Integer> map){
        
        if(node.isWord){
            map.put(str, node.count);
        } 
        for(char c: node.children.keySet()){
            getAllPrefix(node.children.get(c), str + c, map);
        }
        
    }
}
public class AutocompleteSystem {
    public Trie trie;
    public StringBuilder sb;
    public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new Trie();
        sb = new StringBuilder();
        int n = sentences.length;
        for(int i = 0; i < n; i++){
            trie.insertWord(sentences[i], times[i]);
        }
        
    }
    
    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        List<Map.Entry<String, Integer>> entriesList = new ArrayList<>();
        Comparator<Map.Entry<String, Integer>> comparator = new Comparator<>(){
        
            @Override
            public int compare(Entry<String, Integer> arg0, Entry<String, Integer> arg1) {
                // TODO Auto-generated method stub
                if(arg0.getValue() == arg1.getValue()){
                    return arg0.getKey().compareTo(arg1.getKey());
                } else {
                    return arg1.getValue() - arg0.getValue();
                }
            }
        };
        if(c == '#'){
           trie.insertWord(sb.toString(), 1);
           sb.delete(0, sb.length());
           
        } else {
            sb.append(c);
            Map<String, Integer> map = trie.getAllPrefix(sb.toString());
            for(Map.Entry<String, Integer> e : map.entrySet()){
                entriesList.add(e);
            }
            entriesList.sort(comparator);
            for(int i = 0; i < 3 && i < entriesList.size(); i++){
                res.add(entriesList.get(i).getKey());
            }
        }

        return res;
    }
}


