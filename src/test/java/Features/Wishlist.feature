Feature: tester le module Wishlist du site ToolShopDemo


 # default accounts :

  #Jane Doe	user	customer@practicesoftwaretesting.com	welcome01
  #Jack Howe	user	customer2@practicesoftwaretesting.com	welcome01
  #Bob Smith	user	customer3@practicesoftwaretesting.com pass123

  #✅ Acceptance Criteria
  #User can add a product to favorites from product listing or detail page.
  #Favorited products appear in the Favorites list.
  #User can remove a favorite from the list and it no longer appears.
  #🧪 Testing Guidance
  #Add multiple products → check they all appear.
  #Remove one → confirm only that product is removed.
  #Try adding same product twice → check for duplicates or error.
  #Log out & log back in → favorites should persist.



  Background: Connexion reussie avec des données valides et existante
    Given je connecte sur le site ToolShopDemo
    When j ouvre le formulaire  de connexion
    And je saisi adresse email "customer3@practicesoftwaretesting.com"
    And je saisi Password "pass123"
    And je click sur login



    #Ajout produit de la page detail produit  et verification wishlist
  Scenario: tester l'ajout d'un produit a la wishlist
    Given ma wishlist est vide
    When je click sur Acceuil
    And je click sur le produit "Bolt Cutters"
    And je click sur le bouton ajouter au favoris du produit
    Then une alerte de confirmation ajout s affiche
    And le produit "Bolt Cutters" est present dans ma wishlist


    #Try adding same product twice → check for duplicates or error.
  Scenario: tester l'ajout d'un produit plusieurs fois a la wishlist
    Given ma wishlist est vide
    When je click sur Acceuil
    And je click sur le produit "Bolt Cutters"
    And je click sur le bouton ajouter au favoris du produit
    And je click sur Acceuil
    And je click sur le produit "Bolt Cutters"
    And je click sur le bouton ajouter au favoris du produit
    And je click sur le bouton ajouter au favoris du produit
    Then une alerte d avertissement  s affiche
    And le produit "Bolt Cutters" est present dans ma wishlist une seule fois



      #Add multiple products → check they all appear.
  Scenario: tester l'ajout des differents produit a la wishlist
    Given ma wishlist est vide
    When je click sur Acceuil
    And je click sur le produit du nom "Bolt Cutters"
    And je click sur le bouton Addtowishlist Bolt Cutters
    And je click sur Acceuil
    And je click sur le produit du nom "Combination Pliers"
    And je click sur le bouton Addtowishlist Combination Pliers
    And je click sur Acceuil
    And j accede a la wishlist
    Then mes produits "Combination Pliers" et "Bolt Cutters" sont presents dans la wishlist



  #User can remove a favorite from the list and it no longer appears.
  Scenario: tester la suppression d'un produit specifique de ma wishlist
    Given ma wishlist est vide
    When je click sur Acceuil
    And je click sur le produit du nom "Bolt Cutters"
    And je click sur le bouton Addtowishlist Bolt Cutters
    And je click sur Acceuil
    And je click sur le produit du nom "Combination Pliers"
    And je click sur le bouton Addtowishlist Combination Pliers
    And je click sur Acceuil
    And j accede a la wishlist
    And mes produits "Combination Pliers" et "Bolt Cutters" sont presents dans la wishlist
    And je supprime le produit "Bolt Cutters" de ma wishlist
    And je rafraichis la page
    Then le produit "Bolt Cutters" n'est pas present dans ma wishlist



    #persistance wishlist :   #Log out & log back in → favorites should persist.
  Scenario: tester la persistance de la wishlist
    Given ma wishlist est vide
    When je click sur Acceuil
    And je click sur le produit "Bolt Cutters"
    And je click sur le bouton ajouter au favoris du produit
    And j accede a la wishlist
    And le produit "Bolt Cutters" est present dans ma wishlist
    And je click sur Acceuil
    And je click sur Menu
    And je click sur Logout
    And je rafraichis la page
    And j ouvre le formulaire  de connexion
    And je saisi adresse email "customer3@practicesoftwaretesting.com"
    And je saisi Password "pass123"
    And je click sur login
    And j accede a la wishlist
    Then le produit "Bolt Cutters" est present dans ma wishlist



    #Tester les point d'acces a la wishlist
  Scenario Outline: tester l'accés a la wishlist depuis differents emplacements
    Given je suis sur la page de mon compte
    When j accede au bouton favoris depuis "<Emplacement>"
    Then la page favoris s'affiche
    Examples:
      | Emplacement    |
      | PageAccount   |
      | MenuHeaderMYAccount |



    #tester la wishlist en guest mode
  Scenario: tester l'ajout d'un produit a la wishlist en etant un visiteur du site
    Given je suis sur la page de mon compte
    When je click sur Menu
    And je click sur Logout
    And je click sur Acceuil
    And je click sur le produit "Bolt Cutters"
    And je click sur le bouton ajouter au favoris du produit
    Then une alerte de non autorisation s affiche



    #tester le message d'alerte de la wishliste vide
  Scenario:  tester le message de wishlist vide
    Given ma wishlist est vide
    When je rafraichis la page
    Then un message de confirmation wishlist vide est affiché







