package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.util.List;

public class CheckoutPage {


    Page page;

    //Locators

    Locator Checkoutbtn;
    Locator Guestcheckout;
    Locator guestemail;
    Locator guestfirstname;
    Locator guestlastname;
    Locator guestSubmit;
    Locator AlerteEmailguest;
    Locator AlerteFirstnameguest;
    Locator AlerteLastnameguest;
    Locator MSgGuestmode;
    Locator guestsubmit2;
    Locator MSGFacturation;
    Locator country;
    Locator postal;
    Locator RueNum;
    Locator RueNom;
    Locator city;
    Locator state;
    Locator guestsubmit3;
    Locator PaymentRef;
    Locator Bankname;
    Locator AccountName;
    Locator AccountNumber;
    Locator AlertePayment;
    Locator ConfirmPayment;
    Locator MsgPaymentSuccessful;
    Locator Orderconfirmation;
    Locator Creditcardnumber;
    Locator Expirationdate;
    Locator CVV;
    Locator cardholdername;
    Locator MonthlyPayment;
    Locator giftCardnumber;
    Locator validationgiftcard;
    Locator Usermode;
    Locator Emailuser;
    Locator PassUser;
    Locator Submitlogin;
    Locator Confirmconnexion;
    Locator MenuUser;
    Locator Mesfactures;
    Locator latestInvoiceCell;
    Locator PasserAlacaisseUSER;


    // Constructeur : On passe a la page Playwright et on initialise les locators

    public CheckoutPage(Page checkoutpage) {

        this.page = checkoutpage;
        this.Checkoutbtn = checkoutpage.locator("[data-test='proceed-1']");
        this.Guestcheckout = checkoutpage.locator("a.nav-link", new Page.LocatorOptions().setHasText("Continuer en tant qu'invité"));
        this.guestemail = checkoutpage.locator("#guest-email");
        this.guestfirstname = checkoutpage.locator("#guest-first-name");
        this.guestlastname = checkoutpage.locator("#guest-last-name");
        this.guestSubmit = checkoutpage.locator("[data-test='guest-submit']");
        this.AlerteEmailguest = checkoutpage.locator("[data-test='guest-email-error']");
        this.AlerteFirstnameguest = checkoutpage.locator("[data-test='guest-first-name-error']");
        this.AlerteLastnameguest = checkoutpage.locator("[data-test='guest-last-name-error']");

        this.MSgGuestmode = checkoutpage.locator("//*[@id=\"guest-tab\"]/p");
        this.guestsubmit2 = checkoutpage.locator("[data-test='proceed-2-guest']");

        this.MSGFacturation = checkoutpage.locator("[data-test='postcode-lookup-hint']");
        this.country = checkoutpage.locator("[data-test='country']");
        this.postal = checkoutpage.locator("#postal_code");
        this.RueNum = checkoutpage.locator("#house_number");
        this.RueNom = checkoutpage.locator("#street");
        this.city = checkoutpage.locator("#city");
        this.state = checkoutpage.locator("#state");
        this.guestsubmit3 = checkoutpage.locator("[data-test='proceed-3']");
        this.PaymentRef = checkoutpage.locator("#payment-method");
        this.Bankname = checkoutpage.locator("#bank_name");
        this.AccountName = checkoutpage.locator("#account_name");
        this.AccountNumber = checkoutpage.locator("#account_number");
        this.AlertePayment = checkoutpage.locator(".alert-danger");
        this.ConfirmPayment = checkoutpage.locator("[data-test='finish']");

        this.MsgPaymentSuccessful = checkoutpage.locator("[data-test='payment-success-message']");
        this.Orderconfirmation = checkoutpage.locator("#order-confirmation");

        this.Creditcardnumber = checkoutpage.locator("#credit_card_number");
        this.Expirationdate = checkoutpage.locator("#expiration_date");
        this.CVV = checkoutpage.locator("#cvv");
        this.cardholdername = checkoutpage.locator("#card_holder_name");

        this.MonthlyPayment = checkoutpage.locator("#monthly_installments");
        this.giftCardnumber = checkoutpage.locator("#gift_card_number");
        this.validationgiftcard = checkoutpage.locator("#validation_code");
        this.Usermode = checkoutpage.locator("a.nav-link", new Page.LocatorOptions().setHasText("Se connecter"));

        this.Emailuser = checkoutpage.locator("#email");
        this.PassUser = checkoutpage.locator("#password");
        this.Submitlogin = checkoutpage.locator("[data-test='login-submit']");
        this.Confirmconnexion = checkoutpage.locator("//div[contains(@class,'login-form-1')]/p");
        this.MenuUser = checkoutpage.locator("#menu");
        this.Mesfactures = checkoutpage.locator("[data-test='nav-my-invoices']");
        this.latestInvoiceCell = checkoutpage.locator("table tbody tr:first-child td").first();
        this.PasserAlacaisseUSER = checkoutpage.locator("[data-test='proceed-2']");

    }

