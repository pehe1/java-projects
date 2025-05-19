import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;       


abstract class Materia{

    //Atributos
    private String nome_materia;
    private int codigo_materia;
    private double avi;

    //Construtor
    public Materia(String nome_materia, int codigo_materia){
        this.nome_materia = nome_materia;
        this.codigo_materia = codigo_materia;
    }

    //Getters e Setters 
    public String getNome(){
        return nome_materia;
    }

    public int getCodigoMateria(){
        return codigo_materia;
    }

    public double getAvi(){
        return avi;
    }

    public void setNome(String nome_materia){
        this.nome_materia = nome_materia;
    }

    public void setCodigoMateria(int codigo_materia){
        this.codigo_materia = codigo_materia;
    }

    public boolean setAvi(double avi){
        if(avi<0 || avi>10){
            System.out.println("A nota 'AVI' deve estar entre 0 e 10, tente novamente.\n");
            return false;
        }   
        this.avi = avi;
        return true;
    }

    //Métodos
    public abstract void adicionarNota(String nomeNota, double nota);

    public abstract double calcularMedia();

    public abstract double quantoFaltaParaSeis(String nomeNotaFaltante);

    @Override
    public abstract String toString();
    
    public abstract String mostrarBoletimMateria();
        
}

class ArquiteturaOrgComputadores extends Materia{
    //Atributos
    private Map<String, Double> notasAoc = new HashMap<>();

    //Construtor
    public ArquiteturaOrgComputadores(){
        super("Arquitetura e Organização de Computadores", 001);
        notasAoc.put("P1", 0.0);
        notasAoc.put("P2", 0.0);
        notasAoc.put("Relatorios1", 0.0);
        notasAoc.put("Relatorios2", 0.0);
    }

    //Getter
    public Map<String, Double> getNotasAoc(){
        return new HashMap<>(notasAoc);
    }  

    //Métodos
    @Override
    public void adicionarNota(String nomeNota, double nota){
        if("AVI".equals(nomeNota)){
            if(setAvi(nota)){
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
            }
        }else if(!notasAoc.containsKey(nomeNota)){
            System.out.println("Nota '" + nomeNota + "' inexistente. Use os nomes válidos: AVI, " + notasAoc.keySet() + "\n");
        }else if(nota<0 || nota>10){
            System.out.println("Nota '" + nomeNota + "' inválida! Insira um valor positivo entre 0 e 10.\n");
        }else{
            notasAoc.put(nomeNota, nota);
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
        }
    }

    @Override
    public double calcularMedia(){

        return 0.28*notasAoc.get("P1") + 0.12*notasAoc.get("Relatorios1") +
        0.12*getAvi() + 0.12*notasAoc.get("Relatorios2") + 0.36*notasAoc.get("P2");
    }

    @Override
    public double quantoFaltaParaSeis(String nomeNotaFaltante){
        double soma = 0.0;
        double pesoNotaFaltante = 0.0;

        switch (nomeNotaFaltante){
            case "P1":
                pesoNotaFaltante = 0.28;
                soma += 0.36*notasAoc.get("P2");
                soma += 0.12*notasAoc.get("Relatorios1");
                soma += 0.12*notasAoc.get("Relatorios2");
                soma += 0.12*getAvi();
                break;
            case "P2":
                pesoNotaFaltante = 0.36;
                soma += 0.28*notasAoc.get("P1");
                soma += 0.12*notasAoc.get("Relatorios1");
                soma += 0.12*notasAoc.get("Relatorios2");
                soma += 0.12*getAvi();
                break;
            case "Relatórios1":
                pesoNotaFaltante = 0.12;
                soma += 0.28*notasAoc.get("P1");
                soma += 0.36*notasAoc.get("P2");
                soma += 0.12*notasAoc.get("Relatorios2");
                soma += 0.12*getAvi();
                break;
            case "Relatórios2":
                pesoNotaFaltante = 0.12;
                soma += 0.28*notasAoc.get("P1");
                soma += 0.36*notasAoc.get("P2");
                soma += 0.12*notasAoc.get("Relatorios1");
                soma += 0.12*getAvi();
                break;
            case "AVI":
                pesoNotaFaltante = 0.12;
                soma += 0.28*notasAoc.get("P1");
                soma += 0.36*notasAoc.get("P2");
                soma += 0.12*notasAoc.get("Relatorios1");
                soma += 0.12*notasAoc.get("Relatorios2");
                break;
            default:
                System.out.println("Nota inexistente. Use os nomes válidos: AVI, " + notasAoc.keySet() + "\n");
                return 0.0;
        }
        return (6-soma)/pesoNotaFaltante;
    }

