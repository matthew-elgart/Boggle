import java.util.*;

public class GoodWordOnBoardFinder implements IWordOnBoardFinder {
	
	/**Returns true iff a word can be created on this board starting from (row,col) for all the substring of word beginning at index
	 * 
	 */
	public boolean helper(BoggleBoard b, int row, int col, List<BoardCell> list, String word, int index) {
		if (index >= word.length()) {
			return true;
		}
		if (row < 0 || row >= b.size() || col < 0 || col >= b.size()) {
			return false;
		}
		if (list.contains(new BoardCell(row, col))) {
			return false;
		}
		String current = b.getFace(row, col);
		if (current.equals("qu") && index >= word.length()-2) {
			return false;
		}
		else if (current.equals(word.substring(index, index +current.length()))) {
			// add (row,col) to list
			BoardCell cell = new BoardCell(row, col);
			list.add(cell);
			// recurse on the rest of word
			// top left
			if (helper(b, row-1, col-1, list, word, index + current.length()) == true) return true;
			// top
			if (helper(b, row-1, col, list, word, index + current.length()) == true) return true;
			// top right
			if (helper(b, row-1, col+1, list, word, index + current.length()) == true) return true;
			// right
			if (helper(b, row, col+1, list, word, index + current.length()) == true) return true;
			// bottom right
			if (helper(b, row+1, col+1, list, word, index + current.length()) == true) return true;
			// bottom
			if (helper(b, row+1, col, list, word, index + current.length()) == true) return true;
			// bottom left
			if (helper(b, row+1, col-1, list, word, index + current.length()) == true) return true;
			// left
			if (helper(b, row, col-1, list, word, index + current.length()) == true) return true;
			
			// un-modify the list
			list.remove(cell);
			
		}
		return false;
	}
	
	public List<BoardCell> cellsForWord(BoggleBoard board, String word) {
		List<BoardCell> list = new ArrayList<BoardCell>();
		for (int r = 0; r < board.size(); r++) {
			for (int c = 0; c < board.size(); c++) {
				if (helper(board,r,c,list,word,0) == true) {
					return list;
				}
			}
		}
		list.clear();
		return list;
	}
}