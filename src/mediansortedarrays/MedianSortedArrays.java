package mediansortedarrays;

import java.util.Arrays;

public class MedianSortedArrays {

    public static void main(String[] args) {

        int[] array1 = {1, 3};
        int[] array2 = {2};
    }

    //    SOLUÇÃO QUE FUNCIONA MAS DESEMPENHA MAL
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] resultado = new int[nums1.length + nums2.length];
        System.arraycopy(nums1, 0, resultado, 0, nums1.length);
        System.arraycopy(nums2, 0, resultado, nums1.length, nums2.length);

        Arrays.sort(resultado);

        if (resultado.length % 2 == 0) {
            int num1 = resultado[resultado.length / 2 - 1];
            int num2 = resultado[resultado.length / 2];
            return (double) (num1 + num2) / 2;
        } else {
            return resultado[resultado.length / 2];
        }
    }

    public static double findMedianSortedArraysPointers(int[] nums1, int[] nums2) {
        int num1Length = nums1.length;
        int num2Length = nums2.length;
        int ponteiroDireita = 0, ponteiroEsquerda = 0, median1 = 0, median2 = 0;

        // Encontrar a mediana.
        for (int count = 0; count <= (num1Length + num2Length) / 2; count++) {
            median2 = median1;
            if (ponteiroDireita != num1Length && ponteiroEsquerda != num2Length) {
                if (nums1[ponteiroDireita] > nums2[ponteiroEsquerda]) {
                    median1 = nums2[ponteiroEsquerda++];
                } else {
                    median1 = nums1[ponteiroDireita++];
                }
            } else if (ponteiroDireita < num1Length) {
                median1 = nums1[ponteiroDireita++];
            } else {
                median1 = nums2[ponteiroEsquerda++];
            }
        }

        // verifica se a soma é impar ou par
        if ((num1Length + num2Length) % 2 == 1) {
            return median1;
        } else {
            double ans = (double) median1 + (double) median2;
            return ans / 2.0;
        }
    }

    public  double findMedianSortedArraysBinarySearch(int[] arr1, int[] arr2){
        // MÉTODO DESENVOLVIDO NO PACOTE binarysearch
        return 0;
    };
}




