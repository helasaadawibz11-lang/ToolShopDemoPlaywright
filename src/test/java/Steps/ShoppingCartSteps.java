package Steps;

import Pages.ProductDetailPage;
import Pages.ProductListingPage;
import Pages.RegisterPage;
import Pages.ShoppingCartPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en_old.Ac;
import org.junit.Assert;

public class ShoppingCartSteps {

    Page page;
    ShoppingCartPage shoppingCartPage;
    ProductListingPage listingPage;
    ProductDetailPage detailPage;


    // Initialisation de la page via le Hook active

    @Given("je suis sur la page accueil du site")
    public void jeSuisSurLaPageAccueilDuSite() {
        this.page = Hook.getPage();
        this.shoppingCartPage = new ShoppingCartPage(page);
        this.listingPage = new ProductListingPage(page);
        this.detailPage = new ProductDetailPage(page);


        page.navigate("https://practicesoftwaretesting.com/");
    }

    @And("le catalogue des produits est affiché")
    public void leCatalogueDesProduitsEstAffiché() {
        listingPage.ClickAcceuil();
        page.waitForTimeout(1000);
        System.out.println("vous etes sur la page catalogue produit , vous pouvez pousuivre vos test ");

    }

    @And("je choisi d ajouter le produit Bolt Cutters a mon panier")
    public void jeChoisiDAjouterLeProduitBoltCuttersAMonPanier() {

        detailPage.clickproduct();
        detailPage.clickAddtocart();
        System.out.println("Quantité 1 ajoutée au panier ");
        page.waitForTimeout(1000);

    }

    @When("jaccede a mon panier")
    public void jaccedeAMonPanier() {

        shoppingCartPage.clickCart();
        System.out.println("panier est ouvert ");
        page.waitForTimeout(5000);

    }

    @When("je vide mon panier")
    public void jeVideMonPanier() {





    }
    @And("je vide mon panier du produit {string}")
    public void jeVideMonPanierDuProduit(String produit) {

        shoppingCartPage.removeProductByName(produit);
        System.out.println("le produit trouvé est supprimé , panier vide  ");
    }


    @Then("le tableau produit ne doit pas etre visible")
    public void leTableauProduitNeDoitPasEtreVisible() {

        page.waitForTimeout(1000);
        boolean verif = shoppingCartPage.tableproduitIsvisible();
        Assert.assertFalse("le tableau produit est visible , panier n'est pas vide", verif);
        System.out.println("Apres validation , la table des produit est invisible !");
    }


    @And("le bouton Passer à la caisse ne doit pas être visible")
    public void leBoutonPasserÀLaCaisseNeDoitPasÊtreVisible() {

        boolean verif = shoppingCartPage.checkoutISvisible();
        Assert.assertFalse("le bouton passer a la caisse est visible , panier n'est pas vide", verif);
        System.out.println("Apres validation , le bouton passer a la caisse est invisible !");

    }

    @And("le badge Cart quantity est invisible")
    public void leBadgeCartQuantityEstInvisible() {


        boolean verif = shoppingCartPage.cartbadgeIsvisible();
        Assert.assertFalse("le badge quantité du panier est visible , panier n'est pas vide", verif);
        System.out.println("Apres validation , le badge quantité du panier est invisible !");

    }


    @And("mon panier contient le produit {string}")
    public void monPanierContientLeProduit(String produit) {

        String Actualnom =shoppingCartPage.getnomproduit();
        Assert.assertEquals("le nom produit panier est different", produit, Actualnom);
        System.out.println("Apres validation , le produit " + produit + " est present dans le panier ");

    }