    @Override
    public String toString(){
        return "\n----Boletim----\nMateria: " + getNome() + " - CM:"+ getCodigoMateria() + "\n" + "P1: " + notasAoc.get("P1") + "\n" +
        "Relatórios 1: " + notasAoc.get("Relatorios1") + "\n" + "P2: " + notasAoc.get("P2") + "\n" +
        "Relatorios2: " + notasAoc.get("Relatorios2") + "\n" + "AVI: " + getAvi() + "\n" + "Média: " + calcularMedia() + "\n----------------\n";
    }

    @Override
    public String mostrarBoletimMateria(){
        return toString();
    }
}

class CalculoDois extends Materia{
    private Map<String, Double> notasCalc = new HashMap<>();

    //Construtor
    public CalculoDois(){
        super("Cálculo Integral e Diferencial II", 002);
        notasCalc.put("P1",0.0);
        notasCalc.put("P2",0.0);
        notasCalc.put("P3",0.0);
        notasCalc.put("P4",0.0);
    }

    //Getter
    public Map<String, Double> getNotasCalc(){
        return new HashMap<>(notasCalc);
    }

    //Métodos
    @Override
    public void adicionarNota(String nomeNota, double nota){
        if("AVI".equals(nomeNota)){
            if(setAvi(nota)){
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
            }    
        }else if(!notasCalc.containsKey(nomeNota)){
            System.out.println("Nota '" + nomeNota + "' inexistente. Use os nomes válidos: AVI, " + notasCalc.keySet() + "\n");
        }else if(nota<0 || nota>10){
            System.out.println("Nota '" + nomeNota + "' inválida! Insira um valor positivo entre 0 e 10.\n");
        }else{
            notasCalc.put(nomeNota, nota);
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
        }
    }

    @Override
    public double calcularMedia(){

        return 0.2*notasCalc.get("P1") + 0.2*notasCalc.get("P2") + 0.24*notasCalc.get("P3")
        + 0.24*notasCalc.get("P4") + 0.12*getAvi();
    }

    @Override
    public double quantoFaltaParaSeis(String nomeNotaFaltante){
        double soma = 0.0;
        double pesoNotaFaltante = 0.0;

        switch (nomeNotaFaltante){
            case "P1":
                pesoNotaFaltante = 0.2;
                soma += 0.2*notasCalc.get("P2");
                soma += 0.24*notasCalc.get("P3");
                soma += 0.24*notasCalc.get("P4");
                soma += 0.12*getAvi();
                break;
            case "P2":
                pesoNotaFaltante = 0.2;
                soma += 0.2*notasCalc.get("P1");
                soma += 0.24*notasCalc.get("P3");
                soma += 0.24*notasCalc.get("P4");
                soma += 0.12*getAvi();
                break;
            case "P3":
                pesoNotaFaltante = 0.24;
                soma += 0.2*notasCalc.get("P1");
                soma += 0.2*notasCalc.get("P2");
                soma += 0.24*notasCalc.get("P4");
                soma += 0.12*getAvi();
                break;
            case "P4":
                pesoNotaFaltante = 0.24;
                soma += 0.2*notasCalc.get("P1");
                soma += 0.2*notasCalc.get("P2");
                soma += 0.24*notasCalc.get("P3");
                soma += 0.12*getAvi();
                break;
            case "AVI":
                pesoNotaFaltante = 0.12;
                soma += 0.2*notasCalc.get("P1");
                soma += 0.2*notasCalc.get("P2");
                soma += 0.24*notasCalc.get("P3");            
                soma += 0.24*notasCalc.get("P4");
                break;
            default:
                System.out.println("Nota inexistente. Use os nomes válidos: AVI, "+ notasCalc.keySet() + "\n");
                return 0.0;
        }
        return (6-soma)/pesoNotaFaltante;
    }

