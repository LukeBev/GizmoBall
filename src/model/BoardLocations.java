package model;

public class BoardLocations {
	Model model;
	public static boolean[][] available_locations = new boolean[21][21];
	
	public BoardLocations(Model m){
		this.model = m;
		
		for(int i = 0; i < 20; i++){
			for(int j = 0; j < 20; j++){
				available_locations[i][j] = true;
			}
		}
	}
	
	public void setInvalid(int x, int y){
		x = x/25;
		y = y/25;
		available_locations[x][y] = false;
	}
	
	public boolean isAvailable(int x, int y){
		x = x/25;
		y = y/25;
		System.out.println(available_locations[x][y]);
		if(available_locations[x][y]){
			return true;
		}
		else{
			return false;
		}
	}
	
}
