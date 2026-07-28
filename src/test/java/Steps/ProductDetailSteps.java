package Steps;

import Pages.LoginPage;
import Pages.ProductDetailPage;
import Pages.ProductListingPage;
import Pages.RegisterPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class ProductDetailSteps {

    Page page;
    ProductDetailPage detailPage;
    ProductListingPage listingPage;

    String Comptinitial;
    String Comptfinal;


    // Initialisation de la page via le Hook active

    @Given("je suis sur l'accueil du site ToolShopDemo")
    public void jeSuisSurLAccueilDuSiteToolShopDemo() {
        this.page = Hook.getPage();
        this.listingPage = new ProductListingPage(page);
        this.detailPage = new ProductDetailPage(page);

        page.navigate("https://practicesoftwaretesting.com/");
    }

    @And("le catalogue des produits est visible")
    public void leCatalogueDesProduitsEstVisible() {
        listingPage.ClickAcceuil();
        page.waitForTimeout(1000);
        System.out.println("vous etes sur la page catalogue produit , vous pouvez pousuivre vos test ");

    }


    @And("je choisi d ouvrir la page produit Bolt Cutters")
    public void jeChoisiDOuvrirLaPageProduit() {

        detailPage.clickproduct();
        page.waitForTimeout(1000);

    }

    @When("je fixe la quantité à {string}")
    public void jeFixeLaQuantitéÀ(String Qt) {
        detailPage.sendQuantity(Qt);

    }

    @And("je clique sur le bouton Ajouter au panier")
    public void jeCliqueSurLeBoutonAjouterAuPanier() {

        detailPage.clickAddtocart();
        System.out.println("Quantité ajoutée au panier ");
        page.waitForTimeout(1000);


    }

    @Then("un message de confirmation {string} doit s'afficher")
    public void unMessageDeConfirmationDoitSAfficher(String Alerte) {

        page.waitForTimeout(1000);
        String ActualAlerte = detailPage.getAlerte();
        System.out.println("Alerte affichée sur site: " + ActualAlerte);
        Assert.assertTrue("Alerte non adequate", ActualAlerte.contains(Alerte));

    }


    @When("je note le compteur du panier")
    public void jeNoteLeCompteurDuPanier() {

        page.waitForTimeout(1000);
        Comptinitial = detailPage.getCartQuantity();
        System.out.println("le compteur initial de mon panier est :" + Comptinitial);
    }

    @And("le compteur du panier doit être mis à jour avec la quantité ajouté {string}")
    public void leCompteurDuPanierDoitÊtreMisÀJourAvecLaQuantitéAjouté(String Qt) {

        page.waitForTimeout(1000);
        Comptfinal = detailPage.getCartQuantity();
        int ComptExpected = Integer.parseInt(Comptinitial) + Integer.parseInt(Qt);

        Assert.assertEquals("la mise a jour compteur a echoué ", ComptExpected, Integer.parseInt(Comptfinal));
        System.out.println("apres validation , le compteur panier s'est mis a jour " + Comptfinal);

    }

    @When("je veux diminuer la quantité a {int}")
    public void jeVeuxDiminuerLaQuantitéA(int Qt) {
        detailPage.decreaseQT();
        System.out.println("la quantité est diminuée de 1 a Zero");
    }

    @Then("l afficheur quantité affiche toujours {int}")
    public void lAfficheurQuantitéAfficheToujours(int Qt) {

        String ActualQt = detailPage.getnumberQuantity();
        Assert.assertEquals("diminution a zero effectuée ", Qt, Integer.parseInt(ActualQt));
        System.out.println("Apres diminution de la quantité a Zero, la barre quantité affiche toujours " + ActualQt + "d'ou c'est impossible de fixer la quantité produit a zero");


    }

    @Then("un message derreur {string} doit s'afficher")
    public void unMessageDerreurDoitSAfficher(String Alerte) {
        page.waitForTimeout(1000);
        String ActualAlerte = detailPage.getAlerte();
        System.out.println("Alerte affichée sur site: " + ActualAlerte);
        Assert.assertTrue("Alerte non adequate", ActualAlerte.contains(Alerte));

    }

    @And("l afficheur quantité se met a jours a {int} au lieu de {int}")
    public void lAfficheurQuantitéSeMetAJoursAAuLieuDe(int QT1, int QT2) {

        String ActualQt = detailPage.getnumberQuantity();
        Assert.assertEquals("augmentation a 100 effectuée ", QT1, Integer.parseInt(ActualQt));
        System.out.println("Apres tentative d'ajout de la quantité 100 du produit ,la barre quantité affiche toujours " + ActualQt + "d'ou c'est impossible de fixer la quantité produit a " + QT2);


    }

    @When("je verifie que aucun compte n'est connecté")
    public void jeVerifieQueAucunCompteNEstConnecté() {

        page.waitForTimeout(1000);
        String TexteAccount = detailPage.getusernameAccount();
        String ExpectedTexte = "Se connecter";
        Assert.assertTrue("la session n'est pas deconnectée", TexteAccount.contains(ExpectedTexte));
        System.out.println("Apres validation , aucun compte n'est connectée ");

    }


    @And("je clique sur le bouton Ajouter aux favoris")
    public void jeCliqueSurLeBoutonAjouterAuxFavoris() {
        page.waitForTimeout(1000);
        detailPage.clickAddtoWishlist();
    }


    @When("je clique sur le bouton de majoration de quantité")
    public void jeCliqueSurLeBoutonDeMajorationDeQuantité() {

        detailPage.increaseQT();
        System.out.println("la quantité est augmenté avec le bouton plus ");
    }

    @Then("l afficheur quantité indique {string}")
    public void lAfficheurQuantitéIndique(String qt) {

        String ActualQt = detailPage.getnumberQuantity();
        Assert.assertEquals("augmentation echouée  ", qt, ActualQt);
        System.out.println("Apres augmentation de la quantité avec le bouton plus , la barre quantité affiche " + ActualQt);


    }


    @Then("l afficheur quantité affiche toujours {string} au lieu de {string}")
    public void lAfficheurQuantitéAfficheToujoursAuLieuDe(String Const1, String Const2) {

        String ActualQt = detailPage.getnumberQuantity();
        Assert.assertEquals("saisie de " + Const2 + " reussie  ", Const1, ActualQt);
        System.out.println("Apres la tentative de saisie de " + Const2 + "la barre quantité affiche " + ActualQt);


    }

    @When("je fixe la quantité à la sequence {string}")
    public void jeFixeLaQuantitéÀLaSequence(String msg) {

        detailPage.sendQuantitySequence(msg);

    }
}
