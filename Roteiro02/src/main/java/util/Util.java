package util;

/**
 * Class containing useful methods for arrays manipulation.
 */
public class Util {

	/**
	 * Swaps the contents of two positions in an array.
	 *
	 * @param array
	 *            The array to be modified, not null
	 * @param i
	 *            One of the target positions
	 * @param j
	 *            The other target position
	 */
	public static void swap(Object[] array, int i, int j) {
		if (array == null)
			throw new IllegalArgumentException();

		Object temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public static boolean validation(Object[] array, int leftIndex,
			int rightIndex) {
		boolean valid = true;

		if (array == null || array.length == 0 || array.length < 2) {
			valid = false;
		}

		else if (rightIndex > array.length - 1) {
			valid = false;

		}

		else if (leftIndex < 0) {
			valid = false;

		}

		else if (rightIndex < 0) {
			valid = false;
		}

		else if (leftIndex > rightIndex) {
			valid = false;
		}

		return valid;
	}

	/**
	 * It says if a specific number is prime or not.
	 * 
	 * @param n
	 * @return
	 */
	public static boolean isPrime(long n) {
		boolean result = true;
		for (int i = 2; i < n; i++) {
			if (n % i == 0) {
				result = false;
				break;
			}
		}
		return result;
	}
}