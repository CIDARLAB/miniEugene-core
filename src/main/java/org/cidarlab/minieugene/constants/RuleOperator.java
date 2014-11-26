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

package org.cidarlab.minieugene.constants;

public enum RuleOperator {
	/* UNARY STRUCTURAL/POSITIONAL RULES */
	STARTSWITH, ENDSWITH,
	
	/* BINARY STRUCTURAL/POSITIONAL RULES */
	AFTER, ALL_AFTER, SOME_AFTER, 
	NEXTTO, ALL_NEXTTO, SOME_NEXTTO,
	LEFTTO, ALL_LEFTTO, SOME_LEFTTO,
	RIGHTTO, ALL_RIGHTTO, SOME_RIGHTTO,
	ALL_BEFORE, BEFORE,  SOME_BEFORE,
	
	/* NUMBERING/COUNTING RULES */
	CONTAINS, NOTCONTAINS, 
	MORETHAN, NOTMORETHAN, 
	EXACTLY, NOTEXACTLY,
	SAME_COUNT,

	/*
	 * ORIENTATION
	 */
	REVERSE, SOME_REVERSE, ALL_REVERSE,
	FORWARD, SOME_FORWARD, ALL_FORWARD,
	SAME_ORIENTATION, ALL_SAME_ORIENTATION, SOME_SAME_ORIENTATION, 
	ALTERNATE_ORIENTATION,
	
	/* RELATIONSHIP RULES */
	MATCHES,	
	
	/* PAIRING RULES */
	NOTTHEN, THEN, WITH, NOTWITH, NOTEQUALS, EQUALS,
	ALWAYS_NEXTTO,
	
	/* TEMPLATING */
	TEMPLATE, PATTERN, GROUP, SEQUENCE
}
