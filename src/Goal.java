import java.util.ArrayList;
import java.util.LinkedList;

import com.sun.jmx.snmp.Timestamp;

public class Goal {
	private LinkedList<MileStone> mileStones = new LinkedList<MileStone>();
	private ArrayList<GoalCheckIn> checkIns = new ArrayList<GoalCheckIn>();
	private String name;
	private String description;
	private Timestamp start;
	private MileStone nextMileStone;
	private Timestamp end;
	private int progress;
	boolean acomplished;
	

	public Goal(LinkedList<MileStone> mileStones, ArrayList<GoalCheckIn> checkIns, String description, String name, Timestamp end, Timestamp start, int progress){

		this.mileStones = mileStones;
		this.checkIns = checkIns;
		this.name = name;
		this.description = description;
		this.start = start;
		this.end = end;
		this.progress = 0;
		if(mileStones.size() > 0){
			this.nextMileStone = mileStones.get(0);
		}
		acomplished = false;
	}
	public void addMileStone(int indexOfNewMileStone, MileStone newMileStone){
		if(nextMileStone == null){
			nextMileStone = newMileStone;
		}
		else if(this.mileStones.get(indexOfNewMileStone) == nextMileStone){
			nextMileStone = newMileStone;
		}
		this.mileStones.add(indexOfNewMileStone, newMileStone);
	}
	public void removeMileStone(int indexToRemove){
		if(this.mileStones.get(indexToRemove) == nextMileStone){
			nextMileStone = this.mileStones.get(indexToRemove + 1);
		}
		this.mileStones.remove(indexToRemove);
	}
	public void addCheckIn(GoalCheckIn checkIn){
		this.checkIns.add(checkIn);
		int newProgress = 0;
		if(checkIn.progress >= 100){
			this.nextMileStone.setAcomplished(true);
			for(MileStone m: this.mileStones){
				if(m.acomplished){
					newProgress += m.howFarYouHaveCome;
				}
			}
		}
		else{
			for(MileStone m: this.mileStones){
				if(m.acomplished){
					newProgress += m.howFarYouHaveCome;
				}
				else{
					newProgress += Math.round(m.howFarYouHaveCome*(checkIn.progress/100));
					break;
				}
			}
			this.progress = newProgress;
		}
	}
	public LinkedList<MileStone> getMileStones() {
		return mileStones;
	}
	public ArrayList<GoalCheckIn> getCheckIns() {
		return checkIns;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Timestamp getStart() {
		return start;
	}
	public MileStone getNextCheckPoint() {
		return nextMileStone;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public double getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAcomplished() {
		return acomplished;
	}
	
}
