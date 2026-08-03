Feature: tester le module shopping cart (panier) du site ToolShopDemo

  #As a customer, I want to manage my cart so that I can control what I purchase.
  #✅ Acceptance Criteria
  #Show empty cart message when empty.
  #Add product → appears in cart.
  #Remove product → disappears.

  #🧪 Testing Guidance
  #Add/remove multiple items.
  #Check Total cart calculation correctness.
  #Check Total Product calculation correctness.
  #Refresh page → cart persists.


 #Bob Smith	user	customer3@practicesoftwaretesting.com pass123

  Background:
    Given je suis sur la page accueil du site
    And le catalogue des produits est affiché
    And je choisi d ajouter le produit Bolt Cutters a mon panier


  Scenario: tester le panier vide
    When jaccede a mon panier
    And je vide mon panier du produit "Bolt Cutters"
    Then le tableau produit ne doit pas etre visible
    And le bouton Passer à la caisse ne doit pas être visible
    And le badge Cart quantity est invisible


  Scenario Outline: vérification de l'affichage et comportement du panier remplis
    When jaccede a mon panier
    And mon panier contient le produit "Bolt Cutters"
    Then le tableau produit affiche "<Article>","<Quantité>","<Prix>" et "<Total>" correctement
    And le total panier est correcte
    And les boutons Continuer les achats et passer a la caisse sont disponibles

    Examples:
      | Article      | Quantité | Prix  | Total |
      | Bolt Cutters | 1        | 48.41 | 48.41 |


  Scenario: Vérification des détails du panier rempli avec plusieurs articles
    When je reviens a l acceuil
    And je choisi d ajouter le produit "Slip Joint Pliers"
    And je reviens a l acceuil
    And je choisi d ajouter le produit "Combination Pliers"
    And jaccede a mon panier
    Then le tableau affiche les données produit "Bolt Cutters","1","48.41" et "48.41" correctement
    And le tableau affiche les données produit "Slip Joint Pliers","1","9.17" et "9.17" correctement
    And le tableau affiche les données produit "Combination Pliers","1","14.15" et "14.15" correctement
    And le total panier des differents produits est correcte


  Scenario: Modification directe de la quantité d'un produit dans le panier

    When je reviens a l acceuil
    And je choisi d ajouter le produit "Slip Joint Pliers"
    And je reviens a l acceuil
    And je choisi d ajouter le produit "Combination Pliers"
    And jaccede a mon panier
    And je modifie la quantité du produit "Bolt Cutters" a "5"
    And je tape entrée
    Then un msg de confirmation de modification s affiche "Quantité du produit mise à jour."
    And le tableau affiche les données produit "Bolt Cutters","5","48.41" et "242.05" correctement
    And le tableau affiche les données produit "Slip Joint Pliers","1","9.17" et "9.17" correctement
    And le tableau affiche les données produit "Combination Pliers","1","14.15" et "14.15" correctement
    And le total panier des differents produits est correcte
    And le badge Cart quantity indique "7"


  Scenario: Suppression d'un article spécifique via le bouton rouge (X)
    When je reviens a l acceuil
    And je choisi d ajouter le produit "Slip Joint Pliers"
    And je reviens a l acceuil
    And je choisi d ajouter le produit "Combination Pliers"
    And jaccede a mon panier
    And je clique sur le bouton de suppression pour le produit "Slip Joint Pliers"
    Then l'article "Slip Joint Pliers" disparaît de la liste
    And un msg de confirmation de modification s affiche "Produit supprimé."
    And le total panier des differents produits est correcte
    And le badge Cart quantity indique "2"


  Scenario: Persistance des données du panier après rafraîchissement de la page et navigation libre
    When je reviens a l acceuil
    And je choisi d ajouter le produit "Combination Pliers"
    And jaccede a mon panier
    And je modifie la quantité du produit "Bolt Cutters" a "5"
    And je tape entrée
    And je note les données de la table produits
    And je note le total panier
    And je rafraîchis la page du navigateur
    And je reviens a l acceuil
    And jaccede a mon panier
    Then le tableau affiche les données produit "Bolt Cutters","5","48.41" et "242.05" correctement
    And le tableau affiche les données produit "Combination Pliers","1","14.15" et "14.15" correctement
    And le badge Cart quantity indique "6"


  Scenario: Tentative de changer la quantité produit a ZERO

    When jaccede a mon panier
    And je modifie la quantité du produit "Bolt Cutters" a "0"
    And je tape entrée
    Then un msg de confirmation de modification s affiche "Quantité du produit mise à jour."
    And la barre quantité produit se met a jour a la valeur minimal "1"

  Scenario: Retour au catalogue via le bouton Continuer les achats
    When jaccede a mon panier
    And je clique sur le bouton Continuer les achats
    Then je suis réorienté vers la page d'accueil ou le catalogue de produits
