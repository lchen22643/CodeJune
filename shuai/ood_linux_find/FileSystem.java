import java.util.*;

public class FileSystem {
	class File {
		boolean isFile = false;
		Map<String, File> children = new HashMap<>();
		String fileName;
		int fileSize;
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

	File root = null;

	public FileSystem() {
		root = new File();
	}

	public List<File> findWithFilters(File directory, List<Filter> filters) {
		if (directory.isFile) {
			return null;
		}

		List<File> res = new ArrayList<>();
		helper(directory, filters, res);
		return res;
	}

	private void helper(File directory, List<Filter> filters, List<File> res) {
		if (directory.children.size() == 0) {
			return;
		}

		for (File file : directory.children.values()) {
			if (!file.isFile) {
				helper(file, filters, res);
			} else {
				boolean pass = true;
				for (Filter f : filters) {
					if (!f.apply(file)) {
						pass = false;
					}
				}
				if (pass) {
					res.add(file);
				}

			}
		}
	}
}
