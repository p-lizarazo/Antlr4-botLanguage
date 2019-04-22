package co.edu.javeriana.bot.ast;


import co.edu.javeriana.bot.scope.Context;
import java.util.List;

public class FunctionCall implements ASTNode {
	
	private String name;
	private List<ASTNode> parameters; 
	
	public FunctionCall(String name, List<ASTNode> parameters) {
		super();
		this.name = name;
		this.parameters = parameters;
	}

	@Override
	public Object execute(Context my_context) {
		
		Function func = (Function)my_context.get(name);
		
		Context local_context = new Context(func.getMy_context());
		
		List<String> arguments_list = func.getArguments();
			
		for(int i=0;i<arguments_list.size();++i) {
			local_context.put(arguments_list.get(i) ,parameters.get(i).execute(my_context) );
		}
		
		for(ASTNode n:func.getBody()) {
			if(n instanceof Return) {
				return n.execute(local_context);
			}
			Object task = n.execute(local_context);
			if(task!=null) {
				return task;
			};
		}
		
		return null;
	}


}
