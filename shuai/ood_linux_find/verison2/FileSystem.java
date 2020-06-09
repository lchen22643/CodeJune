import java.util.*;
class File {
	boolean isFile = true;
	String fileName;
	int fileSize;
	long createdTime;
	long lastUpdatedTime;
	long lastAccessedTime;
	String owner;
	public File(String fileName){
		this.fileName = fileName;
		this.createdTime = System.currentTimeMillis();
		this.lastAccessedTime = System.currentTimeMillis();
		this.lastUpdatedTime = System.currentTimeMillis();
	}

	public File(String fileName, Integer size){
		this(fileName);
		this.fileSize = size;
	}

	public Map<String, File> getAllChildren(){
		return null;
	}
}

class Dir extends File{
	Map<String, File> children;
	public Dir(String fileName){
		super(fileName);
		this.isFile = false;
		this.children = new HashMap<>();
	}
	@Override
	public Map<String, File> getAllChildren(){
		return this.children;
	}
	public void addFileToDir(File file){
		String fileName = file.fileName;
		this.children.put(fileName, file);
	}
}

interface Filter {
	public boolean apply(File file);
}

class SizeFilter implements Filter {
	int size;

	public SizeFilter(int size) {
		this.size = size;
	}

	public boolean apply(File file) {
		return file.fileSize > this.size;
	}
}

class NameFilter implements Filter {
	String suffix;

	public NameFilter(String suffix) {
		this.suffix = suffix;
	}

	public boolean apply(File file) {
		return file.fileName.endsWith(this.suffix);
	}
}

class AndFilter implements Filter {
	List<Filter> filters;

	public AndFilter(List<Filter> filters){
		this.filters = filters;
	}

	public boolean apply(File file){
		for(Filter f : filters){
			if(!f.apply(file)){
				return false;
			}
		}
		return true;
	}
}

class OrFilter implements Filter {
	List<Filter> filters;

	public OrFilter(List<Filter> filters){
		this.filters = filters;
	}

	public boolean apply(File file){
		for(Filter f : filters){
			if(f.apply(file)){
				return true;
			}
		}
		return true;
	}
}
public class FileSystem {
	
	Dir root = null;

	public FileSystem() {
		root = new Dir("/");
	}
    public List<File> findWithFilters(Filter filter) {
		return findWithFilters(this.root, filter);
	}

	public List<File> findWithFilters(File directory, Filter filter) {
		
		if (directory.isFile) {
			return null;
		}

		List<File> res = new ArrayList<>();
		findWithFilters(directory, filter, res);
		return res;
	}

	private void findWithFilters(File directory, Filter filter, List<File> res) {
		if (directory.getAllChildren().size() == 0) {
			return;
		}

		for (File file : directory.getAllChildren().values()) {
			if (!file.isFile) {
				findWithFilters(file, filter, res);
			} else if(filter.apply(file)){
				res.add(file);
			}
		}
	}

	public static void main(String[] args){
	       FileSystem f = new FileSystem();
		   Dir home = new Dir("home");
		   Dir shuai = new Dir("shuai");
		   Dir ted = new Dir("ted");
		   home.addFileToDir(shuai);
		   home.addFileToDir(ted);
		   File lc100 = new File("lc100.java", 1000);
		   File lc200 = new File("lc300.java", 3000);
		   File lc400 = new File("lc400.java", 4000);
		   File lc500 = new File("lc500", 5000);
		   File readme = new File("readme.md", 5000);
		   shuai.addFileToDir(lc100);
		   ted.addFileToDir(lc200);
		   shuai.addFileToDir(lc400);
		   ted.addFileToDir(lc500);
		   ted.addFileToDir(readme);
		   Filter f1 = new SizeFilter(3000);
		   Filter f2 = new NameFilter("java");
		   List<File> res = new ArrayList<>();
		   List<Filter> filters = new ArrayList<>();
		   filters.add(f1);
		   filters.add(f2);
		   Filter f3 = new AndFilter(filters);
		   Filter f4 = new OrFilter(filters);
		   // test1 
		   System.out.println("test1");
		  
		   res = f.findWithFilters(home, f1);
		   for(File file: res){
			   System.out.println(file.fileName);
		   }
		   // test 2 
		   System.out.println("test2");
		   
		   res = f.findWithFilters(home, f2);
		   for(File file: res){
			   System.out.println(file.fileName);
		   }

		    // test 3 
			System.out.println("test3");
		   
			res = f.findWithFilters(home, f3);
			for(File file: res){
				System.out.println(file.fileName);
			}

			 // test 4 
			 System.out.println("test4");
		   
			 res = f.findWithFilters(home, f4);
			 for(File file: res){
				 System.out.println(file.fileName);
			 }
  

	}
}
