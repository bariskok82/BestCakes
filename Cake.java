package cake;

import java.util.*;

abstract class Item {
	protected String name;
	protected String code;
	
	Item(String name, String code) {
		this.name = name;
		this.code = code;
	}
	
	String get_name() {
		return this.name;
	}
	String get_code() {
		return this.code;
	}
	abstract String info();
	abstract String detail();
}

class Ingredient extends Item {
	private double unit_price;
	private double unit_kcal;
	private double time_multiplier;
	private boolean is_green;
	
	Ingredient(String name, String code, double unit_price, double unit_kcal, double time_multiplier, boolean is_green) {
		super(name, code);
		this.unit_price = unit_price;
		this.unit_kcal = unit_kcal;
		this.time_multiplier = time_multiplier;
		this.is_green = is_green;
	}
	Ingredient(String name, String code, double unit_price, double unit_kcal, double time_multiplier) {
		this(name, code, unit_price, unit_kcal, time_multiplier, false);
	}

	double get_unit_price() {
		return this.unit_price;
	}
	double get_unit_kcal() {
		return this.unit_kcal;
	}
	double get_time_multiplier() {
		return this.time_multiplier;
	}
	boolean is_green() {
		return this.is_green;
	}
	String info() {
		if (this.is_green) {
			return String.format("%s (%.2f 원, %.2f Kcal, x%.2f 필요, 친환경)",
					this.name, this.unit_price, this.unit_kcal, this.time_multiplier);
		} else {
			return String.format("%s (%.2f 원, %.2f Kcal, x%.2f 필요)",
					this.name, this.unit_price, this.unit_kcal, this.time_multiplier);
		}
	}
	String detail() {
		if (this.is_green) {
			return String.format("재료 이름: %s\n단위 가격: %.2f 원\n단위 열량: %.2f Kcal\n필요 시간: x%.2f\n친환경: 예\n",
					this.name, this.unit_price, this.unit_kcal, this.time_multiplier);
		} else {
			return String.format("재료 이름: %s\n단위 가격: %.2f 원\n단위 열량: %.2f Kcal\n필요 시간: x%.2f\n친환경: 아니오\n",
					this.name, this.unit_price, this.unit_kcal, this.time_multiplier);
		}
	}
}

class Mold extends Item {
	private String shape;
	private double area;
	private double width;
	private double height;
	private double time;
	
	Mold(String name, String code, String shape, double area, double width, double height, double time) {
		super(name, code);
		this.shape = shape;
		this.area = area;
		this.width = width;
		this.height = height;
		this.time = time;
	}
	
	String get_shape() {
		return this.shape;
	}
	double get_area() {
		return this.area;
	}
	double get_width() {
		return this.width;
	}
	double get_height() {
		return this.height;
	}
	double get_time() {
		return this.time;
	}
	double get_volume() {
		return this.area * this.height;
	}
	String info() {
		return String.format("%s (%.2fx%.2f %s, %.2f분 필요)",
					this.name, this.area, this.height, this.shape, this.time);
	}
	String detail() {
		return String.format("틀 이름: %s\n모양: %s\n넓이: %.2f\n높이: %.2f\n최대 너비: %.2f\n필요 시간: %.2f분\n",
				this.name, this.shape, this.area, this.height, this.width, this.time);

	}
}

class Cake extends Item {
	private boolean is_complete;
	private int price;
	private double kcal;
	private int time;
	private boolean is_green;
	private Mold mold;
	private Ingredient base;
	private Ingredient cream;
	private ArrayList<Ingredient> decoration;
	
	Cake() {
		super("", "");
		this.is_complete = false;
		this.price = 0;
		this.kcal = 0;
		this.is_green = false;
		this.mold = null;
		this.base = null;
		this.cream = null;
		this.decoration = new ArrayList<Ingredient>();
	}
	
	private void update_code() {
		String output = String.format("%s%s%s_", this.base.get_code(), this.mold.get_code(), this.cream.get_code());
		for (int i=0; i<this.decoration.size(); i++) {
			output += this.decoration.get(i).get_code();
		}
		this.code = output;
	}
	private void update_price() {
		double output = this.mold.get_volume() * (this.base.get_unit_price() + this.cream.get_unit_price() * 0.2);
		for (int i=0; i<this.decoration.size(); i++) {
			output += this.decoration.get(i).get_unit_price();
		}
		this.price = (int)(Math.floor(output));
	}
	private void update_kcal() {
		double output = this.mold.get_volume() * (this.base.get_unit_kcal() + this.cream.get_unit_kcal() * 0.2);
		for (int i=0; i<this.decoration.size(); i++) {
			output += this.decoration.get(i).get_unit_kcal();
		}
		this.kcal = output;
	}
	private void update_time() {
		double output = this.mold.get_time() * (this.base.get_time_multiplier() + this.cream.get_time_multiplier());
		for (int i=0; i<this.decoration.size(); i++) {
			output += this.decoration.get(i).get_time_multiplier();
		}
		this.time = (int)(Math.ceil(output));
	}
	private void update_green() {
		boolean output = this.base.is_green() && this.cream.is_green();
		for (int i=0; i<this.decoration.size(); i++) {
			output = output && this.decoration.get(i).is_green();
		}
		this.is_green = output;
	}
	private void update() {
		if (this.mold != null && this.base != null && this.cream != null) {
			this.update_code();
			this.update_price();
			this.update_kcal();
			this.update_time();
			this.update_green();
			this.is_complete = true;
		} else {
			this.is_complete = false;
		}
	}
	void change_name(String name) {
		this.name = name;
	}
	void select_mold(Mold mold) {
		this.mold = mold;
		this.update();
	}
	void select_base(Ingredient base) {
		this.base = base;
		this.update();
	}
	void select_cream(Ingredient cream) {
		this.cream = cream;
		this.update();
	}
	void add_decoration(Ingredient decoration) {
		this.decoration.add(decoration);
		this.update();
	}
	void clear_decoration() {
		this.decoration.clear();
		this.update();
	}
	String get_code() {
		this.update();
		if (this.is_complete) {
			return this.code;
		} else {
			return "_N_";
		}
	}
	String info() {
		this.update();
		if (this.is_complete) {
			if (this.is_green) {
				return String.format("%s - %s/%s/%s (%d원, %.2fKcal, %d분 필요, 친환경)",
						this.name, this.base.get_name(), this.cream.get_name(), this.mold.get_name(), this.price, this.kcal, this.time);
			} else {
				return String.format("%s - %s/%s/%s (%d원, %.2fKcal, %d분 필요)",
						this.name, this.base.get_name(), this.cream.get_name(), this.mold.get_name(), this.price, this.kcal, this.time);
			}
		} else {
			return "_(유효하지 않음)_";
		}
	}
	String detail() {
		this.update();
		if (this.is_complete) {
			String output = String.format("이름: %s\n바탕: %s\n크림: %s\n틀: %s\n",
					this.name, this.base.get_name(), this.cream.get_name(), this.mold.get_name());
			for (int i=0; i<this.decoration.size(); i++) {
				output += String.format("장식%d: %s\n", i+1, this.decoration.get(i).get_name());
			}
			output += String.format("가격: %d원\n열량: %.2fKcal\n필요 시간: %d분\n", this.price, this.kcal, this.time);
			if (this.is_green) {
				output += String.format("친환경: 예\n");
			} else {
				output += String.format("친환경: 아니오\n");
			}
			return output;
		} else {
			return "_(유효하지 않음)_\n";
		}
		
	}
}