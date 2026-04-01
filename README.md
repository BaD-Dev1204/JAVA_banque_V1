Voici les énoncés des exercices extraits du document, adaptés pour être compatibles avec les standards et les conventions de **Java (POO)**.

---

Exercice 1 : Modélisation de base (Encapsulation et Classes)

1. **Classe `Personne**` :
* Créez une classe `Personne` avec les attributs privés suivants :
* `nom` (String)
* `prenom` (String)
*
`dateNaiss` (Utilisez `java.time.LocalDate`)




* Implémentez les **Getters** et **Setters** publics pour ces attributs.


2. **Classe `Courant**` :
* Créez une classe `Courant` représentant un compte bancaire avec les attributs privés :
* `numero` (String)
*
`solde` (double) - **Lecture seule** (pas de Setter public)


*
`ligneDeCredit` (double) - Doit être supérieure ou égale à 0


* `titulaire` (objet de type `Personne`)


* Méthodes publiques :
* `void retrait(double montant)` : déduit le montant du solde.
*
`void depot(double montant)` : ajoute le montant au solde.







---

Exercice 2 : Gestion de collection (Remplacement des Indexeurs)

1. **Classe `Banque**` :
* Attributs :
* `nom` (String)
*
`comptes` : Utilisez une `Map<String, Courant>` (en Java, on remplace l'indexeur C# par une collection de type clé/valeur pour retrouver un compte par son numéro).




* Méthodes :
* `void ajouter(Courant compte)` : ajoute un compte à la banque.
*
`void supprimer(String numero)` : supprime un compte via son numéro.


*
`Courant getCompte(String numero)` : remplace l'indexeur C# pour retourner un compte sur base de son numéro.







---

Exercice 3 : Logique métier (Remplacement de la surcharge d'opérateurs)

*Note : Java ne supportant pas la surcharge d'opérateurs comme le `+`, nous utilisons des méthodes nommées.*

1. Dans la classe `Courant`, créez une méthode statique (ou d'instance) `calculerSommeSoldes(Courant c1, Courant c2)` qui retourne la somme des soldes. Les soldes négatifs doivent être ignorés (comptés comme 0).


2. Dans la classe `Banque`, ajoutez une méthode `avoirDesComptes(Personne titulaire)` qui calcule le total des avoirs de tous les comptes appartenant à cette personne.



---

Exercice 4 : Héritage et Abstraction

1. **Classe `Epargne**` :
* Créez cette classe avec un attribut supplémentaire `dateDernierRetrait` (`LocalDate`).




2. **Refactoring avec la classe `Compte**` :
* Définissez une classe **abstraite** `Compte`.


* Déplacez les parties communes (`numero`, `solde`, `titulaire`) de `Courant` et `Epargne` vers `Compte`.


* Le mutateur (Setter) de `solde` doit être `protected` ou `private` pour garantir l'encapsulation.




3. **Calcul d'intérêts** :
* Ajoutez une méthode abstraite `protected double calculInteret()` dans `Compte`.


* **Implémentation Java** :
* Dans `Epargne` : le taux est fixe à **4,5%**.


* Dans `Courant` : si le solde est positif, le taux est de **3%**, sinon il est de **9,75%**.




* Ajoutez une méthode publique `appliquerInteret()` dans `Compte` qui met à jour le solde en utilisant le résultat de `calculInteret()`.
