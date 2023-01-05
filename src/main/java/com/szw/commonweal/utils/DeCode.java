package com.szw.commonweal.utils;



public class DeCode {

    public static int deCode(String str){
        /**规则如下：
         *  先得到每位数，然后每位数都加上5，再对10求余，最后将所有数字反转，得到一串新数。
         *  根据以上规则解密：
         * */
        //1、定义数组记录解密之后的结果
        int[] arr = new int[str.length()];// 新建一个数组用来保存str每一位的数字
        for (int i = 0; i < str.length(); i++) {
            // 遍历str将每一位数字添加如intArray
            Character ch = str.charAt(i);
            arr[i] = Integer.parseInt(ch.toString());
        }
        //2、反转
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        //3、由于加密是通过对10取余的方式进行获取的
        // 所以在解密的时候就需要判断，0~4之间+10  5~9数字不变
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= 0 && arr[i] <= 4) {
                arr[i] = arr[i] + 10;
            }
        }
        //4、每位减5
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] - 5;
        }
        // 5、获取数组里面每一位数字拼接成最终结果
        int number = 0;
        for (int i = 0; i < arr.length; i++) {
            number = number * 10 + arr[i];
        }
        System.out.printf("解密后的密码:"+number);
        return number;
    }

}

