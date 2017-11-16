package cn.itcast.jvm.test;

public class MemoryTest {

	public static void main(String[] args) {

		System.out.println("内存信息: " + toMemoryInfo());

	}

	private static String toMemoryInfo() {
		Runtime runtime = Runtime.getRuntime();
		int nFreeMemory = (int) (runtime.freeMemory() / 1024 / 1024);
		int nTotalMemory = (int) (runtime.totalMemory() / 1024 / 1024);
		return nFreeMemory + "M/" + nTotalMemory + "M(free/total)";
	}

}
