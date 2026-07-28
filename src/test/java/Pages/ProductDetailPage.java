package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.cucumber.java.ca.Quan;

public class ProductDetailPage {

    Page page;


    //locators
    Locator product;
    Locator Quantity;
    Locator minusButton;
    Locator plusButton;
    Locator Addtocart;
    Locator Addtowishlist;
    Locator Cartquantity;
    Locator Alerte;
    Locator VerifCompte;


    // Constructeur : On passe a la page Playwright et on initialise les locators

    public ProductDetailPage(Page detailpage) {

        this.page = detailpage;
        this.product = detailpage.locator(".card-title:has-text('Bolt Cutters')");
        this.Quantity = detailpage.locator("#quantity-input");
        this.minusButton = detailpage.locator("#btn-decrease-quantity");
        this.plusButton = detailpage.locator("#btn-increase-quantity");
        this.Addtocart = detailpage.locator("#btn-add-to-cart");
        this.Addtowishlist = detailpage.locator("#btn-add-to-favorites");
        this.Cartquantity = detailpage.locator("#lblCartCount");
        this.Alerte = detailpage.locator("#toast-container");
        this.VerifCompte = detailpage.locator("//*[@id=\"navbarSupportedContent\"]/ul/li[4]/a");


    }

    public void clickproduct() {

        product.click();

    }

    public void sendQuantity(String QT) {

        Quantity.clear();
        Quantity.fill(QT);

    }

    public void sendQuantitySequence(String text) {

        Quantity.clear();
        // Simule la frappe touche par touche comme un utilisateur physique
        Quantity.pressSequentially(text);

    }

    public void clickAddtocart() {

        Addtocart.click();
    }

    public String getAlerte() {

        return Alerte.textContent();
    }

    public String getCartQuantity() {
        // On vérifie si l'élément du compteur est visible sur la page
        if (Cartquantity.isVisible()) {
            return Cartquantity.textContent().trim();
        } else {
            // Si le panier est vide et que le badge n'est pas affiché, la quantité est 0
            return "0";
        }
    }

    public void decreaseQT() {

        minusButton.click();

    }

    public void increaseQT() {

        plusButton.click();
    }

    public String getnumberQuantity() {

        return Quantity.inputValue().trim();
    }

    public String getusernameAccount() {

        return VerifCompte.textContent();
    }

    public void clickAddtoWishlist() {

        Addtowishlist.click();
    }

}
