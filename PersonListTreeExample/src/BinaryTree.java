import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BinaryTree {
	Scanner scan = new Scanner(System.in);
	Person root;

	ArrayList<Person> persons = new ArrayList<Person>();

	public BinaryTree(String fileName) {
		ReadFile1(fileName);
		setNewLevel(root);
	}

	public void ReadFile1(String fileName) {
		try {
			// System.out.println(fileName);
			Scanner scanner = new Scanner(new File(fileName));
			while (scanner.hasNext()) {
				String name = scanner.nextLine().trim();
				String[] names1 = name.split(" ");
				persons.add(new Person(names1[0], names1[1]));
				// AddName(names[0], names[1]);
			}
			scanner.close();

		} catch (FileNotFoundException e) {
			System.out.printf("Error loading file: %s%n", e);
		}
		root = persons.get((int) (persons.size() / 2));
		root.setLevel(1);

		 for (Person p : persons) {
		 if(root!=p)
		 AddName1(root, p);
		
		 }
	}

	public ArrayList<Person> sortInOrder() {
		ArrayList<Person> persons1 = new ArrayList<Person>();
		// persons.clear();
		sort(root, persons1);
		return persons1;
	}

	public ArrayList<Person> sort(Person p, ArrayList<Person> persons1) {

		if (p.getFBefore() != null)
			sort(p.getFBefore(), persons1);
		persons1.add(p);
		p.setLevel(0);
		if (p.getFAfter() != null)
			sort(p.getFAfter(), persons1);
		return persons1;
	}
	Queue<Person> persons1 = new ArrayDeque<Person>();
	public void worstTree() {
		persons1.clear();
		worstTree1(root);
		root = persons1.poll();
		root.setLevel(0);
		Person p1 = root;
		while(!persons1.isEmpty()) {
			
			Person p2 = persons1.poll();
			p1.setFAfter(p2);
			p1=p2;
			
			}
		//setNewLevel(root);
	}
	public void worstTree1(Person p) {
		
	if(p.getFBefore()!=null)worstTree1(p.getFBefore());
	persons1.offer(p);	
	if(!p.getFAfter().equals(null))worstTree1(p.getFAfter());
	}

	public void bestTree() {
		ArrayList<Person> persons1 = sortInOrder();

		root = persons1.get((persons1.size() - 1) / 2);
		root.setLevel(0);
		bestTree1(0, persons1.size() - 1, persons1);
		setNewLevel(root);
	}

	public Person bestTree1(int first, int last, ArrayList<Person> persons1) {
		if (first > last)
			return null;
		int mid = (first + last) / 2;
		Person p = persons1.get(mid);
		

		p.setFBefore1(bestTree1(first, mid - 1, persons1));
		
		p.setFAfter1(bestTree1(mid + 1, last, persons1));
		
		return p;
	}
	
	// change the levels on person after update.	
public void setNewLevel(Person p) {
	if(p.getParent()!=null&&p!=null)p.setnewLevelnew();
	if (p.getFBefore() != null)
		setNewLevel(p.getFBefore());
		if (p.getFAfter() != null)
			setNewLevel(p.getFAfter());
	
}
	public Person SearchName(String fname) {
		Person p = SearchName1(root, fname);
		return p;
	}

	public Person SearchName1(Person currentP, String fname) {
		if (fname.compareToIgnoreCase(currentP.getName()) == 0) {
			return currentP;
		}
		if (fname.compareToIgnoreCase(currentP.getName()) > 0)
			currentP = currentP.getFAfter();
		else if (fname.compareToIgnoreCase(currentP.getName()) < 0)
			currentP = currentP.getFBefore();
		if (currentP != null)
			return SearchName1(currentP, fname);
		return null;
	}

	public boolean UpdateP(Person p, String nFName, String nSName) {
		Person tempAfter = p.getFAfter();
		Person tempBefore = p.getFBefore();
		p.setFAfter(null);
		p.setFBefore(null);
		Person prevP = p.getParent();
		if (prevP != null) {
			if (prevP.getFAfter() == p)
				prevP.setFAfter(null);
			else if (prevP.getFBefore() == p)
				prevP.setFBefore(null);
			p.setfName(nFName);
			p.setsName(nSName);
			if (p != null)
				this.AddName1(root, p);
			if (tempAfter != null)
				this.AddName1(root, tempAfter);
			if (tempBefore != null)
				this.AddName1(root, tempBefore);
		}
		if (prevP == null && root == p) {
			p.setfName(nFName);
			p.setsName(nSName);
			p.setFAfter(null);
			p.setFBefore(null);
			if (tempAfter != null)
				this.AddName1(root, tempAfter);
						if (tempBefore != null)
				this.AddName1(root, tempBefore);
					}
		return true;
	}

	
	public void AddName(String fName, String sName) {
		Person newP = new Person(fName, sName);
		AddName1(root, newP);
	}
	
	public void AddName1(Person currentP, Person newP) {
		if (newP.getName().compareToIgnoreCase(currentP.getName()) < 0) {
			if (currentP.getFBefore() == null)
				currentP.setFBefore(newP);
			else {
				currentP = currentP.getFBefore();
				AddName1(currentP, newP);
			}
		} else if (currentP.getFAfter() == null)
			currentP.setFAfter(newP);
		else {
			currentP = currentP.getFAfter();
			AddName1(currentP, newP);
		}
	}
	

	public Person getRoot() {
		return root;
	}

	public void setRoot(Person root) {
		this.root = root;
	}
}
