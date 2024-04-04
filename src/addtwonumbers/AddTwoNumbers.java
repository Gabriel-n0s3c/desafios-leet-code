package addtwonumbers;

/**
 * Para a resolução desse desafio, acredito que seja importante imaginar exatamente como realizamos a operação de adição.
 *<br/> <br/>
 * POR EXEMPLO:
 * 348 +
 * 465
 *<br/>
 *<br/>
 * Nós começamos a operação da direita para a esquerda, e caso a soma dos números seja maior que 9 então passamos para
 * a próxima soma com o valor restante de 1.
 *<br/>
 *<br/>
 * EXEMPLO:
 *<br/>
 * 34(8) +  <br/>
 * 45(6)     <br/>
 *<br/>
 * 8 + 6 = 14 logo, o que acontece é: <br/> <br/>
 *
 * 3¹48 + <br/>
 *  456   <br/>
 *_____   <br/>
 *    004   <br/><br/>
 * Em resumo, caso a soma seja maior que 9, acontece o famoso "vai 1".<br/><br/>
 */
public class AddTwoNumbers {

    /**
     * DESAFIO: ADICIONAR 2 NÚMEROS:<br/><br/>
     *
     * Você recebe duas listas vinculadas não vazias que representam dois números inteiros não negativos.
     * Os dígitos são armazenados em ordem inversa e cada um de seus nós contém um único dígito.
     * Adicione os dois números e retorne a soma como uma lista vinculada.<br/><br/>
     *
     * Você pode assumir que os dois números não contêm nenhum zero à esquerda, exceto o próprio número 0.<br/><br/>
     *
     * Exemplo 1:<br/>
     *
     * Entrada: l1 = [8,4,3], l2 = [5,6,4]<br/>
     * Saída: [3,1,8]<br/>
     * Explicação: 342 + 465 = 813.
     * @param args
     */
    public static void main(String[] args) {

        ListNode l1 = new ListNode(8, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));


//        PARA ENTENDER A SOBRA DA ADIÇÃO
        System.out.println("O RESULTADO DA DIVISÃO DE 14 POR 10:" + 14/10);
        System.out.println("O RESTO DA DIVISÃO DE 14 POR 10:" +14%10);

        System.out.println(addTwoNumbers(l1,l2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resultado = new ListNode(0);
        ListNode ponteiro =  resultado;

        int sobra = 0;

        while(l1 != null || l2 != null)
        {
            int soma = sobra;

            if(l1!= null)
            {
                soma += l1.val;
                l1 = l1.next;
            }

            if(l2 != null){
                soma += l2.val;
                l2 = l2.next;
            }

//          ARMAZENA O VALOR DE 0 A 9, CASO SEJA MAIOR QUE 10, ENTÃO SALVA O VALOR RESTANTE DA DIVISÃO ex: 13/10 = 3
            ponteiro.next = new ListNode(soma % 10);

//          VERIFICA SE O VALOR É MAIOR QUE 10, E ARMAZENA O RESULTADO PARA A PRÓXIMA ITERAÇÃO
            sobra = soma / 10;

//          PASSAR O PONTEIRO PARA O PRÓXIMO DIGITO
            ponteiro = ponteiro.next;
        }

//        CASO O RESULTADO DA ÚLTIMA SOMA SEJA MAIOR QUE 10, ENTÃO O RESTANTE É ADICIONADO COMO UM NOVO DÍGITO
        if(sobra > 0){
            ponteiro.next = new ListNode(sobra);
        }
        return resultado.next;
    }
}



