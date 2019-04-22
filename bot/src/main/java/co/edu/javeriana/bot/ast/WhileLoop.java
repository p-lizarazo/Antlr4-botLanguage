package co.edu.javeriana.bot.ast;

import java.util.List;

import co.edu.javeriana.bot.scope.Context;

public class WhileLoop implements ASTNode {

	private ASTNode condition;
	private List<ASTNode> body;
	
	public WhileLoop(ASTNode condition, List<ASTNode> body) {
		super();
		this.condition = condition;
		this.body = body;
	}



	@Override
	public Object execute(Context my_context) {
		
		
		while((boolean)condition.execute(my_context)) {
			Context local_context = new Context(my_context);
			for(ASTNode n:body) {
				if(n instanceof Return) {
					return n.execute(local_context);
				}
				Object task = n.execute(local_context);
				if(task!=null) {
					return task;
				};
			}
		}
		return null;
	}



}
