
package oldstudent;import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;


public class Addmember extends OldStudent{
    
      DatePicker dob;
      ComboBox course,loadContact;
  ObservableList fillContact=FXCollections.observableArrayList();
  ObservableList fillCourse=FXCollections.observableArrayList();
  Label getcourse,updatelbl;
    TextField fname,sname,contact,location,house,email,occupation;
    TextField courseStudied;
  String getDate;
 
      Label lbl=new Label("Select course of study");
      void addmember(){
           loadContact=new ComboBox(fillContact);
           back=new Button("back");
  SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
      Date date = new Date();  
      
    getDate=formatter.format(date);
    System.out.println(getDate); 
 
 
    vbox=new VBox(20);
    //load course combo from db
    selectCourse(course);  
      //load contact combo from db
  fillContactCombo(loadContact);
    vbox.setPadding(new Insets(10,10,10,10));
 
    
  fname=TextFields.createClearableTextField();
  fname.setPromptText("Enter your first name");
  sname=TextFields.createClearableTextField();
  sname.setPromptText("Enter your Surname");
  dob=new DatePicker();
  dob.setPromptText("Select date of Birth");
  contact=TextFields.createClearableTextField();
  contact.setPromptText("Enter your Contact Number");
  location=TextFields.createClearableTextField();
  location.setPromptText("Enter your Location");
  house=TextFields.createClearableTextField();
  house.setPromptText("Enter the house you Belong to");
  email=TextFields.createClearableTextField();
  email.setPromptText("Enter Email Address");
  occupation=TextFields.createClearableTextField();
  occupation.setPromptText("Enter your current occupation");
  course=new ComboBox(fillCourse);
  loadContact=new ComboBox(fillContact);
  course.setPromptText("Select the course you studied");
  course.setPrefWidth(300);
  courseStudied=TextFields.createClearableTextField();
  courseStudied.setPromptText("course studied");
  save=new Button("save");
  updatelbl=new Label("click to update");
   hbox=new HBox(10, save,new Separator(Orientation.VERTICAL),updatelbl);
  
   
  //build update stage
 
  loadContact.setEditable(true);
   loadContact.setPrefWidth(300);
   loadContact.setPromptText("search by contact to update");
    TextFields.bindAutoCompletion(loadContact.getEditor(), loadContact.getItems());
         
    update=new Button("update");
   
   updatelbl.setOnMouseClicked(e->{
    buidUpdateForm();
   });
   
   back.setOnAction(e->{
   vbox.getChildren().clear();
   vbox.setSpacing(20);
  vbox.getChildren().addAll(new Label("surname"),sname,fname, dob,contact,house,email,course,occupation,location);
  vbox.getChildren().addAll(new Separator(),hbox);
  
   });
      
   
  vbox.getChildren().addAll(new Label("surname"),sname,fname, dob,contact,house,email,course,occupation,location);
  vbox.getChildren().addAll(new Separator(),hbox);
  
 
   //gets the selected CoboBox item and pass to Label(getcourse)
   getcourse=new Label();
        course.getSelectionModel().selectedItemProperty().addListener((v,o,n)->{
            getcourse.setText((String) course.getValue());
           System.out.println(getcourse.getText());
            });
         
  
 save.setOnAction(e->{SaveDetails();});
  
 update.setOnAction(e->updateResult());
   
    
    //get the selected contact to fill the fields from db
   loadContact.setOnAction(e-> {
   getSelectedContact(loadContact);
      
   });
   
  
 
 
  stage=new Stage();
  stage.initStyle(StageStyle.UTILITY);
  scene=new Scene(vbox, 300, 530);
  scene.getStylesheets().add("allpages.css");
  stage.setScene(scene);
  stage.show();
  }
  
  void SaveDetails(){

  
        try {                           
              String query="INSERT INTO members (sname,fname,dob,contact,house,email,course,occupation,location,date_saved)"
          + " VALUES (?,?,?,?,?,?,?,?,?,?)";
            pst=con.prepareStatement(query);
            pst.setString(1, sname.getText());
            pst.setString(2, fname.getText());
            pst.setString(3, dob.getEditor().getText());
            pst.setString(4, contact.getText());
            pst.setString(5, house.getText());
            pst.setString(6, email.getText());
            pst.setString(7, getcourse.getText());
            pst.setString(8, occupation.getText());
            pst.setString(9, location.getText());
            pst.setString(10,getDate);
            pst.execute();
           clearFields();
        System.out.println("Saved at add member class ");
        } catch (SQLException ex) {
            Logger.getLogger(Addmember.class.getName()).log(Level.SEVERE, null, ex);
        }
  
  }
  
