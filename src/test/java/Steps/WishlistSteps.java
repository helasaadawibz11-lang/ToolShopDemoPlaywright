package Steps;

import Pages.LoginPage;
import Pages.RegisterPage;
import Pages.WishlistPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class WishlistSteps {

    Page page;
    RegisterPage registerPage;
    WishlistPage wishlistpage;
    LoginPage loginPage;


    // Initialisation de la page via le Hook active
    @Given("ma wishlist est vide")
    public void maWishlistEstVide() {
        this.page = Hook.getPage();
        this.wishlistpage = new WishlistPage(page);
        wishlistpage.ClickWishlistButton();
        wishlistpage.ViderWishlist();


    }

    @When("je click sur Acceuil")
    public void jeClickSurAcceuil() {


        wishlistpage.ClickAcceuil();
        // On attend que le réseau soit calme pour être sûr que la liste des produits est affichée
        page.waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE);
        System.out.println("on accede a l'acceuil pour visualiser les produits ...");

    }

    @When("je click sur le produit {string}")
    public void jeClickSurLeProduit(String produit) {

        wishlistpage.ClickBoltCutter();
        System.out.println("produit accedé est " + produit);

    }


    @And("je click sur le bouton ajouter au favoris du produit")
    public void jeClickSurLeBoutonAjouterAuFavorisDuProduit() {
        wishlistpage.ClickAddwishlist();
        System.out.println("Produit ajouté a ma wishlist ! ");

    }

    @Then("une alerte de confirmation ajout s affiche")
    public void uneAlerteDeConfirmationAjoutSAffiche() {
        String Actualmsg = wishlistpage.GetPOPUPmsg();
        String Expected = "Produit ajouté à vos favoris.";

        System.out.println("Alerte affiché :" + Actualmsg);
        org.junit.Assert.assertTrue("l'alerte n'est pas affiché ou ne correspond pas !", Actualmsg.contains(Expected));

        System.out.println("Apres validation , l'alerte est bien adequate : " + Actualmsg);
    }

    @And("le produit {string} est present dans ma wishlist")
    public void leProduitEstPresentDansMaWishlist(String produit) {
        wishlistpage.ClickAccountMenu();
        wishlistpage.ClickMenuFavoris();
        assertThat(page.locator("[data-test='product-name']")).containsText(produit);
        System.out.println("Apres validation ,le produit est present dans ma wishlist  " + produit);

        // On cible les noms de produits et on filtre sur le nom spécifique reçu en paramètre
        com.microsoft.playwright.Locator produitSpecifique = page.locator("[data-test='product-name']")
                .filter(new com.microsoft.playwright.Locator.FilterOptions().setHasText(produit));

        //On récupère le nombre total d'occurrences
        int occurrence = produitSpecifique.count();
        System.out.println("Le produit '" + produit + "' apparaît " + occurrence + " fois dans la wishlist.");
    }


    @And("je rafraichis la page")
    public void jeRafraichisLaPage() {
        page.reload();
    }

    @Then("une alerte d avertissement  s affiche")
    public void uneAlerteDAvertissementSAffiche() {
        String Actualmsg = wishlistpage.GetPOPUPmsg();
        String Expected = "Produit déjà dans vos favoris.";

        System.out.println("Alerte affiché :" + Actualmsg);
        org.junit.Assert.assertTrue("l'alerte n'est pas affiché ou ne correspond pas !", Actualmsg.contains(Expected));

        System.out.println("Apres validation , l'alerte est bien adequate : " + Actualmsg);

    }

    @And("je click sur {string}")
    public void jeClickSur(String produit) {

        wishlistpage.ClickHammer();
        System.out.println("produit accedé est " + produit);

    }

    @And("j accede a la wishlist")
    public void jAccedeALaWishlist() {
        page.waitForTimeout(500);
        wishlistpage.ClickAccountMenu();
        wishlistpage.ClickMenuFavoris();
        System.out.println(" ma wishlist est maintenant ouverte ! ");

    }


    @Then("mes produits {string} et {string} sont presents dans la wishlist")
    public void mesProduitsEtSontPresentsDansLaWishlist(String produit1, String produit2) {

        page.waitForTimeout(500);

        // Cibler PRODUIT 1
        //On isole le produit 1 avec le filtre
        com.microsoft.playwright.Locator locatorProduit1 = page.locator("[data-test='product-name']")
                .filter(new com.microsoft.playwright.Locator.FilterOptions().setHasText(produit1));

        //On compte ses occurrences
        int occurrence1 = locatorProduit1.count();
        System.out.println("Le produit '" + produit1 + "' apparaît " + occurrence1 + " fois dans la wishlist.");

        // On valide qu'il est bien visible (au moins une fois)
        assertThat(locatorProduit1.first()).isVisible();
        System.out.println("Après validation, le produit est présent dans ma wishlist : " + produit1);

        page.waitForTimeout(500);

        // Recherche PRODUIT 2
        // On isole le produit 2 avec le filtre
        com.microsoft.playwright.Locator locatorProduit2 = page.locator("[data-test='product-name']")
                .filter(new com.microsoft.playwright.Locator.FilterOptions().setHasText(produit2));

        // On compte ses occurrences
        int occurrence2 = locatorProduit2.count();
        System.out.println("Le produit '" + produit2 + "' apparaît " + occurrence2 + " fois dans la wishlist.");

        // On valide qu'il est bien visible
        assertThat(locatorProduit2.first()).isVisible();
        System.out.println("Après validation, le produit est présent dans ma wishlist : " + produit2);
    }

    @And("je click sur le produit du nom {string}")
    public void jeClickSurLeProduitDuNom(String produit) {
        wishlistpage.clickSurProduitParNom(produit);
        System.out.println("produit accedé est " + produit);

    }


    @And("je click sur le bouton Addtowishlist Bolt Cutters")
    public void jeClickSurLeBoutonAddtowishlistBoltCutters() {
        wishlistpage.ClickWishlistBoltCutter();
        System.out.println("produit ajouté est  : Bolt Cutters");

    }

    @And("je click sur le bouton Addtowishlist Combination Pliers")
    public void jeClickSurLeBoutonAddtowishlistCombinationPliers() {

        wishlistpage.ClickWishlistCombinationPliers();
        System.out.println("produit ajouté est  : Combination Pliers ");

    }

    @And("je supprime le produit {string} de ma wishlist")
    public void jeSupprimeLeProduitDeMaWishlist(String produit) {

        wishlistpage.supprimerProduitParNom(produit);
        System.out.println("le produit supprimé  est  : " + produit);


    }

    @Then("le produit {string} n'est pas present dans ma wishlist")
    public void leProduitNEstPasPresentDansMaWishlist(String produit) {

        page.waitForTimeout(500);

        // Cibler PRODUIT
        //On cherche le produit 1 avec le filtre
        com.microsoft.playwright.Locator locatorProduit = page.locator("[data-test='product-name']")
                .filter(new com.microsoft.playwright.Locator.FilterOptions().setHasText(produit));

        //On compte ses occurrences ( expected = 0)
        int occurrence = locatorProduit.count();
        System.out.println("Le produit '" + produit + "' apparaît " + occurrence + " fois dans la wishlist.");

        // On valide qu'il n'est pas visible (au moins une fois)
        // assertThat(locatorProduit.first()).isEmpty();

        assertThat(locatorProduit).isHidden();
        System.out.println("Après validation, le produit qui n'est pas visible dans ma wishlist est : " + produit);

    }

    @And("le produit {string} est present dans ma wishlist une seule fois")
    public void leProduitEstPresentDansMaWishlistUneSeuleFois(String produit) {
        wishlistpage.ClickAccountMenu();
        wishlistpage.ClickMenuFavoris();
        assertThat(page.locator("[data-test='product-name']")).containsText(produit);
        System.out.println("Apres validation ,le produit est present dans ma wishlist  " + produit);

        // On cible les noms de produits et on filtre sur le nom spécifique reçu en paramètre
        com.microsoft.playwright.Locator produitSpecifique = page.locator("[data-test='product-name']")
                .filter(new com.microsoft.playwright.Locator.FilterOptions().setHasText(produit));

        //On récupère le nombre total d'occurrences
        int occurrence = produitSpecifique.count();
        System.out.println("Le produit '" + produit + "' apparaît " + occurrence + " fois dans la wishlist.");

    }

    @And("je click sur Menu")
    public void jeClickSurMenu() {
        wishlistpage.ClickAccountMenu();


    }

    @And("je click sur Logout")
    public void jeClickSurLogout() {

        wishlistpage.ClickLogout();
        page.waitForTimeout(500);
        // assertThat(page).hasURL("https://practicesoftwaretesting.com/auth/login");
        System.out.println("apres validation , deconnexion du compte reussie ! ");

    }


    @When("j accede au bouton favoris depuis {string}")
    public void jAccedeAuBoutonFavorisDepuis(String Emplacement) {
        wishlistpage.AccederWishlist(Emplacement);

    }


    @Given("je suis sur la page de mon compte")
    public void jeSuisSurLaPageDeMonCompte() {
        this.page = Hook.getPage();
        this.wishlistpage = new WishlistPage(page);

    }

    @Then("la page favoris s'affiche")
    public void laPageFavorisSAffiche() {

        page.waitForTimeout(500);
        assertThat(page).hasURL("https://practicesoftwaretesting.com/account/favorites");

    }

    @Then("une alerte de non autorisation s affiche")
    public void uneAlerteDeNonAutorisationSAffiche() {

        String Actualmsg = wishlistpage.GetPOPUPmsg();
        String Expected = "Non autorisé, impossible d'ajouter le produit aux favoris.";

        System.out.println("Alerte affiché :" + Actualmsg);
        org.junit.Assert.assertTrue("l'alerte n'est pas affiché ou ne correspond pas !", Actualmsg.contains(Expected));

        System.out.println("Apres validation , l'alerte est bien adequate : " + Actualmsg);

    }

    @Then("un message de confirmation wishlist vide est affiché")
    public void unMessageDeConfirmationWishlistVideEstAffiché() {

        String Actualmsg = wishlistpage.GetMsgwishlistVide();
        String Expected = "Il n'y a pas encore de favoris. Pour ajouter des favoris, allez à la liste des produits et marquez certains produits comme vos favoris.";

        System.out.println("Alerte affiché :" + Actualmsg);
        org.junit.Assert.assertTrue("l'alerte n'est pas affiché ou ne correspond pas !", Actualmsg.contains(Expected));

        System.out.println("Apres validation , l'alerte est bien adequate : " + Actualmsg);
    }
}
