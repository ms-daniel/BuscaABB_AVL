package projeto;


public class TArvoreAVL {

	public TNodo T;
	private int h;
	private TNodo p;
	public int compAvl = 0;

	public TArvoreAVL () { 
		T = null;
	}

	public TNodo raiz(){
		return T;
	}

	public void insere (TInfo item) {
		T = insere(T,item,null);
		AVL(p);
	}

	public TNodo insere (TNodo T, TInfo item, TNodo pai) {
		int Tconvert = 0; 
		int convert = 0;
		
		if (T == null) {
			T = new TNodo(item,pai);
			this.p = T;
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
				if(item.codigoMunicipio.compareTo(T.item.codigoMunicipio) == 0) {
					if(item.data.compareTo(T.item.data) < 0)
						T.esq = insere(T.esq,item,pai);
					else
						T.dir = insere(T.dir,item,pai);
				}else {
					if (item.codigoMunicipio.compareTo(T.item.codigoMunicipio) < 0 ) {
						T.esq = insere(T.esq,item,pai);
					}
					else /*if (item.codigoMunicipio.compareTo(T.item.codigoMunicipio) > 0) */{
						T.dir = insere(T.dir,item,pai);
					}
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
		compAvl++;
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
				}if(item.codigoMunicipio.compareTo(T.item.codigoMunicipio) == 0) {
					if(item.data.compareTo(T.item.data) < 0)
						T = pesquisaMunicipioData(T.esq,item);
					else
						T = pesquisaMunicipioData(T.dir,item);
				}
				else {
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
		compAvl++;
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
				}else if(item.codigoMunicipio.compareTo(T.item.codigoMunicipio) == 0) {
					if(item.data.compareTo(T.item.data) < 0)
						T = pesquisaEstadoData(T.esq,item);
					else
						T = pesquisaEstadoData(T.dir,item);
				}
				else {
					if (item.codigoMunicipio.compareTo(T.item.codigoMunicipio) < 0 ) {
						T = pesquisaEstadoData(T.esq,item);
					}else
						T = pesquisaEstadoData(T.dir,item);
				}
			}
		}
		return T;
	}

	public void AVL(TNodo T) {
		if (T != null) {
			T.bal = balanco(T);
			if (T.bal < 2) {
				AVL(T.pai);
			} else {
				if (T.hesq >= T.hdir) 
					if (T.esq.hesq >= T.esq.hdir) { 
						rotacao_direita(T);
					}
					else { 
						rotacao_esquerda(T.esq);
						rotacao_direita(T);
					}

				if (T.hdir >= T.hesq) 
					if (T.dir.hdir >= T.dir.hesq) { 
						rotacao_esquerda(T);
					}
					else {

						rotacao_direita(T.dir);
						rotacao_esquerda(T);
					}				
			}
		}
	}

	public int balanco(TNodo T) {
		h = 0; balpreOrdem(T.esq,0); T.hesq = h;
		h = 0; balpreOrdem(T.dir,0); T.hdir = h;
		return Math.abs(T.hesq-T.hdir);
	}

	public void balpreOrdem (TNodo T, int v) {
		if (T != null) {
			v++;
			balpreOrdem(T.esq,v);
			balpreOrdem(T.dir,v);
		} else 
			if (v > h) h = v;
	}

	public void rotacao_direita(TNodo T) {
		TNodo apu = T.esq;
		T.esq = apu.dir;
		if (apu.dir != null) 
			apu.dir.pai = T;
		apu.pai = T.pai; 
		apu.dir = T; 
		T.pai = apu;
		T.bal = 0;
		if (apu.pai == null)
			this.T = apu;
		else {
			if(apu.item.codigoMunicipio == "" || apu.item.codigoMunicipio == null) {
				int convert = Integer.parseInt(apu.item.codigoEstado);
				int Tconvert = Integer.parseInt(apu.pai.item.codigoEstado);
				if (convert < Tconvert) {
					apu.pai.esq = apu;
				}
				else {
					apu.pai.dir = apu; 
				}
			}if(apu.item.codigoMunicipio.compareTo(apu.pai.item.codigoMunicipio) == 0) {
				if(apu.item.data.compareTo(apu.pai.item.data) < 0) {
					apu.pai.esq = apu;
				}
				else {
					apu.pai.dir = apu;
				}
			}
			else {
				if (apu.item.codigoMunicipio.compareTo(apu.pai.item.codigoMunicipio) < 0) 
					apu.pai.esq = apu; 
				else
					apu.pai.dir = apu;
			}				
		}
	}

	public void rotacao_esquerda(TNodo T) {
		TNodo apu = T.dir;
		T.dir = apu.esq;
		if (apu.esq != null) 
			apu.esq.pai = T; 
		apu.pai = T.pai; 
		apu.esq = T; 
		T.pai = apu; 
		T.bal = 0;
		if (apu.pai == null) 
			this.T = apu;
		else {
			if(apu.item.codigoMunicipio == "" || apu.item.codigoMunicipio == null) {
				int convert = Integer.parseInt(apu.item.codigoEstado);
				int Tconvert = Integer.parseInt(apu.pai.item.codigoEstado);
				if (convert < Tconvert) 
					apu.pai.esq = apu; 
				else
					apu.pai.dir = apu; 
			}if(apu.item.codigoMunicipio.compareTo(apu.pai.item.codigoMunicipio) == 0) {
				if(apu.item.data.compareTo(apu.pai.item.data) < 0) {
					apu.pai.esq = apu;
				}
				else {
					apu.pai.dir = apu;
				}
			}
			else {
				if (apu.item.codigoMunicipio.compareTo(apu.pai.item.codigoMunicipio) < 0) 
					apu.pai.esq = apu; 
				else
					apu.pai.dir = apu;
			}				
		}
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
