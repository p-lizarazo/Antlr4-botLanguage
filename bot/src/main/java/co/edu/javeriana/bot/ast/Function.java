package co.edu.javeriana.bot.ast;

import java.util.List;

import co.edu.javeriana.bot.scope.Context;

public class Function implements ASTNode {
	
	private String name;
	private List<String> arguments;
	private Context my_context;
	private List<ASTNode> body;

	
	
	public Function(String name, List<String> arguments, List<ASTNode> body) {
		super();
		this.name = name;
		this.arguments = arguments;
		this.body = body;
	}

	

	public String getName() {
		return name;
	}



	public List<String> getArguments() {
		return arguments;
	}



	public Context getMy_context() {
		return my_context;
	}



	public List<ASTNode> getBody() {
		return body;
	}



	@Override
	public Object execute(Context my_context) {
		this.my_context = my_context;
		this.my_context.put(name, this);
		
		return null;
	}

}