    public void clickcheckout() {

        Checkoutbtn.click();
    }

    public void Clickguestcheckout() {

        Guestcheckout.click();
    }

    public String getEmail() {

        return guestemail.getAttribute("placeholder");
    }

    public String getFirstname() {

        return guestfirstname.getAttribute("placeholder");
    }

    public String getLastname() {

        return guestlastname.getAttribute("placeholder");
    }


    public boolean VerifGuestsubmit() {
        return guestSubmit.isVisible();
    }

    public void Entreadresse(String adresse) {

        guestemail.fill(adresse);
    }

    public void EnterFirstname(String firstname) {
        guestfirstname.fill(firstname);
    }

    public void EnterLastname(String lastname) {
        guestlastname.fill(lastname);
    }

    public String getAlerteEmailguest() {

        return AlerteEmailguest.textContent();
    }

    public String getFirstnameAlerteguest() {

        return AlerteFirstnameguest.textContent();
    }

    public String getlastnameAlerteguest() {

        return AlerteLastnameguest.textContent();
    }

    public String getMsgGuest() {
        return MSgGuestmode.textContent();
    }

    public void clickGuestsubmit() {

        guestSubmit.click();
    }

    public void clickGuestsubmit2() {

        guestsubmit2.click();
    }

    public String getFacturationMSG() {

        return MSGFacturation.textContent();
    }

    public void SelectCountry(String pays) {

        country.selectOption(pays);
    }

    public void Enterpostalecode(String code) {

        postal.fill(code);
    }

    public void EnterRuenum(String num) {

        RueNum.fill(num);


    }

    public void ClickGuestsubmit3() {

        guestsubmit3.click();
    }

    public String GetPaymentRef() {

        return PaymentRef.textContent();
    }

    public String getRuenom() {

        return RueNom.inputValue();
    }

    public String getCity() {

        return city.inputValue();
    }

    public String getState() {

        return state.inputValue();
    }

    public void SelectPaymentOption(String option) {

        PaymentRef.selectOption(option);
    }

    public void EnterBankName(String Nom_bank) {

        Bankname.fill(Nom_bank);
    }

    public void EnterAccountName(String Nom_compte) {

        AccountName.fill(Nom_compte);
    }

    public void EnterAccountNumber(String Num_compte) {

        AccountNumber.fill(Num_compte);
    }

    public String GetAlertePayment() {

        return AlertePayment.textContent();
    }

    public void ClickConfirmPayment() {

        if (ConfirmPayment.isEnabled()) {
            ConfirmPayment.click();
        } else {
            System.out.println("Le bouton Confirmer est désactivé (disabled). Aucun clic n'a été effectué.");
        }
    }

    public String getPaymentSuccessMSG() {

        return MsgPaymentSuccessful.textContent().trim();
    }

    public String getOrderconfirmationMSG() {

        return Orderconfirmation.textContent().trim();
    }

    public void EnterCreditnumber(String number) {

        Creditcardnumber.fill(number);

    }

    public void EnterExpirationdate(String date) {

        Expirationdate.fill(date);

    }

    public void EnterCVV(String CV) {

        CVV.fill(CV);

    }

    public void Entercardholdername(String name) {

        cardholdername.fill(name);

    }

    public List<String> GetMonthlyPaymentOptions() {

        return MonthlyPayment.locator("option").allInnerTexts();
    }

    public void ClickMonthlyPaymentOptions() {

        MonthlyPayment.click();

    }

    public void selectMonthlyPaymentsoption(String option) {

        MonthlyPayment.selectOption(option);


    }

    public void EnterCardgiftnumber(String number) {

        giftCardnumber.fill(number);
    }

    public void EntercardgiftCode(String code) {

        validationgiftcard.fill(code);
    }

    public void ClickUsermode() {

        Usermode.click();
    }

    public void FillEmailUser(String Email) {

        Emailuser.fill(Email);
    }

    public void FillUserPassword(String Pass) {

        PassUser.fill(Pass);

    }

    public void ClickLogin() {

        Submitlogin.click();
    }

    public String getConfirmationConnexion() {

        return Confirmconnexion.textContent();
    }

    public void ClickMenuUser() {

        MenuUser.click();
    }

    public void Clickmesfactures() {

        Mesfactures.click();
    }

    public String getDernierefacture() {

        return latestInvoiceCell.textContent();

    }

    public void ClickPasserAlacaisseUser() {

        PasserAlacaisseUSER.click();
    }

}
