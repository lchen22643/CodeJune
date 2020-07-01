import java.util.*;
public class Solution
{ 
	// METHOD SIGNATURE BEGINS, THIS METHOD IS REQUIRED
    public List<String> reorderLines(int logFileSize, List<String> logLines) 
	{
		// WRITE YOUR CODE HERE
		List<String> wordLog = new ArrayList<>();
		List<String> numLog = new ArrayList<>();
		
		for(String s : logLines){
		    int i = s.indexOf(" ");
		    char ch = s.charAt(i+1);
		    if (ch >= '0' && ch <= '9'){
		        numLog.add(s);
		    }
		    else{
		        wordLog.add(s);
		    }
		}
		Collections.sort(wordLog, new Comparator<String>() {
		    @Override
		    public int compare(String s1, String s2) {
		        int index1 = s1.indexOf(" ");
		        String id1 = s1.substring(0, index1);
		        String letter1 = s1.substring(index1 + 1);
		        
		        int index2 = s2.indexOf(" ");
		        String id2 = s2.substring(0, index2);
		        String letter2 = s2.substring(index2 + 1);
		        
		        int v1 = letter1.compareTo(letter2);
		        if(v1 != 0) return v1;
		        int v2 = id1.compareTo(id2);
		        return v2; 
		    }
		});
		List<String> result = new ArrayList<>();
		int j = 0;
		for(int i = 0; i < wordLog.size(); i ++) {
		    result.add(j,wordLog.get(i));
		    j++;
		}
		for (int i = 0; i < numLog.size(); i ++) {
            // wordLog --> change to numLog
		    result.add(j,numLog.get(i));
		    j++;
		}
		return result;
		
	}
	
	
	// METHOD SIGNATURE ENDS
}