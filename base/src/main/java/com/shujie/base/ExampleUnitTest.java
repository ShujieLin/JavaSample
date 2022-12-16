package com.shujie.base;

import com.kovan.secure.KovanSecure;
import com.pax.commonlib.utils.LogUtils;
import com.pax.poslib.gl.convert.ConvertHelper;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private String result;
    private int lenSign;

    @Test
    public void addition_isCorrect() {
        String hexStr = "425241424D6A45304E4445784D7A55314D6A6B33" +
                "4241554141424267434D694141414341" +
                "41452B3172464141414A3659436F7558" +
                "6C567751414155594141414545434946" +
                "4A4A6D5A6D5A55414151515141414141" +
                "414141416E796342674A385145733051" +
                "6F414142496741414141414141414141" +
                "414141412F353844414A383041774141" +
                "4170383141535366486768555A584A74" +
                "61573568624951486F41414141415151" +
                "454A384A416741436E30454541414142" +
                "45513D3D";

        //加密数据
        String encryptedData = "6A 47 2F 4C 6B 42 69 48 58 62" +
                "4E 6C 52 79 56 4F 4E 4E 75 32" +
                "6E 5A 37 72 6D 58 45 69 4D 71" +
                "47 72 7A 6D 77 63 49 4B 2B 2F" +
                "70 63 4C 39 54 46 45 49 4E 50" +
                "4C 79 46 6C 4F 6E 57 55 57 42" +
                "71 58 7A 77 2B 75 43 30 77 76" +
                "36 64 45 36 7A 78 78 73 41 32" +
                "72 51 37 76 48 39 58 41 6E 4B" +
                "49 7A 73 55 2B 6C 51 44 36 44" +
                "6C 46 53 63 77 4B 61 56 43 44" +
                "37 76 74 41 79 31 6D 42 62 61" +
                "51 72 53 38 71 79 6A 38 ";

        //        base64Decry(encryptedData);

        //转成成ASCII码
        //        String encodedStr = DataConvertUtils.hexStr2AsciiStr(encryptedData,true);
        //        String encodedStr = DataConvertUtils.removeEmptyChar(encryptedData);
        //        System.out.println("encodedStr = " + encodedStr);
        //        base64Decode(encodedStr);

        //base64Decode2Str("41414151");
        //System.out.println();
        //base64Encode2Str(" 0x00, 0x00,10");

        base64Decode2Str(hexStr);
    }

    private void base64Encode2Str(String s) {
        byte[] bytes = ConvertUtils.strToBcdPaddingLeft(s);
        String encode = Base64.getEncoder().encodeToString(bytes);
        System.out.println("encode = " + encode);
        String hexString = DataConvertUtils.string2HexString(encode);
        System.out.println("hexString = " + hexString);
    }

    public void base64Decode2Str(String hexStr) {
        //转成成ASCII码
        String encodedStr = DataConvertUtils.hexStr2AsciiStr(hexStr, true);
        System.out.println("encodedStr = " + encodedStr);

        //base64解码
        byte[] decodedBytes = Base64.getDecoder().decode(encodedStr);
        System.out.println(Arrays.toString(decodedBytes));

        String decodedStr = new String(decodedBytes, StandardCharsets.UTF_8);
        System.out.println("base64 解码后 = " + decodedStr);

        System.out.println("bcd2Str = " + ConvertUtils.bcd2Str(decodedStr.getBytes()));
    }

    /**
     * base64解码
     */
    public void base64Decode(String str) {

        //base64解码
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        System.out.println(Arrays.toString(decodedBytes));

        String decodedStr = new String(decodedBytes, StandardCharsets.UTF_8);
        System.out.println("base64 解码后 = " + decodedStr);

        System.out.println("bcd2Str = " + ConvertUtils.bcd2Str(decodedStr.getBytes()));
    }

    @Test
    public void testJulianDate() {
        //2022年3月04日是2064。2 064
        String transDate = "220101";

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt("20" + transDate.substring(0, 2)),
                Integer.parseInt(transDate.substring(2, 4)),
                Integer.parseInt(transDate.substring(4, 6))
        );
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        String julianDay = transDate.substring(1, 2) + dayOfYear;
        System.out.println("transDate = " + transDate + " julianDay = " + julianDay);
    }

    @Test
    public void testJulianDate2() {

        Calendar calendar = new GregorianCalendar(2022, 1, 1);
        Calendar calendar2 = new GregorianCalendar(2022, 2, 1);
        int i = calendar2.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
        //        calendar.set(2022, 1,1);
        System.out.println("时间 " + calendar.getTime() + " dayofyear = " + i);
    }

    @Test
    public void testJulianDate3() {

        int dayOfYear = new Solution().dayOfYear("2022-3-4");
        System.out.println("时间 " + " dayofyear = " + dayOfYear);
    }

    class Solution {
        public int dayOfYear(String date) {
            /**
             * 首先获取年月日
             */
            String[] split = date.split("-");
            /**
             * 年
             */
            int year = Integer.parseInt(split[0]);
            /**
             * 月
             */
            int month = Integer.parseInt(split[1]);
            /**
             * 日
             */
            int day = Integer.parseInt(split[2]);

            /**
             * 创建一个12个月的天数数组
             */
            int[] allDate = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

            /**
             * 判断是否是闰年，如果是闰年，则二月是29天
             */
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                ++allDate[1];
            }

            /**
             * 定义一个天数
             */
            int days = 0;

            /**
             * 循环一个i，拿到本月前一个月的数组坐标，进行天数相加
             */
            for (int i = 0; i < month - 1; i++) {
                days += allDate[i];
            }

            /**
             * 本月前所有天数的和 + 本月的天数 return 这是一年中的第几天。
             */
            return days + day;
        }
    }

    @Test
    public void testInt2String() {
        String s = ConvertUtils.bcd2Str(ConvertUtils.intToByteArray(0x9f10,
                ConvertUtils.EEndian.BIG_ENDIAN));
        System.out.println("----" + s);
    }

    @Test
    public void decode_() {
        int emvIndex = 185;
        String source =
                "02014131D234313030313143202020202020202020313634383839373239372A2A2A2A2A2A3236202032303120204141414230313238474E366B5A6A4C647A7376766D482B433178527972776D2B32315237515A3075796E66673964796E427965616C715A697834736D463950385756555633546C5A614A4A512F42307A5151507A3768716D6E75727470442B667661364E4A637939366830504D6B7A455A335A41387947726D5772714178574D36676F785473444930305630303932425241424D6A49354D6A67794D4445324D5451774E536A4967494141694141387569483142676F5341364367414141396B49446C68667873355143574741414142424169454267414141414145415145454141414141414141413D3D4455414C504159444536333320202020563130322020202020202020202020203130303030303030303103F0";
        String emvData = source.substring(emvIndex * 2);
        String emvDataLen = emvData.substring(0, 4 * 2);
        int len = DataConvertUtils.asciiString2Int(emvDataLen, 4);
        String emvEncData = emvData.substring(4 * 2, len * 2);
        byte[] bytes =
                KovanSecure.decodeBASE64(new String(ConvertUtils.strToBcdPaddingLeft(emvEncData)));
        LogUtils.hexLog("emvDecrypted", "%s", bytes);
    }

    @Test
    public void testFillWithEmptyPoints() {
        /*fillWithEmptyPoints(11, 11);
        fillWithEmptyPoints(9, 9);
        fillWithEmptyPoints(10, 9);*/
        fillWithEmptyPoints(7, 10);
        fillWithEmptyPoints(7, 12);
        fillWithEmptyPoints(10, 15);
        fillWithEmptyPoints(11, 13);
        fillWithEmptyPoints(11, 12);
        fillWithEmptyPoints(20, 2);
        /*fillWithEmptyPoints(10, 13);
        fillWithEmptyPoints(13, 10);*/
        /*fillWithEmptyPoints(12, 12);*/
    }

    private int preX = 10;
    private int preY = 10;

    private void fillWithEmptyPoints(int xCoordinate, int yCoordinate) {
        int x = xCoordinate;
        int y = yCoordinate;
        //发送第一个当前获取到的坐标
        System.out.printf("--------begin--------- (%d :%d)  (%d :%d)", preX, preY, x, y);

        //算法补偿
        while (true) {
            int dValueX = Math.abs(x - preX);
            int dValueY = Math.abs(y - preY);
            System.out.println("dValueX = " + dValueX + " dValueY = " + dValueY);

            //假如用户画点，退出循环,假如当前没有漏点，退出循环
            if ((dValueX > 5 && dValueY > 5) || (dValueX <= 1 && dValueY <= 1)) {
                preX = x;
                preY = y;
                System.out.printf("send(%d,%d)", x, y);
                break;
            }

            if (dValueX > dValueY) {
                if (x > preX) {
                    ++preX;
                } else {
                    --preX;
                }
            } else if (dValueX < dValueY) {
                if (y > preY) {
                    ++preY;
                } else {
                    --preY;
                }
            } else {
                if (x > preX) {
                    ++preX;
                } else {
                    --preX;
                }

                if (y > preY) {
                    ++preY;
                } else {
                    --preY;
                }
            }
            System.out.printf("send(%d,%d)", preX, preY);
        }
        System.out.println("--------end----------");
    }

    /**
     * 测试：B1DDBED73A202020202031303034BFF8
     */
    @Test
    public void testSignaturePinInfo() {
        String info1 = "50494EB9F8C8A32036C0DAB8AEB8A6 0x00,";
        /*System.out.println(info1.substring(0,10));
        System.out.println(info1.substring(10,28));
        System.out.println(info1.substring(28));

        System.out.println(DataConvertUtils.hex2KrString("B1DDBED73A"));
        System.out.println(DataConvertUtils.hexStr2AsciiStr("202020202031303034"));
        System.out.println(DataConvertUtils.hex2KrString("BFF8"));*/

        System.out.println(DataConvertUtils.hex2KrString(info1));

        String info2 = "B4ADB7AFC1D6BCBCBFE42E 0x00, 0x00, 0x00, 0x00, 0x00,";
        System.out.println(DataConvertUtils.hex2KrString(info2));
    }

    @Test
    public void test32A4() {
        String s =
                "02 0x00,7332A4353336313438393031333034393535333D32361C3131313536333235626462373466376364383034303650494EB9F8C8A32036C0DAB8AEB8A6 0x00,1CB4ADB7AFC1D6BCBCBFE42E 0x00, 0x00, 0x00, 0x00, 0x00,1C202020202020202020202020202020201C202020202020202020202020202020201C303103DD";
        int index = 10;
        String cardNumber = DataConvertUtils.getDataSplitByFS(s.substring(index));
        index += cardNumber.length() + 2;
        System.out.println(cardNumber);
        //卡片号码

        //Working Key Index
        String workingKeyIndex = s.substring(index, index + 2 * 2);
        System.out.println(workingKeyIndex);
        index += 2 * 2;

        //Working Key
        System.out.println(s.substring(index, index + 16 * 2));
        index += 16 * 2;

        //最小密码长度
        System.out.println(s.substring(index, index + 2 * 2));
        index += 2 * 2;

        //最大密码长度
        System.out.println(s.substring(index, index + 2 * 2));
        index += 2 * 2;

        //info1
        String info1 = DataConvertUtils.getDataSplitByFS(s.substring(index));
        index += info1.length() + 2;
        System.out.println(info1);

        //info2
        String info2 = DataConvertUtils.getDataSplitByFS(s.substring(index));
        index += info2.length() + 2;
        System.out.println(info2);

        //info1
        String info3 = DataConvertUtils.getDataSplitByFS(s.substring(index));
        index += info3.length() + 2;
        System.out.println(info3);

        //info1
        String info4 = DataConvertUtils.getDataSplitByFS(s.substring(index));
        index += info4.length() + 2;
        System.out.println(info4);

        System.out.println(s.substring(index, index + 2 * 2));
    }

    /*@Test
    public void testEncryptPin() {
        String str1 = " 0x00, 0x00,621094FFFFFF";
        String str2 = "06111111FFFFFFFF";
        //转换的卡号和密码用Hex 8byte转换后 XOR 处理
        *//*String str1Hex = DataConvertUtils.string2HexString(str1);
        String str2Hex = DataConvertUtils.string2HexString(str2);
        System.out.println(str1Hex);
        System.out.println(str2Hex);*//*

        byte[] comp1Bytes = ConvertHelper.getConvert().strToBcdPaddingLeft(str1);
        byte[] comp2Bytes = ConvertHelper.getConvert().strToBcdPaddingLeft(str2);
        byte[] bytes = Algo.xor(comp1Bytes, comp2Bytes, comp1Bytes.length);

        //byte[] key = {0x15, 0x63, 0x25, 0xBD, 0xB7, 0x4F, 0x7C, 0xD8};
        //Algo.des(key, bytes);
        byte[] key = ConvertHelper.getConvert().strToBcdPaddingLeft("156325BDB74F7CD8");
        byte[] des = Algo.des(Algo.ECryptOperation.ENCRYPT,
                Algo.ECryptOption.ECB,
                Algo.ECryptPaddingOption.NO_PADDING,
                bytes,
                key,
                null);
        String encryptStr = ConvertHelper.getConvert().bcdToStr(des);

        System.out.println(encryptStr);
    }*/

    @Test
    public void testGlLib() {
        byte[] bytes = ConvertHelper.getConvert().strToBcdPaddingLeft("768A5B4EFB48BC30C1CC3FD22522714D");
        System.out.println(Arrays.toString(bytes));
    }

    @Test
    public void converPassword() {
        String pin = "111111";
        int pinLenght = pin.length();
        String strPinLenght = DataConvertUtils.leftPaddingInt(pinLenght, 2);
        System.out.println(strPinLenght);
        String result = DataConvertUtils.rightPaddingChar(strPinLenght + pin, 'F', 16);
        System.out.println(result);
    }

    @Test
    public void testCardNumber() {
        String str = "353336313438393031333034393535333D3236";
        System.out.println(str.substring(0, 6));
    }

    @Test
    public void test32A5() {
        String str = "02 0x00,4932A52020203CB0E8C1C2C0DCBED73E2020201CB1DDBED73A202020202031303034BFF81CB8DEBCBCC1F6C5D7BDBAC6AE332020201CB8DEBCBCC1F6C5D7BDBAC6AE342020201C303603AD";
        int index = 10;

        //info1
        String info1 = DataConvertUtils.getDataSplitByFS(str.substring(index));
        System.out.println("info1 = " + info1);
        index += info1.length() + 2;

        //info2
        String info2 = DataConvertUtils.getDataSplitByFS(str.substring(index));
        System.out.println("info2 = " + info2);
        index += info2.length() + 2;


        //info3
        String info3 = DataConvertUtils.getDataSplitByFS(str.substring(index));
        System.out.println("info3 = " + info3);
        index += info3.length() + 2;

        //info4
        String info4 = DataConvertUtils.getDataSplitByFS(str.substring(index));
        System.out.println("info4 = " + info4);
        index += info4.length() + 2;

        //show time

        //info2
        String showTime = str.substring(index, index + 2 * 2);
        System.out.println("showTime = " + showTime);

        //转码
        System.out.println(DataConvertUtils.hex2KrString(info1));
        System.out.println(DataConvertUtils.hex2KrString(info2));
        System.out.println(DataConvertUtils.hex2KrString(info3));
        System.out.println(DataConvertUtils.hex2KrString(info4));
    }

    @Test
    public void test32A8() {
        List<String> list = new ArrayList<>();

        String str = "02 0x00,C032A82020203CB0E8C1C2BCB1C5C33E2020201C3132332D3435362D37383939383720201C3334382D35322D353437382D313534371C3131302D3335352D33373731393720201C3632302D353133352D323833373620201C313131312D323232322D3333333333201C353435372D3134342D313537393837201C31353437382D31353737342D313131201C202020202020202020202020202020201C202020202020202020202020202020201C202020202020202020202020202020201C333 0x00,314";
        int beginIndex = 10;
        str = str.substring(beginIndex);

        int infoIndex = 0;
        String result = "";
        while (true) {
            result = DataConvertUtils.getDataSplitByFS2(str);
            if (result.equals("")) break;

            str = str.substring(result.length() + 2);
            list.add(result);
            System.out.println("info" + ++infoIndex + " : " + result);
        }

        for (String s :
                list) {
            System.out.println(s);
        }

        System.out.println("----------");

        System.out.println(DataConvertUtils.hex2KrString(list.remove(0)));
        for (String s :
                list) {
            System.out.println(DataConvertUtils.hex2KrString(s));
        }
    }

    @Test
    public void testSpliteMethod() {
        String str = " 0x00, 0x00, 0x00,";
        String str2 = " 0x00,1C 0x00,";
        String str3 = "1C 0x00, 0x00,";
        /*System.out.println("result = " + DataConvertUtils.getDataSplitByFS2(""));
        System.out.println("result = " + DataConvertUtils.getDataSplitByFS2("123"));*/
        System.out.println("result = " + DataConvertUtils.getDataSplitByFS2(str));
        System.out.println("result = " + DataConvertUtils.getDataSplitByFS2(str2));
        System.out.println("result = " + DataConvertUtils.getDataSplitByFS2(str3));
    }

    @Test
    public void testCompression() {
        int[] array = new int[]{
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff,
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff, 0xff};

//        DataConvertUtils.bytes2Hex();
        DataConvertUtils.hexStrToByte("00");

        int[] tempSign = nullCompress2(array);
        int[] relCompressData = rleCompress2(tempSign);

        //nullRelData = tempSign长度（2字节） + relCompressData（长度的字节）
        int[] nullRelData = new int[2 + relCompressData.length];
    }


    /**
     * 假如为零，压缩，例如，00 00 00 -> 00 03
     *
     * @param array
     * @return
     */
    public int[] nullCompress2(int[] array) {
        int[] tempSign = new int[1900];
        int zeroCnt = 0;//0的数量
        boolean flagZero = false;//是否为0
        int lenSign = 0;

        for (int i = 0; i < 1024; i++) {
            if (array[i] == 0x00) {//为零
                flagZero = true;
                zeroCnt++;
            } else {//非零
                if (flagZero) {//标记，证明上一个为零
                    if (zeroCnt <= 255) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt;
                    } else if (zeroCnt <= 510) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 255;
                    } else if (zeroCnt <= 765) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 510;
                    } else if (zeroCnt <= 1020) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 765;
                    } else {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 1020;
                    }
                    flagZero = false;
                    zeroCnt = 0;
                }
                //非零数，直接假如数组
                tempSign[lenSign++] = array[i];
            }
        }
        int[] resultData = new int[lenSign];
        System.arraycopy(tempSign, 0, resultData, 0, lenSign);

        System.out.println("length = " + lenSign + " tempSign = " + Arrays.toString(resultData));
        return resultData;
    }


    public int[] nullCompress(int[] array) {
        int[] tempSign = new int[1900];
        int zeroCnt = 0;//0的数量
        boolean flagZero = false;//是否为0
        int lenSign = 0;

        for (int i = 0; i < 1024; i++) {
            if (array[i] == 0x00) {

                flagZero = true;
                zeroCnt++;
                if (i == 1023) {
                    if (zeroCnt <= 255) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt;
                    } else if (zeroCnt <= 510) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 255;
                    } else if (zeroCnt <= 765) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 510;
                    } else if (zeroCnt <= 1020) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 765;
                    } else {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 1020;
                    }
                }


            } else {

                //假如为零，压缩，例如，00 00 00 -> 00 03
                if (flagZero) {
                    if (zeroCnt <= 255) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt;
                    } else if (zeroCnt <= 510) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 255;
                    } else if (zeroCnt <= 765) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 510;
                    } else if (zeroCnt <= 1020) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 765;
                    } else {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = zeroCnt - 1020;
                    }
                    flagZero = false;
                    zeroCnt = 0;
                }
                //非零数，直接假如数组
                tempSign[lenSign++] = array[i];
            }
        }
        System.out.println("length = " + lenSign + " tempSign = " + Arrays.toString(tempSign));
        return tempSign;
    }


    public int[] rleCompress(int[] input) {
        if (input == null || input.length <= 0) {
            return new int[0];
        }

        int origDataLen = input.length;
        int count = 0;
        int index = 0;
        int i;
        int out = 0;
        int pixel;
        int[] output = new int[origDataLen + 1];

        while (count < origDataLen) {
            pixel = input[index++];

            while (index < origDataLen && index - count < 127 && input[index] == pixel) {
                index++;
            }

            if (index - count == 1) {
                while (index < origDataLen && index - count < 127 && (input[index] != input[index - 1] || index > 1 && input[index] != input[index - 2])) {
                    index++;
                }

                while (index < origDataLen && input[index] == input[index - 1]) {
                    index--;
                }

                output[out++] = (count - index);
                for (i = count; i < index; i++) {
                    output[out++] = input[i];
                }
            } else {
                output[out++] = (index - count);
                output[out++] = pixel;
            }
            count = index;
        }

        int[] outData = new int[out];
        System.arraycopy(output, 0, outData, 0, out);

        System.out.println("length = " + out + " tempSign = " + Arrays.toString(outData));

        return outData;
    }


    public int[] rleCompress2(int[] input) {
        int count = 0;
        int index;
        int i;
        int pixel;
        int out = 0;
        int length = input.length;
        int[] output = new int[length + 1];

        while (count < length) {
            index = count;
            pixel = input[index++];
            while (index < length && index - count < 127 && input[index] == pixel) index++;

            if (index - count == 1) {
                while (index < length && index - count < 127 && (input[index] != input[index - 1] || index > 1 && input[index] != input[index - 2]))
                    index++;

                while (index < length && input[index] == input[index - 1])
                    index--;

                output[out++] = count - index;
                for (i = count; i < index; i++)
                    output[out++] = input[i];
            } else {
                output[out++] = index - count;
                output[out++] = pixel;
            }
            count = index;
        }

        int[] outData = new int[out];
        System.arraycopy(output, 0, outData, 0, out);

        System.out.println("length = " + out + " tempSign = " + Arrays.toString(outData));
        return outData;
    }


    @Test
    public void testBcd() {
        byte[] bytes = ConvertUtils.strToBcdPaddingLeft("00FF");
        System.out.println("bytes = " + Arrays.toString(bytes));
        String s = ConvertUtils.bcd2Str(bytes);
        System.out.println("s = " + s);
//        DataConvertUtils.int2HexString2()
//        int i = Integer.parseInt(s);
//        System.out.println("i = " + i);
        byte[] bytes1 = DataConvertUtils.string2ByteArray(s);
        System.out.println("bytes1 = " + Arrays.toString(bytes1));

        int i = 1;


    }

    @Test
    public void testCompression3() {
        String str = "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "ff ff ff ff ff ff ff ff 00 00 00 00 00 00 00 00 " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff " +
                "00 00 00 00 00 00 00 00 ff ff ff ff ff ff ff ff ";
        String s = DataConvertUtils.removeEmptyChar(str);
        System.out.println("s = " + s);
        String[] strings = DataConvertUtils.hexStrToArray(s);

        char[] chars = new char[1900];
        for (int i = 0; i < strings.length; i++) {
            /*String s1 = Integer.toHexString(Integer.parseInt(strings[i]));
            System.out.println("s1 = " + s1);*/
            chars[i] = '0';
        }

        //hexString -> char
        String s1 = "FF";
        String s2 = Integer.toHexString(Integer.parseInt(s1));
        System.out.println("s2 = " + s2);

    }


    public char[] nullCompress3(char[] array) {
        char[] tempSign = new char[1900];
        int zeroCnt = 0;//0的数量
        boolean flagZero = false;//是否为0
        int lenSign = 0;

        for (int i = 0; i < 1024; i++) {
            if (array[i] == 0x00) {//为零
                flagZero = true;
                zeroCnt++;
            } else {//非零
                if (flagZero) {//标记，证明上一个为零
                    if (zeroCnt <= 255) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = (char) zeroCnt;
                    } else if (zeroCnt <= 510) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = (char) (zeroCnt - 255);
                    } else if (zeroCnt <= 765) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = (char) (zeroCnt - 510);
                    } else if (zeroCnt <= 1020) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = (char) (zeroCnt - 765);
                    } else {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = (char) (zeroCnt - 1020);
                    }
                    flagZero = false;
                    zeroCnt = 0;
                }
                //非零数，直接假如数组
                tempSign[lenSign++] = array[i];
            }
        }
        char[] resultData = new char[lenSign];
        System.arraycopy(tempSign, 0, resultData, 0, lenSign);

        System.out.println("length = " + lenSign + " tempSign = " + Arrays.toString(resultData));
        return resultData;
    }


   /* public void nullCompress4(String str) {
        String tempSign = str;
        int zeroCnt = 0;//0的数量
        boolean flagZero = false;//是否为0
        int lenSign = 0;

        for (int i = 0; i < 1024; i++) {
            if (array[i] == 0x00) {//为零
                flagZero = true;
                zeroCnt++;
            } else {//非零
                if (flagZero) {//标记，证明上一个为零
                    if (zeroCnt <= 255) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = (char) zeroCnt;
                    } else if (zeroCnt <= 510) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = (char) (zeroCnt - 255);
                    } else if (zeroCnt <= 765) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = (char) (zeroCnt - 510);
                    } else if (zeroCnt <= 1020) {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = (char) (zeroCnt - 765);
                    } else {
                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = 0xff;

                        tempSign[lenSign++] = 0x00;
                        tempSign[lenSign++] = (char) (zeroCnt - 1020);
                    }
                    flagZero = false;
                    zeroCnt = 0;
                }
                //非零数，直接假如数组
                tempSign[lenSign++] = array[i];
            }
        }
        char[] resultData = new char[lenSign];
        System.arraycopy(tempSign, 0, resultData, 0, lenSign);

        System.out.println("length = " + lenSign + " tempSign = " + Arrays.toString(resultData));
        return resultData;
    }*/
}