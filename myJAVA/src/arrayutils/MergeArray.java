package arrayutils;

import java.io.File;

public class MergeArray {
    public static void main(String[] args) {
        /**
         * @author xuekun
         *动态接收参数
         *将给的数组合并成一个数组
         *转载请注明出处
         */
        /*public static File[] arrayCopy(File[]... arrays){
            //数组长度
            int arrayLength = 0;
            //目标数组的起始位置
            int startIndex = 0;

            for(File[] file : arrays){
                arrayLength = arrayLength + file.length;
            }

            File[] fileArray = new File[arrayLength];

            for(int i = 0; i < arrays.length; i++){

                if(i > 0){
                    //i为0 时，目标数组的起始位置为0 ,i为1时，目标数组的起始位置为第一个数组长度
                    //i为2时，目标数组的起始位置为第一个数组长度+第二个数组长度
                    startIndex = startIndex + arrays[i-1].length;
                }

                System.arraycopy(arrays[i], 0, fileArray, startIndex, arrays[i].length);

            }


            return fileArray;
        }*/


    }
}
