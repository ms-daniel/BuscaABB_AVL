package projeto;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;

public class Dialogo {
	private String caminhoImgEstado = System.getProperty("user.dir") + "\\arq\\";
	private String cam = System.getProperty("user.dir") + "\\arq\\lista.csv";
	private ImageIcon icone = new ImageIcon(System.getProperty("user.dir") + "\\arq\\icone.png"); //cria icone da imagem do brasil
	private String eEstado; //estado escolhido
	private ImageIcon jIcone = new ImageIcon(System.getProperty("user.dir") + "\\arq\\joker.png");
	private ImageIcon calendario = new ImageIcon(System.getProperty("user.dir") + "\\arq\\calendario.png");
	private ImageIcon cuidese = new ImageIcon(System.getProperty("user.dir") + "\\arq\\cuidese.png");
	private Object[] dia31 = criaData(31);
	private Object[] dia29 = criaData(29);
	private Object[] dia30 = criaData(30);
	
	
	private Object[] Mes = {
			"Janeiro",
			"Fevereiro",
			"Março",
			"Abril",
			"Maio",
			"Junho",
			"Julho",
			"Agosto",
			"Setembro",
			"Outubro",
			"Novembro",
			"Dezembro",};
	
	private Object[] estados = { 
			"Acre",
			"Alagoas",
			"Amapá",
			"Amazonas",
			"Bahia",
			"Ceará",
			"Distrito Federal",
			"Espírito Santo",
			"Goiás",
			"Maranhão",
			"Mato Grosso",
			"Mato Grosso do Sul",
			"Minas Gerais",
			"Pará",
			"Paraíba",
			"Paraná",
			"Pernambuco",
			"Piauí",
			"Rio de Janeiro",
			"Rio Grande do Norte",
			"Rio Grande do Sul",
			"Rondônia",
			"Roraima",
			"Santa Catarina",
			"São Paulo",
			"Sergipe",
			"Tocantins",
		};
	
	public int Menu() {
		Object[] options = { "Data e municipio",
							 "Total de um municipio",
							 "Total de uma região",
							 "Cancelar" };
		ImageIcon Avatar = new ImageIcon(System.getProperty("user.dir") + "\\arq\\Avatar.png");
		
		JOptionPane pane = new JOptionPane("Menu Interativo COVIDNOIS.\nPesquisar por:",
				JOptionPane.DEFAULT_OPTION);
		pane.setOptions(options);
		pane.setIcon(Avatar);
		
		JDialog dialogo = pane.createDialog("MENU");
		dialogo.show();
		
		Object Opt = pane.getValue();
		System.out.println(Opt);

		if(Opt == null)
			return -1;
		else if(Opt == options[0])
			return 1;
		else if(Opt == options[1])
			return 2;
		else if (Opt == options[2])
			return 3;
		else
			return 0;
		
	}
	
	public int EscolheRegiao () {
		int escReg = 0;
		ImageIcon iconeR = new ImageIcon(System.getProperty("user.dir") + "\\arq\\Região.png");
		Object[] regioes = {"Brasil", "Norte", "Nordeste", "Sudeste", "Sul", "Centro-Oeste"};
		Object[] opcoes = {"Selecionar", "Cancelar"};
		
		JOptionPane pane = new JOptionPane("Escolha a região:",
				JOptionPane.INFORMATION_MESSAGE);
		pane.setOptions(opcoes);
		pane.setIcon(iconeR);
		pane.setSelectionValues(regioes);
		
		JDialog dialR = pane.createDialog("Região");
		
		dialR.show();

		Object RegiaoSelecionada = pane.getInputValue();
		Object Opt = pane.getValue();
				
		if(Opt == null)
			return -1;
		
		else if (Opt == opcoes[1])
			return 0;
		
		else {
			if(RegiaoSelecionada == regioes[0]) {
				escReg = 6;
			}
			else if(RegiaoSelecionada == regioes[1]) {
				escReg = 1;		
			}
			else if(RegiaoSelecionada == regioes[2]) {
				escReg = 2;
			}
			else if(RegiaoSelecionada == regioes[3]) {
				escReg = 3;
			}
			else if(RegiaoSelecionada == regioes[4]) {
				escReg = 4;
			}
			else if(RegiaoSelecionada == regioes[5]) {
				escReg = 5;
			}
			else
				escReg = 0;
		}
		
		return escReg;
		
	}
	
