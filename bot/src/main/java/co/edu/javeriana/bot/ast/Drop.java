package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.Bot;

import co.edu.javeriana.bot.scope.Context;

public class Drop implements ASTNode {

	private Bot bot;
	
	
	public Drop(Bot bot) {
		super();
		this.bot = bot;
	}

	@Override
	public Object execute(Context my_context) {
		bot.drop();
		return null;
	}


}