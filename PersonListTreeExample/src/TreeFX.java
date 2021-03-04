import java.sql.Time;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class TreeFX extends Application {

	BinaryTree treeFName = new BinaryTree("more_names.csv");
	Person rootFName, rootSName;
	BinaryTree2 treeSName;
	HBox rightPane;
	HBox rHBox;
	VBox rVBox;
	ScrollPane sp;

	@Override
	public void start(Stage primaryStage) throws Exception {
		rootFName = treeFName.getRoot();
		treeSName = new BinaryTree2(rootFName);

		try {
			rightPane = new HBox();
			Group root = new Group();
			BorderPane border = new BorderPane();
			Scene scene = new Scene(root);
			StackPane leftPane = new StackPane();
			leftPane.setMinWidth(150);
			VBox vbox = new VBox();
			Button printAllNames = new Button("Print All Names");
			Button printTList = new Button("Print Tree in List");
			Button printTree = new Button("Draw Tree");
			Button change = new Button("Change/Update");
			Button search = new Button("Search");
			Button add = new Button("Add new Name");
			Button close = new Button("Close");
			Button fassend = new Button("Assending by First Name");
			Button fdesend = new Button("Descending by first Name");
			Button sassend = new Button("Assending by SurName");
			Button sdesend = new Button("Descending by Surname");
			Button bstTree = new Button("Good Tree structure");
			Button wstTree = new Button("Bad Tree structure");
			Button chooseFile = new Button("Choose File");
			Button compare = new Button("Compare Run Time");
			Button searchlength = new Button("Search by Length");
			Text msg = new Text();
			msg.setFont(Font.font ("Verdana", 30));
			msg.setFill(Color.RED);
			Text msg1 = new Text();
			msg1.setFont(Font.font ("Verdana", 20));
			msg1.setFill(Color.BLUE);

			add.setPrefSize(150, 25);
			chooseFile.setPrefSize(150, 25);
			printAllNames.setPrefSize(150, 25);
			printTList.setPrefSize(150, 25);
			printTree.setPrefSize(150, 25);
			sassend.setPrefSize(150, 25);
			close.setPrefSize(150, 25);
			fassend.setPrefSize(150, 25);
			sdesend.setPrefSize(150, 25);
			fdesend.setPrefSize(150, 25);
			change.setPrefSize(150, 25);
			search.setPrefSize(150, 25);
			bstTree.setPrefSize(150, 25);
			wstTree.setPrefSize(150, 25);
			compare.setPrefSize(150, 25);
			searchlength.setPrefSize(150, 25);

			vbox.setSpacing(5);
			vbox.getChildren().addAll(chooseFile,add, printTList, printTree, printAllNames,   search,searchlength, sassend, fassend,
					sdesend, fdesend, change, bstTree,wstTree,compare,close);

			rHBox = new HBox();
			rVBox = new VBox();
			rHBox.setMinSize(640, 600);
			rHBox.setAlignment(Pos.CENTER);

			leftPane.setStyle("-fx-background-color: black;");

			printAllNames.setOnAction(eventprint -> {
				clearAll();
				VBox rVBox1 = PrintAll(treeFName.getRoot(), rVBox);
				rHBox.getChildren().add(rVBox1);
				sp.setContent(rHBox);
				rightPane.getChildren().add(sp);
			});
			printTList.setOnAction(eventprint -> {
				clearAll();
				rVBox = PrintFTree(treeFName.getRoot(), rVBox);
				rVBox.setAlignment(Pos.CENTER_LEFT);
				rHBox.getChildren().add(rVBox);
				sp.setContent(rHBox);
				rightPane.getChildren().add(sp);
			});
			printTree.setOnAction(eventprint -> {
				clearAll();
				double width = sp.getWidth();
				VBox rVBox1 = PrintbFirst(treeFName.getRoot(), rVBox, width);
				rHBox.getChildren().add(rVBox1);
				sp.setContent(rHBox);
				rightPane.getChildren().add(sp);
			});
			bstTree.setOnAction(eventprint -> {
				clearAll();
				treeFName.bestTree();
				VBox rVBox1 = PrintFTree(treeFName.getRoot(), rVBox);
				rHBox.getChildren().add(rVBox1);
				sp.setContent(rHBox);
				rightPane.getChildren().add(sp);
			});
			wstTree.setOnAction(eventprint -> {
				clearAll();
				treeFName.worstTree();
				VBox rVBox1 = PrintFWTree(treeFName.getRoot(), rVBox);
				rHBox.getChildren().add(rVBox1);
				sp.setContent(rHBox);
				rightPane.getChildren().add(sp);
			});
			// Search A name on search button.
			search.setOnAction(eventsearch -> {
				clearAll();
				msg.setText("Search for a Person from the Tree Data Structure.");
				msg1.setText("Type in the First Name.");
				
				rVBox.getChildren().addAll(msg,msg1,rHBox);
				rVBox.setAlignment(Pos.CENTER);
				
				TextField sfield = new TextField("Enter the name to search");
				sfield.focusedProperty().addListener(event2 -> sfield.clear());
				rHBox.getChildren().add(sfield);
				rightPane.getChildren().addAll(rVBox);
				sfield.setOnAction(event1 -> {
					try {
						String sName = sfield.getText().trim();

						rHBox.getChildren().clear();
						Person P = treeFName.SearchName(sName);
						if (P != null) {
							rHBox.getChildren().add(new Text(sName + " is found in the List\n First name:\t"
									+ P.getName() + "\nSur Name:\t" + P.getsName()));
						} else {
							rHBox.getChildren().add(new Text("Person with " + sName + " is not found in the List"));
						}
					} catch (Exception e) {
						System.out.println("Error!");
					}

				});
			});
			// Add a New Entry.
			add.setOnAction(eventAdd -> {
				clearAll();
				msg.setText("Add a new Person to the Tree Data Structure.");
				msg1.setText("Type in the First Name and second name of the person to add.");
				
				
				rVBox.setAlignment(Pos.CENTER);
				rVBox.setSpacing(10);
				Button add1 = new Button("Add");
				add1.setPrefSize(100, 20);
				Text fn = new Text("Enter First Name\t\t\t");
				TextField fName = new TextField();
				Text sn = new Text("SurName / Second Name\t");
				TextField sName = new TextField();
				HBox fHBox = new HBox();
				fHBox.setAlignment(Pos.CENTER);
				HBox sHBox = new HBox();
				sHBox.setAlignment(Pos.CENTER);
				fHBox.getChildren().addAll(fn, fName);
				sHBox.getChildren().addAll(sn, sName);
				Text error = new Text("");
				rVBox.getChildren().addAll(msg,msg1,fHBox, sHBox, add1, error);
				add1.setOnAction(eventAdd1 -> {
					String ftName = fName.getText().trim();
					String sdName = sName.getText().trim();
					if (ftName.equals("") || sdName.equals("")) {
						error.setText("Error: Invalid First name or SurName");
						return;
					} else if (ftName != null && sdName != null) {
						fName.clear();
						sName.clear();
						error.setText(ftName + " " + sdName + " is added to the List");
						Person newP = new Person(ftName, sdName);
						treeFName.AddName1(treeFName.getRoot(), newP);
						treeSName.AddName1(treeSName.getSRoot(), newP);
					}
				});
				rHBox.getChildren().add(rVBox);
				rightPane.getChildren().add(rHBox);

//				
			});
			change.setOnAction(arg0event2 -> {
				clearAll();
				msg.setText("Change the Name of Surname of an existing\nPerson in the Tree Data Structure.");
				msg1.setText("Type in the First Name of the \nperson to change or update");
				rVBox.setAlignment(Pos.CENTER);
				TextField sfield = new TextField("Enter the name to Change / Update");
				sfield.focusedProperty().addListener(event2 -> sfield.clear());
				rHBox.getChildren().add(rVBox);
				rVBox.getChildren().addAll(msg,msg1,sfield);
				rightPane.getChildren().add(rHBox);
				sfield.setOnAction(event1 -> {

					String srName = sfield.getText();

					// rVBox.getChildren().clear();
					Person p = treeFName.SearchName(srName);
					
					if (p == null) {
						rVBox.getChildren().add(new Text("Person with " + srName + " is not found in the List"));
					} else {
						rVBox.getChildren().clear();
						String ftName = p.getName();
						String sdName = p.getsName();

						rVBox.setSpacing(10);
						rVBox.setAlignment(Pos.CENTER);
						Button update = new Button("Update/Change");
						update.setPrefSize(100, 20);
						Text fn = new Text("Update First Name\t\t\t\t");
						TextField fName = new TextField(ftName);
						Text sn = new Text("Update SurName / Second Name\t");
						TextField sName = new TextField(sdName);
						HBox fHBox = new HBox();
						HBox sHBox = new HBox();
						fHBox.getChildren().addAll(fn, fName);
						sHBox.getChildren().addAll(sn, sName);
						rVBox.getChildren().addAll(fHBox, sHBox, update);
						update.setOnAction(eventUpdate -> {
							String nFName = fName.getText().trim();
							String nSName = sName.getText().trim();
							boolean done = treeFName.UpdateP(p, nFName, nSName);
							treeSName.UpdateP(p);
							if (done) {
								rVBox.getChildren().clear();
								rVBox.getChildren()
										.add(new Text(ftName + " " + sdName + " is updated to " + p.toString()));
							}
						}

						);
					}

				});
			});
			searchlength.setOnAction(arg0event2 -> {
				clearAll();
				//VBox rVBox1;
				msg.setText("Search for names greater than a entered value.");
				msg1.setText("Enter a valid number for your search.");
				rVBox.setAlignment(Pos.CENTER);
				TextField sfield = new TextField("Enter the lenght of charecters to search for");
				sfield.focusedProperty().addListener(event2 -> sfield.clear());
				rHBox.getChildren().add(rVBox);
				rVBox.getChildren().addAll(msg,msg1,sfield);
				rightPane.getChildren().add(rHBox);
				sfield.setOnAction(event1 -> {

					String numstr = sfield.getText();
					int num = Integer.parseInt(numstr);
					rVBox.getChildren().clear();
					 rVBox = PrintAllSearch(num,treeFName.getRoot(), rVBox);

									
						
					//rHBox.getChildren().add(rVBox);
					sp.setContent(rHBox);
					rightPane.getChildren().add(sp);
						
					

				
			});
			}		);
			fassend.setOnAction(event1 -> {

				clearAll();
				VBox rVBox1 = PrintAll(treeFName.getRoot(), rVBox);
				// System.out.println(entry.getRoot());
				rHBox.getChildren().add(rVBox1);
				sp.setContent(rHBox);
				rightPane.getChildren().add(sp);

			});

			fdesend.setOnAction(event -> {
				clearAll();
				VBox rVBox1 = PrintFNdesnding(treeFName.getRoot(), rVBox);
				// System.out.println(entry.getRoot());
				rHBox.getChildren().add(rVBox1);
				sp.setContent(rHBox);
				rightPane.getChildren().add(sp);

			});
			chooseFile.setOnAction(event -> {
				clearAll();
				Text error = new Text("");
				Button add1 = new Button("Create New Tree");
				add1.setPrefSize(100, 20);
				rVBox.setSpacing(10);
				String[] fileNames = 
	                { "mswdev.csv", "more_names.csv"}; 
	         ComboBox<String> cBox = 
	                  new ComboBox<String>(FXCollections.observableArrayList(fileNames)); 
				cBox.setPromptText("Choose a File");
				add1.setOnAction(eventbox->{
					String fileName = (String)cBox.getValue();
					System.out.println(fileName);
					if (fileName == null)error.setText("Choose A Valid File");
					else{treeFName = new BinaryTree(fileName);
					rootFName = treeFName.getRoot();
						treeSName = new BinaryTree2(rootFName);
						error.setText("new Tree Data Strucutre created with file "+ fileName);
					rVBox.getChildren().removeAll(add1,cBox)	;
					}
				});
				
				rVBox.getChildren().addAll(cBox,add1,error);
				rHBox.getChildren().add(rVBox);
				sp.setContent(rHBox);
				rightPane.getChildren().add(sp);

			});
			compare.setOnAction(event -> {
				clearAll();
				rVBox.setAlignment(Pos.CENTER);
				TextField sfield = new TextField("Enter the name to compare the search on best tree and worst tree");
				sfield.focusedProperty().addListener(event2 -> sfield.clear());
				rHBox.getChildren().add(rVBox);
				rVBox.getChildren().add(sfield);
				rightPane.getChildren().add(rHBox);
				sfield.setOnAction(event1 -> {

					String srName = sfield.getText();

					Person p = treeFName.SearchName(srName);
					
					if (p == null) {
						rVBox.getChildren().add(new Text("Person with " + srName + " is not found in the List"));
					} else {
						rVBox.getChildren().clear();
						rVBox.setSpacing(10);
						rVBox.setAlignment(Pos.CENTER);
						treeFName.bestTree();
						long t1= System.currentTimeMillis();
						treeFName.SearchName1(treeFName.getRoot(),p.fName);
						long t2= System.currentTimeMillis();
						treeFName.worstTree();
						long t3= System.currentTimeMillis();
						treeFName.SearchName1(treeFName.getRoot(),p.fName);
						long t4= System.currentTimeMillis();
						long t5 = (t4-t3);
						long t6 = (t2-t1);
						Text bst = new Text("Best Tree\n Search Time for: \n"+p.toString()+"\n"+t6);
						Text wst = new Text("Worst Tree\n Search Time for: \n"+p.toString()+"\n"+t5);
						rHBox.getChildren().clear();
						rHBox.getChildren().addAll(bst,wst);
						
						

					}});
			});



			
			sassend.setOnAction(event1 -> {

				clearAll();
				VBox rVBox1 = PrintAll2(treeSName.getSRoot(), rVBox);
				// System.out.println(entry.getRoot());
				rHBox.getChildren().add(rVBox1);
				sp.setContent(rHBox);
				rightPane.getChildren().add(sp);

			});

			sdesend.setOnAction(event -> {
				clearAll();
				VBox rVBox1 = PrintSNdesnding(treeSName.getSRoot(), rVBox);
				// System.out.println(entry.getRoot());
				rHBox.getChildren().add(rVBox1);
				sp.setContent(rHBox);
				rightPane.getChildren().add(sp);

			});
			close.setOnAction(eventClose -> primaryStage.close()

			);

			HBox mHBox = new HBox();
			mHBox.setPrefSize(1200, 650);
			mHBox.getChildren().addAll(leftPane, rightPane);
			// mHBox.setDividerPositions(0.20);

			leftPane.getChildren().add(vbox);
			vbox.setAlignment(Pos.CENTER);

			root.getChildren().addAll(border, mHBox);

			primaryStage.setTitle("Tree Example");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private VBox PrintAllSearch(int num, Person p, VBox rVBox2) {
		if(p.getName().length()>num)rVBox2.getChildren().add(new Text(p.toString()));
		if (p.getFBefore() != null) {
			PrintAllSearch(num,p.getFBefore(), rVBox2);
		}
				if (p.getFAfter() != null) {
			// System.out.println(p + "in");
			PrintAllSearch(num,p.getFAfter(), rVBox2);
		}
	
		return rVBox2;
	}

	private void clearAll() {
		rightPane.getChildren().clear();
		rVBox.getChildren().clear();
		rVBox.setAlignment(Pos.CENTER);
		rVBox.setSpacing(0);
		rHBox.getChildren().clear();
		sp = new ScrollPane();
		sp.setMinSize(1000, 600);
		

	}

	public VBox PrintAll(Person p, VBox rVBox) {
		// System.out.println(p);

		if (p.getFBefore() != null) {
			PrintAll(p.getFBefore(), rVBox);
		}
		rVBox.getChildren().add(new Text(p.toString()));
		if (p.getFAfter() != null) {
			// System.out.println(p + "in");
			PrintAll(p.getFAfter(), rVBox);
		}
		// System.out.println(names);
		return rVBox;
	}

	public VBox PrintAll2(Person p, VBox rVBox) {
		// System.out.println(p);

		if (p.getSBefore() != null) {
			PrintAll2(p.getSBefore(), rVBox);
		}
		rVBox.getChildren().add(new Text(p.toString()));
		if (p.getSAfter() != null) {
			// System.out.println(p + "in");
			PrintAll2(p.getSAfter(), rVBox);
		}
		// System.out.println(names);
		return rVBox;
	}

	Stack<Person> descend = new Stack<Person>();

//	Desesnding by First Name
	public VBox PrintFNdesnding(Person p, VBox rVBox) {
		// System.out.println(p);

		// descend.push(p);
		if (p.getFAfter() != null) {
			PrintFNdesnding(p.getFAfter(), rVBox);
		}
		rVBox.getChildren().add(new Text(p.toString()));
		if (p.getFBefore() != null) {
			PrintFNdesnding(p.getFBefore(), rVBox);

		}
//		while(!descend.isEmpty()) {
//		rVBox.getChildren().add(new Text(descend.pop().toString()));
//		}
		return rVBox;
	}

	public VBox PrintSNdesnding(Person p, VBox rVBox) {
		if (p.getSAfter() != null) {
			PrintSNdesnding(p.getSAfter(), rVBox);
		}
		rVBox.getChildren().add(new Text(p.toString()));
		if (p.getSBefore() != null) {
			PrintSNdesnding(p.getSBefore(), rVBox);

		}
		return rVBox;
	}

	public VBox PrintDFist(Person p, VBox rVBox) {
		// System.out.println(p);
		rVBox.getChildren().add(new Text(p.toString()));
		if (p.getFBefore() != null) {
			PrintDFist(p.getFBefore(), rVBox);
		}
		
		if (p.getFAfter() != null) {
			// System.out.println(p + "in");
			PrintDFist(p.getFAfter(), rVBox);
		}
		// System.out.println(names);
		return rVBox;
	}

	public VBox PrintbFirst(Person root, VBox rVBox, double width) {
		Queue<Person> todo = new ArrayDeque<Person>();
		todo.offer(root);
		int level = root.getLevel();
		HBox hbx = new HBox();
		hbx.setAlignment(Pos.CENTER);
		int count = 1;
		while (!todo.isEmpty()) {
			Person p = todo.poll();

			if (level != p.getLevel()) {
				count++;

				rVBox.getChildren().add(hbx);
				hbx = new HBox();
				hbx.setAlignment(Pos.CENTER);
				level = p.getLevel();
			}
			HBox hbx1 = new HBox();
			hbx1.setAlignment(Pos.CENTER);
			hbx1.getChildren().add(new Text(p.getName()));
			hbx.getChildren().add(hbx1);

			if (p.getFBefore() != null) {
				todo.offer(p.getFBefore());

			}
			if (p.getFAfter() != null) {
				todo.offer(p.getFAfter());

			}

		}
		return rVBox;
	}

	public VBox PrintFTree(Person p, VBox rVBox) {
		rVBox.getChildren().add(new Text(p.toString1()));
		if (p.getFBefore() !=null) {
			PrintFTree(p.getFBefore(), rVBox);
		}
		if (p.getFAfter()!=null) {

			PrintFTree(p.getFAfter(), rVBox);
		}

		return rVBox;

	}

	public VBox PrintFWTree(Person p, VBox rVBox) {
		rVBox.getChildren().add(new Text(p.toString2()));
		if (p.getFAfter() != null) {

			PrintFWTree(p.getFAfter(), rVBox);
		}

		return rVBox;

	}

	public static void main(String[] args) {
		Application.launch(args); // TODO Auto-generated method stub

	}
}