    @Then("le tableau produit affiche {string},{string},{string} et {string} correctement")
    public void leTableauProduitAfficheEtCorrectement(String produit, String Qt, String prixunit, String total) {

        String Actualnom =shoppingCartPage.getnomproduit();
        String ActualQT = shoppingCartPage.getQuantitéproduit();
        String ActualPrixunit = shoppingCartPage.getprixunitproduit();
        String ActualPrixTotal = shoppingCartPage.getprixTotalproduit();

        Assert.assertEquals("le nom produit panier est incorrecte",Actualnom,produit);
        Assert.assertEquals("la Quantité produit panier est incorrecte",ActualQT,Qt);
        Assert.assertEquals("le prix unitaire produit du panier est incorrecte",ActualPrixunit,prixunit);
        Assert.assertEquals("le prix total ligne produit du panier est incorrecte",ActualPrixTotal,total);

        System.out.println("Apres validation des données table produits, les données sont correctes :  Article :" + Actualnom + ", QT : " + ActualQT + ",PrixUnit : " + ActualPrixunit + ", Totalproduit : " + ActualPrixTotal );

    }

    @And("le total panier est correcte")
    public void leTotalPanierEstCorrecte() {

        String QTprod= shoppingCartPage.getQuantitéproduit();
        String Prixunit= shoppingCartPage.getprixunitproduit();
        String Totalpanier= shoppingCartPage.getTotalPanier();
        double TotalpanierActuel = Double.parseDouble(Totalpanier) ;

        double CalculPanier = Double.parseDouble(QTprod)*Double.parseDouble(Prixunit) ;
        Assert.assertEquals("le prix total panier est incorrecte", CalculPanier, TotalpanierActuel, 0.001);
        System.out.println("apres validation , le total panier est correcte " + TotalpanierActuel);

    }

    @And("les boutons Continuer les achats et passer a la caisse sont disponibles")
    public void lesBoutonsContinuerLesAchatsEtPasserALaCaisseSontDisponibles() {

        boolean verif1= shoppingCartPage.checkoutISvisible();
        boolean verif2= shoppingCartPage.Continueisvisible();

        page.waitForTimeout(1000);

        Assert.assertTrue("bouton checkout est invisible ",verif1);
        Assert.assertTrue("bouton Continue shopping est invisible ",verif2);

        System.out.println("Apres validation , les boutons Checkout et Continue shopping sont visibles ");



    }

    @When("je reviens a l acceuil")
    public void jeReviensALAcceuil() {

        shoppingCartPage.clickAcceuil();

    }

    @And("je choisi d ajouter le produit {string}")
    public void jeChoisiDAjouterLeProduit(String produit) {

        shoppingCartPage.selectProductByName(produit);
        page.waitForTimeout(1000);
        shoppingCartPage.clickAddtocart();


    }

    @Then("le tableau affiche les données produit {string},{string},{string} et {string} correctement")
    public void leTableauAfficheLesDonnéesDifferentsProduitsEtCorrectement(String produit, String Qt, String prixunit, String total) {


        String Actualnom =shoppingCartPage.getNomProduitParNom(produit);
        String ActualQT = shoppingCartPage.getQuantiteProduitParNom(produit);
        String ActualPrixunit = shoppingCartPage.getPrixUnitProduitParNom(produit);
        String ActualPrixTotal = shoppingCartPage.getPrixTotalProduitParNom(produit);

        Assert.assertEquals("le nom produit panier est incorrecte",Actualnom,produit);
        Assert.assertEquals("la Quantité produit panier est incorrecte",ActualQT,Qt);
        Assert.assertEquals("le prix unitaire produit du panier est incorrecte",ActualPrixunit,prixunit);
        Assert.assertEquals("le prix total ligne produit du panier est incorrecte",ActualPrixTotal,total);

        System.out.println("Apres validation des données table produits, les données sont correctes :  Article :" + Actualnom + ", QT : " + ActualQT + ",PrixUnit : " + ActualPrixunit + ", Totalproduit : " + ActualPrixTotal );

    }

