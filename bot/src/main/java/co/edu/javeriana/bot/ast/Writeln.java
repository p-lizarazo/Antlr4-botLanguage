package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class Writeln implements ASTNode {
	private ASTNode data;
	
	public Writeln(ASTNode data) {
		super();
		this.data = data;
	}

	@Override
	public Object execute(Context my_context) {
		System.out.println(data.execute(my_context));
		return null;
	}


}
