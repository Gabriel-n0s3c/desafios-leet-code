# Encontrar a mediana de arrays ordenados utilizando busca binária - JAVA

Este código implementa o algoritmo para encontrar a mediana de dois arrays ordenados. 
A ideia é usar o método de pesquisa binária para encontrar a posição correta de particionamento dos arrays, 
de forma que os elementos à esquerda e à direita da partição tenham tamanhos iguais ou difiram em apenas 1 elemento.

O problema pode ser resolvido de maneira simples, e menos performática. 
Basta unir os arrays, reordenar o novo array e com base em seu tamanho determinar as medianas.


### OBS USAREMOS COMO ENTRADA DE EXEMPLO OS VALORES:
```
  arr1:   | 1 | 3  | 8  | 9  | 15 |
  arr2:   | 7 | 11 | 19 | 21 | 25 |
```

## Aqui está uma explicação passo a passo do algoritmo:


## 1. Inicialmente, determina-se qual dos dois arrays, arr1 ou arr2, é o menor. O código dá preferência por usar arr1 como o array menor para encontrar a partição esquerda correta. Se arr1 for maior que arr2, os arrays são invertidos chamando novamente a função..

```java
 if (tamanhoArr1 > tamanhoArr2) {
            return findMedianSortedArraysBinarySearch(arr2, arr1);
        }
```

## 2. particaoEsquerda é calculada como (tamanhoTotalArr + 1) / 2, o que é a posição que a mediana estaria se considerássemos a concatenação dos dois arrays ordenados.
***(tamanhoTotalArr + 1) / 2***
Quando a soma dos tamanhos dos arrays é ímpar, adicionar +1 assegura que a parte esquerda da partição (que contém a mediana) seja maior ou igual à parte direita. Isso é necessário porque, quando o número total de elementos é ímpar, queremos que a mediana esteja incluída na parte esquerda.

## 3. Inicializa-se dois ponteiros ponteiroEsquerda e ponteiroDireita para arr1. O ponteiro ponteiroEsquerda aponta para o início do array arr1, enquanto o ponteiro ponteiroDireita aponta para o final do array arr1.

```java
  int tamanhoTotalArr = tamanhoArr1 + tamanhoArr2;  // 10
  int particaoEsquerda = (tamanhoTotalArr + 1) / 2; // 5
  int ponteiroInicial = 0, ponteiroFinal = arr1.length; // 0 , 5
```
## 4. O algoritmo entra em um loop enquanto o ponteiroInicial for menor ou igual ao ponteiroFinal.

Dentro do loop, é calculada a mediana das duas partições, uma do arr1 e outra do arr2. Isso é feito calculando os elementos à esquerda e à direita das posições de particionamento em ambos os arrays.

### 4.1 Definindo medianas dos arrays 1 e 2 
```java
    int posicaoMedianaArr1 = (ponteiroInicial + ponteiroFinal ) / 2; // (0 + 5) / 2 = 2
    int posicaoMedianaArr2 = particaoEsquerda - posicaoMedianaArr1;  //  5 - 2 = 3
```

Neste caso, o ponto médio de cada array é onde a mediana pode ser encontrada. Vamos representar isso em um gráfico:

### Primeira iteração: 
```yaml
#índices  0   1   2   3    4
arr1:   | 1 | 3 | 8 | 9 | 15 |
        +---+---+---+---+-----+
                  ^
                  |
                  Mediana de arr1: 8

#índices  0    1    2    3   4
arr2:   | 7  | 11 | 19 | 21 | 25 |
        +----+----+----+----+-----+
                         ^
                         |
                         Mediana de arr2: 21
```

### Preenche os valores da mediana e seu valor subsequente de cada array, para posteriormente validar a partição selecionada.
Repare que caso o valor a direita não exista no array, então será atribuido o **MAIOR** inteiro para ele. E caso `````` valorMedianaArr1 - 1 `````` seja menor que zero então o **MENOR** inteiro será atribuído a ela. 

