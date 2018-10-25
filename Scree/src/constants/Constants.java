package constants;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants
{
    /**
     * 标识符正则表达式
     */
    public static final String IDENTIFIER_REGEX = "[a-zA-Z_][a-zA-Z0-9_]*";

    /**
     * 关键字
     */
    public static final List<String> KEYWORDS = Arrays.asList("if", "while", "else", "\n");

    /**
     * 整数值正则表达式
     */
    public static final String INTEGER_REGEX = "[0-9]+";

    /**
     * 字符串正则表达式(匹配含有\"、\\、\n的字符串)
     */
    public static final String STRING_REGEX = "\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\"";

    /**
     * 符号正则表达式
     */
    public static final String SYMBOL_REGEX = "==|<=|>=|&&|\\|\\||\\p{Punct}";
}
