package projeto;

public class TNodo {

	TNodo esq;
	TInfo item;
	TNodo dir;
	TNodo pai;
	int bal=0;
	int hesq=0;
	int hdir=0;

	public TNodo (TInfo item, TNodo pai) { 
		this.item = new TInfo(item.dadosTNojo);
		this.esq = null;
		this.dir = null;
		this.pai = pai;
	}
	
	@Override
	public String toString() {
		return "Região: "+item.regiao+"\nEstado: " + item.estado + "\nMunicípio: " + item.municipio + 
		"\nData: " + item.data + "\nCasos Acumulados: " + item.casosAcumulados + "\nCasos Novos: " + item.casosNovos + 
		"\nObitos Acumulados: " + item.obitosAcumulados + "\nObitos Novos: " + item.obitosNovos;
	}
}
