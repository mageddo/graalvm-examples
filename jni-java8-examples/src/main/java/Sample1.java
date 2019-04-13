public class Sample1 {

	// --- Native methods
	public native int intMethod(int n);

	public native boolean booleanMethod(boolean bool);

	public native String stringMethod(String text);

	public native int intArrayMethod(int[] intArray);


	// --- Main method to test our native library
	public static void main(String[] args) {

		System.load(Sample1.class.getResource("/lib/Sample1.so").getPath());
		Sample1 sample = new Sample1();
		int square = sample.intMethod(5);
		boolean bool = sample.booleanMethod(true);
		String text = sample.stringMethod("java");
		int sum = sample.intArrayMethod(new int[]{1, 2, 3, 4, 5, 6, 7});

		System.out.println("intMethod: " + square);
		System.out.println("booleanMethod: " + bool);
		System.out.println("stringMethod: " + text);
		System.out.println("intArrayMethod: " + sum);
	}
}
