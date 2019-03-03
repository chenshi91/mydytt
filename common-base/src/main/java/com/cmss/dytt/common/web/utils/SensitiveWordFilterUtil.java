/*created by chenshi    at 2018-05-12*/
package com.cmss.dytt.common.web.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
 * 中 = {
 * 国 = {
 * 人 = {
 * 民 = {}
 * }
 * 男 = {
 * 人 = {}
 * }
 * }
 * }
 * 五 = {
 * 星 = {
 * 红 = {
 * 旗 = {
 * }
 * }
 * }
 * }
 */
public class SensitiveWordFilterUtil {
    private static final Map<Character, Map> wordMap = new HashMap();

    /**
     * 添加进敏感词库wordMap
     *
     * @param strings
     */
    public static void addSensitiveWord(Set<String> strings) {
        Map<Character, Map> nowMap = wordMap;
        Map<Character, Map> valueMap = null;
        for (String string : strings) {
            char[] chars = string.toCharArray();
            for (char c : chars) {
                Object object = nowMap.get(c);
                if (object == null) {
                    valueMap = new HashMap<>();
                    nowMap.put(c, valueMap);
                    nowMap = nowMap.get(c);
                } else {
                    nowMap = (Map<Character, Map>) object;
                }
            }
            nowMap = wordMap;
        }
    }


    /**
     * 敏感词过滤
     *
     * @param content（敏感词）
     * @param replaceWord（替换的词）
     * @return returnContent（替换后的内容）
     */
    public static String sensitiveWordFilter(String content, String replaceWord) throws IOException {
        if (wordMap.isEmpty()) {
            init();
        }
        Map<Character, Map> nowMap = wordMap;
        Set<String> sensitiveWord = new HashSet<>();//敏感词set
        StringBuilder builder = new StringBuilder();//敏感词收集工具
        for (int i = 0; i < content.length(); i++) {
            char word = content.charAt(i);
            Map map = nowMap.get(word);
            if (map != null) {
                //是敏感字符
                builder.append(word);
                if (map.values().size() > 0) {
                    //不是最后一个字符，继续匹配
                    nowMap = map;

                } else {
                    //是最后一个字符，不继续匹配
                    sensitiveWord.add(builder.toString());
                    builder.delete(0, builder.length());
                }

            } else {
                //不是敏感字符,重新匹配
                builder.delete(0, builder.length());
                nowMap = wordMap;
            }
        }

        //敏感词统一过滤
        if (sensitiveWord.isEmpty()) {
            return content;
        }
        String returnWord = content;
        for (String word : sensitiveWord) {
            returnWord = returnWord.replace(word, createReplaceWord(replaceWord, word.length()));
        }
        return returnWord;
    }

    /**
     * 初始化，导入敏感词库
     */
    private static void init() throws IOException {
        String flg = PropertiesUtil.getPropertiesValue("mgWord", "flg");
        String rb = PropertiesUtil.getPropertiesValue("mgWord", "rb");
        String mzd = PropertiesUtil.getPropertiesValue("mgWord", "mzd");
        String xjp = PropertiesUtil.getPropertiesValue("mgWord", "xjp");
        String dxp = PropertiesUtil.getPropertiesValue("mgWord", "dxp");
        Set<String> set = new HashSet<>();
        set.add(flg);
        set.add(rb);
        set.add(mzd);
        set.add(xjp);
        set.add(dxp);
        addSensitiveWord(set);
    }

    /**
     * 根据敏感词长度生成对应长度的替换字符
     *
     * @param replaceWord
     * @param length
     * @return
     */
    private static CharSequence createReplaceWord(String replaceWord, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(replaceWord);
        }
        return builder.toString();
    }

    public static void main(String[] args) throws IOException {
        /*HashSet<String> hashSet = new HashSet<>();
        hashSet.add("日本男人");
        hashSet.add("日本鬼子");
        hashSet.add("毛泽东");
        addSensitiveWord(hashSet);
*/
        String filter = sensitiveWordFilter("我说毛东怎么ribe日本鬼子没事," +
                        "我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事," +
                        "我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事," +
                        "我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事," +
                        "我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事," +
                        "我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事," +
                        "我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事," +
                        "我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事," +
                        "我说法轮功flg么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事," +
                        "我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事," +
                        "我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事," +
                        "等效【邓小平dxp,我说毛东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事," +
                        "我说毛东怎么ribe习近平xjp,我说毛毛泽东mzd东怎么ribe日本鬼子没事,我说毛东怎么ribe日本鬼子没事,"
                , "*");
        System.out.println(filter);

    }

}
