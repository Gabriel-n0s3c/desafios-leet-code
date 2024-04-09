package mediansortedarrays.binarysearch;

public class MedianSortedArraysBinarySearch {

    public static void main(String[] args) {
        int[] array1 = {1 , 3  , 8  , 9  , 15};
        int[] array2 = {7 , 11 , 19 , 21 , 25 };
        System.out.println("A média é: " + findMedianSortedArraysBinarySearch(array1, array2));
    }

    public static double findMedianSortedArraysBinarySearch(int[] arr1, int[] arr2) {
        int tamanhoArr1 = arr1.length;
        int tamanhoArr2 = arr2.length;

//    Vamos dar preferência por utilizar ARR1 como menor array para encontrar a partição esquerda correta.
        if (tamanhoArr1 > tamanhoArr2) {
            return findMedianSortedArraysBinarySearch(arr2, arr1);
        }

        int tamanhoTotalArr = tamanhoArr1 + tamanhoArr2;
        int particaoEsquerda = (tamanhoTotalArr + 1) / 2;
        int ponteiroInicial = 0, ponteiroFinal = arr1.length;

        while (ponteiroInicial <= ponteiroFinal) {

            //PROCESSO PARA ENCONTRAR A PARTIÇÃO ESQUERDA CORRETAMENTE
            int posicaoMedianaArr1 = (ponteiroInicial + ponteiroFinal ) / 2; //CALCULA A MEDIANA DO INTERVALO QUE ESTAMOS ANALISANDO DO ARR1
            int posicaoMedianaArr2 = particaoEsquerda - posicaoMedianaArr1; //CALCULA A MEDIANA DO INTERVALO QUE ESTAMOS ANALISANDO DO ARR2

            //DECLARA OS PONTEIROS PARA CADA ARRAY
            int valorMedianaArr1 =  (posicaoMedianaArr1 - 1 >= 0) ? arr1[posicaoMedianaArr1 - 1] : Integer.MIN_VALUE;
            int valorDireitaMedianaArr1  =  (posicaoMedianaArr1  < arr1.length) ? arr1[posicaoMedianaArr1 ] : Integer.MAX_VALUE;

            int valorMedianaArr2 = (posicaoMedianaArr2 - 1 >= 0) ? arr2[posicaoMedianaArr2 - 1] : Integer.MIN_VALUE;
            int valorDireitaMedianaArr2  = (posicaoMedianaArr2  < arr2.length) ? arr2[posicaoMedianaArr2 ] : Integer.MAX_VALUE;

            if (valorMedianaArr1 <= valorDireitaMedianaArr2 && valorMedianaArr2 <= valorDireitaMedianaArr1) {
                if (tamanhoTotalArr % 2 != 0)
                {
                    return Math.max(valorMedianaArr1, valorMedianaArr2);
                } else
                {
                    return ((double) (Math.max(valorMedianaArr1, valorMedianaArr2) + Math.min(valorDireitaMedianaArr1, valorDireitaMedianaArr2))) / 2.0;
                }
            } else if (valorMedianaArr1 > valorDireitaMedianaArr2) {
                ponteiroFinal = posicaoMedianaArr1 - 1;
            } else {
                ponteiroInicial = posicaoMedianaArr1 + 1;
            }
        }
        return 0;
    }
}