    @Override
    public String toString(){
        return "\n----Boletim----\nMateria: " + getNome() + " - CM:"+ getCodigoMateria() + "\n" + "P1: " + notasCalc.get("P1") + "\n" +
        "P2: " + notasCalc.get("P2") + "\n" + "P3: " + notasCalc.get("P3") + "\n" +
        "P4: " + notasCalc.get("P4") + "\n" + "AVI: " + getAvi() + "\n" + "Média: " + calcularMedia() + "\n----------------\n";
    }

    @Override
    public String mostrarBoletimMateria(){
        return toString();
    }
}

class EletricidadeAplicada extends Materia{

    //Atributos
    private Map<String, Double> notasEle = new HashMap<>();

    //Getter 
    public Map<String, Double> getNotasEle(){
        return new HashMap<>(notasEle);
    }

    //Construtor
    public EletricidadeAplicada(){
        super("Eletricidade Aplicada", 003);
        notasEle.put("P1",0.0);
        notasEle.put("Relatorio1",0.0);
        notasEle.put("Relatorio2",0.0);     
        notasEle.put("P2",0.0);
        notasEle.put("Relatorio3",0.0);
        notasEle.put("Relatorio4",0.0);
    }

    //Métodos

    @Override 
    public void adicionarNota(String nomeNota, double nota){
        if("AVI".equals(nomeNota)){
            if(setAvi(nota)){
                System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
            }
        }else if("Relatorio1".equals(nomeNota) || "Relatorio2".equals(nomeNota) || "Relatorio3".equals(nomeNota) || "Relatorio4".equals(nomeNota)){
            if(nota<0 || nota>5){
                System.out.println("Nota '" + nomeNota + "' inválida. Insira um valor positivo entre 0 e 5, para relatórios.\n");
            }else{
                notasEle.put(nomeNota, nota);
            }
        }else if(!notasEle.containsKey(nomeNota)){
            System.out.println("Nota '" + nomeNota + "' inexistente. Use os nomes válidos: AVI, " + notasEle.keySet() + "\n");
        }else if(nota<0 || nota>10){
            System.out.println("Nota '" + nomeNota + "' inválida! Insira um valor positivo entre 0 e 10.\n");
        }else{
            notasEle.put(nomeNota, nota);
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
        }
    }

    @Override
    public double calcularMedia(){
        return 0.32*notasEle.get("P1") + 0.08*(notasEle.get("Relatorio1") + notasEle.get("Relatorio2")) +
        0.36*notasEle.get("P2") + 0.12*(notasEle.get("Relatorio3") + notasEle.get("Relatorio4")) + 0.12*getAvi();
    }

    @Override
    public double quantoFaltaParaSeis(String nomeNotaFaltante){
        double soma = 0.0;
        double pesoNotaFaltante = 0.0;

        switch(nomeNotaFaltante){
            case "P1":
                pesoNotaFaltante = 0.32;
                soma += 0.08*(notasEle.get("Relatorio1") + notasEle.get("Relatorio2"));
                soma += 0.12*(notasEle.get("Relatorio3") + notasEle.get("Relatorio4"));
                soma += 0.36*notasEle.get("P2");
                soma += 0.12*getAvi();
                break;
            case "P2":
                pesoNotaFaltante = 0.36;
                soma += 0.32*notasEle.get("P1");
                soma += 0.08*(notasEle.get("Relatorio1") + notasEle.get("Relatorio2"));
                soma += 0.12*(notasEle.get("Relatorio3") + notasEle.get("Relatorio4"));
                soma += 0.12*getAvi();
                break;
            case "Relatorio4":
                pesoNotaFaltante = 0.12;
                soma += 0.32*notasEle.get("P1");
                soma += 0.08*(notasEle.get("Relatorio1") + notasEle.get("Relatorio2"));
                soma += 0.36*notasEle.get("P2");
                soma += 0.12*notasEle.get("Relatorio3");
                soma += 0.12*getAvi();
                break;
            case "Relatorio3":
                pesoNotaFaltante = 0.12;
                soma += 0.32*notasEle.get("P1");
                soma += 0.08*(notasEle.get("Relatorio1") + notasEle.get("Relatorio2"));
                soma += 0.36*notasEle.get("P2");
                soma += 0.12*notasEle.get("Relatorio4");
                soma += 0.12*getAvi();
                break;
            case "Relatorio2":
                pesoNotaFaltante = 0.08;
                soma += 0.32*notasEle.get("P1");
                soma += 0.08*notasEle.get("Relatorio1");
                soma += 0.36*notasEle.get("P2");
                soma += 0.12*(notasEle.get("Relatorio3") + notasEle.get("Relatorio4"));
                soma += 0.12*getAvi();
                break;
            case "Relatorio1":
                pesoNotaFaltante = 0.08;
                soma += 0.32*notasEle.get("P1");
                soma += 0.08*notasEle.get("Relatorio2");
                soma += 0.36*notasEle.get("P2");
                soma += 0.12*(notasEle.get("Relatorio3") + notasEle.get("Relatorio4"));
                soma += 0.12*getAvi();
                break;
            case "AVI":
                pesoNotaFaltante = 0.12;
                soma += 0.32*notasEle.get("P1");
                soma += 0.08*(notasEle.get("Relatorio1") + notasEle.get("Relatorio2"));
                soma += 0.36*notasEle.get("P2");
                soma += 0.12*(notasEle.get("Relatorio3") + notasEle.get("Relatorio4"));
                break;
            default:
                System.out.println("Nota inexistente. Use os nomes válidos: AVI, "+ notasEle.keySet() + "\n");
                return 0.0;
        }
        return (6-soma)/pesoNotaFaltante;
    }
    
