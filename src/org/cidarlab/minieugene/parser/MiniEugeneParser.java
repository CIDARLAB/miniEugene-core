// $ANTLR 3.4 /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g 2014-04-03 15:40:32

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


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class MiniEugeneParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "CHAR", "COMMENT", "ESC_SEQ", "EXPONENT", "HEX_DIGIT", "ID", "INT", "OCTAL_ESC", "STRING", "UNICODE_ESC", "WS", "'('", "')'", "','", "'.'", "':='", "'='", "'AFTER'", "'ALL_AFTER'", "'ALL_BEFORE'", "'ALL_FORWARD'", "'ALL_NEXTTO'", "'ALL_REVERSE'", "'ALL_SAME_ORIENTATION'", "'ALTERNATE_ORIENTATION'", "'ALWAYS_NEXTTO'", "'BEFORE'", "'CONTAINS'", "'DRIVES'", "'ENDSWITH'", "'EQUALS'", "'EXACTLY'", "'FORWARD'", "'GROUP'", "'INDUCES'", "'MATCHES'", "'MORETHAN'", "'N'", "'NEXTTO'", "'NOT'", "'NOTCONTAINS'", "'NOTEQUALS'", "'NOTEXACTLY'", "'NOTMATCHES'", "'NOTMORETHAN'", "'NOTTHEN'", "'NOTWITH'", "'OR'", "'PATTERN'", "'REPRESSES'", "'REVERSE'", "'SAME_COUNT'", "'SAME_ORIENTATION'", "'SEQUENCE'", "'SOME_AFTER'", "'SOME_BEFORE'", "'SOME_FORWARD'", "'SOME_NEXTTO'", "'SOME_REVERSE'", "'SOME_SAME_ORIENTATION'", "'STARTSWITH'", "'TEMPLATE'", "'THEN'", "'WITH'", "'['", "'\\\\/'", "']'", "'after'", "'all_after'", "'all_before'", "'all_forward'", "'all_nextto'", "'all_reverse'", "'all_same_orientation'", "'alternate_orientation'", "'always_nextto'", "'before'", "'contains'", "'drives'", "'endswith'", "'equals'", "'exactly'", "'forward'", "'group'", "'induces'", "'matches'", "'minN'", "'morethan'", "'nextto'", "'not'", "'notcontains'", "'notequals'", "'notexactly'", "'notmatches'", "'notmorethan'", "'notthen'", "'notwith'", "'or'", "'pattern'", "'represses'", "'reverse'", "'same_count'", "'same_orientation'", "'sequence'", "'some_after'", "'some_before'", "'some_forward'", "'some_nextto'", "'some_reverse'", "'some_same_orientation'", "'startswith'", "'template'", "'then'", "'with'", "'||'"
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




    // $ANTLR start "miniEugene"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:109:1: miniEugene : ( size )? (c= or_constraint '.' | composite_constraint )+ ;
    public final void miniEugene() throws EugeneException, RecognitionException {
        List<Predicate> c =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:111:2: ( ( size )? (c= or_constraint '.' | composite_constraint )+ )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:111:4: ( size )? (c= or_constraint '.' | composite_constraint )+
            {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:111:4: ( size )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==41||LA1_0==90) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:111:5: size
                    {
                    pushFollow(FOLLOW_size_in_miniEugene45);
                    size();

                    state._fsp--;


                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:111:12: (c= or_constraint '.' | composite_constraint )+
            int cnt2=0;
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==INT||LA2_0==18||(LA2_0 >= 21 && LA2_0 <= 40)||(LA2_0 >= 42 && LA2_0 <= 69)||(LA2_0 >= 71 && LA2_0 <= 89)||(LA2_0 >= 91 && LA2_0 <= 118)) ) {
                    alt2=1;
                }
                else if ( (LA2_0==ID) ) {
                    int LA2_3 = input.LA(2);

                    if ( ((LA2_3 >= ID && LA2_3 <= INT)||LA2_3==18||(LA2_3 >= 21 && LA2_3 <= 40)||(LA2_3 >= 42 && LA2_3 <= 69)||(LA2_3 >= 71 && LA2_3 <= 89)||(LA2_3 >= 91 && LA2_3 <= 118)) ) {
                        alt2=1;
                    }
                    else if ( (LA2_3==15||LA2_3==19) ) {
                        alt2=2;
                    }


                }


                switch (alt2) {
            	case 1 :
            	    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:111:13: c= or_constraint '.'
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
            	    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:118:8: composite_constraint
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
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:121:1: size : ( 'minN' '=' minN= INT '.' )? 'N' '=' maxN= INT '.' ;
    public final void size() throws EugeneException, RecognitionException {
        Token minN=null;
        Token maxN=null;

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:123:2: ( ( 'minN' '=' minN= INT '.' )? 'N' '=' maxN= INT '.' )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:123:5: ( 'minN' '=' minN= INT '.' )? 'N' '=' maxN= INT '.'
            {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:123:5: ( 'minN' '=' minN= INT '.' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==90) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:123:6: 'minN' '=' minN= INT '.'
                    {
                    match(input,90,FOLLOW_90_in_size80); 

                    match(input,20,FOLLOW_20_in_size82); 

                    minN=(Token)match(input,INT,FOLLOW_INT_in_size86); 

                    match(input,18,FOLLOW_18_in_size88); 


                    this.minN = Integer.parseInt((minN!=null?minN.getText():null));
                    this.interp.setMinN(this.minN);
                    	

                    }
                    break;

            }


            match(input,41,FOLLOW_41_in_size94); 

            match(input,20,FOLLOW_20_in_size96); 

            maxN=(Token)match(input,INT,FOLLOW_INT_in_size100); 

            match(input,18,FOLLOW_18_in_size102); 


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
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:132:1: composite_constraint : ID ( '(' list_of_parameters ')' )? ':=' composite_constraint_block '.' ;
    public final void composite_constraint() throws EugeneException, RecognitionException {
        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:134:2: ( ID ( '(' list_of_parameters ')' )? ':=' composite_constraint_block '.' )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:134:4: ID ( '(' list_of_parameters ')' )? ':=' composite_constraint_block '.'
            {
            match(input,ID,FOLLOW_ID_in_composite_constraint120); 

            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:134:7: ( '(' list_of_parameters ')' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==15) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:134:9: '(' list_of_parameters ')'
                    {
                    match(input,15,FOLLOW_15_in_composite_constraint124); 

                    pushFollow(FOLLOW_list_of_parameters_in_composite_constraint126);
                    list_of_parameters();

                    state._fsp--;


                    match(input,16,FOLLOW_16_in_composite_constraint128); 

                    }
                    break;

            }


            match(input,19,FOLLOW_19_in_composite_constraint133); 

            pushFollow(FOLLOW_composite_constraint_block_in_composite_constraint135);
            composite_constraint_block();

            state._fsp--;


            match(input,18,FOLLOW_18_in_composite_constraint137); 

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
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:137:1: composite_constraint_block : constraint ( ',' composite_constraint_block )? ;
    public final void composite_constraint_block() throws EugeneException, RecognitionException {
        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:139:2: ( constraint ( ',' composite_constraint_block )? )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:139:4: constraint ( ',' composite_constraint_block )?
            {
            pushFollow(FOLLOW_constraint_in_composite_constraint_block153);
            constraint();

            state._fsp--;


            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:139:15: ( ',' composite_constraint_block )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==17) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:139:16: ',' composite_constraint_block
                    {
                    match(input,17,FOLLOW_17_in_composite_constraint_block156); 

                    pushFollow(FOLLOW_composite_constraint_block_in_composite_constraint_block158);
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
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:142:1: or_constraint returns [List<Predicate> lst] : c= constraint ( ( 'OR' | '\\\\/' | 'or' | '||' ) o= or_constraint )? ;
    public final List<Predicate> or_constraint() throws EugeneException, RecognitionException {
        List<Predicate> lst = null;


        Predicate c =null;

        List<Predicate> o =null;



        lst = new ArrayList<Predicate>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:148:2: (c= constraint ( ( 'OR' | '\\\\/' | 'or' | '||' ) o= or_constraint )? )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:148:4: c= constraint ( ( 'OR' | '\\\\/' | 'or' | '||' ) o= or_constraint )?
            {
            pushFollow(FOLLOW_constraint_in_or_constraint198);
            c=constraint();

            state._fsp--;



            lst.add(c);
            	

            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:150:4: ( ( 'OR' | '\\\\/' | 'or' | '||' ) o= or_constraint )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==51||LA6_0==69||LA6_0==101||LA6_0==118) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:150:5: ( 'OR' | '\\\\/' | 'or' | '||' ) o= or_constraint
                    {
                    if ( input.LA(1)==51||input.LA(1)==69||input.LA(1)==101||input.LA(1)==118 ) {
                        input.consume();
                        state.errorRecovery=false;
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        throw mse;
                    }


                    pushFollow(FOLLOW_or_constraint_in_or_constraint215);
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
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:155:1: constraint returns [Predicate p] : ( (not= ( 'NOT' | 'not' ) )? (lhs= operand )? op= operator (rhs= operand )? |tem= template |pat= pattern |gr= group |seq= sequence );
    public final Predicate constraint() throws EugeneException, RecognitionException {
        Predicate p = null;


        Token not=null;
        MiniEugeneParser.operand_return lhs =null;

        MiniEugeneParser.operator_return op =null;

        MiniEugeneParser.operand_return rhs =null;

        Template tem =null;

        Pattern pat =null;

        Group gr =null;

        Sequence seq =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:158:2: ( (not= ( 'NOT' | 'not' ) )? (lhs= operand )? op= operator (rhs= operand )? |tem= template |pat= pattern |gr= group |seq= sequence )
            int alt10=5;
            switch ( input.LA(1) ) {
            case 43:
            case 93:
                {
                switch ( input.LA(2) ) {
                case ID:
                case INT:
                case 17:
                case 18:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 38:
                case 39:
                case 40:
                case 42:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 53:
                case 54:
                case 55:
                case 56:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 66:
                case 67:
                case 68:
                case 69:
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
                case 88:
                case 89:
                case 91:
                case 92:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 103:
                case 104:
                case 105:
                case 106:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 116:
                case 117:
                case 118:
                    {
                    alt10=1;
                    }
                    break;
                case 65:
                case 115:
                    {
                    alt10=2;
                    }
                    break;
                case 52:
                case 102:
                    {
                    alt10=3;
                    }
                    break;
                case 37:
                case 87:
                    {
                    alt10=4;
                    }
                    break;
                case 57:
                case 107:
                    {
                    alt10=5;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;

                }

                }
                break;
            case ID:
                {
                switch ( input.LA(2) ) {
                case ID:
                case INT:
                case 17:
                case 18:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 38:
                case 39:
                case 40:
                case 42:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                case 50:
                case 51:
                case 53:
                case 54:
                case 55:
                case 56:
                case 58:
                case 59:
                case 60:
                case 61:
                case 62:
                case 63:
                case 64:
                case 66:
                case 67:
                case 68:
                case 69:
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
                case 88:
                case 89:
                case 91:
                case 92:
                case 94:
                case 95:
                case 96:
                case 97:
                case 98:
                case 99:
                case 100:
                case 101:
                case 103:
                case 104:
                case 105:
                case 106:
                case 108:
                case 109:
                case 110:
                case 111:
                case 112:
                case 113:
                case 114:
                case 116:
                case 117:
                case 118:
                    {
                    alt10=1;
                    }
                    break;
                case 43:
                case 93:
                    {
                    switch ( input.LA(3) ) {
                    case 65:
                    case 115:
                        {
                        alt10=2;
                        }
                        break;
                    case 52:
                    case 102:
                        {
                        alt10=3;
                        }
                        break;
                    case 37:
                    case 87:
                        {
                        alt10=4;
                        }
                        break;
                    case 57:
                    case 107:
                        {
                        alt10=5;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 10, 8, input);

                        throw nvae;

                    }

                    }
                    break;
                case 65:
                case 115:
                    {
                    alt10=2;
                    }
                    break;
                case 52:
                case 102:
                    {
                    alt10=3;
                    }
                    break;
                case 37:
                case 87:
                    {
                    alt10=4;
                    }
                    break;
                case 57:
                case 107:
                    {
                    alt10=5;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 2, input);

                    throw nvae;

                }

                }
                break;
            case INT:
            case 17:
            case 18:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 38:
            case 39:
            case 40:
            case 42:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 50:
            case 51:
            case 53:
            case 54:
            case 55:
            case 56:
            case 58:
            case 59:
            case 60:
            case 61:
            case 62:
            case 63:
            case 64:
            case 66:
            case 67:
            case 68:
            case 69:
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
            case 88:
            case 89:
            case 91:
            case 92:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 103:
            case 104:
            case 105:
            case 106:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 116:
            case 117:
            case 118:
                {
                alt10=1;
                }
                break;
            case 65:
            case 115:
                {
                alt10=2;
                }
                break;
            case 52:
            case 102:
                {
                alt10=3;
                }
                break;
            case 37:
            case 87:
                {
                alt10=4;
                }
                break;
            case 57:
            case 107:
                {
                alt10=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }

            switch (alt10) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:158:4: (not= ( 'NOT' | 'not' ) )? (lhs= operand )? op= operator (rhs= operand )?
                    {
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:158:4: (not= ( 'NOT' | 'not' ) )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0==43||LA7_0==93) ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:158:5: not= ( 'NOT' | 'not' )
                            {
                            not=(Token)input.LT(1);

                            if ( input.LA(1)==43||input.LA(1)==93 ) {
                                input.consume();
                                state.errorRecovery=false;
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                throw mse;
                            }



                            addToken("NOT");
                            	

                            }
                            break;

                    }


                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:160:6: (lhs= operand )?
                    int alt8=2;
                    switch ( input.LA(1) ) {
                        case ID:
                            {
                            alt8=1;
                            }
                            break;
                        case INT:
                            {
                            alt8=1;
                            }
                            break;
                        case 68:
                            {
                            int LA8_3 = input.LA(2);

                            if ( (LA8_3==INT) ) {
                                int LA8_5 = input.LA(3);

                                if ( (LA8_5==70) ) {
                                    alt8=1;
                                }
                            }
                            }
                            break;
                    }

                    switch (alt8) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:160:7: lhs= operand
                            {
                            pushFollow(FOLLOW_operand_in_constraint264);
                            lhs=operand();

                            state._fsp--;



                            addToken((lhs!=null?input.toString(lhs.start,lhs.stop):null));	
                            	

                            }
                            break;

                    }


                    pushFollow(FOLLOW_operator_in_constraint272);
                    op=operator();

                    state._fsp--;



                    addToken((op!=null?input.toString(op.start,op.stop):null));	
                    	

                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:164:4: (rhs= operand )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( ((LA9_0 >= ID && LA9_0 <= INT)||LA9_0==68) ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:164:5: rhs= operand
                            {
                            pushFollow(FOLLOW_operand_in_constraint279);
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
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:174:4: tem= template
                    {
                    pushFollow(FOLLOW_template_in_constraint292);
                    tem=template();

                    state._fsp--;



                    p = tem;	
                    	

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:177:4: pat= pattern
                    {
                    pushFollow(FOLLOW_pattern_in_constraint301);
                    pat=pattern();

                    state._fsp--;



                    p = pat;	
                    	

                    }
                    break;
                case 4 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:180:4: gr= group
                    {
                    pushFollow(FOLLOW_group_in_constraint310);
                    gr=group();

                    state._fsp--;



                    p = gr;	
                    	

                    }
                    break;
                case 5 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:183:4: seq= sequence
                    {
                    pushFollow(FOLLOW_sequence_in_constraint319);
                    seq=sequence();

                    state._fsp--;



                    p = seq;	
                    	

                    }
                    break;

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



    // $ANTLR start "template"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:191:1: template returns [Template p] : (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'TEMPLATE' | 'template' ) ids= list_of_ids ;
    public final Template template() throws RecognitionException {
        Template p = null;


        Token name=null;
        Token not=null;
        List<String> ids =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:193:2: ( (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'TEMPLATE' | 'template' ) ids= list_of_ids )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:193:4: (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'TEMPLATE' | 'template' ) ids= list_of_ids
            {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:193:4: (name= ID )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==ID) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:193:5: name= ID
                    {
                    name=(Token)match(input,ID,FOLLOW_ID_in_template347); 

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:193:18: (not= ( 'NOT' | 'not' ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==43||LA12_0==93) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:193:18: not= ( 'NOT' | 'not' )
                    {
                    not=(Token)input.LT(1);

                    if ( input.LA(1)==43||input.LA(1)==93 ) {
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


            if ( input.LA(1)==65||input.LA(1)==115 ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_list_of_ids_in_template368);
            ids=list_of_ids();

            state._fsp--;



            p = this.interp.createTemplate((name!=null?name.getText():null), ids);
            if(null != not) {
                p.setNegated();
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
    // $ANTLR end "template"



    // $ANTLR start "pattern"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:201:1: pattern returns [Pattern p] : (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'PATTERN' | 'pattern' ) ids= list_of_ids ;
    public final Pattern pattern() throws RecognitionException {
        Pattern p = null;


        Token name=null;
        Token not=null;
        List<String> ids =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:203:2: ( (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'PATTERN' | 'pattern' ) ids= list_of_ids )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:203:4: (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'PATTERN' | 'pattern' ) ids= list_of_ids
            {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:203:4: (name= ID )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==ID) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:203:5: name= ID
                    {
                    name=(Token)match(input,ID,FOLLOW_ID_in_pattern390); 

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:203:18: (not= ( 'NOT' | 'not' ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==43||LA14_0==93) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:203:18: not= ( 'NOT' | 'not' )
                    {
                    not=(Token)input.LT(1);

                    if ( input.LA(1)==43||input.LA(1)==93 ) {
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


            if ( input.LA(1)==52||input.LA(1)==102 ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_list_of_ids_in_pattern411);
            ids=list_of_ids();

            state._fsp--;



            p = this.interp.createPattern((name!=null?name.getText():null), ids);
            if(null != not) {
                p.setNegated();
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
    // $ANTLR end "pattern"



    // $ANTLR start "sequence"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:211:1: sequence returns [Sequence p] : (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'SEQUENCE' | 'sequence' ) ids= list_of_ids ;
    public final Sequence sequence() throws RecognitionException {
        Sequence p = null;


        Token name=null;
        Token not=null;
        List<String> ids =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:213:2: ( (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'SEQUENCE' | 'sequence' ) ids= list_of_ids )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:213:4: (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'SEQUENCE' | 'sequence' ) ids= list_of_ids
            {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:213:4: (name= ID )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==ID) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:213:5: name= ID
                    {
                    name=(Token)match(input,ID,FOLLOW_ID_in_sequence433); 

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:213:18: (not= ( 'NOT' | 'not' ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==43||LA16_0==93) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:213:18: not= ( 'NOT' | 'not' )
                    {
                    not=(Token)input.LT(1);

                    if ( input.LA(1)==43||input.LA(1)==93 ) {
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


            if ( input.LA(1)==57||input.LA(1)==107 ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_list_of_ids_in_sequence454);
            ids=list_of_ids();

            state._fsp--;



            p = this.interp.createSequence((name!=null?name.getText():null), ids);
            if(null != not) {
                p.setNegated();
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
    // $ANTLR end "sequence"



    // $ANTLR start "group"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:221:1: group returns [Group p] : (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'GROUP' | 'group' ) ids= list_of_ids ;
    public final Group group() throws RecognitionException {
        Group p = null;


        Token name=null;
        Token not=null;
        List<String> ids =null;


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:223:2: ( (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'GROUP' | 'group' ) ids= list_of_ids )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:223:4: (name= ID )? (not= ( 'NOT' | 'not' ) )? ( 'GROUP' | 'group' ) ids= list_of_ids
            {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:223:4: (name= ID )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==ID) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:223:5: name= ID
                    {
                    name=(Token)match(input,ID,FOLLOW_ID_in_group477); 

                    }
                    break;

            }


            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:223:18: (not= ( 'NOT' | 'not' ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==43||LA18_0==93) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:223:18: not= ( 'NOT' | 'not' )
                    {
                    not=(Token)input.LT(1);

                    if ( input.LA(1)==43||input.LA(1)==93 ) {
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


            if ( input.LA(1)==37||input.LA(1)==87 ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


            pushFollow(FOLLOW_list_of_ids_in_group498);
            ids=list_of_ids();

            state._fsp--;



            p = this.interp.createGroup((name!=null?name.getText():null), ids);
            if(null != not) {
                p.setNegated();
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
    // $ANTLR end "group"



    // $ANTLR start "list_of_ids"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:231:1: list_of_ids returns [List<String> lst] : id= ID ( ',' ids= list_of_ids )? ;
    public final List<String> list_of_ids() throws RecognitionException {
        List<String> lst = null;


        Token id=null;
        List<String> ids =null;



        lst = new ArrayList<String>();

        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:236:2: (id= ID ( ',' ids= list_of_ids )? )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:236:4: id= ID ( ',' ids= list_of_ids )?
            {
            id=(Token)match(input,ID,FOLLOW_ID_in_list_of_ids530); 


            lst.add((id!=null?id.getText():null));	
            	

            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:238:4: ( ',' ids= list_of_ids )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==17) ) {
                int LA19_1 = input.LA(2);

                if ( (LA19_1==ID) ) {
                    alt19=1;
                }
            }
            switch (alt19) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:238:5: ',' ids= list_of_ids
                    {
                    match(input,17,FOLLOW_17_in_list_of_ids535); 

                    pushFollow(FOLLOW_list_of_ids_in_list_of_ids539);
                    ids=list_of_ids();

                    state._fsp--;



                    lst.addAll(ids);		
                    	

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


    public static class operator_return extends ParserRuleReturnScope {
    };


    // $ANTLR start "operator"
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:243:1: operator : (| ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'NOTWITH' | 'notwith' ) | ( 'THEN' | 'then' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) );
    public final MiniEugeneParser.operator_return operator() throws RecognitionException {
        MiniEugeneParser.operator_return retval = new MiniEugeneParser.operator_return();
        retval.start = input.LT(1);


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:243:9: (| ( 'CONTAINS' | 'contains' ) | ( 'NOTCONTAINS' | 'notcontains' ) | ( 'EXACTLY' | 'exactly' ) | ( 'NOTEXACTLY' | 'notexactly' ) | ( 'MORETHAN' | 'morethan' ) | ( 'NOTMORETHAN' | 'notmorethan' ) | ( 'SAME_COUNT' | 'same_count' ) | ( 'WITH' | 'with' ) | ( 'NOTWITH' | 'notwith' ) | ( 'THEN' | 'then' ) | ( 'NOTTHEN' | 'notthen' ) | ( 'STARTSWITH' | 'startswith' ) | ( 'ENDSWITH' | 'endswith' ) | ( 'BEFORE' | 'before' ) | ( 'ALL_BEFORE' | 'all_before' ) | ( 'SOME_BEFORE' | 'some_before' ) | ( 'AFTER' | 'after' ) | ( 'ALL_AFTER' | 'all_after' ) | ( 'SOME_AFTER' | 'some_after' ) | ( 'NEXTTO' | 'nextto' ) | ( 'ALL_NEXTTO' | 'all_nextto' ) | ( 'SOME_NEXTTO' | 'some_nextto' ) | ( 'ALWAYS_NEXTTO' | 'always_nextto' ) | ( 'EQUALS' | 'equals' ) | ( 'NOTEQUALS' | 'notequals' ) | ( 'MATCHES' | 'matches' ) | ( 'NOTMATCHES' | 'notmatches' ) | ( 'FORWARD' | 'forward' ) | ( 'ALL_FORWARD' | 'all_forward' ) | ( 'SOME_FORWARD' | 'some_forward' ) | ( 'REVERSE' | 'reverse' ) | ( 'ALL_REVERSE' | 'all_reverse' ) | ( 'SOME_REVERSE' | 'some_reverse' ) | ( 'SAME_ORIENTATION' | 'same_orientation' ) | ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' ) | ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' ) | ( 'REPRESSES' | 'represses' ) | ( 'INDUCES' | 'induces' ) | ( 'DRIVES' | 'drives' ) | ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' ) )
            int alt20=41;
            switch ( input.LA(1) ) {
            case ID:
            case INT:
            case 17:
            case 18:
            case 51:
            case 68:
            case 69:
            case 101:
            case 118:
                {
                alt20=1;
                }
                break;
            case 31:
            case 81:
                {
                alt20=2;
                }
                break;
            case 44:
            case 94:
                {
                alt20=3;
                }
                break;
            case 35:
            case 85:
                {
                alt20=4;
                }
                break;
            case 46:
            case 96:
                {
                alt20=5;
                }
                break;
            case 40:
            case 91:
                {
                alt20=6;
                }
                break;
            case 48:
            case 98:
                {
                alt20=7;
                }
                break;
            case 55:
            case 105:
                {
                alt20=8;
                }
                break;
            case 67:
            case 117:
                {
                alt20=9;
                }
                break;
            case 50:
            case 100:
                {
                alt20=10;
                }
                break;
            case 66:
            case 116:
                {
                alt20=11;
                }
                break;
            case 49:
            case 99:
                {
                alt20=12;
                }
                break;
            case 64:
            case 114:
                {
                alt20=13;
                }
                break;
            case 33:
            case 83:
                {
                alt20=14;
                }
                break;
            case 30:
            case 80:
                {
                alt20=15;
                }
                break;
            case 23:
            case 73:
                {
                alt20=16;
                }
                break;
            case 59:
            case 109:
                {
                alt20=17;
                }
                break;
            case 21:
            case 71:
                {
                alt20=18;
                }
                break;
            case 22:
            case 72:
                {
                alt20=19;
                }
                break;
            case 58:
            case 108:
                {
                alt20=20;
                }
                break;
            case 42:
            case 92:
                {
                alt20=21;
                }
                break;
            case 25:
            case 75:
                {
                alt20=22;
                }
                break;
            case 61:
            case 111:
                {
                alt20=23;
                }
                break;
            case 29:
            case 79:
                {
                alt20=24;
                }
                break;
            case 34:
            case 84:
                {
                alt20=25;
                }
                break;
            case 45:
            case 95:
                {
                alt20=26;
                }
                break;
            case 39:
            case 89:
                {
                alt20=27;
                }
                break;
            case 47:
            case 97:
                {
                alt20=28;
                }
                break;
            case 36:
            case 86:
                {
                alt20=29;
                }
                break;
            case 24:
            case 74:
                {
                alt20=30;
                }
                break;
            case 60:
            case 110:
                {
                alt20=31;
                }
                break;
            case 54:
            case 104:
                {
                alt20=32;
                }
                break;
            case 26:
            case 76:
                {
                alt20=33;
                }
                break;
            case 62:
            case 112:
                {
                alt20=34;
                }
                break;
            case 56:
            case 106:
                {
                alt20=35;
                }
                break;
            case 27:
            case 77:
                {
                alt20=36;
                }
                break;
            case 63:
            case 113:
                {
                alt20=37;
                }
                break;
            case 53:
            case 103:
                {
                alt20=38;
                }
                break;
            case 38:
            case 88:
                {
                alt20=39;
                }
                break;
            case 32:
            case 82:
                {
                alt20=40;
                }
                break;
            case 28:
            case 78:
                {
                alt20=41;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 20, 0, input);

                throw nvae;

            }

            switch (alt20) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:244:2: 
                    {
                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:244:4: ( 'CONTAINS' | 'contains' )
                    {
                    if ( input.LA(1)==31||input.LA(1)==81 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:245:4: ( 'NOTCONTAINS' | 'notcontains' )
                    {
                    if ( input.LA(1)==44||input.LA(1)==94 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:246:4: ( 'EXACTLY' | 'exactly' )
                    {
                    if ( input.LA(1)==35||input.LA(1)==85 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:247:4: ( 'NOTEXACTLY' | 'notexactly' )
                    {
                    if ( input.LA(1)==46||input.LA(1)==96 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:248:4: ( 'MORETHAN' | 'morethan' )
                    {
                    if ( input.LA(1)==40||input.LA(1)==91 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:249:4: ( 'NOTMORETHAN' | 'notmorethan' )
                    {
                    if ( input.LA(1)==48||input.LA(1)==98 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:250:4: ( 'SAME_COUNT' | 'same_count' )
                    {
                    if ( input.LA(1)==55||input.LA(1)==105 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:251:4: ( 'WITH' | 'with' )
                    {
                    if ( input.LA(1)==67||input.LA(1)==117 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:252:4: ( 'NOTWITH' | 'notwith' )
                    {
                    if ( input.LA(1)==50||input.LA(1)==100 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:253:4: ( 'THEN' | 'then' )
                    {
                    if ( input.LA(1)==66||input.LA(1)==116 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:254:4: ( 'NOTTHEN' | 'notthen' )
                    {
                    if ( input.LA(1)==49||input.LA(1)==99 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:255:4: ( 'STARTSWITH' | 'startswith' )
                    {
                    if ( input.LA(1)==64||input.LA(1)==114 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:256:4: ( 'ENDSWITH' | 'endswith' )
                    {
                    if ( input.LA(1)==33||input.LA(1)==83 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:257:4: ( 'BEFORE' | 'before' )
                    {
                    if ( input.LA(1)==30||input.LA(1)==80 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:258:4: ( 'ALL_BEFORE' | 'all_before' )
                    {
                    if ( input.LA(1)==23||input.LA(1)==73 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:259:4: ( 'SOME_BEFORE' | 'some_before' )
                    {
                    if ( input.LA(1)==59||input.LA(1)==109 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:260:4: ( 'AFTER' | 'after' )
                    {
                    if ( input.LA(1)==21||input.LA(1)==71 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:261:4: ( 'ALL_AFTER' | 'all_after' )
                    {
                    if ( input.LA(1)==22||input.LA(1)==72 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:262:4: ( 'SOME_AFTER' | 'some_after' )
                    {
                    if ( input.LA(1)==58||input.LA(1)==108 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:263:4: ( 'NEXTTO' | 'nextto' )
                    {
                    if ( input.LA(1)==42||input.LA(1)==92 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:264:4: ( 'ALL_NEXTTO' | 'all_nextto' )
                    {
                    if ( input.LA(1)==25||input.LA(1)==75 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:265:4: ( 'SOME_NEXTTO' | 'some_nextto' )
                    {
                    if ( input.LA(1)==61||input.LA(1)==111 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:266:4: ( 'ALWAYS_NEXTTO' | 'always_nextto' )
                    {
                    if ( input.LA(1)==29||input.LA(1)==79 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:267:4: ( 'EQUALS' | 'equals' )
                    {
                    if ( input.LA(1)==34||input.LA(1)==84 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:268:4: ( 'NOTEQUALS' | 'notequals' )
                    {
                    if ( input.LA(1)==45||input.LA(1)==95 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:269:4: ( 'MATCHES' | 'matches' )
                    {
                    if ( input.LA(1)==39||input.LA(1)==89 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:270:4: ( 'NOTMATCHES' | 'notmatches' )
                    {
                    if ( input.LA(1)==47||input.LA(1)==97 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:271:4: ( 'FORWARD' | 'forward' )
                    {
                    if ( input.LA(1)==36||input.LA(1)==86 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:272:4: ( 'ALL_FORWARD' | 'all_forward' )
                    {
                    if ( input.LA(1)==24||input.LA(1)==74 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:273:4: ( 'SOME_FORWARD' | 'some_forward' )
                    {
                    if ( input.LA(1)==60||input.LA(1)==110 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:274:4: ( 'REVERSE' | 'reverse' )
                    {
                    if ( input.LA(1)==54||input.LA(1)==104 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:275:4: ( 'ALL_REVERSE' | 'all_reverse' )
                    {
                    if ( input.LA(1)==26||input.LA(1)==76 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:276:4: ( 'SOME_REVERSE' | 'some_reverse' )
                    {
                    if ( input.LA(1)==62||input.LA(1)==112 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:277:4: ( 'SAME_ORIENTATION' | 'same_orientation' )
                    {
                    if ( input.LA(1)==56||input.LA(1)==106 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:278:4: ( 'ALL_SAME_ORIENTATION' | 'all_same_orientation' )
                    {
                    if ( input.LA(1)==27||input.LA(1)==77 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:279:4: ( 'SOME_SAME_ORIENTATION' | 'some_same_orientation' )
                    {
                    if ( input.LA(1)==63||input.LA(1)==113 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:280:4: ( 'REPRESSES' | 'represses' )
                    {
                    if ( input.LA(1)==53||input.LA(1)==103 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:281:4: ( 'INDUCES' | 'induces' )
                    {
                    if ( input.LA(1)==38||input.LA(1)==88 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:282:4: ( 'DRIVES' | 'drives' )
                    {
                    if ( input.LA(1)==32||input.LA(1)==82 ) {
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
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:283:4: ( 'ALTERNATE_ORIENTATION' | 'alternate_orientation' )
                    {
                    if ( input.LA(1)==28||input.LA(1)==78 ) {
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
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:286:1: operand : ( ID | INT | '[' INT ']' );
    public final MiniEugeneParser.operand_return operand() throws RecognitionException {
        MiniEugeneParser.operand_return retval = new MiniEugeneParser.operand_return();
        retval.start = input.LT(1);


        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:286:9: ( ID | INT | '[' INT ']' )
            int alt21=3;
            switch ( input.LA(1) ) {
            case ID:
                {
                alt21=1;
                }
                break;
            case INT:
                {
                alt21=2;
                }
                break;
            case 68:
                {
                alt21=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;

            }

            switch (alt21) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:286:11: ID
                    {
                    match(input,ID,FOLLOW_ID_in_operand925); 

                    }
                    break;
                case 2 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:287:4: INT
                    {
                    match(input,INT,FOLLOW_INT_in_operand931); 

                    }
                    break;
                case 3 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:288:4: '[' INT ']'
                    {
                    match(input,68,FOLLOW_68_in_operand936); 

                    match(input,INT,FOLLOW_INT_in_operand938); 

                    match(input,70,FOLLOW_70_in_operand940); 

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
    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:291:1: list_of_parameters : operand ( ',' list_of_parameters )? ;
    public final void list_of_parameters() throws RecognitionException {
        try {
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:292:2: ( operand ( ',' list_of_parameters )? )
            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:292:4: operand ( ',' list_of_parameters )?
            {
            pushFollow(FOLLOW_operand_in_list_of_parameters951);
            operand();

            state._fsp--;


            // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:292:12: ( ',' list_of_parameters )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==17) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // /Users/ernstl/PostDoc/BU/Eugene/miniEugene/workspace/miniEugene-core/grammar/MiniEugene.g:292:13: ',' list_of_parameters
                    {
                    match(input,17,FOLLOW_17_in_list_of_parameters954); 

                    pushFollow(FOLLOW_list_of_parameters_in_list_of_parameters956);
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


 

    public static final BitSet FOLLOW_size_in_miniEugene45 = new BitSet(new long[]{0xFFF7FDFFFFE00600L,0x003FFFDFFBFFFF9FL});
    public static final BitSet FOLLOW_or_constraint_in_miniEugene52 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_miniEugene55 = new BitSet(new long[]{0xFFF7FDFFFFE00602L,0x003FFFDFFBFFFF9FL});
    public static final BitSet FOLLOW_composite_constraint_in_miniEugene58 = new BitSet(new long[]{0xFFF7FDFFFFE00602L,0x003FFFDFFBFFFF9FL});
    public static final BitSet FOLLOW_90_in_size80 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_size82 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_INT_in_size86 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_size88 = new BitSet(new long[]{0x0000020000000000L});
    public static final BitSet FOLLOW_41_in_size94 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_size96 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_INT_in_size100 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_size102 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_composite_constraint120 = new BitSet(new long[]{0x0000000000088000L});
    public static final BitSet FOLLOW_15_in_composite_constraint124 = new BitSet(new long[]{0x0000000000000600L,0x0000000000000010L});
    public static final BitSet FOLLOW_list_of_parameters_in_composite_constraint126 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_composite_constraint128 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_composite_constraint133 = new BitSet(new long[]{0xFFF7FDFFFFE00600L,0x003FFFDFFBFFFF9FL});
    public static final BitSet FOLLOW_composite_constraint_block_in_composite_constraint135 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_composite_constraint137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_composite_constraint_block153 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_composite_constraint_block156 = new BitSet(new long[]{0xFFF7FDFFFFE00600L,0x003FFFDFFBFFFF9FL});
    public static final BitSet FOLLOW_composite_constraint_block_in_composite_constraint_block158 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_constraint_in_or_constraint198 = new BitSet(new long[]{0x0008000000000002L,0x0040002000000020L});
    public static final BitSet FOLLOW_set_in_or_constraint203 = new BitSet(new long[]{0xFFF7FDFFFFE00600L,0x003FFFDFFBFFFF9FL});
    public static final BitSet FOLLOW_or_constraint_in_or_constraint215 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_constraint251 = new BitSet(new long[]{0xFDE7F5DFFFE00600L,0x0037F79FDB7FFF9DL});
    public static final BitSet FOLLOW_operand_in_constraint264 = new BitSet(new long[]{0xFDE7F5DFFFE00600L,0x0037F79FDB7FFF9DL});
    public static final BitSet FOLLOW_operator_in_constraint272 = new BitSet(new long[]{0x0000000000000602L,0x0000000000000010L});
    public static final BitSet FOLLOW_operand_in_constraint279 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_template_in_constraint292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pattern_in_constraint301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_group_in_constraint310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sequence_in_constraint319 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_template347 = new BitSet(new long[]{0x0000080000000000L,0x0008000020000002L});
    public static final BitSet FOLLOW_set_in_template353 = new BitSet(new long[]{0x0000000000000000L,0x0008000000000002L});
    public static final BitSet FOLLOW_set_in_template360 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_list_of_ids_in_template368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_pattern390 = new BitSet(new long[]{0x0010080000000000L,0x0000004020000000L});
    public static final BitSet FOLLOW_set_in_pattern396 = new BitSet(new long[]{0x0010000000000000L,0x0000004000000000L});
    public static final BitSet FOLLOW_set_in_pattern403 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_list_of_ids_in_pattern411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_sequence433 = new BitSet(new long[]{0x0200080000000000L,0x0000080020000000L});
    public static final BitSet FOLLOW_set_in_sequence439 = new BitSet(new long[]{0x0200000000000000L,0x0000080000000000L});
    public static final BitSet FOLLOW_set_in_sequence446 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_list_of_ids_in_sequence454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_group477 = new BitSet(new long[]{0x0000082000000000L,0x0000000020800000L});
    public static final BitSet FOLLOW_set_in_group483 = new BitSet(new long[]{0x0000002000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_set_in_group490 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_list_of_ids_in_group498 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_list_of_ids530 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_list_of_ids535 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_list_of_ids_in_list_of_ids539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator557 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator584 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator611 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator620 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator674 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator701 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator710 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator719 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator737 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator764 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator773 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator800 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator836 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator845 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator863 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator890 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_operator908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ID_in_operand925 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INT_in_operand931 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_operand936 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_INT_in_operand938 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_operand940 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_operand_in_list_of_parameters951 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_17_in_list_of_parameters954 = new BitSet(new long[]{0x0000000000000600L,0x0000000000000010L});
    public static final BitSet FOLLOW_list_of_parameters_in_list_of_parameters956 = new BitSet(new long[]{0x0000000000000002L});

}