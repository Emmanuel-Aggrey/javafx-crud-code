
package oldstudent;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;


public class AdminPage extends Addmember{
      Addmember addMember = new Addmember();
   PasswordField confirmpass,password;
      ObservableList<Table>members=FXCollections.observableArrayList();
    TableView<Table>table;
    ComboBox search ;
    ComboBox addcourse;
    Button view,addmember,registerbtn,registeruser;
    TextField username; 
        Label promtErro;
    //FXCollections fillSearch;
    AdminPage(){
        promtErro=new Label();
         search=new ComboBox(fillCourse);
         search.setPromptText("search");
         
    vbox =new VBox(20);
                                   
      vbox.setPadding(new Insets(10,10,10,10));
   hbox=new HBox(20);
   
    addcourse=new ComboBox(fillCourse);
    addcourse.setEditable(true);
    addcourse.setPromptText("add a new course");
    addcourse.setTooltip(new Tooltip("already exist course won't be added"));
    
     registerbtn =new Button("register");
    addmember=new Button("add member");
    save=new Button("save");
    view=new Button("view members");
    back=new Button("back");
    registeruser=new Button("register user");
     delete=new Button("delete");
    hbox.getChildren().addAll(save,view,addmember,registeruser);
    
    //REGISTER FORM FIELDS
          password=TextFields.createClearablePasswordField();
          password.setPromptText("new passord");
          confirmpass=TextFields.createClearablePasswordField();
           confirmpass.setPromptText("confirm password");
        username = TextFields.createClearableTextField();//forget password
       registerbtn = new Button("register");
        username.setPromptText("please input name");
  
      
    
     
        vbox.getChildren().addAll(new Label("add new course"),addcourse,hbox);
        
        //selects all courses from Addmember class
     selectCourse(addcourse);
    // selectCourse(search);
        
      save.setOnAction(e->{
      addNewCourse();
      });
      
      registeruser.setOnAction(e->{registerUserForm();});
        
      view.setOnAction(e->{
          vbox.getChildren().clear();
       //  vbox.setPrefSize(1020,700);
         stage.setWidth(1030);
         stage.setHeight(500);
          //table.setItems(getMembers());
           buildTable();
      });
      
    
    
      //provides a search for the comboBox
     //  addcourse.getItems().addAll(getMembers());
     TextFields.bindAutoCompletion(addcourse.getEditor(), addcourse.getItems());
         
      addmember.setOnAction(e->{
    addMember.addmember();});
      
      
        //register new user
      registerbtn.setOnAction(e->{
      if (username.getText().isEmpty() || password.getText().isEmpty()){ }
      else{registerUser(registerbtn);
      }
      });
  
      
      
      
      //register user
      
      

    stage=new Stage();
     
       //stage.initStyle(StageStyle.UTILITY);
    scene =new Scene(vbox, 600, 300);
     scene.getStylesheets().add("allpages.css");
    stage.setScene(scene);
     
  
    stage.show();
    }
    
    void buildTable(){
    
        Label getMembersSize=new Label();
        Label getSearchResultSize=new Label();
        
        HBox hb=new HBox(100,delete,getMembersSize,getSearchResultSize);
        TableColumn<Table,String>sname_colum=new TableColumn<>("Surname");
        sname_colum.setPrefWidth(100);
    sname_colum.setCellValueFactory(new PropertyValueFactory<>("sname"));
        
        TableColumn<Table,String>fname_colum=new TableColumn<>("First name");
        fname_colum.setPrefWidth(100);
     fname_colum.setCellValueFactory(new PropertyValueFactory<>("fname"));
        //fname,sname,dob,contact,house,email,course,occupation,location,date_saved;

        
        TableColumn<Table,String>bod_coclum=new TableColumn<>("Date of Birth");
        bod_coclum.setPrefWidth(100);
     bod_coclum.setCellValueFactory(new PropertyValueFactory<>("dob"));
        
        TableColumn<Table,String>contact_colum=new TableColumn<>("Contact");
        contact_colum.setPrefWidth(100);
     contact_colum.setCellValueFactory(new PropertyValueFactory<>("contact"));
     
     
   TableColumn<Table,String>house_colum=new TableColumn<>("House");
        house_colum.setPrefWidth(100);
     house_colum.setCellValueFactory(new PropertyValueFactory<>("house"));
        
   
        TableColumn<Table,String>email_colum=new TableColumn<>("Email");
        email_colum.setPrefWidth(100);
     email_colum.setCellValueFactory(new PropertyValueFactory<>("email"));
        
      TableColumn<Table,String>course_colum=new TableColumn<>("Course");
        course_colum.setPrefWidth(100);
     course_colum.setCellValueFactory(new PropertyValueFactory<>("course"));
     
     
        TableColumn<Table,String>occupation_colum=new TableColumn<>("Occupation");
        occupation_colum.setPrefWidth(100);
     occupation_colum.setCellValueFactory(new PropertyValueFactory<>("occupation"));
     
       
        TableColumn<Table,String>location_colum=new TableColumn<>("Location");
        location_colum.setPrefWidth(100);
     location_colum.setCellValueFactory(new PropertyValueFactory<>("location"));
     
     
        TableColumn<Table,String>dateSaved_colum=new TableColumn<>("Date saved");
        dateSaved_colum.setPrefWidth(100);
     dateSaved_colum.setCellValueFactory(new PropertyValueFactory<>("date_saved"));
       
     table=new TableView<>();
   
    table.getColumns().addAll(sname_colum,fname_colum,bod_coclum,contact_colum,house_colum);
    table.getColumns().addAll(email_colum,course_colum,occupation_colum,location_colum,dateSaved_colum);
    search.setEditable(true);
    table.setPlaceholder(new Label("search result not found... "));
    table.setTableMenuButtonVisible(true);
    //delete selected value in table
    delete.setOnAction(e->{deleteMember();});
     
    vbox.getChildren().addAll(back,search,table,hb);
    table.setItems(getMembers());

       getMembersSize.setText("Available menbers: "+table.getItems().size());


         //provides a search for the comboBox
         
    //   search.getItems().addAll(getMembers());//commented bcoz combobox is already filld from addmember class method
    
     TextFields.bindAutoCompletion(search.getEditor(), search.getItems());
      search.setOnKeyReleased(e->{
          table.getItems().clear();
         searchTable(search);
           getSearchResultSize.setText("search result: "+table.getItems().size());
   hb.getChildren().add(getSearchResultSize);
      });
      
      search.setOnAction(e->{
       table.getItems().clear();
          searchTable(search);
        
           getSearchResultSize.setText("search result: "+table.getItems().size());
 //   hb.getChildren().add(getSearchResultSize);
      });
      
      
      //getback to main page
      switchBack();
      
    
    }
    