    @Override
    public String toString(){
        return "\n----Boletim----\nMateria: " + getNome() + " - CM:"+ getCodigoMateria() + "\n" + "P1: " + notasEle.get("P1") + "\n" +
        "Relatório 1 (L1): " + notasEle.get("Relatorio1") + "\n" + "Relatório 2 (L2): " + notasEle.get("Relatorio2") + "\n" +
        "P2: " + notasEle.get("P2") + "\n" + "Relatório 3 (L3): " + notasEle.get("Relatorio3") + "\n" + "Relatório 4 (L4): " +
        notasEle.get("Relatorio4") + "\n" + "AVI: " + getAvi() + "\n" + "Média: " + calcularMedia() + "\n----------------\n";
    }

    @Override
    public String mostrarBoletimMateria(){
        return toString();
    }
}

class Estatistica extends Materia{
    //Atributos
    private Map<String, Double> notasEst = new HashMap<>();

    //Getter
    public Map<String, Double> getNotasEst(){
        return new HashMap<>(notasEst);
    }

    //Construtor
    public Estatistica(){
        super("Estatística I", 004);
        notasEst.put("P1",0.0);
        notasEst.put("P2",0.0);
        notasEst.put("P3",0.0);
        notasEst.put("P4", 0.0);
    }

    //Métodos
    @Override
    public void adicionarNota(String nomeNota, double nota){
        if("AVI".equals(nomeNota)){
            if(setAvi(nota)){
                System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
            }
        }else if(!notasEst.containsKey(nomeNota)){
            System.out.println("Nota '" + nomeNota + "' inexistente. Use os nomes válidos: AVI, " + notasEst.keySet() + "\n");
        }else if(nota<0 || nota>10){
            System.out.println("Nota '" + nomeNota + "' inválida! Insira um valor positivo entre 0 e 10.\n");
        }else{
            notasEst.put(nomeNota, nota);
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
        }
    }

    @Override
    public double calcularMedia(){
        return 0.16*notasEst.get("P1") + 0.24*notasEst.get("P2") + 0.18*notasEst.get("P3") + 0.3*notasEst.get("P4") + 0.12*getAvi();
    }

    @Override
    public double quantoFaltaParaSeis(String nomeNotaFaltante){
        double soma = 0.0;
        double pesoNotaFaltante = 0.0;

        switch(nomeNotaFaltante){
            case "P1":
                pesoNotaFaltante = 0.16;
                soma += 0.24*notasEst.get("P2");
                soma += 0.18*notasEst.get("P3");
                soma += 0.3*notasEst.get("P4");
                soma += 0.12*getAvi();
                break;
            case "P2":
                pesoNotaFaltante = 0.24;
                soma += 0.16*notasEst.get("P1");
                soma += 0.18*notasEst.get("P3");
                soma += 0.3*notasEst.get("P4");
                soma += 0.12*getAvi();
                break;
            case "P3":
                pesoNotaFaltante = 0.18;
                soma += 0.16*notasEst.get("P1");
                soma += 0.24*notasEst.get("P2");
                soma += 0.3*notasEst.get("P4");
                soma += 0.12*getAvi();
                break;
            case "P4":
                pesoNotaFaltante = 0.3;
                soma += 0.16*notasEst.get("P1");
                soma += 0.24*notasEst.get("P2");
                soma += 0.18*notasEst.get("P3");
                soma += 0.12*getAvi();
                break;
            case "AVI":
                pesoNotaFaltante = 0.12;
                soma += 0.16*notasEst.get("P1");
                soma += 0.24*notasEst.get("P2");
                soma += 0.18*notasEst.get("P3");
                soma += 0.3*notasEst.get("P4");
                break;
            default:
                System.out.println("Nota '" + nomeNotaFaltante + "' inexistente. Use os nomes válidos: AVI, " + notasEst.keySet() + "\n");
                return 0.0;
        }
        return (6-soma)/pesoNotaFaltante;
    }