    @And("le total panier des differents produits est correcte")
    public void leTotalPanierDesDifferentsProduitsEstCorrecte() {

        String totalPanierText = shoppingCartPage.getTotalPanier();
        double totalPanierAffiche = Double.parseDouble(totalPanierText);

        double sommeAttendue = shoppingCartPage.calculerSommeTotaleDesLignes();

        Assert.assertEquals("Le prix total du panier est incorrect", sommeAttendue, totalPanierAffiche, 0.001);

        System.out.println("Après validation, le total général du panier est correct : " + totalPanierAffiche);

    }

    @And("le badge Cart quantity indique {string}")
    public void leBadgeCartQuantityIndique(String QTbadge) {

        String Actualbadge=shoppingCartPage.getbadgeQuantity();
        Assert.assertEquals("quantité badge panier est incorrecte" ,Actualbadge,QTbadge);
        System.out.println("apres validation la quantité du badge panier est correcte = "+ Actualbadge);
    }

    @And("je modifie la quantité du produit {string} a {string}")
    public void jeModifieLaQuantitéDuProduitA(String produit, String QT) {

        shoppingCartPage.EnterQuantity(produit,QT);
        System.out.println("la quantité produit" + produit + " est modifiée à :" +QT);
    }

    @And("je tape entrée")
    public void jeTapeEntrée() {

        page.keyboard().press("Enter");
        page.waitForTimeout(500);

    }


    @Then("un msg de confirmation de modification s affiche {string}")
    public void unMsgDeConfirmationDeModificationSAffiche(String msg) {

        page.waitForTimeout(1000);
        String ActualAlerte= shoppingCartPage.getMsgAlertePanier();

        Assert.assertEquals("Alerte affiché est inadequate" , msg,ActualAlerte);

        System.out.println("Apres validation l'alerte s'affiche correctement : "+ ActualAlerte);


    }

    @And("je clique sur le bouton de suppression pour le produit {string}")
    public void jeCliqueSurLeBoutonDeSuppressionPourLeProduit(String productname ) {

        shoppingCartPage.removeProductByName(productname);
        page.waitForTimeout(2000);



    }

    @Then("l'article {string} disparaît de la liste")
    public void lArticleDisparaîtDeLaListe(String produit) {

        boolean verif = shoppingCartPage.Checkproductvisibility(produit);

        Assert.assertFalse("le produit est encore visible " ,verif);
        System.out.println("apres validation de la table produits , le produit" + produit +"est supprimé ");
    }


    @And("je note les données de la table produits")
    public void jeNoteLesDonnéesDeLaTableProduits() {

        System.out.println("la table contient :\n" + shoppingCartPage.Affichetableproduit());

    }

    @And("je note le total panier")
    public void jeNoteLeTotalPanier() {

       String Total= shoppingCartPage.getTotalPanier();
       System.out.println("le total panier affiché = " +Total);
    }

    @And("je rafraîchis la page du navigateur")
    public void jeRafraîchisLaPageDuNavigateur() {
        page.reload();
        page.waitForTimeout(2000);

    }

    @And("je clique sur le bouton Continuer les achats")
    public void jeCliqueSurLeBoutonContinuerLesAchats() {

        shoppingCartPage.clickContinue();
    }

    @Then("je suis réorienté vers la page d'accueil ou le catalogue de produits")
    public void jeSuisRéorientéVersLaPageDAccueilOuLeCatalogueDeProduits() {

        String Expectedpage="https://practicesoftwaretesting.com/";

        String Actuallink=page.url();
        Assert.assertEquals("redirection page d acceuil failed",Expectedpage, Actuallink);
    }

    @And("la barre quantité produit se met a jour a la valeur minimal {string}")
    public void laBarreQuantitéProduitSeMetAJourALaValeur(String QT) {

        String ActualQT= shoppingCartPage.getQuantitéproduit();

        Assert.assertEquals("QT produit incorrect",QT, ActualQT);
        System.out.println("la quantité se met a jour a "+ ActualQT + "d'ou la mise a zero de la qt produit est impossible ");

    }
}
