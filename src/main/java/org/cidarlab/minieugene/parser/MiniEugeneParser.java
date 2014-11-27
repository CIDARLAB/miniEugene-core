// $ANTLR 3.5.1 /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g 2014-11-27 12:49:45

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


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class MiniEugeneParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "BOOL_AND", "BOOL_NOT", "BOOL_OR", 
		"CHAR", "COLON", "COMMA", "COMMENT", "DOT", "EQUALS", "ESC_SEQ", "EXPONENT", 
		"FallThrough", "HEX_DIGIT", "ID", "INT", "LC_AND", "LC_GROUP", "LC_IS_A", 
		"LC_NOT", "LC_OR", "LC_PATTERN", "LC_SEQUENCE", "LC_TEMPLATE", "LEFTCUR", 
		"LEFTP", "LEFTSBR", "LOG_AND", "LOG_OR", "MIN_SIZE", "N_SIZE", "OCTAL_ESC", 
		"PIPE", "RIGHTCUR", "RIGHTP", "RIGHTSBR", "STRING", "UC_AND", "UC_GROUP", 
		"UC_IS_A", "UC_NOT", "UC_OR", "UC_PATTERN", "UC_SEQUENCE", "UC_TEMPLATE", 
		"UNDERS", "UNICODE_ESC", "WS", "':='", "'AFTER'", "'ALL_AFTER'", "'ALL_BEFORE'", 
		"'ALL_FORWARD'", "'ALL_NEXTTO'", "'ALL_REVERSE'", "'ALL_SAME_ORIENTATION'", 
		"'ALTERNATE_ORIENTATION'", "'ALWAYS_NEXTTO'", "'BEFORE'", "'CONTAINS'", 
		"'DRIVES'", "'ENDSWITH'", "'EQUALS'", "'EXACTLY'", "'FORWARD'", "'INDUCES'", 
		"'MATCHES'", "'MORETHAN'", "'NEXTTO'", "'NOTCONTAINS'", "'NOTEQUALS'", 
		"'NOTEXACTLY'", "'NOTMATCHES'", "'NOTMORETHAN'", "'NOTTHEN'", "'NOTWITH'", 
		"'REPRESSES'", "'REVERSE'", "'SAME_COUNT'", "'SAME_ORIENTATION'", "'SOME_AFTER'", 
		"'SOME_BEFORE'", "'SOME_FORWARD'", "'SOME_NEXTTO'", "'SOME_REVERSE'", 
		"'SOME_SAME_ORIENTATION'", "'STARTSWITH'", "'THEN'", "'WITH'", "'after'", 
		"'all_after'", "'all_before'", "'all_forward'", "'all_nextto'", "'all_reverse'", 
		"'all_same_orientation'", "'alternate_orientation'", "'always_nextto'", 
		"'before'", "'contains'", "'drives'", "'endswith'", "'equals'", "'exactly'", 
		"'forward'", "'induces'", "'matches'", "'morethan'", "'nextto'", "'notcontains'", 
		"'notequals'", "'notexactly'", "'notmatches'", "'notmorethan'", "'notthen'", 
		"'notwith'", "'represses'", "'reverse'", "'same_count'", "'same_orientation'", 
		"'some_after'", "'some_before'", "'some_forward'", "'some_nextto'", "'some_reverse'", 
		"'some_same_orientation'", "'startswith'", "'then'", "'with'"
	};
	public static final int EOF=-1;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int T__53=53;
	public static final int T__54=54;
	public static final int T__55=55;
	public static final int T__56=56;
	public static final int T__57=57;
	public static final int T__58=58;
	public static final int T__59=59;
	public static final int T__60=60;
	public static final int T__61=61;
	public static final int T__62=62;
	public static final int T__63=63;
	public static final int T__64=64;
	public static final int T__65=65;
	public static final int T__66=66;
	public static final int T__67=67;
	public static final int T__68=68;
	public static final int T__69=69;
	public static final int T__70=70;
	public static final int T__71=71;
	public static final int T__72=72;
	public static final int T__73=73;
	public static final int T__74=74;
	public static final int T__75=75;
	public static final int T__76=76;
	public static final int T__77=77;
	public static final int T__78=78;
	public static final int T__79=79;
	public static final int T__80=80;
	public static final int T__81=81;
	public static final int T__82=82;
	public static final int T__83=83;
	public static final int T__84=84;
	public static final int T__85=85;
	public static final int T__86=86;
	public static final int T__87=87;
	public static final int T__88=88;
	public static final int T__89=89;
	public static final int T__90=90;
	public static final int T__91=91;
	public static final int T__92=92;
	public static final int T__93=93;
	public static final int T__94=94;
	public static final int T__95=95;
	public static final int T__96=96;
	public static final int T__97=97;
	public static final int T__98=98;
	public static final int T__99=99;
	public static final int T__100=100;
	public static final int T__101=101;
	public static final int T__102=102;
	public static final int T__103=103;
	public static final int T__104=104;
	public static final int T__105=105;
	public static final int T__106=106;
	public static final int T__107=107;
	public static final int T__108=108;
	public static final int T__109=109;
	public static final int T__110=110;
	public static final int T__111=111;
	public static final int T__112=112;
	public static final int T__113=113;
	public static final int T__114=114;
	public static final int T__115=115;
	public static final int T__116=116;
	public static final int T__117=117;
	public static final int T__118=118;
	public static final int T__119=119;
	public static final int T__120=120;
	public static final int T__121=121;
	public static final int T__122=122;
	public static final int T__123=123;
	public static final int T__124=124;
	public static final int T__125=125;
	public static final int T__126=126;
	public static final int T__127=127;
	public static final int T__128=128;
	public static final int T__129=129;
	public static final int T__130=130;
	public static final int T__131=131;
	public static final int BOOL_AND=4;
	public static final int BOOL_NOT=5;
	public static final int BOOL_OR=6;
	public static final int CHAR=7;
	public static final int COLON=8;
	public static final int COMMA=9;
	public static final int COMMENT=10;
	public static final int DOT=11;
	public static final int EQUALS=12;
	public static final int ESC_SEQ=13;
	public static final int EXPONENT=14;
	public static final int FallThrough=15;
	public static final int HEX_DIGIT=16;
	public static final int ID=17;
	public static final int INT=18;
	public static final int LC_AND=19;
	public static final int LC_GROUP=20;
	public static final int LC_IS_A=21;
	public static final int LC_NOT=22;
	public static final int LC_OR=23;
	public static final int LC_PATTERN=24;
	public static final int LC_SEQUENCE=25;
	public static final int LC_TEMPLATE=26;
	public static final int LEFTCUR=27;
	public static final int LEFTP=28;
	public static final int LEFTSBR=29;
	public static final int LOG_AND=30;
	public static final int LOG_OR=31;
	public static final int MIN_SIZE=32;
	public static final int N_SIZE=33;
	public static final int OCTAL_ESC=34;
	public static final int PIPE=35;
	public static final int RIGHTCUR=36;
	public static final int RIGHTP=37;
	public static final int RIGHTSBR=38;
	public static final int STRING=39;
	public static final int UC_AND=40;
	public static final int UC_GROUP=41;
	public static final int UC_IS_A=42;
	public static final int UC_NOT=43;
	public static final int UC_OR=44;
	public static final int UC_PATTERN=45;
	public static final int UC_SEQUENCE=46;
	public static final int UC_TEMPLATE=47;
	public static final int UNDERS=48;
	public static final int UNICODE_ESC=49;
	public static final int WS=50;

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public MiniEugeneParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public MiniEugeneParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return MiniEugeneParser.tokenNames; }
	@Override public String getGrammarFileName() { return "/Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g"; }



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

	// LOGICAL CONJUNCTION of all specified Constraints
	private LogicalAnd la = new LogicalAnd();

	private void addConstraint(Constraint c) {
	    this.la.getConstraints().add(c);  
	}

	public LogicalAnd getConstraint() {
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
	    this.symbols.printFacts();
	}



	// $ANTLR start "miniEugene"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:192:1: miniEugene : ( size DOT )? ( statement DOT )+ ;
	public final void miniEugene() throws RecognitionException, MiniEugeneException {
		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:194:2: ( ( size DOT )? ( statement DOT )+ )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:194:4: ( size DOT )? ( statement DOT )+
			{
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:194:4: ( size DOT )?
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( ((LA1_0 >= MIN_SIZE && LA1_0 <= N_SIZE)) ) {
				alt1=1;
			}
			switch (alt1) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:194:5: size DOT
					{
					pushFollow(FOLLOW_size_in_miniEugene338);
					size();
					state._fsp--;

					match(input,DOT,FOLLOW_DOT_in_miniEugene340); 
					}
					break;

			}

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:194:16: ( statement DOT )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= BOOL_NOT && LA2_0 <= BOOL_OR)||LA2_0==COMMA||LA2_0==DOT||(LA2_0 >= ID && LA2_0 <= INT)||(LA2_0 >= LC_NOT && LA2_0 <= LC_OR)||(LA2_0 >= LC_SEQUENCE && LA2_0 <= LC_TEMPLATE)||LA2_0==LEFTSBR||LA2_0==LOG_OR||(LA2_0 >= UC_NOT && LA2_0 <= UC_OR)||(LA2_0 >= UC_SEQUENCE && LA2_0 <= UC_TEMPLATE)||(LA2_0 >= 52 && LA2_0 <= 131)) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:194:17: statement DOT
					{
					pushFollow(FOLLOW_statement_in_miniEugene345);
					statement();
					state._fsp--;

					match(input,DOT,FOLLOW_DOT_in_miniEugene347); 
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "miniEugene"



	// $ANTLR start "statement"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:197:1: statement : ( fact | constraint_specification );
	public final void statement() throws RecognitionException, MiniEugeneException {
		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:199:2: ( fact | constraint_specification )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==ID) ) {
				int LA3_1 = input.LA(2);
				if ( (LA3_1==LC_IS_A||LA3_1==UC_IS_A) ) {
					alt3=1;
				}
				else if ( (LA3_1==BOOL_OR||LA3_1==COLON||LA3_1==DOT||(LA3_1 >= ID && LA3_1 <= INT)||LA3_1==LC_OR||(LA3_1 >= LEFTP && LA3_1 <= LEFTSBR)||LA3_1==LOG_OR||LA3_1==UC_OR||(LA3_1 >= 51 && LA3_1 <= 131)) ) {
					alt3=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 3, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( ((LA3_0 >= BOOL_NOT && LA3_0 <= BOOL_OR)||LA3_0==DOT||LA3_0==INT||(LA3_0 >= LC_NOT && LA3_0 <= LC_OR)||(LA3_0 >= LC_SEQUENCE && LA3_0 <= LC_TEMPLATE)||LA3_0==LEFTSBR||LA3_0==LOG_OR||(LA3_0 >= UC_NOT && LA3_0 <= UC_OR)||(LA3_0 >= UC_SEQUENCE && LA3_0 <= UC_TEMPLATE)||(LA3_0 >= 52 && LA3_0 <= 131)) ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:199:5: fact
					{
					pushFollow(FOLLOW_fact_in_statement366);
					fact();
					state._fsp--;

					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:200:5: constraint_specification
					{
					pushFollow(FOLLOW_constraint_specification_in_statement372);
					constraint_specification();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "statement"



	// $ANTLR start "fact"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:203:1: fact : c= ID ( UC_IS_A | LC_IS_A ) t= ID ;
	public final void fact() throws RecognitionException, MiniEugeneException {
		Token c=null;
		Token t=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:205:2: (c= ID ( UC_IS_A | LC_IS_A ) t= ID )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:205:4: c= ID ( UC_IS_A | LC_IS_A ) t= ID
			{
			c=(Token)match(input,ID,FOLLOW_ID_in_fact391); 
			if ( input.LA(1)==LC_IS_A||input.LA(1)==UC_IS_A ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			t=(Token)match(input,ID,FOLLOW_ID_in_fact401); 

			if(bCollectFacts) {		
			    this.interp.insertFact((c!=null?c.getText():null), (t!=null?t.getText():null));	
			}
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "fact"



	// $ANTLR start "constraint_specification"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:212:1: constraint_specification : (c= or_constraint | composite_constraint );
	public final void constraint_specification() throws RecognitionException, MiniEugeneException {
		List<Constraint> c =null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:214:2: (c= or_constraint | composite_constraint )
			int alt4=2;
			int LA4_0 = input.LA(1);
			if ( ((LA4_0 >= BOOL_NOT && LA4_0 <= BOOL_OR)||LA4_0==DOT||LA4_0==INT||(LA4_0 >= LC_NOT && LA4_0 <= LC_OR)||(LA4_0 >= LC_SEQUENCE && LA4_0 <= LC_TEMPLATE)||LA4_0==LEFTSBR||LA4_0==LOG_OR||(LA4_0 >= UC_NOT && LA4_0 <= UC_OR)||(LA4_0 >= UC_SEQUENCE && LA4_0 <= UC_TEMPLATE)||(LA4_0 >= 52 && LA4_0 <= 131)) ) {
				alt4=1;
			}
			else if ( (LA4_0==ID) ) {
				int LA4_2 = input.LA(2);
				if ( (LA4_2==BOOL_OR||LA4_2==COLON||LA4_2==DOT||(LA4_2 >= ID && LA4_2 <= INT)||LA4_2==LC_OR||LA4_2==LEFTSBR||LA4_2==LOG_OR||LA4_2==UC_OR||(LA4_2 >= 52 && LA4_2 <= 131)) ) {
					alt4=1;
				}
				else if ( (LA4_2==LEFTP||LA4_2==51) ) {
					alt4=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 4, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 4, 0, input);
				throw nvae;
			}

			switch (alt4) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:214:4: c= or_constraint
					{
					pushFollow(FOLLOW_or_constraint_in_constraint_specification422);
					c=or_constraint();
					state._fsp--;


					if(!bCollectFacts) {		
					    if(c.size() == 1) 	{
					        // ``store'' the constraint
					        this.addConstraint(c.get(0));   
					    } else {
					        this.addConstraint(new LogicalOr(c));   
					    }	
					}
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:224:5: composite_constraint
					{
					pushFollow(FOLLOW_composite_constraint_in_constraint_specification431);
					composite_constraint();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "constraint_specification"



	// $ANTLR start "size"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:227:1: size : ( 'minN' EQUALS minN= INT DOT )? N_SIZE EQUALS maxN= INT ;
	public final void size() throws RecognitionException, MiniEugeneException {
		Token minN=null;
		Token maxN=null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:229:2: ( ( 'minN' EQUALS minN= INT DOT )? N_SIZE EQUALS maxN= INT )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:229:5: ( 'minN' EQUALS minN= INT DOT )? N_SIZE EQUALS maxN= INT
			{
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:229:5: ( 'minN' EQUALS minN= INT DOT )?
			int alt5=2;
			int LA5_0 = input.LA(1);
			if ( (LA5_0==MIN_SIZE) ) {
				alt5=1;
			}
			switch (alt5) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:229:6: 'minN' EQUALS minN= INT DOT
					{
					match(input,MIN_SIZE,FOLLOW_MIN_SIZE_in_size451); 
					match(input,EQUALS,FOLLOW_EQUALS_in_size453); 
					minN=(Token)match(input,INT,FOLLOW_INT_in_size457); 
					match(input,DOT,FOLLOW_DOT_in_size459); 

					this.minN = Integer.parseInt((minN!=null?minN.getText():null));
					this.interp.setMinN(this.minN);
						
					}
					break;

			}

			match(input,N_SIZE,FOLLOW_N_SIZE_in_size470); 
			match(input,EQUALS,FOLLOW_EQUALS_in_size472); 
			maxN=(Token)match(input,INT,FOLLOW_INT_in_size476); 

			this.maxN = Integer.parseInt((maxN!=null?maxN.getText():null));
			this.interp.setMaxN(this.maxN);

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "size"



	// $ANTLR start "composite_constraint"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:239:1: composite_constraint : ID ( LEFTP list_of_parameters RIGHTP )? ':=' composite_constraint_block DOT ;
	public final void composite_constraint() throws RecognitionException, MiniEugeneException {
		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:241:2: ( ID ( LEFTP list_of_parameters RIGHTP )? ':=' composite_constraint_block DOT )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:241:4: ID ( LEFTP list_of_parameters RIGHTP )? ':=' composite_constraint_block DOT
			{
			match(input,ID,FOLLOW_ID_in_composite_constraint494); 
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:241:7: ( LEFTP list_of_parameters RIGHTP )?
			int alt6=2;
			int LA6_0 = input.LA(1);
			if ( (LA6_0==LEFTP) ) {
				alt6=1;
			}
			switch (alt6) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:241:9: LEFTP list_of_parameters RIGHTP
					{
					match(input,LEFTP,FOLLOW_LEFTP_in_composite_constraint498); 
					pushFollow(FOLLOW_list_of_parameters_in_composite_constraint500);
					list_of_parameters();
					state._fsp--;

					match(input,RIGHTP,FOLLOW_RIGHTP_in_composite_constraint502); 
					}
					break;

			}

			match(input,51,FOLLOW_51_in_composite_constraint507); 
			pushFollow(FOLLOW_composite_constraint_block_in_composite_constraint509);
			composite_constraint_block();
			state._fsp--;

			match(input,DOT,FOLLOW_DOT_in_composite_constraint511); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "composite_constraint"



	// $ANTLR start "composite_constraint_block"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:244:1: composite_constraint_block : constraint ( COMMA composite_constraint_block )? ;
	public final void composite_constraint_block() throws RecognitionException, MiniEugeneException {
		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:246:2: ( constraint ( COMMA composite_constraint_block )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:246:4: constraint ( COMMA composite_constraint_block )?
			{
			pushFollow(FOLLOW_constraint_in_composite_constraint_block527);
			constraint();
			state._fsp--;

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:246:15: ( COMMA composite_constraint_block )?
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==COMMA) ) {
				alt7=1;
			}
			switch (alt7) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:246:16: COMMA composite_constraint_block
					{
					match(input,COMMA,FOLLOW_COMMA_in_composite_constraint_block530); 
					pushFollow(FOLLOW_composite_constraint_block_in_composite_constraint_block532);
					composite_constraint_block();
					state._fsp--;

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "composite_constraint_block"



	// $ANTLR start "or_constraint"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:249:1: or_constraint returns [List<Constraint> lst] : con= constraint ( ( UC_OR | LC_OR | LOG_OR | BOOL_OR ) o= or_constraint )? ;
	public final List<Constraint> or_constraint() throws RecognitionException, MiniEugeneException {
		List<Constraint> lst = null;


		Constraint con =null;
		List<Constraint> o =null;


		lst = new ArrayList<Constraint>();

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:255:2: (con= constraint ( ( UC_OR | LC_OR | LOG_OR | BOOL_OR ) o= or_constraint )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:255:4: con= constraint ( ( UC_OR | LC_OR | LOG_OR | BOOL_OR ) o= or_constraint )?
			{
			pushFollow(FOLLOW_constraint_in_or_constraint572);
			con=constraint();
			state._fsp--;


			if(!bCollectFacts) {		
			    lst.add(con);
			}
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:259:4: ( ( UC_OR | LC_OR | LOG_OR | BOOL_OR ) o= or_constraint )?
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==BOOL_OR||LA8_0==LC_OR||LA8_0==LOG_OR||LA8_0==UC_OR) ) {
				alt8=1;
			}
			switch (alt8) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:259:5: ( UC_OR | LC_OR | LOG_OR | BOOL_OR ) o= or_constraint
					{
					if ( input.LA(1)==BOOL_OR||input.LA(1)==LC_OR||input.LA(1)==LOG_OR||input.LA(1)==UC_OR ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_or_constraint_in_or_constraint589);
					o=or_constraint();
					state._fsp--;


					if(!bCollectFacts) {		
					    lst.addAll(o);
					}
						
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return lst;
	}
	// $ANTLR end "or_constraint"



	// $ANTLR start "constraint"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:266:1: constraint returns [Constraint c] : ( (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? (lhs= operand )? op= operator (rhs= operand )? |temp= templatingConstraints );
	public final Constraint constraint() throws RecognitionException, MiniEugeneException {
		Constraint c = null;


		Token not=null;
		ParserRuleReturnScope lhs =null;
		ParserRuleReturnScope op =null;
		ParserRuleReturnScope rhs =null;
		Constraint temp =null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:269:2: ( (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? (lhs= operand )? op= operator (rhs= operand )? |temp= templatingConstraints )
			int alt12=2;
			switch ( input.LA(1) ) {
			case BOOL_NOT:
			case LC_NOT:
			case UC_NOT:
				{
				int LA12_1 = input.LA(2);
				if ( (LA12_1==BOOL_OR||LA12_1==COMMA||LA12_1==DOT||(LA12_1 >= ID && LA12_1 <= INT)||LA12_1==LC_OR||LA12_1==LEFTSBR||LA12_1==LOG_OR||LA12_1==UC_OR||(LA12_1 >= 52 && LA12_1 <= 131)) ) {
					alt12=1;
				}
				else if ( ((LA12_1 >= LC_SEQUENCE && LA12_1 <= LC_TEMPLATE)||(LA12_1 >= UC_SEQUENCE && LA12_1 <= UC_TEMPLATE)) ) {
					alt12=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 12, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case ID:
				{
				int LA12_2 = input.LA(2);
				if ( (LA12_2==COLON) ) {
					alt12=2;
				}
				else if ( (LA12_2==BOOL_OR||LA12_2==COMMA||LA12_2==DOT||(LA12_2 >= ID && LA12_2 <= INT)||LA12_2==LC_OR||LA12_2==LEFTSBR||LA12_2==LOG_OR||LA12_2==UC_OR||(LA12_2 >= 52 && LA12_2 <= 131)) ) {
					alt12=1;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 12, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case BOOL_OR:
			case COMMA:
			case DOT:
			case INT:
			case LC_OR:
			case LEFTSBR:
			case LOG_OR:
			case UC_OR:
			case 52:
			case 53:
			case 54:
			case 55:
			case 56:
			case 57:
			case 58:
			case 59:
			case 60:
			case 61:
			case 62:
			case 63:
			case 64:
			case 65:
			case 66:
			case 67:
			case 68:
			case 69:
			case 70:
			case 71:
			case 72:
			case 73:
			case 74:
			case 75:
			case 76:
			case 77:
			case 78:
			case 79:
			case 80:
			case 81:
			case 82:
			case 83:
			case 84:
			case 85:
			case 86:
			case 87:
			case 88:
			case 89:
			case 90:
			case 91:
			case 92:
			case 93:
			case 94:
			case 95:
			case 96:
			case 97:
			case 98:
			case 99:
			case 100:
			case 101:
			case 102:
			case 103:
			case 104:
			case 105:
			case 106:
			case 107:
			case 108:
			case 109:
			case 110:
			case 111:
			case 112:
			case 113:
			case 114:
			case 115:
			case 116:
			case 117:
			case 118:
			case 119:
			case 120:
			case 121:
			case 122:
			case 123:
			case 124:
			case 125:
			case 126:
			case 127:
			case 128:
			case 129:
			case 130:
			case 131:
				{
				alt12=1;
				}
				break;
			case LC_SEQUENCE:
			case LC_TEMPLATE:
			case UC_SEQUENCE:
			case UC_TEMPLATE:
				{
				alt12=2;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 12, 0, input);
				throw nvae;
			}
			switch (alt12) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:269:4: (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? (lhs= operand )? op= operator (rhs= operand )?
					{
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:269:4: (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )?
					int alt9=2;
					int LA9_0 = input.LA(1);
					if ( (LA9_0==BOOL_NOT||LA9_0==LC_NOT||LA9_0==UC_NOT) ) {
						alt9=1;
					}
					switch (alt9) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:269:5: not= ( UC_NOT | LC_NOT | BOOL_NOT )
							{
							not=input.LT(1);
							if ( input.LA(1)==BOOL_NOT||input.LA(1)==LC_NOT||input.LA(1)==UC_NOT ) {
								input.consume();
								state.errorRecovery=false;
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								throw mse;
							}

							if(!bCollectFacts) {		
							    addToken("NOT");
							}
								
							}
							break;

					}

					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:273:6: (lhs= operand )?
					int alt10=2;
					switch ( input.LA(1) ) {
						case ID:
							{
							alt10=1;
							}
							break;
						case INT:
							{
							alt10=1;
							}
							break;
						case LEFTSBR:
							{
							int LA10_3 = input.LA(2);
							if ( (LA10_3==INT) ) {
								int LA10_5 = input.LA(3);
								if ( (LA10_5==RIGHTSBR) ) {
									alt10=1;
								}
							}
							}
							break;
					}
					switch (alt10) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:273:7: lhs= operand
							{
							pushFollow(FOLLOW_operand_in_constraint640);
							lhs=operand();
							state._fsp--;


							if(!bCollectFacts) {		
							    addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));
							}	
								
							}
							break;

					}

					pushFollow(FOLLOW_operator_in_constraint648);
					op=operator();
					state._fsp--;


					if(!bCollectFacts) {		
					    addToken((op!=null?input.toString(op.start,op.stop):null));	
					}
						
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:281:4: (rhs= operand )?
					int alt11=2;
					int LA11_0 = input.LA(1);
					if ( ((LA11_0 >= ID && LA11_0 <= INT)||LA11_0==LEFTSBR) ) {
						alt11=1;
					}
					switch (alt11) {
						case 1 :
							// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:281:5: rhs= operand
							{
							pushFollow(FOLLOW_operand_in_constraint655);
							rhs=operand();
							state._fsp--;


							if(!bCollectFacts) {		
							    addToken((rhs!=null?input.toString(rhs.start,rhs.stop):null));	
							}
								
							}
							break;

					}


					if(!bCollectFacts) {		
					    // turn the tokens into a miniEugene constraint	
					    c = this.interp.interpreteRule(this.tokens);

					    // reset the global token array
					    this.tokens = null;
					}
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:294:4: temp= templatingConstraints
					{
					pushFollow(FOLLOW_templatingConstraints_in_constraint668);
					temp=templatingConstraints();
					state._fsp--;


					if(!bCollectFacts) {		
					    c = temp;	
					}
						
					}
					break;

			}
		}
		catch (MiniEugeneException e) {

			throw new MiniEugeneException(e.getMessage());	
				
		}

		finally {
			// do for sure before leaving
		}
		return c;
	}
	// $ANTLR end "constraint"



	// $ANTLR start "templatingConstraints"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:304:1: templatingConstraints returns [Constraint c] : (tem= templateConstraint |seq= sequenceConstraint );
	public final Constraint templatingConstraints() throws RecognitionException, MiniEugeneException {
		Constraint c = null;


		Template tem =null;
		Sequence seq =null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:307:2: (tem= templateConstraint |seq= sequenceConstraint )
			int alt13=2;
			switch ( input.LA(1) ) {
			case ID:
				{
				int LA13_1 = input.LA(2);
				if ( (LA13_1==COLON) ) {
					switch ( input.LA(3) ) {
					case BOOL_NOT:
					case LC_NOT:
					case UC_NOT:
						{
						int LA13_2 = input.LA(4);
						if ( (LA13_2==LC_TEMPLATE||LA13_2==UC_TEMPLATE) ) {
							alt13=1;
						}
						else if ( (LA13_2==LC_SEQUENCE||LA13_2==UC_SEQUENCE) ) {
							alt13=2;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 13, 2, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

						}
						break;
					case LC_TEMPLATE:
					case UC_TEMPLATE:
						{
						alt13=1;
						}
						break;
					case LC_SEQUENCE:
					case UC_SEQUENCE:
						{
						alt13=2;
						}
						break;
					default:
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 13, 5, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 13, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case BOOL_NOT:
			case LC_NOT:
			case UC_NOT:
				{
				int LA13_2 = input.LA(2);
				if ( (LA13_2==LC_TEMPLATE||LA13_2==UC_TEMPLATE) ) {
					alt13=1;
				}
				else if ( (LA13_2==LC_SEQUENCE||LA13_2==UC_SEQUENCE) ) {
					alt13=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 13, 2, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case LC_TEMPLATE:
			case UC_TEMPLATE:
				{
				alt13=1;
				}
				break;
			case LC_SEQUENCE:
			case UC_SEQUENCE:
				{
				alt13=2;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 13, 0, input);
				throw nvae;
			}
			switch (alt13) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:307:4: tem= templateConstraint
					{
					pushFollow(FOLLOW_templateConstraint_in_templatingConstraints699);
					tem=templateConstraint();
					state._fsp--;


					if(!bCollectFacts) {		
					    c = tem;	
					}
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:312:4: seq= sequenceConstraint
					{
					pushFollow(FOLLOW_sequenceConstraint_in_templatingConstraints708);
					seq=sequenceConstraint();
					state._fsp--;


					if(!bCollectFacts) {		
					    c = seq;	
					}
						
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return c;
	}
	// $ANTLR end "templatingConstraints"



	// $ANTLR start "templateConstraint"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:319:1: templateConstraint returns [Template t] : (name= ID COLON )? not= ( UC_NOT | LC_NOT | BOOL_NOT )? ( UC_TEMPLATE | LC_TEMPLATE ) ids= list_of_ids ;
	public final Template templateConstraint() throws RecognitionException {
		Template t = null;


		Token name=null;
		Token not=null;
		List<List<String>> ids =null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:321:2: ( (name= ID COLON )? not= ( UC_NOT | LC_NOT | BOOL_NOT )? ( UC_TEMPLATE | LC_TEMPLATE ) ids= list_of_ids )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:321:4: (name= ID COLON )? not= ( UC_NOT | LC_NOT | BOOL_NOT )? ( UC_TEMPLATE | LC_TEMPLATE ) ids= list_of_ids
			{
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:321:4: (name= ID COLON )?
			int alt14=2;
			int LA14_0 = input.LA(1);
			if ( (LA14_0==ID) ) {
				alt14=1;
			}
			switch (alt14) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:321:5: name= ID COLON
					{
					name=(Token)match(input,ID,FOLLOW_ID_in_templateConstraint731); 
					match(input,COLON,FOLLOW_COLON_in_templateConstraint733); 
					}
					break;

			}

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:321:25: ( UC_NOT | LC_NOT | BOOL_NOT )?
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==BOOL_NOT||LA15_0==LC_NOT||LA15_0==UC_NOT) ) {
				alt15=1;
			}
			switch (alt15) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:
					{
					if ( input.LA(1)==BOOL_NOT||input.LA(1)==LC_NOT||input.LA(1)==UC_NOT ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;

			}

			if ( input.LA(1)==LC_TEMPLATE||input.LA(1)==UC_TEMPLATE ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_list_of_ids_in_templateConstraint756);
			ids=list_of_ids();
			state._fsp--;


			if(!bCollectFacts) {		
			    t = (Template)this.interp.createTemplatingConstraint(
			        TemplateType.TEMPLATE, 
			        (name!=null?name.getText():null), 
			        ids);
			    if(null != not) {
			        t.setNegated();
			    }
			}	
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return t;
	}
	// $ANTLR end "templateConstraint"



	// $ANTLR start "sequenceConstraint"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:334:1: sequenceConstraint returns [Sequence s] : (name= ID COLON )? not= ( UC_NOT | LC_NOT | BOOL_NOT )? ( UC_SEQUENCE | LC_SEQUENCE ) ids= list_of_ids ;
	public final Sequence sequenceConstraint() throws RecognitionException {
		Sequence s = null;


		Token name=null;
		Token not=null;
		List<List<String>> ids =null;

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:336:2: ( (name= ID COLON )? not= ( UC_NOT | LC_NOT | BOOL_NOT )? ( UC_SEQUENCE | LC_SEQUENCE ) ids= list_of_ids )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:336:4: (name= ID COLON )? not= ( UC_NOT | LC_NOT | BOOL_NOT )? ( UC_SEQUENCE | LC_SEQUENCE ) ids= list_of_ids
			{
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:336:4: (name= ID COLON )?
			int alt16=2;
			int LA16_0 = input.LA(1);
			if ( (LA16_0==ID) ) {
				alt16=1;
			}
			switch (alt16) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:336:5: name= ID COLON
					{
					name=(Token)match(input,ID,FOLLOW_ID_in_sequenceConstraint778); 
					match(input,COLON,FOLLOW_COLON_in_sequenceConstraint780); 
					}
					break;

			}

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:336:25: ( UC_NOT | LC_NOT | BOOL_NOT )?
			int alt17=2;
			int LA17_0 = input.LA(1);
			if ( (LA17_0==BOOL_NOT||LA17_0==LC_NOT||LA17_0==UC_NOT) ) {
				alt17=1;
			}
			switch (alt17) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:
					{
					if ( input.LA(1)==BOOL_NOT||input.LA(1)==LC_NOT||input.LA(1)==UC_NOT ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;

			}

			if ( input.LA(1)==LC_SEQUENCE||input.LA(1)==UC_SEQUENCE ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_list_of_ids_in_sequenceConstraint803);
			ids=list_of_ids();
			state._fsp--;


			if(!bCollectFacts) {		
			    s = (Sequence)this.interp.createTemplatingConstraint(
			        TemplateType.SEQUENCE, 
			        (name!=null?name.getText():null), 
			        ids);
			    if(null != not) {
			        s.setNegated();
			    }
			}
				
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return s;
	}
	// $ANTLR end "sequenceConstraint"



	// $ANTLR start "list_of_ids"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:349:1: list_of_ids returns [List<List<String>> lst] : (id= ID | LEFTSBR sel= selection RIGHTSBR ) ( COMMA ids= list_of_ids )? ;
	public final List<List<String>> list_of_ids() throws RecognitionException {
		List<List<String>> lst = null;


		Token id=null;
		List<String> sel =null;
		List<List<String>> ids =null;


		lst = new ArrayList<List<String>>();

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:354:2: ( (id= ID | LEFTSBR sel= selection RIGHTSBR ) ( COMMA ids= list_of_ids )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:354:4: (id= ID | LEFTSBR sel= selection RIGHTSBR ) ( COMMA ids= list_of_ids )?
			{
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:354:4: (id= ID | LEFTSBR sel= selection RIGHTSBR )
			int alt18=2;
			int LA18_0 = input.LA(1);
			if ( (LA18_0==ID) ) {
				alt18=1;
			}
			else if ( (LA18_0==LEFTSBR) ) {
				alt18=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 18, 0, input);
				throw nvae;
			}

			switch (alt18) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:354:5: id= ID
					{
					id=(Token)match(input,ID,FOLLOW_ID_in_list_of_ids837); 

					if(!bCollectFacts) {		
					    List<String> id_lst = new ArrayList<String>();
					    id_lst.add((id!=null?id.getText():null));
					    lst.add(id_lst);	
					}
						
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:360:5: LEFTSBR sel= selection RIGHTSBR
					{
					match(input,LEFTSBR,FOLLOW_LEFTSBR_in_list_of_ids842); 
					pushFollow(FOLLOW_selection_in_list_of_ids846);
					sel=selection();
					state._fsp--;


					if(!bCollectFacts) {		
					    lst.add(sel);	
					}
						
					match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_list_of_ids850); 
					}
					break;

			}

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:365:3: ( COMMA ids= list_of_ids )?
			int alt19=2;
			int LA19_0 = input.LA(1);
			if ( (LA19_0==COMMA) ) {
				int LA19_1 = input.LA(2);
				if ( (LA19_1==ID) ) {
					alt19=1;
				}
				else if ( (LA19_1==LEFTSBR) ) {
					int LA19_4 = input.LA(3);
					if ( (LA19_4==ID) ) {
						alt19=1;
					}
				}
			}
			switch (alt19) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:365:4: COMMA ids= list_of_ids
					{
					match(input,COMMA,FOLLOW_COMMA_in_list_of_ids856); 
					pushFollow(FOLLOW_list_of_ids_in_list_of_ids860);
					ids=list_of_ids();
					state._fsp--;


					if(!bCollectFacts) {		
					    lst.addAll(ids);		
					}
						
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return lst;
	}
	// $ANTLR end "list_of_ids"



	// $ANTLR start "selection"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:372:1: selection returns [List<String> lst] : id= ID ( PIPE sel= selection )? ;
	public final List<String> selection() throws RecognitionException {
		List<String> lst = null;


		Token id=null;
		List<String> sel =null;


		lst = new ArrayList<String>();

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:377:2: (id= ID ( PIPE sel= selection )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:377:4: id= ID ( PIPE sel= selection )?
			{
			id=(Token)match(input,ID,FOLLOW_ID_in_selection887); 

			if(!bCollectFacts) {		
			    lst.add((id!=null?id.getText():null));		
			}
				
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:381:5: ( PIPE sel= selection )?
			int alt20=2;
			int LA20_0 = input.LA(1);
			if ( (LA20_0==PIPE) ) {
				alt20=1;
			}
			switch (alt20) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:381:6: PIPE sel= selection
					{
					match(input,PIPE,FOLLOW_PIPE_in_selection893); 
					pushFollow(FOLLOW_selection_in_selection897);
					sel=selection();
					state._fsp--;


					if(!bCollectFacts) {		
					    lst.addAll(sel);	
					}
						
					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return lst;
	}
	// $ANTLR end "selection"


	public static class operator_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "operator"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:388:1: operator : (| ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'NOTWITH' | 'notwith' ) | ( 'THEN' | 'then' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) );
	public final MiniEugeneParser.operator_return operator() throws RecognitionException {
		MiniEugeneParser.operator_return retval = new MiniEugeneParser.operator_return();
		retval.start = input.LT(1);

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:388:9: (| ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'NOTWITH' | 'notwith' ) | ( 'THEN' | 'then' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) )
			int alt21=41;
			switch ( input.LA(1) ) {
			case BOOL_OR:
			case COMMA:
			case DOT:
			case ID:
			case INT:
			case LC_OR:
			case LEFTSBR:
			case LOG_OR:
			case UC_OR:
				{
				alt21=1;
				}
				break;
			case 62:
			case 102:
				{
				alt21=2;
				}
				break;
			case 72:
			case 112:
				{
				alt21=3;
				}
				break;
			case 66:
			case 106:
				{
				alt21=4;
				}
				break;
			case 74:
			case 114:
				{
				alt21=5;
				}
				break;
			case 70:
			case 110:
				{
				alt21=6;
				}
				break;
			case 76:
			case 116:
				{
				alt21=7;
				}
				break;
			case 81:
			case 121:
				{
				alt21=8;
				}
				break;
			case 91:
			case 131:
				{
				alt21=9;
				}
				break;
			case 78:
			case 118:
				{
				alt21=10;
				}
				break;
			case 90:
			case 130:
				{
				alt21=11;
				}
				break;
			case 77:
			case 117:
				{
				alt21=12;
				}
				break;
			case 89:
			case 129:
				{
				alt21=13;
				}
				break;
			case 64:
			case 104:
				{
				alt21=14;
				}
				break;
			case 61:
			case 101:
				{
				alt21=15;
				}
				break;
			case 54:
			case 94:
				{
				alt21=16;
				}
				break;
			case 84:
			case 124:
				{
				alt21=17;
				}
				break;
			case 52:
			case 92:
				{
				alt21=18;
				}
				break;
			case 53:
			case 93:
				{
				alt21=19;
				}
				break;
			case 83:
			case 123:
				{
				alt21=20;
				}
				break;
			case 71:
			case 111:
				{
				alt21=21;
				}
				break;
			case 56:
			case 96:
				{
				alt21=22;
				}
				break;
			case 86:
			case 126:
				{
				alt21=23;
				}
				break;
			case 60:
			case 100:
				{
				alt21=24;
				}
				break;
			case 65:
			case 105:
				{
				alt21=25;
				}
				break;
			case 73:
			case 113:
				{
				alt21=26;
				}
				break;
			case 69:
			case 109:
				{
				alt21=27;
				}
				break;
			case 75:
			case 115:
				{
				alt21=28;
				}
				break;
			case 67:
			case 107:
				{
				alt21=29;
				}
				break;
			case 55:
			case 95:
				{
				alt21=30;
				}
				break;
			case 85:
			case 125:
				{
				alt21=31;
				}
				break;
			case 80:
			case 120:
				{
				alt21=32;
				}
				break;
			case 57:
			case 97:
				{
				alt21=33;
				}
				break;
			case 87:
			case 127:
				{
				alt21=34;
				}
				break;
			case 82:
			case 122:
				{
				alt21=35;
				}
				break;
			case 58:
			case 98:
				{
				alt21=36;
				}
				break;
			case 88:
			case 128:
				{
				alt21=37;
				}
				break;
			case 79:
			case 119:
				{
				alt21=38;
				}
				break;
			case 68:
			case 108:
				{
				alt21=39;
				}
				break;
			case 63:
			case 103:
				{
				alt21=40;
				}
				break;
			case 59:
			case 99:
				{
				alt21=41;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 21, 0, input);
				throw nvae;
			}
			switch (alt21) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:389:2: 
					{
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:389:4: ( 'CONTAINS' | 'contains' )
					{
					if ( input.LA(1)==62||input.LA(1)==102 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:390:4: ( 'NOTCONTAINS' | 'notcontains' )
					{
					if ( input.LA(1)==72||input.LA(1)==112 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 4 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:391:4: ( 'EXACTLY' | 'exactly' )
					{
					if ( input.LA(1)==66||input.LA(1)==106 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 5 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:392:4: ( 'NOTEXACTLY' | 'notexactly' )
					{
					if ( input.LA(1)==74||input.LA(1)==114 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 6 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:393:4: ( 'MORETHAN' | 'morethan' )
					{
					if ( input.LA(1)==70||input.LA(1)==110 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 7 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:394:4: ( 'NOTMORETHAN' | 'notmorethan' )
					{
					if ( input.LA(1)==76||input.LA(1)==116 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 8 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:395:4: ( 'SAME_COUNT' | 'same_count' )
					{
					if ( input.LA(1)==81||input.LA(1)==121 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 9 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:396:4: ( 'WITH' | 'with' )
					{
					if ( input.LA(1)==91||input.LA(1)==131 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 10 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:397:4: ( 'NOTWITH' | 'notwith' )
					{
					if ( input.LA(1)==78||input.LA(1)==118 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 11 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:398:4: ( 'THEN' | 'then' )
					{
					if ( input.LA(1)==90||input.LA(1)==130 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 12 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:399:4: ( 'NOTTHEN' | 'notthen' )
					{
					if ( input.LA(1)==77||input.LA(1)==117 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 13 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:400:4: ( 'STARTSWITH' | 'startswith' )
					{
					if ( input.LA(1)==89||input.LA(1)==129 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 14 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:401:4: ( 'ENDSWITH' | 'endswith' )
					{
					if ( input.LA(1)==64||input.LA(1)==104 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 15 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:402:4: ( 'BEFORE' | 'before' )
					{
					if ( input.LA(1)==61||input.LA(1)==101 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 16 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:403:4: ( 'ALL_BEFORE' | 'all_before' )
					{
					if ( input.LA(1)==54||input.LA(1)==94 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 17 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:404:4: ( 'SOME_BEFORE' | 'some_before' )
					{
					if ( input.LA(1)==84||input.LA(1)==124 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 18 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:405:4: ( 'AFTER' | 'after' )
					{
					if ( input.LA(1)==52||input.LA(1)==92 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 19 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:406:4: ( 'ALL_AFTER' | 'all_after' )
					{
					if ( input.LA(1)==53||input.LA(1)==93 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 20 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:407:4: ( 'SOME_AFTER' | 'some_after' )
					{
					if ( input.LA(1)==83||input.LA(1)==123 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 21 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:408:4: ( 'NEXTTO' | 'nextto' )
					{
					if ( input.LA(1)==71||input.LA(1)==111 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 22 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:409:4: ( 'ALL_NEXTTO' | 'all_nextto' )
					{
					if ( input.LA(1)==56||input.LA(1)==96 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 23 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:410:4: ( 'SOME_NEXTTO' | 'some_nextto' )
					{
					if ( input.LA(1)==86||input.LA(1)==126 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 24 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:411:4: ( 'ALWAYS_NEXTTO' | 'always_nextto' )
					{
					if ( input.LA(1)==60||input.LA(1)==100 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 25 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:412:4: ( 'EQUALS' | 'equals' )
					{
					if ( input.LA(1)==65||input.LA(1)==105 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 26 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:413:4: ( 'NOTEQUALS' | 'notequals' )
					{
					if ( input.LA(1)==73||input.LA(1)==113 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 27 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:414:4: ( 'MATCHES' | 'matches' )
					{
					if ( input.LA(1)==69||input.LA(1)==109 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 28 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:415:4: ( 'NOTMATCHES' | 'notmatches' )
					{
					if ( input.LA(1)==75||input.LA(1)==115 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 29 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:416:4: ( 'FORWARD' | 'forward' )
					{
					if ( input.LA(1)==67||input.LA(1)==107 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 30 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:417:4: ( 'ALL_FORWARD' | 'all_forward' )
					{
					if ( input.LA(1)==55||input.LA(1)==95 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 31 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:418:4: ( 'SOME_FORWARD' | 'some_forward' )
					{
					if ( input.LA(1)==85||input.LA(1)==125 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 32 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:419:4: ( 'REVERSE' | 'reverse' )
					{
					if ( input.LA(1)==80||input.LA(1)==120 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 33 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:420:4: ( 'ALL_REVERSE' | 'all_reverse' )
					{
					if ( input.LA(1)==57||input.LA(1)==97 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 34 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:421:4: ( 'SOME_REVERSE' | 'some_reverse' )
					{
					if ( input.LA(1)==87||input.LA(1)==127 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 35 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:422:4: ( 'SAME_ORIENTATION' | 'same_orientation' )
					{
					if ( input.LA(1)==82||input.LA(1)==122 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 36 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:423:4: ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' )
					{
					if ( input.LA(1)==58||input.LA(1)==98 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 37 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:424:4: ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' )
					{
					if ( input.LA(1)==88||input.LA(1)==128 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 38 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:425:4: ( 'REPRESSES' | 'represses' )
					{
					if ( input.LA(1)==79||input.LA(1)==119 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 39 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:426:4: ( 'INDUCES' | 'induces' )
					{
					if ( input.LA(1)==68||input.LA(1)==108 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 40 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:427:4: ( 'DRIVES' | 'drives' )
					{
					if ( input.LA(1)==63||input.LA(1)==103 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;
				case 41 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:428:4: ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' )
					{
					if ( input.LA(1)==59||input.LA(1)==99 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					}
					break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "operator"


	public static class operand_return extends ParserRuleReturnScope {
	};


	// $ANTLR start "operand"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:431:1: operand : ( ID | INT | LEFTSBR INT RIGHTSBR );
	public final MiniEugeneParser.operand_return operand() throws RecognitionException {
		MiniEugeneParser.operand_return retval = new MiniEugeneParser.operand_return();
		retval.start = input.LT(1);

		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:431:9: ( ID | INT | LEFTSBR INT RIGHTSBR )
			int alt22=3;
			switch ( input.LA(1) ) {
			case ID:
				{
				alt22=1;
				}
				break;
			case INT:
				{
				alt22=2;
				}
				break;
			case LEFTSBR:
				{
				alt22=3;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 22, 0, input);
				throw nvae;
			}
			switch (alt22) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:431:11: ID
					{
					match(input,ID,FOLLOW_ID_in_operand1284); 
					}
					break;
				case 2 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:432:4: INT
					{
					match(input,INT,FOLLOW_INT_in_operand1290); 
					}
					break;
				case 3 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:433:4: LEFTSBR INT RIGHTSBR
					{
					match(input,LEFTSBR,FOLLOW_LEFTSBR_in_operand1295); 
					match(input,INT,FOLLOW_INT_in_operand1297); 
					match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_operand1299); 
					}
					break;

			}
			retval.stop = input.LT(-1);

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return retval;
	}
	// $ANTLR end "operand"



	// $ANTLR start "list_of_parameters"
	// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:436:1: list_of_parameters : operand ( COMMA list_of_parameters )? ;
	public final void list_of_parameters() throws RecognitionException {
		try {
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:437:2: ( operand ( COMMA list_of_parameters )? )
			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:437:4: operand ( COMMA list_of_parameters )?
			{
			pushFollow(FOLLOW_operand_in_list_of_parameters1310);
			operand();
			state._fsp--;

			// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:437:12: ( COMMA list_of_parameters )?
			int alt23=2;
			int LA23_0 = input.LA(1);
			if ( (LA23_0==COMMA) ) {
				alt23=1;
			}
			switch (alt23) {
				case 1 :
					// /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:437:13: COMMA list_of_parameters
					{
					match(input,COMMA,FOLLOW_COMMA_in_list_of_parameters1313); 
					pushFollow(FOLLOW_list_of_parameters_in_list_of_parameters1315);
					list_of_parameters();
					state._fsp--;

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "list_of_parameters"

	// Delegated rules



	public static final BitSet FOLLOW_size_in_miniEugene338 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_DOT_in_miniEugene340 = new BitSet(new long[]{0xFFF0C80026460020L,0xFFFFFFFFFFFFFFFFL,0x000000000000000FL});
	public static final BitSet FOLLOW_statement_in_miniEugene345 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_DOT_in_miniEugene347 = new BitSet(new long[]{0xFFF0C80026460022L,0xFFFFFFFFFFFFFFFFL,0x000000000000000FL});
	public static final BitSet FOLLOW_fact_in_statement366 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constraint_specification_in_statement372 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_fact391 = new BitSet(new long[]{0x0000040000200000L});
	public static final BitSet FOLLOW_set_in_fact393 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_ID_in_fact401 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_or_constraint_in_constraint_specification422 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_composite_constraint_in_constraint_specification431 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_MIN_SIZE_in_size451 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_EQUALS_in_size453 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_INT_in_size457 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_DOT_in_size459 = new BitSet(new long[]{0x0000000200000000L});
	public static final BitSet FOLLOW_N_SIZE_in_size470 = new BitSet(new long[]{0x0000000000001000L});
	public static final BitSet FOLLOW_EQUALS_in_size472 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_INT_in_size476 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_composite_constraint494 = new BitSet(new long[]{0x0008000010000000L});
	public static final BitSet FOLLOW_LEFTP_in_composite_constraint498 = new BitSet(new long[]{0x0000000020060000L});
	public static final BitSet FOLLOW_list_of_parameters_in_composite_constraint500 = new BitSet(new long[]{0x0000002000000000L});
	public static final BitSet FOLLOW_RIGHTP_in_composite_constraint502 = new BitSet(new long[]{0x0008000000000000L});
	public static final BitSet FOLLOW_51_in_composite_constraint507 = new BitSet(new long[]{0xFFF0C80026460020L,0xFFFFFFFFFFFFFFFFL,0x000000000000000FL});
	public static final BitSet FOLLOW_composite_constraint_block_in_composite_constraint509 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_DOT_in_composite_constraint511 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constraint_in_composite_constraint_block527 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_composite_constraint_block530 = new BitSet(new long[]{0xFFF0C80026460020L,0xFFFFFFFFFFFFFFFFL,0x000000000000000FL});
	public static final BitSet FOLLOW_composite_constraint_block_in_composite_constraint_block532 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_constraint_in_or_constraint572 = new BitSet(new long[]{0x0000100080800042L});
	public static final BitSet FOLLOW_set_in_or_constraint577 = new BitSet(new long[]{0xFFF0C80026460020L,0xFFFFFFFFFFFFFFFFL,0x000000000000000FL});
	public static final BitSet FOLLOW_or_constraint_in_or_constraint589 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_constraint625 = new BitSet(new long[]{0xFFF0000020060000L,0xFFFFFFFFFFFFFFFFL,0x000000000000000FL});
	public static final BitSet FOLLOW_operand_in_constraint640 = new BitSet(new long[]{0xFFF0000020060000L,0xFFFFFFFFFFFFFFFFL,0x000000000000000FL});
	public static final BitSet FOLLOW_operator_in_constraint648 = new BitSet(new long[]{0x0000000020060002L});
	public static final BitSet FOLLOW_operand_in_constraint655 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_templatingConstraints_in_constraint668 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_templateConstraint_in_templatingConstraints699 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_sequenceConstraint_in_templatingConstraints708 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_templateConstraint731 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COLON_in_templateConstraint733 = new BitSet(new long[]{0x0000880004400020L});
	public static final BitSet FOLLOW_set_in_templateConstraint748 = new BitSet(new long[]{0x0000000020020000L});
	public static final BitSet FOLLOW_list_of_ids_in_templateConstraint756 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_sequenceConstraint778 = new BitSet(new long[]{0x0000000000000100L});
	public static final BitSet FOLLOW_COLON_in_sequenceConstraint780 = new BitSet(new long[]{0x0000480002400020L});
	public static final BitSet FOLLOW_set_in_sequenceConstraint795 = new BitSet(new long[]{0x0000000020020000L});
	public static final BitSet FOLLOW_list_of_ids_in_sequenceConstraint803 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_list_of_ids837 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_LEFTSBR_in_list_of_ids842 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_selection_in_list_of_ids846 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_list_of_ids850 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_list_of_ids856 = new BitSet(new long[]{0x0000000020020000L});
	public static final BitSet FOLLOW_list_of_ids_in_list_of_ids860 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_selection887 = new BitSet(new long[]{0x0000000800000002L});
	public static final BitSet FOLLOW_PIPE_in_selection893 = new BitSet(new long[]{0x0000000000020000L});
	public static final BitSet FOLLOW_selection_in_selection897 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator916 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator925 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator934 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator943 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator952 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator961 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator970 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator979 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator988 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator997 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1006 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1015 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1024 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1033 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1042 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1051 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1060 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1069 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1078 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1087 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1096 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1105 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1114 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1123 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1132 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1141 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1150 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1159 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1168 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1177 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1186 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1195 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1204 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1213 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1222 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1231 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1240 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1249 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1258 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_operator1267 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_ID_in_operand1284 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_INT_in_operand1290 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_LEFTSBR_in_operand1295 = new BitSet(new long[]{0x0000000000040000L});
	public static final BitSet FOLLOW_INT_in_operand1297 = new BitSet(new long[]{0x0000004000000000L});
	public static final BitSet FOLLOW_RIGHTSBR_in_operand1299 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_operand_in_list_of_parameters1310 = new BitSet(new long[]{0x0000000000000202L});
	public static final BitSet FOLLOW_COMMA_in_list_of_parameters1313 = new BitSet(new long[]{0x0000000020060000L});
	public static final BitSet FOLLOW_list_of_parameters_in_list_of_parameters1315 = new BitSet(new long[]{0x0000000000000002L});
}
