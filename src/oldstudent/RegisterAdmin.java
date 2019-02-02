
package oldstudent;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;


public class RegisterAdmin extends Application{
    OldStudent o =new OldStudent();
    TextField username,password;
    Button register;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
         adminPage();
    }
    
    void adminPage(){
    register=new Button("register");
    username=TextFields.createClearableTextField();
    password=TextFields.createClearablePasswordField();
    password.setPromptText("enter password");
    
    o.vbox=new VBox(20,new Label("username"), username,new Label("password"),password,register);
    o.vbox.setPadding(new Insets(150, 10, 10, 10));
    
      register.setOnAction(e->{
       if (username.getText().isEmpty() || password.getText().isEmpty()){ 
   }
       else{ registerAdmin(register);}
      });
  
  
    
    o.stage=new Stage();
   o.stage.initStyle(StageStyle.UTILITY);
   
   o.stage.setResizable(true);
    o.scene=new Scene(o.vbox,300,400);
    o. scene.getStylesheets().add("login.css");
    o.stage.setScene(o.scene);
    o.stage.show();
    }
      void registerAdmin(Button register){
          try {
              String query="INSERT INTO admin_login (name,password) VALUES (?,?)";
              o. pst= o.con.prepareStatement(query);
               o.pst.setString(1, username.getText());
                o.pst.setString(2, password.getText());
                 o.pst.execute();
                  System.out.println("registered admin");
                  o.popUpSAVE();
                username.clear();
                password.clear();
          } catch (SQLException ex) {
              System.out.println("username already exist");
              Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
}
