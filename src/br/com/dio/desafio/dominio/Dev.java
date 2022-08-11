package br.com.dio.desafio.dominio;

import java.util.*;

public class Dev {
    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();    

    public void inscreverBootcamp(Bootcamp bootcamp){
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        if(conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
        } else {
            System.err.println("Voc√™ n√£o est√° matriculado em nenhum conte√∫do!");
        }
    }

    public double calcularTotalXp() {
        Iterator<Conteudo> iterator = this.conteudosConcluidos.iterator();
        double soma = 0;
        while(iterator.hasNext()){
            double next = iterator.next().calcularXp();
            soma += next;
        }
        return soma;        
    }


    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return this.conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return this.conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }
    
    public String getCertificado (Curso c) {
    	if (this.conteudosConcluidos.contains(c)) {
    		return "\nA DIO certifica que o Aluno: " + this.getNome() +
    				"\nconcluiu o " + c.getTitulo() + 
    				"\ncom carga hor·ria de: " +c.getCargaHoraria() +"h";    				
    	}
    	else if (this.conteudosInscritos.contains(c)) {
    		return "O aluno: "  + this.getNome() +
    				"\nainda n„o concluiu o "+ c.getTitulo() +"\n";
    	}
    	else return "Aluno n„o inscrito no " + c.getTitulo() +"\n";
	}  
    
    public String gelAllCertificados() {
    	if (this.conteudosConcluidos.isEmpty()) {
    		return "Aluno n„o possui certificado";
    	} 
    	else {
	    	Iterator<Conteudo> iterator = this.conteudosConcluidos.iterator();   		
	    	while(iterator.hasNext()){
	    		Curso c = (Curso) iterator.next();
	    		return "\nA DIO certifica que o Aluno: " + this.getNome() +
	    				"\nconcluiu o " + c.getTitulo()  +
	    				"\ncom carga hor·ria de: " + c.getCargaHoraria() +"h";  	    				
			}
    	}
    	return "";    	
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}
