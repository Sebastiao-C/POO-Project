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
		int flagAndValue[] = {0,0};
	    for(int i = 0; i < 4; i++){
	        if(cards[i].charAt(0) == cards[i+1].charAt(0)){
	            // flagAndValue[0] = 1 revela que há um par no conjunto (=0 revela que não) e flagAndValue[1] revela o valor das cartas desse par
	            flagAndValue[0] = 1;
	            flagAndValue[1] = getCardValue(cards[i]);
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
		int flagAndValue[] = {0,0};
	    for(int i = 0; i < 4; i++){
	        if((cards[i].charAt(0) == cards[i+1].charAt(0)) && (cards[i].charAt(0) != values[forbiddenValue])){
	            // flagAndValue[0] = 1 revela que h� um par no conjunto (=0 revela que n�o) e flagAndValue[1] revela o valor das cartas desse par.
	            flagAndValue[0] = 1;
	            flagAndValue[1] = getCardValue(cards[i]);
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
		int flagAndValue3[] = {0,0,0};
	    // flagAndValue[0] = 1 revela que h� um par no conjunto (=0 revela que n�o) e flagAndValue[1] revela o valor das cartas desse par.
	    // Este flagAndValue � tempor�rio, pelo que n�o � um argumento e � declarado dentro da fun��o.
	    int flagAndValue[] = checkForPair(cards);
	    if(flagAndValue[0] == 1){

	        // guarda o valor do par, que ser� o par mais alto do conjunto.
	        int max = flagAndValue[1];

	        // Procura um segundo par de valor diferente de max (o valor das cartas do primeiro par).
	        flagAndValue = checkForPair2(cards, max);
	        if(flagAndValue[0] == 1){
	            // Quando usamos flagAndValue3, precisamos de guardar 2 valores para al�m da flag, e o valor mais alto ou mais importante est� em
	            // flagAndValue[1]
	            flagAndValue3[0] = 1;
	            flagAndValue3[1] = max;
	            flagAndValue3[2] = flagAndValue[1];
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
		int flagAndValue[] = {0,0};
	    for(int i = 0; i < 3; i++){
	        if(getCardValue(cards[i]) == getCardValue(cards[i+1]) && getCardValue(cards[i+1]) == getCardValue(cards[i+2])){
	            // flagAndValue[0] = 1 revela que h� um par no conjunto (=0 revela que n�o) e flagAndValue[1] revela o valor das cartas desse trio.
	            flagAndValue[0] = 1;
	            flagAndValue[1] = getCardValue(cards[i]);
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

}
