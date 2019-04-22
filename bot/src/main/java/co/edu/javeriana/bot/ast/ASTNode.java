package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public interface ASTNode {
	public Object execute(Context my_context);
}
