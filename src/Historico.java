import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Historico {
    public static List<String> getHistorico(String caminhoArquivo) {
        List<String> historico = new ArrayList<>();
        BufferedReader leitor = null;

        try {
            leitor = new BufferedReader(new FileReader(new File(caminhoArquivo)));
            String site;

            while ((site = leitor.readLine()) != null) {
                historico.add(site);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo: " + e.getMessage());
        } finally {
            try {
                if (leitor != null) {
                    leitor.close();
                }
            } catch (IOException e) {
                System.err.println("Erro ao fechar o leitor: " + e.getMessage());
            }
        }

        return historico;
    }

    public static void exibirHistorico(List<String> historico) {
        for (int i = 0; i < historico.size(); i++) {
            System.out.println("Site: " + historico.get(i));
        }
    }

    public static void main(String[] args) {
        String caminhoArquivo = "C:/Users/Public/Documents/Historico/Lista_URL.txt";
        List<String> historico = getHistorico(caminhoArquivo);
        Scanner scanner = new Scanner(System.in);
        int opcaoExit = 999;
        while (opcaoExit != 0) {
            System.out.println("O que deseja fazer");
            System.out.println("---------------");
            System.out.println("1: Visualizar Histórico");
            System.out.println("2: Excluir site em Histórico");
            System.out.println("3: Pesquisar site em Histórico");
            System.out.println("0: Sair");
            int opcaoHist = scanner.nextInt();
            scanner.nextLine();
            opcaoExit = opcaoHist;
            if (opcaoHist == 1) {
                exibirHistorico(historico);
            } else if (opcaoHist == 2) {
                System.out.print("Digite a URL a ser excluída: ");
                String excluirUrl = scanner.nextLine();
                excluirHistorico(excluirUrl, historico);
            } else if (opcaoHist == 3) {
                System.out.print("Pesquise uma URL: ");
                String urlToShow = scanner.nextLine();
                exibirSite(urlToShow, historico);
            } else if (opcaoHist == 0) {
                opcaoExit = opcaoHist;
            } else {
                System.out.println("OPCAO INVALIDA!");
            }
        }
        scanner.close();
    }

    public static void excluirHistorico(String urlToExclude, List<String> historico) {
        if (historico.contains(urlToExclude)) {
            historico.remove(urlToExclude);
            System.out.println("Site removido: " + urlToExclude);
        } else {
            System.out.println("O site não foi encontrado: " + urlToExclude);
        }
    }

    public static void exibirSite(String urlToShow, List<String> historico) {
        if (historico.contains(urlToShow)) {
            System.out.println("Site encontrado: " + urlToShow);
        } else {
            System.out.println("O site não foi encontrado: " + urlToShow);
        }
    }
}