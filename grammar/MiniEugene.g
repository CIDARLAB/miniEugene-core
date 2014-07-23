grammar MiniEugene;

tokens {
	LEFTP = '(';
	RIGHTP = ')';
	LEFTSBR = '[';
	RIGHTSBR = ']';
	LEFTCUR = '{';
	RIGHTCUR = '}';
	UNDERS = '_';
	DOT = '.';
	COMMA = ',';
	PIPE = '|';
	
	UC_AND = 'AND';
	LC_AND = 'and';
	LOG_AND = '/\\';
	BOOL_AND = '&&';
	UC_OR = 'OR';
	LC_OR = 'or';
	LOG_OR = '\\/';
	BOOL_OR = '||';
	UC_NOT = 'NOT';
	LC_NOT = 'not';
	BOOL_NOT = '!';
	
	LC_IS_A = 'is_a';
	UC_IS_A = 'IS_A';
	
	UC_TEMPLATE = 'TEMPLATE';
	LC_TEMPLATE = 'template';
	UC_PATTERN = 'PATTERN';
	LC_PATTERN = 'pattern';
	UC_SEQUENCE = 'SEQUENCE';
	LC_SEQUENCE = 'sequence';
	UC_GROUP = 'GROUP';
	LC_GROUP = 'group';
	
	EQUALS = '=';
	N_SIZE = 'N';
	MIN_SIZE = 'minN';
}


