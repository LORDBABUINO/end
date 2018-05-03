package ninja.soumuito.projetofinal;


import java.util.ArrayList;

/**
 * Created by root on 26/04/18.
 */

public class Perfil {



        public String nome ;
        public String matricula;
        public String curso;
        public String campus;
    public ArrayList<Interesse> interesses = new ArrayList<>();



        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String getMatricula() {
            return matricula;
        }

        public void setMatricula(String matricula) {
            this.matricula = matricula;
        }

        public String getCurso() {
            return curso;
        }

        public void setCurso(String curso) {
            this.curso = curso;
        }

        public String getCampus() {
            return campus;
        }

        public void setCampus(String campus) {
            this.campus = campus;
        }

    public void setInteresses(ArrayList<Interesse> interesses) {
        this.interesses = interesses;}


    }



