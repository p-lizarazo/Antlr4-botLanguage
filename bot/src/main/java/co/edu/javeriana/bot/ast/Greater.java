package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class Greater implements ASTNode {
	private ASTNode operand1;
	private ASTNode operand2;
	
	public Greater(ASTNode operand1, ASTNode operand2) {
		super();
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	@Override
	public Object execute(Context my_context) {
		Object op1,op2;
		op1 = operand1.execute(my_context); op2 = operand2.execute(my_context);
		
		if(op1 instanceof String && op2 instanceof String){
			String s = (String) op1;
			int res =  s.compareTo((String) op2);
			return res > 0;
		} else if(op1 instanceof Double && op2 instanceof Double) {
			return (Double)operand1.execute(my_context) > (Double)operand2.execute(my_context);
		} else {
			return null;
		}
		
		
	}



}