	public String DialogoEstado() {
		Object[] opcoes = {"Selecionar", "Cancelar"};
		JOptionPane painelE = new JOptionPane("De qual Estado você quer obter informações?",
				JOptionPane.INFORMATION_MESSAGE);
		
		painelE.setOptions(opcoes);
		painelE.setIcon(icone);
		painelE.setSelectionValues(estados);
		
		JDialog dialEst = painelE.createDialog("Selecione o Estado");
		
		dialEst.show();
		
		Object estadoSelecionado = painelE.getInputValue(); //pega estado escolhido
		Object Opt = painelE.getValue(); //pega opcao escolhida: Selecionar ou Cancelar

		if(Opt == null)
			return null;
		
		else if (Opt == opcoes[1])
			return "Cancelar";
		
		else
			return (String)estadoSelecionado;

	}
	
	@SuppressWarnings("deprecation")
	public String DialogoMunicicpio(String[] municipios, String Estado) {
		String caminhoIconeEstado = caminhoImgEstado + Estado + ".png";
		ImageIcon iconeEstado = new ImageIcon(caminhoIconeEstado);
		Object[] opcoes = {"Ok", "Voltar"};
		Object[] listaMun = new Object[municipios.length];
		
		for(int i=0;i<municipios.length; i++) 
			listaMun[i] = municipios[i];
		
		
		JOptionPane pane = new JOptionPane("De qual Município você quer obter informações?",
					JOptionPane.INFORMATION_MESSAGE);
		
		pane.setOptions(opcoes);
		pane.setIcon(iconeEstado);
		pane.setSelectionValues(listaMun);
		
		JDialog dialogoMun = pane.createDialog("Selecione o Município");
		
		dialogoMun.show();
		Object municipioSelecionado = pane.getInputValue(); //pega o municipio escolhido
		Object valueEsc = pane.getValue(); //pega a opção escolhida: Ok ou Voltar
		
		
		if(valueEsc == null) 
			return null;
		
		else if(valueEsc == opcoes[1])
			return "Voltar";
		else
			return (String) municipioSelecionado;
		
	}
	
	public String EscolheData() {
		String data;
		Object[] opcoes = {"Selecionar", "Cancelar"};
		
		JOptionPane painelMes = new JOptionPane("Selecione o mês:",
				JOptionPane.INFORMATION_MESSAGE);
		painelMes.setOptions(opcoes);
		painelMes.setSelectionValues(Mes);
		painelMes.setIcon(calendario);
		
		JDialog dialMes = painelMes.createDialog("Mês");
		dialMes.show();
		
		Object mes = painelMes.getInputValue();
		Object Opt = painelMes.getValue();	
		
		String mesNumeral = null; //para trasnformar o mes em numeral string
		for(int i =0; i < 12; i++) //pra poder por o 0 antes de um mes menorq  10
			if(mes == Mes[i]) 
				if(i < 9) 
					mesNumeral = "0" + Integer.toString(i+1);
				
				else
					mesNumeral = Integer.toString(i+1);
				
		if(Opt == null) 
			return null;
		
		else if(Opt == opcoes[1])
			return "Cancelar";
		
		else {
			JOptionPane painelDia = new JOptionPane("Selecione o dia:", 
					JOptionPane.INFORMATION_MESSAGE);
			
			painelDia.setOptions(opcoes);
			painelDia.setIcon(calendario);
			
			JDialog dialDia = painelDia.createDialog((String)mes);
			
			if(mes == Mes[0] || mes == Mes[2] || mes == Mes[4] || mes == Mes[6] ||
						mes == Mes[7] || mes == Mes[9] || mes == Mes[11]) 
				painelDia.setSelectionValues(dia31);
				
			else if(mes == Mes[3] || mes == Mes[5] ||
						mes == Mes[8] || mes == Mes[10]) 
				painelDia.setSelectionValues(dia30);
				
			else
				painelDia.setSelectionValues(dia29);
			
			dialDia.show();
			
			Object Opt2 = painelDia.getValue();
			Object dia = painelDia.getInputValue();
			
			if(Opt2 == null || dia == null) 
				return null;
			
			else if(Opt2 == opcoes[1])
				return "Cancelar";
			
			else {
				for(int i = 0; i < 9; i++) //pra poder por o 0 antes de um dia menorq  10
					if(Integer.parseInt((String) dia) == Integer.parseInt((String) dia31[i])) {
							dia = "0" + Integer.toString(i+1);
							break;
					}
				
				data = (String)mesNumeral + "-" + (String)dia;
				return data;
			}
		}
	}
	
