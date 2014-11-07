package homework;


/**
 * 目的：String类的熟悉运用
 * 请尝试多使用以下上课时讲过的方法
 * String a = “a”;
 * String b = new String(“a”);
 * length()
 * charAt()
 * substring()
 * String.valueOf()
 * equals
 * equalsIgnoreCase
 * startsWith
 * endsWith
 * replace
 * indexOf
 */

public abstract class AbstractStringUtil {

    //基准
    protected String base;

    public AbstractStringUtil(String str){
        this.base = str;
    }

    /**
     * 作为例子，这个方法就不声明为abstract了，下面的abstract方法请全部在子类中实现
     * @return
     */
    protected String toUpperCase(){
        return base.toUpperCase();
    }

    /**
     * @return base的逆序
     * 如base如果为xiaolu，则返回uloaix
     * 提示，使用length，charAt方法
     */
    protected abstract String getReverse();

    /**
     * @return base中的大写字母全部去除
     * 如果base为StringBuffer,则返回tringuffer
     *
     * 验证一个字符是否为大小写，使用
     * Character.isUpperCase()
     * 其他部分使用charAt,String类型的+操作
     */
    protected abstract String stripUpperCase();


    /**
     * @return 将base转换成一个Person对象，字符串格式为 name:age
     * 如xiaolu：20
     *
     * 提示：使用split，Integer.parse
     */
    protected abstract Person makePerson();

    /**
     * @return  去除base的最后一个字符的字符串
     * 如base=xiaolu，则返回xiaol
     *
     * 提示使用substring，length方法
     */
    protected abstract String stripLastCharacter();

}

