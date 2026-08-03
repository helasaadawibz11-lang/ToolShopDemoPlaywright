#🎭 [Playwright] ToolShopDemo Automated Testing Framework

> Ce dépôt héberge un framework d'automatisation de tests de bout en bout (E2E) pour le site e-commerce d'entraînement **ToolShopDemo**.
>  L'objectif est de couvrir le tunnel de conversion critique et les parcours utilisateurs essentiels.

---

## 🎯 Plan de Test & Couverture des Modules


### 🔐 Gestion du Compte, Session  & gestion produit 
- **Register** : Inscription d'un nouvel utilisateur avec validation des données obligatoires.
- **Login** : Connexion réussie, gestion des profils (ex: *Bob Smith*) et déconnexion.
- **Wishlist** : Ajout/suppression de produits depuis la fiche détail, gestion des doublons, points d'accès et persistance après reconnexion.

### 🛒 Tunnel d'Achat Critique (E2E Core) — *En cours de construction*
-  **Product Listing** : Navigation dans le catalogue, filtres de recherche 
-  **Product Detail Page** : Vérification des fiches produits, ajustement des quantités et ajout au panier.
-  **Shopping Cart** : Modification du panier, suppression d'articles et calcul automatique du montant total.
-  **Checkout and Payment** : Flux complet de validation des adresses, choix de la méthode de paiement (Bank Transfer, Credit Card, ..) et validation de la commande avec génération de facture.

---

## 💻 Stack Technique & Architecture

Le projet est conçu comme suit :

* **Langage de programmation :** Java
* **Framework d'automatisation :** Playwright Java 
* **Approche Méthodologique :** BDD (Behavior-Driven Development) avec **Cucumber** (scénarios rédigés en Gherkin)
* **Design Pattern :** Page Object Model (POM) pour garantir la maintenabilité et la réutilisabilité des locators
* **Gestionnaire de dépendances :** Maven (`pom.xml`)

---


