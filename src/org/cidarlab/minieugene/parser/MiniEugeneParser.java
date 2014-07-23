// $ANTLR 3.4 /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g 2014-07-23 13:08:51

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

@SuppressWarnings({"all", "warnings", "unchecked"})
public class MiniEugeneParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BOOL_AND", "BOOL_NOT", "BOOL_OR", "CHAR", "COMMA", "COMMENT", "DOT", "EQUALS", "ESC_SEQ", "EXPONENT", "FallThrough", "HEX_DIGIT", "ID", "INT", "LC_AND", "LC_GROUP", "LC_IS_A", "LC_NOT", "LC_OR", "LC_PATTERN", "LC_SEQUENCE", "LC_TEMPLATE", "LEFTCUR", "LEFTP", "LEFTSBR", "LOG_AND", "LOG_OR", "MIN_SIZE", "N_SIZE", "OCTAL_ESC", "PIPE", "RIGHTCUR", "RIGHTP", "RIGHTSBR", "STRING", "UC_AND", "UC_GROUP", "UC_IS_A", "UC_NOT", "UC_OR", "UC_PATTERN", "UC_SEQUENCE", "UC_TEMPLATE", "UNDERS", "UNICODE_ESC", "WS", "':='", "'AFTER'", "'ALL_AFTER'", "'ALL_BEFORE'", "'ALL_FORWARD'", "'ALL_NEXTTO'", "'ALL_REVERSE'", "'ALL_SAME_ORIENTATION'", "'ALTERNATE_ORIENTATION'", "'ALWAYS_NEXTTO'", "'BEFORE'", "'CONTAINS'", "'DRIVES'", "'ENDSWITH'", "'EQUALS'", "'EXACTLY'", "'FORWARD'", "'INDUCES'", "'MATCHES'", "'MORETHAN'", "'NEXTTO'", "'NOTCONTAINS'", "'NOTEQUALS'", "'NOTEXACTLY'", "'NOTMATCHES'", "'NOTMORETHAN'", "'NOTTHEN'", "'NOTWITH'", "'REPRESSES'", "'REVERSE'", "'SAME_COUNT'", "'SAME_ORIENTATION'", "'SOME_AFTER'", "'SOME_BEFORE'", "'SOME_FORWARD'", "'SOME_NEXTTO'", "'SOME_REVERSE'", "'SOME_SAME_ORIENTATION'", "'STARTSWITH'", "'THEN'", "'WITH'", "'after'", "'all_after'", "'all_before'", "'all_forward'", "'all_nextto'", "'all_reverse'", "'all_same_orientation'", "'alternate_orientation'", "'always_nextto'", "'before'", "'contains'", "'drives'", "'endswith'", "'equals'", "'exactly'", "'forward'", "'induces'", "'matches'", "'morethan'", "'nextto'", "'notcontains'", "'notequals'", "'notexactly'", "'notmatches'", "'notmorethan'", "'notthen'", "'notwith'", "'represses'", "'reverse'", "'same_count'", "'same_orientation'", "'some_after'", "'some_before'", "'some_forward'", "'some_nextto'", "'some_reverse'", "'some_same_orientation'", "'startswith'", "'then'", "'with'"
    };

    public static final int EOF=-1;
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
    public static final int BOOL_AND=4;
    public static final int BOOL_NOT=5;
    public static final int BOOL_OR=6;
    public static final int CHAR=7;
    public static final int COMMA=8;
    public static final int COMMENT=9;
    public static final int DOT=10;
    public static final int EQUALS=11;
    public static final int ESC_SEQ=12;
    public static final int EXPONENT=13;
    public static final int FallThrough=14;
    public static final int HEX_DIGIT=15;
    public static final int ID=16;
    public static final int INT=17;
    public static final int LC_AND=18;
    public static final int LC_GROUP=19;
    public static final int LC_IS_A=20;
    public static final int LC_NOT=21;
    public static final int LC_OR=22;
    public static final int LC_PATTERN=23;
    public static final int LC_SEQUENCE=24;
    public static final int LC_TEMPLATE=25;
    public static final int LEFTCUR=26;
    public static final int LEFTP=27;
    public static final int LEFTSBR=28;
    public static final int LOG_AND=29;
    public static final int LOG_OR=30;
    public static final int MIN_SIZE=31;
    public static final int N_SIZE=32;
    public static final int OCTAL_ESC=33;
    public static final int PIPE=34;
    public static final int RIGHTCUR=35;
    public static final int RIGHTP=36;
    public static final int RIGHTSBR=37;
    public static final int STRING=38;
    public static final int UC_AND=39;
    public static final int UC_GROUP=40;
    public static final int UC_IS_A=41;
    public static final int UC_NOT=42;
    public static final int UC_OR=43;
    public static final int UC_PATTERN=44;
    public static final int UC_SEQUENCE=45;
    public static final int UC_TEMPLATE=46;
    public static final int UNDERS=47;
    public static final int UNICODE_ESC=48;
    public static final int WS=49;

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
    public String getGrammarFileName() { return "/Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g"; }



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
    	this.symbols.print();
    }



    // $ANTLR start "miniEugene"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:191:1: miniEugene : ( size DOT )? ( statement DOT )+ ;
    public final void miniEugene() throws RecognitionException, MiniEugeneException {
        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:193:2: ( ( size DOT )? ( statement DOT )+ )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:193:4: ( size DOT )? ( statement DOT )+
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:193:4: ( size DOT )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( ((LA1_0 >= MIN_SIZE && LA1_0 <= N_SIZE)) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:193:5: size DOT
                    {
                    pushFollow(FOLLOW_size_in_miniEugene330);
                    size();

                    state._fsp--;


                    match(input,DOT,FOLLOW_DOT_in_miniEugene332); 

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:193:16: ( statement DOT )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= BOOL_NOT && LA2_0 <= BOOL_OR)||LA2_0==COMMA||LA2_0==DOT||(LA2_0 >= ID && LA2_0 <= INT)||LA2_0==LC_GROUP||(LA2_0 >= LC_NOT && LA2_0 <= LC_TEMPLATE)||LA2_0==LEFTSBR||LA2_0==LOG_OR||LA2_0==UC_GROUP||(LA2_0 >= UC_NOT && LA2_0 <= UC_TEMPLATE)||(LA2_0 >= 51 && LA2_0 <= 130)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:193:17: statement DOT
            	    {
            	    pushFollow(FOLLOW_statement_in_miniEugene337);
            	    statement();

            	    state._fsp--;


            	    match(input,DOT,FOLLOW_DOT_in_miniEugene339); 

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



    // $ANTLR start "statement"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:196:1: statement : ( fact | constraint_specification );
    public final void statement() throws RecognitionException, MiniEugeneException {
        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:198:2: ( fact | constraint_specification )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==ID) ) {
                int LA3_1 = input.LA(2);

                if ( (LA3_1==LC_IS_A||LA3_1==UC_IS_A) ) {
                    alt3=1;
                }
                else if ( ((LA3_1 >= BOOL_NOT && LA3_1 <= BOOL_OR)||LA3_1==DOT||(LA3_1 >= ID && LA3_1 <= INT)||LA3_1==LC_GROUP||(LA3_1 >= LC_NOT && LA3_1 <= LC_TEMPLATE)||(LA3_1 >= LEFTP && LA3_1 <= LEFTSBR)||LA3_1==LOG_OR||LA3_1==UC_GROUP||(LA3_1 >= UC_NOT && LA3_1 <= UC_TEMPLATE)||(LA3_1 >= 50 && LA3_1 <= 130)) ) {
                    alt3=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 3, 1, input);

                    throw nvae;

                }
            }
            else if ( ((LA3_0 >= BOOL_NOT && LA3_0 <= BOOL_OR)||LA3_0==DOT||LA3_0==INT||LA3_0==LC_GROUP||(LA3_0 >= LC_NOT && LA3_0 <= LC_TEMPLATE)||LA3_0==LEFTSBR||LA3_0==LOG_OR||LA3_0==UC_GROUP||(LA3_0 >= UC_NOT && LA3_0 <= UC_TEMPLATE)||(LA3_0 >= 51 && LA3_0 <= 130)) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }
            switch (alt3) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:198:5: fact
                    {
                    pushFollow(FOLLOW_fact_in_statement358);
                    fact();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:199:5: constraint_specification
                    {
                    pushFollow(FOLLOW_constraint_specification_in_statement364);
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
        return ;
    }
    // $ANTLR end "statement"



    // $ANTLR start "fact"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:202:1: fact : c= ID ( UC_IS_A | LC_IS_A ) t= ID ;
    public final void fact() throws RecognitionException, MiniEugeneException {
        Token c=null;
        Token t=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:204:2: (c= ID ( UC_IS_A | LC_IS_A ) t= ID )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:204:4: c= ID ( UC_IS_A | LC_IS_A ) t= ID
            {
            c=(Token)match(input,ID,FOLLOW_ID_in_fact383); 

            if ( input.LA(1)==LC_IS_A||input.LA(1)==UC_IS_A ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            t=(Token)match(input,ID,FOLLOW_ID_in_fact393); 


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
        return ;
    }
    // $ANTLR end "fact"



    // $ANTLR start "constraint_specification"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:211:1: constraint_specification : (c= or_constraint | composite_constraint );
    public final void constraint_specification() throws RecognitionException, MiniEugeneException {
        List<Predicate> c =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:213:2: (c= or_constraint | composite_constraint )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( ((LA4_0 >= BOOL_NOT && LA4_0 <= BOOL_OR)||LA4_0==DOT||LA4_0==INT||LA4_0==LC_GROUP||(LA4_0 >= LC_NOT && LA4_0 <= LC_TEMPLATE)||LA4_0==LEFTSBR||LA4_0==LOG_OR||LA4_0==UC_GROUP||(LA4_0 >= UC_NOT && LA4_0 <= UC_TEMPLATE)||(LA4_0 >= 51 && LA4_0 <= 130)) ) {
                alt4=1;
            }
            else if ( (LA4_0==ID) ) {
                int LA4_2 = input.LA(2);

                if ( ((LA4_2 >= BOOL_NOT && LA4_2 <= BOOL_OR)||LA4_2==DOT||(LA4_2 >= ID && LA4_2 <= INT)||LA4_2==LC_GROUP||(LA4_2 >= LC_NOT && LA4_2 <= LC_TEMPLATE)||LA4_2==LEFTSBR||LA4_2==LOG_OR||LA4_2==UC_GROUP||(LA4_2 >= UC_NOT && LA4_2 <= UC_TEMPLATE)||(LA4_2 >= 51 && LA4_2 <= 130)) ) {
                    alt4=1;
                }
                else if ( (LA4_2==LEFTP||LA4_2==50) ) {
                    alt4=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:213:4: c= or_constraint
                    {
                    pushFollow(FOLLOW_or_constraint_in_constraint_specification414);
                    c=or_constraint();

                    state._fsp--;



                    if(!bCollectFacts) {		
                        if(c.size() == 1) 	{
                            // ``store'' the predicate
                            this.addPredicate(c.get(0));   
                        } else {
                            this.addPredicate(new LogicalOr(c));   
                        }	
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:223:5: composite_constraint
                    {
                    pushFollow(FOLLOW_composite_constraint_in_constraint_specification423);
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
        return ;
    }
    // $ANTLR end "constraint_specification"



    // $ANTLR start "size"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:226:1: size : ( 'minN' EQUALS minN= INT DOT )? N_SIZE EQUALS maxN= INT ;
    public final void size() throws RecognitionException, MiniEugeneException {
        Token minN=null;
        Token maxN=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:228:2: ( ( 'minN' EQUALS minN= INT DOT )? N_SIZE EQUALS maxN= INT )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:228:5: ( 'minN' EQUALS minN= INT DOT )? N_SIZE EQUALS maxN= INT
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:228:5: ( 'minN' EQUALS minN= INT DOT )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==MIN_SIZE) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:228:6: 'minN' EQUALS minN= INT DOT
                    {
                    match(input,MIN_SIZE,FOLLOW_MIN_SIZE_in_size443); 

                    match(input,EQUALS,FOLLOW_EQUALS_in_size445); 

                    minN=(Token)match(input,INT,FOLLOW_INT_in_size449); 

                    match(input,DOT,FOLLOW_DOT_in_size451); 


                    this.minN = Integer.parseInt((minN!=null?minN.getText():null));
                    this.interp.setMinN(this.minN);
                    	

                    }
                    break;

            }


            match(input,N_SIZE,FOLLOW_N_SIZE_in_size462); 

            match(input,EQUALS,FOLLOW_EQUALS_in_size464); 

            maxN=(Token)match(input,INT,FOLLOW_INT_in_size468); 


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
        return ;
    }
    // $ANTLR end "size"



    // $ANTLR start "composite_constraint"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:238:1: composite_constraint : ID ( LEFTP list_of_parameters RIGHTP )? ':=' composite_constraint_block DOT ;
    public final void composite_constraint() throws RecognitionException, MiniEugeneException {
        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:240:2: ( ID ( LEFTP list_of_parameters RIGHTP )? ':=' composite_constraint_block DOT )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:240:4: ID ( LEFTP list_of_parameters RIGHTP )? ':=' composite_constraint_block DOT
            {
            match(input,ID,FOLLOW_ID_in_composite_constraint486); 

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:240:7: ( LEFTP list_of_parameters RIGHTP )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==LEFTP) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:240:9: LEFTP list_of_parameters RIGHTP
                    {
                    match(input,LEFTP,FOLLOW_LEFTP_in_composite_constraint490); 

                    pushFollow(FOLLOW_list_of_parameters_in_composite_constraint492);
                    list_of_parameters();

                    state._fsp--;


                    match(input,RIGHTP,FOLLOW_RIGHTP_in_composite_constraint494); 

                    }
                    break;

            }


            match(input,50,FOLLOW_50_in_composite_constraint499); 

            pushFollow(FOLLOW_composite_constraint_block_in_composite_constraint501);
            composite_constraint_block();

            state._fsp--;


            match(input,DOT,FOLLOW_DOT_in_composite_constraint503); 

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:243:1: composite_constraint_block : constraint ( COMMA composite_constraint_block )? ;
    public final void composite_constraint_block() throws RecognitionException, MiniEugeneException {
        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:245:2: ( constraint ( COMMA composite_constraint_block )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:245:4: constraint ( COMMA composite_constraint_block )?
            {
            pushFollow(FOLLOW_constraint_in_composite_constraint_block519);
            constraint();

            state._fsp--;


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:245:15: ( COMMA composite_constraint_block )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==COMMA) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:245:16: COMMA composite_constraint_block
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_composite_constraint_block522); 

                    pushFollow(FOLLOW_composite_constraint_block_in_composite_constraint_block524);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:248:1: or_constraint returns [List<Predicate> lst] : c= constraint ( ( UC_OR | LC_OR | LOG_OR | BOOL_OR ) o= or_constraint )? ;
    public final List<Predicate> or_constraint() throws RecognitionException, MiniEugeneException {
        List<Predicate> lst = null;


        Predicate c =null;

        List<Predicate> o =null;



        lst = new ArrayList<Predicate>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:254:2: (c= constraint ( ( UC_OR | LC_OR | LOG_OR | BOOL_OR ) o= or_constraint )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:254:4: c= constraint ( ( UC_OR | LC_OR | LOG_OR | BOOL_OR ) o= or_constraint )?
            {
            pushFollow(FOLLOW_constraint_in_or_constraint564);
            c=constraint();

            state._fsp--;



            if(!bCollectFacts) {		
                lst.add(c);
            }
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:258:4: ( ( UC_OR | LC_OR | LOG_OR | BOOL_OR ) o= or_constraint )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==BOOL_OR||LA8_0==LC_OR||LA8_0==LOG_OR||LA8_0==UC_OR) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:258:5: ( UC_OR | LC_OR | LOG_OR | BOOL_OR ) o= or_constraint
                    {
                    if ( input.LA(1)==BOOL_OR||input.LA(1)==LC_OR||input.LA(1)==LOG_OR||input.LA(1)==UC_OR ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_or_constraint_in_or_constraint581);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:265:1: constraint returns [Predicate p] : ( (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? (lhs= operand )? op= operator (rhs= operand )? |temp= templatingConstraints );
    public final Predicate constraint() throws RecognitionException, MiniEugeneException {
        Predicate p = null;


        Token not=null;
        MiniEugeneParser.operand_return lhs =null;

        MiniEugeneParser.operator_return op =null;

        MiniEugeneParser.operand_return rhs =null;

        Predicate temp =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:268:2: ( (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? (lhs= operand )? op= operator (rhs= operand )? |temp= templatingConstraints )
            int alt12=2;
            switch ( input.LA(1) ) {
            case BOOL_NOT:
            case LC_NOT:
            case UC_NOT:
                {
                int LA12_1 = input.LA(2);

                if ( (LA12_1==BOOL_OR||LA12_1==COMMA||LA12_1==DOT||(LA12_1 >= ID && LA12_1 <= INT)||LA12_1==LC_OR||LA12_1==LEFTSBR||LA12_1==LOG_OR||LA12_1==UC_OR||(LA12_1 >= 51 && LA12_1 <= 130)) ) {
                    alt12=1;
                }
                else if ( (LA12_1==LC_GROUP||(LA12_1 >= LC_PATTERN && LA12_1 <= LC_TEMPLATE)||LA12_1==UC_GROUP||(LA12_1 >= UC_PATTERN && LA12_1 <= UC_TEMPLATE)) ) {
                    alt12=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 1, input);

                    throw nvae;

                }
                }
                break;
            case ID:
                {
                int LA12_2 = input.LA(2);

                if ( (LA12_2==BOOL_OR||LA12_2==COMMA||LA12_2==DOT||(LA12_2 >= ID && LA12_2 <= INT)||LA12_2==LC_OR||LA12_2==LEFTSBR||LA12_2==LOG_OR||LA12_2==UC_OR||(LA12_2 >= 51 && LA12_2 <= 130)) ) {
                    alt12=1;
                }
                else if ( (LA12_2==BOOL_NOT||LA12_2==LC_GROUP||LA12_2==LC_NOT||(LA12_2 >= LC_PATTERN && LA12_2 <= LC_TEMPLATE)||LA12_2==UC_GROUP||LA12_2==UC_NOT||(LA12_2 >= UC_PATTERN && LA12_2 <= UC_TEMPLATE)) ) {
                    alt12=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 12, 2, input);

                    throw nvae;

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
            case 51:
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
                {
                alt12=1;
                }
                break;
            case LC_GROUP:
            case LC_PATTERN:
            case LC_SEQUENCE:
            case LC_TEMPLATE:
            case UC_GROUP:
            case UC_PATTERN:
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:268:4: (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? (lhs= operand )? op= operator (rhs= operand )?
                    {
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:268:4: (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0==BOOL_NOT||LA9_0==LC_NOT||LA9_0==UC_NOT) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:268:5: not= ( UC_NOT | LC_NOT | BOOL_NOT )
                            {
                            not=(Token)input.LT(1);

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


                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:272:6: (lhs= operand )?
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
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:272:7: lhs= operand
                            {
                            pushFollow(FOLLOW_operand_in_constraint632);
                            lhs=operand();

                            state._fsp--;



                            if(!bCollectFacts) {		
                                addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));
                            }	
                            	

                            }
                            break;

                    }


                    pushFollow(FOLLOW_operator_in_constraint640);
                    op=operator();

                    state._fsp--;



                    if(!bCollectFacts) {		
                        addToken((op!=null?input.toString(op.start,op.stop):null));	
                    }
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:280:4: (rhs= operand )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0 >= ID && LA11_0 <= INT)||LA11_0==LEFTSBR) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:280:5: rhs= operand
                            {
                            pushFollow(FOLLOW_operand_in_constraint647);
                            rhs=operand();

                            state._fsp--;



                            if(!bCollectFacts) {		
                                addToken((rhs!=null?input.toString(rhs.start,rhs.stop):null));	
                            }
                            	

                            }
                            break;

                    }



                    if(!bCollectFacts) {		
                        // turn the tokens into a miniEugene predicate	
                        p = this.interp.interpreteRule(this.tokens);

                        // reset the global token array
                        this.tokens = null;
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:293:4: temp= templatingConstraints
                    {
                    pushFollow(FOLLOW_templatingConstraints_in_constraint660);
                    temp=templatingConstraints();

                    state._fsp--;



                    if(!bCollectFacts) {		
                        p = temp;	
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
        return p;
    }
    // $ANTLR end "constraint"



    // $ANTLR start "templatingConstraints"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:303:1: templatingConstraints returns [Predicate p] : (tem= templateConstraint |pat= patternConstraint |gr= groupConstraint |seq= sequenceConstraint );
    public final Predicate templatingConstraints() throws RecognitionException, MiniEugeneException {
        Predicate p = null;


        Template tem =null;

        Pattern pat =null;

        Group gr =null;

        Sequence seq =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:306:2: (tem= templateConstraint |pat= patternConstraint |gr= groupConstraint |seq= sequenceConstraint )
            int alt13=4;
            switch ( input.LA(1) ) {
            case ID:
                {
                switch ( input.LA(2) ) {
                case BOOL_NOT:
                case LC_NOT:
                case UC_NOT:
                    {
                    switch ( input.LA(3) ) {
                    case LC_TEMPLATE:
                    case UC_TEMPLATE:
                        {
                        alt13=1;
                        }
                        break;
                    case LC_PATTERN:
                    case UC_PATTERN:
                        {
                        alt13=2;
                        }
                        break;
                    case LC_GROUP:
                    case UC_GROUP:
                        {
                        alt13=3;
                        }
                        break;
                    case LC_SEQUENCE:
                    case UC_SEQUENCE:
                        {
                        alt13=4;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 13, 2, input);

                        throw nvae;

                    }

                    }
                    break;
                case LC_TEMPLATE:
                case UC_TEMPLATE:
                    {
                    alt13=1;
                    }
                    break;
                case LC_PATTERN:
                case UC_PATTERN:
                    {
                    alt13=2;
                    }
                    break;
                case LC_GROUP:
                case UC_GROUP:
                    {
                    alt13=3;
                    }
                    break;
                case LC_SEQUENCE:
                case UC_SEQUENCE:
                    {
                    alt13=4;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 1, input);

                    throw nvae;

                }

                }
                break;
            case BOOL_NOT:
            case LC_NOT:
            case UC_NOT:
                {
                switch ( input.LA(2) ) {
                case LC_TEMPLATE:
                case UC_TEMPLATE:
                    {
                    alt13=1;
                    }
                    break;
                case LC_PATTERN:
                case UC_PATTERN:
                    {
                    alt13=2;
                    }
                    break;
                case LC_GROUP:
                case UC_GROUP:
                    {
                    alt13=3;
                    }
                    break;
                case LC_SEQUENCE:
                case UC_SEQUENCE:
                    {
                    alt13=4;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 13, 2, input);

                    throw nvae;

                }

                }
                break;
            case LC_TEMPLATE:
            case UC_TEMPLATE:
                {
                alt13=1;
                }
                break;
            case LC_PATTERN:
            case UC_PATTERN:
                {
                alt13=2;
                }
                break;
            case LC_GROUP:
            case UC_GROUP:
                {
                alt13=3;
                }
                break;
            case LC_SEQUENCE:
            case UC_SEQUENCE:
                {
                alt13=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 13, 0, input);

                throw nvae;

            }

            switch (alt13) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:306:4: tem= templateConstraint
                    {
                    pushFollow(FOLLOW_templateConstraint_in_templatingConstraints691);
                    tem=templateConstraint();

                    state._fsp--;



                    if(!bCollectFacts) {		
                        p = tem;	
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:311:4: pat= patternConstraint
                    {
                    pushFollow(FOLLOW_patternConstraint_in_templatingConstraints700);
                    pat=patternConstraint();

                    state._fsp--;



                    if(!bCollectFacts) {		
                        p = pat;	
                    }
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:316:4: gr= groupConstraint
                    {
                    pushFollow(FOLLOW_groupConstraint_in_templatingConstraints709);
                    gr=groupConstraint();

                    state._fsp--;



                    if(!bCollectFacts) {		
                        p = gr;	
                    }
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:321:4: seq= sequenceConstraint
                    {
                    pushFollow(FOLLOW_sequenceConstraint_in_templatingConstraints718);
                    seq=sequenceConstraint();

                    state._fsp--;



                    if(!bCollectFacts) {		
                        p = seq;	
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
        return p;
    }
    // $ANTLR end "templatingConstraints"



    // $ANTLR start "templateConstraint"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:328:1: templateConstraint returns [Template p] : (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_TEMPLATE | LC_TEMPLATE ) ids= list_of_ids ;
    public final Template templateConstraint() throws RecognitionException {
        Template p = null;


        Token name=null;
        Token not=null;
        List<List<String>> ids =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:330:2: ( (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_TEMPLATE | LC_TEMPLATE ) ids= list_of_ids )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:330:4: (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_TEMPLATE | LC_TEMPLATE ) ids= list_of_ids
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:330:4: (name= ID )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==ID) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:330:5: name= ID
                    {
                    name=(Token)match(input,ID,FOLLOW_ID_in_templateConstraint741); 

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:330:18: (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==BOOL_NOT||LA15_0==LC_NOT||LA15_0==UC_NOT) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:330:18: not= ( UC_NOT | LC_NOT | BOOL_NOT )
                    {
                    not=(Token)input.LT(1);

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


            pushFollow(FOLLOW_list_of_ids_in_templateConstraint764);
            ids=list_of_ids();

            state._fsp--;



            if(!bCollectFacts) {		
                p = (Template)this.interp.createTemplatingConstraint(
                    TemplateType.TEMPLATE, 
                    (name!=null?name.getText():null), 
                    ids);
                if(null != not) {
                    p.setNegated();
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
        return p;
    }
    // $ANTLR end "templateConstraint"



    // $ANTLR start "patternConstraint"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:343:1: patternConstraint returns [Pattern p] : (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_PATTERN | LC_PATTERN ) ids= list_of_ids ;
    public final Pattern patternConstraint() throws RecognitionException {
        Pattern p = null;


        Token name=null;
        Token not=null;
        List<List<String>> ids =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:345:2: ( (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_PATTERN | LC_PATTERN ) ids= list_of_ids )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:345:4: (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_PATTERN | LC_PATTERN ) ids= list_of_ids
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:345:4: (name= ID )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==ID) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:345:5: name= ID
                    {
                    name=(Token)match(input,ID,FOLLOW_ID_in_patternConstraint786); 

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:345:18: (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==BOOL_NOT||LA17_0==LC_NOT||LA17_0==UC_NOT) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:345:18: not= ( UC_NOT | LC_NOT | BOOL_NOT )
                    {
                    not=(Token)input.LT(1);

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


            if ( input.LA(1)==LC_PATTERN||input.LA(1)==UC_PATTERN ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_list_of_ids_in_patternConstraint809);
            ids=list_of_ids();

            state._fsp--;



            if(!bCollectFacts) {		
                p = (Pattern)this.interp.createTemplatingConstraint(
                    TemplateType.PATTERN, 
                    (name!=null?name.getText():null), 
                    ids);
                if(null != not) {
                    p.setNegated();
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
        return p;
    }
    // $ANTLR end "patternConstraint"



    // $ANTLR start "sequenceConstraint"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:358:1: sequenceConstraint returns [Sequence p] : (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_SEQUENCE | LC_SEQUENCE ) ids= list_of_ids ;
    public final Sequence sequenceConstraint() throws RecognitionException {
        Sequence p = null;


        Token name=null;
        Token not=null;
        List<List<String>> ids =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:360:2: ( (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_SEQUENCE | LC_SEQUENCE ) ids= list_of_ids )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:360:4: (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_SEQUENCE | LC_SEQUENCE ) ids= list_of_ids
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:360:4: (name= ID )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==ID) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:360:5: name= ID
                    {
                    name=(Token)match(input,ID,FOLLOW_ID_in_sequenceConstraint831); 

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:360:18: (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==BOOL_NOT||LA19_0==LC_NOT||LA19_0==UC_NOT) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:360:18: not= ( UC_NOT | LC_NOT | BOOL_NOT )
                    {
                    not=(Token)input.LT(1);

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


            pushFollow(FOLLOW_list_of_ids_in_sequenceConstraint854);
            ids=list_of_ids();

            state._fsp--;



            if(!bCollectFacts) {		
                p = (Sequence)this.interp.createTemplatingConstraint(
                    TemplateType.SEQUENCE, 
                    (name!=null?name.getText():null), 
                    ids);
                if(null != not) {
                    p.setNegated();
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
        return p;
    }
    // $ANTLR end "sequenceConstraint"



    // $ANTLR start "groupConstraint"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:373:1: groupConstraint returns [Group p] : (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_GROUP | LC_GROUP ) ids= list_of_ids ;
    public final Group groupConstraint() throws RecognitionException {
        Group p = null;


        Token name=null;
        Token not=null;
        List<List<String>> ids =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:375:2: ( (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_GROUP | LC_GROUP ) ids= list_of_ids )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:375:4: (name= ID )? (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )? ( UC_GROUP | LC_GROUP ) ids= list_of_ids
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:375:4: (name= ID )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==ID) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:375:5: name= ID
                    {
                    name=(Token)match(input,ID,FOLLOW_ID_in_groupConstraint877); 

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:375:18: (not= ( UC_NOT | LC_NOT | BOOL_NOT ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==BOOL_NOT||LA21_0==LC_NOT||LA21_0==UC_NOT) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:375:18: not= ( UC_NOT | LC_NOT | BOOL_NOT )
                    {
                    not=(Token)input.LT(1);

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


            if ( input.LA(1)==LC_GROUP||input.LA(1)==UC_GROUP ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_list_of_ids_in_groupConstraint900);
            ids=list_of_ids();

            state._fsp--;



            if(!bCollectFacts) {		
                p = (Group)this.interp.createTemplatingConstraint(
                    TemplateType.GROUP, 
                    (name!=null?name.getText():null), 
                    ids);
                if(null != not) {
                    p.setNegated();
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
        return p;
    }
    // $ANTLR end "groupConstraint"



    // $ANTLR start "list_of_ids"
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:388:1: list_of_ids returns [List<List<String>> lst] : (id= ID | LEFTSBR sel= selection RIGHTSBR ) ( COMMA ids= list_of_ids )? ;
    public final List<List<String>> list_of_ids() throws RecognitionException {
        List<List<String>> lst = null;


        Token id=null;
        List<String> sel =null;

        List<List<String>> ids =null;



        lst = new ArrayList<List<String>>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:393:2: ( (id= ID | LEFTSBR sel= selection RIGHTSBR ) ( COMMA ids= list_of_ids )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:393:4: (id= ID | LEFTSBR sel= selection RIGHTSBR ) ( COMMA ids= list_of_ids )?
            {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:393:4: (id= ID | LEFTSBR sel= selection RIGHTSBR )
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==ID) ) {
                alt22=1;
            }
            else if ( (LA22_0==LEFTSBR) ) {
                alt22=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 22, 0, input);

                throw nvae;

            }
            switch (alt22) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:393:5: id= ID
                    {
                    id=(Token)match(input,ID,FOLLOW_ID_in_list_of_ids933); 


                    if(!bCollectFacts) {		
                        List<String> id_lst = new ArrayList<String>();
                        id_lst.add((id!=null?id.getText():null));
                        lst.add(id_lst);	
                    }
                    	

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:399:5: LEFTSBR sel= selection RIGHTSBR
                    {
                    match(input,LEFTSBR,FOLLOW_LEFTSBR_in_list_of_ids938); 

                    pushFollow(FOLLOW_selection_in_list_of_ids942);
                    sel=selection();

                    state._fsp--;



                    if(!bCollectFacts) {		
                        lst.add(sel);	
                    }
                    	

                    match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_list_of_ids946); 

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:404:3: ( COMMA ids= list_of_ids )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==COMMA) ) {
                int LA23_1 = input.LA(2);

                if ( (LA23_1==ID) ) {
                    alt23=1;
                }
                else if ( (LA23_1==LEFTSBR) ) {
                    int LA23_4 = input.LA(3);

                    if ( (LA23_4==ID) ) {
                        alt23=1;
                    }
                }
            }
            switch (alt23) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:404:4: COMMA ids= list_of_ids
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_list_of_ids952); 

                    pushFollow(FOLLOW_list_of_ids_in_list_of_ids956);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:411:1: selection returns [List<String> lst] : id= ID ( PIPE sel= selection )? ;
    public final List<String> selection() throws RecognitionException {
        List<String> lst = null;


        Token id=null;
        List<String> sel =null;



        lst = new ArrayList<String>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:416:2: (id= ID ( PIPE sel= selection )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:416:4: id= ID ( PIPE sel= selection )?
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_selection983); 


            if(!bCollectFacts) {		
                lst.add((id!=null?id.getText():null));		
            }
            	

            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:420:5: ( PIPE sel= selection )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==PIPE) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:420:6: PIPE sel= selection
                    {
                    match(input,PIPE,FOLLOW_PIPE_in_selection989); 

                    pushFollow(FOLLOW_selection_in_selection993);
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:427:1: operator : (| ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'NOTWITH' | 'notwith' ) | ( 'THEN' | 'then' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) );
    public final MiniEugeneParser.operator_return operator() throws RecognitionException {
        MiniEugeneParser.operator_return retval = new MiniEugeneParser.operator_return();
        retval.start = input.LT(1);


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:427:9: (| ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'NOTWITH' | 'notwith' ) | ( 'THEN' | 'then' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) )
            int alt25=41;
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
                alt25=1;
                }
                break;
            case 61:
            case 101:
                {
                alt25=2;
                }
                break;
            case 71:
            case 111:
                {
                alt25=3;
                }
                break;
            case 65:
            case 105:
                {
                alt25=4;
                }
                break;
            case 73:
            case 113:
                {
                alt25=5;
                }
                break;
            case 69:
            case 109:
                {
                alt25=6;
                }
                break;
            case 75:
            case 115:
                {
                alt25=7;
                }
                break;
            case 80:
            case 120:
                {
                alt25=8;
                }
                break;
            case 90:
            case 130:
                {
                alt25=9;
                }
                break;
            case 77:
            case 117:
                {
                alt25=10;
                }
                break;
            case 89:
            case 129:
                {
                alt25=11;
                }
                break;
            case 76:
            case 116:
                {
                alt25=12;
                }
                break;
            case 88:
            case 128:
                {
                alt25=13;
                }
                break;
            case 63:
            case 103:
                {
                alt25=14;
                }
                break;
            case 60:
            case 100:
                {
                alt25=15;
                }
                break;
            case 53:
            case 93:
                {
                alt25=16;
                }
                break;
            case 83:
            case 123:
                {
                alt25=17;
                }
                break;
            case 51:
            case 91:
                {
                alt25=18;
                }
                break;
            case 52:
            case 92:
                {
                alt25=19;
                }
                break;
            case 82:
            case 122:
                {
                alt25=20;
                }
                break;
            case 70:
            case 110:
                {
                alt25=21;
                }
                break;
            case 55:
            case 95:
                {
                alt25=22;
                }
                break;
            case 85:
            case 125:
                {
                alt25=23;
                }
                break;
            case 59:
            case 99:
                {
                alt25=24;
                }
                break;
            case 64:
            case 104:
                {
                alt25=25;
                }
                break;
            case 72:
            case 112:
                {
                alt25=26;
                }
                break;
            case 68:
            case 108:
                {
                alt25=27;
                }
                break;
            case 74:
            case 114:
                {
                alt25=28;
                }
                break;
            case 66:
            case 106:
                {
                alt25=29;
                }
                break;
            case 54:
            case 94:
                {
                alt25=30;
                }
                break;
            case 84:
            case 124:
                {
                alt25=31;
                }
                break;
            case 79:
            case 119:
                {
                alt25=32;
                }
                break;
            case 56:
            case 96:
                {
                alt25=33;
                }
                break;
            case 86:
            case 126:
                {
                alt25=34;
                }
                break;
            case 81:
            case 121:
                {
                alt25=35;
                }
                break;
            case 57:
            case 97:
                {
                alt25=36;
                }
                break;
            case 87:
            case 127:
                {
                alt25=37;
                }
                break;
            case 78:
            case 118:
                {
                alt25=38;
                }
                break;
            case 67:
            case 107:
                {
                alt25=39;
                }
                break;
            case 62:
            case 102:
                {
                alt25=40;
                }
                break;
            case 58:
            case 98:
                {
                alt25=41;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }

            switch (alt25) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:428:2: 
                    {
                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:428:4: ( 'CONTAINS' | 'contains' )
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
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:429:4: ( 'NOTCONTAINS' | 'notcontains' )
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
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:430:4: ( 'EXACTLY' | 'exactly' )
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
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:431:4: ( 'NOTEXACTLY' | 'notexactly' )
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
                case 6 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:432:4: ( 'MORETHAN' | 'morethan' )
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
                case 7 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:433:4: ( 'NOTMORETHAN' | 'notmorethan' )
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
                case 8 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:434:4: ( 'SAME_COUNT' | 'same_count' )
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
                case 9 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:435:4: ( 'WITH' | 'with' )
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
                case 10 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:436:4: ( 'NOTWITH' | 'notwith' )
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
                case 11 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:437:4: ( 'THEN' | 'then' )
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
                case 12 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:438:4: ( 'NOTTHEN' | 'notthen' )
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
                case 13 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:439:4: ( 'STARTSWITH' | 'startswith' )
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
                case 14 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:440:4: ( 'ENDSWITH' | 'endswith' )
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
                case 15 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:441:4: ( 'BEFORE' | 'before' )
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
                case 16 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:442:4: ( 'ALL_BEFORE' | 'all_before' )
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
                case 17 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:443:4: ( 'SOME_BEFORE' | 'some_before' )
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
                case 18 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:444:4: ( 'AFTER' | 'after' )
                    {
                    if ( input.LA(1)==51||input.LA(1)==91 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:445:4: ( 'ALL_AFTER' | 'all_after' )
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
                case 20 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:446:4: ( 'SOME_AFTER' | 'some_after' )
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
                case 21 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:447:4: ( 'NEXTTO' | 'nextto' )
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
                case 22 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:448:4: ( 'ALL_NEXTTO' | 'all_nextto' )
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
                case 23 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:449:4: ( 'SOME_NEXTTO' | 'some_nextto' )
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
                case 24 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:450:4: ( 'ALWAYS_NEXTTO' | 'always_nextto' )
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
                case 25 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:451:4: ( 'EQUALS' | 'equals' )
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
                case 26 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:452:4: ( 'NOTEQUALS' | 'notequals' )
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
                case 27 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:453:4: ( 'MATCHES' | 'matches' )
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
                case 28 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:454:4: ( 'NOTMATCHES' | 'notmatches' )
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
                case 29 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:455:4: ( 'FORWARD' | 'forward' )
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
                case 30 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:456:4: ( 'ALL_FORWARD' | 'all_forward' )
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
                case 31 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:457:4: ( 'SOME_FORWARD' | 'some_forward' )
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
                case 32 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:458:4: ( 'REVERSE' | 'reverse' )
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
                case 33 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:459:4: ( 'ALL_REVERSE' | 'all_reverse' )
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
                case 34 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:460:4: ( 'SOME_REVERSE' | 'some_reverse' )
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
                case 35 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:461:4: ( 'SAME_ORIENTATION' | 'same_orientation' )
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
                case 36 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:462:4: ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' )
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
                case 37 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:463:4: ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' )
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
                case 38 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:464:4: ( 'REPRESSES' | 'represses' )
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
                case 39 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:465:4: ( 'INDUCES' | 'induces' )
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
                case 40 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:466:4: ( 'DRIVES' | 'drives' )
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
                case 41 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:467:4: ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' )
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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:470:1: operand : ( ID | INT | LEFTSBR INT RIGHTSBR );
    public final MiniEugeneParser.operand_return operand() throws RecognitionException {
        MiniEugeneParser.operand_return retval = new MiniEugeneParser.operand_return();
        retval.start = input.LT(1);


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:470:9: ( ID | INT | LEFTSBR INT RIGHTSBR )
            int alt26=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt26=1;
                }
                break;
            case INT:
                {
                alt26=2;
                }
                break;
            case LEFTSBR:
                {
                alt26=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }

            switch (alt26) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:470:11: ID
                    {
                    match(input,ID,FOLLOW_ID_in_operand1380); 

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:471:4: INT
                    {
                    match(input,INT,FOLLOW_INT_in_operand1386); 

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:472:4: LEFTSBR INT RIGHTSBR
                    {
                    match(input,LEFTSBR,FOLLOW_LEFTSBR_in_operand1391); 

                    match(input,INT,FOLLOW_INT_in_operand1393); 

                    match(input,RIGHTSBR,FOLLOW_RIGHTSBR_in_operand1395); 

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
    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:475:1: list_of_parameters : operand ( COMMA list_of_parameters )? ;
    public final void list_of_parameters() throws RecognitionException {
        try {
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:476:2: ( operand ( COMMA list_of_parameters )? )
            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:476:4: operand ( COMMA list_of_parameters )?
            {
            pushFollow(FOLLOW_operand_in_list_of_parameters1406);
            operand();

            state._fsp--;


            // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:476:12: ( COMMA list_of_parameters )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==COMMA) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/ecosystem/workspace/miniEugene-core/grammar/MiniEugene.g:476:13: COMMA list_of_parameters
                    {
                    match(input,COMMA,FOLLOW_COMMA_in_list_of_parameters1409); 

                    pushFollow(FOLLOW_list_of_parameters_in_list_of_parameters1411);
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


 

    public static final BitSet FOLLOW_size_in_miniEugene330 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_miniEugene332 = new BitSet(new long[]{0xFFF8750013AB0020L,0xFFFFFFFFFFFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_statement_in_miniEugene337 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_miniEugene339 = new BitSet(new long[]{0xFFF8750013AB0022L,0xFFFFFFFFFFFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_fact_in_statement358 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_specification_in_statement364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_fact383 = new BitSet(new long[]{0x0000020000100000L});
    public static final BitSet FOLLOW_set_in_fact385 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_ID_in_fact393 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_or_constraint_in_constraint_specification414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_composite_constraint_in_constraint_specification423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MIN_SIZE_in_size443 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_EQUALS_in_size445 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_INT_in_size449 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_size451 = new BitSet(new long[]{0x0000000100000000L});
    public static final BitSet FOLLOW_N_SIZE_in_size462 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_EQUALS_in_size464 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_INT_in_size468 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_composite_constraint486 = new BitSet(new long[]{0x0004000008000000L});
    public static final BitSet FOLLOW_LEFTP_in_composite_constraint490 = new BitSet(new long[]{0x0000000010030000L});
    public static final BitSet FOLLOW_list_of_parameters_in_composite_constraint492 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_RIGHTP_in_composite_constraint494 = new BitSet(new long[]{0x0004000000000000L});
    public static final BitSet FOLLOW_50_in_composite_constraint499 = new BitSet(new long[]{0xFFF8750013AB0020L,0xFFFFFFFFFFFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_composite_constraint_block_in_composite_constraint501 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_DOT_in_composite_constraint503 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_composite_constraint_block519 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_composite_constraint_block522 = new BitSet(new long[]{0xFFF8750013AB0020L,0xFFFFFFFFFFFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_composite_constraint_block_in_composite_constraint_block524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_or_constraint564 = new BitSet(new long[]{0x0000080040400042L});
    public static final BitSet FOLLOW_set_in_or_constraint569 = new BitSet(new long[]{0xFFF8750013AB0020L,0xFFFFFFFFFFFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_or_constraint_in_or_constraint581 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_constraint617 = new BitSet(new long[]{0xFFF8000010030000L,0xFFFFFFFFFFFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_operand_in_constraint632 = new BitSet(new long[]{0xFFF8000010030000L,0xFFFFFFFFFFFFFFFFL,0x0000000000000007L});
    public static final BitSet FOLLOW_operator_in_constraint640 = new BitSet(new long[]{0x0000000010030002L});
    public static final BitSet FOLLOW_operand_in_constraint647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_templatingConstraints_in_constraint660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_templateConstraint_in_templatingConstraints691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_patternConstraint_in_templatingConstraints700 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_groupConstraint_in_templatingConstraints709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sequenceConstraint_in_templatingConstraints718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_templateConstraint741 = new BitSet(new long[]{0x0000440002200020L});
    public static final BitSet FOLLOW_set_in_templateConstraint747 = new BitSet(new long[]{0x0000400002000000L});
    public static final BitSet FOLLOW_set_in_templateConstraint756 = new BitSet(new long[]{0x0000000010010000L});
    public static final BitSet FOLLOW_list_of_ids_in_templateConstraint764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_patternConstraint786 = new BitSet(new long[]{0x0000140000A00020L});
    public static final BitSet FOLLOW_set_in_patternConstraint792 = new BitSet(new long[]{0x0000100000800000L});
    public static final BitSet FOLLOW_set_in_patternConstraint801 = new BitSet(new long[]{0x0000000010010000L});
    public static final BitSet FOLLOW_list_of_ids_in_patternConstraint809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_sequenceConstraint831 = new BitSet(new long[]{0x0000240001200020L});
    public static final BitSet FOLLOW_set_in_sequenceConstraint837 = new BitSet(new long[]{0x0000200001000000L});
    public static final BitSet FOLLOW_set_in_sequenceConstraint846 = new BitSet(new long[]{0x0000000010010000L});
    public static final BitSet FOLLOW_list_of_ids_in_sequenceConstraint854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_groupConstraint877 = new BitSet(new long[]{0x0000050000280020L});
    public static final BitSet FOLLOW_set_in_groupConstraint883 = new BitSet(new long[]{0x0000010000080000L});
    public static final BitSet FOLLOW_set_in_groupConstraint892 = new BitSet(new long[]{0x0000000010010000L});
    public static final BitSet FOLLOW_list_of_ids_in_groupConstraint900 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_list_of_ids933 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_LEFTSBR_in_list_of_ids938 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_selection_in_list_of_ids942 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_list_of_ids946 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_list_of_ids952 = new BitSet(new long[]{0x0000000010010000L});
    public static final BitSet FOLLOW_list_of_ids_in_list_of_ids956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_selection983 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_PIPE_in_selection989 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_selection_in_selection993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1012 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1021 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1030 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1057 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1066 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1075 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1084 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1093 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1111 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1138 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1147 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1165 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1192 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1219 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1264 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1282 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1300 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1309 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1327 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1345 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator1363 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operand1380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_operand1386 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LEFTSBR_in_operand1391 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_INT_in_operand1393 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_RIGHTSBR_in_operand1395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operand_in_list_of_parameters1406 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_list_of_parameters1409 = new BitSet(new long[]{0x0000000010030000L});
    public static final BitSet FOLLOW_list_of_parameters_in_list_of_parameters1411 = new BitSet(new long[]{0x0000000000000002L});

}