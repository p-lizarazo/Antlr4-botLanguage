package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class Not implements ASTNode {
	private ASTNode operand;
	
	public Not(ASTNode operand) {
		super();
		this.operand = operand;
	}

	@Override
	public Object execute(Context my_context) {
		return !(boolean)operand.execute(my_context);
	}



}
