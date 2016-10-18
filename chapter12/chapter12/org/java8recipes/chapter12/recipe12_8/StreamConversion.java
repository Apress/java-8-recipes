package org.java8recipes.chapter12.recipe12_8;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author John O'Conner (john@joconner.com)
 * Update:  Juneau
 * Recipe 12-8: Converting Character Streams and Buffers - Solution 1
 */
public class StreamConversion {
    public void run() {
        try {
            String input = readStream();
            System.out.printf("Input stream: %s\n", input);
            writeStream(input);
        } catch (IOException ex) {
            Logger.getLogger(StreamConversion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        
    public String readStream() throws IOException {
        InputStream is = getClass().getResourceAsStream("resource/helloworld.sjis.txt");
        InputStreamReader reader = null;
        StringBuilder sb = new StringBuilder();
        if (is != null){
            reader = new InputStreamReader(is, Charset.forName("SJIS"));
            int ch = reader.read();
            while(ch != -1) {
                sb.append((char)ch);
                ch = reader.read();
            }            
            reader.close();
        }        
        return sb.toString();
    }
    
    public void writeStream(String text) throws IOException {
        OutputStreamWriter writer = null;
        FileOutputStream fos = new FileOutputStream("helloworld.utf8.txt");
        writer = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
        writer.write(text);
        writer.close();
    }
    
    public static void main(String[] args) {
        StreamConversion app = new StreamConversion();
        app.run();
        
    }
    
}
