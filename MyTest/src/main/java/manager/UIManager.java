/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package manager;
import webelement.*;
/**
 *
 * @author luluk
 */
public class UIManager {
    String webelement="";
       public String FindElement(String Name){
         if(Name.startsWith("Login_"))
                {Login ui= new Login(); webelement = ui.UI(Name);}
         if(Name.startsWith("BK_"))
                {Booking ui= new Booking(); webelement =ui.UI(Name);}
         if(Name.startsWith("Golf_"))
                {Golf ui= new Golf(); webelement =ui.UI(Name);}

         return webelement;
    }
}
