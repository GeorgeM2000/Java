import java.util.ArrayList;
import java.util.List;

public class TictactoeFunctionality {
	private int globalMINLevel = 0;
	private int globalMAXLevel = 0;
	private int globalPlayer = 0;
	private ArrayList<Integer> minArray = new ArrayList<>();
	private ArrayList<Integer> maxArray = new ArrayList<>();
	
	
	public void setGlobalPlayer(String aiPlayer) {
		if(aiPlayer.equals("X")) globalPlayer = 1;
		else if(aiPlayer.equals("O")) globalPlayer = -1;
	}
	

	// Returns the maximum element of the < array >
	public Integer MAXIMUM(ArrayList<Integer> array) {
		Integer max = array.get(0);
		for(int i = 1;i < array.size();i++) {
			if(array.get(i) > max) max = array.get(i);
		}
		return max;
	}
	

	// When the computer makes a move, take the coordinates of the move(a move consists of two coordinates, x and y)
	// and convert them into a button number
	public int convertCoordinatesToButtonNum(List<Integer> coordinates) {
		if(coordinates.get(0) == 0 && coordinates.get(1) == 0) return 1;
		else if(coordinates.get(0) == 0 && coordinates.get(1) == 1) return 2;
		else if(coordinates.get(0) == 0 && coordinates.get(1) == 2) return 3;
		else if(coordinates.get(0) == 1 && coordinates.get(1) == 0) return 4;
		else if(coordinates.get(0) == 1 && coordinates.get(1) == 1) return 5;
		else if(coordinates.get(0) == 1 && coordinates.get(1) == 2) return 6;
		else if(coordinates.get(0) == 2 && coordinates.get(1) == 0) return 7;
		else if(coordinates.get(0) == 2 && coordinates.get(1) == 1) return 8;
		else if(coordinates.get(0) == 2 && coordinates.get(1) == 2) return 9;
		return 0;
	}
	

	// Returns the minimum element of the < array >.
	public Integer MINIMUM(ArrayList<Integer> array) {
		Integer min = array.get(0);
		for(int i = 1;i < array.size();i++) {
			if(array.get(i) < min) min = array.get(i);
		}
		return min;
	}
	

	// Returns all possible actions given state < s >.
	public ArrayList<List<Integer>> ACTIONS(String[][] state) {
		ArrayList<List<Integer>> actions = new ArrayList<List<Integer>>();
		for(int i = 0;i < 3;i++) {
			for(int j = 0;j < 3;j++) {
				if(state[i][j].equals("0")) {
					List<Integer> list = new ArrayList<Integer>();
					list.add(i); list.add(j);
					actions.add(list);
				}
			}
		}
		return actions;
	}
	

	// Returns the winner of the game. The winner is decided based on the current state < s >.
	public int UTILITY(String[][] state) {
		for(int i = 0;i < 3;i++) {
			if(state[i][0].equals("X") && state[i][1].equals("X") && state[i][2].equals("X")) return 1;
			if(state[i][0].equals("O") && state[i][1].equals("O") && state[i][2].equals("O")) return -1;
		}
		
		for(int j = 0;j < 3;j++) {
			if(state[0][j].equals("X") && state[1][j].equals("X") && state[2][j].equals("X")) return 1;
			if(state[0][j].equals("O") && state[1][j].equals("O") && state[2][j].equals("O")) return -1;
		}
		
		if(state[0][0].equals("X") && state[1][1].equals("X") && state[2][2].equals("X")) return 1;
		if(state[0][0].equals("O") && state[1][1].equals("O") && state[2][2].equals("O")) return -1;
	
		if(state[0][2].equals("X") && state[1][1].equals("X") && state[2][0].equals("X")) return 1;
		if(state[0][2].equals("O") && state[1][1].equals("O") && state[2][0].equals("O")) return -1;
		
		return 0;
	}
	

	// Returns < true > if the game is over or < false > if the game is not over.
	public boolean TERMINAL(String state[][]) {
		int gameover = UTILITY(state);
		if(gameover == 1 || gameover == -1) return true;
		
		for(int i = 0;i < 3;i++) {
			for(int j = 0;j < 3;j++) {
				if(state[i][j].equals("0")) return false;
			}
		}
		
		return true;
	}
	

	// Returns the state after action < a > is taken in state < s >.
	public String[][] RESULT(String[][] state,int player,Integer[] action) {
		String[][] newState = {{"0","0","0"},{"0","0","0"},{"0","0","0"}};
		for(int i = 0;i < 3;i++) {
			for(int j = 0;j < 3;j++) {
				newState[i][j] = state[i][j];
			}
		}
		
		if(player == -1) newState[action[0]][action[1]] = "O";
		else if(player == 1) newState[action[0]][action[1]] = "X";
		
		return newState;
	}
	

	// Picks action a in ACTIONS(s) that produces the highest value of MIN_VALUE(RESULT(s,a))
	public int MAX_VALUE(String[][] state, int player, int globalMINLevel, int globalMAXLevel) {
		if(TERMINAL(state)) return UTILITY(state);
		int v = -10000;
		Integer[] array;
		int index;
		for(List<Integer> action : ACTIONS(state)) {
			array = new Integer[2];
            index = 0;
            for(Integer item : action){
                array[index] = item;
                index += 1;
            }
			v = Math.max(v, MIN_VALUE(RESULT(state,player,array),player-2,globalMINLevel+1,globalMAXLevel));
			if(globalPlayer == 1 && globalMAXLevel == 1) maxArray.add(v);
		}
		return v;
	}
	

	// Picks action a in ACTIONS(s) that produces the lowest value of MIN_VALUE(RESULT(s,a))
	public int MIN_VALUE(String[][] state, int player, int globalMINLevel, int globalMAXLevel) {
		if(TERMINAL(state)) return UTILITY(state);
		int v = 10000;
		Integer[] array;
		int index;
		for(List<Integer> action : ACTIONS(state)) {
			array = new Integer[2];
            index = 0;
            for(Integer item : action){
                array[index] = item;
                index += 1;
            }
			v = Math.min(v, MAX_VALUE(RESULT(state,player,array),player+2,globalMINLevel,globalMAXLevel+1));
			if(globalPlayer == -1 && globalMINLevel == 1) minArray.add(v);
		}
		return v;
	}
	
	
	// Call MAX_VALUE or MIN_VALUE according to the player variable
	public int MINIMAX(String[][] state) {
		int player = globalPlayer;
		if(player == 1) {																// Computer has X symbol
			int n = MAX_VALUE(state, player, globalMINLevel, globalMAXLevel+1);
			ArrayList<List<Integer>> action = ACTIONS(state);
			List<Integer> tmpList = action.get(maxArray.indexOf(MAXIMUM(maxArray))); 
			maxArray.clear();
			return convertCoordinatesToButtonNum(tmpList);
		}
		else {																			// Computer has O symbol
			int n = MIN_VALUE(state, player, globalMINLevel+1, globalMAXLevel);			
			ArrayList<List<Integer>> action = ACTIONS(state);
			List<Integer> tmpList = action.get(minArray.indexOf(MINIMUM(minArray)));
			minArray.clear();
			return convertCoordinatesToButtonNum(tmpList);
		}
	}
	
}
