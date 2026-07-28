Feature: Tester le module Product detail page  du site ToolShopDemo

  #Product Detail Page
  #As a customer, I want to view product details, set quantity,
  # and add to cart or favorites.
  #✅ Acceptance Criteria
  #Add to cart adds correct product and quantity.
  #Add to favorites requires login (error otherwise).

  #🧪 Testing Guidance
  #Add product with quantity 1, 0 (should fail), max allowed.(99)
  #Try favorite while logged out → expect error.


  Background:
    Given je suis sur l'accueil du site ToolShopDemo
    And le catalogue des produits est visible
    And je choisi d ouvrir la page produit Bolt Cutters


  Scenario: Ajouter le produit avec une quantité valide (1)
    When je note le compteur du panier
    And je fixe la quantité à "1"
    And  je clique sur le bouton Ajouter au panier
    Then un message de confirmation "Produit ajouté au panier" doit s'afficher
    And le compteur du panier doit être mis à jour avec la quantité ajouté "1"


  Scenario: : tentative de saisie d'une quantité produit nulle (0)
    When je veux diminuer la quantité a 0
    Then l afficheur quantité affiche toujours 1

  Scenario: Ajouter le produit avec la quantité maximale autorisée (99)
    When je note le compteur du panier
    And je fixe la quantité à "99"
    And  je clique sur le bouton Ajouter au panier
    Then un message de confirmation "Produit ajouté au panier." doit s'afficher
    And le compteur du panier doit être mis à jour avec la quantité ajouté "99"


  Scenario: Tentative de saisie d'une quantité supérieure à la limite (100)
    When je note le compteur du panier
    And je fixe la quantité à "100"
    Then un message derreur "You can order at most 99 of this product." doit s'afficher
    And l afficheur quantité se met a jours a 99 au lieu de 100


  Scenario: Tentative de saisie d'une quantité négative
    When je note le compteur du panier
    And je fixe la quantité à "-5"
    Then l afficheur quantité affiche toujours "1" au lieu de "-5"


  Scenario: Saisie de caractères alphabétiques dans la quantité
    When je note le compteur du panier
    When je fixe la quantité à la sequence "abc"
    Then l afficheur quantité affiche toujours "1" au lieu de "abc"


  Scenario: Augmentation de la quantité via le bouton +
    When je note le compteur du panier
    And je clique sur le bouton de majoration de quantité
    Then l afficheur quantité indique "2"


  Scenario: Tentative d'ajout aux favoris sans être connecté
    When je verifie que aucun compte n'est connecté
    And je fixe la quantité à "2"
    And je clique sur le bouton Ajouter aux favoris
    Then un message derreur "Non autorisé, impossible d'ajouter le produit aux favoris." doit s'afficher

