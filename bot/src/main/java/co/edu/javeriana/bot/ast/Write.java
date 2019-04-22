package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class Write implements ASTNode {
	private ASTNode data;
	
	public Write(ASTNode data) {
		super();
		this.data = data;
	}

	@Override
	public Object execute(Context my_context) {
		System.out.print(data.execute(my_context));
		return null;
	}

}
