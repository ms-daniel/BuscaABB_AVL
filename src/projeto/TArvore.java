package projeto;

public class TArvore {

	public TNodo T;
	public int comp = 0;

	public TArvore () { 
		T = null;
	}

	public TNodo raiz(){
		return T;
	}

	public void insere (TInfo item) {
		T = insere(T,item,null);
	}
	
	public void inserirBIN (String[][]coluna , int i , int j) {
		 int pos = (i+j) /2;
		 TInfo item = new TInfo (coluna[pos]);
		 insere ( item ) ;
		 if (i <j) {
			 inserirBIN ( coluna ,i , pos -1) ;
			 inserirBIN ( coluna , pos +1 , j );
		 }
	}

	public TNodo insere (TNodo T, TInfo item, TNodo pai) {
		int Tconvert = 0; 
		int convert = 0;
		
		if (T == null) {
			T = new TNodo(item,pai);
		} else {
			
			pai = T;
			if (item.codigoMunicipio == "" || item.codigoMunicipio == null) {
				convert = Integer.parseInt(item.codigoEstado);
				Tconvert = Integer.parseInt(T.item.codigoEstado);
				if (convert < Tconvert) {
					T.esq = insere(T.esq,item,pai); 
				}
				else if (convert > Tconvert) {
					T.dir = insere(T.dir,item,pai);
				}
			}
			else {
	
				if (item.codigoMunicipio.compareTo(T.item.codigoMunicipio) < 0 ) {
					T.esq = insere(T.esq,item,pai);
				}
				else /*if (item.codigoMunicipio.compareTo(T.item.codigoMunicipio) > 0) */{
					T.dir = insere(T.dir,item,pai);
				}
			}
		}
		return T;
	}


	public TNodo pesquisa (TInfo item, int op) {
		if(op == 2) 
			return pesquisaMunicipioData(T,item);
		else if(op == 3)
			return pesquisaEstadoData(T,item);
		else
			return pesquisaMunicipioData(T,item);
	}

	public TNodo pesquisaMunicipioData(TNodo T,TInfo item) {
		comp++;
		int convert;
		int Tconvert;
		if (T == null) {
			return T;
		} else {
			if (item.municipio.compareToIgnoreCase(T.item.municipio) == 0 && item.data.compareToIgnoreCase(T.item.data) == 0) {
				return T;
			}
			else {
				if(T.item.codigoMunicipio == "" || T.item.codigoMunicipio == null) {
					convert = Integer.parseInt(item.codigoEstado);
					Tconvert = Integer.parseInt(T.item.codigoEstado);
				if (convert < Tconvert) 
					T = pesquisaMunicipioData(T.esq,item);
				else
					T = pesquisaMunicipioData(T.dir,item);
				}else {
					if (item.codigoMunicipio.compareTo(T.item.codigoMunicipio) < 0 ) {
						T = pesquisaMunicipioData(T.esq,item);
					}else
						T = pesquisaMunicipioData(T.dir,item);
				}
			}
		}
		return T;
	}
	

	public TNodo pesquisaEstadoData(TNodo T,TInfo item) {
		comp++;
		int convert;
		int Tconvert;
		if (T == null) {
			return T;
		} else {
			if (item.codigoEstado.compareToIgnoreCase(T.item.codigoEstado) == 0 && item.data.compareToIgnoreCase(T.item.data) == 0) {
				return T;
			}
			else {
				if(T.item.codigoMunicipio == "" || T.item.codigoMunicipio == null) {
					convert = Integer.parseInt(item.codigoEstado);
					Tconvert = Integer.parseInt(T.item.codigoEstado);
				if (convert < Tconvert) 
					T = pesquisaEstadoData(T.esq,item);
				else
					T = pesquisaEstadoData(T.dir,item);
				}else {
					if (item.codigoMunicipio.compareTo(T.item.codigoMunicipio) < 0 ) {
						T = pesquisaEstadoData(T.esq,item);
					}else
						T = pesquisaEstadoData(T.dir,item);
				}
			}
		}
		return T;
	}

	public void emOrdem (TNodo T) {
		if (T != null) {
			emOrdem(T.esq);
			System.out.print (T.item + " ");
			emOrdem(T.dir);
		}
	}

	public void preOrdem (TNodo T) {
		if (T != null) {
			System.out.print (T.item + " ");
			preOrdem(T.esq);
			preOrdem(T.dir);
		}
	}

	public void posOrdem (TNodo T) {
		if (T != null) {
			posOrdem(T.esq);
			posOrdem(T.dir);
			System.out.print (T.item + " ");
		}
	}	
	
	public void mostraArvore()
	{
		//preOrdem(T);
		//mostraEstados(T);
		//mostraMunicipio(T);
		//mostraMunicipio(T);
		//posOrdem(T);
		//emOrdem(T);
	}
	
	@SuppressWarnings("unused")
	private void mostraEstados(TNodo T) {
			if(T.pai == null && T != null) { 
				System.out.print(T.item + " ");
				System.out.print("coduf: " + T.item.codigoEstado + " ");
				System.out.println("codmun: " + T.item.codigoMunicipio + " ");
			}
			
			else if(T.item.codigoEstado != T.pai.item.codigoEstado && T != null) {
				System.out.print(T.item + " ");
				System.out.print(T.item.estado + " ");
				System.out.print("coduf: " + T.item.codigoEstado + " ");
				System.out.println("codmun: " + T.item.codigoMunicipio + " ");
			}

			if(T.esq != null)
				mostraEstados(T.esq);
			if(T.dir != null)
				mostraEstados(T.dir);
	}
	
	private void mostraMunicipio(TNodo T) {
		if(T.item.municipio != null) {
			//System.out.print(T.item + " ");
			System.out.print("Mun: " + T.item.municipio + " ");
			System.out.print(T.item.codigoMunicipio);
			System.out.println(" Data: " + T.item.data);
			//System.out.println("codmun: " + T.item.codigoMunicipio + " ");
		}

		if(T.esq != null)
			mostraMunicipio(T.esq);
		if(T.dir != null)
			mostraMunicipio(T.dir);
	}
}
