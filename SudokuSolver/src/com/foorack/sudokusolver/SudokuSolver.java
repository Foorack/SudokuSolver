package com.foorack.sudokusolver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SudokuSolver {

	public static void main(String[] args) {

		char[] plate = new char[81];
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File("sudoku.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			for (int i = 0; i != 9; i++) {

				char[] n = br.readLine().toCharArray();

				for (int j = 0; j != 9; j++) {
					plate[i * 9 + j] = n[j];
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		Sudoku s = new Sudoku(plate);

		long start = System.currentTimeMillis();

		int itterations = 0;
		while (!s.isComplete()) {
			boolean didChange = false;
			for (int y = 0; y != 9; y++) {
				A: for (int x = 0; x != 9; x++) {
					int possibilities = 0;
					int set = -1;
					for (int j = 1; j != 10; j++) {
						if (s.canbe(x, y, (j + "").toCharArray()[0])) {
							possibilities++;
							if (possibilities > 1)
								continue A;
							set = j;
						}
					}
					if (possibilities == 1) {
						s.set(x, y, (set + "").toCharArray()[0]);
						didChange = true;
					} else if (possibilities == 0 && s.get(x, y) == '_') {
						System.out.println("Error: No values can be at pos X:"
								+ x + " Y:" + y + "!! exiting...");
						return;
					}
				}
			}

			if (didChange == false) {
				for (int y = 0; y != 9; y++) {
					A: for (int i = 1; i != 10; i++) {
						int times = 0;
						int canbeX = 0, canbeY = 0;
						B: for (int x = 0; x != 9; x++) {
							char c = s.get(x, y);
							if (c == '_') {
								if (s.canbe(x, y, (i + "").toCharArray()[0])) {
									canbeX = x;// check if 'num' can be at this
												// empty col. if so, save it and
												// possibly set it.
									canbeY = y;
									times++;
									if (times > 1)
										continue A; // no need to
													// continue if we
													// already reached
													// 'times' variable
													// > 2!
								}
								continue B;// emtpy col, continue on axel
							} else {
								if (Integer.parseInt(c + "") == i) {
									continue A;// this means there already that
												// number on that line
								}
							}

						}

						if (times == 1) {
							s.set(canbeX, canbeY, (i + "").toCharArray()[0]);
						}
					}
				}
				for (int x = 0; x != 9; x++) {
					A: for (int i = 1; i != 10; i++) {
						int times = 0;
						int canbeX = 0, canbeY = 0;
						B: for (int y = 0; y != 9; y++) {
							char c = s.get(x, y);
							if (c == '_') {
								if (s.canbe(x, y, (i + "").toCharArray()[0])) {
									canbeX = x;// check if 'num' can be at this
												// empty col
									canbeY = y;
									times++;
									if (times > 1)
										continue A; // no need to
													// continue if we
													// already reached
													// 'times' variable
													// > 2!
								}
								continue B;// emtpy col, continue on axel
							} else {
								if (Integer.parseInt(c + "") == i) {
									continue A;// this means there already that
												// number on that line
								}
							}

						}

						if (times == 1) {
							s.set(canbeX, canbeY, (i + "").toCharArray()[0]);
						}
					}
				}
				for (int yy = 0; yy != 3; yy++) {
					for (int xx = 0; xx != 3; xx++) {
						A: for (int i = 1; i != 10; i++) {
							int times = 0;
							int canbeX = 0, canbeY = 0;
							for (int y = 0; y != 3; y++) {
								B: for (int x = 0; x != 3; x++) {
									char c = s.get(xx * 3 + x, yy * 3 + y);
									if (c == '_') {
										if (s.canbe(xx * 3 + x, yy * 3 + y,
												(i + "").toCharArray()[0])) {
											canbeX = xx * 3 + x;// check if
																// 'num' can be
											// at this
											// empty col
											canbeY = yy * 3 + y;
											times++;
											if (times > 1)
												continue A; // no need to
															// continue if we
															// already reached
															// 'times' variable
															// > 2!
										}
										continue B;// emtpy col, continue on
													// axel
									} else {
										if (Integer.parseInt(c + "") == i) {
											continue A;// this means there
														// already that
														// number on that line
										}
									}
								}
							}
							if (times == 1) {
								s.set(canbeX, canbeY, (i + "").toCharArray()[0]);
							}
						}
					}
				}
			}

			/*
			 * for (int i = 0; i != 30; i++) { System.out.println(); }
			 * 
			 * s.print(System.out);
			 * 
			 * try { Thread.sleep(2000); } catch (InterruptedException e) {
			 * e.printStackTrace(); }
			 */

			itterations++;
		}// end of while complete = false

		System.out.println("Sudoku Complete!! Solved in: "
				+ (System.currentTimeMillis() - start) / 1000D
				+ " seconds..          (" + itterations + " itterations)");

		s.print(System.out);

	}
}