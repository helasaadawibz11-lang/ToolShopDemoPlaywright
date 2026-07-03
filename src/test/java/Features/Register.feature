Feature: Tester le module inscription du site ToolShopDemo

    #Registred account for test  ( tryme@gmail.com  /  tryME123456789#)
  #✅ Acceptance Criteria for this module
  #Age must be between 18 and 75 inclusive.
  #Email must be unique and in valid format.
  #All mandatory fields (first name, last name, email, password, confirm password) must be filled.
  #Name/address/phone fields respect max lengths and validation rules.
  #Password must be at least 8 characters, contain upper, lower, digit, and symbol, not be compromised, and match confirm password.



    #Happy path Register
    #modifier l'adresse email a chaque execution
  Scenario:  Tester l inscription avec des données valides
    Given je suis sur le site ToolShopDemo
    When j accede au formulaire  de connexion
    And je choisi de creer un compte
    And je remplis le champs prenom avec "Henry"
    And je remplis le champs nom avec "renard"
    And je remplis la date de naissance avec "1999-01-13"
    And je selectionne le pays "Argentina"
    And je remplis le code postal "5000"
    And je remplis le numero de la rue "1234"
    And je remplis le champs telephone avec "334567654344"
    And je remplis le champs adresse email avec "Henry@gmail.com"
    And je saisi un mot de passe conforme "Henry12345678####"
    And je click sur s enregistrer
    Then redirection vers la page Login
    And Connexion reussi avec les données email "Henry@gmail.com" mot de passe "Henry12345678####"



    #cas invalide isolé  : email existant
  Scenario:  Tester l inscription avec un email existant
    Given je suis sur le site ToolShopDemo
    When j accede au formulaire  de connexion
    And je choisi de creer un compte
    And je remplis le champs prenom avec "donatella"
    And je remplis le champs nom avec "renard"
    And je remplis la date de naissance avec "1993-01-18"
    And je selectionne le pays "Albania"
    And je remplis le code postal "5000"
    And je remplis le numero de la rue "1234"
    And je remplis le champs telephone avec "334567654344"
    And je remplis le champs adresse email avec "Henry@gmail.com"
    And je saisi un mot de passe conforme "Donatella12345678####"
    And je click sur s enregistrer
    Then Echec de la redirection vers la page login
    And Affichage alerte "A customer with this email address already exists."



    #Champs obligatoires (*)
  Scenario Outline: Tester les champs obligatoires du formulaire REGISTER

    Given je suis sur le site ToolShopDemo
    When j accede au formulaire  de connexion
    And je choisi de creer un compte
    And je remplis le champs prenom avec "<Firstname>"
    And je remplis le champs nom avec "<Lastname>"
    And je remplis la date de naissance avec "<Birthdate>"
    And je selectionne le pays "<pays>"
    And je remplis le code postal "<Codepostal>"
    And je remplis le numero de la rue "<numeroRue>"
    And je remplis le champs telephone avec "<Tel>"
    And je remplis le champs adresse email avec "<Email>"
    And je saisi un mot de passe conforme "<Password>"
    And je click sur s enregistrer
    Then Echec de la redirection vers la page login
    And Affichage alerte adequate "<MSGERREUR>"
    Examples:
      | Firstname | Lastname | Birthdate  | pays      | Codepostal | numeroRue | Tel          | Email            | Password          | MSGERREUR                        |
      |           | renard   | 1999-01-13 | Argentina | 5000       | 1234      | 334567654344 | renard@gmail.com | Henry12345678#### | Le prénom est requis             |
      | celine    |          | 1999-01-13 | Argentina | 5000       | 1234      | 334567654344 | celine@gmail.com | Henry12345678#### | Le nom de famille est requis     |
      | Amelie    | rené     |            | Argentina | 5000       | 1234      | 334567654344 | ameli@gmail.com  | Henry12345678#### | La date de naissance est requise |
      | sam       | rené     | 1999-01-13 |           |            | 1234      | 334567654344 | sam@gmail.com    | Henry12345678#### | Le pays est requis               |
      | elsa      | rené     | 1999-01-13 | Argentina |            | 1234      | 334567654344 | elsa@gmail.com   | Henry12345678#### | Le code postal est requis        |
      | elsa      | rené     | 1999-01-13 | Argentina | 5000       |           | 334567654344 | elsa@gmail.com   | Henry12345678#### | Le numéro de rue est requis      |
      | anne      | rené     | 1999-01-13 | Argentina | 5000       | 1234      |              | anne@gmail.com   | Henry12345678#### | Le téléphone est requis.         |
      | axe       | rené     | 1999-01-13 | Argentina | 5000       | 1234      | 334567654344 |                  | Henry12345678#### | L'email est requis               |
      | maxime    | rené     | 1999-01-13 | Argentina | 5000       | 1234      | 334567654344 | max@gmail.com    |                   | Le mot de passe est requis       |




        #Données invalides
  Scenario Outline: Tester la soumission du formulaitre avec des données invalides

    Given je suis sur le site ToolShopDemo
    When j accede au formulaire  de connexion
    And je choisi de creer un compte
    And je remplis le champs prenom avec "<Firstname>"
    And je remplis le champs nom avec "<Lastname>"
    And je remplis la date de naissance avec "<Birthdate>"
    And je selectionne le pays "<pays>"
    And je remplis le code postal "<Codepostal>"
    And je remplis le numero de la rue "<numeroRue>"
    And je remplis le champs telephone avec "<Tel>"
    And je remplis le champs adresse email avec "<Email>"
    And je saisi un mot de passe conforme "<Password>"
    And je click sur s enregistrer
    Then Echec de la redirection vers la page login
    And Affichage alerte adequate "<MSGERREUR>"
    Examples:
      | Firstname | Lastname | Birthdate       | pays      | Codepostal | numeroRue | Tel                                               | Email                 | Password                       | MSGERREUR                                                    |
      | max       | renard   | 13/06/99        | Argentina | 5000       | 1234      | 334567654344                                      | max@gmail.com         | Henry12345678####              | Please enter a valid date in YYYY-MM-DD format.              |
      | max       | renard   | 11 fevrier 2000 | Argentina | 5000       | 1234      | 334567654344                                      | max@gmail.com         | Henry12345678####              | Please enter a valid date in YYYY-MM-DD format.              |
      | sabine    | renard   | 2012-01-01      | Argentina | 5000       | 1234      | 334567654344                                      | msabine@gmail.com     | Henry12345678####              | Customer must be 18 years old.                               |
      | sabine    | renard   | 1900-01-01      | Argentina | 5000       | 1234      | 334567654344                                      | msabine@gmail.com     | Henry12345678####              | Customer must be younger than 75 years old.                               |
      | maxime    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | #######                                           | max334@gmail.com      | Henry12345678####              | Seuls les chiffres sont autorisés.                           |
      | maxime    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | OR=1                                              | max334@gmail.com      | Henry12345678####              | Seuls les chiffres sont autorisés.                           |
      | sabine    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 1234567891011121314151617192021222324 | msabine3345@gmail.com | Henry12345678####              | The phone field must not be greater than 24 characters.      |
      | maxime    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | @gmail.com            | Henry12345678####              | Le format de l'email est invalide                            |
      | maxime    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | maxgmail.com          | Henry12345678####              | Le format de l'email est invalide                            |
      | maxime    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | max@                  | Henry12345678####              | Le format de l'email est invalide                            |
      | maxime    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | maxgmailcom           | Henry12345678####              | Le format de l'email est invalide                            |
      | maxime    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | #####@gmail.com       | Henry12345678####              | Le format de l'email est invalide                            |
      | maxime    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | max@gmail.com#        | Henry12345678####              | Le format de l'email est invalide                            |
      | sabine    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | msabine@gmail.com     | 123                            | Le mot de passe doit comporter au moins 6 caractères.        |
      | sabine    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | msabine@gmail.com     | 123456                         | Le mot de passe doit comporter au moins 6 caractères.        |
      | sabine    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | msabine@gmail.com     | 123457                         | Le mot de passe doit comporter au moins 6 caractères.        |
      | sabine    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | msabine@gmail.com     | 12345678                       | Le mot de passe ne peut pas inclure de caractères invalides. |
      | sabine    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | msabine@gmail.com     | 1MAJUSCULEONLY                 | Le mot de passe ne peut pas inclure de caractères invalides. |
      | sabine    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | msabine@gmail.com     | 1minusculeonly                 | Le mot de passe ne peut pas inclure de caractères invalides. |
      | sabine    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | msabine@gmail.com     | 1MAJUSCULEminusculenosymboles  | Le mot de passe ne peut pas inclure de caractères invalides. |
      | sabine    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | msabine@gmail.com     | 1(e.g., @, #, $, etc.          | Le mot de passe ne peut pas inclure de caractères invalides. |
      | sabine    | renard   | 1999-01-13      | Argentina | 5000       | 1234      | 334567654344                                      | msabine@gmail.com     | minusculeMAJUSCULE##'nonumbers | Le mot de passe ne peut pas inclure de caractères invalides. |



       #Edge case : Données invalides cas limites
  Scenario Outline: Tester la soumission du formulaitre avec des données limites

    Given je suis sur le site ToolShopDemo
    When j accede au formulaire  de connexion
    And je choisi de creer un compte
    And je remplis le champs prenom avec "<Firstname>"
    And je remplis le champs nom avec "<Lastname>"
    And je remplis la date de naissance avec "<Birthdate>"
    And je selectionne le pays "<pays>"
    And je remplis le code postal "<Codepostal>"
    And je remplis le numero de la rue "<numeroRue>"
    And je remplis le champs telephone avec "<Tel>"
    And je remplis le champs adresse email avec "<Email>"
    And je saisi un mot de passe conforme "<Password>"
    And je click sur s enregistrer
    Then Echec de la redirection vers la page login

    Examples:
      | Firstname  | Lastname | Birthdate  | pays      | Codepostal | numeroRue | Tel          | Email             | Password          |
      | bernadette | renard   | 1999-01-13 | Argentina | ###!       | 1234      | 334567654344 | berna123@gmail.com   | Henry12345678#### |
      | sabine     | renard   | 1999-01-13 | Argentina | 5000       | ###!      | 334567654344 | msabine435677@gmail.com | Henry12345678#### |
      | sabine     | renard   | 1999-01-13 | Argentina | 5000       | 1234      | 3            | renard4msabine@gmail.com | Henry12345678#### |
      | &##!       | renard   | 1999-01-13 | Argentina | 5000       | 1234      | 334567654344 | reb45msabine@gmail.com | Henry12345678#### |
      | sabine     | #&&!     | 1999-01-13 | Argentina | 5000       | 1234      | 334567654344 | msa3476bine@gmail.com | Henry12345678#### |



  #Format champs password (securité)
  Scenario: Tester le format securisé du password

    Given je suis sur le site ToolShopDemo
    When j accede au formulaire  de connexion
    And je choisi de creer un compte
    And je saisi un mot de passe conforme "Donatella12345678####"
    And je note le format du password
    And je clique sur activer le eye button password
    Then le format du password change et je peux visualiser le texte password



  #champs remplis par defaut

  Scenario: Tester les champs remplis automatiquement
    Given je suis sur le site ToolShopDemo
    When j accede au formulaire  de connexion
    And je choisi de creer un compte
      And je selectionne le pays "Argentina"
      And je remplis le code postal "5000"
      And je remplis le numero de la rue "1234"
    Then Le nom de rue la ville et la région sont remplies automatiquement

