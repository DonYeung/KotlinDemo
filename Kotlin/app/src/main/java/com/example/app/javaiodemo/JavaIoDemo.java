package com.example.app.javaiodemo;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public class JavaIoDemo {
    public static void main(String[] args) {
//        writeIo();
//        readIo();
//        copy();
//        okio1();
//        OkIoCopy2();
        OkIoCopy();
    }

    private static void writeIo() {
        try (OutputStream outputStream = new FileOutputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/text.txt");
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
             BufferedWriter writer = new BufferedWriter(outputStreamWriter)) {
            writer.write("sadsa");
            writer.flush();
//            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readIo() {
        try (InputStream inputStream = new FileInputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/text.txt");
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader reader = new BufferedReader(inputStreamReader)
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void copy() {
        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/copy.txt"));
             InputStream inputStream = new BufferedInputStream(new FileInputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/text.txt"))) {
            byte[] datas = new byte[1024];
            int line;
            while ((line = inputStream.read(datas)) != -1) {
                outputStream.write(datas, 0, line);
                outputStream.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void okio1() {
        try (Source source = Okio.source(new FileInputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/copy.txt"))) {
            /*Buffer buffer = new Buffer();
            source.read(buffer,1024);//从source写到buffer
            System.out.println(buffer.readUtf8Line()); //再从buffer读出 readUtf8Lie*/
            //or
            BufferedSource bufferedSource = Okio.buffer(Okio.source(new FileInputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/copy.txt")));
            System.out.println("haha:" + bufferedSource.readUtf8Line());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void OkIoCopy() {
        try (Source source = Okio.source(new FileInputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/text.txt"));
             Sink sink = Okio.sink(new FileOutputStream("D:\\GitDir\\HenCoderPlus5\\Kotlin/copy.txt"))) {
            Buffer buffer = new Buffer();
            Long length = source.read(buffer, 1024);
            sink.write(buffer,length);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void OkIoCopy2() {
        try (BufferedSource source = Okio.buffer(Okio.source(new File("D:\\GitDir\\HenCoderPlus5\\Kotlin/text.txt")));
             BufferedSink sink = Okio.buffer(Okio.sink(new File("D:\\GitDir\\HenCoderPlus5\\Kotlin/copy.txt")))) {
            sink.writeUtf8(source.readUtf8());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
