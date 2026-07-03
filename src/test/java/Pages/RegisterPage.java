package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class RegisterPage {

    Page RegisterPage;

    //Locators
    Locator SeConnecter;
    Locator LoginEmail;
    Locator LoginPassword;
    Locator LoginButton;
    Locator RegisterLink;
    Locator Firstname;
    Locator Lastname;
    Locator BirthDate;
    Locator Country;
    Locator PostalCode;
    Locator StreetNumber;
    Locator Streetname;
    Locator City;
    Locator State;
    Locator Tel;
    Locator Email;
    Locator Password;
    Locator RegisterButton;
    Locator SuccessfulLogin;
    Locator AlerteEmail ;
    Locator AlerteDanger ;
    Locator EyePasswordRegister ;


    // Constructeur : On passe a la page Playwright et on initialise les locators

    public RegisterPage(Page RegisterPage) {

        this.RegisterPage = RegisterPage;

        this.SeConnecter = RegisterPage.locator("#navbarSupportedContent > ul > li:nth-child(4) > a");
        this.LoginEmail = RegisterPage.locator("#email");
        this.LoginPassword = RegisterPage.locator("#password");
        this.LoginButton = RegisterPage.locator("body > app-root > div.container > app-login > div > div > div > form > div.input-group.mb-3 > input");
        this.RegisterLink = RegisterPage.locator("body > app-root > div.container > app-login > div > div > div > div.input-group.mb-3 > p > a:nth-child(1)");
        this.Firstname = RegisterPage.locator("#first_name");
        this.Lastname = RegisterPage.locator("#last_name");
        this.BirthDate = RegisterPage.locator("#dob");
        this.Country = RegisterPage.locator("#country");
        this.PostalCode = RegisterPage.locator("#postal_code");
        this.StreetNumber = RegisterPage.locator("#house_number");
        this.Streetname = RegisterPage.locator("#street");
        this.City = RegisterPage.locator("#city");
        this.State = RegisterPage.locator("#state");
        this.Tel = RegisterPage.locator("#phone");
        this.Email = RegisterPage.locator("#email");
        this.Password = RegisterPage.locator("#password");
        this.RegisterButton = RegisterPage.locator("body > app-root > div.container > app-register > div > div > div > form > div > button");
        this.SuccessfulLogin = RegisterPage.locator("body > app-root > div.container > app-overview > h1");
        this.AlerteEmail=RegisterPage.locator("body > app-root > div.container > app-register > div > div > div > form > div > div.alert.alert-danger > div > div");
        this.AlerteDanger = RegisterPage.locator("[class='alert alert-danger']");
        this.EyePasswordRegister=RegisterPage.locator("body > app-root > div.container > app-register > div > div > div > form > div > div:nth-child(13) > app-password-input > div > div > button");

    }

    public void OuvrirleSite() {

        RegisterPage.navigate("https://practicesoftwaretesting.com/");
    }

    public void clickerSeConnecter() {
        SeConnecter.click();

    }

    public void ClickRegisterLink() {

        RegisterLink.click();
    }

    public void SaisirFirstname(String Firstnamedata) {

        Firstname.clear();
        Firstname.fill(Firstnamedata);

    }

    public void SaisirLastname(String Lastnamedata) {

        Lastname.clear();
        Lastname.fill(Lastnamedata);

    }

    public void SaisirBirthDate(String birthdata) {
        BirthDate.clear();
        BirthDate.fill(birthdata);
    }

    public void SelectCountry(String pays) {

        Country.selectOption(pays);
    }

    public void SaisirCodePostal(String codeP) {

        PostalCode.clear();
        PostalCode.fill(codeP);
    }

    public void SaisirStreetNum(String Streetdata) {

        StreetNumber.clear();
        StreetNumber.fill(Streetdata);
    }

    public Boolean IsStreetnamefilled() {

        boolean result = false;
        String streetValue = Streetname.inputValue();

        if (streetValue != null && !streetValue.isEmpty()) {
            result = true;
        }
        return result;
    }

    public Boolean IsCitynamefilled() {

        boolean result = false;
        String citynamevalue = City.inputValue();

        if (citynamevalue != null && !citynamevalue.isEmpty()) {
            result = true;
        }
        return result;
    }

    public Boolean IsStatenameFilled() {

        boolean result = false;
        String Statevalue = State.inputValue();

        if (Statevalue != null && !Statevalue.isEmpty()) {
            result = true;
        }
        return result;
    }

    public void SaisirTel(String Teldata) {

        Tel.clear();
        Tel.fill(Teldata);
    }

    public void SaisirPAssword(String pass) {

        Password.clear();
        Password.fill(pass);
    }

    public void SaisirEmail(String email) {

        Email.clear();
        Email.fill(email);
    }

    public void ClickRegister() {

        RegisterButton.click();
    }

    public void SaisirEmailLogin(String email) {

        LoginEmail.clear();
        LoginEmail.fill(email);

    }

    public void SaisirPAsswordLogin(String pass) {

        LoginPassword.clear();
        LoginPassword.fill(pass);
    }

    public void ClickLogin() {

        LoginButton.click();
    }

    public String GetTitleAccount() {

        return SuccessfulLogin.textContent();

    }

    public String GetAlerteEmail(){
        return  AlerteEmail.textContent();


    }

    public Locator getAlerteParTexte(String message) {
        return RegisterPage.getByText(message);
    }

    public String GetFormatPAssword(){

        return Password.getAttribute("type");
    }

    public void  ClickEyeButtonPassword (){

        EyePasswordRegister.click();


    }

}