    @Override
    public String toString(){
        return "\n----Boletim----\nMateria: " + getNome() + " - CM:"+ getCodigoMateria() + "\n" + "P1: " + notasEst.get("P1") + "\n" +
        "P2: " + notasEst.get("P2") + "\n" + "P3: " + notasEst.get("P3") + "\n" +
        "P4: " + notasEst.get("P4") + "\n" + "AVI: " + getAvi() + "\n" + "Média: " + calcularMedia() + "\n----------------\n";
    }
    
    @Override
    public String mostrarBoletimMateria(){
        return toString();
    }
}

class EstruturaDeDados extends Materia{
    private Map<String, Double> notasEd = new HashMap<>();

    //Getter
    public Map<String, Double> getNotasEd(){
        return new HashMap<>(notasEd);
    }

    //Construtor
    public EstruturaDeDados(){
        super("Estrutura de Dados", 005);
        notasEd.put("PT1",0.0);
        notasEd.put("PT2",0.0);
        notasEd.put("PP1",0.0);
        notasEd.put("Projeto",0.0);
    }

    //Métodos
    @Override
    public void adicionarNota(String nomeNota, double nota){
        if("AVI".equals(nomeNota)){
            if(setAvi(nota)){
                System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
            }
        }else if(!notasEd.containsKey(nomeNota)){
            System.out.println("Nota '" + nomeNota + "' inexistente. Use os nomes válidos: AVI, " + notasEd.keySet() + "\n");
        }else if(nota<0 || nota>10){
            System.out.println("Nota '" + nomeNota + "' inválida. Insira um valor positivo entre 0 e 10.\n");
        }else{
            notasEd.put(nomeNota, nota);
            System.out.println("Nota '" + nomeNota+ "' inserida com sucesso!\n");
        }
    }
    @Override
    public double calcularMedia(){
        return 0.28*notasEd.get("PT1") + 0.12*notasEd.get("PP1") + 0.12*notasEd.get("Projeto") + 0.12*getAvi() +
        0.36*notasEd.get("PT2");
    }

    @Override
    public double quantoFaltaParaSeis(String nomeNotaFaltante){
        double soma = 0.0;
        double pesoNotaFaltante = 0.0;

        switch(nomeNotaFaltante){
            case "PT1":
                pesoNotaFaltante = 0.28;
                soma += 0.12*notasEd.get("PP1");
                soma += 0.36*notasEd.get("PT2");
                soma += 0.12*notasEd.get("Projeto");
                soma += 0.12*getAvi();                
                break;
            case "PP1":
                pesoNotaFaltante = 0.12;
                soma += 0.28*notasEd.get("PT1");
                soma += 0.36*notasEd.get("PT2");
                soma += 0.12*notasEd.get("Projeto");
                soma += 0.12*getAvi();                
                break;
            case "Projeto":
                pesoNotaFaltante = 0.12;
                soma += 0.28*notasEd.get("PT1");
                soma += 0.36*notasEd.get("PT2");
                soma += 0.12*notasEd.get("PP1");
                soma += 0.12*getAvi();
                break;
            case "PT2":
                pesoNotaFaltante = 0.36;
                soma += 0.28*notasEd.get("PT1");
                soma += 0.12*notasEd.get("PP1");
                soma += 0.12*notasEd.get("Projeto");
                soma += 0.12*getAvi();
                break;
            case "AVI":
                pesoNotaFaltante = 0.12;
                soma += 0.28*notasEd.get("PT1");
                soma += 0.12*notasEd.get("PP1");
                soma += 0.12*notasEd.get("Projeto");
                soma += 0.36*notasEd.get("PT2");
                break;
            default:
                System.out.println("Nota inexistente. Use os nomes válidos: AVI, "+ notasEd.keySet() + "\n");
                return 0.0;
           
        }
        return (6-soma) / pesoNotaFaltante;
    }

