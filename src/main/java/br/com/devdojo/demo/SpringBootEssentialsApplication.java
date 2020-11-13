package br.com.devdojo.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@SpringBootApplication
public class SpringBootEssentialsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootEssentialsApplication.class, args);
    }

//    public static void main(String[] args) {
//        int qtd_participantes = 6;
//        int qtde_equipes = 2;
//
//        if (qtd_participantes % qtde_equipes == 0) {
//
//            List<String> strings = Arrays.asList("1", "2", "3", "4", "5", "6");
//
//            // Embaralha lista
//            Collections.shuffle(strings);
//
//            //Depois de embaralhar
//            System.out.println(strings);
//
//            Collections.shuffle(strings);
//
//            //Depois de embaralhar novamente
//            System.out.println(strings);
//
//            List<String> head = strings.subList(0, 3);
//            System.out.println(head);
//
//            List<String> tail = strings.subList(3, 6);
//            System.out.println(tail);
//
//        } else {
//            System.out.println("!= 0");
//        }

        List<Pessoa> pessoas = Arrays.asList(new Pessoa("Ana", 21),
                new Pessoa("Felipe", 24),
                new Pessoa("Felipe", 22));

//        // Ordenando uma lista de objetos por mais de um critério
//        pessoas.sort((x, y) -> Comparator.comparing(Pessoa::getNome)
//                .thenComparing(Pessoa::getIdade)
//                .compare(y, x));
//
//        pessoas.forEach(System.out::println);


//    }

}

class Pessoa {

    private String nome;
    private int idade;


    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}