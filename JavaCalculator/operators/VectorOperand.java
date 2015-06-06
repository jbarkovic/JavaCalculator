package operators;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import exceptions.MathException;
import exceptions.OperatorException;
import exceptions.SyntaxException;

public class VectorOperand extends Operator implements List<Operator> {
	protected List<Operator> list;
	public VectorOperand (int size) {
		list = new ArrayList<Operator> (size);
	}
	public VectorOperand (Operator [] vector) {
		list = new ArrayList<Operator> (vector.length);
		for (int i=0;i<vector.length;i++) {
			if (vector[i] != null) list.add(vector[i]);
		}
	}
	public VectorOperand(Operator op0) {
		(list = new ArrayList<Operator> (1)).add(op0);
	}
	@Override
	public int size() {
		return list.size();
	}
	@Override
	public double internalCalc () throws SyntaxException, MathException, OperatorException {
		if (list.size() > 0) return list.get(0).eval();
		else return Double.NaN;
	}
	@Override
	public boolean add(Operator e) {
		if (e instanceof VectorOperand) {
			return list.addAll((VectorOperand) e);
		}
		return list.add(e);
	}
	@Override
	public void add(int index, Operator element) {	
		if (element instanceof VectorOperand) {
			list.addAll(index,(VectorOperand) element);
		}
		list.add(index,element);
	}
	@Override
	public boolean addAll(Collection<? extends Operator> c) {
		return list.addAll(c);
	}
	@Override
	public boolean addAll(int index, Collection<? extends Operator> c) {
		return list.addAll(index,c);
	}
	@Override
	public void clear() {
		list.clear();		
	}
	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}
	@Override
	public boolean containsAll(Collection<?> c) {		
		return list.containsAll(c);
	}
	@Override
	public Operator get(int index) {
		return list.get(index);
	}
	@Override
	public int indexOf(Object o) {
		return list.indexOf(o);
	}
	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}
	@Override
	public Iterator<Operator> iterator() {
		return list.iterator();
	}
	@Override
	public int lastIndexOf(Object o) {
		return list.lastIndexOf(o);
	}
	@Override
	public ListIterator<Operator> listIterator() {
		return list.listIterator();
	}
	@Override
	public ListIterator<Operator> listIterator(int index) {
		return list.listIterator(index);
	}
	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}
	@Override
	public Operator remove(int index) {
		return list.remove(index);
	}
	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}
	@Override
	public boolean retainAll(Collection<?> c) {
		return list.removeAll(c);
	}
	@Override
	public Operator set(int index, Operator element) {
		return list.set(index, element);
	}
	@Override
	public List<Operator> subList(int fromIndex, int toIndex) {
		return list.subList(fromIndex, toIndex);
	}
	@Override
	public Object[] toArray() {
		return list.toArray();
	}
	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}
}
