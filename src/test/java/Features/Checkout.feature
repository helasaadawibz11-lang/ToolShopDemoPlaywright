Feature: tester le module checkout et paiement du site Toolshopdemo

  #Account registred for test : Bob Smith	user	customer3@practicesoftwaretesting.com pass123

  #As a customer, I want to checkout with different payment methods
  # so that I can complete my purchase securely.

  #✅ Acceptance Criteria
  #Guest checkout requires all fields.
  #Each payment method validates its inputs according to rules (regex/format).
  #Credit card date must be future.
  #Invalid fields prevent order creation.
  #🧪 Testing Guidance
  #Leave each field empty one at a time → check error message.
  #Enter invalid card number format, expired date, CVV length.
  #Test each payment method end-to-end.

  Background:
    Given je suis sur l accueil du site
    And le catalogue produits est affiché
    And j ajoute le produit Bolt Cutters a mon panier
    And jaccede au panier
    And je tape le bouton passer a la caisse



#///////////Guest mode process checkout ///////////////////

  Scenario: Tester le passage checkout en mode guest
    When je click continuer en tant que invité
    Then le formulaire saffiche contenant les champs "Votre email" , "Votre prénom", "Votre nom de famille"
    And le bouton Continuer en tant qu'invité est disponible


  Scenario: Soumission du formulaire invité avec des champs vides
    When je click continuer en tant que invité
    And je laisse les champs obligatoires vides
    And je click sur le bouton submit du formulaire
    Then un messages derreur saffiche sous le champs Email: "L'email est requis"
    And  un messages derreur saffiche sous le champs Prenom : "Le prénom est requis"
    And un messages derreur saffiche sous le champs Nom : "Le nom de famille est requis"
    And le client reste sur l'étape de connexion


  Scenario Outline: Validation du format de l'adresse email en mode invité
    When je click continuer en tant que invité
    And je saisit l'email "<email_invalide>"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    Then un messages derreur saffiche sous le champs Email: "Le format de l'email est invalide"

    Examples:
      | email_invalide       |
      | email_invalide       |
      | helasaadaoui         |
      | he=la@domain         |
      | @domain.com          |
      | hela space@domain.fr |
      | é#'§#!               |


  Scenario: Validation nominale du profil invité
    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    Then le formulaire de facturation saffiche


  Scenario: Validation de l'etape facturation en mode guest

    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    Then letape de paiement saffiche


  Scenario: Validation des champs saisies automatique de l'etape facturation en mode guest

    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    Then les champs Rue , ville , etat sont remplis automatiquement


  Scenario Outline: Remplissage du formulaire paiment option "Virement bancaire" avec données invalides

    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    And je selectionne l option  "Virement bancaire"
    And je tape le nom de la banque "<Nom_bank>" , le nom du compte "<Nom_compte>" , et le numero du compte "<Num_compte>"
    And je click le bouton confirmer
    Then un message d erreur adequat s affiche "<Msg>"
    Examples:
      | Nom_bank  | Nom_compte       | Num_compte | Msg                                                                                                   |
      |           | Hela             | 334        | Le nom de la banque ne peut contenir que des lettres et des espaces.                                  |
      | Asci##### | Hela             | 334        | Le nom de la banque ne peut contenir que des lettres et des espaces.                                  |
      | 1=3       | Hela             | 334        | Le nom de la banque ne peut contenir que des lettres et des espaces.                                  |
      | Bank      |                  | 334        | Le nom du compte peut contenir des lettres, des chiffres, des espaces, des points et des apostrophes. |
      | Bank      | #####ask'        | 334        | Le nom du compte peut contenir des lettres, des chiffres, des espaces, des points et des apostrophes. |
      | Bank      | ^ùùùù334555 bank | 334        | Le nom du compte peut contenir des lettres, des chiffres, des espaces, des points et des apostrophes. |
      | Bank      | Hela             |            | Le numéro de compte doit être numérique.                                                              |
      | Bank      | Hela             | sd         | Le numéro de compte doit être numérique.                                                              |
      | Bank      | Hela             | ###'3333   | Le numéro de compte doit être numérique.                                                              |
      | Bank      | Hela             | 0-34-234   | Le numéro de compte doit être numérique.                                                              |
      | Bank      | Hela             | 33 445 665 | Le numéro de compte doit être numérique.                                                              |

  Scenario: Remplissage du formulaire paiment option "Virement bancaire" avec données valides

    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    And je selectionne l option  "Virement bancaire"
    And je tape le nom de la banque "Bank" , le nom du compte "Hela" , et le numero du compte "345"
    And je click le bouton confirmer
    Then un message de confirmation s affiche "Payment was successful"


  Scenario: Finaliser le paiment avec option "Virement bancaire" ( données valides)

    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    And je selectionne l option  "Virement bancaire"
    And je tape le nom de la banque "Bank" , le nom du compte "Hela" , et le numero du compte "345"
    And je click le bouton confirmer
    And je note le premier msg de confirmation
    And je click le bouton confirmer
    Then redirection vers confirmation de la commande et affichage msg "Merci pour votre commande ! Votre numéro de facture est "


  Scenario: Finaliser le paiment avec option "Paiement à la livraison"

    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    And je selectionne l option  "Paiement à la livraison"
    And je click le bouton confirmer
    And je note le premier msg de confirmation
    And je click le bouton confirmer
    Then redirection vers confirmation de la commande et affichage msg "Merci pour votre commande ! Votre numéro de facture est "

  Scenario Outline: Remplissage du formulaire de paiment avec option "Carte de crédit" (données invalides)

    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    And je selectionne l option  "Carte de crédit"
    And je tape le num de carte "<Num_carte>" , la date d'expiration "<date>" , et le CVV "<CVV>" et le nom du titulaire "<Titulaire>"
    And je click le bouton confirmer
    Then un message d erreur adequat s affiche "<Msg>"
    Examples:
      | Num_carte           | date    | CVV   | Titulaire | Msg                                           |
      |                     | 03/2029 | 345   | Hela      | Unknown error                                 |
      | 234                 | 03/2029 | 345   | Hela      | Format de numéro de carte invalide.           |
      | 0000-4-0000-0000    | 03/2029 | 345   | Hela      | Format de numéro de carte invalide.           |
      | 0000-abcd-0000-0000 | 03/2029 | 345   | Hela      | Format de numéro de carte invalide.           |
      | 2334234534544543    | 03/2029 | 345   | Hela      | Format de numéro de carte invalide.           |
      | 2334 2345 3454 4543 | 03/2029 | 345   | Hela      | Format de numéro de carte invalide.           |
      | 0000-0000-0000-0000 |         | 345   | Hela      | Unknown error                                 |
      | 0000-0000-0000-0000 | 01/2020 | 345   | Hela      | La date d'expiration doit être dans le futur. |
      | 0000-0000-0000-0000 | 1/2020  | 345   | Hela      | Format de date invalide. Utilisez MM/AAAA.    |
      | 0000-0000-0000-0000 | fv/deux | 345   | Hela      | Format de date invalide. Utilisez MM/AAAA.    |
      | 0000-0000-0000-0000 | ##/#### | 345   | Hela      | Format de date invalide. Utilisez MM/AAAA.    |
      | 0000-0000-0000-0000 | 33/2029 | 345   | Hela      | Format de date invalide. Utilisez MM/AAAA.    |
      | 0000-0000-0000-0000 | 01/2029 |       | Hela      | Unknown error                                 |
      | 0000-0000-0000-0000 | 01/2029 | 1     | Hela      | Le CVV doit comporter 3 ou 4 chiffres.        |
      | 0000-0000-0000-0000 | 01/2029 | 12    | Hela      | Le CVV doit comporter 3 ou 4 chiffres.        |
      | 0000-0000-0000-0000 | 01/2029 | 12456 | Hela      | Le CVV doit comporter 3 ou 4 chiffres.        |
      | 0000-0000-0000-0000 | 01/2029 | abc   | Hela      | Le CVV doit comporter 3 ou 4 chiffres.        |
      | 0000-0000-0000-0000 | 01/2029 | 345   |           | Unknown error                                 |
      | 0000-0000-0000-0000 | 01/2029 | 345   | 345       |                                               |


  Scenario: Finaliser le paiment avec option "Carte de crédit" (données valides)(End-to-End)
    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    And je selectionne l option  "Carte de crédit"
    And je tape le num de carte "0000-0000-0000-0000" , la date d'expiration "01/2029" , et le CVV "345" et le nom du titulaire "hela"
    And je click le bouton confirmer
    And je note le premier msg de confirmation
    And je click le bouton confirmer
    Then redirection vers confirmation de la commande et affichage msg "Merci pour votre commande ! Votre numéro de facture est "


  Scenario: Verifier les options de paiment en mode "Achetez maintenant, payez plus tard"
    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    And je selectionne l option  "Achetez maintenant, payez plus tard"
    And je click la barre des options versements
    Then je peux voir les quatres options de paiment mensuels


  Scenario: valider le paiement en mode "Achetez maintenant, payez plus tard"(End-to-End)
    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    And je selectionne l option  "Achetez maintenant, payez plus tard"
    And je selectionne l option de paiement "9 Versements mensuels"
    And je click le bouton confirmer
    And je note le premier msg de confirmation
    And je click le bouton confirmer
    Then redirection vers confirmation de la commande et affichage msg "Merci pour votre commande ! Votre numéro de facture est "


  Scenario Outline: remplir le formulaire du paiement en mode "Carte cadeau" (données invalides)
    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    And je selectionne l option  "Carte cadeau"
    And je tape le num de carte "<Num_carteCadeau>" et le code de validation "<code>"
    And je click le bouton confirmer
    Then un message d erreur adequat s affiche "<Msg>"
    Examples:
      | Num_carteCadeau  | code | Msg                                                    |
      | 1234             | 234a | Le numéro de la carte cadeau doit être alphanumérique. |
      | abc              | 234a | Le numéro de la carte cadeau doit être alphanumérique. |
      |                  | 234a | Le numéro de la carte cadeau doit être alphanumérique. |
      | ù&é!!!'          | 234a | Le numéro de la carte cadeau doit être alphanumérique. |
      | 33ghjDSSS222344  | 234a | Le numéro de la carte cadeau doit être alphanumérique. |
      | 33ghjDSSS222344d |      | Le code de validation doit être alphanumérique.        |
      | 33ghjDSSS222344d | ééé! | Le code de validation doit être alphanumérique.        |
      | 33ghjDSSS222344d | 233  | Le code de validation doit être alphanumérique.        |
      | 33ghjDSSS222344d | ABC  | Le code de validation doit être alphanumérique.        |


  Scenario: valider le paiement en mode "Carte cadeau" (données valides) (End-to-End)
    When je click continuer en tant que invité
    And je saisit l'email "hela.test@example.com"
    And je saisit le prenom "Hela"
    And je saisit le Nom de famille  "sd"
    And je click sur le bouton submit du formulaire
    And je click sur le bouton  Passer à la caisse deux
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    And je selectionne l option  "Carte cadeau"
    And je tape le num de carte "33ghjDSSS222344d" et le code de validation "234a"
    And je click le bouton confirmer
    And je note le premier msg de confirmation
    And je click le bouton confirmer
    Then redirection vers confirmation de la commande et affichage msg "Merci pour votre commande ! Votre numéro de facture est "





