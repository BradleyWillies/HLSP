package beans;

import java.util.ArrayList;

public class DailyEntry {
	private int mealCalories;
	private int exerciseCalories;
	private int exerciseTime;
	private int exerciseSteps;
	private int workTime;
	private int workStress;
	private int sleepTime;
	private int sleepRestfulness;
	private int meditationTime;
	
	public DailyEntry() {}
	
	public DailyEntry(int mealCalories, int exerciseCalories, int exerciseTime, int exerciseSteps, int workTime,
			int workStress, int sleepTime, int sleepRestfulness, int meditationTime) {
		super();
		this.mealCalories = mealCalories;
		this.exerciseCalories = exerciseCalories;
		this.exerciseTime = exerciseTime;
		this.exerciseSteps = exerciseSteps;
		this.workTime = workTime;
		this.workStress = workStress;
		this.sleepTime = sleepTime;
		this.sleepRestfulness = sleepRestfulness;
		this.meditationTime = meditationTime;
	}
	
	// TODO HLSP - create validate method for daily entry fields
	public static ArrayList<String> validateDailyEntry(String mealCalories, String exerciseCalories, String exerciseTime,
			String exerciseSteps, String workTime, String workStress, String sleepTime, String sleepRestfulness, String meditationTime) {
		ArrayList<String> errors = new ArrayList<String>();
		
		// validating mealCalories
		
		// validating exerciseCalories
		
		// validating exerciseTime
		
		// validating exerciseSteps
		
		// validating workTime
		
		// validating workStress
		
		// validating sleepTime
		
		// validating sleepRestfulness
		
		// validating meditationTime
		
		
		return errors;
	}
	
	public int getMealCalories() {
		return mealCalories;
	}
	public void setMealCalories(int mealCalories) {
		this.mealCalories = mealCalories;
	}
	public int getExerciseCalories() {
		return exerciseCalories;
	}
	public void setExerciseCalories(int exerciseCalories) {
		this.exerciseCalories = exerciseCalories;
	}
	public int getExerciseTime() {
		return exerciseTime;
	}
	public void setExerciseTime(int exerciseTime) {
		this.exerciseTime = exerciseTime;
	}
	public int getExerciseSteps() {
		return exerciseSteps;
	}
	public void setExerciseSteps(int exerciseSteps) {
		this.exerciseSteps = exerciseSteps;
	}
	public int getWorkTime() {
		return workTime;
	}
	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}
	public int getWorkStress() {
		return workStress;
	}
	public void setWorkStress(int workStress) {
		this.workStress = workStress;
	}
	public int getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	public int getSleepRestfulness() {
		return sleepRestfulness;
	}
	public void setSleepRestfulness(int sleepRestfulness) {
		this.sleepRestfulness = sleepRestfulness;
	}
	public int getMeditationTime() {
		return meditationTime;
	}
	public void setMeditationTime(int meditationTime) {
		this.meditationTime = meditationTime;
	}
}
