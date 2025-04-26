import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

//TODO Fazer o resto das matérias 
//todo Criar a classe aluno

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

    public void setAvi(double avi){
        if(avi<0 || avi>10){
            System.out.println("A nota 'AVI' deve estar entre 0 e 10.");
            return;
        }   
        this.avi = avi;
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
    public void adicionarNota(String nomeNota, double nota){
        if("AVI".equals(nomeNota)){
            setAvi(nota);
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
        }else if(!notasAoc.containsKey(nomeNota)){
            System.out.println("Nota '" + nomeNota + "' inexistente. Use os nomes válidos: AVI, " + notasAoc.keySet() + "\n");
        }else if(nota<0 || nota>10){
            System.out.println("Nota '" + nomeNota + "' inválida! Insira um valor positivo entre 0 e 10.\n");
        }else{
            notasAoc.put(nomeNota, nota);
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
        }
    }

    public double calcularMedia(){

        return 0.28*notasAoc.get("P1") + 0.12*notasAoc.get("Relatorios1") +
        0.12*getAvi() + 0.12*notasAoc.get("Relatorios2") + 0.36*notasAoc.get("P2");
    }

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
            default:
                System.out.println("Nota inexistente. Use os nomes válidos: AVI, "+ notasAoc.keySet() + "\n");
                return 0.0;
        }
        double notaFaltante = (6-soma)/pesoNotaFaltante;
        return notaFaltante;
    }

    public String toString(){
        return "\n----Boletim----\nMateria: " + getNome() + " - CM:"+ getCodigoMateria() + "\n" + "P1: " + notasAoc.get("P1") + "\n" +
        "Relatórios 1: " + notasAoc.get("Relatorios1") + "\n" + "P2: " + notasAoc.get("P2") + "\n" +
        "Relatorios2: " + notasAoc.get("Relatorios2") + "\n" + "AVI: " + getAvi() + "\n" + "Média: " + calcularMedia() + "\n----------------\n";
    }

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
    public void adicionarNota(String nomeNota, double nota){
        if("AVI".equals(nomeNota)){
            setAvi(nota);
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
        }else if(!notasCalc.containsKey(nomeNota)){
            System.out.println("Nota '" + nomeNota + "' inexistente. Use os nomes válidos: AVI, " + notasCalc.keySet() + "\n");
        }else if(nota<0 || nota>10){
            System.out.println("Nota '" + nomeNota + "' inválida! Insira um valor positivo entre 0 e 10.\n");
        }else{
            notasCalc.put(nomeNota, nota);
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
        }
    }

    public double calcularMedia(){

        return 0.2*notasCalc.get("P1") + 0.2*notasCalc.get("P2") + 0.24*notasCalc.get("P3")
        + 0.24*notasCalc.get("P4") + 0.12*getAvi();
    }

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
            default:
                System.out.println("Nota inexistente. Use os nomes válidos: AVI, "+ notasCalc.keySet() + "\n");
                return 0.0;
        }
        double notaFaltante = (6-soma)/pesoNotaFaltante;
        return notaFaltante;
    }

    public String toString(){
        return "\n----Boletim----\nMateria: " + getNome() + " - CM:"+ getCodigoMateria() + "\n" + "P1: " + notasCalc.get("P1") + "\n" +
        "P2: " + notasCalc.get("P2") + "\n" + "P3: " + notasCalc.get("P3") + "\n" +
        "P4: " + notasCalc.get("P4") + "\n" + "AVI: " + getAvi() + "\n" + "Média: " + calcularMedia() + "\n----------------\n";
    }

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
    public void adicionarNota(String nomeNota, double nota){
        if("AVI".equals(nomeNota)){
            setAvi(nota);
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
        }else if(!notasEle.containsKey(nomeNota)){
            System.out.println("Nota '" + nomeNota + "' inexistente. Use os nomes válidos: "+" AVI, " + notasEle.keySet() + "\n");
        }else if(nota<0 || nota>10){
            System.out.println("Nota '" + nomeNota + "' inválida! Insira um valor positivo entre 0 e 10.\n");
        }else{
            notasEle.put(nomeNota, nota);
            System.out.println("Nota '" + nomeNota + "' inserida com sucesso!\n");
        }
    }

    public double calcularMedia(){
        return 0.32*notasEle.get("P1") + 0.08*notasEle.get("Relatorio1") + 0.08*notasEle.get("Relatorio2") +
        0.36*notasEle.get("P2") + 0.12*notasEle.get("Relatorio3") + 0.12*notasEle.get("Relatorio4") + 0.12*getAvi();
    }

    public double quantoFaltaParaSeis(String nomeNotaFaltante){
        double soma = 0.0;
        double pesoNotaFaltante = 0.0;

        switch(nomeNotaFaltante){
            case "P1":
                pesoNotaFaltante = 0.32;
                soma += 0.08*notasEle.get("Relatorio1");
                soma += 0.08*notasEle.get("Relatorio2");
                soma += 0.12*notasEle.get("Relatorio3");
                soma += 0.12*notasEle.get("Relatorio4");
                soma += 0.36*notasEle.get("P2");
                soma += 0.12*getAvi();
                break;
            case "Relatorio4":
                pesoNotaFaltante = 0.12;
                soma += 0.32*notasEle.get("P1");
                soma += 0.08*notasEle.get("Relatorio1");
                soma += 0.08*notasEle.get("Relatorio2");
                soma += 0.36*notasEle.get("P2");
                soma += 0.12*notasEle.get("Relatorio3");
                soma += 0.12*getAvi();
                break;
            case "P2":
                pesoNotaFaltante = 0.36;
                soma += 0.32*notasEle.get("P1");
                soma += 0.08*notasEle.get("Relatorio1");
                soma += 0.08*notasEle.get("Relatorio2");
                soma += 0.12*notasEle.get("Relatorio3");
                soma += 0.12*notasEle.get("Relatorio4");
                soma += 0.12*getAvi();
                break;
            default:
                System.out.println("Nota inexistente. Use os nomes válidos: AVI, "+ notasEle.keySet() + "\n");
                return 0.0;
        }
        double notaFaltante = (6-soma)/pesoNotaFaltante;
        return notaFaltante;
    }
    
    public String toString(){
        return "\n----Boletim----\nMateria: " + getNome() + " - CM:"+ getCodigoMateria() + "\n" + "P1: " + notasEle.get("P1") + "\n" +
        "Relatório 1 (L1): " + notasEle.get("Relatorio1") + "\n" + "Relatório 2 (L2): " + notasEle.get("Relatorio2") + "\n" +
        "P2: " + notasEle.get("P2") + "\n" + "Relatório 3 (L3): " + notasEle.get("Relatorio3") + "\n" + "Relatório 4 (L4): " +
        notasEle.get("Relatorio4") + "\n" + "AVI: " + getAvi() + "\n" + "Média: " + calcularMedia() + "\n----------------\n";
    }

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

        aoc.adicionarNota("P1", 6.4);
        aoc.adicionarNota("P2", 6.5);
        aoc.adicionarNota("Relatorios1", 8.0);
        aoc.adicionarNota("Relatorios2", 9.0);
        aoc.adicionarNota("AVI", 5.5);
        aoc.adicionarNota("teste1", 10);

        System.out.println("Quanto falta para 6 na P1: " + aoc.quantoFaltaParaSeis("P1"));

        System.out.println(aoc.mostrarBoletimMateria());

        c2.adicionarNota("P1", 5.0);
        c2.adicionarNota("P2", 6.0);
        c2.adicionarNota("P3", 4.0);
        c2.adicionarNota("P4", 5.5);
        c2.adicionarNota("AVI", -1);

        System.out.println("Quanto falta para 6 na P4: " + c2.quantoFaltaParaSeis("P4"));

        System.out.println(c2.mostrarBoletimMateria());


        ea.adicionarNota("P1", 7.5);
        ea.adicionarNota("Relatorio1", 6);
        ea.adicionarNota("Relatorio2", 8);
        ea.adicionarNota("Relatorio3", 9.5);
        ea.adicionarNota("Relatorio4", 5);
        ea.adicionarNota("P2", 8.3);
        ea.adicionarNota("AVI", 6.2);
        
        System.out.println(ea.mostrarBoletimMateria());

        System.out.println("Quanto falta para 6 na P2: " + ea.quantoFaltaParaSeis("P2"));

        scanner.close();
    }
}
