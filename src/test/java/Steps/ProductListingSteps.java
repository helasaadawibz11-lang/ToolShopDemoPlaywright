package Steps;

import Pages.ProductListingPage;
import Pages.RegisterPage;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class ProductListingSteps {

    Page page;
    ProductListingPage listingPage;

     List<String> initialProductListNames;
     List<Double> initialProductListPrices;

     List<String> filteredProductListNames;
     List<Double> filteredProductLisPrices;



    // Initialisation de la page via le Hook active

    @Given("je suis sur la page d'accueil du site ToolShopDemo")
    public void jeSuisSurLaPageDAccueilDuSiteToolShopDemo() {
        this.page = Hook.getPage();
        this.listingPage=new ProductListingPage(page);
        page.navigate("https://practicesoftwaretesting.com/");
    }

    @And("le catalogue de produits est visible")
    public void leCatalogueDeProduitsEstVisible() {
        listingPage.ClickAcceuil();
        page.waitForTimeout(1000);
        System.out.println("vous etes sur la page catalogue produit , vous pouvez pousuivre vos test ");
    }

    @When("je coche la sous-catégorie {string} dans la section Filtres")
    public void jeCocheLaSousCatégorieDansLaSectionFiltres(String sous_categorie) {
        listingPage.Selectioncathegorie(sous_categorie);
        page.waitForTimeout(1000);
        System.out.println("la sous categorie selectionné est : " +sous_categorie);
    }

    @Then("la liste ne doit afficher que des produits appartenant à {string}")
    public void laListeNeDoitAfficherQueDesProduitsAppartenantÀ(String sous_categorie) {

        listingPage.Getproduitcategorie();
        listingPage.verifcategorie(sous_categorie);


    }

    @And("le nombre de produits {string} affichés doit être mis a jour")
    public void leNombreDeProduitsAffichésDoitÊtreMisAJour(String sous_categorie) {
        int sizelist= listingPage.GetsizetousLesNomsCategories();


        if (sous_categorie.equals("Chisels")){

            Assert.assertEquals(3,sizelist);    }

        else if (sous_categorie.equals("Drill")) {
            Assert.assertEquals(2,sizelist);    }

        else if (sous_categorie.equals("Hammer")) {
            Assert.assertEquals(7,sizelist);    }

    System.out.println("Apres validation , le nombre de produit de la sous categorie selectionné est correcte et est egale a "+sizelist);


    }

    @And("je note la liste initial des produits affichés")
    public void jeNoteLaListeInitialDesProduitsAffichés() {

        // capture liste des noms initiaux
        initialProductListNames = listingPage.Getproduitcategorie();

        System.out.println("Noms des  Produits initiaux (" + initialProductListNames.size() + ") : " + initialProductListNames);

        //capture liste des prix initiaux
        initialProductListPrices=listingPage.getDisplayedProductPrices() ;
        System.out.println("les prix des Produits initiaux (" + initialProductListPrices.size() + ") : " + initialProductListPrices);
    }

    @When("je sélectionne le tri {string} dans le menu déroulant Trier")
    public void jeSélectionneLeTriDansLeMenuDéroulantTrier(String option_tri) {

        listingPage.ClickFilter();
        listingPage.SelectionOption(option_tri);
        page.waitForTimeout(1000);
        System.out.println("l'option de tri selectionnée est : " +option_tri);

    }
    @Then("les produits doivent être ordonnés par {string} de manière correcte")
    public void lesProduitsDoiventÊtreOrdonnésParDeManièreCorrecte(String option_tri) {

     //   Récupérer les listes (noms et prix) réellement affichées à l'écran APRÈS le clic sur le tri
        filteredProductListNames = listingPage.Getproduitcategorie();
        filteredProductLisPrices=listingPage.getDisplayedProductPrices() ;


        if (option_tri.equals("Nom (A - Z)")) {

            //Créer la liste attendue en triant la liste initiale par ordre alphabétique
            List<String> ExpectedList = initialProductListNames.stream().sorted().collect(Collectors.toList());

            // Comparer la liste filtrée avec la liste attendue
            Assert.assertEquals("La liste n'est pas triée de A à Z", ExpectedList, filteredProductListNames);

            System.out.println(" apres verification de la liste filtrée , le filtre de A a Z fonctionne correctement " +filteredProductListNames);

        } else if (option_tri.equals("Nom (Z - A)")) {

            // tri inverse (Z à A) ( sorted .reverseOrder)
            List<String> ExpectedList = initialProductListNames.stream().sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());

            Assert.assertEquals("La liste n'est pas triée de Z à A", ExpectedList, filteredProductListNames);
            System.out.println(" apres verification de la liste filtrée , le filtre de Z a A fonctionne correctement " +filteredProductListNames);

        }
         else if (option_tri.equals("Prix (Élevé - Bas)")) {

            //Créer la liste attendue en triant la liste initiale par ordre Décroissant
            List<Double> ExpectedList =initialProductListPrices.stream().sorted(Comparator.reverseOrder())
                    .collect(Collectors.toList());
            Assert.assertEquals("La liste des prix n'est pas triée par ordre decroissant Prix (Élevé - Bas)", ExpectedList, filteredProductLisPrices);
            System.out.println(" apres verification de la liste filtrée , le filtre Prix (Élevé - Bas) fonctionne correctement " +filteredProductLisPrices);

        } else if (option_tri.equals("Prix (Bas - Élevé)")) {

            //Créer la liste attendue en triant la liste initiale par ordre Décroissant
            List<Double> ExpectedList =initialProductListPrices.stream().sorted()
                    .collect(Collectors.toList());
            Assert.assertEquals("La liste des prix n'est pas triée par ordre croissant Prix (Bas - Élevé)", ExpectedList, filteredProductLisPrices);
            System.out.println(" apres verification de la liste filtrée , le filtre Prix (Bas - Élevé) fonctionne correctement " +filteredProductLisPrices);

        }
    }

    @When("j'ajuste le curseur de la Fourchette de prix pour MAX {int}")
    public void jAjusteLeCurseurDeLaFourchetteDePrixPourMAX(int targetvalue) {

        listingPage.setMaxPriceSliderWithKeyboard(targetvalue);
        System.out.println("le curseur de fourchette de prix est maintenant fixé sur l'intervalle : 0 , " +targetvalue);
   }

    @Then("tous les produits affichés doivent avoir un prix inférieur ou égal à {int}")
    public void tousLesProduitsAffichésDoiventAvoirUnPrixInférieurOuÉgalÀ(int targetvalue) {

        filteredProductLisPrices=listingPage.getDisplayedProductPrices() ;

        for (double price : filteredProductLisPrices) {

            // Assertion JUnit : si un prix > targetvalue, le test s'arrête et échoue immédiatement
            Assert.assertTrue(
                    "Anomalie : Le produit au prix de " + price + "€ dépasse la valeur cible de " + targetvalue + "€",
                    price <= targetvalue
            );
        }

        System.out.println("Validation réussie : Tous les prix (" + filteredProductLisPrices + ") sont <= " + targetvalue);

    }

    @When("je saisis {string} dans le champ de recherche")
    public void jeSaisisDansLeChampDeRecherche(String produit) {

        listingPage.Enterproductname(produit);

    }


    @And("je clique sur le bouton Rechercher")
    public void jeCliqueSurLeBoutonRechercher() {
        listingPage.Clickrechercher();
        page.waitForTimeout(1000);

    }

    @Then("la liste doit afficher les produits correspondants a {string}")
    public void laListeDoitAfficherLesProduitsCorrespondantsA(String produit) {

        filteredProductListNames = listingPage.Getproduitcategorie();

        for (String product : filteredProductListNames) {

            // Assertion JUnit : si un element ne contient pas le nom produit , le test s'arrête et échoue immédiatement
            Assert.assertTrue(
                    "Anomalie : Le produit au nom de " + product + " ne fait pas partie de la cathegorie filtrée " + produit ,
                    product.contains(produit)
            );
        }
        System.out.println(" la liste filtrée affichée aprés verification est conforme au mot clé :" +produit);

    }

    @And("je clique sur le bouton X de recherche")
    public void jeCliqueSurLeBoutonXDeRecherche() {
        listingPage.ClickButtonX();
        page.waitForTimeout(1000);

    }

    @Then("tous les filtres doivent être décochés")
    public void tousLesFiltresDoiventÊtreDécochés() {
        boolean allUnchecked = listingPage.areAllCheckboxesUnchecked();

        Assert.assertTrue(
                "Erreur : Au moins une case à cocher est restée sélectionnée dans les filtres !",
                allUnchecked
        );

        System.out.println("Validation réussie : Toutes les cases des filtres sont bien décochées.");
    }

    @And("le champ de recherche doit être vide")
    public void leChampDeRechercheDoitÊtreVide() {

        boolean verif = listingPage.checkSearchbar();
        Assert.assertTrue("Erreur : le champs de recherche n'est pas vide", verif );

        System.out.println("apres verification , la barre de recherche st vide ! ");
    }

    @And("la liste complète des produits initial doit être visible")
    public void laListeComplèteDesProduitsInitialDoitÊtreVisible() {

       List<String> ActualList= listingPage.Getproduitcategorie();
        Assert.assertTrue("Erreur :la liste produits affiché ne correspond pas a la liste initial"
                , ActualList.equals(initialProductListNames) );

        System.out.println("apres validation , la liste actuelle affichée est la liste initial sans filtres " +ActualList);


    }
}
