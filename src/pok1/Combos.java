package pok1;

/*
 * AS FUNÇÕES DESTE FICHEIRO TRATAM DE COMBINAÇÕES DE 5 CARTAS E ASSUMEM QUE VÊM ORDENADAS 
 * POR ORDEM DECRESCENTE (E ALFABÉTICA)
 * TALVEZ ACRESCENTAR SORTS NO PRINCÍPIO DAS FUNÇÕES TODAS? (MENOS AUXILIARES)
 */ 

public final class Combos {
	public static char suits[] = Deck.suits;
	public static char values[] = Deck.values;
	
	// This is a utility class. Not to be instantiated.
	private Combos() {
		
	}
	
	private static int getCardValue(String card) {
		for(int i = 0; i < values.length; i++)
			if(card.charAt(0) == values[i])
				return i;
		return -2;
	}
	


	/* Verifica se duas cartas seguidas são iguais.
	 */

	public static int[] checkForPair(String cards[]){
		int flagAndValue[] = {0,0,0};
	    for(int i = 0; i < 4; i++){
	        if(cards[i].charAt(0) == cards[i+1].charAt(0)){
	            // flagAndValue[0] = 1 revela que há um par no conjunto (=0 revela que não) e flagAndValue[1] revela o valor das cartas desse par
	            flagAndValue[0] = 1;
	            flagAndValue[1] = getCardValue(cards[i]);
	            flagAndValue[2] = i;
	            return flagAndValue;
	        }
	    }
	    // Se não houver um par, põe flagAndValue a zeros.
	    flagAndValue[0] = 0;
	    flagAndValue[1] = 0;
	    return flagAndValue;
	}

	// Esta fun��o ser� usada em checkForTwoPair para descobrir um par diferente do j� encontrado, e na fun��o checkForFullHouse para descobrir
	// um par de valor diferente do valor do trio (forbiddenValue)

	public static int[] checkForPair2(String cards[], int forbiddenValue){
		int flagAndValue[] = {0,0,0};
	    for(int i = 0; i < 4; i++){
	        if((cards[i].charAt(0) == cards[i+1].charAt(0)) && (cards[i].charAt(0) != values[forbiddenValue])){
	            // flagAndValue[0] = 1 revela que h� um par no conjunto (=0 revela que n�o) e flagAndValue[1] revela o valor das cartas desse par.
	            flagAndValue[0] = 1;
	            flagAndValue[1] = getCardValue(cards[i]);
	            flagAndValue[2] = i;
	            return flagAndValue;
	        }
	    }
	    // Se n�o houver um par de valor diferente do valor proibido, p�e flagAndValue a zeros.
	    flagAndValue[0] = 0;
	    flagAndValue[1] = 0;
	    return flagAndValue;
	}

	// Fun��o que verifica se h� dois pares distintos num conjunto. Depois de verificar se h� um par, guarda o valor das cartas desse par, e pro-
	// cura um par com cartas de valor diferente.

	public static int[] checkForTwoPair(String cards[]){
		int flagAndValue3[] = {0,0,0,0,0};
	    // flagAndValue[0] = 1 revela que h� um par no conjunto (=0 revela que n�o) e flagAndValue[1] revela o valor das cartas desse par.
	    // Este flagAndValue � tempor�rio, pelo que n�o � um argumento e � declarado dentro da fun��o.
	    int flagAndValue[] = checkForPair(cards);
	    if(flagAndValue[0] == 1){

	        // guarda o valor do par, que ser� o par mais alto do conjunto.
	        int max = flagAndValue[1];
	        int index = flagAndValue[2];

	        // Procura um segundo par de valor diferente de max (o valor das cartas do primeiro par).
	        flagAndValue = checkForPair2(cards, max);
	        if(flagAndValue[0] == 1){
	            // Quando usamos flagAndValue3, precisamos de guardar 2 valores para al�m da flag, e o valor mais alto ou mais importante est� em
	            // flagAndValue[1]
	            flagAndValue3[0] = 1;
	            flagAndValue3[1] = max;
	            flagAndValue3[2] = flagAndValue[1];
	            flagAndValue3[3] = index;
	            flagAndValue3[4] = flagAndValue[2];
	            return flagAndValue3;

	        }
	    }

	    // Se n�o houver dois pares, p�e flagAndValue3 a zeros.
	    flagAndValue3[0] = 0;
	    flagAndValue3[1] = 0;
	    flagAndValue3[2] = 0;
	    return flagAndValue3;
	}

	// Semelhante a checkForPair, mas verifica tr�s cartas seguidas do mesmo valor, em vez de duas.

	public static int[] checkForThreeOfAKind(String cards[]){
		int flagAndValue[] = {0,0,0};
	    for(int i = 0; i < 3; i++){
	        if(getCardValue(cards[i]) == getCardValue(cards[i+1]) && getCardValue(cards[i+1]) == getCardValue(cards[i+2])){
	            // flagAndValue[0] = 1 revela que h� um par no conjunto (=0 revela que n�o) e flagAndValue[1] revela o valor das cartas desse trio.
	            flagAndValue[0] = 1;
	            flagAndValue[1] = getCardValue(cards[i]);
	            flagAndValue[2] = i;
	            return flagAndValue;
	        }
	    }
	    // Se n�o houver um trio, p�e flagAndValue2 a zeros.
	    flagAndValue[0] = 0;
	    flagAndValue[1] = 0;
	    return flagAndValue;
	}

	// A fun��o check for straight baseia-se em verificar se os valores das 5 cartas, j� organizadas, est�o a 1 valor de dist�ncia das cartas vizinhas.
	// No entanto, existe o caso de uma sequ�ncia em que o �s � a carta mais baixa, e por isso a fun��o tem de estar pronta para esses casos.

	public static int checkForStraight(String cards[]){
	    int a = getCardValue(cards[0]);
	    int b = getCardValue(cards[1]);
	    int c = getCardValue(cards[2]);
	    int d = getCardValue(cards[3]);
	    int e = getCardValue(cards[4]);

	    // Permite, no pr�ximo if, verificar rapidamente se h� uma sequ�ncia em que o �s � a carta de menor valor.
	    if(a == 12 && e == 0){
	        a = -1;
	    }

	    // Este if permite verificar o seguinte caso: Uma sequ�ncia de �s como carta mais baixa e que "chegou � fun��o" organizada com o �s em primeiro
	    // lugar;
	    if(a == -1 && (b-c == 1) && (c-d == 1 ) && (d-e == 1) && e == 0){
	        // Esta fun��o organiza o conjunto para que o �s seja movido para o fim.
	        sortStraightWithLowAce(cards);  //[novo] é preciso??
	        return 1;
	    }
	    // Este if permite verificar o seguinte caso: e uma sequ�ncia de �s como carta mais baixa que chegou j� organizada da forma correta � fun��o.
	    if((a == 3) && (b == 2) && (c == 1) && (d == 0) && (e == 12)){
	        return 1;
	    }

	    // Este if verifica as restantes sequ�ncias, que n�o t�m uma necessidade especial de alterar valores ou reorganizar.
	    if((a != -1) && (a-b == 1) && (b-c == 1) && (c-d == 1) && (d-e ==1)){
	        return 1;
	    }
	    return 0;
	}

	// A fun��o limita-se a verificar se as 5 cartas t�m o mesmo naipe.

	public static int checkForFlush(String cards[]){
	    if((cards[0].charAt(1) == cards[1].charAt(1)) && (cards[1].charAt(1) == cards[2].charAt(1)) &&
	    	(cards[2].charAt(1) == cards[3].charAt(1)) && (cards[3].charAt(1) == cards[4].charAt(1))){
	        return 1;
	    }
	    return 0;
	}

	// Verifica se h� um par e se h� um trio, e se o par � de valor diferente do valor do trio. (Usa checkForPair2)

	public static int[] checkForFullHouse(String cards[]){
		int flagAndValue3[] = {0,0,0};
	    int flagAndValue[] = checkForThreeOfAKind(cards);
	    int a;
	    
	    if(flagAndValue[0] == 1){
	        // Guarda o valor do trio
	        a = flagAndValue[1];
	    }
	    else{
	        // Se n�o houver um trio, p�e flagAndValue3 a zeros.
	        flagAndValue3[0] = 0;
	        flagAndValue3[1] = 0;
	        flagAndValue3[2] = 0;
	        return flagAndValue3;
	    }
	    // Verifica se h� um par de valor diferente do valor do trio.
	    if(checkForPair2(cards, a)[0] == 1){
	        // Quando usamos flagAndValue3, precisamos de guardar 2 valores para al�m da flag, e o valor mais alto ou mais importante est� em
	        // flagAndValue[1] (mais importante no caso do fullHouse � o trio).
	        flagAndValue3[0] = 1;
	        flagAndValue3[1] = a;
	        flagAndValue3[2] = flagAndValue[1];
	        return flagAndValue3;
	    }
	    // Se n�o houver um par de valor diferente  do valor do trio, p�e flagAndValue3 a zeros.
	    flagAndValue3[0] = 0;
	    flagAndValue3[1] = 0;
	    flagAndValue3[2] = 0;
	    return flagAndValue3;
	}

