package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.Bot;

import co.edu.javeriana.bot.scope.Context;

public class MoveDown implements ASTNode {
	
	private ASTNode number;
	private Bot bot;
	
	
	public MoveDown(ASTNode number, Bot bot) {
		super();
		this.bot = bot;
		this.number = number;
	}

	@Override
	public Object execute(Context my_context) {
		Double i = (Double) number.execute(my_context);
		bot.down( i.intValue() );
		return null;
	}


}