    @Override
    public String toString(){
        return "\n----Boletim----\nMateria: " + getNome() + " - CM:"+ getCodigoMateria() + "\n" + "Prova teórica 1: " + notasEd.get("PT1") + "\n" +
        "Prova teórica 2: " + notasEd.get("PT2") + "\n" + "Prova prática: " + notasEd.get("PP1") + "\n" +
        "Projeto: " + notasEd.get("Projeto") + "\n" + "AVI: " + getAvi() + "\n" + "Média: " + calcularMedia() + "\n----------------\n";
    }
    
    @Override
    public String mostrarBoletimMateria(){
        return toString();
    }

}

class ProgramacaoOrientadaObjeto extends Materia{
    //Atributos
    private Map<String, Double> notasPoo = new HashMap<>();

    //Construtor
    public ProgramacaoOrientadaObjeto(){
        super("Programação Orientada a Objetos", 006);
        notasPoo.put("PT1",0.0);
        notasPoo.put("PP",0.0);
        notasPoo.put("Projeto",0.0);
        notasPoo.put("PT2",0.0);
    }

    //Getter
    public Map<String, Double> getNotasPoo(){
        return new HashMap<>(notasPoo);
    }

    //Métodos
    @Override
    public void adicionarNota(String nomeNota, double nota){
        if("AVI".equals(nomeNota)){
            if(setAvi(nota)){
                System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
            }
        }else if(!notasPoo.containsKey(nomeNota)){
            System.out.println("Nota '" + nomeNota + "' inexistente. Use os nomes válidos: AVI, " + notasPoo.keySet() + "\n");
        }else if(nota<0 || nota>10){
            System.out.println("Nota '" + nomeNota + "' inválida! Insira um valor positivo entre 0 e 10.\n");
        }else{
            notasPoo.put(nomeNota, nota);
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
        }
    }

    @Override
    public double calcularMedia(){
        return 0.28*notasPoo.get("PT1") + 0.12*notasPoo.get("PP") + 0.2*notasPoo.get("Projeto") +
        0.36*notasPoo.get("PT2") + 0.12*getAvi();
    }
    
    @Override
    public double quantoFaltaParaSeis(String notaFaltante){
        double soma = 0.0;
        double pesoNotaFaltante = 0.0;

        switch(notaFaltante){
            case "PT1":
                pesoNotaFaltante = 0.28;
                soma += 0.12*notasPoo.get("PP");
                soma += 0.2*notasPoo.get("Projeto");
                soma += 0.36*notasPoo.get("PT2");
                soma += 0.12*getAvi();
                break;
            case "PP":
                pesoNotaFaltante = 0.12;
                soma += 0.28*notasPoo.get("PT1");
                soma += 0.2*notasPoo.get("Projeto");
                soma += 0.36*notasPoo.get("PT2");
                soma += 0.12*getAvi();
                break;
            case "Projeto":
                pesoNotaFaltante = 0.2;
                soma += 0.28*notasPoo.get("PT1");
                soma += 0.12*notasPoo.get("PP");
                soma += 0.36*notasPoo.get("PT2");
                soma += 0.12*getAvi();
                break;
            case "PT2":
                pesoNotaFaltante = 0.36;
                soma += 0.28*notasPoo.get("PT1");
                soma += 0.12*notasPoo.get("PP");
                soma += 0.2*notasPoo.get("Projeto");
                soma += 0.12*getAvi();
                break;
            case "AVI":
                pesoNotaFaltante = 0.12;
                soma += 0.28*notasPoo.get("PT1");
                soma += 0.12*notasPoo.get("PP");
                soma += 0.2*notasPoo.get("Projeto");
                soma += 0.36*notasPoo.get("PT2");
        }
        return (6-soma) / pesoNotaFaltante;
    }

