package projeto;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
    	
    	TArvore T1 = new TArvore();
    	TArvoreAVL T2 = new TArvoreAVL();
    	TInfo item = new TInfo();
    	Dialogo dia = new Dialogo();
    	String caminho = System.getProperty("user.dir") + "\\CSV\\parte12021.csv";
    	String caminho2 = System.getProperty("user.dir") + "\\CSV\\parte22021.csv";
    	String linha = "";
    	int indiceColuna = 0; //0 == regiao; 1 == estado; 2 == municipio; 3 == codigo estado(chave); 4 == codigo municipio(chave)
    	//5 == codigo regiao de saude; 6 == nomeRegiaoSaude; 7 == data(chave); 8 == semanaEpi; 9 == populacaoTCU2019; 10 == casosAcumulados
    	//11 == casosNovos; 12 == obitosAcumulados; 13 == obitosNovos; 14 == RecuperadosNovos; 15 == emAcompanhamentoNovos
    	//16 == interior/metropolitana
    	int contCo = 0;
    	String[] auxColunas = new String[17];
    	String[][] colunas = new String[1048577][17]; 
    	int contador = 0;
    	
    	try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(caminho2), "UTF-8"));
			while((linha = br.readLine()) != null) { 
				if(contador > 0) {
					auxColunas = linha.split(";"); //maximo de colunas 17
					
					colunas[contCo] = auxColunas;
					
					TInfo item2 = new TInfo(auxColunas);
					T2.insere(item2);
					
					contCo++;
				}else {
					contador++;
				}
			}
			
			T1.inserirBIN(colunas, 0, contCo-1);
			
		}	catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	TInfo TestePesquisa = new TInfo();
    	TNodo Tnojo;
    	String[] municipioEstadoEscolhido = null;
    	int op = dia.Menu();
    	if(op == 1) {
    		for(int i = 0; i<1048577; i++) {
        		if(colunas[i][1].compareToIgnoreCase("Brasil") > 0) {
        			TestePesquisa.data = colunas[i-1][7];
        			break;
        		}
        	}
    		
    		TestePesquisa.municipio = dia.DialogoEstado();
    		municipioEstadoEscolhido = dia.SelecionaMunicipio(TestePesquisa.municipio);
    		TestePesquisa.municipio = dia.DialogoMunicicpio(municipioEstadoEscolhido, TestePesquisa.municipio);
    		String[] dataAno = TestePesquisa.data.split("-");
    		TestePesquisa.data = dataAno[0] + "-" + dia.EscolheData();
        	for(int i = 0; i<1048577; i++) {
        		if(colunas[i][2].compareToIgnoreCase(TestePesquisa.municipio) == 0) {
        			TestePesquisa.codigoMunicipio = colunas[i][4];
        			TestePesquisa.codigoEstado = colunas[i][3];
        			break;
        		}
        	}
        	System.out.println("#------------------------------------#");
        	Tnojo = T2.pesquisa(TestePesquisa,2);
        	System.out.println(Tnojo);
        	System.out.println("Número de comparações AVL: "+ T2.compAvl);
        	System.out.println("#------------------------------------#");
        	Tnojo = T1.pesquisa(TestePesquisa, 2);
        	System.out.println(Tnojo);
        	System.out.println("Número de comparações ABB: "+ T1.comp);
        	System.out.println("#------------------------------------#");
    	}else if(op == 2) {
    		int totalObitosABB = 0, totalCasosABB = 0;
        	int totalObitosAVL = 0, totalCasosAVL = 0;
        	TestePesquisa.municipio = dia.DialogoEstado();
    		municipioEstadoEscolhido = dia.SelecionaMunicipio(TestePesquisa.municipio);
    		TestePesquisa.municipio = dia.DialogoMunicicpio(municipioEstadoEscolhido, TestePesquisa.municipio);
        	for(int i = 0; i<1048577; i++) {
        		if(colunas[i][2].compareToIgnoreCase(TestePesquisa.municipio) == 0) {
        			TestePesquisa.codigoMunicipio = colunas[i][4];
        			TestePesquisa.codigoEstado = colunas[i][3];
        			break;
        		}
        	}
        	for(int i = 0; i<1048577; i++) {
        		if(colunas[i][1].compareToIgnoreCase("Brasil") > 0) {
        			TestePesquisa.data = colunas[i-1][7];
        			break;
        		}
        	}
	        Tnojo = T1.pesquisa(TestePesquisa,2);
	        int x = Integer.parseInt(Tnojo.item.obitosAcumulados);
	        int y = Integer.parseInt(Tnojo.item.casosAcumulados);
	        totalObitosABB += x;
	        totalCasosABB += y;
	        Tnojo = T2.pesquisa(TestePesquisa,2);
	        x = Integer.parseInt(Tnojo.item.obitosAcumulados);
	        y = Integer.parseInt(Tnojo.item.casosAcumulados);
	        totalObitosAVL += x;
	        totalCasosAVL += y;
        	System.out.println("#------------------------------------#");
        	System.out.println("Município escolhido: "+Tnojo.item.municipio);
        	System.out.println("Total de casos: "+totalCasosAVL);
        	System.out.println("Total de obitos: "+totalObitosAVL);
        	System.out.println("Número de comparações AVL: "+ T2.compAvl);
        	System.out.println();
        	System.out.println("Município escolhido: "+Tnojo.item.municipio);
        	System.out.println("Total de casos: "+totalCasosABB);
        	System.out.println("Total de obitos: "+totalObitosABB);
        	System.out.println("Número de comparações ABB: "+ T1.comp);
        	System.out.println("#------------------------------------#");
    	}
    	else if(op == 3 || op == 4) {
    		int opcao = 6;
        	int inicio = 0, fim = 0;
        	int totalObitosABB = 0, totalCasosABB = 0;
        	int totalObitosAVL = 0, totalCasosAVL = 0;
        	String regiaoEscolhida = "";
        	int x = 0, y = 0;
        	if(op == 3) {
	        	opcao = dia.EscolheRegiao();
        	}else {
        		opcao = 6;
        	}
        	switch(opcao) {
            case 1:
                inicio = 11;
                fim = 17;
                break;
            case 2:
                inicio = 21;
                fim = 29;
                break;  
            case 3:
                inicio = 31;
                fim = 35;
                break;
            case 4:
                inicio = 41;
                fim = 43;
                break;
            case 5:
                inicio = 50;
                fim = 53;
                break;
            case 6:
            	inicio = 76;
            	fim = 76;
            	break;
            default:
                System.out.println("Opção inválida!!!");
                break;
        	}
        	for(int i = 0; i<1048577; i++) {
        		if(colunas[i][1].compareToIgnoreCase("Brasil") > 0) {
        			TestePesquisa.data = colunas[i-1][7];
        			break;
        		}
        	}
        	for (int i = inicio; i <= fim; i++) {
            	TestePesquisa.codigoEstado = Integer.toString(i);
            	for(int c = 0; c<1048577; c++) {
            			if(opcao == 3) {
            				if(colunas[c][7].compareToIgnoreCase(TestePesquisa.data) == 0 ^ colunas[c][3].compareToIgnoreCase(TestePesquisa.codigoEstado) == 0) {
    	            			TestePesquisa.codigoMunicipio = colunas[c][4];
    	            			break;
    	            		}
            			}
            			else if(colunas[c][7].compareToIgnoreCase(TestePesquisa.data) == 0 && colunas[c][3].compareToIgnoreCase(TestePesquisa.codigoEstado) == 0) {
	            			TestePesquisa.codigoMunicipio = colunas[c][4];
	            			break;
	            		}
            }
               Tnojo = T1.pesquisa(TestePesquisa,3);
               if(Tnojo != null) {
            	   x = Integer.parseInt(Tnojo.item.obitosAcumulados);
            	   y = Integer.parseInt(Tnojo.item.casosAcumulados);
               }
               totalObitosABB += x;
               totalCasosABB += y;
               Tnojo = T2.pesquisa(TestePesquisa,3);
               if(Tnojo != null) {
            	   x = Integer.parseInt(Tnojo.item.obitosAcumulados);
            	   y = Integer.parseInt(Tnojo.item.casosAcumulados);
               }
               totalObitosAVL += x;
               totalCasosAVL += y;
               if(Tnojo != null && opcao != 3)
            	   regiaoEscolhida = Tnojo.item.regiao;
               else if(opcao == 3)
            	   regiaoEscolhida = "Sudeste";
            }
        	System.out.println("#------------------------------------#");
        	System.out.println("Região escolhida: "+regiaoEscolhida);
        	System.out.println("Total de casos: "+totalCasosAVL);
        	System.out.println("Total de obitos: "+totalObitosAVL);
        	System.out.println("Número de comparações AVL: "+ T2.compAvl);
        	System.out.println("Região escolhido: "+regiaoEscolhida);
        	System.out.println("Total de casos: "+totalCasosABB);
        	System.out.println("Total de obitos: "+totalObitosABB);
        	System.out.println("Número de comparações ABB: "+ T1.comp);
        	System.out.println("#------------------------------------#");
    	}else if(op == 5) {
    		System.out.println("#--------------Fechando--------------#");
    		return;
    	}
    	else {
    		System.out.println("#-----------Opção-Inválida-----------#");
    		System.out.println("#--------------Fechando--------------#");
    	}
    	System.exit(0);
    }
}
