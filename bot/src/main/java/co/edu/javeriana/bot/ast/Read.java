package co.edu.javeriana.bot.ast;

import java.util.Scanner;

import co.edu.javeriana.bot.scope.Context;

public class Read implements ASTNode {
	
	private String name;
	
	public Read(String name) {
		super();
		this.name = name;
	}
	
	@Override
	public Object execute(Context my_context) {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		my_context.put(name, s);
		in.close();
		return null;
	}



}
