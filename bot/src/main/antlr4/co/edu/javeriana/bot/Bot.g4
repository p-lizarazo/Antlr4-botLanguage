grammar Bot;

@header {

import org.jpavlich.bot.*;
import co.edu.javeriana.bot.scope.Context;
import co.edu.javeriana.bot.ast.*;
import java.util.*;

}

@parser::members {

	private Bot bot;
	
	public BotParser(TokenStream input, Bot bot) {
	    this(input);
	    this.bot = bot;
	}

}


 
program
:
	{
		List<ASTNode> body = new ArrayList<ASTNode>();
		Context my_context = new Context();
	}
    (sentence {body.add($sentence.node);})*
    {
    	for(ASTNode n : body ){
    		n.execute(my_context);
    	}
    }
;

sentence returns [ASTNode node]
:
    movement {$node = $movement.node;}
    | drop {$node = $drop.node;}
    | pick {$node = $pick.node;}
    | look {$node = $look.node;}
    | writeln {$node = $writeln.node;}
    | write {$node = $write.node;}
    | read {$node = $read.node;}
    | var_decl {$node = $var_decl.node;}
    | var_assign {$node = $var_assign.node;}
    | var_declassign {$node = $var_declassign.node;}
    | conditional {$node = $conditional.node;}
    | while_loop {$node = $while_loop.node;}
    | function {$node = $function.node;}
    | function_call {$node = $function_call.node;}
    | returnval {$node = $returnval.node;}
;

returnval returns [ASTNode node]
:
	RETURN SEMI {$node = new Return();}
	| RETURN expression SEMI { $node = new Return($expression.node);}
;

function returns [ASTNode node]
:
	(
	{
		List<ASTNode> body = new ArrayList<ASTNode>();
		List<String> parameters = new ArrayList<String>();
	}
	 
		FUNCTION t1=ID PAR_OPEN (LET t2=ID{parameters.add($t2.text);})? PAR_CLOSE BEGIN
		(sentence{body.add($sentence.node);})*
		END SEMI
		{ $node = new Function($t1.text,parameters,body);} 
	)
	| 
	(
		{
			List<ASTNode> body = new ArrayList<ASTNode>();
			List<String> parameters = new ArrayList<String>();
		}
		FUNCTION t1=ID PAR_OPEN ( (LET t2=ID{parameters.add($t2.text);} COMMA)*(LET t3=ID{parameters.add($t3.text);})) PAR_CLOSE BEGIN
		(sentence{body.add($sentence.node);})* 
		END SEMI 
		{ $node = new Function($t1.text,parameters,body); }
	)
;

function_call returns [ASTNode node]
:
	{
		List<ASTNode> parameters = new ArrayList<ASTNode>();
	}
	ID PAR_OPEN (expression{parameters.add($expression.node);})? PAR_CLOSE SEMI
	{$node = new FunctionCall($ID.text,parameters); } 
	|
	{
		List<ASTNode> parameters = new ArrayList<ASTNode>();
	}
	 ID PAR_OPEN (t1=expression{parameters.add($t1.node);} COMMA)*(t2=expression{parameters.add($t2.node);}) PAR_CLOSE SEMI
	{$node = new FunctionCall($ID.text,parameters); }
;

function_assign returns [ASTNode node]
:
	{
		List<ASTNode> parameters = new ArrayList<ASTNode>();
	}
	ID PAR_OPEN (expression{parameters.add($expression.node);})? PAR_CLOSE
	{$node = new FunctionCall($ID.text,parameters); } 
	|
	{
		List<ASTNode> parameters = new ArrayList<ASTNode>();
	}
	 ID PAR_OPEN (t1=expression{parameters.add($t1.node);} COMMA)*(t2=expression{parameters.add($t2.node);}) PAR_CLOSE
	{$node = new FunctionCall($ID.text,parameters); }
;


var_decl returns [ASTNode node]
:
	LET ID SEMI {$node = new VarDecl($ID.text);}
;

var_assign returns [ASTNode node]
:
	ID ASSIGN expression SEMI
	{$node = new VarAssign($ID.text, $expression.node);}
;

var_declassign returns [ASTNode node]
:
	LET ID ASSIGN expression SEMI 
	{$node = new VarDeclAssign($ID.text, $expression.node);
	}
;



expression returns [ASTNode node]
:
	arithm_exp {$node = $arithm_exp.node;}
	| boolean_exp {$node = $boolean_exp.node;}
	| function_assign {$node = $function_assign.node;}
;



arithm_exp returns [ASTNode node]
:
	factor {$node = $factor.node;}
	| t1=arithm_exp PLUS t2=arithm_exp {$node=new Addition($t1.node,$t2.node);}
	| t3=arithm_exp MINUS t4=arithm_exp {$node= new Minus($t3.node,$t4.node);}
;


factor returns [ASTNode node]
:
	additive_inverse {$node=$additive_inverse.node;}
	| t1=factor MULT t2=factor {$node=new Multiplication($t1.node,$t2.node);}
	| t3=factor DIV t4=factor {$node=new Division($t3.node,$t4.node);}
	/* 
	t1=term{$node = $t1.node;} ( (MULT) t2=term {$node = new Multiplication($node,$t2.node); } )* 
	| t3=term{$node = $t3.node;} ( (DIV) t4=term {$node = new Division($node,$t4.node);} )*
	*/
;

additive_inverse returns [ASTNode node]
:
	term {$node=$term.node;}
	| MINUS term {$node=new AdditiveInverse($term.node);}
;