@lexer::header {
/*
 * Copyright (c) 2014, Boston University
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 *    
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *    
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.minieugene.parser;
}

@header {
/*
 * Copyright (c) 2014, Boston University
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or 
 * without modification, are permitted provided that the following 
 * conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright 
 *    notice, this list of conditions and the following disclaimer.
 *    
 * 2. Redistributions in binary form must reproduce the above copyright 
 *    notice, this list of conditions and the following disclaimer in 
 *    the documentation and/or other materials provided with the distribution.
 *    
 * 3. Neither the name of the copyright holder nor the names of its 
 *    contributors may be used to endorse or promote products derived 
 *    from this software without specific prior written permission.
 *    
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS 
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT 
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR 
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT 
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, 
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED 
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, 
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.cidarlab.minieugene.parser;

import org.cidarlab.minieugene.Interp;
import org.cidarlab.minieugene.constants.TemplateType;
import org.cidarlab.minieugene.symbol.*;
import org.cidarlab.minieugene.predicates.*;
import org.cidarlab.minieugene.exception.MiniEugeneException;
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

boolean bError = false;
@Override
public void reportError(RecognitionException e) {
    e.printStackTrace();
    bError = true;
}

public boolean hasErrors() {
    return bError;
}

private boolean bCollectFacts = true;
public void setCollectFacts(boolean b) {
    this.bCollectFacts = b;
}

public void printFacts() {
    this.interp.printFacts();
}
}

 	
miniEugene
	throws MiniEugeneException
	:	(size DOT)? (statement DOT)+
	;

statement
	throws MiniEugeneException
	: 	fact
	| 	constraint_specification
	;
	
fact
	throws MiniEugeneException
	:	c=ID (UC_IS_A|LC_IS_A) t=ID {
if(bCollectFacts) {		
    this.interp.insertFact($c.text, $t.text);	
}
	}
	;

constraint_specification
	throws MiniEugeneException 
	:	c=or_constraint {
if(!bCollectFacts) {		
    if($c.lst.size() == 1) 	{
        // ``store'' the predicate
        this.addPredicate($c.lst.get(0));   
    } else {
        this.addPredicate(new LogicalOr($c.lst));   
    }	
}
	}	
	 |	composite_constraint
	;

size 	
	throws MiniEugeneException
	:	 ('minN' EQUALS minN=INT DOT {
this.minN = Integer.parseInt($minN.text);
this.interp.setMinN(this.minN);
	}	)? 	
		N_SIZE EQUALS maxN=INT {
this.maxN = Integer.parseInt($maxN.text);
this.interp.setMaxN(this.maxN);
}
	;

composite_constraint
	throws MiniEugeneException
	:	ID ( LEFTP list_of_parameters RIGHTP )? ':=' composite_constraint_block DOT
	;

composite_constraint_block
	throws MiniEugeneException
	:	constraint (COMMA composite_constraint_block)?
	;	
	
or_constraint 
        returns [List<Predicate> lst]
	throws MiniEugeneException
@init{
$lst = new ArrayList<Predicate>();
}	
	:	c=constraint {
if(!bCollectFacts) {		
    $lst.add($c.p);
}
	}	((UC_OR|LC_OR|LOG_OR|BOOL_OR) o=or_constraint {
if(!bCollectFacts) {		
    $lst.addAll($o.lst);
}
	})?
	;
	
constraint
        returns [Predicate p]
	throws MiniEugeneException
	:	(not=(UC_NOT|LC_NOT|BOOL_NOT) {
if(!bCollectFacts) {		
    addToken("NOT");
}
	})? (lhs=operand {
if(!bCollectFacts) {		
    addToken($lhs.text);
}	
	})? op=operator {
if(!bCollectFacts) {		
    addToken($op.text);	
}
	} (rhs=operand {
if(!bCollectFacts) {		
    addToken($rhs.text);	
}
	})? {
if(!bCollectFacts) {		
    // turn the tokens into a miniEugene predicate	
    $p = this.interp.interpreteRule(this.tokens);

    // reset the global token array
    this.tokens = null;
}
	}
	|	temp=templatingConstraints {
if(!bCollectFacts) {		
    $p = $temp.p;	
}
	}
	;
	catch[MiniEugeneException e] {
throw new MiniEugeneException(e.getMessage());	
	}

templatingConstraints
	returns [Predicate p]
	throws MiniEugeneException
	:	tem=templateConstraint {
if(!bCollectFacts) {		
    $p = $tem.p;	
}
	}
	|	pat=patternConstraint {
if(!bCollectFacts) {		
    $p = $pat.p;	
}
	}
	|	gr=groupConstraint {
if(!bCollectFacts) {		
    $p = $gr.p;	
}
	}
	|	seq=sequenceConstraint {
if(!bCollectFacts) {		
    $p = $seq.p;	
}
	}
	;
	
templateConstraint	
	returns [Template p]
	:	(name=ID)? not=(UC_NOT|LC_NOT|BOOL_NOT)? (UC_TEMPLATE|LC_TEMPLATE) ids=list_of_ids {
if(!bCollectFacts) {		
    $p = (Template)this.interp.createTemplatingConstraint(
        TemplateType.TEMPLATE, 
        $name.text, 
        $ids.lst);
    if(null != not) {
        $p.setNegated();
    }
}	
	}
	;

patternConstraint	
	returns [Pattern p]
	:	(name=ID)? not=(UC_NOT|LC_NOT|BOOL_NOT)? (UC_PATTERN|LC_PATTERN) ids=list_of_ids {
if(!bCollectFacts) {		
    $p = (Pattern)this.interp.createTemplatingConstraint(
        TemplateType.PATTERN, 
        $name.text, 
        $ids.lst);
    if(null != not) {
        $p.setNegated();
    }
}
	}
	;

sequenceConstraint	
	returns [Sequence p]
	:	(name=ID)? not=(UC_NOT|LC_NOT|BOOL_NOT)? (UC_SEQUENCE|LC_SEQUENCE) ids=list_of_ids {
if(!bCollectFacts) {		
    $p = (Sequence)this.interp.createTemplatingConstraint(
        TemplateType.SEQUENCE, 
        $name.text, 
        $ids.lst);
    if(null != not) {
        $p.setNegated();
    }
}
	}
	;
	
groupConstraint	
	returns [Group p]
	:	(name=ID)? not=(UC_NOT|LC_NOT|BOOL_NOT)? (UC_GROUP|LC_GROUP) ids=list_of_ids {
if(!bCollectFacts) {		
    $p = (Group)this.interp.createTemplatingConstraint(
        TemplateType.GROUP, 
        $name.text, 
        $ids.lst);
    if(null != not) {
        $p.setNegated();
    }
}
	}
	;

list_of_ids
        returns [List<List<String>> lst]
@init{
$lst = new ArrayList<List<String>>();
}	
	:	(id=ID {
if(!bCollectFacts) {		
    List<String> id_lst = new ArrayList<String>();
    id_lst.add($id.text);
    $lst.add(id_lst);	
}
	}	|LEFTSBR sel=selection {
if(!bCollectFacts) {		
    $lst.add($sel.lst);	
}
	}	RIGHTSBR)
		(COMMA ids=list_of_ids {
if(!bCollectFacts) {		
    $lst.addAll($ids.lst);		
}
	})?
	;

selection
	returns [List<String> lst]
@init{
$lst = new ArrayList<String>();
}	
	:	id=ID {
if(!bCollectFacts) {		
    $lst.add($id.text);		
}
	} 	(PIPE sel=selection {
if(!bCollectFacts) {		
    $lst.addAll($sel.lst);	
}
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
	|	LEFTSBR INT RIGHTSBR
	;

list_of_parameters
	:	operand (COMMA list_of_parameters)?
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

FallThrough
@after{
  throw new RuntimeException(String.format(
      "Encountered an illegal char  '\%s'", getText()));
}
  :  '*'|'+'|'?' 
  ;
