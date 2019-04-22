package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class Multiplication implements ASTNode {
	private ASTNode operand1;
	private ASTNode operand2;
	
	public Multiplication(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	@Override
	public Object execute(Context my_context) {
		return (Double)operand1.execute(my_context) * (Double)operand2.execute(my_context);
	}



}
