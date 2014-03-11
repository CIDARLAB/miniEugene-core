// $ANTLR 3.4 /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g 2014-03-11 13:53:26

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


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class MiniEugeneParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CHAR", "COMMENT", "ESC_SEQ", "EXPONENT", "HEX_DIGIT", "ID", "INT", "OCTAL_ESC", "STRING", "UNICODE_ESC", "WS", "'('", "')'", "','", "'.'", "':='", "'='", "'AFTER'", "'ALL_AFTER'", "'ALL_BEFORE'", "'ALL_FORWARD'", "'ALL_NEXTTO'", "'ALL_REVERSE'", "'BEFORE'", "'CONTAINS'", "'DRIVES'", "'ENDSWITH'", "'EQUALS'", "'EXACTLY'", "'FORWARD'", "'INDUCES'", "'MATCHES'", "'MORETHAN'", "'N'", "'NEXTTO'", "'NOT'", "'NOTCONTAINS'", "'NOTEQUALS'", "'NOTEXACTLY'", "'NOTMATCHES'", "'NOTMORETHAN'", "'NOTTHEN'", "'NOTWITH'", "'OR'", "'REPRESSES'", "'REVERSE'", "'SAME_ORIENTATION'", "'SOME_AFTER'", "'SOME_BEFORE'", "'SOME_FORWARD'", "'SOME_NEXTTO'", "'SOME_REVERSE'", "'STARTSWITH'", "'THEN'", "'WITH'", "'['", "'\\\\/'", "']'", "'after'", "'all_after'", "'all_before'", "'all_forward'", "'all_nextto'", "'all_reverse'", "'before'", "'contains'", "'drives'", "'endswith'", "'equals'", "'exactly'", "'forward'", "'induces'", "'matches'", "'morethan'", "'nextto'", "'notcontains'", "'notequals'", "'notexactly'", "'notmatches'", "'notmorethan'", "'notthen'", "'notwith'", "'or'", "'represses'", "'reverse'", "'same_orientation'", "'some_after'", "'some_before'", "'some_forward'", "'some_nextto'", "'some_reverse'", "'startswith'", "'then'", "'with'"
    };

    public static final int EOF=-1;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__19=19;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int T__22=22;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int T__29=29;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int T__35=35;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
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
    public static final int CHAR=4;
    public static final int COMMENT=5;
    public static final int ESC_SEQ=6;
    public static final int EXPONENT=7;
    public static final int HEX_DIGIT=8;
    public static final int ID=9;
    public static final int INT=10;
    public static final int OCTAL_ESC=11;
    public static final int STRING=12;
    public static final int UNICODE_ESC=13;
    public static final int WS=14;

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

    public String[] getTokenNames() { return MiniEugeneParser.tokenNames; }
    public String getGrammarFileName() { return "/Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g"; }



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




    // $ANTLR start "miniEugene"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:107:1: miniEugene : ( size )? (c= or_constraint '.' | composite_constraint )+ ;
    public final void miniEugene() throws EugeneException, RecognitionException {
        List<Predicate> c =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:109:2: ( ( size )? (c= or_constraint '.' | composite_constraint )+ )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:109:4: ( size )? (c= or_constraint '.' | composite_constraint )+
            {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:109:4: ( size )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==37) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:109:5: size
                    {
                    pushFollow(FOLLOW_size_in_miniEugene45);
                    size();

                    state._fsp--;


                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:109:12: (c= or_constraint '.' | composite_constraint )+
            int cnt2=0;
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==INT||LA2_0==18||(LA2_0 >= 21 && LA2_0 <= 36)||(LA2_0 >= 38 && LA2_0 <= 60)||(LA2_0 >= 62 && LA2_0 <= 97)) ) {
                    alt2=1;
                }
                else if ( (LA2_0==ID) ) {
                    int LA2_3 = input.LA(2);

                    if ( ((LA2_3 >= ID && LA2_3 <= INT)||LA2_3==18||(LA2_3 >= 21 && LA2_3 <= 36)||LA2_3==38||(LA2_3 >= 40 && LA2_3 <= 60)||(LA2_3 >= 62 && LA2_3 <= 97)) ) {
                        alt2=1;
                    }
                    else if ( (LA2_3==15||LA2_3==19) ) {
                        alt2=2;
                    }


                }


                switch (alt2) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:109:13: c= or_constraint '.'
            	    {
            	    pushFollow(FOLLOW_or_constraint_in_miniEugene52);
            	    c=or_constraint();

            	    state._fsp--;



            	    if(c.size() == 1) {
            	        // ``store'' the predicate
            	        this.addPredicate(c.get(0));   
            	    } else {
            	        this.addPredicate(new LogicalOr(c));   
            	    }	
            	    	

            	    match(input,18,FOLLOW_18_in_miniEugene55); 

            	    }
            	    break;
            	case 2 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:116:8: composite_constraint
            	    {
            	    pushFollow(FOLLOW_composite_constraint_in_miniEugene58);
            	    composite_constraint();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "miniEugene"



    // $ANTLR start "size"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:119:1: size : 'N' '=' n= INT '.' ;
    public final void size() throws EugeneException, RecognitionException {
        Token n=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:121:2: ( 'N' '=' n= INT '.' )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:121:5: 'N' '=' n= INT '.'
            {
            match(input,37,FOLLOW_37_in_size79); 

            match(input,20,FOLLOW_20_in_size81); 

            n=(Token)match(input,INT,FOLLOW_INT_in_size85); 

            match(input,18,FOLLOW_18_in_size87); 


            this.N = Integer.parseInt((n!=null?n.getText():null));
            this.interp = new Interp(this.symbols, this.N);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "size"



    // $ANTLR start "composite_constraint"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:127:1: composite_constraint : ID ( '(' list_of_parameters ')' )? ':=' composite_constraint_block '.' ;
    public final void composite_constraint() throws EugeneException, RecognitionException {
        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:129:2: ( ID ( '(' list_of_parameters ')' )? ':=' composite_constraint_block '.' )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:129:4: ID ( '(' list_of_parameters ')' )? ':=' composite_constraint_block '.'
            {
            match(input,ID,FOLLOW_ID_in_composite_constraint105); 

            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:129:7: ( '(' list_of_parameters ')' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==15) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:129:9: '(' list_of_parameters ')'
                    {
                    match(input,15,FOLLOW_15_in_composite_constraint109); 

                    pushFollow(FOLLOW_list_of_parameters_in_composite_constraint111);
                    list_of_parameters();

                    state._fsp--;


                    match(input,16,FOLLOW_16_in_composite_constraint113); 

                    }
                    break;

            }


            match(input,19,FOLLOW_19_in_composite_constraint118); 

            pushFollow(FOLLOW_composite_constraint_block_in_composite_constraint120);
            composite_constraint_block();

            state._fsp--;


            match(input,18,FOLLOW_18_in_composite_constraint122); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
        }
        return ;
    }
    // $ANTLR end "composite_constraint"



    // $ANTLR start "composite_constraint_block"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:132:1: composite_constraint_block : constraint ( ',' composite_constraint_block )? ;
    public final void composite_constraint_block() throws EugeneException, RecognitionException {
        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:134:2: ( constraint ( ',' composite_constraint_block )? )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:134:4: constraint ( ',' composite_constraint_block )?
            {
            pushFollow(FOLLOW_constraint_in_composite_constraint_block138);
            constraint();

            state._fsp--;


            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:134:15: ( ',' composite_constraint_block )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==17) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:134:16: ',' composite_constraint_block
                    {
                    match(input,17,FOLLOW_17_in_composite_constraint_block141); 

                    pushFollow(FOLLOW_composite_constraint_block_in_composite_constraint_block143);
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
        return ;
    }
    // $ANTLR end "composite_constraint_block"



    // $ANTLR start "or_constraint"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:137:1: or_constraint returns [List<Predicate> lst] : c= constraint ( ( 'OR' | '\\\\/' | 'or' ) o= or_constraint )? ;
    public final List<Predicate> or_constraint() throws EugeneException, RecognitionException {
        List<Predicate> lst = null;


        Predicate c =null;

        List<Predicate> o =null;



        lst = new ArrayList<Predicate>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:143:2: (c= constraint ( ( 'OR' | '\\\\/' | 'or' ) o= or_constraint )? )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:143:4: c= constraint ( ( 'OR' | '\\\\/' | 'or' ) o= or_constraint )?
            {
            pushFollow(FOLLOW_constraint_in_or_constraint183);
            c=constraint();

            state._fsp--;



            lst.add(c);
            	

            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:145:4: ( ( 'OR' | '\\\\/' | 'or' ) o= or_constraint )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==47||LA5_0==60||LA5_0==86) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:145:5: ( 'OR' | '\\\\/' | 'or' ) o= or_constraint
                    {
                    if ( input.LA(1)==47||input.LA(1)==60||input.LA(1)==86 ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_or_constraint_in_or_constraint198);
                    o=or_constraint();

                    state._fsp--;



                    lst.addAll(o);
                    	

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
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:150:1: constraint returns [Predicate p] : (not= 'NOT' )? (lhs= operand )? op= operator (rhs= operand )? ;
    public final Predicate constraint() throws EugeneException, RecognitionException {
        Predicate p = null;


        Token not=null;
        MiniEugeneParser.operand_return lhs =null;

        MiniEugeneParser.operator_return op =null;

        MiniEugeneParser.operand_return rhs =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:153:2: ( (not= 'NOT' )? (lhs= operand )? op= operator (rhs= operand )? )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:153:4: (not= 'NOT' )? (lhs= operand )? op= operator (rhs= operand )?
            {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:153:4: (not= 'NOT' )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==39) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:153:5: not= 'NOT'
                    {
                    not=(Token)match(input,39,FOLLOW_39_in_constraint234); 


                    addToken("NOT");
                    	

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:155:6: (lhs= operand )?
            int alt7=2;
            switch ( input.LA(1) ) {
                case ID:
                    {
                    alt7=1;
                    }
                    break;
                case INT:
                    {
                    alt7=1;
                    }
                    break;
                case 59:
                    {
                    int LA7_3 = input.LA(2);

                    if ( (LA7_3==INT) ) {
                        int LA7_5 = input.LA(3);

                        if ( (LA7_5==61) ) {
                            alt7=1;
                        }
                    }
                    }
                    break;
            }

            switch (alt7) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:155:7: lhs= operand
                    {
                    pushFollow(FOLLOW_operand_in_constraint243);
                    lhs=operand();

                    state._fsp--;



                    addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));	
                    	

                    }
                    break;

            }


            pushFollow(FOLLOW_operator_in_constraint251);
            op=operator();

            state._fsp--;



            addToken((op!=null?input.toString(op.start,op.stop):null));	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:159:4: (rhs= operand )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( ((LA8_0 >= ID && LA8_0 <= INT)||LA8_0==59) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:159:5: rhs= operand
                    {
                    pushFollow(FOLLOW_operand_in_constraint258);
                    rhs=operand();

                    state._fsp--;



                    addToken((rhs!=null?input.toString(rhs.start,rhs.stop):null));	
                    	

                    }
                    break;

            }




            // turn the tokens into a miniEugene predicate	
            p = this.interp.interpreteRule(this.tokens);

            // reset the global token array
            this.tokens = null;
            	

            }

        }
        catch (EugeneException e) {

            throw new EugeneException(e.getMessage());	
            	
        }

        finally {
        	// do for sure before leaving
        }
        return p;
    }
    // $ANTLR end "constraint"


    public static class operator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "operator"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:174:1: operator : (| ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'WITH' | 'with' ) | ( 'NOTWITH' | 'notwith' ) | ( 'THEN' | 'then' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) );
    public final MiniEugeneParser.operator_return operator() throws RecognitionException {
        MiniEugeneParser.operator_return retval = new MiniEugeneParser.operator_return();
        retval.start = input.LT(1);


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:174:9: (| ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'WITH' | 'with' ) | ( 'NOTWITH' | 'notwith' ) | ( 'THEN' | 'then' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) )
            int alt9=36;
            switch ( input.LA(1) ) {
            case ID:
            case INT:
            case 17:
            case 18:
            case 47:
            case 59:
            case 60:
            case 86:
                {
                alt9=1;
                }
                break;
            case 28:
            case 69:
                {
                alt9=2;
                }
                break;
            case 40:
            case 79:
                {
                alt9=3;
                }
                break;
            case 32:
            case 73:
                {
                alt9=4;
                }
                break;
            case 42:
            case 81:
                {
                alt9=5;
                }
                break;
            case 36:
            case 77:
                {
                alt9=6;
                }
                break;
            case 44:
            case 83:
                {
                alt9=7;
                }
                break;
            case 58:
            case 97:
                {
                alt9=8;
                }
                break;
            case 46:
            case 85:
                {
                alt9=9;
                }
                break;
            case 57:
            case 96:
                {
                alt9=10;
                }
                break;
            case 45:
            case 84:
                {
                alt9=11;
                }
                break;
            case 56:
            case 95:
                {
                alt9=12;
                }
                break;
            case 30:
            case 71:
                {
                alt9=13;
                }
                break;
            case 27:
            case 68:
                {
                alt9=14;
                }
                break;
            case 23:
            case 64:
                {
                alt9=15;
                }
                break;
            case 52:
            case 91:
                {
                alt9=16;
                }
                break;
            case 21:
            case 62:
                {
                alt9=17;
                }
                break;
            case 22:
            case 63:
                {
                alt9=18;
                }
                break;
            case 51:
            case 90:
                {
                alt9=19;
                }
                break;
            case 38:
            case 78:
                {
                alt9=20;
                }
                break;
            case 25:
            case 66:
                {
                alt9=21;
                }
                break;
            case 54:
            case 93:
                {
                alt9=22;
                }
                break;
            case 31:
            case 72:
                {
                alt9=23;
                }
                break;
            case 41:
            case 80:
                {
                alt9=24;
                }
                break;
            case 35:
            case 76:
                {
                alt9=25;
                }
                break;
            case 43:
            case 82:
                {
                alt9=26;
                }
                break;
            case 33:
            case 74:
                {
                alt9=27;
                }
                break;
            case 24:
            case 65:
                {
                alt9=28;
                }
                break;
            case 53:
            case 92:
                {
                alt9=29;
                }
                break;
            case 49:
            case 88:
                {
                alt9=30;
                }
                break;
            case 26:
            case 67:
                {
                alt9=31;
                }
                break;
            case 55:
            case 94:
                {
                alt9=32;
                }
                break;
            case 50:
            case 89:
                {
                alt9=33;
                }
                break;
            case 48:
            case 87:
                {
                alt9=34;
                }
                break;
            case 34:
            case 75:
                {
                alt9=35;
                }
                break;
            case 29:
            case 70:
                {
                alt9=36;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:175:2: 
                    {
                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:175:4: ( 'CONTAINS' | 'contains' )
                    {
                    if ( input.LA(1)==28||input.LA(1)==69 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:176:4: ( 'NOTCONTAINS' | 'notcontains' )
                    {
                    if ( input.LA(1)==40||input.LA(1)==79 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:177:4: ( 'EXACTLY' | 'exactly' )
                    {
                    if ( input.LA(1)==32||input.LA(1)==73 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:178:4: ( 'NOTEXACTLY' | 'notexactly' )
                    {
                    if ( input.LA(1)==42||input.LA(1)==81 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:179:4: ( 'MORETHAN' | 'morethan' )
                    {
                    if ( input.LA(1)==36||input.LA(1)==77 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:180:4: ( 'NOTMORETHAN' | 'notmorethan' )
                    {
                    if ( input.LA(1)==44||input.LA(1)==83 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:181:4: ( 'WITH' | 'with' )
                    {
                    if ( input.LA(1)==58||input.LA(1)==97 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:182:4: ( 'NOTWITH' | 'notwith' )
                    {
                    if ( input.LA(1)==46||input.LA(1)==85 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:183:4: ( 'THEN' | 'then' )
                    {
                    if ( input.LA(1)==57||input.LA(1)==96 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:184:4: ( 'NOTTHEN' | 'notthen' )
                    {
                    if ( input.LA(1)==45||input.LA(1)==84 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:185:4: ( 'STARTSWITH' | 'startswith' )
                    {
                    if ( input.LA(1)==56||input.LA(1)==95 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:186:4: ( 'ENDSWITH' | 'endswith' )
                    {
                    if ( input.LA(1)==30||input.LA(1)==71 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:187:4: ( 'BEFORE' | 'before' )
                    {
                    if ( input.LA(1)==27||input.LA(1)==68 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:188:4: ( 'ALL_BEFORE' | 'all_before' )
                    {
                    if ( input.LA(1)==23||input.LA(1)==64 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:189:4: ( 'SOME_BEFORE' | 'some_before' )
                    {
                    if ( input.LA(1)==52||input.LA(1)==91 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:190:4: ( 'AFTER' | 'after' )
                    {
                    if ( input.LA(1)==21||input.LA(1)==62 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:191:4: ( 'ALL_AFTER' | 'all_after' )
                    {
                    if ( input.LA(1)==22||input.LA(1)==63 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:192:4: ( 'SOME_AFTER' | 'some_after' )
                    {
                    if ( input.LA(1)==51||input.LA(1)==90 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:193:4: ( 'NEXTTO' | 'nextto' )
                    {
                    if ( input.LA(1)==38||input.LA(1)==78 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:194:4: ( 'ALL_NEXTTO' | 'all_nextto' )
                    {
                    if ( input.LA(1)==25||input.LA(1)==66 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:195:4: ( 'SOME_NEXTTO' | 'some_nextto' )
                    {
                    if ( input.LA(1)==54||input.LA(1)==93 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:196:4: ( 'EQUALS' | 'equals' )
                    {
                    if ( input.LA(1)==31||input.LA(1)==72 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:197:4: ( 'NOTEQUALS' | 'notequals' )
                    {
                    if ( input.LA(1)==41||input.LA(1)==80 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:198:4: ( 'MATCHES' | 'matches' )
                    {
                    if ( input.LA(1)==35||input.LA(1)==76 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:199:4: ( 'NOTMATCHES' | 'notmatches' )
                    {
                    if ( input.LA(1)==43||input.LA(1)==82 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:200:4: ( 'FORWARD' | 'forward' )
                    {
                    if ( input.LA(1)==33||input.LA(1)==74 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:201:4: ( 'ALL_FORWARD' | 'all_forward' )
                    {
                    if ( input.LA(1)==24||input.LA(1)==65 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:202:4: ( 'SOME_FORWARD' | 'some_forward' )
                    {
                    if ( input.LA(1)==53||input.LA(1)==92 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:203:4: ( 'REVERSE' | 'reverse' )
                    {
                    if ( input.LA(1)==49||input.LA(1)==88 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:204:4: ( 'ALL_REVERSE' | 'all_reverse' )
                    {
                    if ( input.LA(1)==26||input.LA(1)==67 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:205:4: ( 'SOME_REVERSE' | 'some_reverse' )
                    {
                    if ( input.LA(1)==55||input.LA(1)==94 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:206:4: ( 'SAME_ORIENTATION' | 'same_orientation' )
                    {
                    if ( input.LA(1)==50||input.LA(1)==89 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:207:4: ( 'REPRESSES' | 'represses' )
                    {
                    if ( input.LA(1)==48||input.LA(1)==87 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:208:4: ( 'INDUCES' | 'induces' )
                    {
                    if ( input.LA(1)==34||input.LA(1)==75 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:209:4: ( 'DRIVES' | 'drives' )
                    {
                    if ( input.LA(1)==29||input.LA(1)==70 ) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:212:1: operand : ( ID | INT | '[' INT ']' );
    public final MiniEugeneParser.operand_return operand() throws RecognitionException {
        MiniEugeneParser.operand_return retval = new MiniEugeneParser.operand_return();
        retval.start = input.LT(1);


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:212:9: ( ID | INT | '[' INT ']' )
            int alt10=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt10=1;
                }
                break;
            case INT:
                {
                alt10=2;
                }
                break;
            case 59:
                {
                alt10=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:212:11: ID
                    {
                    match(input,ID,FOLLOW_ID_in_operand605); 

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:213:4: INT
                    {
                    match(input,INT,FOLLOW_INT_in_operand611); 

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:214:4: '[' INT ']'
                    {
                    match(input,59,FOLLOW_59_in_operand616); 

                    match(input,INT,FOLLOW_INT_in_operand618); 

                    match(input,61,FOLLOW_61_in_operand620); 

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
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:217:1: list_of_parameters : operand ( ',' list_of_parameters )? ;
    public final void list_of_parameters() throws RecognitionException {
        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:218:2: ( operand ( ',' list_of_parameters )? )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:218:4: operand ( ',' list_of_parameters )?
            {
            pushFollow(FOLLOW_operand_in_list_of_parameters631);
            operand();

            state._fsp--;


            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:218:12: ( ',' list_of_parameters )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==17) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:218:13: ',' list_of_parameters
                    {
                    match(input,17,FOLLOW_17_in_list_of_parameters634); 

                    pushFollow(FOLLOW_list_of_parameters_in_list_of_parameters636);
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
        return ;
    }
    // $ANTLR end "list_of_parameters"

    // Delegated rules


 

    public static final BitSet FOLLOW_size_in_miniEugene45 = new BitSet(new long[]{0xCFFF7FDFFFE00600L,0x00000003FFBFFFFFL});
    public static final BitSet FOLLOW_or_constraint_in_miniEugene52 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_miniEugene55 = new BitSet(new long[]{0xCFFF7FDFFFE00602L,0x00000003FFBFFFFFL});
    public static final BitSet FOLLOW_composite_constraint_in_miniEugene58 = new BitSet(new long[]{0xCFFF7FDFFFE00602L,0x00000003FFBFFFFFL});
    public static final BitSet FOLLOW_37_in_size79 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_size81 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_INT_in_size85 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_size87 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_composite_constraint105 = new BitSet(new long[]{0x0000000000088000L});
    public static final BitSet FOLLOW_15_in_composite_constraint109 = new BitSet(new long[]{0x0800000000000600L});
    public static final BitSet FOLLOW_list_of_parameters_in_composite_constraint111 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_composite_constraint113 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_composite_constraint118 = new BitSet(new long[]{0xCFFF7FDFFFE00600L,0x00000003FFBFFFFFL});
    public static final BitSet FOLLOW_composite_constraint_block_in_composite_constraint120 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_composite_constraint122 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_composite_constraint_block138 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_composite_constraint_block141 = new BitSet(new long[]{0xCFFF7FDFFFE00600L,0x00000003FFBFFFFFL});
    public static final BitSet FOLLOW_composite_constraint_block_in_composite_constraint_block143 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_or_constraint183 = new BitSet(new long[]{0x1000800000000002L,0x0000000000400000L});
    public static final BitSet FOLLOW_set_in_or_constraint188 = new BitSet(new long[]{0xCFFF7FDFFFE00600L,0x00000003FFBFFFFFL});
    public static final BitSet FOLLOW_or_constraint_in_or_constraint198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_constraint234 = new BitSet(new long[]{0xCFFF7F5FFFE00600L,0x00000003FFBFFFFFL});
    public static final BitSet FOLLOW_operand_in_constraint243 = new BitSet(new long[]{0xCFFF7F5FFFE00600L,0x00000003FFBFFFFFL});
    public static final BitSet FOLLOW_operator_in_constraint251 = new BitSet(new long[]{0x0800000000000602L});
    public static final BitSet FOLLOW_operand_in_constraint258 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator372 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator390 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator399 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator417 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator426 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator435 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator453 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator489 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator516 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator525 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator570 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator579 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operand605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_operand611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_operand616 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_INT_in_operand618 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_operand620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operand_in_list_of_parameters631 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_list_of_parameters634 = new BitSet(new long[]{0x0800000000000600L});
    public static final BitSet FOLLOW_list_of_parameters_in_list_of_parameters636 = new BitSet(new long[]{0x0000000000000002L});

}