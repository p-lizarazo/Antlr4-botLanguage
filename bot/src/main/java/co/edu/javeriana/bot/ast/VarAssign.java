package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class VarAssign implements ASTNode {
	
	private String name;
	private ASTNode expression;
	
	
	
	public VarAssign(String name, ASTNode expression) {
		super();
		this.name = name;
		this.expression = expression;
	}



	@Override
	public Object execute(Context my_context) {
		my_context.replace(name, expression.execute(my_context));
		return null;
	}



}
