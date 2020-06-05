import java.util.LinkedList;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int x) 
    { val = x; }
}
public class Codec {
    private String NN = "X";
    private String spliter = ",";

    private void buildString(TreeNode node, StringBuilder sb){
        if(node == null){
            sb.append(NN);
            sb.append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right, sb);
        }
    }

    private TreeNode buildTree(LinkedList<String> list){
        String str = list.removeFirst();
        if(str.equals(NN)){
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.parseInt(str));
            node.left = buildTree(list);
            node.right = buildTree(list);
            return node;
        }
    }
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> list = new LinkedList<>();
        for(String s : data.split(spliter)){
            list.addLast(s);
        }
        TreeNode node = buildTree(list);
        return node;
    }
}