   String selectCourse(ComboBox course){
    
        try {
            String query="SELECT course from courses";
           // fillCourse=FXCollections.observableArrayList(); // collects all courses
            pst=con.prepareStatement(query);
            rset=pst.executeQuery();
            while(rset.next()) {
                fillCourse.add(rset.getString("course"));
            //  return true;   
           
            }   
        
        } catch (SQLException ex) {
            Logger.getLogger(Addmember.class.getName()).log(Level.SEVERE, null, ex);
        }
                  return null;  
  }   
   

   void buidUpdateForm(){
   
      vbox.getChildren().clear();
      Label snamel,fnamel,contactl,emaill,occupationl,locationl;
      snamel=new Label("surname");
      fnamel=new Label("first name");
      contactl=new Label("contact");
      emaill=new Label("email");
      occupationl=new Label("occupation");
      locationl=new Label("location");
    vbox.setSpacing(6);
        vbox.getChildren().addAll(loadContact,snamel,sname,fnamel,fname,contactl,contact,emaill,email,occupationl,occupation,locationl,location);
        vbox.getChildren().addAll(new Separator(),new HBox(10,update,back));
        clearFields();
   }
   
   
   void updateResult(){
          try {
              String query="UPDATE members SET sname=?, fname=?,"
                      + "contact=?,email=?,course=?,occupation=?,location=?"
                      + "WHERE contact='"+ loadContact.getEditor().getText()+ "'";
              pst=con.prepareStatement(query);
               pst.setString(1, sname.getText());
            pst.setString(2, fname.getText());
            pst.setString(3, contact.getText());
            pst.setString(4, email.getText());
            pst.setString(5, getcourse.getText());
            pst.setString(6, occupation.getText());
            pst.setString(7, location.getText());
           
             pst.execute();
             clearFields();
          
            System.out.println("updated");
          } catch (SQLException ex) {
              Logger.getLogger(Addmember.class.getName()).log(Level.SEVERE, null, ex);
          }
   
   }
   
   String fillContactCombo(ComboBox contacts){
         try {
            
              fillContact=FXCollections.observableArrayList();
            String query="SELECT contact from members";
           // fillCourse=FXCollections.observableArrayList(); // collects all courses
            pst=con.prepareStatement(query);
            rset=pst.executeQuery();
            while(rset.next()) {
                fillContact.add(rset.getString("contact"));
            //  return true;   
            
            }   
        
        } catch (SQLException ex) {
            Logger.getLogger(Addmember.class.getName()).log(Level.SEVERE, null, ex);
        }
                  return null;  
   
   }
   void getSelectedContact(ComboBox getContact) {
     
        try {
            String query="SELECT sname,fname,contact,email,occupation,location FROM members WHERE contact =?";
            pst=con.prepareStatement(query);
            pst.setString(1,(String)getContact.getSelectionModel().getSelectedItem());
            rset=pst.executeQuery();
            //sname,fname, dob,contact,house,email,courseStusied,occupation,location
            while(rset.next()) {
               
                sname.setText(rset.getString("sname"));
                fname .setText(rset.getString("fname"));
                contact .setText(rset.getString("contact"));
                email.setText(rset.getString("email"));//use to replace dateTimePicket
                occupation.setText(rset.getString("occupation"));//use to replace dateTimePicket
                location.setText(rset.getString("location"));
               
            }
        } catch (SQLException e) {
         
            
        }
   
  }  
   void clearFields(){
   //fname,sname,contact,location,house,email,occupation,courseStusied;
 fname.clear();
 sname.clear();
 contact.clear();
 location.clear();
 house.clear();
 email.clear();
 occupation.clear();
 courseStudied.clear();
 dob.getEditor().clear();
 course.getEditor().clear();
 loadContact.getEditor().clear();
   }

}//end of main class