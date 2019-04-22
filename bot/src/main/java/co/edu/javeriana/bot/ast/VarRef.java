package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class VarRef implements ASTNode {
	
	private String name;
	
	
	
	public VarRef(String name) {
		super();
		this.name = name;
	}

	@Override
	public Object execute(Context my_context) {
		return my_context.get(name);
	}



}
