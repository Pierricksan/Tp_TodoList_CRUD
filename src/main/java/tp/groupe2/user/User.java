package tp.groupe2.user;

public class User {

        //attribut
        public int id;
        public String nom;
        public String prenom;
        // getter and setter
        public String getNom() {
            return nom;
        }
        public void setNom(String nom) {
            this.nom = nom;
        }

        public String getPrenom() {
            return prenom;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setPrenom(String prenom) {
            this.prenom = prenom;
        }

        // constructeur
        public User(int id, String nom, String prenom){
            this.id = id;
            this.nom = nom;
            this.prenom = prenom;
        }
    }