    @Override
    public String toString(){
        return "\n----Boletim----\nMateria: " + getNome() + " - CM:"+ getCodigoMateria() + "\n" + "Prova teórica 1: " + notasPoo.get("PT1") + "\n" +
        "Prova prática: " + notasPoo.get("PP") + "\n" + "Projeto: " + notasPoo.get("Projeto") + "\n" +
        "Prova teórica 2: " + notasPoo.get("PT2") + "\n" + "AVI: " + getAvi() + "\n" + "Média: " + calcularMedia() + "\n----------------\n";
    }

    @Override
    public String mostrarBoletimMateria(){
        return toString();
    }
    
}

public class OperadorNotas{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        ArquiteturaOrgComputadores aoc = new ArquiteturaOrgComputadores();
        CalculoDois c2 = new CalculoDois();
        EletricidadeAplicada ea = new EletricidadeAplicada();
        Estatistica est = new Estatistica();
        EstruturaDeDados ed = new EstruturaDeDados();
        ProgramacaoOrientadaObjeto poo = new ProgramacaoOrientadaObjeto();

        //Teste Arquitetura
        aoc.adicionarNota("P1", 6.4);
        aoc.adicionarNota("P2", 6.5);
        aoc.adicionarNota("Relatorios1", 8.0);
        aoc.adicionarNota("Relatorios2", 9.0);
        aoc.adicionarNota("AVI", -1);
        aoc.adicionarNota("teste1", 10);

        System.out.println("Quanto faltaria para 6 se não tivesse feito a AVI: " + aoc.quantoFaltaParaSeis("AVI"));

        System.out.println(aoc.mostrarBoletimMateria());

        //Teste Cálculo 2
        c2.adicionarNota("P1", 5.0);
        c2.adicionarNota("P2", 6.0);
        c2.adicionarNota("P3", 4.0);
        c2.adicionarNota("P4", 5.5);
        c2.adicionarNota("AVI", -1);

        System.out.println("Quanto faltaria para 6 se não tivesse feito a P4: " + c2.quantoFaltaParaSeis("P4"));

        System.out.println(c2.mostrarBoletimMateria());

        //Teste Eletricidade
        ea.adicionarNota("P1", 7.5);
        ea.adicionarNota("Relatorio1", 6);
        ea.adicionarNota("Relatorio2", 8);
        ea.adicionarNota("Relatorio3", 9.5);
        ea.adicionarNota("Relatorio4", 5);
        ea.adicionarNota("P2", 8.3);
        ea.adicionarNota("AVI", 6.2);
        
        System.out.println(ea.mostrarBoletimMateria());

        System.out.println("Quanto faltaria para 6 se não tivesse feito a P2: " + ea.quantoFaltaParaSeis("P2"));

        //Teste Estatística
        est.adicionarNota("P1", 4.0);
        est.adicionarNota("P2", 6);
        est.adicionarNota("P3", 10);
        est.adicionarNota("P4", 7);
        est.adicionarNota("AVI", 6);
        est.adicionarNota("teste", 10);
        est.adicionarNota("P2", -1);
        est.adicionarNota("AVI", 11);

        System.out.println(est.mostrarBoletimMateria());

        System.out.println("Quanto faltaria para 6 não tivesse feito a P4: " + est.quantoFaltaParaSeis("P4"));

        //Teste Estrutura de Dados 
        ed.adicionarNota("PT1", 9);
        ed.adicionarNota("PP1", 6.5);
        ed.adicionarNota("PT2", 8);
        ed.adicionarNota("Projeto", 8.5);
        ed.adicionarNota("AVI", 5.0);
        ed.adicionarNota("teste", 6);
        ed.adicionarNota("Projeto", 87);

        System.out.println(ed.mostrarBoletimMateria());

        System.out.println("Quanto faltaria para 6 se não tivesse feito a PT2: "+ ed.quantoFaltaParaSeis("PT2"));

        //Teste Programação Orientada a Objetos
        poo.adicionarNota("PT1", 7);
        poo.adicionarNota("PP1", 8);    
        poo.adicionarNota("PT2", 9);
        poo.adicionarNota("PT3", 8);
        poo.adicionarNota("PT4", 7);
        poo.adicionarNota("AVI", 6);
        poo.adicionarNota("teste", 10);
        poo.adicionarNota("PP1", -1); 

        System.out.println(poo.mostrarBoletimMateria());

        System.out.println("Quanto faltaria para 6 se não tivesse feito a PP1: " + poo.quantoFaltaParaSeis("PP1"));
        
        scanner.close();
    }
}
