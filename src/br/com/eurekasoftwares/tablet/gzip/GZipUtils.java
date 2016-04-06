package br.com.eurekasoftwares.tablet.gzip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import com.googlecode.compress_j2me.gzip.Gzip;

public class GZipUtils {
	public static synchronized byte[] compress(String xml)
    {
        byte[] compressed = null;
        try {
            ByteArrayInputStream in1 = new ByteArrayInputStream(xml.getBytes());
            ByteArrayOutputStream out1 = new ByteArrayOutputStream();
            int num1 = Gzip.gzip(in1, out1);
            compressed = out1.toByteArray();            // 396 bytes total
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compressed;
    }
    public static synchronized byte[] decompress(byte[] compressed)
    {
        byte[] decompressed = new byte[0];
        try {
            ByteArrayInputStream in2 = new ByteArrayInputStream(compressed);
            ByteArrayOutputStream out2 = new ByteArrayOutputStream();
            Gzip num2 = Gzip.gunzip(in2, out2);
            decompressed = out2.toByteArray();          // 1024 bytes total
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decompressed;
    }
}
