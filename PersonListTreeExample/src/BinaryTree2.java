import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BinaryTree2 {
	Scanner scan = new Scanner(System.in);
	Person sroot;

	ArrayList<Person> persons = new ArrayList<Person>();

	public BinaryTree2(Person root) {
		this.sroot =root;
		this.bestTree();
		
	}

	

	public ArrayList<Person> sortInOrder() {
		ArrayList<Person> persons1 = new ArrayList<Person>();
		SNameComparator sNameSort = new SNameComparator();
		// persons.clear();
		persons1 = sort(sroot, persons1);
		Collections.sort(persons1, sNameSort);
		return persons1;
	}

	
	public ArrayList<Person> sort(Person p, ArrayList<Person> persons1) {

		if (p.getFBefore() != null)
			sort(p.getFBefore(), persons1);
		persons1.add(p);
		p.setSLevel(0);
		if (p.getFAfter() != null)
			sort(p.getFAfter(), persons1);
		return persons1;
	}
	

	public void bestTree() {
		ArrayList<Person> persons1 = sortInOrder();

		sroot = persons1.get((persons1.size() - 1) / 2);
		sroot.setLevel(0);
		bestSTree1(0, persons1.size() - 1, persons1);
		setNewSLevel(sroot);
	}

	public Person bestSTree1(int first, int last, ArrayList<Person> persons1) {
		if (first > last)
			return null;
		int mid = (first + last) / 2;
		Person p = persons1.get(mid);
		

		p.setSBefore1(bestSTree1(first, mid - 1, persons1));
		
		p.setSAfter1(bestSTree1(mid + 1, last, persons1));
		
		return p;
	}
	
	////////////////////
	
public void setNewSLevel(Person p) {
	if(p.getSParent()!=null&&p!=null)p.setnewSLevel();
	if (p.getSBefore() != null)
		setNewSLevel(p.getSBefore());
		if (p.getSAfter() != null)
			setNewSLevel(p.getSAfter());
	
}
	
	public boolean UpdateP(Person p) {
		Person tempSAfter = p.getSAfter();
		Person tempSBefore = p.getSBefore();
		p.setSAfter(null);
		p.setSBefore(null);
		Person prntS = p.getSParent();
		if (prntS != null) {
			if (prntS.getSAfter() == p)
				prntS.setSAfter(null);
			else if (prntS.getSBefore() == p)
				prntS.setSBefore(null);
			
			if (p != null)
				this.AddName1(sroot, p);
			
			if (tempSAfter != null)
				this.AddName1(sroot, tempSAfter);
			
			if (tempSBefore != null)
				this.AddName1(sroot, tempSBefore);
		
		}
		if (prntS == null && sroot == p) {
			
			p.setSAfter(null);
			p.setSBefore(null);
			if (tempSAfter != null)
				this.AddName1(sroot, tempSAfter);
			
			if (tempSBefore != null)
				this.AddName1(sroot, tempSBefore);
			
		}
		return true;
	}

	

	
	
	public void AddName1(Person currentP, Person newP) {
		if (newP.getsName().compareToIgnoreCase(currentP.getsName()) < 0) {
			if (currentP.getSBefore() == null)
				currentP.setSBefore(newP);
			else {
				currentP = currentP.getSBefore();
				AddName1(currentP, newP);
			}
		} else if (currentP.getSAfter() == null)
			currentP.setSAfter(newP);
		else {
			currentP = currentP.getSAfter();
			AddName1(currentP, newP);
		}
	}
	

	public Person getSRoot() {
		return sroot;
	}

	public void setSRoot(Person root) {
		this.sroot = root;
	}
}
