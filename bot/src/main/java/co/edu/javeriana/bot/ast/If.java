package co.edu.javeriana.bot.ast;

import java.util.List;

import co.edu.javeriana.bot.scope.Context;

public class If implements ASTNode {

	private ASTNode condition;
	private List<ASTNode> body;
	private List<ASTNode> elseBody;
	
	public If(ASTNode condition, List<ASTNode> body, List<ASTNode> elseBody) {
		super();
		this.condition = condition;
		this.body = body;
		this.elseBody = elseBody;
	}
	
	@Override
	public Object execute(Context my_context) {
		Context local_context = new Context(my_context);
		if((boolean)condition.execute(my_context)) {
			for(ASTNode n:body) {
				if(n instanceof Return) {
					return n.execute(local_context);
				}
				Object task = n.execute(local_context);
				if(task!=null) {
					return task;
				};
			}
		} else {
			for(ASTNode n:elseBody) {
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
