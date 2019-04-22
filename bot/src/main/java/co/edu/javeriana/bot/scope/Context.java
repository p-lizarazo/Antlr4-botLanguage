package co.edu.javeriana.bot.scope;

import java.util.Map;
import java.util.HashMap;

public class Context {
	private Map<String, Object> symbolTable;
	protected Context dad = null;
	
	public Context(Context dad) {
		symbolTable = new HashMap<String,Object>();
		this.dad = dad;
	}
	
	public Context() {
		symbolTable = new HashMap<String,Object>();
	}
	
	public void put(String name, Object value) {
		symbolTable.put(name, value);
	}
	
	public Object get(String name) {
		for(Context e = this; e!=null; e = e.dad) {
			Object found = (Object) (e.symbolTable.get(name));
			if(found!=null)
				return found;
		}
		return null;
	}
	
	public void replace(String name, Object value) {
		for(Context e = this; e!=null; e = e.dad) {
			if(e.symbolTable.containsKey(name)) {
				e.symbolTable.put(name, value);
				break;
			}
		}
	}
	
	
}
