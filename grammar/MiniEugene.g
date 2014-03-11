grammar MiniEugene;

@lexer::header {
/*
Copyright (c) 2012 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
*/

package org.cidarlab.minieugene.parser;
}

@header {
/*
Copyright (c) 2012 Boston University.
All rights reserved.
Permission is hereby granted, without written agreement and without
license or royalty fees, to use, copy, modify, and distribute this
software and its documentation for any purpose, provided that the above
copyright notice and the following two paragraphs appear in all copies
of this software.

IN NO EVENT SHALL BOSTON UNIVERSITY BE LIABLE TO ANY PARTY
FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
BOSTON UNIVERSITY HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

BOSTON UNIVERSITY SPECIFICALLY DISCLAIMS ANY WARRANTIES,
INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND BOSTON UNIVERSITY HAS
NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
ENHANCEMENTS, OR MODIFICATIONS.
*/

package org.cidarlab.minieugene.parser;

import org.cidarlab.minieugene.Interp;
import org.cidarlab.minieugene.symbol.*;
import org.cidarlab.minieugene.predicates.*;
import org.cidarlab.minieugene.exception.EugeneException;

import org.apache.commons.lang3.ArrayUtils;
}

@parser::members {

// INTERPRETER
private Interp interp;

// SYMBOL TABLES
private SymbolTables symbols;
public void init(SymbolTables symbols) {
    this.symbols = symbols;
}

// N
private int N;
public int getN() {
    return this.N;
}

// PREDICATES

private LogicalAnd la = new LogicalAnd();

private void addPredicate(Predicate p) {
    this.la.getPredicates().add(p);  
}


public LogicalAnd getPredicate() {
    return this.la;
}


// for tokenization
String[] tokens = null;
private void addToken(String token) {
    if(null != tokens) {
        tokens = ArrayUtils.add(tokens, token);
    } else {
        tokens = new String[1];
        tokens[0] = token;
    }
}

}

 	
miniEugene 
	throws EugeneException
	:	(size)? (c=or_constraint {
if($c.lst.size() == 1) {
    // ``store'' the predicate
    this.addPredicate($c.lst.get(0));   
} else {
    this.addPredicate(new LogicalOr($c.lst));   
}	
	}'.' |composite_constraint)+
	;

size 	
	throws EugeneException
	:	 'N' '=' n=INT '.' {
this.N = Integer.parseInt($n.text);
this.interp = new Interp(this.symbols, this.N);
}
	;

composite_constraint
	throws EugeneException
	:	ID ( '(' list_of_parameters ')' )? ':=' composite_constraint_block '.'
	;

composite_constraint_block
	throws EugeneException
	:	constraint (',' composite_constraint_block)?
	;	
	
or_constraint 
        returns [List<Predicate> lst]
	throws EugeneException
@init{
$lst = new ArrayList<Predicate>();
}	
	:	c=constraint {
$lst.add($c.p);
	}	(('OR'|'\\/'|'or') o=or_constraint {
$lst.addAll($o.lst);
	})?
	;
	
constraint
        returns [Predicate p]
	throws EugeneException
	:	(not='NOT' {
addToken("NOT");
	})? (lhs=operand {
addToken($lhs.text);	
	})? op=operator {
addToken($op.text);	
	} (rhs=operand {
addToken($rhs.text);	
	})? {

// turn the tokens into a miniEugene predicate	
$p = this.interp.interpreteRule(this.tokens);

// reset the global token array
this.tokens = null;
	}
	;
	catch[EugeneException e] {
throw new EugeneException(e.getMessage());	
	}

operator:
	|	('CONTAINS'|'contains')
	|	('NOTCONTAINS'|'notcontains')
	|	('EXACTLY'|'exactly')
	|	('NOTEXACTLY'|'notexactly')
	|	('MORETHAN'|'morethan')
	|	('NOTMORETHAN'|'notmorethan')
	|	('WITH'|'with')
	|	('NOTWITH'|'notwith')
	|	('THEN'|'then')
	|	('NOTTHEN'|'notthen')
	|	('STARTSWITH'|'startswith')
	|	('ENDSWITH'|'endswith')
	|	('BEFORE'|'before')
	|	('ALL_BEFORE'|'all_before')
	|	('SOME_BEFORE'|'some_before')
	|	('AFTER'|'after')
	|	('ALL_AFTER'|'all_after')
	|	('SOME_AFTER'|'some_after')
	|	('NEXTTO'|'nextto')
	|	('ALL_NEXTTO'|'all_nextto')
	|	('SOME_NEXTTO'|'some_nextto')
	|	('EQUALS'|'equals')
	|	('NOTEQUALS'|'notequals')
	|	('MATCHES'|'matches')
	|	('NOTMATCHES'|'notmatches')
	|	('FORWARD'|'forward')
	|	('ALL_FORWARD'|'all_forward')
	|	('SOME_FORWARD'|'some_forward')
	|	('REVERSE'|'reverse')
	|	('ALL_REVERSE'|'all_reverse')
	|	('SOME_REVERSE'|'some_reverse')
	|	('SAME_ORIENTATION'|'same_orientation')
	|	('REPRESSES'|'represses')
	|	('INDUCES'|'induces')
	|	('DRIVES'|'drives')
	;	
		
operand	:	ID 
	|	INT
	|	'[' INT ']'
	;

list_of_parameters
	:	operand (',' list_of_parameters)?
	;	

	
					
ID  :	('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'-')*
    ;

INT :	'0'..'9'+
    ;

				
COMMENT
    :   '//' ~('\n'|'\r')* '\r'? '\n' {$channel=HIDDEN;}
    |   '/*' ( options {greedy=false;} : . )* '*/' {$channel=HIDDEN;}
    ;

WS  :   ( ' '
        | '\t'
        | '\r'
        | '\n'
        ) {$channel=HIDDEN;}
    ;

STRING
    :  '"' ( ESC_SEQ | ~('\\'|'"') )* '"'
    ;

CHAR:  '\'' ( ESC_SEQ | ~('\''|'\\') ) '\''
    ;

fragment
EXPONENT : ('e'|'E') ('+'|'-')? ('0'..'9')+ ;

fragment
HEX_DIGIT : ('0'..'9'|'a'..'f'|'A'..'F') ;

fragment
ESC_SEQ
    :   '\\' ('b'|'t'|'n'|'f'|'r'|'\"'|'\''|'\\')
    |   UNICODE_ESC
    |   OCTAL_ESC
    ;

fragment
OCTAL_ESC
    :   '\\' ('0'..'3') ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7') ('0'..'7')
    |   '\\' ('0'..'7')
    ;

fragment
UNICODE_ESC
    :   '\\' 'u' HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
