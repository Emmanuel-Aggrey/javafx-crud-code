
package oldstudent;

import javafx.application.Application;
import javafx.stage.Stage;


public class mainPage extends Application{
//OldStudent d =new OldStudent();
    @Override
    public void start(Stage primaryStage) throws Exception {
    //  Addmember addMember = new Addmember();//working
        // addMember.addmember();//working 
      //  AdminPage adminPage = new AdminPage();//working
      
      Login login=new Login();
      login.logInScreen();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
