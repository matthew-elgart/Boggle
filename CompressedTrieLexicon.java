import java.util.*;


public class CompressedTrieLexicon extends TrieLexicon {
	
	
	
	public void compress() {
		doCompress(myRoot);
	}
	
	public void doCompress(Node root) {
		if (root == null) {
			return;
		}
		if (root.children.size() == 0) {
			while (!root.parent.isWord && root.parent.children.keySet().size() == 1) {
				root.parent.info = root.parent.info.concat(root.info);
				root.parent.children = new TreeMap<Character,Node>();
			}
		}
		for (Node n: root.children.values()) {
			doCompress(n);
		}
		return;
	}
	
	@Override
	public void load (ArrayList<String> list) {
		super.load(list);
		compress();
	}
}