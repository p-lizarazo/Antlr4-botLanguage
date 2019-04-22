package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class AdditiveInverse implements ASTNode {
	private ASTNode operand;
	
	public AdditiveInverse(ASTNode operand) {
		super();
		this.operand = operand;
	}

	@Override
	public Object execute(Context my_context) {
		return (Double)operand.execute(my_context)*-1;
	}



}
