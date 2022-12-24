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
	
	public static ArrayList<String> validateDailyEntry(int mealCalories, int exerciseCalories, int exerciseTime,
			int exerciseSteps, int workTime, int workStress, int sleepTime, int sleepRestfulness, int meditationTime) {
		ArrayList<String> errors = new ArrayList<String>();
		final int MINUTES_IN_DAY = 1440;
		final int HOURS_IN_DAY = 24;
		
		try {
			// validating mealCalories
			if (mealCalories < 0) {
				errors.add("Meals - Calories must be a positive number");
			}
			
			// validating exerciseCalories
			if (exerciseCalories < 0) {
				errors.add("Exercise - Calories burned must be a positive number");
			}
			
			// validating exerciseTime
			if (exerciseTime < 0) {
				errors.add("Exercise - Time exercising must be a positive number");
			}
			if (exerciseTime > MINUTES_IN_DAY) {
				errors.add("Exercise - Time must not exceed " + MINUTES_IN_DAY + ", since this is the total minutes in a day");
			}
			
			// validating exerciseSteps
			if (exerciseSteps < 0) {
				errors.add("Exercise - Step count must be a positive number");
			}
			
			// validating workTime
			if (workTime < 0) {
				errors.add("Work - Time worked must be a positive number");
			}
			if (workTime > HOURS_IN_DAY) {
				errors.add("Work - Time worked must not exceed " + HOURS_IN_DAY + ", since this is the total hours in a day");
			}
			
			// validating workStress
			if (workStress < 0 || workStress > 3) {
				errors.add("Invalid value entered for Work Stressfulness");
			}
			
			// validating sleepTime
			if (sleepTime < 0) {
				errors.add("Sleep - Time slept must be a positive number");
			}
			if (sleepTime > HOURS_IN_DAY) {
				errors.add("Sleep - Time slept must not exceed " + HOURS_IN_DAY + ", since this is the total hours in a day");
			}
			
			// validating sleepRestfulness
			if (sleepRestfulness < 0 || sleepRestfulness > 3) {
				errors.add("Invalid value entered for Sleep Restfulness");
			}
			
			// validating meditationTime
			if (meditationTime < 0) {
				errors.add("Meditation - Time must be a positive number");
			}
			if (meditationTime > MINUTES_IN_DAY) {
				errors.add("Meditation - Time must not exceed " + MINUTES_IN_DAY + ", since this is the total minutes in a day");
			}
		} catch (Exception e) {
			errors.add("Unable to submit daily entry. The problem is: " + e.getMessage());
		}
		
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