```java

//DEFININDO MEDIANA E VALOR SUBSQUENTE DO ARR1
 int valorMedianaArr1        = (posicaoMedianaArr1 - 1 >= 0) ? arr1[posicaoMedianaArr1 - 1] : Integer.MIN_VALUE;
 int valorDireitaMedianaArr1 = (posicaoMedianaArr1  < arr1.length) ? arr1[posicaoMedianaArr1 ] : Integer.MAX_VALUE;

//DEFININDO MEDIANA E VALOR SUBSQUENTE DO ARR2
 int valorMedianaArr2        = (posicaoMedianaArr2 - 1 >= 0) ? arr2[posicaoMedianaArr2 - 1] : Integer.MIN_VALUE;
 int valorDireitaMedianaArr2 = (posicaoMedianaArr2  < arr2.length) ? arr2[posicaoMedianaArr2 ] : Integer.MAX_VALUE;
```

### Graficamente ficaria assim:

```yaml
#índices  0   1   2   3    4
arr1:   | 1 | 3 | 8 | 9 | 15 |
        +---+---+---+---+-----+
              ^   ^
              |   |
              |   Valor subsequente: 3 - valorDireitaMedianaArr1
              |
              Mediana de arr1: 8 - valorMedianaArr1 

#índices  0    1    2    3    4
arr2:   | 7  | 11 | 19 | 21 | 25 |
        +----+----+----+----+-----+
                    ^    ^
                    |    |
                    |    Valor subsequente: 21 - valorDireitaMedianaArr2
                    |
                    Mediana de arr2: 19 - valorMedianaArr2 
```

### 4.2 Com os valores definidos, é necessário verificar se foi particionado corretamente, mas antes, o que seria considerado particionamento correto?
Teremos particionado corretamente, caso o valor **valorDireitaMedianaArr1** seja **<= (menor ou igual)** ao valor **valorDireitaMedianaArr2** E também se
o valor **valorDireitaMedianaArr2** seja **<= (menor ou igual)** ao valor **valorDireitaMedianaArr1**;

### GRAFICAMENTE:

```yaml
    valorMedianaArr1
              ^  
              |  valorDireitaMedianaArr1
              |   ^
              |   |
arr1:   | 1 | 3 | 8 | 9 | 15 |
        +---+---+---+---+-----+
              ^   ^
              |   |
              |   |
              \___|___
                  |   \             3 <= 21 #true
                   \   \            19 <= 8 #false
                    |   \
                    |    \
                    |     |
                    |     |
arr2:   | 7  | 11 | 19 | 21 | 25 |
        +----+----+----+----+-----+
                    |     |
                    |     |
                    |     valorDireitaMedianaArr2          
         valorMedianaArr2  

                   
```

### Nesse caso queremos ver se:
-  **valorMedianaArr1 = 3** é menor ou igual **<=** que o subsequente de **valorMedianaArr2 = 19** que é o valor **valorDireitaMedianaArr2 = 21**   
    - 3 <= 21 = true
-  **valorMedianaArr2 = 19** é menor ou igual **<=** que o subsequente de **valorMedianaArr1 = 3** que é o valor **valorDireitaMedianaArr1 = 8**    
    -  

### 4.2 Validando a partição selecionada:

```java
                
        if (valorMedianaArr1 <= valorDireitaMedianaArr2 && // 3 <= 21 = true 
            valorMedianaArr2 <= valorDireitaMedianaArr1)   // 19 <= 8 = false
        {
            ...// CÓDIGO PARA VERIFICAR SE O ARRAY TEM TAMANHO ÍMPAR OU PAR;

        } else if (valorMedianaArr1 > valorDireitaMedianaArr2) {
            ponteiroFinal = posicaoMedianaArr1 - 1; //MOVE O PONTEIRO FINAL PARA A ESQUERDA DA MEDIANA CALCULADA
        } else {
            ponteiroInicial = posicaoMedianaArr1 + 1; //MOVE O PONTEIRO INICIAL PARA A DIREITA DA MEDIANA CALCULADA
        }
```

### Como vimos anteriormente o valor **valorMedianaArr2** não é <= que **valorDireitaMedianaArr2**, logo nossa partição ainda não está correta.
#### Para selecionarmos corretamente precisamos redefinir nossos ponteiros. Para isso é necessário saber se devemos mover para a esquerda ou a direita da partição inicial. 
<br>
<br>