boolean_exp returns [ASTNode node]
:
	and {$node = $and.node;}
	| t1=boolean_exp OR t2=boolean_exp {$node = new Or($t1.node,$t2.node);}
//	| t1=arithm_exp LT t2=arithm_exp {$node=new Less($t1.node,$t2.node);}
	//lo que tenga que ir xd
;

and returns [ASTNode node]
:
	not {$node=$not.node;}
	| t1=and AND t2=and {$node=new And($t1.node,$t2.node);}
;

not returns [ASTNode node]
:
	comparation {$node=$comparation.node;}
	|NOT comparation {$node=new Not($comparation.node);}
;

string_exp returns [ASTNode node]
:
	//lo que tenga que ir xd
;

writeln returns [ASTNode node]:
	WRITELN expression SEMI
	{$node = new Writeln($expression.node); }
;
write returns [ASTNode node]:
	WRITE expression SEMI
	{$node = new Write($expression.node); }
;

read returns [ASTNode node]:
	READ ID SEMI
	{$node = new Read($ID.text);}
;

while_loop returns [ASTNode node]:
	WHILE PAR_OPEN boolean_exp PAR_CLOSE
	{
		List<ASTNode> body = new ArrayList<ASTNode>();
	}
	BEGIN
	(sentence {body.add($sentence.node);})*
	END SEMI
	{
		$node = new WhileLoop($boolean_exp.node, body);
	}
;

conditional returns [ASTNode node]:
	IF PAR_OPEN expression PAR_CLOSE 
	{
		List<ASTNode> body = new ArrayList<ASTNode>();
	}
	BEGIN  
		(s1=sentence {body.add($s1.node);})*
	END
	{
		List<ASTNode> elseBody = new ArrayList<ASTNode>();
	}
	( 
	ELSE 
	
	BEGIN 
		(s2=sentence {elseBody.add($s2.node);})*
	END
	)? 
	{
		$node = new If($expression.node, body, elseBody);
	}
	SEMI
;

movement returns [ASTNode node]:
   (moveup{$node = $moveup.node;} | moveleft{$node = $moveleft.node;} | moveright{$node = $moveright.node;} | movedown{$node = $movedown.node;})
	SEMI
;

pick returns [ASTNode node]:
	PICK SEMI
	{ $node = new Pick(bot); }
;

drop returns [ASTNode node]:
	DROP SEMI
 	{ $node = new Drop(bot) ;}
;

look returns [ASTNode node]:
	LOOK SEMI
	{ $node = new Look(bot); }
;


moveup returns [ASTNode node]:
	UP expression
	{ $node = new MoveUp($expression.node,bot); }
;

moveleft returns [ASTNode node]:
	LEFT expression
	{ $node = new MoveLeft($expression.node,bot); }
;

moveright returns [ASTNode node]:
	RIGHT expression
	{ $node = new MoveRight($expression.node,bot); }
;

movedown returns [ASTNode node]:
	DOWN expression
	{ $node = new MoveDown($expression.node,bot); }
;

comparation returns [ASTNode node]:
	arithm_exp {$node = $arithm_exp.node;}
	| t1=comparation GT t2=comparation {$node = new Greater($t1.node,$t2.node);}
	| t1=comparation LT t2=comparation {$node = new Less($t1.node,$t2.node);}
	| t1=comparation GTEQ t2=comparation {$node = new GreaterEq($t1.node,$t2.node);}
	| t1=comparation LTEQ t2=comparation {$node = new LessEq($t1.node,$t2.node);}
	| t1=comparation EQ t2=comparation {$node = new Equal($t1.node,$t2.node);}
	| t1=comparation DIFF t2=comparation {$node = new Different($t1.node,$t2.node);}
;

term returns [ASTNode node]:
	NUMBER {$node = new Constant(Double.parseDouble($NUMBER.text));}
	| BOOLEAN {$node = new Constant(Boolean.parseBoolean($BOOLEAN.text));}
	| STRING {$node = new Constant(String.valueOf($STRING.text).replace("\"","") );}
	| PAR_OPEN expression {$node = $expression.node;} PAR_CLOSE
	| ID {$node = new VarRef($ID.text);}
	| function_assign {$node = $function_assign.node;}
;



// Los tokens se escriben a continuación de estos comentarios.
// Todo lo que esté en líneas previas a lo modificaremos cuando hayamos visto Análisis Sintáctico

UP: 'up';
DOWN: 'down';
LEFT: 'left';
RIGHT: 'right';
PICK: 'pick';
DROP: 'drop';
LOOK: 'look';

WRITELN: 'writeln';
WRITE: 'write';
READ: 'read';

FUNCTION: 'function';
RETURN: 'return';

IF : 'if';
ELSE: 'else';

WHILE: 'while';

BEGIN: 'begin';
END: 'end';
LET : 'let';

PAR_OPEN : '(';
PAR_CLOSE : ')';
SEMI: ';';
COMMA: ',';
ASSIGN : '=';

PLUS : '+';
MINUS : '-';
MULT: '*';
DIV: '/';

GT: '>';
LT: '<';
GTEQ: '>=';
LTEQ: '<=';
DIFF: '<>';
EQ: '==';

AND: 'and';
OR: 'or';
NOT: 'not';

BOOLEAN: 'true' | 'false';
STRING: '"' ~('"')* '"';
NUMBER: [0-9]+('.'[0-9]+)?;
ID: [a-zA-Z_][a-zA-Z0-9_]*;


WS
:
    [ \t\r\n]+ -> skip
;