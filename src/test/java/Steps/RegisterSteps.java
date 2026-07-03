package Steps;

import Pages.RegisterPage;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Page;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.awt.image.PixelGrabber;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RegisterSteps {

    Page page;
    RegisterPage registerPage;


    // Initialisation de la page via le Hook active
    @Given("je suis sur le site ToolShopDemo")
    public void jeSuisSurLeSiteToolShopDemo() {

        this.page = Hook.getPage();
        this.registerPage = new RegisterPage(page);
        registerPage.OuvrirleSite();

    }


    @When("j accede au formulaire  de connexion")
    public void jAccedeAuFormulaireDeConnexion() {
        registerPage.clickerSeConnecter();

    }

    @And("je choisi de creer un compte")
    public void jeChoisiDeCreerUnCompte() {
        registerPage.ClickRegisterLink();
        System.out.println("Acces au formulaire d'inscription en cours ...");
    }


    @And("je remplis le champs prenom avec {string}")
    public void jeRemplisLeChampsPrenomAvec(String inputFirstname) {
        registerPage.SaisirFirstname(inputFirstname);
    }


    @And("je remplis le champs nom avec {string}")
    public void jeRemplisLeChampsNomAvec(String inputlast) {
        registerPage.SaisirLastname(inputlast);
    }


    @And("je remplis la date de naissance avec {string}")
    public void jeRemplisLaDateDeNaissanceAvec(String inputBirthdate) {
        registerPage.SaisirBirthDate(inputBirthdate);
    }


    @And("je selectionne le pays {string}")
    public void jeSelectionneLePays(String Country) {
        registerPage.SelectCountry(Country);
    }

    @And("je remplis le code postal {string}")
    public void jeRemplisLeCodePostal(String codepostal) {
        registerPage.SaisirCodePostal(codepostal);
    }

    @And("je remplis le numero de la rue {string}")
    public void jeRemplisLeNumeroDeLaRue(String numStreet) {
        registerPage.SaisirStreetNum(numStreet);
    }


    @And("je remplis le champs telephone avec {string}")
    public void jeRemplisLeChampsTelephoneAvec(String Tel) {
        registerPage.SaisirTel(Tel);
    }

    @And("je remplis le champs adresse email avec {string}")
    public void jeRemplisLeChampsAdresseEmailAvec(String email) {
        registerPage.SaisirEmail(email);
    }

    @And("je saisi un mot de passe conforme {string}")
    public void jeSaisiUnMotDePasseConforme(String pass) {
        registerPage.SaisirPAssword(pass);
    }

    @And("je click sur s enregistrer")
    public void jeClickSurSEnregistrer() {
        registerPage.ClickRegister();
    }

    @Then("redirection vers la page Login")
    public void redirectionVersLaPageLogin() {

        // verification de l'URL
        assertThat(page).hasURL("https://practicesoftwaretesting.com/auth/login");
        System.out.println("apres validation de l'URL , redirection a la page login reussie !");
    }


    @And("Connexion reussi avec les données email {string} mot de passe {string}")
    public void connexionReussiAvecLesDonnéesEmailMotDePasse(String email, String pass) {

        System.out.println("tentative de connexion avec les données du comptes crée ...");
        registerPage.SaisirEmailLogin(email);
        registerPage.SaisirPAsswordLogin(pass);
        registerPage.ClickLogin();

        String Title = registerPage.GetTitleAccount();
        String ExpectedTitle = "Mon compte";
        // Utilisation de l'assertion de base JUnit pour les chaînes de caractères
        org.junit.Assert.assertEquals("Le titre de la page compte ne correspond pas !", ExpectedTitle, Title);
        System.out.println("apres validation , l'inscription est reussi , redirection vers mon compte effectuée !");


    }

    @Then("Echec de la redirection vers la page login")
    public void echecDeLaRedirectionVersLaPageLogin() {
        // On attend 2 secondes pour s'assurer que le site n'a pas bougé
        page.waitForTimeout(2000);
        // verification de l'URL
        assertThat(page).hasURL("https://practicesoftwaretesting.com/auth/register");
        System.out.println("apres validation de l'URL , Echec de redirection vers la page login  !");

    }

    @And("Affichage alerte {string}")
    public void affichageAlerte(String Expectedalerte) {

       String ActualAlerte= registerPage.GetAlerteEmail();
       System.out.println("Alerte affiché :"+ActualAlerte);
        org.junit.Assert.assertEquals("l'alerte n'est pas affiché ou ne correspond pas !", Expectedalerte, ActualAlerte);

        System.out.println("Apres validation , l'alerte est bien affiché : "+ActualAlerte);
    }

    @And("Affichage alerte adequate {string}")
    public void affichageAlerteAdequate(String MSGERREUR) {
        // L'assertion native vérifie directement si le bloc contenant ce texte exact est visible
        assertThat(registerPage.getAlerteParTexte(MSGERREUR)).isVisible();
        System.out.println("Apres validation , l'alerte est bien adequate");





    }

    @And("je note le format du password")
    public void jeNoteLeFormatDuPassword() {

        String attributePardefaut= registerPage.GetFormatPAssword();
        System.out.println("le format par defaut du password est : " + attributePardefaut + " du coup il n'est pas visible ");

    }

    @And("je clique sur activer le eye button password")
    public void jeCliqueSurActiverLeEyeButtonPassword() {

        registerPage.ClickEyeButtonPassword();
        System.out.println("Activation de la visualisation Password est effectuée ! ");

    }

    @Then("le format du password change et je peux visualiser le texte password")
    public void leFormatDuPasswordChangeEtJePeuxVisualiserLeTextePassword() {

        String attributePardefaut= registerPage.GetFormatPAssword();
        System.out.println("le format actuel du password est : " + attributePardefaut + " du coup le password est visible ");
    }

    @Then("Le nom de rue la ville et la région sont remplies automatiquement")
    public void leNomDeRueLaVilleEtLaRégionSontRempliesAutomatiquement() {

        page.waitForTimeout(2000);

        boolean NomRue=registerPage.IsStreetnamefilled() ;
        boolean city = registerPage.IsCitynamefilled();
        boolean state= registerPage.IsStatenameFilled();

        // Vérification que chaque condition est vraie
        Assert.assertTrue("Le nom de la rue n'est pas rempli !", NomRue);
        Assert.assertTrue("La ville n'est pas remplie !", city);
        Assert.assertTrue("La région n'est pas remplie !", state);

    }
}
