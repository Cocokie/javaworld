package com.lix;

import java.io.*;
import java.util.Date;
import java.util.Scanner;

/**
 * @program: javaworld
 * @description: 自定义测试
 * @author: lixin
 * @create: 2020-03-05 16:47
 **/
public class Customize {
    public static void main(String[] args) throws Exception {

        //printStream();
        //objectStream();
        //dataStream();
        //bufferedReaderWriter();
        //streamWriterReader();
        //ByteArrayDemo();
    }

    private static void printStream() throws IOException {
        PrintStream out = System.out;
        out.println("sss");


        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintWriter p = new PrintWriter(new OutputStreamWriter(byteArrayOutputStream));
        p.println("sds");
        p.println("w");
        p.flush();
        InputStreamReader  inputStreamReader = new InputStreamReader(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        char []k=new char[1*1024];
        inputStreamReader.read(k);
        System.out.println(new String(k));
    }

    private static void objectStream() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new BufferedOutputStream(byteArrayOutputStream));
        objectOutputStream.writeUTF("天地绝杀霸主");
        objectOutputStream.writeObject(new Date());
        objectOutputStream.flush();

        ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())));
        System.out.println(objectInputStream.readUTF());
        System.out.println(objectInputStream.readObject());
    }

    private static void dataStream() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(byteArrayOutputStream));
        dataOutputStream.writeUTF("自定义测试");
        dataOutputStream.writeInt(666);
        dataOutputStream.flush();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(bytes)));

        System.out.println(dataInputStream.readUTF());
        System.out.println(dataInputStream.readInt());
    }

    private static void bufferedReaderWriter() throws IOException {
        System.out.println(System.getProperty("user.dir"));
        BufferedReader br = new BufferedReader(new FileReader("stream-io/test.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("stream-io/testCopy.txt"));
        String line;
        while ((line = br.readLine()) != null) {
            bw.write(line);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }

    private static void streamWriterReader() throws IOException {
        Scanner sc = new Scanner(System.in);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(System.out);
        while (sc.hasNext()) {
            outputStreamWriter.write(sc.next());
            outputStreamWriter.flush();
            System.out.println("");
        }
    }

    private static void ByteArrayDemo() throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream("stream-io/xixi.jpg");
             FileOutputStream fileOutputStream = new FileOutputStream("stream-io/xixiCopy.jpg")) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] b = new byte[1 * 1024];
            int k;
            while ((k = fileInputStream.read(b)) != -1) {
                byteArrayOutputStream.write(b, 0, k);
            }

            byte[] bytes = byteArrayOutputStream.toByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
            byte[] c = new byte[1 * 512];
            int x;
            while ((x = byteArrayInputStream.read(c)) != -1) {
                fileOutputStream.write(c, 0, x);
            }
        }
    }
}
