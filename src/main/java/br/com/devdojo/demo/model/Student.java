package br.com.devdojo.demo.model;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity
public class Student extends AbstractEntity {

    @NotEmpty(message = "O campo nome do estudante é obrigatório")
    private String name;

    @NotBlank
    @Email(message = "Digite um e-mail válido")
    private String email;

    public Student() {

    }

    public Student(@NotEmpty String name, @NotBlank @Email String email) {
        this.name = name;
        this.email = email;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
