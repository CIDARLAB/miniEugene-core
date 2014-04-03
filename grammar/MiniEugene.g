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
import org.cidarlab.minieugene.predicates.templating.*;

import org.apache.commons.lang3.ArrayUtils;
}

@parser::members {

// INTERPRETER
private Interp interp;

// SYMBOL TABLES
private SymbolTables symbols;
public void init(SymbolTables symbols) {
    this.symbols = symbols;
    this.interp = new Interp(this.symbols);
}

// MINIMUM LENGTH OF DESIGN
private int minN = -1;

// MAXIMUM LENGTH OF DESIGN
private int maxN = -1;

// PREDICATES
private LogicalAnd la = new LogicalAnd();

private void addPredicate(Predicate p) {
    this.la.getPredicates().add(p);  
}

public LogicalAnd getPredicate() {
    this.la.setMinN(this.minN);
    this.la.setMaxN(this.maxN);
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
	:	 ('minN' '=' minN=INT '.' {
this.minN = Integer.parseInt($minN.text);
this.interp.setMinN(this.minN);
	})? 'N' '=' maxN=INT '.' {
this.maxN = Integer.parseInt($maxN.text);
this.interp.setMaxN(this.maxN);
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
	}	(('OR'|'\\/'|'or'|'||') o=or_constraint {
$lst.addAll($o.lst);
	})?
	;
	
constraint
        returns [Predicate p]
	throws EugeneException
	:	(not=('NOT'|'not') {
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
	|	tem=template {
$p = $tem.p;	
	}
	|	pat=pattern {
$p = $pat.p;	
	}
	|	gr=group {
$p = $gr.p;	
	}
	|	seq=sequence {
$p = $seq.p;	
	}
	;
	catch[EugeneException e] {
throw new EugeneException(e.getMessage());	
	}

template	
	returns [Template p]
	:	(name=ID)? not=('NOT'|'not')? ('TEMPLATE'|'template') ids=list_of_ids {
$p = this.interp.createTemplate($name.text, $ids.lst);
if(null != not) {
    $p.setNegated();
}
	}
	;

pattern	
	returns [Pattern p]
	:	(name=ID)? not=('NOT'|'not')? ('PATTERN'|'pattern') ids=list_of_ids {
$p = this.interp.createPattern($name.text, $ids.lst);
if(null != not) {
    $p.setNegated();
}
	}
	;

sequence	
	returns [Sequence p]
	:	(name=ID)? not=('NOT'|'not')? ('SEQUENCE'|'sequence') ids=list_of_ids {
$p = this.interp.createSequence($name.text, $ids.lst);
if(null != not) {
    $p.setNegated();
}
	}
	;
	
group	
	returns [Group p]
	:	(name=ID)? not=('NOT'|'not')? ('GROUP'|'group') ids=list_of_ids {
$p = this.interp.createGroup($name.text, $ids.lst);
if(null != not) {
    $p.setNegated();
}
	}
	;

list_of_ids
        returns [List<String> lst]
@init{
$lst = new ArrayList<String>();
}	
	:	id=ID {
$lst.add($id.text);	
	}	(',' ids=list_of_ids {
$lst.addAll($ids.lst);		
	})?
	;
		
operator:
	|	('CONTAINS'|'contains')
	|	('NOTCONTAINS'|'notcontains')
	|	('EXACTLY'|'exactly')
	|	('NOTEXACTLY'|'notexactly')
	|	('MORETHAN'|'morethan')
	|	('NOTMORETHAN'|'notmorethan')
	|	('SAME_COUNT'|'same_count')
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
	|	('ALWAYS_NEXTTO'|'always_nextto')
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
	|	('ALL_SAME_ORIENTATION'|'all_same_orientation')
	|	('SOME_SAME_ORIENTATION'|'some_same_orientation')
	|	('REPRESSES'|'represses')
	|	('INDUCES'|'induces')
	|	('DRIVES'|'drives')
	|	('ALTERNATE_ORIENTATION'|'alternate_orientation')
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