#/////////// User mode process checkout (Session activated)///////////////////

#Bob Smith	user	customer3@practicesoftwaretesting.com pass123
  Scenario Outline: Tester le formulaire Connexion checkout (données invalides)

    When je saisi Email "<Email>" et  Mot de passe "<MPS>"
    And je click Connexion
    Then un message d erreur adequat s affiche "<Msg>"
    Examples:
      | Email                                                                                                | MPS                                                 | Msg                                      |
      | hgj@gmail.com                                                                                        | 123456789                                           | Invalid email or password                |
      | ustomer3@practicesoftwaretesting.com                                                                 | 123456789                                           | Invalid email or password                |
      | customer3@practicesoftwaretesting.com                                                                | 123456789                                           | Invalid email or password                |
      | customer3practicesoftwaretesting.com                                                                 | 123456789                                           | Le format de l'email est invalide        |
      | customer3@practicesoftwaretestingcom                                                                 | 123456789                                           | Invalid email or password                |
      | customer3@practicesoftwaretestingcom                                                                 | pass123                                             | Invalid email or password                |
      |                                                                                                      | pass123                                             | L'email est requis                       |
      | customer3@practicesoftwaretesting.com                                                                |                                                     | Le mot de passe est requis               |
      | customer3@practicesoftwaretesting.com                                                                | pass1                                               | La longueur du mot de passe est invalide |
      | customer3@practicesoftwaretesting.com                                                                | pass123455555555555555555555555555555555456666666gh | La longueur du mot de passe est invalide |
      | customer3@practicesoftwaretesting.comDFGFGGGGGGGGRESSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS     | pass123                                             | Invalid email or password                |
      | customer3@practicesoftwaretesting.comDFGFGGGGGGGGRESSSSSSSSSSDFFFSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS | 123456789                                           | Le format de l'email est invalide        |


  Scenario: Tester le formulaire Connexion checkout (données valides )

    When je saisi Email "customer3@practicesoftwaretesting.com" et  Mot de passe "pass123"
    And je click Connexion
    Then redirection vers la confirmation de l'etape connexion et affichage "vous êtes déjà connecté. Vous pouvez passer à la caisse."


  Scenario: Verifier la disponibilité de la facture apres un checkout en mode Connecté

    When je saisi Email "customer3@practicesoftwaretesting.com" et  Mot de passe "pass123"
    And je click Connexion
    And je click sur le bouton  Passer à la caisse
    And je selectionne le nom pays "Angola"
    And je saisi code postale "33400"
    And je saisi numero de la rue "33"
    And je click sur le bouton passer a la caisse trois
    And je selectionne l option  "Paiement à la livraison"
    And je click le bouton confirmer
    And je note le premier msg de confirmation
    And je click le bouton confirmer
    And je note le numero de facture
    And je click sur mon nom dans le menu Header
    And j ouvre mes factures
    Then le numero de facture recente est disponible
