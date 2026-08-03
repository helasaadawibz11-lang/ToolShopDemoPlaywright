package Steps;

import Pages.CheckoutPage;
import Pages.ProductDetailPage;
import Pages.ProductListingPage;
import Pages.ShoppingCartPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.PendingException;
import io.cucumber.java.an.E;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import org.junit.Assert;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class CheckoutSteps {


    Page page;
    ShoppingCartPage shoppingCartPage;
    ProductListingPage listingPage;
    ProductDetailPage detailPage;
    CheckoutPage checkoutpage;
    String NumeroFacture;
    String FactureClean;

    // Initialisation de la page via le Hook active

    @Given("je suis sur l accueil du site")
    public void jeSuisSurLAccueilDuSite() {
        this.page = Hook.getPage();
        this.shoppingCartPage = new ShoppingCartPage(page);
        this.listingPage = new ProductListingPage(page);
        this.detailPage = new ProductDetailPage(page);
        this.checkoutpage = new CheckoutPage(page);


        page.navigate("https://practicesoftwaretesting.com/");
    }

    @And("le catalogue produits est affiché")
    public void leCatalogueProduitsEstAffiché() {
        listingPage.ClickAcceuil();
        page.waitForTimeout(1000);
        System.out.println("vous etes sur la page catalogue produit , vous pouvez pousuivre vos test ");

    }

    @And("j ajoute le produit Bolt Cutters a mon panier")
    public void jAjouteLeProduitBoltCuttersAMonPanier() {
        detailPage.clickproduct();
        detailPage.clickAddtocart();
        System.out.println("Quantité 1 ajoutée au panier ");
        page.waitForTimeout(1000);
    }

    @And("jaccede au panier")
    public void jaccedeAuPanier() {
        shoppingCartPage.clickCart();
        System.out.println("panier est ouvert ");
        page.waitForTimeout(5000);

    }

    @And("je tape le bouton passer a la caisse")
    public void jeTapeLeBoutonPasserALaCaisse() {

        checkoutpage.clickcheckout();
        System.out.println("vous etes sur la page checkout ");


    }

    @And("je click continuer en tant que invité")
    public void jeClickContinuerEnTantQueInvité() {

        checkoutpage.Clickguestcheckout();

    }


    @Then("le formulaire saffiche contenant les champs {string} , {string}, {string}")
    public void leFormulaireSafficheContenantLesChamps(String adresse, String prenom, String nom) {

        String champsadresse = checkoutpage.getEmail();
        String champsfirstname = checkoutpage.getFirstname();
        String champsLastname = checkoutpage.getLastname();

        Assert.assertEquals("champs email non conforme", champsadresse, adresse);
        Assert.assertEquals("champs Prenom non conforme", champsfirstname, prenom);
        Assert.assertEquals("champs nom de famille non conforme", champsLastname, nom);

        System.out.println("Apres validations , les champs adresse , prenom et nom sont disponibles ");
    }

    @And("le bouton Continuer en tant qu'invité est disponible")
    public void leBoutonContinuerEnTantQuInvitéEstDisponible() {

        Assert.assertTrue("le bouton Continuer en tant qu'invité est indisponible", checkoutpage.VerifGuestsubmit());
        System.out.println("Apres validation du formulaire , le bouton Continuer en tant qu'invité est disponible ");

    }

    @And("je laisse les champs obligatoires vides")
    public void jeLaisseLesChampsObligatoiresVides() {

        checkoutpage.Entreadresse("");
        checkoutpage.EnterFirstname("");
        checkoutpage.EnterLastname("");

    }


    @Then("un messages derreur saffiche sous le champs Email: {string}")
    public void unMessagesDerreurSaffichentSousLeChampsEmail(String Email) {
        String AlerteEmail = checkoutpage.getAlerteEmailguest();
        Assert.assertEquals("l'alerte Email est inadequate ou non affiché ", Email, AlerteEmail);
        System.out.println("apres validation , l'alerte du champ email est conforme:" + AlerteEmail);
    }

    @And("un messages derreur saffiche sous le champs Prenom : {string}")
    public void unMessagesDerreurSaffichentSousLeChampsPrenom(String Prenom) {
        String AlerteFirstname = checkoutpage.getFirstnameAlerteguest();
        Assert.assertEquals("l'alerte Prenom est inadequate", Prenom, AlerteFirstname);
        System.out.println("apres validation , l'alerte du champ Prenom est conforme :" + AlerteFirstname);

    }

    @And("un messages derreur saffiche sous le champs Nom : {string}")
    public void unMessagesDerreurSaffichentSousLeChampsNom(String Nom) {
        String AlerteLastname = checkoutpage.getlastnameAlerteguest();
        Assert.assertEquals("l'alerte Nom est inadequate", Nom, AlerteLastname);
        System.out.println("apres validation , l'alerte du champ Nom est conforme :" + AlerteLastname);


    }

    @And("le client reste sur l'étape de connexion")
    public void leClientResteSurLÉtapeDeConnexion() {

        String MSG = checkoutpage.getMsgGuest();
        Assert.assertEquals("le client n'est pas sur l'étape de connexion ", "Payer sans créer de compte", MSG);
        System.out.println("Apres validation , le client est encore sur  l'étape de connexion ");
    }

    @And("je click sur le bouton submit du formulaire")
    public void jeClickSurLeBoutonSubmitDuFormulaire() {

        checkoutpage.clickGuestsubmit();
    }

    @And("je saisit l'email {string}")
    public void jeSaisitLEmail(String Email) {

        checkoutpage.Entreadresse(Email);
    }

    @And("je saisit le prenom {string}")
    public void jeSaisitLePrenom(String Prenom) {

        checkoutpage.EnterFirstname(Prenom);
    }

    @And("je saisit le Nom de famille  {string}")
    public void jeSaisitLeNomDeFamille(String Nom) {

        checkoutpage.EnterLastname(Nom);
    }


    @And("je click sur le bouton  Passer à la caisse deux")
    public void jeClickSurLeBoutonPasserÀLaCaisseDeux() {
        checkoutpage.clickGuestsubmit2();
    }

    @Then("le formulaire de facturation saffiche")
    public void leFormulaireDeFacturationSaffiche() {

        String Facturation = checkoutpage.getFacturationMSG();

        Assert.assertEquals("le formulaire de facturation n'est pas affiché ", " Saisissez le pays, le code postal et le numéro de rue. Le reste sera rempli automatiquement. ", Facturation);

        System.out.println("Apres validation , le formulaire de facturation est ouvert ! ");
    }

    @And("je selectionne le nom pays {string}")
    public void jeSelectionneLeNomPays(String pays) {

        checkoutpage.SelectCountry(pays);
    }

    @And("je saisi code postale {string}")
    public void jeSaisiCodePostale(String code) {

        checkoutpage.Enterpostalecode(code);

    }

    @And("je saisi numero de la rue {string}")
    public void jeSaisiNumeroDeLaRue(String num) {

        checkoutpage.EnterRuenum(num);

    }

    @And("je click sur le bouton passer a la caisse trois")
    public void jeClickSurLeBoutonPasserALaCaisseTrois() {

        checkoutpage.ClickGuestsubmit3();
    }

    @Then("letape de paiement saffiche")
    public void letapeDePaiementSaffiche() {

        String ExpectedRef = "Choisissez votre méthode de paiement";
        String ActualRef = checkoutpage.GetPaymentRef();

        Assert.assertTrue("page paiement est inaccessible", ActualRef.contains(ExpectedRef));

        System.out.println("apres validation , la page paiment est ouverte ! ");

    }

    @Then("les champs Rue , ville , etat sont remplis automatiquement")
    public void lesChampsRueVilleEtatSontRemplisAutomatiquement() {

        page.waitForTimeout(3000);

        String Rue = checkoutpage.getRuenom();
        String city = checkoutpage.getCity();
        String State = checkoutpage.getState();

        Assert.assertFalse("champs rue est vide ", Rue.isEmpty());
        Assert.assertFalse("champs city est vide ", city.isEmpty());
        Assert.assertFalse("champs State est vide ", State.isEmpty());

        System.out.println("apres validation , les champs Rue , City et state sont remplies automatiquement :Rue = " + Rue + " , " + "City : " + city + " , " + "State: " + State);


    }

    @And("je selectionne l option  {string}")
    public void jeSelectionneLOption(String option) {

        checkoutpage.SelectPaymentOption(option);
    }


    @And("je tape le nom de la banque {string} , le nom du compte {string} , et le numero du compte {string}")
    public void jeTapeLeNomDeLaBanqueLeNomDuCompteEtLeNumeroDuCompte(String bankname, String accountname, String accountnumb) {

        checkoutpage.EnterBankName(bankname);
        checkoutpage.EnterAccountName(accountname);
        checkoutpage.EnterAccountNumber(accountnumb);

    }

    @And("je click le bouton confirmer")
    public void jeClickLeBoutonConfirmer() {

        checkoutpage.ClickConfirmPayment();

    }

    @Then("un message d erreur adequat s affiche {string}")
    public void unMessageDErreurAdequatSAffiche(String MSG) {

        String ActualMSG = checkoutpage.GetAlertePayment().trim();

        Assert.assertEquals("msg d'erreur inadequat ou inaffiché", MSG, ActualMSG);

        System.out.println("Apres validation l'alerte est adequate ,alerte affiché : " + ActualMSG);


    }

    @Then("un message de confirmation s affiche {string}")
    public void unMessageDeConfirmationSAffiche(String ExpectedMSG) {

        String ActualMSG = checkoutpage.getPaymentSuccessMSG();
        Assert.assertEquals("la confirmation n'est pas adequate ", ExpectedMSG, ActualMSG);
    }

    @Then("redirection vers confirmation de la commande et affichage msg {string}")
    public void redirectionVersConfirmationDeLaCommandeEtAffichageMsg(String ExpectedMSG) {

        String ActualMSG = checkoutpage.getOrderconfirmationMSG();

        Assert.assertTrue("confirmation de la commande indisponible !", ActualMSG.contains(ExpectedMSG));
        System.out.println("Confirmation sur site :" + ActualMSG);
    }

    @And("je note le premier msg de confirmation")
    public void jeNoteLePremierMsgDeConfirmation() {
        String ActualMSG = checkoutpage.getPaymentSuccessMSG();

        System.out.println("premiere confirmation affiché : " + ActualMSG);
    }

    @And("je tape le num de carte {string} , la date d'expiration {string} , et le CVV {string} et le nom du titulaire {string}")
    public void jeTapeLeNumDeCarteLaDateDExpirationEtLeCVVEtLeNomDuTitulaire(String Num_carte, String date, String cvv, String Titulaire) {

        checkoutpage.EnterCreditnumber(Num_carte);
        checkoutpage.EnterExpirationdate(date);
        checkoutpage.EnterCVV(cvv);
        checkoutpage.Entercardholdername(Titulaire);

    }

    @Then("je peux voir les quatres options de paiment mensuels")
    public void jePeuxVoirLesQuatresOptionsDePaimentMensuels() {

        List<String> Liste = checkoutpage.GetMonthlyPaymentOptions();

        Assert.assertEquals("la liste n'est pas conforme ", 5, Liste.size());

        System.out.println("les options de versements possibles sont : " + Liste);

    }

    @And("je click la barre des options versements")
    public void jeClickLaBarreDesOptionsVersements() {

        checkoutpage.ClickMonthlyPaymentOptions();
    }

    @And("je selectionne l option de paiement {string}")
    public void jeSelectionneLOptionDePaiement(String option) {

        checkoutpage.selectMonthlyPaymentsoption(option);


    }

    @And("je tape le num de carte {string} et le code de validation {string}")
    public void jeTapeLeNumDeCarteEtLeCodeDeValidation(String Num_carteCadeau, String code) {

        checkoutpage.EnterCardgiftnumber(Num_carteCadeau);
        checkoutpage.EntercardgiftCode(code);
    }

    @When("je choisi le mode se connecter du checkout")
    public void jeChoisiLeModeSeConnecterDuCheckout() {
        checkoutpage.ClickUsermode();

    }

    @And("je saisi Email {string} et  Mot de passe {string}")
    public void jeSaisiEmailEtMotDePasse(String Email, String Pass) {

        checkoutpage.FillEmailUser(Email);
        checkoutpage.FillUserPassword(Pass);
    }

    @And("je click Connexion")
    public void jeClickConnexion() {

        checkoutpage.ClickLogin();
    }

    @Then("redirection vers la confirmation de l'etape connexion et affichage {string}")
    public void redirectionVersLaConfirmationDeLEtapeConnexionEtAffichage(String msg) {

        String ActualMSg = checkoutpage.getConfirmationConnexion();

        page.waitForTimeout(2000);
        Assert.assertTrue("confirmation indisponible ", ActualMSg.contains(msg));
        System.out.println("Apres validation la confirmation de connexion est affiché : " + ActualMSg);
    }

    @And("je click sur mon nom dans le menu Header")
    public void jeClickSurMonNomDansLeMenuHeader() {
        checkoutpage.ClickMenuUser();
    }

    @And("j ouvre mes factures")
    public void jOuvreMesFactures() {
        checkoutpage.Clickmesfactures();
    }


    @And("je note le numero de facture")
    public void jeNoteLeNumeroDeFacture() {

        NumeroFacture = checkoutpage.getOrderconfirmationMSG();
        FactureClean = NumeroFacture.split("est")[1].replaceAll("[^0-9]", "");

        System.out.println("numero de la facture est  : " + FactureClean);


    }

    @Then("le numero de facture recente est disponible")
    public void leNumeroDeFactureRecenteEstDisponible() {

        page.waitForTimeout(2000);
        String Dernierefacture = checkoutpage.getDernierefacture();
        Assert.assertTrue("la facture recente n'est pas disponible ", Dernierefacture.contains(FactureClean));

        System.out.println("Apres validation de mes factures , la derniere facture est disponible sous l'ordre " + Dernierefacture);
    }

    @And("je click sur le bouton  Passer à la caisse")
    public void jeClickSurLeBoutonPasserÀLaCaisse() {

        checkoutpage.ClickPasserAlacaisseUser();
        page.waitForTimeout(2000);


    }
}
