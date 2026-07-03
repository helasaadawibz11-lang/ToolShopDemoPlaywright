package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WishlistPage {

    Page page;

    //Locators

    Locator Wishlist;
    Locator AddTowishlistProduit;
    Locator BoltCutter;
    Locator POPUP;
    Locator Acceuil;
    Locator DeleteButton;
    Locator MenuAccount;
    Locator MenuFavoris;
    Locator Hammer;
    Locator wishlistboltcutterBTN;
    Locator wishlistCombinationpliersBTN;
    Locator Logout;
    Locator WishlistAcceuil;
    Locator MsgWishlistVide;


    // Constructeur : On passe a la page Playwright et on initialise les locators

    public WishlistPage(Page wishpage) {
        this.page = wishpage;

        this.BoltCutter = wishpage.locator("//h5[@data-test='product-name' and normalize-space()='Bolt Cutters']");
        this.AddTowishlistProduit = wishpage.locator("#btn-add-to-favorites");
        this.Wishlist = wishpage.locator("[data-test='nav-favorites']");
        this.POPUP = wishpage.locator(".toast-message");
        this.Acceuil = wishpage.locator("#navbarSupportedContent > ul > li:nth-child(1) > a");
        this.DeleteButton = wishpage.locator("[data-test='delete']");
        this.MenuAccount = wishpage.locator("#menu");
        this.MenuFavoris = wishpage.locator("#navbarSupportedContent > ul > li:nth-child(4) > ul > li:nth-child(2) > a");
        this.Hammer = wishpage.locator("//h5[@data-test='product-name' and normalize-space()='Hammer']");
        this.wishlistboltcutterBTN = wishpage.locator("//*[@id=\"btn-add-to-favorites\"]");
        this.wishlistCombinationpliersBTN = wishpage.locator("//*[@id=\"btn-add-to-favorites\"]");
        this.Logout = wishpage.locator("#navbarSupportedContent > ul > li:nth-child(4) > ul > li:nth-child(7) > a");
        this.WishlistAcceuil = wishpage.locator("body > app-root > div.container > app-overview > div > a:nth-child(1)");
        this.MsgWishlistVide = wishpage.locator("body > app-root > div.container > app-favorites > div > div");
    }


    public void clickSurProduitParNom(String produit) {
        // Utilise un sélecteur dynamique qui s'adapte au paramètre string
        Locator nomproduit = page.locator("//h5[@data-test='product-name']").filter(new Locator.FilterOptions().setHasText(produit));

        nomproduit.waitFor(new Locator.WaitForOptions().setTimeout(5000));
        // Aligne l'écran sur le produit s'il est caché en bas de page
        nomproduit.scrollIntoViewIfNeeded();
        nomproduit.click();
    }

    public void ClickBoltCutter() {

        BoltCutter.click();
    }

    public void ClickHammer() {

        Hammer.click();
    }

    public void ClickAddwishlist() {

        AddTowishlistProduit.waitFor(new Locator.WaitForOptions().setState(com.microsoft.playwright.options.WaitForSelectorState.VISIBLE));
        AddTowishlistProduit.click();
    }

    public void ClickWishlistButton() {

        Wishlist.click();
        page.waitForTimeout(500);
    }

    public String GetPOPUPmsg() {

        // .last() permet d'ignorer la première pop-up en train de disparaître
        // et de se concentrer sur la deuxième qui vient d'apparaître
        page.waitForTimeout(500);

        Locator dernierToast = POPUP.last();

        dernierToast.waitFor();
        return dernierToast.textContent();
    }

    public void ClickAcceuil() {

        Acceuil.click();
    }

    public void ViderWishlist() {

        //  On compte combien de boutons de suppression sont présents dans la page
        int count = DeleteButton.count();
        System.out.println("Nombre de produits à supprimer : " + count);

        //Tant qu'il reste au moins un produit, on clique sur le premier
        while (count > 0) {
            DeleteButton.first().click();

            // On attend que le nombre d'éléments diminue ou change
            page.waitForTimeout(500);

            // On recompte pour mettre à jour la condition de la boucle
            count = DeleteButton.count();
        }

        // Synchronisation finale avec le serveur
        page.reload();
        page.waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE);
        System.out.println("La wishlist est totalement vidée !");


    }

    public void ClickAccountMenu() {

        MenuAccount.click();


    }

    public void ClickMenuFavoris() {

        MenuFavoris.click();
    }

    public void ClickWishlistBoltCutter() {

        wishlistboltcutterBTN.click();
        page.waitForTimeout(500);
    }

    public void ClickWishlistCombinationPliers() {

        wishlistCombinationpliersBTN.click();
        page.waitForTimeout(500);
    }

    public void supprimerProduitParNom(String produit) {
        // Ce XPath cible la bonne ligne contenant le produit voulu
        String xpathDynamique = "//div[contains(@class, 'row') and .//h5[normalize-space()='" + produit + "']]//button[@data-test='delete']";

        Locator boutonDelete = page.locator(xpathDynamique);

        //Attendre que le bouton soit prêt
        boutonDelete.waitFor(new Locator.WaitForOptions().setTimeout(3000));

        // seul clic forcé
        boutonDelete.click(new Locator.ClickOptions().setForce(true));

        page.waitForTimeout(1000);
        page.waitForLoadState(com.microsoft.playwright.options.LoadState.NETWORKIDLE);
    }

    public void ClickLogout() {
        //Logout.waitFor(new Locator.WaitForOptions().setTimeout(3000));
        Logout.click();


    }

    public void AccederWishlist(String Emplacement) {

        if (Emplacement.equals("PageAccount")) {
            //WishlistAcceuil.waitFor(new Locator.WaitForOptions().setTimeout(3000));
            WishlistAcceuil.click();

        } else if (Emplacement.equals("MenuHeaderMYAccount")) {

            MenuAccount.click();
            MenuFavoris.click();

        }

    }

    public String GetMsgwishlistVide() {

        return MsgWishlistVide.textContent();
    }


}
