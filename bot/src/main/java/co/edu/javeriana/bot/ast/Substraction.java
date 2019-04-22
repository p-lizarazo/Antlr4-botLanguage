package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class Substraction implements ASTNode {
	private ASTNode operand1;
	private ASTNode operand2;
	
	public Substraction(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	@Override
	public Object execute(Context my_context) {
		return (int)operand1.execute(my_context) - (int)operand2.execute(my_context);
	}



}
