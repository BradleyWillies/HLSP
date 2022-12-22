package beans;

public class DailyEntry {
	private int mealCalories;
	private int exerciseCalories;
	private int exerciseTime;
	private int workTime;
	private String workStress;
	private int sleepTime;
	private String sleepRestfulness;
	private int meditationTime;
	
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
	public int getWorkTime() {
		return workTime;
	}
	public void setWorkTime(int workTime) {
		this.workTime = workTime;
	}
	public String getWorkStress() {
		return workStress;
	}
	public void setWorkStress(String workStress) {
		this.workStress = workStress;
	}
	public int getSleepTime() {
		return sleepTime;
	}
	public void setSleepTime(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	public String getSleepRestfulness() {
		return sleepRestfulness;
	}
	public void setSleepRestfulness(String sleepRestfulness) {
		this.sleepRestfulness = sleepRestfulness;
	}
	public int getMeditationTime() {
		return meditationTime;
	}
	public void setMeditationTime(int meditationTime) {
		this.meditationTime = meditationTime;
	}
}
