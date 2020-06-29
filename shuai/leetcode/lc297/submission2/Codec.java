import java.util.LinkedList;
import java.util.Queue;

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

    
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node == null){
                sb.append(NN);
            } else {
                sb.append(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
            sb.append(spliter);
            

        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        LinkedList<String> list = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        for(String s : data.split(spliter)){
            list.addLast(s);
        }

        TreeNode root = null;
        String str = list.removeFirst();
        if(str.equals(NN)){
            return null;
        } else {
            int val = Integer.parseInt(str);
            root = new TreeNode(val);
        }
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();
            String nextStr = list.removeFirst();
            if(!nextStr.equals(NN)){
                int val = Integer.parseInt(nextStr);
                curr.left = new TreeNode(val);
                queue.add(curr.left);
            }
            nextStr = list.removeFirst();
            if(!nextStr.equals(NN)){
                int val = Integer.parseInt(nextStr);
                curr.right = new TreeNode(val);
                queue.add(curr.right);
            }
            
        }

        return root;
    }
}