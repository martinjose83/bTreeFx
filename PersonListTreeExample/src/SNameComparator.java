import java.util.Comparator;

public class SNameComparator implements Comparator<Person> {
	

	public SNameComparator() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Person o1, Person o2) {
		if(o1.getsName()==o2.getsName()) {
			return 0;}
			else {
				if (o1.getsName().compareToIgnoreCase(o2.getsName())<0) {return -1;}
				return 1;
			}
	}

}
