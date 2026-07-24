Feature: Tester le module Product listing du site ToolShopDemo

  #✅ Acceptance Criteria
  #As a customer, I want to filter, sort, and search products so that I can find what I need quickly.
  #Filtering by category/brand shows matching products only.
  #Sorting by name/price works in both directions.
  #Price range filters products correctly.
  #Reset search clears filters and shows all products.
  #🧪 Testing Guidance
  #Apply filter → check results match criteria.
  #Apply sort → check order.
  #Combine filter + sort → still correct.
  #Reset → all products visible again.


  Background:
    Given je suis sur la page d'accueil du site ToolShopDemo
    And le catalogue de produits est visible


  @test

  # Filtres par categories / marques
  Scenario Outline: Filtrer les produits par sous-catégorie
    When je coche la sous-catégorie "<sous_categorie>" dans la section Filtres
    Then la liste ne doit afficher que des produits appartenant à "<sous_categorie>"
    And le nombre de produits "<sous_categorie>" affichés doit être mis a jour

    Examples:
      | sous_categorie |
      | Chisels        |
      | Drill          |
      | Hammer         |


  @test

  #  Tester le Tri sur une selection de produit
  Scenario Outline: Trier les produits par nom et par prix
    When je coche la sous-catégorie "Hammer" dans la section Filtres
    And je note la liste initial des produits affichés
    And je sélectionne le tri "<option_tri>" dans le menu déroulant Trier
    Then les produits doivent être ordonnés par "<option_tri>" de manière correcte

    Examples:
      | option_tri         |
      | Nom (A - Z)        |
      | Nom (Z - A)        |
      | Prix (Élevé - Bas) |
      | Prix (Bas - Élevé) |

  @test


  # Tester le filtre fourchette de prix
  Scenario: Filtrer les produits par fourchette de prix
    When j'ajuste le curseur de la Fourchette de prix pour MAX 35
    Then tous les produits affichés doivent avoir un prix inférieur ou égal à 35

  @test

  # Rechercher un produit par son nom
  Scenario: Rechercher un produit spécifique par mot-clé
    When je saisis "Hammer" dans le champ de recherche
    And je clique sur le bouton Rechercher
    Then la liste doit afficher les produits correspondants a "Hammer"

  @test

  # Combinaison (Filtre + tri)
  Scenario: Combiner un filtre de catégorie et un tri par prix
    When je coche la sous-catégorie "Hammer" dans la section Filtres
    And je note la liste initial des produits affichés
    And je sélectionne le tri "Prix (Bas - Élevé)" dans le menu déroulant Trier
    Then la liste ne doit afficher que des produits appartenant à "Hammer"
    And les produits doivent être ordonnés par "Prix (Bas - Élevé)" de manière correcte

  @test
  # Réinitialisation = Reset
  Scenario: Réinitialiser les filtres et la recherche
    When je note la liste initial des produits affichés
    And je coche la sous-catégorie "Hammer" dans la section Filtres
    And je saisis "Pliers" dans le champ de recherche
    And je clique sur le bouton X de recherche
    Then tous les filtres doivent être décochés
    And le champ de recherche doit être vide
    And la liste complète des produits initial doit être visible
