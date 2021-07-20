package projeto;

public class TInfo {
	public String codigoEstado; //codigo estado(chave) // 3
    public String codigoMunicipio; //municipio(chave) // 4
    public String data; //data(chave) // 7
    public String aux_codMun;

    public String regiao; // 0
    public String estado; // 1
    public String municipio; // 2
    public String casosAcumulados; // 10
    public String casosNovos; // 11
    public String obitosAcumulados; // 12
    public String obitosNovos; // 13
    
    public String[] dadosTNojo = new String[14];
	
	
	public TInfo(String[] coluna) {
		
		
		this.regiao = coluna[0];
		
		this.estado = coluna[1];
	
		this.municipio = coluna[2];
	
		this.codigoEstado = coluna[3];
	
		this.codigoMunicipio = coluna[4];
		
		this.data = coluna[7];
	
		this.casosAcumulados = coluna[10];
	
		this.casosNovos = coluna[11];
	
		this.obitosAcumulados = coluna[12];
	
		this.obitosNovos = coluna[13];
		
		for(int i=0; i<14;i++)
			dadosTNojo[i] = coluna[i];

	}
	public TInfo() {
		
	}
	@Override
	public String toString(){

		return municipio+" ";
	}
	
	/*private String CodMunicipio(String codMun, String data) {
		String codigoAll;
		data = data.replaceAll("[^0-9]+","");
		codigoAll = codMun + data;
		
		return codigoAll;
	}*/
	
}