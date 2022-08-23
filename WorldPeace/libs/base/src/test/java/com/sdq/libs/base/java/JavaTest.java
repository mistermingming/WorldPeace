package com.sdq.libs.base.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author songdongqi
 * @PackageName com.sdq.libs.base
 * @date 2022/7/19 11:53
 */
@RunWith(JUnit4.class)
public class JavaTest {
    @Test
    public void test01(){
        Map<String, List<String>> stockaccountMap = new HashMap<> ();
        stockaccountMap.put("S", new ArrayList<String>(Arrays.asList("s12","s35")));
        stockaccountMap.put("G", new ArrayList<String>(Arrays.asList("g5678","g36")));
        stockaccountMap.put("9", new ArrayList<String>(Arrays.asList("978","916")));
        stockaccountMap.put("1", new ArrayList<String>(Arrays.asList("123","124")));
        stockaccountMap.put("2", new ArrayList<String>(Arrays.asList("234","245")));

        Iterator<Map.Entry<String, List<String>>> iterator = stockaccountMap.entrySet().iterator();
        List<CharSequence> exTypeList = new ArrayList<CharSequence>(stockaccountMap.size());
        List<CharSequence> accountList = new ArrayList<CharSequence>(stockaccountMap.size());

        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> entry = iterator.next();
            List<String> tempAccList = entry.getValue();
            char key = entry.getKey().charAt(0);
            for (String account : tempAccList) {
                if (exTypeList.size() == 0) {
                    exTypeList.add(entry.getKey());
                    accountList.add(account);
                } else {
                    int index = 0;
                    for (int i = exTypeList.size() - 1; i > -1; i--) {
                        char c = exTypeList.get(i).charAt(0);
                        if (c > key) {
                            continue;
                        } else {
                            index = i + 1;
                            break;
                        }
                    }
                    exTypeList.add(index, entry.getKey());
                    accountList.add(index, account);
                }
            }
        }

        System.out.println("exTypeList = "+ exTypeList);
        System.out.println("accountList ="+ accountList);
    }


    @Test
    public void testArray(){
        CharSequence[][] mStockAccountArr =new CharSequence[2][];
        mStockAccountArr[0] = new CharSequence[]{"1","2","3","4"};
        mStockAccountArr[1] = new CharSequence[]{"s","a","v","q"};
        System.out.println("mStockAccountArr = "+ mStockAccountArr[0].length);

        CharSequence[][] copyValue = mStockAccountArr;
        System.out.println("copyValue = "+ copyValue[0].length);

        mStockAccountArr[0] = new CharSequence[]{"1"};
        System.out.println("copyValue1 = "+ copyValue[0].length);
    }
}
