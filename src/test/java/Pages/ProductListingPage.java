package Pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import org.junit.Assert;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;

public class ProductListingPage {

    Page page ;


    //locators :

    Locator Acceuil ;
    Locator checkboxChisels ;
    Locator CheckboxDrill ;
    Locator Checkboxhammer ;
    Locator ClassnameCategorie ;

    List<String> tousLesNomsCategories ;
    Locator Filtericon ;
    Locator productPricesSelector ;
    Locator slidermax ;
    Locator searchbar ;
    Locator submit ;
    Locator ButtonX ;
    Locator allFilterCheckboxes ;





    // Constructeur : On passe a la page Playwright et on initialise les locators


    public ProductListingPage(Page listingpage){

        this.page=listingpage;

        this.Acceuil = listingpage.locator("#navbarSupportedContent > ul > li:nth-child(1) > a");
        this.checkboxChisels = listingpage.locator("label:has-text('Chisels')");
        this.CheckboxDrill = listingpage.locator("label:has-text('Drill')");
        this.Checkboxhammer  = listingpage.locator("label:has-text('Hammer')");
        this.ClassnameCategorie=listingpage.locator(".card-title");
        this.Filtericon = listingpage.locator("[data-test='sort']");

        this.productPricesSelector = listingpage.locator("[data-test='product-price']");
        this.slidermax = listingpage.locator(".ngx-slider-pointer-max");
        this.searchbar=listingpage.locator("#search-query");
        this.submit= listingpage.locator("//*[@id=\"filters\"]/form[2]/div/button[2]");
        this.ButtonX=listingpage.locator("//*[@id=\"filters\"]/form[2]/div/button[1]");
        this.allFilterCheckboxes= listingpage.locator("#filters input[type='checkbox']");




    }

    public void ClickAcceuil (){

        Acceuil.click();


    }

    public void Selectioncathegorie(String sous_categorie){

        if (sous_categorie.equalsIgnoreCase("Chisels")){
            checkboxChisels.click();
        } else if (sous_categorie.equalsIgnoreCase("Drill")) {
            CheckboxDrill.click();
        } else if (sous_categorie.equalsIgnoreCase("Hammer")) {
            Checkboxhammer.click();
        }

        page.waitForTimeout(1000);


    }

    public List<String> Getproduitcategorie (){

        tousLesNomsCategories  = ClassnameCategorie.allTextContents();
        System.out.println(tousLesNomsCategories);
        return  tousLesNomsCategories ;
    }


    public void verifcategorie(String sous_categorie){

        if (tousLesNomsCategories == null || tousLesNomsCategories.isEmpty()) {
            Assert.fail("La liste des catégories est vide ! Les filtres n'ont peut-être pas fini de charger.");
        }

        for (String nom : tousLesNomsCategories){
            // Utilisation de .toLowerCase() pour éviter les échecs liés à la casse (ex: "Hand Saw" vs "hand saw")
            Assert.assertTrue(
                    "Le produit '" + nom + "' ne contient pas la sous-catégorie '" + sous_categorie + "'",
                    nom.toLowerCase().contains(sous_categorie.toLowerCase())
            );
        }
    }

    public int GetsizetousLesNomsCategories (){

        return tousLesNomsCategories.size();
    }

    public void ClickFilter(){

        Filtericon.click();
    }
    public void SelectionOption(String option_Tri){

        if (option_Tri.equalsIgnoreCase("Nom (A - Z)")) {
            Filtericon.selectOption("name,asc");
        } else if (option_Tri.equalsIgnoreCase("Nom (Z - A)")) {
            Filtericon.selectOption("name,desc");
        } else if (option_Tri.equalsIgnoreCase("Prix (Élevé - Bas)")) {
            Filtericon.selectOption("price,desc");
        } else if (option_Tri.equalsIgnoreCase("Prix (Bas - Élevé)")) {
            Filtericon.selectOption("price,asc");
        }

        page.waitForTimeout(1000);

    }

    //Récupère la liste des prix sous forme de valeurs numériques (Double)

    public List<Double> getDisplayedProductPrices() {

        List<String> rawPrices =productPricesSelector.allTextContents();

        // Transforme "$12.58" en Double 12.58 pour faciliter les comparaisons mathématiques
        return rawPrices.stream()
                .map(price -> price.replace("$", "").trim())
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    public void setMaxPriceSliderWithKeyboard(int targetValue) {

        //Donner le focus au slider
        slidermax.focus();

        // Appuyer sur la flèche gauche ou droite jusqu'à atteindre la valeur ciblée targetValue
        // lire la valeur actuelle via l'attribut 'aria-valuenow'
        while (Integer.parseInt(slidermax.getAttribute("aria-valuenow")) > targetValue) {
            slidermax.press("ArrowLeft");
        }

        while (Integer.parseInt(slidermax.getAttribute("aria-valuenow")) < targetValue) {
            slidermax.press("ArrowRight");
        }
    }

    public void Enterproductname(String produit){

        searchbar.fill(produit);

    }
    public void Clickrechercher (){
        submit.click();

    }
    public void ClickButtonX (){

        ButtonX.click();

    }
    public boolean areAllCheckboxesUnchecked() {
        // Récupère le nombre total de checkboxes dans la section filtres
        int count = allFilterCheckboxes.count();

        for (int i = 0; i < count; i++) {
            // Si au moins une case est cochée, la fonction renvoie false immédiatement
            if (allFilterCheckboxes.nth(i).isChecked()) {
                System.out.println("Anomalie : La checkbox à l'index " + i + " est encore cochée !");
                return false;
            }
        }
        return true; // Toutes les cases sont bien décochées
    }

    public boolean checkSearchbar(){

        if(searchbar.inputValue().trim().isEmpty()){

            return true ;
        }
        else return false ;

    }

}
