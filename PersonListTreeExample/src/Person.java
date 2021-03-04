
public class Person {
	
String fName;
String sName;
Person beforef;
Person befores;
Person afterf;
Person afters;
Person Parent;
Person sParent;
int level;
int slevel;

	public Person(String fName,String sName) {
		this.fName = fName;
		this.sName =sName;
		
	}
	
	public String getName() {
		return fName;
	}

	
	public String getsName() {
		return sName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public void setsName(String sName) {
		this.sName = sName;
	}

	public Person getFBefore() {
		return beforef;
	}

	public void setFBefore(Person beforef) {
		this.beforef = beforef;
		if( beforef!=null)beforef.setParent(this);
		if( beforef!=null)beforef.setLevel(level+1);
		
	}
	public void setFBefore1(Person beforef) {
		this.beforef = beforef;
		if( beforef!=null)beforef.setParent(this);
		//if( beforef!=null)beforef.setLevel(level+1);
		
	}

	public Person getFAfter() {
		return afterf;
	}

	public void setFAfter(Person afterf) {
		this.afterf = afterf;
		if( afterf!=null)afterf.setParent(this);
		if( afterf!=null)afterf.setLevel(level+1);
	}
	public void setFAfter1(Person afterf) {
		this.afterf = afterf;
		if( afterf!=null)afterf.setParent(this);
		//if( afterf!=null)afterf.setLevel(level+1);
	}

	public String toString() {
		return fName+" "+sName;
		
	}

	public int getSLevel() {
		return slevel;
	}

	public void setSLevel(int slevel) {
		this.slevel = slevel;
	}
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public void setnewLevelnew() {
		this.level = this.Parent.getLevel()+1;
	}
	public void setnewSLevel() {
		this.slevel = this.sParent.getLevel()+1;
	}

	public String toString1() {
		String name = "";
		for(int i = 0;i<level;i++) {
			name = name+"\t"+i;
		}
		name = name+">"+ this.fName;
		return name;
	}
	public String toString2() {
		String name = "";
		for(int i = 0;i<level;i++) {
			name = name+" ";
		}
		name = name+">"+ this.fName;
		return name;
	}

	public Person getSParent() {
		return sParent;
	}

	public void setSParent(Person sparent) {
		sParent = sparent;
	}
	public Person getParent() {
		return Parent;
	}

	public void setParent(Person parent) {
		Parent = parent;
	}
	public Person getSBefore() {
		return befores;
	}

	public void setSBefore(Person befores) {
		this.befores = befores;
		if( befores!=null)befores.setSParent(this);
		if( befores!=null)befores.setSLevel(slevel+1);
		
	}
	public void setSBefore1(Person befores) {
		this.befores = befores;
		if( befores!=null)befores.setSParent(this);
		//if( beforef!=null)beforef.setLevel(level+1);
		
	}

	public Person getSAfter() {
		return afters;
	}

	public void setSAfter(Person afters) {
		this.afters = afters;
		if( afters!=null)afters.setSParent(this);
		if( afters!=null)afters.setSLevel(slevel+1);
	}
	public void setSAfter1(Person afters) {
		this.afters = afters;
		if( afters!=null)afters.setSParent(this);
		//if( afterf!=null)afterf.setLevel(level+1);
	}

}
