package logger;


import java.io.*;

public class Logger {
    private final static String dupLogPath = "resources/insert_dup_log";
    private final static String logPath = "resources/insert_log";
    private static FileOutputStream idFos = null;
    private static FileOutputStream iFos = null;
    private static BufferedOutputStream idBos = null;
    private static BufferedOutputStream iBos = null;
    private static int insertCount = 0;
    private static int dupCount = 0;

    static {
        try {
            idFos = new FileOutputStream(new File(dupLogPath));
            iFos = new FileOutputStream(new File(logPath));
            idBos = new BufferedOutputStream(idFos);
            iBos = new BufferedOutputStream(iFos);
        } catch (FileNotFoundException e) {
            System.out.println("Error: fail to open log file!");
        }
    }

    public static void add_data_sql(String sql) {
        try {
            iBos.write(sql.getBytes());
            iBos.write("\n".getBytes());
            insertCount++;
        } catch (IOException ignored) {

        }
    }

    public static void add_dup_data_sql(String sql) {
        try {
            idBos.write(sql.getBytes());
            idBos.write("\n".getBytes());
            dupCount++;
        } catch (IOException ignored) {
        }
    }

    public static int getInsertCount() {
        return insertCount;
    }

    public static int getDupCount() {
        return dupCount;
    }

    public static void close() {
        try {
            iBos.flush();
            idBos.flush();
            idFos.close();
            iFos.close();
            idBos.close();
            iBos.close();
        } catch (IOException ignored) {
        }
    }
}
