
package oldstudent ;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.textfield.TextFields;


public class Login extends OldStudent{
    String getUserName;
    
    final Button picbtn = new Button();
    final Label welcome = new Label("welcome");
     ChoiceBox choice;
    BorderPane root = new BorderPane();
   
   // String getUserPassword;
  
    Button submit, send;
    TextField name, txt;
    PasswordField passw;
    TextField userTextField, pass; //for Login screen
    Button signin=new Button("Sign in");
    
      void logInScreen() {
         back=new Button("back");
          choice =new ChoiceBox(FXCollections.observableArrayList("admin","user"));
        choice.setPrefSize(200, 20);
    

        
      //  back.setId("button-back");
        //picbtn.setId("button-picbtn");
 
        // back.getStyleClass().add("button-back");
   
       //BorderPane.setAlignment(view, Pos.TOP_CENTER);
        stage = new Stage();
        stage.setResizable(false);
 
 
       
         vbox=new VBox(10);
        vbox.setPadding(new Insets(150, 10, 10, 10));
 
        userTextField = TextFields.createClearableTextField();
        userTextField.setPromptText("user name");
 
        pass = TextFields.createClearablePasswordField();
        pass.setPromptText("password");
 
        Label forgetPass = new Label("forgot password?");
       
 
      //  signin = new Button("Sign in");
 
 
        signin.setPrefSize(500, 20);
        hbox = new HBox(200);
 
        hbox.getChildren().addAll(forgetPass);
 
        //workomg but pic not nice and set the padding in css
        //pic name is login_pic
        //  root.setTop(picbtn);
        
        vbox.getChildren().addAll(welcome,choice, userTextField, pass, signin, new Separator(), hbox, new Separator());
        root.setPadding(new Insets(5, 5, 5, 5));
 
        root.setCenter(vbox);
 
 
        forgetPass.setOnMousePressed(e -> {
            vbox.getChildren().removeAll(userTextField, pass, signin, hbox, welcome);
           forgetPasswordScene();
            userTextField.clear();
            pass.clear();
        });
 
 
        //Login if user press enter
        signin.setOnKeyPressed(v -> {
           if (v.getCode() == KeyCode.ENTER)
              if (choice.getValue().equals("user")){
     userExist();
     }
          
            signin.setOnKeyPressed(e -> {
                if (v.getCode() == KeyCode.ENTER)
              if (choice.getValue().equals("admin")){
    adminExist();
     }
            
       });
       });
        
           //Login if user press enter for admin
       
        
 
    
 
           //Login if user press enter
        signin.setOnKeyPressed(v -> {
           if (v.getCode() == KeyCode.ENTER)
              if (choice.getValue().equals("user")){
    userExist();
     }
            
       });
        
        
        
        //LOGIN IF USER PRESS SIGIN BUTTON
       signin.setOnAction(e -> {
       
         
     if (choice.getValue().equals("user")){
     userExist();
     }
     if(choice.getValue().equals("admin")){
    adminExist();
     }
     else{
     System.out.println("please select user type");
     }
       });
 
 
        scene = new Scene(root, 450, 500);
        scene.getStylesheets().add("login.css");
        stage.setScene(scene);
       
        //scene.getStylesheets().add("logIn.css");
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
      
      void forgetPasswordScene() {
        //    back=new Button("back");
        txt = TextFields.createClearableTextField();//forget password
        send = new Button("send");
        txt.setPromptText("please input name");
        Label l = new Label("input name");
        vbox.getChildren().addAll(l, txt, send);
 
        root.setTop(back);
 
 
        //getting back to main page
        back.setOnAction(e -> {
            vbox.getChildren().removeAll(txt, send, l);
            root.getChildren().remove(back);
            vbox.getChildren().addAll(welcome,userTextField, pass, signin, hbox);
        });
 
        
       
        //GETTING THE USER PASSWORD
   
        send.setOnAction(e -> {
           
     if (choice.getValue().equals("user")){
     getUserPassword();
     }
    else if(choice.getValue().equals("admin")){
    getAdminPassword();
     }
     else{
   
     }
       });
      
 
    }
 
      boolean adminExist() {
        try {
            String query = "SELECT name,password FROM admin_login where name='" + userTextField.getText() + "' AND password='" + pass.getText() + "'";
 
            pst = con.prepareStatement(query);
            rset = pst.executeQuery();
            if (rset.next()) {
              AdminPage adminPage = new AdminPage();//working
                
                stage.hide();
                userTextField.clear();
                pass.clear();
                pst.close();
                con.close();
            } else {
               // popUpUserNotFound();
               System.out.println("not found");
               popUpName_Password_notfound();
            }
 
        } catch (SQLException e) {
        }
 
        return false;
 
    }
      
       boolean userExist() {
        try {
            String query = "SELECT name,password FROM user_login where name='" + userTextField.getText() + "' AND password='" + pass.getText() + "'";
                
            pst = con.prepareStatement(query);
            rset = pst.executeQuery();
            if (rset.next()) {
               Addmember addMember = new Addmember();//working
         addMember.addmember();//working 
                stage.hide();
                userTextField.clear();
                pass.clear();
                pst.close();
                con.close();
            } else {
                 System.out.println("not found");
               popUpName_Password_notfound();
            }
 
        } catch (SQLException e) {
        }
                
        return false;
 
    }
       
       void getAdminPassword(){
       
        try {
         String query="SELECT password from admin_login where name ='"+txt.getText()+"'";
       
            pst=con.prepareStatement(query);
            rset=pst.executeQuery();
            while(rset.next()) {
                getUserName=rset.getString("password");
            //    work here
              //  fillCourse.add(rset.getString("course"));
            //  return true;   
           System.out.println("password is "+getUserName);
           popUpGetpassword() ;
            }   
      System.out.println("not found");
      popUpUserNotFound();
        } catch (SQLException ex) {
            Logger.getLogger(Addmember.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       }
       
        void getUserPassword(){
       
        try {
         String query="SELECT password from user_login where name ='"+txt.getText()+"'";
       
            pst=con.prepareStatement(query);
            rset=pst.executeQuery();
            while(rset.next()) {
                getUserName=rset.getString("password");
            //    work here
              //  fillCourse.add(rset.getString("course"));
            //  return true;   
           System.out.println("password is "+getUserName);
           popUpGetpassword() ;
            }   
          System.out.println("not found");
          popUpUserNotFound();
        } catch (SQLException ex) {
            Logger.getLogger(Addmember.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       }
        void popUpGetpassword() {
    notify= Notifications.create()
            .position(Pos.BASELINE_LEFT)
            .darkStyle()
            .title("gettting passord  ....")
            .text("your password is " +getUserName)
            .hideAfter(Duration.seconds(6));
         
            notify.showConfirm();
             
             
}
}
