package com.foorack.sudokusolver;

import java.io.PrintStream;

class Sudoku {

	char[] s;

	public Sudoku(char[] plate) {
		this.s = plate;
	}

	public char get(int x, int y) {
		return s[y * 9 + x];
	}

	public void set(int x, int y, char c) {
		s[y * 9 + x] = c;
		System.err.println("SET: X:" + x + " Y:" + y + " TO:" + c);
	}

	public boolean isComplete() {
		for (char c : s) {
			if (c == '_') {
				return false;
			}
		}
		return true;
	}

	public boolean canbe(int x, int y, char c) {

		if (s[y * 9 + x] != '_') {
			return false;
		}

		for (int xx = 0; xx != 9; xx++) {
			if (s[y * 9 + xx] == c) {
				return false;
			}
		}

		for (int yy = 0; yy != 9; yy++) {
			if (s[yy * 9 + x] == c) {
				return false;
			}
		}

		int x2 = (x == 0 || x == 3 || x == 6) ? 1
				: (x == 1 || x == 4 || x == 7) ? 2 : 3;
		int y2 = (y == 0 || y == 3 || y == 6) ? 1
				: (y == 1 || y == 4 || y == 7) ? 2 : 3;

		if (x2 == 1) {
			if (y2 == 1) {
				boolean b = c(x, y, c);
				if (b == false) {
					return false;
				}
				boolean b1 = c(x, y + 1, c);
				if (b1 == false) {
					return false;
				}
				boolean b2 = c(x, y + 2, c);
				if (b2 == false) {
					return false;
				}
			} else if (y2 == 2) {
				boolean b = c(x, y - 1, c);
				if (b == false) {
					return false;
				}
				boolean b1 = c(x, y, c);
				if (b1 == false) {
					return false;
				}
				boolean b2 = c(x, y + 1, c);
				if (b2 == false) {
					return false;
				}
			} else {
				boolean b = c(x, y - 2, c);
				if (b == false) {
					return false;
				}
				boolean b1 = c(x, y - 1, c);
				if (b1 == false) {
					return false;
				}
				boolean b2 = c(x, y, c);
				if (b2 == false) {
					return false;
				}
			}
		} else if (x2 == 2) {
			if (y2 == 1) {
				boolean b = c(x - 1, y, c);
				if (b == false) {
					return false;
				}
				boolean b1 = c(x - 1, y + 1, c);
				if (b1 == false) {
					return false;
				}
				boolean b2 = c(x - 1, y + 2, c);
				if (b2 == false) {
					return false;
				}
			} else if (y2 == 2) {
				boolean b = c(x - 1, y - 1, c);
				if (b == false) {
					return false;
				}
				boolean b1 = c(x - 1, y, c);
				if (b1 == false) {
					return false;
				}
				boolean b2 = c(x - 1, y + 1, c);
				if (b2 == false) {
					return false;
				}
			} else {
				boolean b = c(x - 1, y - 2, c);
				if (b == false) {
					return false;
				}
				boolean b1 = c(x - 1, y - 1, c);
				if (b1 == false) {
					return false;
				}
				boolean b2 = c(x - 1, y, c);
				if (b2 == false) {
					return false;
				}
			}
		} else {
			if (y2 == 1) {
				boolean b = c(x - 2, y, c);
				if (b == false) {
					return false;
				}
				boolean b1 = c(x - 2, y + 1, c);
				if (b1 == false) {
					return false;
				}
				boolean b2 = c(x - 2, y + 2, c);
				if (b2 == false) {
					return false;
				}
			} else if (y2 == 2) {
				boolean b = c(x - 2, y - 1, c);
				if (b == false) {
					return false;
				}
				boolean b1 = c(x - 2, y, c);
				if (b1 == false) {
					return false;
				}
				boolean b2 = c(x - 2, y + 1, c);
				if (b2 == false) {
					return false;
				}
			} else {
				boolean b = c(x - 2, y - 2, c);
				if (b == false) {
					return false;
				}
				boolean b1 = c(x - 2, y - 1, c);
				if (b1 == false) {
					return false;
				}
				boolean b2 = c(x - 2, y, c);
				if (b2 == false) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean c(int x, int y, char c) {
		if (s[y * 9 + x] == c) {
			return false;
		}
		if (s[y * 9 + x + 1] == c) {
			return false;
		}
		if (s[y * 9 + x + 2] == c) {
			return false;
		}
		return true;
	}

	public void print(PrintStream o) {
		o.println("=====================================");
		for (int i = 0; i != 9; i++) {
			o.println("| " + s[i * 9 + 0] + " | " + s[i * 9 + 1] + " | "
					+ s[i * 9 + 2] + " | " + s[i * 9 + 3] + " | "
					+ s[i * 9 + 4] + " | " + s[i * 9 + 5] + " | "
					+ s[i * 9 + 6] + " | " + s[i * 9 + 7] + " | "
					+ s[i * 9 + 8] + " |");
			o.println("=====================================");
		}
	}

}