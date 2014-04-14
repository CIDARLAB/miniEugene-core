package org.cidarlab.eugenerbc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.cidarlab.minieugene.dom.Component;
import org.cidarlab.minieugene.predicates.BinaryPredicate;
import org.cidarlab.minieugene.predicates.LogicalPredicate;
import org.cidarlab.minieugene.predicates.Predicate;
import org.cidarlab.minieugene.predicates.UnaryPredicate;
import org.cidarlab.minieugene.predicates.counting.Contains;
import org.cidarlab.minieugene.predicates.counting.Exactly;
import org.cidarlab.minieugene.predicates.counting.MoreThan;
import org.cidarlab.minieugene.predicates.orientation.AllForward;
import org.cidarlab.minieugene.predicates.orientation.AllReverse;
import org.cidarlab.minieugene.predicates.position.before.AllBefore;

public class EugeneRBC {
	
	public static BigInteger count(LogicalPredicate lp, int N) throws EugeneRBCCannotCalculateException {
		List<Predicate> predicates = new ArrayList<Predicate>(lp.getPredicates());
		boolean isSingleDirection = false;
		Predicate alldirectionPredicate = getAllDirectionPredicate(lp);
		if(alldirectionPredicate != null) {
			isSingleDirection = true;
			predicates.remove(alldirectionPredicate);
		}
		int L = getLibrary(lp).size();
		if(predicates.size() == 1) {
			return calculateSingleRule(predicates.get(0), N, L, isSingleDirection);
		} else {
			throw new EugeneRBCCannotCalculateException("Cannot yet calculate this predicate");
		}
	}
	
	public static BigInteger upperBound(LogicalPredicate lp, int N) {
		try {
			return count(lp, N);
		} catch (EugeneRBCCannotCalculateException e) {
			// Do nothing if cannot calculate
		}
		
		// Now will calculate the single rule bound
		// The count from a single rule will upper bound the total of all rules
		List<Predicate> predicates = new ArrayList<Predicate>(lp.getPredicates());
		boolean isSingleDirection = false;
		Predicate alldirectionPredicate = getAllDirectionPredicate(lp);
		if(alldirectionPredicate != null) {
			isSingleDirection = true;
			predicates.remove(alldirectionPredicate);
		}
		int L = getLibrary(lp).size();
		
		// Get the counts of every single rule
		List<BigInteger> upperBounds = new ArrayList<BigInteger>();
		for(Predicate predicate : predicates) {
			try {
				upperBounds.add(calculateSingleRule(predicate, N, L, isSingleDirection));
			} catch (EugeneRBCCannotCalculateException e) {
				// Do nothing if cannot calculate
			}
		}
		
		if(upperBounds.size() != 0) {
			return Combinatorics.max(upperBounds);
		} else {
			// If no counts are known, use the unconstrained bound
			return Combinatorics.pow((isSingleDirection ? 1 : 2) * L, N);
		}
	}
	
	/* Gets the first instance of ALL_FORWARDS or ALL_REVERSE that applies to all components and
	 * returns null if there is none
	 */
	private static Predicate getAllDirectionPredicate(LogicalPredicate lp) {
		List<Predicate> predicates = lp.getPredicates();
		for(Predicate predicate : predicates) {
			if(predicate instanceof AllForward || predicate instanceof AllReverse) {
				// if 'a' is null then ALL_<direction> applies to all components
				if(((UnaryPredicate) predicate).getA() == null) {
					return predicate;
				}
			}
		}
		return null;
	}
	
	public static Set<Component> getLibrary(LogicalPredicate lp) {
		List<Predicate> predicates = lp.getPredicates();
		Set<Component> components = new HashSet<Component>();
		for(Predicate predicate : predicates) {
			if(predicate instanceof UnaryPredicate) {
				Component a = ((UnaryPredicate) predicate).getA();
				if(a != null) {
					components.add(a);
				}
			}
			// BinaryPredicate extends UnaryPredicate so 'a' will have been obtained
			if(predicate instanceof BinaryPredicate) {
				Component b = ((BinaryPredicate) predicate).getB();
				if(b != null) {
					components.add(b);
				}
			}
		}
		return components;
	}
	
	private static BigInteger calculateSingleRule(Predicate predicate, int N, int L, boolean isSingleDirection) throws EugeneRBCCannotCalculateException {
		if(predicate instanceof Exactly) {
			return calculateSingleExactly((Exactly) predicate, N, L, isSingleDirection);
		} else if(predicate instanceof Contains) {
			return calculateSingleContains((Contains) predicate, N, L, isSingleDirection);
		} else if(predicate instanceof AllBefore) {
			return calculateSingleAllBefore((AllBefore) predicate, N, L, isSingleDirection);
		} else if(predicate instanceof MoreThan) {
			return calculateSingleMoreThan((MoreThan) predicate, N, L, isSingleDirection);
		} else {
			throw new EugeneRBCCannotCalculateException("Cannot yet calculate single " + predicate.getClass().toString().toUpperCase());
		}
	}
	
	private static BigInteger calculateSingleExactly(Exactly exactly, int N, int L, boolean isSingleDirection) throws EugeneRBCCannotCalculateException {
		int n = exactly.getNum();
		if(isSingleDirection) {
			return Combinatorics.nCr(N, n).multiply(Combinatorics.pow(L - 1, N - n));
		} else {
			throw new EugeneRBCCannotCalculateException("Cannot yet calculate single EXACTLY without singleDir true");
		}
	}
	
	private static BigInteger calculateSingleContains(Contains contains, int N, int L, boolean isSingleDirection) throws EugeneRBCCannotCalculateException {
		if(isSingleDirection) {
			return Combinatorics.pow(L, N).subtract(Combinatorics.pow(L - 1, N));
		} else {
			throw new EugeneRBCCannotCalculateException("Cannot yet calculate single CONTAINS without singleDir true");
		}
	}
	
	private static BigInteger calculateSingleAllBefore(AllBefore allbefore, int N, int L, boolean isSingleDirection) throws EugeneRBCCannotCalculateException {
		if(isSingleDirection) {
			BigInteger total = Combinatorics.pow(L - 1, N);
			for(int i = 1; i <= N; i++) {
				for(int j = i; j <= N; j++) {
					BigInteger term = Combinatorics.nCr(j - 1, i - 1);
					term.multiply(Combinatorics.pow(L - 2, j - i));
					term.multiply(Combinatorics.pow(L - 1, N - j));
					total.add(term);
				}
			}
			return total;
		} else {
			throw new EugeneRBCCannotCalculateException("Cannot yet calculate single ALL_BEFORE without singleDir true");
		}
	}
	
	private static BigInteger calculateSingleMoreThan(MoreThan morethan, int N, int L, boolean isSingleDirection) throws EugeneRBCCannotCalculateException {
		int n = morethan.getNum();
		if(isSingleDirection) {
			BigInteger total = BigInteger.ZERO;
			for(int i = n + 1; i <= N; i++) {
				BigInteger term = Combinatorics.nCr(N, i);
				term.multiply(Combinatorics.pow(L - 1, N - i));
				total.add(term);
			}
			return total;
		} else {
			throw new EugeneRBCCannotCalculateException("Cannot yet calculate single MORETHAN without singleDir true");
		}
	}
}