    public final ObservableList<Table>getMembers(){
        
        try {
          
            String query="SELECT * FROM members  ORDER BY sname ";
            pst=con.prepareStatement(query);
            rset=pst.executeQuery();
            while(rset.next()) {
                members.add(new Table(
                               rset.getString("id"),
                                rset.getString("sname"),
                                rset.getString("fname"),
                                rset.getString("dob"),
                                rset.getString("contact"),
                                rset.getString("house"),
                                rset.getString("email"),
                                rset.getString("course"),
                                rset.getString("occupation"), 
                                rset.getString("location"),
                                rset.getString("date_saved")
                                                                                   
                        ));
                
                   //return members; 
                   
            }   
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return members; 
       
        
}
    
    void addNewCourse(){
          try {
              String query="INSERT INTO courses (course) VALUES (?)";
              pst=con.prepareStatement(query);
              pst.setString(1, addcourse.getEditor().getText());
              pst.execute();
            
              System.out.println("new courses added ");
              popUpSAVE();
          } catch (SQLException ex) {
              Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
          }
          
    }
    
    public final ObservableList<Table>searchTable(ComboBox searchValues){
        
        try {
          
            String query="SELECT * FROM members WHERE '"+searchValues.getEditor().getText()+"' = sname or  '"+searchValues.getEditor().getText()+"' = fname or "
                    + "'"+searchValues.getEditor().getText()+"' = dob or '"+searchValues.getEditor().getText()+"' = contact or '"+searchValues.getEditor().getText()+"' = house or "
                    + "'"+searchValues.getEditor().getText()+"' = email or '"+searchValues.getEditor().getText()+"' = course or "
                    + "'"+searchValues.getEditor().getText()+"' = occupation or '"+searchValues.getEditor().getText()+"' = location or '"+searchValues.getEditor().getText()+"' = date_saved  ";
         System.out.println(searchValues.getEditor().getText());
            pst=con.prepareStatement(query);
            rset=pst.executeQuery();
          
            while(rset.next()) {
                members.add(new Table(
                                rset.getString("id"),
                                rset.getString("sname"),
                                rset.getString("fname"),
                                rset.getString("dob"),
                                rset.getString("contact"),
                                rset.getString("house"),
                                rset.getString("email"),
                                rset.getString("course"),
                                rset.getString("occupation"), 
                                rset.getString("location"),
                                rset.getString("date_saved")
                                                                                   
                        ));
                
                    ///return members; 
            }   
        
        } catch (SQLException ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return members; 
      
        //return null;
}
   void deleteMember(){
       try {
////                  ObservableList<Table>memberSelected,allselected;
////   allselected=table.getItems();
////    memberSelected=table.getSelectionModel().getSelectedItems();    
////    memberSelected.forEach(allselected::remove);
//    //above works but only in table does not communicate tp db
Table t=table.getSelectionModel().getSelectedItem();
String getId=t.id;
System.out.println(getId);
String query="DELETE FROM members where id=?";
//
pst=con.prepareStatement(query);
pst.setString(1, getId);
pst.execute();

 table.getItems().clear();
table.setItems(getMembers());
  popUpDelete();
System.out.println("deleted  ");
          } catch (SQLException ex) {
              Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        }
   }       
      void registerUserForm(){
          
          Label l = new Label("input name");
       vbox.getChildren().clear();
        vbox.getChildren().addAll(back,l, username,password, registerbtn,promtErro);
     switchBack();
       }
      
      //getback to mian page
      void switchBack(){
          back.setOnAction(e->{
            vbox.getChildren().clear();
            stage.setHeight(300);
            stage.setWidth(600);
        vbox.getChildren().addAll(new Label("add new course"), addcourse,hbox);
     
         username.clear();
                confirmpass.clear();
                password.clear();
      });
      
     
      }
      
      void registerUser(Button register){
          try {
              String query="INSERT INTO user_login (name,password) VALUES (?,?)";
              pst=con.prepareStatement(query);
               pst.setString(1, username.getText());
               pst.setString(2, password.getText());
                pst.execute();
                  System.out.println("registered");
                  popUpSAVE();
                username.clear();
                confirmpass.clear();
                password.clear();
          } catch (SQLException ex) {
              System.out.println("username already exist");
              Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
      
   }
          
