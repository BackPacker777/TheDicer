package org.howardbates.thedicer;

import java.util.Random;

/**
 * Created by bates.he.z on 5/7/2014.
 */
public class ProcessRoll {
	private int result;
	private int[] rolls;

	public ProcessRoll(int dieQty, int dieType, boolean total) {
		result = 0;
		Random roll = new Random();
		rolls = new int[dieQty];
		if (dieType == 0) {
			result = 0;
		} else {
			for (int i = 0; i < dieQty; i++) {
				rolls[i] = roll.nextInt(dieType) + 1;
				if (total) {
					result = result + roll.nextInt(dieType) + 1;
				}
			}
		}
	}

	public int getResult() {
		return result;
	}

	public int[] getRolls() {
		return rolls;
	}
}