	// Semelhante a checkForPair e a checkForThreeOfAKind, mas verifica se quatro cartas seguidas t�m o mesmo valor.

	public static int[] checkForFourOfAKind(String cards[]){
		int flagAndValue[] = {0,0};
	    for(int i = 0; i < 2; i++){
	        if(getCardValue(cards[i]) == getCardValue(cards[i+1]) && getCardValue(cards[i+1]) == getCardValue(cards[i+2]) &&
	           getCardValue(cards[i+2]) == getCardValue(cards[i+3])){
	            // flagAndValue[0] = 1 revela que h� um par no conjunto (=0 revela que n�o) e flagAndValue[1] revela o valor das cartas dessa quadrupla.
	            flagAndValue[0] = 1;
	            flagAndValue[1] = getCardValue(cards[i]);
	            return flagAndValue;
	        }
	    }
	    flagAndValue[0] = 0;
	    flagAndValue[1] = 0;
	    return flagAndValue;
	}

	// A fun��o limita-se a verificar se, no conjunto de 5 cartas, h� ao mesmo tempo uma sequ�ncia e um flush. � a defini��o de um Straight Flush.

	public static int checkForStraightFlush(String cards[]){
	    if((checkForFlush(cards) == 1) && (checkForStraight(cards) == 1))
	    	return 1;
	    return 0;
	}

	// A fun��o limita-se a verificar se existe um Straight Flush, e se a carta mais alta do Straight Flush � um �s. � a defini��o de um Royal Straight
	// Flush.

	public static int checkForRoyalStraightFlush(String cards[]){
	    if((checkForStraightFlush(cards) == 1) && (getCardValue(cards[0]) == 12))
	    	return 1;
	    return 0;
	}

	// � a fun��o usada por checkForStraight, quando a sequ�ncia tem um �s e � do tipo 5432A. P�e o �s no fim do conjunto, e chega as restantes cartas
	// uma posi��o para a frente.

	// VERIFICAR SE ISTO FICOU BEM COM AS ALTERAÇOES
	public static String[] sortStraightWithLowAce(String cards[]){
	    if(getCardValue(cards[0]) == 12 && getCardValue(cards[4]) == 0){
	        String tempCard;
	        tempCard = cards[0];
	        for(int i = 0; i < 4; i++){
	            cards[i] = cards[i+1];
	        }
	        cards[4] = tempCard;
	        return cards;
	    }
	    else {
			return null;
		}
	}
	
	public static String[] sortCards(String cardArray[]){

	    // Dois valores que evitam chamar mais vezes do que o necess�rio a fun��o getCardValue.
	    int currentValue;
	    int nextValue;

	    // Percorre as cartas at� � ordem i (que est� a diminuir, por isso vai percorrer cada vez menos cartas) e cada vez que duas cartas consecutivas
	    // est�o na ordem errada, troca a sua ordem. Resulta em cada passagem pelo loop exterior levar a menor carta das que s�o percorridas nesse loop
	    // para a ordem i (primeiro loop a carta mais fraca vai para o fim, segundo loop a segunda carta mais fraca vai para pen�ltimo lugar, etc...).
	    int numCards = cardArray.length;
	    for(int i = numCards; i > 0; i--){
	        currentValue = 0;
	        nextValue = getCardValue(cardArray[0]);
	        for(int j = 0; j < i; j++){
	            if(j < i && j< (numCards - 1)){
	                currentValue = nextValue;
	                nextValue = getCardValue(cardArray[j+1]);
	            }
	            // Este if p�e a hip�tese de dois valores seguidos serem iguais, e por isso compara os naipes, para p�r as cartas por ordem alfab�tica.
	            if((j < i && j < (numCards -1)) && ((currentValue < nextValue) || (currentValue == nextValue && (cardArray[j].compareTo(cardArray[j+1]) > 0)))){
	                String tempCard;
	                tempCard = cardArray[j+1];
	                cardArray[j+1] = cardArray[j];
	                cardArray[j] = tempCard;
	                nextValue = currentValue;
	            }
	        }
	    }
	    return cardArray;
	}
	
	
	public static int getHandValue(String cards[]){
	    // As fun��es check precisam de receber as cartas ordenadas para funcionar.
	    sortCards(cards);
	
	    // "flag" � o primeiro elemento destes arrays, e revela a presen�a de uma m�o com a for�a presente no nome da fun��o no conjunto recebido. �
	    // o �nico elemento destes arrays que esta fun��o utiliza (utiliza-os como condia��o nos ifs). O resto dos arrays � utilizado para informa��o
	    // entre as fun��es check, que para o seu funcionamento, �s vezes dependem de outras.
	    //int flagAndValue[];
	    //int flagAndValue3[];
	
	    // verificar com todas as fun��es "checkFor", da combina��o mais alta para a mais baixa. Dar return assim que a primeira for encontrada no con-
	    // junto recebido.
	
	    if(checkForRoyalStraightFlush(cards) == 1){
	        return 10;
	    }
	    if(checkForStraightFlush(cards) == 1){
	        return 9;
	    }
	    if(checkForFourOfAKind(cards)[0] == 1){
	        return 8;
	    }
	    if(checkForFullHouse(cards)[0] == 1){
	        return 7;
	    }
	    if(checkForFlush(cards) == 1){
	        return 6;
	    }
	    if(checkForStraight(cards) == 1){
	        return 5;
	    }
	    if(checkForThreeOfAKind(cards)[0] == 1){
	        return 4;
	    }
	    if(checkForTwoPair(cards)[0] == 1){
	        return 3;
	    }
	    if(checkForPair(cards)[0] == 1){
	        return 2;
	    }
	
	    // Se nenhuma das combina��es anteriores estiver presente, � uma m�o de for�a High Card. Esta � a defini��o de High Card.
	    return 1;
	}
	
	
	//Função que devolve o índice da mão na tabela de preços para pagamento//
	public static int getTableIndex(String cards[]) {
		if (checkForRoyalStraightFlush(cards) == 1) {
			return 0;
		}
		if (checkForStraightFlush(cards)==1) {
			return 1;
		}
		if (checkForFourOfAKind(cards)[0]==1 && checkForFourOfAKind(cards)[1]==12) {
			return 2;
		}
		if (checkForFourOfAKind(cards)[0]==1 && (checkForFourOfAKind(cards)[1]>=0 && checkForFourOfAKind(cards)[1]<=2)) {
			return 3;
		}
		if (checkForFourOfAKind(cards)[0]==1 && (checkForFourOfAKind(cards)[1]>=3 && checkForFourOfAKind(cards)[1]<=11)) {
			return 4;
		}
		if (checkForFullHouse(cards)[0] == 1) {
			return 5;
		}
		if (checkForFlush(cards) == 1) {
			return 6;
		}
		if (checkForStraight(cards) == 1) {
			return 7;
		}
		if (checkForThreeOfAKind(cards)[0]==1) {
			return 8;
		}
		if (checkForTwoPair(cards)[0]==1) {
			return 9;
		}
		if (checkForPair(cards)[0]==1 && checkForPair(cards)[1]>=9 ){
			return 10;
		}
		return 11;
	}
	
	
	
