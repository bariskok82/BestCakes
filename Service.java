package cake;

import java.util.*;

class Order {
	private Scanner sc;
	private ArrayList<Item> ingredient_list;
	private ArrayList<Double> ingredient_stock;
	private ArrayList<Item> mold_list;
	private ArrayList<Item> cake_preset;
	private int state;
	
	Order() {
		this.sc = new Scanner(System.in);
		this.ingredient_list = new ArrayList<Item>();
		this.ingredient_stock = new ArrayList<Double>();
		this.mold_list = new ArrayList<Item>();
		this.cake_preset = new ArrayList<Item>();
		this.state = 1;
	}
	void main_menu() {
		System.out.printf("1: ��� �غ�\n2: ���� �غ�\n3: ���� �غ�\n4: �ֹ� �ޱ�\n5: ����\n�޴��� �����ϼ���: ");
		String input1 = this.sc.nextLine();
		System.out.printf("\n");
		if (input1.equals("1")) {
			this.state = 10;
		}
		if (input1.equals("2")) {
			this.state = 20;
		}
		if (input1.equals("3")) {
			this.state = 30;
		}
		if (input1.equals("4")) {
			this.state = 40;
		}
		if (input1.equals("5")) {
			this.state = 99;
		}
	}
	void prepare_ingredient() {
		System.out.printf(show_info("<��� ���>\n", ingredient_list));
		System.out.printf("\n<��ȣ: ��ȸ> <A: �߰�> <B: �ڷ�>\n�Է�: ");
		String input1 = this.sc.nextLine();
		System.out.printf("\n");
		if (input1.equals("A")) {
			this.state = 12;
		}
		if (input1.equals("B")) {
			this.state = 1;
		}
	}
	void prepare_tool() {
		System.out.printf("<���� �غ� �޴�>\n1: ����\n2: �߰�\n3: ����\n4: ��ü\n5: �ڷ�\n�޴��� �����ϼ���: ");
		String input1 = this.sc.nextLine();
		System.out.printf("\n");
		if (input1.equals("1")) {
			this.state = 21;
		}
		if (input1.equals("2")) {
			this.state = 22;
		}
		if (input1.equals("3")) {
			this.state = 23;
		}
		if (input1.equals("4")) {
			this.state = 24;
		}
		if (input1.equals("5")) {
			this.state = 1;
		}
	}
	void prepare_preset() {
		System.out.printf("<���� �غ� �޴�>\n1: ����\n2: �߰�\n3: ����\n4: �ڷ�\n�޴��� �����ϼ���: ");
		String input1 = this.sc.nextLine();
		System.out.printf("\n");
		if (input1.equals("1")) {
			this.state = 31;
		}
		if (input1.equals("2")) {
			this.state = 32;
		}
		if (input1.equals("3")) {
			this.state = 33;
		}
		if (input1.equals("4")) {
			this.state = 1;
		}
	}
	void ask_order() {
		System.out.printf("<�ֹ� �ޱ� �޴�>\n1: ����\n2: �ޱ�\n3: ó��\n4: �ڷ�\n�޴��� �����ϼ���: ");
		String input1 = this.sc.nextLine();
		System.out.printf("\n");
		if (input1.equals("1")) {
			this.state = 41;
		}
		if (input1.equals("2")) {
			this.state = 42;
		}
		if (input1.equals("3")) {
			this.state = 43;
		}
		if (input1.equals("4")) {
			this.state = 1;
		}
	}
	String show_info(String title, ArrayList<Item> list) {
		String output = title;
		for (int i=0; i<list.size(); i++) {
			output += String.format("%2d. %s\n", i+1, list.get(i).info());
		}
		return output;
	}
}
