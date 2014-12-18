import java.util.*;

public class BoardFirstAutoPlayer extends AbstractAutoPlayer {
    BoggleBoard b;
    ILexicon iLex;
    int minimumLength;
	/**
     * Adds all words that are in our lexicon starting from (row,col) 
     * to our list of found words. You cannot repeat cells in creating a word
     * @param row is starting point for search 
     * @param col is starting point for search
     * @param prefix are characters that we have found up to this point
     * @param visited are BoardCells associated with prefix
     */
    private void find(int row, int col, StringBuilder prefix, List<BoardCell> visited) {
    	if (iLex.wordStatus(prefix) == LexStatus.WORD && prefix.length() >= minimumLength) {
			add(prefix.toString());
		}
    	boolean check = true;
    	if (row < 0 || row >= b.size() || col < 0 || col >= b.size()) {
			check = false;
		}
    	BoardCell cell = new BoardCell(row, col);
    	if (visited.contains(cell)) {
			check = false;
		}
    	if (iLex.wordStatus(prefix) == LexStatus.NOT_WORD) {
    		check = false;
    	}
    	
    	if (check == true) {
   			prefix.append(b.getFace(row, col));    		
    		visited.add(cell);
    			
   			find(row-1, col-1, prefix, visited);
   			find(row-1, col, prefix, visited);
    		find(row-1, col+1, prefix, visited);
    		find(row, col+1, prefix, visited);
    		find(row+1, col+1, prefix, visited);
   			find(row+1, col, prefix, visited);
   			find(row+1, col-1, prefix, visited);
   			find(row, col-1, prefix, visited);
    		
    		visited.remove(cell);
    		if (b.getFace(row, col).length() == 2) {
    			prefix.deleteCharAt(prefix.length()-1);
    			prefix.deleteCharAt(prefix.length()-1);
    		}
    		else {
    			prefix.deleteCharAt(prefix.length()-1);
    		}
    	}
    }
	
	@Override
    public void findAllValidWords(BoggleBoard board, ILexicon lex, int minLength) {
	    // TODO: you write this code
		b = board;
		iLex = lex;
		minimumLength = minLength;
		clear();
		List<BoardCell> list = new ArrayList<BoardCell>();
		for (int r = 0; r < board.size(); r++) {
			for (int c = 0; c < board.size(); c++) {
				list.clear();
				find (r, c, new StringBuilder(""), list);
				}
			}
		}
    }
    


