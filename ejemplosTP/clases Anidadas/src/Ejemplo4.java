public class Ejemplo4 {
	private int f = 1;

	public class InnerClass1 {
		private int f = 2;

		public class InnerClass2 {
			private int f = 3;

			public void test() {
				System.out.println(this.f + " " + InnerClass1.this.f);
			}
		}

		public void test() {
			System.out.println(this.f + " " + Ejemplo4.this.f);
		}
	}
}
