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
	
	private Integer MAXIMUM(ArrayList<Integer> array) {
		Integer max = array.get(0);
		for(int i = 1;i < array.size();i++) {
			if(array.get(i) > max) max = array.get(i);
		}
		return max;
	}
	
	private Integer MINIMUM(ArrayList<Integer> array) {
		Integer min = array.get(0);
		for(int i = 1;i < array.size();i++) {
			if(array.get(i) < min) min = array.get(i);
		}
		return min;
	}
	
	
	private int convertCoordinatesToButtonNum(List<Integer> coordinates) {
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
	
	
	private ArrayList<List<Integer>> ACTIONS(String[][] state) {
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
	
	private String[][] RESULT(String[][] state,int player,Integer[] action) {
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
	
	private int MAX_VALUE(String[][] state, int player, int globalMINLevel, int globalMAXLevel) {
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
	
	private int MIN_VALUE(String[][] state, int player, int globalMINLevel, int globalMAXLevel) {
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
	
	
	public int MINIMAX(String[][] state) {
		int player = globalPlayer;
		if(player == 1) {
			@SuppressWarnings("unused")
			int n = MAX_VALUE(state, player, globalMINLevel, globalMAXLevel+1);
			ArrayList<List<Integer>> action = ACTIONS(state);
			List<Integer> tmpList = action.get(maxArray.indexOf(MAXIMUM(maxArray)));
			maxArray.clear();
			return convertCoordinatesToButtonNum(tmpList);
		}
		else {
			@SuppressWarnings("unused")
			int n = MIN_VALUE(state, player, globalMINLevel+1, globalMAXLevel);
			ArrayList<List<Integer>> action = ACTIONS(state);
			List<Integer> tmpList = action.get(minArray.indexOf(MINIMUM(minArray)));
			minArray.clear();
			return convertCoordinatesToButtonNum(tmpList);
		}
	}
	
	
}
