package Steps;

import Pages.LoginPage;
import Pages.RegisterPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.MatcherAssert.assertThat;

public class LoginSteps {


    Page page ;
    LoginPage loginPage ;
    RegisterPage registerPage;



    // Initialisation de la page via le Hook active

    @Given("je connecte sur le site ToolShopDemo")
    public void jeConnecteSurLeSiteToolShopDemo() {
        this.page = Hook.getPage();
        this.loginPage = new LoginPage(page);
        this.registerPage=new RegisterPage(page);
        loginPage.OuvrirleSite();
    }

    @When("j ouvre le formulaire  de connexion")
    public void jOuvreLeFormulaireDeConnexion() {

        loginPage.ClickSeconnecter();
    }


    @And("je saisi adresse email {string}")
    public void jeSaisiAdresseEmail(String email) {
       loginPage.saisirEmail(email);
    }

    @And("je saisi Password {string}")
    public void jeSaisiPassword(String pass) {
        loginPage.saisirPassword(pass);

    }

    @And("je click sur login")
    public void jeClickSurLogin() {
        loginPage.ClickLogin();
    }

    @Then("Redirection vers mon tableau de bord")
    public void redirectionVersMonTableauDeBord() {


        //verifier redirection reussie par URL
        page.waitForTimeout(2000);
        PlaywrightAssertions.assertThat(page).hasURL("https://practicesoftwaretesting.com/account");

        PlaywrightAssertions.assertThat(loginPage.GetDashboardAccountName()).hasText("try me");



        System.out.println("Apres validation de l'URL et du nom du compte , redirection vers mon compte reussie ! on peut voir le nom du compte adequat  ");
    }

    @Then("Echec de connexion et affichage Alerte adequate {string}")
    public void echecDeConnexionEtAffichageAlerteAdequate(String Alerte) {

        com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat(loginPage.getAlerteParTexte(Alerte)).isVisible();
        System.out.println("Apres validation , l'alerte est bien adequate");


    }
}