	public static int[] checkForSFD(String cards[]) {
		int flagAndValue[] = {0,0,0,0,0,0};
	    int a = getCardValue(cards[0]);
	    int b = getCardValue(cards[1]);
	    int c = getCardValue(cards[2]);
	    int d = getCardValue(cards[3]);
	    int e = getCardValue(cards[4]);
	    
	    // If I'm already checking for Flush, do I need to return indices?
	    //int flagAndValue[] = {0, 0, 0, 0, 0, 0, 0}; //isThere, type (1=outside,0=inside), isItFlush, indices(4)
	    int tempIndices[] = {0,0,0,0};
	    // Criar arrays para terem versoes piores (mas que dao mais jeito organizar da forma atual na funçao)
	    // Tipo ter um flagAndValueOutsideNoFlush, para assim que virmos que nao ha um insideflush darmos return a esta
	    int flagAndValueNF[] = {0,0,0,0,0,0};
	    //int flagAndValueINF[] = {0,0,0,0,0,0};
	    int array[] = {a,b,c,d,e};
	    String cardArrays[][] = new String[10][3];
    	int arrays[][] = new int[10][3];
    	int cnt = 0;
    	int IsJs[][] = new int[10][2];
    	for(int i = 0; i < 4; i++) {
    		for(int j = i+1; j < 5; j++) {
    			int cnt2 = 0;
    			for(int p = 0; p < 5; p++) {
    				
    				if((4-p != i) && (4-p != j)) {
    					arrays[cnt][cnt2] = array[p];
    					cardArrays[cnt][cnt2] = cards[p];
    					cnt2++;
    				}
    			}
    			IsJs[cnt][0] = i;
    			IsJs[cnt][1] = j;
    			cnt++;
    		}
    	}
    	
    	boolean isThereAce = true;
		int newa = b, newb = c, newc = d, newd = e, newe = -1;
		String sa = cards[1], sb = cards[2], sc = cards[3], sd = cards[4], se = cards[0];
		String movedCards[] = {sa, sb, sc, sd, se};
		if(a!=12) {
			isThereAce = false;
			newa = a;
			newb = b;
			newc = c;
			newd = d;
			newe = e;
		}
		
	    int narray[]={newa,newb,newc,newd, newe};	
	    String cardnArrays[][] = new String[10][3];
    	int narrays[][] = new int[10][3];
    	int nIsJs[][] = new int[10][2];

    	cnt = 0;
    	for(int i = 0; i < 4; i++) {
    		for(int j = i+1; j < 5; j++) {
    			int cnt2 = 0;
    			for(int p = 0; p < 5; p++) {
    				
    				if((4-p != i) && (4-p != j)) {
    					narrays[cnt][cnt2] = narray[p];
    					cardnArrays[cnt][cnt2] = movedCards[p];
    					cnt2++;
    				}
    			}
    			nIsJs[cnt][0] = i;
    			nIsJs[cnt][1] = j;
    			cnt++;
    		}
    		
    	}
    	
    	// Tests only begin here...
    	for (int k = 0; k < 10; k++) {
	    	if(areConsecutive(arrays[k]) && (arrays[k][0] != 2)) { // A primeira nao pode ser um 4!!
				String theseCards[] = new String[3];
				
				cnt = 0;
				theseCards = cardArrays[k];
				for(int p = 0; p < 5; p++) {
					if((p != IsJs[k][0]) && (p != IsJs[k][1])) {
						tempIndices[cnt] = 4-p;
						cnt++;
					}
				}

				if(areFlushy(theseCards)) {
					// It's ok to return it because this is the best possible scenario 
					flagAndValue[0] =  1;
					flagAndValue[1] =  2;
					flagAndValue[2] =  1;
					
					// Como fazer isto
					flagAndValue[3] =  tempIndices[2];
					flagAndValue[4] =  tempIndices[1];
					flagAndValue[5] =  tempIndices[0];
					
					return flagAndValue;
					
				}
				/*
				else {
					flagAndValueNF[0] =  1;
					flagAndValueNF[1] =  2;
					
					// Como fazer isto
					flagAndValueNF[3] =  tempIndices[0];
					flagAndValueNF[4] =  tempIndices[1];
					flagAndValueNF[5] =  tempIndices[2];
				}
				*/
			}
    	}
    	
    	for (int k = 0; k < 10; k++) {
    		if(countNumHighCards(cardArrays[k]) >= 2) {
    			System.out.println(cardArrays[k][0] + cardArrays[k][1] + cardArrays[k][2]);
    			System.out.println(arrays[k][0] + " " + arrays[k][1] + " " + arrays[k][2]);
    			for(int u = 0; u < 2; u++) {
    				int w = 1-u;
					for(int s = 2; s <= 3; s++) {
						for(int t = 1; t+s <=4  ; t++) {
	    					
					    	if(areConsecutiveWith2Holes(arrays[k], u, w, s, t)) {
					    		
								String theseCards[] = new String[3];
								
								cnt = 0;
								theseCards = cardArrays[k];
				    			//System.out.println(theseCards[0] + theseCards[1] + theseCards[2]);

								for(int p = 0; p < 5; p++) {
									if((p != IsJs[k][0]) && (p != IsJs[k][1])) {
										tempIndices[cnt] = 4-p;
										cnt++;
									}
								}
				
								if(areFlushy(theseCards)) {
									// It's ok to return it because this is the best possible scenario 
									flagAndValue[0] =  1;
									flagAndValue[1] =  2;
									flagAndValue[2] =  1;
									
									// Como fazer isto
									flagAndValue[3] =  tempIndices[2];
									flagAndValue[4] =  tempIndices[1];
									flagAndValue[5] =  tempIndices[0];
									
									return flagAndValue;
									
								}
								/*
								else {
									flagAndValueNF[0] =  1;
									flagAndValueNF[1] =  2;
									
									// Como fazer isto
									flagAndValueNF[3] =  tempIndices[0];
									flagAndValueNF[4] =  tempIndices[1];
									flagAndValueNF[5] =  tempIndices[2];
								}
								*/
							}
	    				}
					}
    			}
    		}
    		
    		
    		
    		
    		else if (countNumHighCards(cardArrays[k]) == 1) {
    			for(int u = 0; u < 2; u++) {
    				int w = 1-u;
			    	if(areConsecutiveWith2Holes(arrays[k], u, w, 2, 1)) {
						String theseCards[] = new String[3];
						
						cnt = 0;
						theseCards = cardArrays[k];
						for(int p = 0; p < 5; p++) {
							if((p != IsJs[k][0]) && (p != IsJs[k][1])) {
								tempIndices[cnt] = 4-p;
								cnt++;
							}
						}
		
						if(areFlushy(theseCards)) {
							// It's ok to return it because this is the best possible scenario 
							flagAndValue[0] =  1;
							flagAndValue[1] =  2;
							flagAndValue[2] =  1;
							
							// Como fazer isto
							flagAndValue[3] =  tempIndices[2];
							flagAndValue[4] =  tempIndices[1];
							flagAndValue[5] =  tempIndices[0];
							
							return flagAndValue;
							
						}
						/*
						else {
							flagAndValueNF[0] =  1;
							flagAndValueNF[1] =  2;
							
							// Como fazer isto
							flagAndValueNF[3] =  tempIndices[0];
							flagAndValueNF[4] =  tempIndices[1];
							flagAndValueNF[5] =  tempIndices[2];
						}
						*/
					}
    			}
    		}
    	}
    	//Type 2: do the same but with the ace low stuff (you can repeat the ones without the ace because everything has been checked, I THINK)...
    	//if(isThereAce)
    	
    	
    	
    	for (int k = 0; k < 10; k++) {
	    	if(areConsecutive(narrays[k])) { 
				String theseCards[] = new String[3];
				
				cnt = 0;
				theseCards = cardnArrays[k];
				for(int p = 0; p < 5; p++) {
					if((p != nIsJs[k][0]) && (p != nIsJs[k][1])) {
						tempIndices[cnt] = 4-p;
						cnt++;
					}
				}

				if(areFlushy(theseCards)) {
					// It's ok to return it because this is the best possible scenario 
					flagAndValue[0] =  1;
					flagAndValue[1] =  1;
					flagAndValue[2] =  1;
					
					cards[0] = movedCards[0];
					cards[1] = movedCards[1];
					cards[2] = movedCards[2];
					cards[3] = movedCards[3];
					cards[4] = movedCards[4];
					
					// Como fazer isto
					flagAndValue[3] =  tempIndices[2];
					flagAndValue[4] =  tempIndices[1];
					flagAndValue[5] =  tempIndices[0];
					
					return flagAndValue;
					
				}
				/*
				else {
					flagAndValueNF[0] =  1;
					flagAndValueNF[1] =  2;
					
					// Como fazer isto
					flagAndValueNF[3] =  tempIndices[0];
					flagAndValueNF[4] =  tempIndices[1];
					flagAndValueNF[5] =  tempIndices[2];
				}
				*/
			}
    	}
    	
    	for (int k = 0; k < 10; k++) {
    		if(countNumHighCards(cardArrays[k]) == 1) {
    			
    			for(int u = 0; u < 2; u++) {
    				int w = 1-u;
					for(int s = 2; s <= 3; s++) {
						for(int t = 1; t+s <=4  ; t++) {
	    					
					    	if(areConsecutiveWith2Holes(arrays[k], u, w, s, t)) {
								String theseCards[] = new String[3];
								
								cnt = 0;
								theseCards = cardArrays[k];
								for(int p = 0; p < 5; p++) {
									if((p != IsJs[k][0]) && (p != IsJs[k][1])) {
										tempIndices[cnt] = 4-p;
										cnt++;
									}
								}
				
								if(areFlushy(theseCards)) {
									// It's ok to return it because this is the best possible scenario 
									flagAndValue[0] =  1;
									flagAndValue[1] =  1;
									flagAndValue[2] =  1;
									
									// Como fazer isto
									flagAndValue[3] =  tempIndices[2];
									flagAndValue[4] =  tempIndices[1];
									flagAndValue[5] =  tempIndices[0];
									
									return flagAndValue;
									
								}
								/*
								else {
									flagAndValueNF[0] =  1;
									flagAndValueNF[1] =  2;
									
									// Como fazer isto
									flagAndValueNF[3] =  tempIndices[0];
									flagAndValueNF[4] =  tempIndices[1];
									flagAndValueNF[5] =  tempIndices[2];
								}
								*/
							}
	    				}
					}
    			}
    		}
    		
    		else if (countNumHighCards(cardArrays[k]) == 0) {
    			for(int u = 0; u < 2; u++) {
    				int w = 1-u;
			    	if(areConsecutiveWith2Holes(arrays[k], u, w, 2, 1)) {
						String theseCards[] = new String[3];
						
						cnt = 0;
						theseCards = cardArrays[k];
						for(int p = 0; p < 5; p++) {
							if((p != IsJs[k][0]) && (p != IsJs[k][1])) {
								tempIndices[cnt] = 4-p;
								cnt++;
							}
						}
		
						if(areFlushy(theseCards)) {
							// It's ok to return it because this is the best possible scenario 
							flagAndValue[0] =  1;
							flagAndValue[1] =  1;
							flagAndValue[2] =  1;
							
							// Como fazer isto
							flagAndValue[3] =  tempIndices[2];
							flagAndValue[4] =  tempIndices[1];
							flagAndValue[5] =  tempIndices[0];
							
							return flagAndValue;
							
						}
						/*
						else {
							flagAndValueNF[0] =  1;
							flagAndValueNF[1] =  2;
							
							// Como fazer isto
							flagAndValueNF[3] =  tempIndices[0];
							flagAndValueNF[4] =  tempIndices[1];
							flagAndValueNF[5] =  tempIndices[2];
						}
						*/
					}
	    				
					
    			}
    		}
    	}
    	
    	for (int k = 0; k < 10; k++) {
			
			for(int u = 0; u < 2; u++) {
				int w = 1-u;
				for(int s = 2; s <= 3; s++) {
					for(int t = 1; t+s <=4  ; t++) {
    					
				    	if(areConsecutiveWith2Holes(narrays[k], u, w, s, t) && (narrays[k][2] == -1)) {
							String theseCards[] = new String[3];
							
							cnt = 0;
							theseCards = cardnArrays[k];
							for(int p = 0; p < 5; p++) {
								if((p != nIsJs[k][0]) && (p != nIsJs[k][1])) {
									tempIndices[cnt] = 4-p;
									cnt++;
								}
							}
			
							if(areFlushy(theseCards)) {
								// It's ok to return it because this is the best possible scenario 
								flagAndValue[0] =  1;
								flagAndValue[1] =  1;
								flagAndValue[2] =  1;
								cards[0] = movedCards[0];
								cards[1] = movedCards[1];
								cards[2] = movedCards[2];
								cards[3] = movedCards[3];
								cards[4] = movedCards[4];
								
								// Como fazer isto
								flagAndValue[3] =  tempIndices[2];
								flagAndValue[4] =  tempIndices[1];
								flagAndValue[5] =  tempIndices[0];
								
								return flagAndValue;
								
							}
							/*
							else {
								flagAndValueNF[0] =  1;
								flagAndValueNF[1] =  2;
								
								// Como fazer isto
								flagAndValueNF[3] =  tempIndices[0];
								flagAndValueNF[4] =  tempIndices[1];
								flagAndValueNF[5] =  tempIndices[2];
							}
							*/
						}
    				}
				}
			}
		}
    	
    	// Type 3
    	
    	for (int k = 0; k < 10; k++) {
    		if(countNumHighCards(cardArrays[k]) == 0) {
    			
    			// Estes fors ja so apanham os restos, por isso nao ha problema?...
    			for(int u = 0; u < 2; u++) {
    				int w = 1-u;
					for(int s = 2; s <= 3; s++) {
						for(int t = 1; t+s <=4  ; t++) {
	    					
					    	if(areConsecutiveWith2Holes(arrays[k], u, w, s, t)) {
								String theseCards[] = new String[3];
								
								cnt = 0;
								theseCards = cardArrays[k];
								for(int p = 0; p < 5; p++) {
									if((p != IsJs[k][0]) && (p != IsJs[k][1])) {
										tempIndices[cnt] = 4-p;
										cnt++;
									}
								}
				
								if(areFlushy(theseCards)) {
									System.out.println(theseCards[0] + theseCards[1] + theseCards[2]);
									// It's ok to return it because this is the best possible scenario 
									flagAndValue[0] =  1;
									flagAndValue[1] =  0;
									flagAndValue[2] =  1;
									
									// Como fazer isto
									flagAndValue[3] =  tempIndices[2];
									flagAndValue[4] =  tempIndices[1];
									flagAndValue[5] =  tempIndices[0];
									
									return flagAndValue;
									
								}
								/*
								else {
									flagAndValueNF[0] =  1;
									flagAndValueNF[1] =  2;
									
									// Como fazer isto
									flagAndValueNF[3] =  tempIndices[0];
									flagAndValueNF[4] =  tempIndices[1];
									flagAndValueNF[5] =  tempIndices[2];
								}
								*/
							}
	    				}
					}
    			}
    		}
    	}
    		
    		
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	// The highcards must be in the SFD itself! In the 3 cards that compose it
	    //int numHighCards = countNumHighCards(cards);
	    
	    //if (numHighCards >= 2) {
    	
    	
    	
    	
    	
    	
    
	    
	    
		
		
		
		
		return flagAndValue;
	}
	