### Caso valorMedianaArr1 > valorDireitaMedianaArr2, então devemos mover o ponteiro final para a esquerda

#### GRAFICAMENTE:
```java 
ponteiroFinal = posicaoMedianaArr1 - 1 // 2 - 1 = 1
```


```yaml
   
#ANTES DE MOVER
arr1:   | 1 | 3 | 8 | 9 | 15 | null
        +---+---+---+---+-----+
          ^                      ^   
          |                      |
          |                 ponteiroFinal = 5
   ponteiroInicial = 0  


#DEPOIS DE MOVER
arr1:   | 1 | 3 | 8 | 9 | 15 |
        +---+---+---+---+-----+
          ^   ^   
          |   |
          |  ponteiroFinal = 1
          |
   ponteiroInicial = 0 
                   

 #RESULTADO DA NOVA PARTIÇÃO NO ARR1:        
 arr1:   | 1 | 3 |
         +---+----+
           ^   ^   
           |   |
           |  ponteiroFinal = 1
           |
    ponteiroInicial = 0           
```

### Caso contrário, ou seja, caso o valorMedianaArr1 < valorDireitaMedianaArr2, então devemos mover o ponteiro inicial para a direita

#### GRAFICAMENTE:
```java 
ponteiroInicial = posicaoMedianaArr1 + 1 // 2 + 1 = 3
```

```yaml
   
#ANTES DE MOVER
arr1:   | 1 | 3 | 8 | 9 | 15 | null
        +---+---+---+---+-----+
          ^                      ^   
          |                      |
          |                 ponteiroFinal = 5
   ponteiroInicial = 0  


#DEPOIS DE MOVER
arr1:   | 1 | 3 | 8 | 9 | 15 | null
        +---+---+---+---+-----+
                      ^         ^   
                      |         |
                      |    ponteiroFinal = 5
               ponteiroInicial = 1 


#RESULTADO DA NOVA PARTIÇÃO NO ARR1:
arr1:   | 9 | 15 | null
        +---+-----+
           ^        ^   
           |        |
           |        ponteiroFinal = 5
           |
   ponteiroInicial = 1 

```

### No caso do exemplo, o **valorMedianaArr1 < valorDireitaMedianaArr2**, então o o resultado do novo intervalo seria:
```yaml
#RESULTADO DA NOVA PARTIÇÃO NO ARR1:
arr1:   | 9 | 15 | null
        +---+-----+
           ^        ^   
           |        |
           |        ponteiroFinal = 5
           |
   ponteiroInicial = 1 

```

### Chegando neste ponto, o laço se repete, mas agora com uma nova mediana a ser calculada:
### Segunda iteração: 
```java
    int posicaoMedianaArr1 = (ponteiroInicial + ponteiroFinal ) / 2; // (3 + 5) / 2 = 4
    int posicaoMedianaArr2 = particaoEsquerda - posicaoMedianaArr1;  //  5 - 1 = 4


     int valorMedianaArr1 =  (posicaoMedianaArr1 - 1 >= 0) ? arr1[posicaoMedianaArr1 - 1] : Integer.MIN_VALUE; // 9 
     int valorDireitaMedianaArr1  =  (posicaoMedianaArr1  < arr1.length) ? arr1[posicaoMedianaArr1 ] : Integer.MAX_VALUE; // 15

     int valorMedianaArr2 = (posicaoMedianaArr2 - 1 >= 0) ? arr2[posicaoMedianaArr2 - 1] : Integer.MIN_VALUE; // 7 
     int valorDireitaMedianaArr2  = (posicaoMedianaArr2  < arr2.length) ? arr2[posicaoMedianaArr2 ] : Integer.MAX_VALUE; //11

```

```yaml
#índices  0   1   2   3    4
arr1:   | 1 | 3 | 8 | 9 | 15 |
        +---+---+---+---+----+
                      ^    ^
                      |    |
                      |    Valor subsequente: 15 - valorDireitaMedianaArr1
                      |
                      Mediana de arr1: 9 - valorMedianaArr1 

#índices  0    1    2    3    4
arr2:   | 7  | 11 | 19 | 21 | 25 |
        +----+----+----+----+-----+
           ^    ^
           |    |
           |    Valor subsequente: 11 - valorDireitaMedianaArr2
           |
           Mediana de arr2: 7 - valorMedianaArr2
```
### Validar se a partição está correta:

