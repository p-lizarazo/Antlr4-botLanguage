package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class Constant implements ASTNode {
	
	private Object value;
	
	public Constant(Object value) {
		super();
		this.value = value;
	}

	@Override
	public Object execute(Context my_context) {
		return value;
	}


}
