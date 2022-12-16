package com.shujie.base;

import android.text.TextUtils;

import com.kovan.secure.KovanSecure;
import com.pax.commonlib.utils.LogUtils;
import com.pax.protocol.utils.constant.ConstantCommon;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * 数据转换工具类
 *
 * @date: 2022/8/24
 * @author: linshujie
 */
public class DataConvertUtils {

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);
    private static final String SPACE_STRING = " ";
    private static final HashMap<String, Integer> kv = new HashMap<String, Integer>();

    private static void initKv() {
        if (!kv.isEmpty()) return;
        kv.put("0", 0);
        kv.put("1", 1);
        kv.put("2", 2);
        kv.put("3", 3);
        kv.put("4", 4);
        kv.put("5", 5);
        kv.put("6", 6);
        kv.put("7", 7);
        kv.put("8", 8);
        kv.put("9", 9);
        kv.put("A", 10);
        kv.put("a", 10);
        kv.put("B", 11);
        kv.put("b", 11);
        kv.put("C", 12);
        kv.put("c", 12);
        kv.put("D", 13);
        kv.put("d", 13);
        kv.put("E", 14);
        kv.put("e", 14);
        kv.put("F", 15);
        kv.put("f", 15);
    }

    public static byte getXor(byte[] datas) {
        byte temp = datas[0];
        for (int i = 1; i < datas.length; i++) {
            temp ^= datas[i];
        }
        return temp;
    }

    /**
     * 十六进制字符串转换成字节数组
     *
     * @param hexstr 十六进制字符串
     * @return 字节数组
     */
    public static byte[] hexStrToByte(String hexstr) {
        int len = (hexstr.length() / 2);
        byte[] result = new byte[len];
        char[] achar = hexstr.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (((byte) "0123456789ABCDEF".indexOf(achar[pos])) << 4
                    | (((byte) "0123456789ABCDEF".indexOf(achar[pos + 1]))) & 0xff);
        }
        return result;
    }

    /**
     * 16进制字符转为韩语
     */
    public static String hex2KrString(String hexStr) {
        byte[] hex = DataConvertUtils.hexStrToByte(hexStr);
        return new String(hex, Charset.forName("euc-kr"));
    }

    //1字节转2个Hex字符
    public static String byte2Hex(Byte inByte) {
        return String.format("%02x", new Object[]{inByte}).toUpperCase();
    }

    /**
     * 校验Lrc
     */
    public static String calculateLrc(String hexString) {
        return byte2Hex(getXor(hexStrToByte(hexString)));
    }

    /**
     * 移除所有的空格符
     */
    public static String removeEmptyChar(String data) {
        return data.replaceAll(" ", "").replaceAll("\n", "");
    }

    /**
     * hexStr -> String[]
     * 例如："02002A31A5" -》 String[]{02,00,2A,31,A5}
     */
    public static String[] hexStrToArray(String hexStr) {
        hexStr = removeEmptyChar(hexStr);
        String[] array = new String[hexStr.length() / 2];
        int k = 2;
        for (int i = 0; i < array.length; i++) {
            array[i] = hexStr.substring(i * 2, k);
            //array[i] = "0x" + hexStr.substring(i * 2, k);
            k += 2;
        }
        return array;
    }

  /*  *//**
     * hexStr -> String[]
     * 例如："02002A31A5" -》 String[]{02,00,2A,31,A5}
     *//*
    public static char[] hexStrToArray(String hexStr) {
        hexStr = removeEmptyChar(hexStr);
        char[] array = new char[hexStr.length() / 2];
        int k = 2;
        for (int i = 0; i < array.length; i++) {
            array[i] = hexStr.substring(i * 2, k);
            //array[i] = "0x" + hexStr.substring(i * 2, k);
            k += 2;
        }
        return array;
    }*/

    /**
     * int -> hexString
     *
     * @param hexStringLength 转换成16进制字符后字符串的长度
     */
    public static String int2HexString(int integer, int hexStringLength) {
        String hexString = Integer.toHexString(integer);
        switch (hexStringLength) {
            case 2:
                if (hexString.length() == 1) {
                    hexString = "0" + hexString;
                }
                break;
            case 4:
                if (hexString.length() == 1) {
                    hexString = "000" + hexString;
                } else if (hexString.length() == 2) {
                    hexString = "00" + hexString;
                } else if (hexString.length() == 3) {
                    hexString = "0" + hexString;
                }
                break;
            default:
                break;
        }

        return hexString;
    }

    /**
     * int -> hexString
     *
     * @param hexStringLength 转换成16进制字符后字符串的长度
     */
    public static String int2HexString2(int integer, int hexStringLength) {
        StringBuilder sb = new StringBuilder(Integer.toHexString(integer));
        while (sb.length() < hexStringLength) {
            sb.insert(0, "0");
        }
        return sb.toString();
    }

    /**
     * String -> byte[]
     */
    public static byte[] string2ByteArray(String string) {
        return string.getBytes(StandardCharsets.US_ASCII);
    }

    /**
     * bytes -> hexString
     */
    public static String bytes2Hex(byte[] bytes) {
        byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8);
    }

    /**
     * asciiString -> hexString
     */
    public static String string2HexString(String string) {
        byte[] bytes = string2ByteArray(string);
        return bytes2Hex(bytes);
    }

    /**
     * asciiString -> hexString
     * 例如：“DUALPAYDE633” -> "4455414C504159444536333320202020"
     *
     * @param length 字节长度
     */
    public static String string2HexString(String string, int length) {
        if (string == null) {
            LogUtils.e(new Exception("string 不能为null"));
            return "";
        }
        StringBuilder sb = new StringBuilder(string);
        while (sb.length() < length) {
            sb.append(SPACE_STRING);
        }
        byte[] bytes = string2ByteArray(sb.toString());
        return bytes2Hex(bytes);
    }

    /**
     * the ascii/hex of int transfer to int
     *
     * @param length the count of byte in hex/ascii
     */
    public static int asciiString2Int(String string, int length) {
        StringBuilder sb = new StringBuilder();

        char[] chars;
        byte[] result = new byte[1];
        if (string.length() / 2 == length) {
            for (int i = 0; i < string.length(); i += 2) {
                String substring = string.substring(i, i + 2);
                chars = substring.toCharArray();
                // 例如 substring 为 3238 中的 32
                byte byte1 = (byte) ((Integer.parseInt(String.valueOf(chars[0])) << 4) & 0xf0);
                byte byte2 = (byte) (Integer.parseInt(String.valueOf(chars[1])) & 0x0f);
                result[0] = (byte) (byte1 ^ byte2);
                sb.append(new String(result));
            }
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * the ascii/hex of int transfer to int
     * emxample:
     * input：string "3036"
     * output:6
     */
    public static int asciiString2Int(String string) {
        StringBuilder sb = new StringBuilder();

        char[] chars;
        byte[] result = new byte[1];
        if (string.length() % 2 == 0) {
            for (int i = 0; i < string.length(); i += 2) {
                String substring = string.substring(i, i + 2);
                chars = substring.toCharArray();
                // 例如 substring 为 3238 中的 32
                byte byte1 = (byte) ((Integer.parseInt(String.valueOf(chars[0])) << 4) & 0xf0);
                byte byte2 = (byte) (Integer.parseInt(String.valueOf(chars[1])) & 0x0f);
                result[0] = (byte) (byte1 ^ byte2);
                sb.append(new String(result));
            }
        } else {
            throw new IllegalArgumentException("error argument");
        }
        return Integer.parseInt(sb.toString());
    }

    /**
     * 实质为asciiStr2Chars
     * 16进制转ASCII字符
     * 如
     * ASCII: 0X31
     * HEX: 0X01
     * STR: 1
     */
    public static String hexStr2AsciiStr(String hex) {
        return hexStr2AsciiStr(hex, true);
    }

    /**
     * 实质为asciiStr2Chars
     * 16进制转ASCII字符
     * 如
     * ASCII: 0X31
     * HEX: 0X01
     * STR: 1
     */
    public static String hexStr2AsciiStr(String hex, boolean isRemoveEmptyChars) {
        if (isRemoveEmptyChars) {
            hex = removeEmptyChar(hex);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hex.length(); i += 2) {
            String substring = hex.substring(i, i + 2);
            sb.append((char) Integer.parseInt(substring, 16));
        }
        return sb.toString();
    }

    /**
     * 截取数组，组成新的string
     */
    public static String subStringArray(String[] strings, int firstIndex, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = firstIndex; i < firstIndex + length; i++) {
            sb.append(strings[i]);
        }
        return sb.toString();
    }

    /**
     * calculate the LRC
     * for example
     * var1 = "00 04 AC 32 38 03 A1"
     * return A1
     */
    public static String calcLRC(String var1) {
        initKv();
        var1 = var1.trim();
        // 先将hexString var1转换为byte数组
        char[] chars;
        byte[] bytes = new byte[var1.length() / 2];

        for (int i = 0; i < var1.length(); i += 2) {
            String subString = var1.substring(i, i + 2);
            chars = subString.toCharArray();
            if (!kv.isEmpty()) {
                byte byte1 = (byte) ((kv.get(String.valueOf(chars[0])) << 4) & 0xf0);
                byte byte2 = (byte) (kv.get(String.valueOf(chars[1])) & 0x0f);
                bytes[i / 2] = (byte) (byte1 ^ byte2);
            }
        }
        // 对转换后的byte数组进行异或计算
        byte[] result = new byte[1];

        for (byte aByte : bytes) {
            result[0] ^= aByte;
        }

        return bytes2Hex(result);
    }

    // 将十六进制ascii码表示的字符串转换为对应字符的原字符串
    public static String asciiString2String(String hexString) {
        initKv();
        StringBuilder sb = new StringBuilder();

        char[] chars;
        byte[] result = new byte[1];
        for (int i = 0; i < hexString.length(); i += 2) {
            String substring = hexString.substring(i, i + 2);
            chars = substring.toCharArray();
            byte byte1 = (byte) ((kv.get(String.valueOf(chars[0])) << 4) & 0xf0);
            byte byte2 = (byte) (kv.get(String.valueOf(chars[1])) & 0x0f);
            result[0] = (byte) (byte1 ^ byte2);
            sb.append(new String(result));
        }
        return sb.toString();
    }

    /**
     * 从一个字符串数组中取出指定长度并拼接返回
     */
    public static String getLenString(String[] strings, int startIndex, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(strings[startIndex + i]);
        }
        return sb.toString();
    }

    /**
     * 将int整数转换为对应的ascii码（自定义长度）
     * 例如 123 需要的长度为4 则输出30313233
     */
    public static String int2Ascii(int integer, int intLength) {
        StringBuilder sb = new StringBuilder(String.valueOf(integer));
        if (sb.length() < intLength) {
            while (sb.length() < intLength) {
                sb.insert(0, "0");
            }
        }
        byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);

        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(Integer.toHexString(aByte));
        }
        return result.toString();
    }

    /**
     * 将int整数转换为对应的ascii码（自定义长度）
     * 例如 123 需要的长度为4 则输出30313233
     */
    public static String string2Ascii(String str, int length) {
        StringBuilder sb = new StringBuilder(String.valueOf(str));
        if (sb.length() < length) {
            while (sb.length() < length) {
                sb.insert(0, "0");
            }
        }
        byte[] bytes = sb.toString().getBytes(StandardCharsets.UTF_8);

        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            result.append(Integer.toHexString(aByte));
        }
        return result.toString();
    }

    /**
     * 获取空格String
     */
    public static String getSpaceString(int spaceNumber) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < spaceNumber; i++) {
            sb.append(ConstantCommon.SPACE_STRING);
        }
        return sb.toString();
    }

    /**
     * 掩码CARD号码长度(2) + 掩码CARD号码
     * 包含卡号的情况‘=’之前为止掩码处理
     * 9位到14‘*’ 掩码处理
     * 例) 16+111122******4444
     * 剩下 0x20 padding (ASCII)
     */
    public static String maskPan(String pan) {
        //        return pan.substring(6,11);

        return new StringBuilder(pan).replace(6, 11, "******").toString();
    }

    /**
     * judge the data is valid in (0-9,A-Z,a-z)
     * if data is valid - return true
     * if data is invalid - return false
     *
     * @param data the data
     * @param size the length of data
     * @return the boolean
     */
    public static boolean numericCheck(byte[] data, int size) {
        int i;
        for (i = 0; i < size; i++) {
            if ((data[i] < '0' || data[i] > '9') && (data[i] < 'A' || data[i] > 'Z') && (data[i]
                    < 'a' || data[i] > 'z')) {
                break;
            }
        }
        return i == size;
    }

    /**
     * masking card number
     *
     * @param cardNo card pan
     * @return masked String
     */
    public static String maskCardNo(String cardNo) {
        StringBuilder sb = new StringBuilder(cardNo.replace("-", ""));
        if (sb.length() == 10) {
            //자진 발급
            sb.replace(4, 6, "**");
            sb.replace(8, 10, "**");
        } else if (sb.length() == 11) {
            //phone number
            sb.replace(5, 7, "**");
            sb.replace(9, 11, "**");
        } else if (sb.length() > 11) {
            //card number
            sb.replace(8, 12, "****");
            sb.replace(sb.length() - 1, sb.length(), "*");
        }
        return sb.toString();
    }

    /**
     * base64编码
     * String : "000010" -> base64 encode -> hexString : "41414151"
     */
    public static String base64Encode2HexStr(String s) {
        byte[] bytes = ConvertUtils.strToBcdPaddingLeft(s);
        //        byte[] bytes = DataConvertUtils.hexStrToByte(s);
        String encode = KovanSecure.encodeBASE64(bytes);
        return ConvertUtils.bcd2Str(encode.getBytes());
        //        return DataConvertUtils.bytes2Hex(encode.getBytes());
    }

    /**
     * 给卡号添加掩码
     */
    public static String maskedPan(String pan) {
        if ("".equals(pan)) return "";
        return pan.substring(0, 6)
                + "******"
                + pan.substring(12);
    }

    /**
     * Find separator in track2 data
     *
     * @param track2 track2 dara
     * @return return the index of separator
     */
    public static int findSepOfTrack2(String track2) {
        int i;
        int track2Len = track2.length();
        if (TextUtils.isEmpty(track2)) {
            return 0;
        }

        if (track2Len > 32) {
            track2Len = 32;
        }

        for (i = 0; i < track2Len; i++) {
            char c = track2.charAt(i);
            if (c == '=' || c == 0x1C || c == 'D' || c == '^') {
                return i;
            }
        }
        return i;
    }

    /**
     * 截取当前字符串到FS分割的字符
     */
    public static String getDataSplitByFS(String s) {
        /**
         * 思路1：便利下标，获取三个“1C”的index
         * 思路2：转为数组，遍历下表，没遇到一个不为“1C”的，进行字符合并
         */
        String[] strings = DataConvertUtils.hexStrToArray(s);
        StringBuilder sb = new StringBuilder();
        int pointIndex = 0;
        while (!ConstantCommon.FS.equals(strings[pointIndex])) {
            sb.append(strings[pointIndex]);
            pointIndex++;
        }
        return sb.toString();
    }

    /**
     * 截取当前字符串到FS分割的字符
     * 思路：
     * 定义指针index，遍历s，每次index += 2
     * 把每次遍历的字符串添加到sb中
     * 遍历过程中假如遇到标记，退出循环，返回sb。假如没有遇到标记，返回“”
     */
    public static String getDataSplitByFS2(String s) {
        if (s.equals("") || s.length() % 2 != 0) {
            throw new IllegalArgumentException("please check the argument,it can not be a odd number or '' ");
        }

        int index = 0;
        StringBuilder result = new StringBuilder();
        String curSub = "";
        boolean isFound = false;
        while (index < s.length()) {
            curSub = s.substring(index,index + 2);
            if (ConstantCommon.FS.equals(curSub)){
                isFound = true;
                break;
            }
            result.append(curSub);
            index += 2;
        }
        return isFound ? result.toString() : "";
    }

    /**
     * 截取当前字符串到FS分割的字符
     */
    public static String getDataSplitByETX(String s) {
        /**
         * 思路1：便利下标，获取三个“1C”的index
         * 思路2：转为数组，遍历下表，没遇到一个不为“1C”的，进行字符合并
         */
        String[] strings = DataConvertUtils.hexStrToArray(s);
        StringBuilder sb = new StringBuilder();
        int pointIndex = 0;
        while (!ConstantCommon.ETX.equals(strings[pointIndex])) {
            sb.append(strings[pointIndex]);
            pointIndex++;
        }
        return sb.toString();
    }

    /**
     * 截取当前字符串到FS分割的字符
     */
    public static String getDataSplitByZero(String s) {
        /**
         * 思路1：便利下标，获取三个“1C”的index
         * 思路2：转为数组，遍历下表，没遇到一个不为“1C”的，进行字符合并
         */
        String[] strings = DataConvertUtils.hexStrToArray(s);
        StringBuilder sb = new StringBuilder();
        int pointIndex = 0;
        while (!ConstantCommon.ZERO.equals(strings[pointIndex])) {
            sb.append(strings[pointIndex]);
            pointIndex++;
        }
        return sb.toString();
    }

    /**
     * 填充int
     * example：
     * 输入：num = 6,lenght = 2
     * 输出： "06"
     *
     * @param num    需要填充的整型数据
     * @param lenght 填充后的长度
     */
    public static String leftPaddingInt(int num, int lenght) {
        StringBuilder str = new StringBuilder(String.valueOf(num));
        while (str.length() < lenght) {
            str.insert(0, "0");
        }
        return str.toString();
    }

    /**
     * 右填充char
     * example：
     * 输入：str = "shujie" c = ’F‘ lenght = 7
     * 输出：shujieF
     *
     * @param str    原始数据
     * @param c      需要填充的char
     * @param lenght 填充后的长度
     */
    public static String rightPaddingChar(String str, char c, int lenght) {
        if (lenght < str.length()) {
            LogUtils.e(new IllegalArgumentException());
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < lenght) {
            sb.append(c);
        }
        return sb.toString();
    }
}
