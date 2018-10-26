package lexical_analysis.test;

import javax.swing.*;
import java.io.IOException;
import java.io.Reader;

/**
 *  供用户输入Scree源代码文本的对话框类，继承了Reader
 */
public class CodeDialog extends Reader
{
    private String buffer = null;

    /**
     * 目前在buffer中已读取到的位置
     */
    private int curPosition;

    /**
     * 重写父类的读取方法
     * @param cbuf char数组用于储存读到的字符
     * @param off 从cbuf[]第几位开始储存而不是指从读文件第几个字符开始读
     * @param len 每次最多读多少个字符
     * @return 实际读取的字符个数，-1表示已经读到字符流结束
     * @throws IOException
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException
    {
        if(buffer == null)
        {
            String textIn = showInputDialog();

            if(textIn == null)
            {
                return -1;
            }
            else
            {
                buffer = textIn + "\n";
                curPosition = 0;
            }
        }

        int size = 0;

        while(curPosition < buffer.length() && size < len)
        {
            cbuf[off + size++] = buffer.charAt(curPosition++);
        }

        if(curPosition == buffer.length())
        {
            buffer = null;
        }

        return size;
    }

    @Override
    public void close() throws IOException
    {

    }

    /**
     * 从对话框中得到输入文本
     * @return 用户的输入文本值
     */
    private String showInputDialog()
    {
        JTextArea textArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);

        int result = JOptionPane.showOptionDialog(null, scrollPane, "Input",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);

        if(result == JOptionPane.OK_OPTION)
        {
            return textArea.getText();
        }
        else
        {
            return null;
        }
    }


}
