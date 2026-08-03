package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ShoppingCartPage {

    Page page;

    //Locators

    Locator Cart;
    Locator tableProduit;
    Locator remove;
    Locator checkout;
    Locator Cartbadge;
    Locator nomproduit;
    Locator Qtproduit;
    Locator prixunit;
    Locator Totalproduit;
    Locator TotalPanier;
    Locator Continue;
    Locator Acceuil;
    Locator AddTocart ;
    Locator TableProducts ;


    // Constructeur : On passe a la page Playwright et on initialise les locators

    public ShoppingCartPage(Page shoppingcartpage) {

        this.page = shoppingcartpage;
        this.Cart = shoppingcartpage.locator("a[href='/checkout']");
        this.tableProduit = shoppingcartpage.locator("table th:has-text('Article')");
        this.remove = shoppingcartpage.locator("a.btn-danger, button.btn-danger");
        this.Cartbadge = shoppingcartpage.locator("#lblCartCount");
        this.nomproduit = shoppingcartpage.locator("[data-test='product-title']");
        this.Qtproduit = shoppingcartpage.locator("[data-test='product-quantity']");
        this.prixunit = shoppingcartpage.locator("[data-test='product-price']");
        this.Totalproduit = shoppingcartpage.locator("[data-test='line-price']");
        this.TotalPanier = shoppingcartpage.locator("[data-test='cart-total']");
        this.Continue = shoppingcartpage.locator("[data-test='continue-shopping']");
        this.checkout = shoppingcartpage.locator("[data-test='proceed-1']");
        this.AddTocart=shoppingcartpage.locator("#btn-add-to-cart");

        //this.Acceuil = shoppingcartpage.locator("[data-test='nav-home']");
        this.Acceuil = shoppingcartpage.locator("#navbarSupportedContent > ul > li:nth-child(1) > a");
        this.TableProducts = shoppingcartpage.locator("table.table-hover");





    }

    public void clickCart() {

        Cart.click();
    }

    public void removeProductByName(String productName) {
        // Localise la ligne (tr) contenant le nom du produit, puis clique sur son bouton rouge
        Locator productRow = page.locator("tr", new Page.LocatorOptions().setHasText(productName));
        productRow.locator("a.btn-danger, button.btn-danger").click();
    }

    public boolean tableproduitIsvisible() {

        return tableProduit.isVisible();
    }

    public boolean Continueisvisible() {

        return Continue.isVisible();
    }

    public boolean checkoutISvisible() {

        return checkout.isVisible();
    }

    public boolean cartbadgeIsvisible() {

        return Cartbadge.isVisible();
    }

    public String getnomproduit() {

        return nomproduit.textContent().replace("\u00a0", " ").trim();
    }


    public String getQuantitéproduit() {

        return Qtproduit.inputValue().trim();


    }

    public String getprixunitproduit() {

        String priceText = prixunit.textContent();
        String cleanPrice = priceText.replace("$", "").trim();

        return cleanPrice;


    }

    public String getprixTotalproduit() {

        String priceText = Totalproduit.textContent();
        String cleanPrice = priceText.replace("$", "").trim();

        return cleanPrice;


    }

    public String getTotalPanier() {

        String priceText = TotalPanier.textContent();
        String cleanPrice = priceText.replace("$", "").trim();

        return cleanPrice;


    }
    public void clickAcceuil(){

        Acceuil.click();
    }

    public void selectProductByName(String productName) {
        // Localise la carte produit (.card) qui contient le nom recherché
        Locator productCard = page.locator(".card")
                .filter(new Locator.FilterOptions().setHasText(productName)).first();

        // Clique sur le produit
        productCard.click();
    }


    // Méthode utilitaire : Trouve la ligne <tr> correspondant au nom de l'article
    private Locator getRowForProduct(String productName) {
        return page.locator("tr").filter(new Locator.FilterOptions().setHasText(productName));
    }

    // Récupère le nom affiché dans la ligne du produit
    public String getNomProduitParNom(String productName) {
        Locator row = getRowForProduct(productName);
        return row.locator("[data-test='product-title']").textContent().replace("\u00a0", " ").trim();
    }

    // Récupère la quantité de la ligne du produit
    public String getQuantiteProduitParNom(String productName) {
        Locator row = getRowForProduct(productName);
        return row.locator("[data-test='product-quantity']").inputValue().trim();
    }

    // Récupère le prix unitaire de la ligne du produit
    public String getPrixUnitProduitParNom(String productName) {
        Locator row = getRowForProduct(productName);
        String priceText = row.locator("[data-test='product-price']").textContent();
        return priceText.replace("$", "").trim();
    }

    // Récupère le prix total de la ligne du produit
    public String getPrixTotalProduitParNom(String productName) {
        Locator row = getRowForProduct(productName);
        String priceText = row.locator("[data-test='line-price']").textContent();
        return priceText.replace("$", "").trim();
    }

    public double calculerSommeTotaleDesLignes() {
        // Récupère tous les éléments de prix de ligne du tableau
        Locator linePrices = page.locator("[data-test='line-price']");
        int count = linePrices.count();
        double sommeTotale = 0.0;

        // Parcourt toutes les lignes du panier et additionne les prix
        for (int i = 0; i < count; i++) {
            String priceText = linePrices.nth(i).textContent();
            String cleanPrice = priceText.replace("$", "").trim();
            sommeTotale += Double.parseDouble(cleanPrice);
        }

        return sommeTotale;
    }

    public void clickAddtocart(){

        AddTocart.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        AddTocart.click();
        // Attend que la notification Toast de succès apparaisse à l'écran
        page.locator(".toast-message, div[role='alert']").waitFor(
                new Locator.WaitForOptions().setTimeout(5000) ) ;

    }

    public String getbadgeQuantity(){

        return Cartbadge.textContent();
    }

    public void EnterQuantity(String productName,String QT){

        Locator row = getRowForProduct(productName);
         row.locator("[data-test='product-quantity']").fill(QT);
    }

    public String getMsgAlertePanier(){

        return page.locator(".toast-message, div[role='alert']").last().textContent().trim();
    }

    public boolean Checkproductvisibility(String productName){

        Locator row = getRowForProduct(productName);

        // Si la ligne existe et est visible, retourne true. Sinon false.
        return row.isVisible();
    }

    public String Affichetableproduit(){

        return TableProducts.innerText();    }

    public void clickContinue(){

        Continue.click();
    }

}
