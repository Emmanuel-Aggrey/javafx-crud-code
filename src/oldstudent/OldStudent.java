
package oldstudent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class OldStudent {
    Notifications notify;
    Connection con=null;
    PreparedStatement pst=null;
    ResultSet rset=null;
    String url="jdbc:sqlite:oldStudent_db";
Stage stage;
Scene scene;
VBox vbox;
HBox hbox;
Button save,delete,update,back;
  


  OldStudent(){
  
        try {
            
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection(url);
       
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(OldStudent.class.getName()).log(Level.SEVERE, null, ex);
            
            }  
    
}

 void popUpSAVE() {
    notify= Notifications.create()
            .position(Pos.BASELINE_LEFT)
            .darkStyle()
            .title("saving  ....")
            .text("saved successfully")
            .hideAfter(Duration.seconds(6));
         
            notify.showConfirm();
             
             
}
 void popUpDelete() {
    notify= Notifications.create()
            .position(Pos.BASELINE_LEFT)
            .darkStyle()
            .title("deleting user  ....")
            .text("deleted successfully")
            .hideAfter(Duration.seconds(6));
         
            notify.showConfirm();
             
             
}
     void popUpUserNotFound() {
    notify= Notifications.create()
            .position(Pos.BASELINE_LEFT)
            .darkStyle()
            .title("please wait getting password  ....")
            .text("password not found")
            .hideAfter(Duration.seconds(6));
         
            notify.showConfirm();
             
             
}
     
 void popUpName_Password_notfound() {
    notify= Notifications.create()
            .position(Pos.BASELINE_LEFT)
            .darkStyle()
            .title("please wait ....")
            .text("either username or passwrod not correct")
            .hideAfter(Duration.seconds(6));
         
            notify.showConfirm();
             
             
}
     
}
