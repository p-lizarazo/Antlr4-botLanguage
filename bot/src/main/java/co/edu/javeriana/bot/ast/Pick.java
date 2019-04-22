package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.Bot;

import co.edu.javeriana.bot.scope.Context;

public class Pick implements ASTNode {

	private Bot bot;
	
	
	public Pick(Bot bot) {
		super();
		this.bot = bot;
	}

	@Override
	public Object execute(Context my_context) {
		bot.pick();
		return null;
	}



}
