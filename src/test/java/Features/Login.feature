Feature: Tester le module Login du site ToolShopDemo

    #Registred account for test  ( tryme@gmail.com  /  tryME123456789#)
      #Bob Smith	user	customer3@practicesoftwaretesting.com pass123


  #✅ Acceptance Criteria
  #User can log in with valid credentials.
  #Login fails with incorrect password, unknown email, or disabled account.
  #Error messages are clear and specific.
  #🧪 Testing Guidance
  #Valid login: expect redirect to dashboard.
  #Invalid password: expect error message, no login.
  #Invalid email: error message, no login.
  #Disabled account: error message.


   #Happy path Register ( lancer ce test au cas ou happy path login echoue )
  Scenario:   inscription avec des données valides
    Given je suis sur le site ToolShopDemo
    When j accede au formulaire  de connexion
    And je choisi de creer un compte
    And je remplis le champs prenom avec "try"
    And je remplis le champs nom avec "me"
    And je remplis la date de naissance avec "1999-01-13"
    And je selectionne le pays "Argentina"
    And je remplis le code postal "5000"
    And je remplis le numero de la rue "1234"
    And je remplis le champs telephone avec "334567654344"
    And je remplis le champs adresse email avec "tryme@gmail.com"
    And je saisi un mot de passe conforme "tryME123456789#"
    And je click sur s enregistrer
    Then redirection vers la page Login


  #Happy path login
  Scenario: Connexion reussie avec des données valides et existante
    Given je connecte sur le site ToolShopDemo
    When j ouvre le formulaire  de connexion
    And je saisi adresse email "customer3@practicesoftwaretesting.com"
    And je saisi Password "tryME123456789#"
    And je click sur login
    Then Redirection vers mon tableau de bord



    #Login avec identifiants invalides (jeux de données)
  Scenario Outline: Connexion avec des données invalides
    Given je connecte sur le site ToolShopDemo
    When j ouvre le formulaire  de connexion
    And je saisi adresse email "<Email>"
    And je saisi Password "<Password>"
    And je click sur login
    Then Echec de connexion et affichage Alerte adequate "<Alerte>"

    Examples:
      | Email            | Password                 | Alerte                                   |
      |                  | tryME123456789#          | L'email est requis                       |
      | tryme@gmail.com  |                          | Le mot de passe est requis               |
      | tryher@gmail.com | tryME123456789#          | Invalid email or password                |
      | try              | tryME123456789#          | Le format de l'email est invalide        |
      | @                | tryME123456789#          | Le format de l'email est invalide        |
      | @gmail.com       | tryME123456789#          | Le format de l'email est invalide        |
      | tryme@gmailcom   | tryME123456789#          | Invalid email or password                |
      | tryme@gmail.com  | 123                      | Invalid email or password                |
      | tryme@gmail.com  | 1                        | La longueur du mot de passe est invalide |
      | sabine@gmail.com | sabineISNOTREGISTRED123# | Invalid email or password                |
      | OR=123@gmail.com | ####                     | Invalid email or password                |
      | TRYME@GMAIL.COM  | tryME123456789#          | Invalid email or password                |




    #destruction session apres logout
  Scenario: Tester le retour en arriére vers la session du compte apres logout
    Given je connecte sur le site ToolShopDemo
    When j ouvre le formulaire  de connexion
    And je saisi adresse email "customer3@practicesoftwaretesting.com"
    And je saisi Password "pass123"
    And je click sur login
    And je me deconnecte de mon compte
    And j actualise la page
    And je click sur le retour page precedante
    Then je reste toujours sur la page login

