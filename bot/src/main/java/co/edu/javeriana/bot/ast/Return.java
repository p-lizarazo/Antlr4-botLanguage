package co.edu.javeriana.bot.ast;

import co.edu.javeriana.bot.scope.Context;

public class Return implements ASTNode {
	private ASTNode element;
	
	public Return(ASTNode element) {
		super();
		this.element = element;
	}
	
	public Return() {
		super();
		this.element = null;
	}



	@Override
	public Object execute(Context my_context) {
		if(this.element==null) {
			return "null";
		}
		Object ret = element.execute(my_context);
		if(ret == null) return "null";
		return ret;
	}

}
