package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class VarDecl implements ASTNode {
	
	private String name;
	
	public VarDecl(String name) {
		super();
		this.name = name;
	}

	@Override
	public Object execute(Context my_context) {
		my_context.put(name, new Object());
		return null;
	}



}
