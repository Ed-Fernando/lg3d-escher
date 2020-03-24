package antlr;

/* ANTLR Translator Generator
 * Project led by Terence Parr at http://www.jGuru.com
 * Software rights: http://www.antlr.org/license.html
 *
 * $Id: FileLineFormatter.java,v 1.1.1.1 2004-12-13 19:09:06 deronj Exp $
 */

public abstract class FileLineFormatter {

    private static FileLineFormatter formatter = new DefaultFileLineFormatter();

    public static FileLineFormatter getFormatter() {
        return formatter;
    }

    public static void setFormatter(FileLineFormatter f) {
        formatter = f;
    }

    /** @param fileName the file that should appear in the prefix. (or null)
     * @param line the line (or -1)
     * @param column the column (or -1)
     */
    public abstract String getFormatString(String fileName, int line, int column);
}
