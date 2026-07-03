package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class LoginPage {

    Page Loginpage ;

    //Locators

    Locator SeConnecter;
    Locator LoginEmail;
    Locator LoginPassword;
    Locator LoginButton;
    Locator DashboardAccountName ;




    // Constructeur : On passe a la page Playwright et on initialise les locators

    public LoginPage(Page Loginpage) {

        this.Loginpage = Loginpage;

        this.SeConnecter = Loginpage.locator("#navbarSupportedContent > ul > li:nth-child(4) > a");
        this.LoginEmail = Loginpage.locator("#email");
        this.LoginPassword = Loginpage.locator("#password");
        this.LoginButton = Loginpage.locator("body > app-root > div.container > app-login > div > div > div > form > div.input-group.mb-3 > input");
        this.DashboardAccountName=Loginpage.locator("#menu");

    }

    public void OuvrirleSite() {

        Loginpage.navigate("https://practicesoftwaretesting.com/");
    }
    public void ClickSeconnecter(){

        SeConnecter.click();
    }

    public void saisirEmail(String Email ){

        LoginEmail.clear();
        LoginEmail.fill(Email);
    }
    public void saisirPassword(String pass ){

        LoginPassword.clear();
        LoginPassword.fill(pass);
    }

    public void ClickLogin(){

        LoginButton.click();
    }

    public Locator GetDashboardAccountName(){

        return this.DashboardAccountName;
    }

    public Locator getAlerteParTexte(String message) {
        return Loginpage.getByText(message);
    }

}
