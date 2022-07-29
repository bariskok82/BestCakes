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
		System.out.printf("1: 재료 준비\n2: 도구 준비\n3: 제안 준비\n4: 주문 받기\n5: 종료\n메뉴를 선택하세요: ");
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
		System.out.printf(show_info("<재료 목록>\n", ingredient_list));
		System.out.printf("\n<번호: 조회> <A: 추가> <B: 뒤로>\n입력: ");
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
		System.out.printf("<도구 준비 메뉴>\n1: 보기\n2: 추가\n3: 삭제\n4: 교체\n5: 뒤로\n메뉴를 선택하세요: ");
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
		System.out.printf("<제안 준비 메뉴>\n1: 보기\n2: 추가\n3: 삭제\n4: 뒤로\n메뉴를 선택하세요: ");
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
		System.out.printf("<주문 받기 메뉴>\n1: 보기\n2: 받기\n3: 처리\n4: 뒤로\n메뉴를 선택하세요: ");
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
