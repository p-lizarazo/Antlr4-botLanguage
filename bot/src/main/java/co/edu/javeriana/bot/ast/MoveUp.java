package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.Bot;

import co.edu.javeriana.bot.scope.Context;

public class MoveUp implements ASTNode {
	
	private ASTNode number;
	private Bot bot;
	
	
	public MoveUp(ASTNode number, Bot bot) {
		super();
		this.bot = bot;
		this.number = number;
	}

	@Override
	public Object execute(Context my_context) {
		Double i = (Double) number.execute(my_context);
		bot.up( i.intValue() );
		return null;
	}



}