	// Em alguns é mais fácil verificar quantas high cards tens do que o atual. Como o 4 to Royal Flush...
	public static int[] playPerfectly(String cards[]) {
		
		//1
		if(checkForRoyalStraightFlush(cards) == 1) {
			int[] myArray = {1,1,1,1,1};
			System.out.println("Here1");
			return myArray;
		}
		if(checkForStraightFlush(cards) == 1) {
			int[] myArray = {1,1,1,1,1};
			System.out.println("Here11");
			return myArray;
		}
		if(checkForFourOfAKind(cards)[0] == 1) {
			int[] myArray = {1,1,1,1,1};
			System.out.println("Here111");
			return myArray;			
		}
		int intArray[] = checkFor4toSF(cards);
		
		//2
		if((intArray[0] == 1) && (intArray[2] == 1)) {
			//System.out.println("Here2");

			if((getCardValue(cards[intArray[3]]) == 12) || ((intArray[1] == 1) && (getCardValue(cards[intArray[3]]) == 11))) {
				int[] myArray = {0,0,0,0,0};
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 4; j++) {
						if(intArray[j+3] == i) {
							myArray[i] = 1;
						}
					}

				}
				System.out.println("Here2");
				return myArray;
				
			}
		}
		//System.out.println("Here3");

		
		int intArray2[] = checkForThreeOfAKind(cards);
		//3
		if ((intArray2[0]==1) && (intArray2[1]==12)) {
			int[] myArray = {1,1,1,0,0};
			System.out.println("Here3");
			return myArray;
		}
		
		//4
		if(checkForFullHouse(cards)[0] == 1){
			int[] myArray = {1,1,1,1,1};
			System.out.println("Here4");
			return myArray;
	    }
	    if(checkForFlush(cards) == 1){
			int[] myArray = {1,1,1,1,1};
			System.out.println("Here44");
			return myArray;
		}
	    if(checkForStraight(cards) == 1){
			int[] myArray = {1,1,1,1,1};
			System.out.println("Here444");
			return myArray;
		}
	    
	    //5
	    
	    if(intArray2[0] == 1) {
	    	//Needs index of ToaK
			int[] myArray = {0,0,0,0,0};
			myArray[intArray2[2]] = 1;
			myArray[intArray2[2] + 1] = 1;
			myArray[intArray2[2] + 2] = 1;
			System.out.println("Here5");
			return myArray;

	    }
	    
	    //6
		if((intArray[0] == 1) && (intArray[2] == 1)) {
		
			int[] myArray = {0,0,0,0,0};
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 4; j++) {
					if(intArray[j+3] == i) {
						myArray[i] = 1;
					}
				}

			}
			System.out.println("Here6");
			return myArray;
		}
		
		//7
		int intArray3[] = checkForTwoPair(cards);
		if(intArray3[0] == 1) {
			int[] myArray = {0,0,0,0,0};
			myArray[intArray3[3]] = 1;
			myArray[intArray3[3] + 1] = 1;
			myArray[intArray3[4]] = 1;
			myArray[intArray3[4] + 1] = 1;
			System.out.println("Here7");
			return myArray;
		}

		//8
		int intArray7[] = checkForPair(cards);
		if (intArray7[0]==1 && intArray7[1]>=9 ){
			int[] myArray = {0,0,0,0,0};
			myArray[intArray7[2]] = 1;
			myArray[intArray7[2] + 1] = 1;
			System.out.println("Here8");
			return myArray;
		}
		
		//9
		int intArray5[] = howManyToFlush(cards);
		if((intArray5[0] == 1) && (intArray5[1] == 4)) {
			int[] myArray = {0,0,0,0,0};
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 4; j++) {
					if(intArray5[j+2] == i) {
						myArray[i] = 1;
					}
				}

			}
			System.out.println("Here9");
			return myArray;
		}
		
		//10
		
		int intArray4[] = checkForSFD(cards);
		if(intArray4[0] == 1) {
			String cards1[] = {cards[intArray4[3]], cards[intArray4[4]], cards[intArray4[5]]};
			if((countNumHighCards(cards1) == 3) || ((countNumHighCards(cards1) == 2) && (getCardValue(cards1[2]) == 8))) {
				int[] myArray = {0,0,0,0,0};
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 3; j++) {
						if(intArray4[j+3] == i) {
							myArray[i] = 1;
						}
					}

				}
				System.out.println("Here10");
				return myArray;
			}
		}
		
		
		//11
		
		int intArray11[] = checkFor4toSNF(cards);
		if((intArray11[0] == 1) && (intArray11[1] == 1)) {
			int[] myArray = {0,0,0,0,0};
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 4; j++) {
					if(intArray11[j+3] == i) {
						myArray[i] = 1;
					}
				}

			}
			System.out.println("Here11");
			return myArray;
		}
		
		//12
		if (intArray7[0]==1 && intArray7[1]<9 ){
			int[] myArray = {0,0,0,0,0};
			myArray[intArray7[2]] = 1;
			myArray[intArray7[2] + 1] = 1;
			System.out.println("Here12");
			return myArray;
		}
		
		//13
		if((intArray11[0] == 1)) {
			//System.out.println("Im here in 13: " + intArray[8] + intArray[9] + intArray[10] + intArray[11] );
			String cards1[] = {cards[intArray11[3]], cards[intArray11[4]], cards[intArray11[5]], cards[intArray11[6]]};
			if(countNumHighCards(cards1) == 4) {
				int[] myArray = {0,0,0,0,0};
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 4; j++) {
						if(intArray11[j+3] == i) {
							myArray[i] = 1;
						}
					}
				}
				System.out.println("Here13");
				return myArray;
			}
		}
		
		//14
		if((intArray4[0] == 1) && (intArray4[1] == 2)) {
			//String cards1[] = {cards[intArray4[3]], cards[intArray4[4]], cards[intArray4[5]]};
			int[] myArray = {0,0,0,0,0};
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 3; j++) {
					if(intArray4[j+3] == i) {
						myArray[i] = 1;
					}
				}

			}
			System.out.println("Here14");
			return myArray;
			
		}
		
		//15
		if((intArray11[0] == 1) && (intArray11[1] == 0)) {
			String cards1[] = {cards[intArray11[3]], cards[intArray11[4]], cards[intArray11[5]], cards[intArray11[6]]};
			if(countNumHighCards(cards1) == 3) {
				int[] myArray = {0,0,0,0,0};
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 4; j++) {
						if(intArray11[j+3] == i) {
							myArray[i] = 1;
						}
					}
				}
				System.out.println("Here15");
				return myArray;
			}
		}
		
		//16
		boolean isThereQueen = false;
		boolean isThereJack = false;
		int i1 =-1, i2 = -1;
		for(int i = 0; i < 5; i++) {
			if(getCardValue(cards[i]) == 10) {
				isThereQueen = true;
				i1 = i;
			}
		}
		for(int i = 0; i < 5; i++) {
			if(getCardValue(cards[i]) == 9) {
				isThereJack = true;
				i2 = i;
			}
		}
		if(isThereQueen && isThereJack) {	
			if(cards[i1].charAt(1) == cards[i2].charAt(1)) {
				int[] myArray = {0,0,0,0,0};
				myArray[i1] = 1;
				myArray[i2] = 1;
				System.out.println("Here16");
				return myArray;
			}
		}
		
		//17
		if((intArray5[0] == 1) && (intArray5[1] == 3)) {
			String cards1[] = {cards[intArray5[2]], cards[intArray5[3]], cards[intArray5[4]]};
			if(countNumHighCards(cards1) == 2) {
				int[] myArray = {0,0,0,0,0};
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 3; j++) {
						if(intArray5[j+2] == i) {
							myArray[i] = 1;
						}
					}

				}
				System.out.println("Here17");
				return myArray;
			}

		}
		
		//18
		
		if(countNumHighCards(cards) == 2) {
			int index1 = -1;
			int index2 = -1;
			for(int i = 0; i < 5; i++) {
				if((getCardValue(cards[i]) >= 9) && (index1 == -1)){
					index1 = i;
				}
				else if(getCardValue(cards[i]) >= 9) {
					index2 = i;
				}
			}
			if(cards[index1].charAt(1) == cards[index2].charAt(1)) {
				int[] myArray = {0,0,0,0,0};
				myArray[index1] = 1;
				myArray[index2] = 1;
				System.out.println("Here18");
				return myArray;
			}
		}
		
		//19
		if((intArray11[0] == 1) && (intArray11[1] == 0)) {
			String cards1[] = {cards[intArray11[3]], cards[intArray11[4]], cards[intArray11[5]], cards[intArray11[6]]};
			if(countNumHighCards(cards1) == 2) {
				int[] myArray = {0,0,0,0,0};
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 4; j++) {
						if(intArray11[j+3] == i) {
							myArray[i] = 1;
						}
					}
				}
				System.out.println("Here19");
				return myArray;
			}
		}
		
		//20
		if((intArray4[0] == 1) && (intArray4[1] == 1)) {
			//String cards1[] = {cards[intArray4[3]], cards[intArray4[4]], cards[intArray4[5]]};
			int[] myArray = {0,0,0,0,0};
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 3; j++) {
					if(intArray4[j+3] == i) {
						myArray[i] = 1;
					}
				}

			}
			System.out.println("Here20");
			return myArray;
			
		}
		
		
		
		//21
		if((intArray11[0] == 1) && (intArray11[1] == 0)) {
			String cards1[] = {cards[intArray11[3]], cards[intArray11[4]], cards[intArray11[5]], cards[intArray11[6]]};
			if(countNumHighCards(cards1) == 1) {
				int[] myArray = {0,0,0,0,0};
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 4; j++) {
						if(intArray11[j+3] == i) {
							myArray[i] = 1;
						}
					}
				}
				System.out.println("Here21");
				return myArray;
			}
		}
		
		//22
		boolean isThereKing = false;
		int i3 = -1;
		for(int i = 0; i < 5; i++) {
			if(getCardValue(cards[i]) == 11) {
				isThereKing = true;
				i3 = i;
			}
		}
		if(isThereQueen && isThereJack && isThereKing) {
			if(isThereKing) {
				int[] myArray = {0,0,0,0,0};
				myArray[i1] = 1;
				myArray[i2] = 1;
				myArray[i3] = 1;
				System.out.println("Here22");
				return myArray;
			}
		}
		
		//23
		boolean isThereTen = false;
		int i4 = -1;
		for(int i = 0; i < 5; i++) {
			if(getCardValue(cards[i]) == 8) {
				isThereTen = true;
				i4 = i;
			}
		}
		if(isThereJack && isThereTen) {
			if(cards[i2].charAt(1) == cards[i4].charAt(1)) {
				int[] myArray = {0,0,0,0,0};
				myArray[i2] = 1;
				myArray[i4] = 1;
				System.out.println("Here23");
				return myArray;
			}
		}
		
		//24
		if(isThereQueen && isThereJack) {
			int[] myArray = {0,0,0,0,0};
			myArray[i1] = 1;
			myArray[i2] = 1;
			System.out.println("Here24");
			return myArray;
			
		}
		
		//25
		if((intArray5[0] == 1) && (intArray5[1] == 3)) {
			String cards1[] = {cards[intArray5[2]], cards[intArray5[3]], cards[intArray5[4]]};
			if(countNumHighCards(cards1) == 1) {
				int[] myArray = {0,0,0,0,0};
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 3; j++) {
						if(intArray5[j+2] == i) {
							myArray[i] = 1;
						}
					}

				}
				System.out.println("Here25");
				return myArray;
			}

		}
		
		
		//26
		
		if(isThereQueen && isThereTen) {
			if(cards[i1].charAt(1) == cards[i4].charAt(1)) {
				int[] myArray = {0,0,0,0,0};
				myArray[i1] = 1;
				myArray[i4] = 1;
				System.out.println("Here26");
				return myArray;
			}
		}
		
		//27
		
		if((intArray4[0] == 1) && (intArray4[1] == 0)) {
			//String cards1[] = {cards[intArray4[3]], cards[intArray4[4]], cards[intArray4[5]]};
			int[] myArray = {0,0,0,0,0};
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 3; j++) {
					if(intArray4[j+3] == i) {
						myArray[i] = 1;
					}
				}

			}
			System.out.println("Here27");
			return myArray;
			
		}
		
		//28
		
		if(isThereKing && isThereQueen) {
			int[] myArray = {0,0,0,0,0};
			myArray[i1] = 1;
			myArray[i3] = 1;
			System.out.println("Here28");
			return myArray;
			
		}
		
		if(isThereKing && isThereJack) {
			int[] myArray = {0,0,0,0,0};
			myArray[i2] = 1;
			myArray[i3] = 1;
			System.out.println("Here2828");
			return myArray;
			
		}
		
		//29
		
		boolean isThereAce = false;
		int i5 = -1;
		for(int i = 0; i < 5; i++) {
			if(getCardValue(cards[i]) == 12) {
				isThereAce = true;
				i5 = i;
			}
		}
		if(isThereAce) {
			int[] myArray = {0,0,0,0,0};
			myArray[i5] = 1;
			System.out.println("Here29");
			return myArray;
		}
		
		
		//30
		if(isThereKing && isThereTen) {
			if(cards[i3].charAt(1) == cards[i4].charAt(1)) {
				int[] myArray = {0,0,0,0,0};
				myArray[i3] = 1;
				myArray[i4] = 1;
				System.out.println("Here30");
				return myArray;
			}
			
		}
		
		//31
		if(isThereKing) {
			int[] myArray = {0,0,0,0,0};
			myArray[i3] = 1;
			System.out.println("Here31");
			return myArray;
		}
		if(isThereQueen) {
			int[] myArray = {0,0,0,0,0};
			myArray[i1] = 1;
			System.out.println("Here3131");
			return myArray;
		}
		if(isThereJack) {
			int[] myArray = {0,0,0,0,0};
			myArray[i2] = 1;
			System.out.println("Here313131");
			return myArray;
		}
		
		//32
		
		if((intArray11[0] == 1) && (intArray11[1] == 0)) {
			String cards1[] = {cards[intArray11[3]], cards[intArray11[4]], cards[intArray11[5]], cards[intArray11[6]]};
			if(countNumHighCards(cards1) == 0) {
				int[] myArray = {0,0,0,0,0};
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 4; j++) {
						if(intArray11[j+3] == i) {
							myArray[i] = 1;
						}
					}
				}
				System.out.println("Here32");
				return myArray;
			}
		}
		
		//33
		if((intArray5[0] == 1) && (intArray5[1] == 3)) {
			String cards1[] = {cards[intArray5[2]], cards[intArray5[3]], cards[intArray5[4]]};
			if(countNumHighCards(cards1) == 0) {
				int[] myArray = {0,0,0,0,0};
				for(int i = 0; i < 5; i++) {
					for(int j = 0; j < 3; j++) {
						if(intArray5[j+2] == i) {
							myArray[i] = 1;
						}
					}

				}
				System.out.println("Here33");
				return myArray;
			}

		}
		
		
		int[] myArray = {0,0,0,0,0};
		return myArray;
	}
	
	// There is a danger in not checking immediately for a flush! You might return the first 4toS that you find, which might not be a 4toSF, but there might be another 4toS which is a 4toSF
	// It's currently checking for the flush at the same time
	
	public static int[] checkFor4toSF(String cards[]) {
	    int a = getCardValue(cards[0]);
	    int b = getCardValue(cards[1]);
	    int c = getCardValue(cards[2]);
	    int d = getCardValue(cards[3]);
	    int e = getCardValue(cards[4]);
	    
	    // If I'm already checking for Flush, do I need to return indices?
	    int flagAndValue[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //isThere, type (1=outside,0=inside), isItFlush, indices(4) + 1 flag + 4 indices in case of AKQJ (cause it's special, apparently...)
	    int tempIndices[] = {0,0,0,0};
	    // Criar arrays para terem versoes piores (mas que dao mais jeito organizar da forma atual na funçao)
	    // Tipo ter um flagAndValueOutsideNoFlush, para assim que virmos que nao ha um insideflush darmos return a esta

	    
	    
	    // Outside straights
	    int array[]={a,b,c,d};	
    	int array2[] ={a,b,c,e};    	
    	int array3[] ={a,b,d,e};
    	int array4[] ={a,c,d,e};
    	int array5[] ={b,c,d,e};
    	int arrays[][] = {array, array2, array3, array4, array5};
    	
		int newa = b, newb = c, newc = d, newd = e, newe = -1;
		if(a!=12) {
			newa = a;
			newb = b;
			newc = c;
			newd = d;
			newe = e;
		}
	    int narray[]={newa,newb,newc,newd};	
    	int narray2[] ={newa,newb,newc,newe};    	
    	int narray3[] ={newa,newb,newd,newe};
    	int narray4[] ={newa,newc,newd,newe};
    	int narray5[] ={newb,newc,newd,newe};
    	int narrays[][] = {narray, narray2, narray3, narray4, narray5};
    	
		String movedCards[] = new String[5];
		movedCards[0] = cards[1];
		movedCards[1] = cards[2];
		movedCards[2] = cards[3];
		movedCards[3] = cards[4];
		movedCards[4] = cards[0];
    	
    	
    	if(a!= 12) {
    		
    		for(int k = 0; k < 5; k++) {
    			if(areConsecutive(arrays[k])) {
    				String theseCards[] = new String[4];
    				
    				int cnt = 0;
    				for(int l = 0; l < 5; l++) {
    					if(l != 4-k) {
    						theseCards[cnt] = cards[l];
    						tempIndices[cnt] = l;
    						cnt++;
    					}
    				}
    				if(areFlushy(theseCards)) {
    					// It's ok to return it because this is the best possible scenario 
    					flagAndValue[0] =  1;
    					flagAndValue[1] =  1;
    					flagAndValue[2] =  1;
    					
    				
    					flagAndValue[3] =  tempIndices[0];
    					flagAndValue[4] =  tempIndices[1];
    					flagAndValue[5] =  tempIndices[2];
    					flagAndValue[6] =  tempIndices[3];
    					
    					return flagAndValue;
    					
    				}
    				else {
					}
    			}
    		}

    	}
	    /*
	    else if ((d==e) && areConsecutive(array)  && (a!=12)) {
	    	return flagAndValue;
	    }*/
	    
	    
	    // Inside straights
	    
	    // ==4 e depois if(a-b) == -1 {verificar se a gap está em b-c, else...} else está aí a gap, verificar se é sequência no resto
	    //int arrayT[] = {a,b,c,d,e};


    	else {
    		
    		if(b != 12) {
    			String theseCards[] = new String[4];
    			if(areConsecutive(arrays[4])) {
    				for(int l = 1; l < 5; l++) {
    					theseCards[l-1] = cards[l];
    				}
    				
    				if(areFlushy(theseCards)) {
    					// It's ok to return it because this is the best possible scenario 
    					flagAndValue[0] =  1;
    					flagAndValue[1] =  1;
    					flagAndValue[2] =  1;
    					
    				
    					flagAndValue[3] =  1;
    					flagAndValue[4] =  2;
    					flagAndValue[5] =  3;
    					flagAndValue[6] =  4;
    					
    					return flagAndValue;
    					
    				}
    			}
    		}
    		
    		
    		
    		
    		
    		
    		for(int l = 0; l < 4; l++) { // Only goes up to 4 because the last array doesn't have the ace!
    			if(areConsecutive(arrays[l])) {
    				String theseCards[] = new String[4];
    				
    				int cnt = 0;
    				for(int p = 0; p < 5; p++) {
    					if(p != 4-l) {
    						theseCards[cnt] = cards[p];
    						tempIndices[cnt] = p;
    						cnt++;
    					}
    				}
    				if(areFlushy(theseCards)) {
    					// It's ok to change it because this is the best possible scenario
    					System.out.println("Here");
    					flagAndValue[0] =  1;
    					flagAndValue[1] =  0;
    					flagAndValue[2] =  1;
    					
    				
    					flagAndValue[3] =  tempIndices[0];
    					flagAndValue[4] =  tempIndices[1];
    					flagAndValue[5] =  tempIndices[2];
    					flagAndValue[6] =  tempIndices[3];
    					return flagAndValue;
    					
    				}
    				else {
    				}
				}
    		}
    		
    		
    		
    		
    		// I'm not sure if this checks everything left...

    		

    		for(int l = 1; l < 5; l++) { // Only goes from 2 because the first array doesn't have the ace!
    			if(areConsecutive(narrays[l])) {
    				String theseCards[] = new String[4];
    				int cnt = 0;
    				// Esta brincadeira fica diferente porque os narrays nao correspondem às ordens normais!
    				// Agora talvez dê com o uso de um outro "cards"
    				for(int p = 0; p < 5; p++) {
    					if(p != 4-l) {
    						theseCards[cnt] = movedCards[p];
    						tempIndices[cnt] = p;
    						cnt++;
    					}
    				}
    				if(areFlushy(theseCards)) {
    					// It's ok to change it because this is the best possible scenario
    					flagAndValue[0] =  1;
    					flagAndValue[1] =  0;
    					flagAndValue[2] =  1;
    					
    				
    					flagAndValue[3] =  tempIndices[0];
    					flagAndValue[4] =  tempIndices[1];
    					flagAndValue[5] =  tempIndices[2];
    					flagAndValue[6] =  tempIndices[3];
    					
    					String tempCard = cards[0];
    		    		cards[0] = cards[1];
    		    		cards[1] = cards[2];
    		    		cards[2] = cards[3];
    		    		cards[3] = cards[4];
    		    		cards[4] = tempCard;
    					return flagAndValue;
    					
    				}
    				else {
					}
    			}
    		}
    		

		}

        for (int k = 0; k < 5; k++) {
        	System.out.println("InHere!");
	    	for(int i = 0; i < 3; i++) {
	    		
		    	if(areConsecutiveWithHole(arrays[k], i, 2)) {
		    		//System.out.println("sup");
		    		String theseCards[] = new String[4];
    				
    				int cnt = 0;
    				for(int p = 0; p < 5; p++) {
    					if(p != 4-k) {
    						theseCards[cnt] = cards[p];
    						tempIndices[cnt] = p;
    						cnt++;
    					}
    				}
    				if(areFlushy(theseCards)) {
    					// It's ok to change it because this is the best possible scenario
    					flagAndValue[0] =  1;
    					flagAndValue[1] =  0;
    					flagAndValue[2] =  1;
    					
    				
    					flagAndValue[3] =  tempIndices[0];
    					flagAndValue[4] =  tempIndices[1];
    					flagAndValue[5] =  tempIndices[2];
    					flagAndValue[6] =  tempIndices[3];
    					return flagAndValue;
    					
    				}
    				else {
					}
		    	}
   
	    	}
    	}
        for (int k = 0; k < 5; k++) {
	    	for(int i = 0; i < 3; i++) {
	    		
		    	if(areConsecutiveWithHole(narrays[k], i, 1)) {
		    		String theseCards[] = new String[4];
    				
    				int cnt = 0;
    				for(int p = 0; p < 5; p++) {
    					if(p != 4-k) {
    						theseCards[cnt] = movedCards[p];
    						tempIndices[cnt] = p;
    						cnt++;
    					}
    				}
    				if(areFlushy(theseCards)) {
    					// It's ok to change it because this is the best possible scenario
    					flagAndValue[0] =  1;
    					flagAndValue[1] =  0;
    					flagAndValue[2] =  1;
    					
    					String tempCard = cards[0];
    		    		cards[0] = cards[1];
    		    		cards[1] = cards[2];
    		    		cards[2] = cards[3];
    		    		cards[3] = cards[4];
    		    		cards[4] = tempCard;
    		    		
    					flagAndValue[3] =  tempIndices[0];
    					flagAndValue[4] =  tempIndices[1];
    					flagAndValue[5] =  tempIndices[2];
    					flagAndValue[6] =  tempIndices[3];
    					return flagAndValue;
    					
    				}
    				else {
					}
		    	}
   
	    	}
    	}
        
        
		return flagAndValue;
	    
	}
	
	
	
	public static int[] checkFor4toSNF(String cards[]) {
		int a = getCardValue(cards[0]);
	    int b = getCardValue(cards[1]);
	    int c = getCardValue(cards[2]);
	    int d = getCardValue(cards[3]);
	    int e = getCardValue(cards[4]);
	    
	    // If I'm already checking for Flush, do I need to return indices?
	    int flagAndValue[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}; //isThere, type (1=outside,0=inside), isItFlush, indices(4) + 1 flag + 4 indices in case of AKQJ (cause it's special, apparently...)
	    int tempIndices[] = {0,0,0,0};
	    // Criar arrays para terem versoes piores (mas que dao mais jeito organizar da forma atual na funçao)
	    // Tipo ter um flagAndValueOutsideNoFlush, para assim que virmos que nao ha um insideflush darmos return a esta
	    int flagAndValueONF[] = {0,0,0,0,0,0,0, 0, 0 ,0 ,0 ,0};
	    int flagAndValueINF[] = {0,0,0,0,0,0,0, 0, 0 ,0 ,0 ,0};
	    
	    
	    // Outside straights
	    int array[]={a,b,c,d};	
    	int array2[] ={a,b,c,e};    	
    	int array3[] ={a,b,d,e};
    	int array4[] ={a,c,d,e};
    	int array5[] ={b,c,d,e};
    	int arrays[][] = {array, array2, array3, array4, array5};
    	
		int newa = b, newb = c, newc = d, newd = e, newe = -1;
		if(a!=12) {
			newa = a;
			newb = b;
			newc = c;
			newd = d;
			newe = e;
		}
	    int narray[]={newa,newb,newc,newd};	
    	int narray2[] ={newa,newb,newc,newe};    	
    	int narray3[] ={newa,newb,newd,newe};
    	int narray4[] ={newa,newc,newd,newe};
    	int narray5[] ={newb,newc,newd,newe};
    	int narrays[][] = {narray, narray2, narray3, narray4, narray5};
    	
		String movedCards[] = new String[5];
		movedCards[0] = cards[1];
		movedCards[1] = cards[2];
		movedCards[2] = cards[3];
		movedCards[3] = cards[4];
		movedCards[4] = cards[0];
    	
    	
    	if(a!= 12) {
    		
    		for(int k = 0; k < 5; k++) {
    			if(areConsecutive(arrays[k])) {
    				String theseCards[] = new String[4];
    				
    				int cnt = 0;
    				for(int l = 0; l < 5; l++) {
    					if(l != 4-k) {
    						theseCards[cnt] = cards[l];
    						tempIndices[cnt] = l;
    						cnt++;
    					}
    				}
    				if(areFlushy(theseCards)) {
    					
    				}
    				else {
    					flagAndValueONF[0] =  1;
    					flagAndValueONF[1] =  1;
    					
    					flagAndValueONF[3] =  tempIndices[0];
    					flagAndValueONF[4] =  tempIndices[1];
    					flagAndValueONF[5] =  tempIndices[2];
    					flagAndValueONF[6] =  tempIndices[3];	
    					return flagAndValueONF;
					}
    			}
    		}

    	}
	    /*
	    else if ((d==e) && areConsecutive(array)  && (a!=12)) {
	    	return flagAndValue;
	    }*/
	    
	    
	    // Inside straights
	    
	    // ==4 e depois if(a-b) == -1 {verificar se a gap está em b-c, else...} else está aí a gap, verificar se é sequência no resto
	    //int arrayT[] = {a,b,c,d,e};


    	else {
    		
    		if(b != 12) {
    			String theseCards[] = new String[4];
    			if(areConsecutive(arrays[4])) {
    				for(int l = 1; l < 5; l++) {
    					theseCards[l-1] = cards[l];
    				}
    				
    				if(areFlushy(theseCards)) {

    					
    				}
    				else {
    					flagAndValueONF[0] =  1;
    					flagAndValueONF[1] =  1;
    					
    					flagAndValueONF[3] =  tempIndices[0];
    					flagAndValueONF[4] =  tempIndices[1];
    					flagAndValueONF[5] =  tempIndices[2];
    					flagAndValueONF[6] =  tempIndices[3];	
    					return flagAndValueONF;
					}
    			}
    		}
    		
    		
    		
    		
    		
    		
    		for(int l = 0; l < 4; l++) { // Only goes up to 4 because the last array doesn't have the ace!
    			if(areConsecutive(arrays[l])) {
    				String theseCards[] = new String[4];
    				
    				int cnt = 0;
    				for(int p = 0; p < 5; p++) {
    					if(p != 4-l) {
    						theseCards[cnt] = cards[p];
    						tempIndices[cnt] = p;
    						cnt++;
    					}
    				}
    				if(areFlushy(theseCards)) {

    					
    				}
    				else {
    					flagAndValueINF[0] =  1;
    					
    					flagAndValueINF[3] =  tempIndices[0];
    					flagAndValueINF[4] =  tempIndices[1];
    					flagAndValueINF[5] =  tempIndices[2];
    					flagAndValueINF[6] =  tempIndices[3];
    					return flagAndValueINF;
    					/*
    					flagAndValue[7] = 1; 
    					flagAndValue[8] =  tempIndices[0];
    					flagAndValue[9] =  tempIndices[1];
    					flagAndValue[10] =  tempIndices[2];
    					flagAndValue[11] =  tempIndices[3];
    					flagAndValueINF[7] = 1; 
    					flagAndValueINF[8] =  tempIndices[0];
    					flagAndValueINF[9] =  tempIndices[1];
    					flagAndValueINF[10] =  tempIndices[2];
    					flagAndValueINF[11] =  tempIndices[3];
    					flagAndValueONF[7] = 1; 
    					flagAndValueONF[8] =  tempIndices[0];
    					flagAndValueONF[9] =  tempIndices[1];
    					flagAndValueONF[10] =  tempIndices[2];
    					flagAndValueONF[11] =  tempIndices[3];
    					*/
    				}
				}
    		}
    		
    		
    		
    		
    		// I'm not sure if this checks everything left...

    		

    		for(int l = 1; l < 5; l++) { // Only goes from 2 because the first array doesn't have the ace!
    			if(areConsecutive(narrays[l])) {
    				String theseCards[] = new String[4];
    				int cnt = 0;
    				// Esta brincadeira fica diferente porque os narrays nao correspondem às ordens normais!
    				// Agora talvez dê com o uso de um outro "cards"
    				for(int p = 0; p < 5; p++) {
    					if(p != 4-l) {
    						theseCards[cnt] = movedCards[p];
    						tempIndices[cnt] = p;
    						cnt++;
    					}
    				}
    				if(areFlushy(theseCards)) {
    
    					
    				}
    				else {
    					flagAndValueINF[0] =  1;
    					String tempCard = cards[0];
    		    		cards[0] = cards[1];
    		    		cards[1] = cards[2];
    		    		cards[2] = cards[3];
    		    		cards[3] = cards[4];
    		    		cards[4] = tempCard;
    					flagAndValueINF[3] =  tempIndices[0];
    					flagAndValueINF[4] =  tempIndices[1];
    					flagAndValueINF[5] =  tempIndices[2];
    					flagAndValueINF[6] =  tempIndices[3];
					}
    			}
    		}
    		

		}

        for (int k = 0; k < 5; k++) {
        	System.out.println("InHere!");
	    	for(int i = 0; i < 3; i++) {
	    		
		    	if(areConsecutiveWithHole(arrays[k], i, 2)) {
		    		//System.out.println("sup");
		    		String theseCards[] = new String[4];
    				
    				int cnt = 0;
    				for(int p = 0; p < 5; p++) {
    					if(p != 4-k) {
    						theseCards[cnt] = cards[p];
    						tempIndices[cnt] = p;
    						cnt++;
    					}
    				}
    				if(areFlushy(theseCards)) {

    					
    				}
    				else {
    					flagAndValueINF[0] =  1;
    					
    					flagAndValueINF[3] =  tempIndices[0];
    					flagAndValueINF[4] =  tempIndices[1];
    					flagAndValueINF[5] =  tempIndices[2];
    					flagAndValueINF[6] =  tempIndices[3];
    					return flagAndValueINF;
					}
		    	}
   
	    	}
    	}
        for (int k = 0; k < 5; k++) {
	    	for(int i = 0; i < 3; i++) {
	    		
		    	if(areConsecutiveWithHole(narrays[k], i, 1)) {
		    		String theseCards[] = new String[4];
    				
    				int cnt = 0;
    				for(int p = 0; p < 5; p++) {
    					if(p != 4-k) {
    						theseCards[cnt] = movedCards[p];
    						tempIndices[cnt] = p;
    						cnt++;
    					}
    				}
    				if(areFlushy(theseCards)) {

    					
    				}
    				else {
    					flagAndValueINF[0] =  1;
    					String tempCard = cards[0];
    		    		cards[0] = cards[1];
    		    		cards[1] = cards[2];
    		    		cards[2] = cards[3];
    		    		cards[3] = cards[4];
    		    		cards[4] = tempCard;
    					flagAndValueINF[3] =  tempIndices[0];
    					flagAndValueINF[4] =  tempIndices[1];
    					flagAndValueINF[5] =  tempIndices[2];
    					flagAndValueINF[6] =  tempIndices[3];
    					return flagAndValueINF;
					}
		    	}
   
	    	}
    	}
        
        
        
        
        
		if(flagAndValueONF[0] == 1)
			return flagAndValueONF;		
		if(flagAndValueINF[0] == 1) {
			if (a==-1) {
				String tempCard = cards[0];
	    		cards[0] = cards[1];
	    		cards[1] = cards[2];
	    		cards[2] = cards[3];
	    		cards[3] = cards[4];
	    		cards[4] = tempCard;
			}
			return flagAndValueINF;
		}
		return flagAndValue;
	}
	
	public static boolean areConsecutive(int array[]) {
		int length = array.length;
		for(int i = 0; i < length-1; i++) {
			if(array[i] - array[i+1] != 1) return false;
		}
		return true;
	}
	
	
	
	public static boolean areFlushy(String cards[]) {
		int length = cards.length;
		for(int i = 0; i < length-1; i++) {
			if(cards[i].charAt(1) != cards[i+1].charAt(1)) return false;
		}
		return true;
	}
	
	
	
	public static boolean areConsecutiveWithHole(int array[], int holeIndex, int holeSize) {
		int length = array.length;
		for(int i = 0; i < length-1; i++) {
			if ((i != holeIndex) && (array[i] - array[i+1] != 1))
				return false;
			else if((i == holeIndex) && (array[i] - array[i+1] != holeSize))
				return false;
		}
		return true;
	}
	
	
	
	public static boolean areConsecutiveWith2Holes(int array[], int holeIndex, int holeIndex2, int holeSize, int holeSize2) {
		int length = array.length;
		for(int i = 0; i < length-1; i++) {
			if ((i != holeIndex) && (i!=holeIndex2) && (array[i] - array[i+1] != 1))
				return false;
			else if((i == holeIndex) && (array[i] - array[i+1] != holeSize))
				return false;
			else if ((i == holeIndex2) && (array[i] - array[i+1] != holeSize2))
				return false;
		}
		
		return true;
	}
	
	
	
	public static int countNumHighCards(String cards[]) {
		int count  = 0;
		for(int i = 0; i < cards.length; i++) {
			if (getCardValue(cards[i]) > 8) count++;
		}
		return count;
	}
	
	
	public static int[] howManyToFlush(String cards[]) {
		int[] rArray = {0,0,0,0,0,0,0};
		//5
		if(areFlushy(cards)) {
			rArray[0] = 1;
			rArray[1] = 5;
			rArray[2] = 0;
			rArray[3] = 1;
			rArray[4] = 2;
			rArray[5] = 3;
			rArray[6] = 4;
			return rArray;
			
		}
		
		//4
		String a = cards[0];
		String b = cards[1];
		String c = cards[2];
		String d = cards[3];
		String e = cards[4];
	    String array[]={a,b,c,d};	
    	String array2[] ={a,b,c,e};    	
    	String array3[] ={a,b,d,e};
    	String array4[] ={a,c,d,e};
    	String array5[] ={b,c,d,e};
    	String arrays[][] = {array, array2, array3, array4, array5};
    	
    	for(int i = 0; i < 5; i++) {
    		if(areFlushy(arrays[i])) {
    			System.out.println("Here in flushy4");
    			
    			int[] tempIndices= {0,0,0,0};
    			int cnt = 0;
				for(int l = 0; l < 5; l++) {
					if(l != 4-i) {
						tempIndices[cnt] = l;
						cnt++;
					}
					rArray[0] = 1;
					rArray[1] =  4;
					rArray[2] =  tempIndices[0];
					rArray[3] =  tempIndices[1];
					rArray[4] =  tempIndices[2];
					rArray[5] =  tempIndices[3];
				}
    			
    			return rArray;
    		}
    	}
		
		//3
	    String cardArrays[][] = new String[10][3];
    	int cnt = 0;
    	int IsJs[][] = new int[10][2];
    	for(int i = 0; i < 4; i++) {
    		for(int j = i+1; j < 5; j++) {
    			int cnt2 = 0;
    			for(int p = 0; p < 5; p++) {
    				
    				if((4-p != i) && (4-p != j)) {
    					//iarrays[cnt][cnt2] = array[p];
    					cardArrays[cnt][cnt2] = cards[p];
    					cnt2++;
    				}
    			}
    			IsJs[cnt][0] = i;
    			IsJs[cnt][1] = j;
    			cnt++;
    		}
    	}

    	for (int k = 0; k < 10; k++) {
    		if(areFlushy(cardArrays[k])) {
    			int tempIndices[] = new int[3];
				cnt = 0;
				for(int p = 0; p < 5; p++) {
					if((p != IsJs[k][0]) && (p != IsJs[k][1])) {
						tempIndices[cnt] = 4-p;
						cnt++;
					}
					
				}
				rArray[0] =  1;
				rArray[1] =  3;
				rArray[2] =  tempIndices[0];
				rArray[3] =  tempIndices[1];
				rArray[4] =  tempIndices[2];
				return rArray;
    		}

    	}
    	
    	return rArray;

		
	}
	
	

	
	
	
	
	

}
