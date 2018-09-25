package homework;

public class Juzhen {
    public static int[][] A={{1,2,3,4},
            {1,2,3,4},
            {1,2,3,4},
            {1,2,3,4}};

    public static int[][] B={{1,2,3,4},
            {1,2,3,4},
            {1,2,3,4},
            {1,2,3,4}};

    public static void main(String[] args){
        System.out.println(matrixMulti(A,B));
    }






    public static int[][] matrixMulti(int[][] A,int[][] B){
        int[][] intss = new int[A.length][B.length];
        //遍历A的行
        for(int i=0;i<A.length;i++){
            //遍历B的列
            for (int j=0;j<B.length;j++){
                //遍历B的每一行
                for(int k=0;k<B.length;k++){
                    intss[i][j]=A[i][j]*B[k][j];
                }

            }
        }



        return intss;
    }
}
