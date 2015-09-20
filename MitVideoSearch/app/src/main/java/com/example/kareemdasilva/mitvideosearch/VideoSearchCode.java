package com.example.kareemdasilva.mitvideosearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

public class VideoSearchCode {
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c <= '/' || c >= ':') {
                return false;
            }
        }
        return true;
    }
    /*
    Main method parsing an srt file (Files with subtitle) and organizing into a list of list where the list are [begin, end, text1,text2]
     */
    public static List<String> parse(String filename){
        List<List<String>> records = new ArrayList<List<String>>();
        String filename = "/Users/mohamedkane/Documents/HackMit2015/video/src/video/test.srt";
        Map<String, String> hash = new HashMap<String, String>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            List<String> rec = new ArrayList<String>();
            while ((line = reader.readLine()) != null)
            {
                if(line.length()==0){
                    boolean scan = false;
                    boolean empty = line.length()==0;
                    rec = new ArrayList<String>();
                }
                else{
                    boolean scan = true;
                    if(line.length()==29 && line.charAt(0)=='0' && line.charAt(1)=='0'){
                        String[] k = line.split(" --> ");
                        if (k.length==2){
                            rec.add(k[0]);
                            rec.add(k[1]);
                        }
                    }  else if(!isInteger(line)){
                        rec.add(line);
                    }
                }
                if(records.size()==0){
                    records.add(rec);
                    if(rec.size()==4){
                        hash.put(rec.get(0), rec.get(2));
                    }
                }
                else if (!records.get(records.size()-1).equals(rec)){
                    records.add(rec);
                }
            }
            for (List<String> time : records){
                if(time.size()==4){
                    String one = time.get(2);
                    String two = time.get(3);
                    time.remove(3);
                    time.remove(2);
                    time.add(one+" "+two);
                    hash.put(time.get(0), time.get(2));
                }
            }
            reader.close();
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
        }
    }
}
