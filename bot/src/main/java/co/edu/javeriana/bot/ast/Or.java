package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class Or implements ASTNode {
	private ASTNode operand1;
	private ASTNode operand2;
	
	public Or(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	@Override
	public Object execute(Context my_context) {
		return (boolean)operand1.execute(my_context) || (boolean)operand2.execute(my_context);
	}


}