```yaml
            valorMedianaArr1
                      ^  
                      |  valorDireitaMedianaArr1
                      |   ^
                      |   |
arr1:   | 1 | 3 | 8 | 9 | 15 |
        +---+---+---+---+-----+
                      ^    ^
                      |    |
                 _____|    |       
             ___|__________|                 
            /   |                   9 <= 11 
           /    |                   7 <= 15
          |     |           
          |     |            
arr2:   | 7  | 11 | 19 | 21 | 25 |
        +----+----+----+----+-----+
          |     |
          |     |
          |     valorDireitaMedianaArr2          
  valorMedianaArr2  
                   
```

```java
                
        if (valorMedianaArr1 <= valorDireitaMedianaArr2 && // 9 <= 11 = true 
            valorMedianaArr2 <= valorDireitaMedianaArr1)   // 7 <= 15 = true
        {
            ...// CÓDIGO PARA VERIFICAR SE O ARRAY TEM TAMANHO ÍMPAR OU PAR;

        } else if (valorMedianaArr1 > valorDireitaMedianaArr2) {
            ponteiroFinal = posicaoMedianaArr1 - 1; //MOVE O PONTEIRO FINAL PARA A ESQUERDA DA MEDIANA CALCULADA
        } else {
            ponteiroInicial = posicaoMedianaArr1 + 1; //MOVE O PONTEIRO INICIAL PARA A DIREITA DA MEDIANA CALCULADA
        }
```

### Nesse caso a partição está correta. Logo, nos resta retornar a mediana, que pode variar dependendo do tamanho total do array, que pode ser ímpar ou par:

```java
                
        if (valorMedianaArr1 <= valorDireitaMedianaArr2 && // 9 <= 11 = true 
            valorMedianaArr2 <= valorDireitaMedianaArr1)   // 7 <= 15 = true
        {
            
            if (tamanhoTotalArr % 2 != 0) // 10 % 2 = 0 --> false (não é ímpar)
                {
                    return Math.max(valorMedianaArr1, valorMedianaArr2);
                } else
                {
                    return ((double) (Math.max(valorMedianaArr1, valorMedianaArr2) + Math.min(valorDireitaMedianaArr1, valorDireitaMedianaArr2))) / 2.0;
                }

        } else if (valorMedianaArr1 > valorDireitaMedianaArr2) {
            ponteiroFinal = posicaoMedianaArr1 - 1; //MOVE O PONTEIRO FINAL PARA A ESQUERDA DA MEDIANA CALCULADA
        } else {
            ponteiroInicial = posicaoMedianaArr1 + 1; //MOVE O PONTEIRO INICIAL PARA A DIREITA DA MEDIANA CALCULADA
        }
```

### Porque o retorno muda quando o array tem tamanho par? 
Quando o array for ímpar basta retornar o maior valor encontrado entre as medianas encontradas. 
Porém, quando o array tiver tamanho par, então é necessário somar o **maior** valor entre as medianas, e o **menor** valor entre os valores subsequentes.

### Array de tamanho par
Com base nos valores que usamos no exemplo, se fizessimos a uniãos dos arrays e depois a sua ordenação, teríamos como resultado as medianas ``9 e 11``, que após o cálculo ``(9 + 11) / 2 = 10``
```yaml

união:  | 1 | 3 | 7 | 8 | 9 | 11 | 15 | 19 | 21 | 25 |
        +---+---+---+---+---+----+----+----+----+-----+
                          ^    ^
                          |    |
                          |    | 
                        Medianas encontradas

```

### Array de tamanho ímpar
Caso adicionássemos mais um valor em array de exemplo, ele passaria a ter tamanho ímpar: `
```yaml

união:  | 1 | 3 | 7 | 8 | 9 | 11 | 15 | 19 | 21 | 25  | 27 |
        +---+---+---+---+---+----+----+----+----+-----+----+
                              ^                         ^
                              |                         |
                              |                  Valor adicionado
                        Mediana encontrada
```
