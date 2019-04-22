package co.edu.javeriana.bot.ast;

import org.jpavlich.bot.Bot;

import co.edu.javeriana.bot.scope.Context;

public class Look implements ASTNode {
	
	private Bot bot;
	
	
	public Look(Bot bot) {
		super();
		this.bot = bot;
	}

	@Override
	public Object execute(Context my_context) {
		bot.look();
		return null;
	}


}
