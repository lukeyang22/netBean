/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import org.openqa.selenium.*;
//import our own classes
import manager.*;
//

/**
 *
 * @author lyang
 */
public class Login extends Base{
    TestManager testmanager = new TestManager();
	private WebDriver driver;  private String webElement="";      
	//Constructor	
        public Login() {
            super();     
        }

     String email="";
    public String getEmail(){
        try{
            email = testManager.setProperty("username");
        }catch(Exception e){};
        return email;
    }

    String ps ="";
    public String getPassword(){
        try{
            ps = testManager.setProperty("password");
        }catch(Exception e){};
        return ps;
    }
       public void Login(){
            clickLink("Login");
            enterText("Login_username",getEmail());
            enterText("Login_password",getPassword());
            clickButton("Login_Login", "Log in");
       }
        
       public void Logoff(){
            clickLink("Logout");
       }
}