	public String[] SelecionaMunicipio(String estado) {
		String[][] munEstado = new String [900][4];
		
		if(estado != null)
			if(estado.equals("Acre")) {
				munEstado = TodosMunicipiosEstado("AC");
			}
			else if(estado.equals("Alagoas")){
				munEstado = TodosMunicipiosEstado("AL");
			}
			else if(estado.equals("Amapá")){
				munEstado = TodosMunicipiosEstado("AP");
			}
			else if(estado.equals("Amazonas")){
				munEstado = TodosMunicipiosEstado("AM");
			}
			else if(estado.equals("Bahia")){
				munEstado = TodosMunicipiosEstado("BA");
			}
			else if(estado.equals("Ceará")){
				munEstado = TodosMunicipiosEstado("CE");
			}
			else if(estado.equals("Distrito Federal")){
				munEstado = TodosMunicipiosEstado("DF");
			}
			else if(estado.equals("Espírito Santo")){
				munEstado = TodosMunicipiosEstado("ES");
			}
			else if(estado.equals("Goiás")){
				munEstado = TodosMunicipiosEstado("GO");
			}
			else if(estado.equals("Maranhão")){
				munEstado = TodosMunicipiosEstado("MA");
			}
			else if(estado.equals("Mato Grosso")){
				munEstado = TodosMunicipiosEstado("MT");
			}
			else if(estado.equals("Mato Grosso do Sul")){
				munEstado = TodosMunicipiosEstado("MS");
			}
			else if(estado.equals("Minas Gerais")){
				munEstado = TodosMunicipiosEstado("MG");
			}
			else if(estado.equals("Pará")){
				munEstado = TodosMunicipiosEstado("PA");
			}
			else if(estado.equals("Paraíba")){
				munEstado = TodosMunicipiosEstado("PB");
			}
			else if(estado.equals("Paraná")){
				munEstado = TodosMunicipiosEstado("PR");
			}
			else if(estado.equals("Piauí")){
				munEstado = TodosMunicipiosEstado("PI");
			}
			else if(estado.equals("Pernambuco")){
				munEstado = TodosMunicipiosEstado("PE");
			}
			else if(estado.equals("Rio de Janeiro")){
				munEstado = TodosMunicipiosEstado("RJ");
			}
			else if(estado.equals("Rio Grande do Norte")){
				munEstado = TodosMunicipiosEstado("RN");
			}
			else if(estado.equals("Rio Grande do Sul")){
				munEstado = TodosMunicipiosEstado("RS");
			}
			else if(estado.equals("Rondônia")){
				munEstado = TodosMunicipiosEstado("RO");
			}
			else if(estado.equals("Roraima")){
				munEstado = TodosMunicipiosEstado("RR");
			}
			else if(estado.equals("Santa Catarina")){
				munEstado = TodosMunicipiosEstado("SC");
			}
			else if(estado.equals("São Paulo")){
				munEstado = TodosMunicipiosEstado("SP");
			}
			else if(estado.equals("Sergipe")){
				munEstado = TodosMunicipiosEstado("SE");
			}
			else if(estado.equals("Tocantins")){
				munEstado = TodosMunicipiosEstado("TO");
			}
			else {
				return null;
			}
		else
			return null;
		
		String[] municipios = new String[ContaQtMunicipios(munEstado)];
		municipios = CopiaMunicipios(munEstado, municipios);
		
		return municipios;
	}
	
	private String[][] TodosMunicipiosEstado(String UF) {
		String[][] Municipios = new String[900][4];
		String linha;
		String munUF = null;
		int cont =0;
		
		
		
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cam), "UTF-8"));
			
			while((linha = br.readLine()) != null) { 
				munUF = linha.substring(8, 10);

				if(cont ==0)
					cont++;
				
				else if (munUF.equals(UF) && cont > 0){
					Municipios[cont-1] = linha.split(";");
					cont++;
				}
			}
			
		}	catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Municipios;
	}
	
	public void MsgErro() {
		JOptionPane.showMessageDialog(null, "       Fechou por quê?\n"
				+ "        Até a próxima!", "Haha!", JOptionPane.ERROR_MESSAGE, jIcone);
	}
	
	private int ContaQtMunicipios(String[][] municipios) {
		for(int i =0; i<municipios.length; i++) 
			if(municipios[i][2] == null)
				return i;
		return 0;
	}
	
	private String[] CopiaMunicipios(String[][] mun, String[] municipios) {
		for(int i =0; i<mun.length; i++) 
			if(mun[i][2] != null)
				municipios[i] = mun[i][2];
			else
				break;
		
		return municipios;
	}
	
	public static void pause(int ms) {
	    try {
	        Thread.sleep(ms);
	    } catch (InterruptedException e) {
	        System.err.format("IOException: %s%n", e);
	    }
	}
	private String[] criaData(int dia) {
		String[] data = new String[dia];

		for(int i=0; i < data.length; i++) 
			data[i] = Integer.toString(i + 1);
			
		return data;
	}

}
