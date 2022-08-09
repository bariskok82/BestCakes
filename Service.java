package cake;

import java.util.*;

class Service {
	private Scanner sc;
	private List<Item> ingredient_list;
	private List<Item> mold_list;
	private List<Item> preset_list;
	private List<Item> order_list;
	private int state;
	private String input1;
	private String input2;
	
	Service() {
		this.sc = new Scanner(System.in);
		this.ingredient_list = new ArrayList<Item>();
		this.mold_list = new ArrayList<Item>();
		this.preset_list = new ArrayList<Item>();
		this.order_list = new ArrayList<Item>();
		this.state = 1;
		this.input1 = "";
		this.input2 = "";
	}
	
	void run() {
		while (true) {
			if (this.state == 1) {
				this.main_menu();
			}
			if (this.state == 10 || this.state == 20 || this.state == 30 || this.state == 40) {
				this.show_list();
			}
			if (this.state == 11 || this.state == 21 || this.state == 31 || this.state == 41) {
				this.show_detail();
			}
			if (this.state == 12 || this.state == 22 || this.state == 32 || this.state == 42) {
				this.show_add();
			}
			if (this.state == 99) {
				break;
			}
		}
	}
	void main_menu() {
		System.out.printf("<���� �޴�>\n");
		System.out.printf("1: ��� �غ�\n2: ���� �غ�\n3: ���� �غ�\n4: �ֹ� �ޱ�\n5: ����\n�޴��� �����ϼ���: ");
		this.input1 = this.sc.nextLine();
		System.out.printf("\n");
		if (this.input1.equals("1")) {
			this.state = 10;
		}
		if (this.input1.equals("2")) {
			this.state = 20;
		}
		if (this.input1.equals("3")) {
			this.state = 30;
		}
		if (this.input1.equals("4")) {
			this.state = 40;
		}
		if (this.input1.equals("5")) {
			this.state = 99;
		}
	}
	String menu_name() {
		if (Math.floor(this.state/10) == 1) {
			return "���";
		}
		if (Math.floor(this.state/10) == 2) {
			return "����";
		}
		if (Math.floor(this.state/10) == 3) {
			return "����";
		}
		if (Math.floor(this.state/10) == 4) {
			return "�ֹ�";
		}
		return "";
	}
	List<Item> current_list() {
		if (Math.floor(this.state/10) == 1) {
			return this.ingredient_list;
		}
		if (Math.floor(this.state/10) == 2) {
			return this.mold_list;
		}
		if (Math.floor(this.state/10) == 3) {
			return this.preset_list;
		}
		if (Math.floor(this.state/10) == 4) {
			return this.order_list;
		}
		return null;
	}
	void show_list() {
		if (!(this.state == 10 || this.state == 20 || this.state == 30 || this.state == 40)) {
			return;
		}
		System.out.printf("<%s ���>\n", this.menu_name());
		for (int i=0; i<this.current_list().size(); i++) {
			System.out.printf("%2d. %s\n", i+1, this.current_list().get(i).info());
		}
		System.out.printf("\n<��ȣ: ��ȸ> <A: �߰�> <B: �ڷ�>: ");
		this.input1 = this.sc.nextLine();
		System.out.printf("\n");
		if (this.input1.matches("\\d+")) {
			this.state = this.state + 1;
			return;
		}
		if (this.input1.equals("A") || this.input1.equals("a")) {
			this.state = this.state + 2;
			return;
		}
		this.state = 1;
		return;
	}
	void show_detail() {
		if (!(this.state == 11 || this.state == 21 || this.state == 31 || this.state == 41)) {
			return;
		}
		System.out.printf("<%s ����>\n", this.menu_name());
		int index = Integer.parseInt(this.input1) - 1;
		System.out.printf(this.current_list().get(index).detail());
		if (this.state == 11) {
			System.out.printf("\n<A: �ֹ�> <X: ����> <B: �ڷ�>: ");
		}
		if (this.state == 21) {
			System.out.printf("\n<A: ��ü> <X: ����> <B: �ڷ�>: ");
		}
		if (this.state == 31) {
			System.out.printf("\n<X: ����> <B: �ڷ�>: ");
		}
		if (this.state == 41) {
			System.out.printf("\n<A: ó��> <X: ����> <B: �ڷ�>: ");
		}
		this.input1 = this.sc.nextLine();
		System.out.printf("\n");
		if (this.input1.equals("A") || this.input1.equals("a")) {
			if (this.state == 11) {
				System.out.printf("�ֹ��� ������ �Է��ϼ���. <����: ����> <B: ���>: ");
				this.input2 = this.sc.nextLine();
				if (this.input2.matches("\\d+")) {
					System.out.printf("��Ḧ �ֹ��Ͽ����ϴ�.\n");
				}
				System.out.printf("\n");
			}
			if (this.state == 21) {
				System.out.printf("������ ��ü�Ͻðڽ��ϱ�? <Y: ��> <N: �ƴϿ�>: ");
				this.input2 = this.sc.nextLine();
				if (this.input2 == "Y") {
					System.out.printf("������ ��ü�Ͽ����ϴ�.\n");
				}
				System.out.printf("\n");
			}
			if (this.state == 41) {
				System.out.printf("�ֹ��� ó���Ͻðڽ��ϱ�? <Y: ��> <N: �ƴϿ�>: ");
				this.input2 = this.sc.nextLine();
				if (this.input2 == "Y") {
					System.out.printf("�ֹ��� ó���Ͽ����ϴ�.\n");
				}
				System.out.printf("\n");
			}
		}
		if (this.input1.equals("X") || this.input1.equals("x")) {
			System.out.printf("�����Ͻðڽ��ϱ�? <Y: ��> <N: �ƴϿ�>: ");
			this.input2 = this.sc.nextLine();
			if (this.input2.equals("Y") || this.input2.equals("y")) {
				this.current_list().remove(index);
				System.out.printf("�����Ͽ����ϴ�.\n");
			}
			System.out.printf("\n");
		}
		this.state = this.state - 1;
	}
	void show_add() {
		if (!(this.state == 12 || this.state == 22 || this.state == 32 || this.state == 42)) {
			return;
		}
		System.out.printf("<%s �߰�>\n", this.menu_name());
		if (this.state == 12) {
			System.out.printf("�̸�: ");
			this.input2 = this.sc.nextLine();
			String name = this.input2;
			
			System.out.printf("�ڵ�: ");
			this.input2 = this.sc.nextLine();
			String code = this.input2;
			
			System.out.printf("���� ���� (��): ");
			this.input2 = this.sc.nextLine();
			double price = Double.parseDouble(this.input2);
			
			System.out.printf("���� ���� (Kcal): ");
			this.input2 = this.sc.nextLine();
			double kcal = Double.parseDouble(this.input2);
			
			System.out.printf("�ʿ� �ð� (���): ");
			this.input2 = this.sc.nextLine();
			double multiplier = Double.parseDouble(this.input2);
			
			System.out.printf("ģȯ�� ���� <Y: ��> <N: �ƴϿ�>: ");
			this.input2 = this.sc.nextLine();
			boolean isgreen = false;
			if (this.input2.equals("Y") || this.input2.equals("y")) {
				isgreen = true;
			}
			
			this.current_list().add(new Ingredient(name, code, price, kcal, multiplier, isgreen));
			System.out.printf("��Ḧ �߰��Ͽ����ϴ�.\n\n");
		}
		if (this.state == 22) {
			System.out.printf("�̸�: ");
			this.input2 = this.sc.nextLine();
			String name = this.input2;
			
			System.out.printf("�ڵ�: ");
			this.input2 = this.sc.nextLine();
			String code = this.input2;
			
			System.out.printf("���: ");
			this.input2 = this.sc.nextLine();
			String shape = this.input2;
			
			System.out.printf("����: ");
			this.input2 = this.sc.nextLine();
			double area = Double.parseDouble(this.input2);
			
			System.out.printf("����: ");
			this.input2 = this.sc.nextLine();
			double height = Double.parseDouble(this.input2);
			
			System.out.printf("�ִ� �ʺ�: ");
			this.input2 = this.sc.nextLine();
			double width = Double.parseDouble(this.input2);
			
			System.out.printf("�ʿ� �ð� (��): ");
			this.input2 = this.sc.nextLine();
			double time = Double.parseDouble(this.input2);
			
			this.current_list().add(new Mold(name, code, shape, area, height, width, time));
			System.out.printf("������ �߰��Ͽ����ϴ�.\n\n");
		}
		if (this.state == 32) {
			Cake cake = new Cake();
			
			for (int i=0; i<this.mold_list.size(); i++) {
				System.out.printf("%2d. %s\n", i+1, this.mold_list.get(i).info());
			}
			System.out.printf("Ʋ <��ȣ: ����>: ");
			this.input2 = this.sc.nextLine();
			System.out.printf("\n");
			cake.select_mold((Mold)this.mold_list.get(Integer.parseInt(this.input2)-1));
			
			for (int i=0; i<this.ingredient_list.size(); i++) {
				System.out.printf("%2d. %s\n", i+1, this.ingredient_list.get(i).info());
			}
			System.out.printf("���̽� <��ȣ: ����>: ");
			this.input2 = this.sc.nextLine();
			System.out.printf("\n");
			cake.select_base((Ingredient)this.ingredient_list.get(Integer.parseInt(this.input2)-1));
			
			for (int i=0; i<this.ingredient_list.size(); i++) {
				System.out.printf("%2d. %s\n", i+1, this.ingredient_list.get(i).info());
			}
			System.out.printf("ũ�� <��ȣ: ����>: ");
			this.input2 = this.sc.nextLine();
			System.out.printf("\n");
			cake.select_cream((Ingredient)this.ingredient_list.get(Integer.parseInt(this.input2)-1));
			
			while (true) {
				for (int i=0; i<this.ingredient_list.size(); i++) {
					System.out.printf("%2d. %s\n", i+1, this.ingredient_list.get(i).info());
				}
				System.out.printf("��� <��ȣ: ����> <A: �Ϸ�>: ");
				this.input2 = this.sc.nextLine();
				System.out.printf("\n");
				if (!this.input2.matches("\\d+")) {
					break;
				}
				cake.add_decoration((Ingredient)this.ingredient_list.get(Integer.parseInt(this.input2)-1));
			}
			
			System.out.printf("�̸�: ");
			this.input2 = this.sc.nextLine();
			System.out.printf("\n");
			cake.change_name(this.input2);
			
			this.current_list().add(cake);
			System.out.printf("������ �߰��Ͽ����ϴ�.\n\n");
		}
		if (this.state == 42) {
			Cake cake = new Cake();
			
			for (int i=0; i<this.mold_list.size(); i++) {
				System.out.printf("%2d. %s\n", i+1, this.mold_list.get(i).info());
			}
			System.out.printf("Ʋ <��ȣ: ����>: ");
			this.input2 = this.sc.nextLine();
			System.out.printf("\n");
			cake.select_mold((Mold)this.mold_list.get(Integer.parseInt(this.input2)-1));
			
			for (int i=0; i<this.ingredient_list.size(); i++) {
				System.out.printf("%2d. %s\n", i+1, this.ingredient_list.get(i).info());
			}
			System.out.printf("���̽� <��ȣ: ����>: ");
			this.input2 = this.sc.nextLine();
			System.out.printf("\n");
			cake.select_base((Ingredient)this.ingredient_list.get(Integer.parseInt(this.input2)-1));
			
			for (int i=0; i<this.ingredient_list.size(); i++) {
				System.out.printf("%2d. %s\n", i+1, this.ingredient_list.get(i).info());
			}
			System.out.printf("ũ�� <��ȣ: ����>: ");
			this.input2 = this.sc.nextLine();
			System.out.printf("\n");
			cake.select_cream((Ingredient)this.ingredient_list.get(Integer.parseInt(this.input2)-1));
			
			while (true) {
				for (int i=0; i<this.ingredient_list.size(); i++) {
					System.out.printf("%2d. %s\n", i+1, this.ingredient_list.get(i).info());
				}
				System.out.printf("��� <��ȣ: ����> <A: �Ϸ�>: ");
				this.input2 = this.sc.nextLine();
				System.out.printf("\n");
				if (!this.input2.matches("\\d+")) {
					break;
				}
				cake.add_decoration((Ingredient)this.ingredient_list.get(Integer.parseInt(this.input2)-1));
			}
			
			System.out.printf("�̸�: ");
			this.input2 = this.sc.nextLine();
			System.out.printf("\n");
			cake.change_name(this.input2);
			
			this.current_list().add(cake);
			System.out.printf("�ֹ��� �߰��Ͽ����ϴ�.\n\n");
		}
		this.state = this.state - 2;
	}
	
	public static void main(String[] args) {
		Service s = new Service();
		s.run();
	}
}
