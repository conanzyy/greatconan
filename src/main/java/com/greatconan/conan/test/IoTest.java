package com.greatconan.conan.test;


import java.io.*;
public class IoTest {
   public static void main(String[] args) {
//      FileReader fr = null;
//      FileWriter fw = null;
//      int b = 0;
//      char[] cbuf = new char[18];
//      try {
//        fr = new FileReader("E:\\java\\io\\1.txt");//1.txt保存的位置
//        
//        fw = new FileWriter("E:\\java\\io\\2.txt");//2.txt保存的位置
//        while ((b=fr.read(cbuf,0,18))!=-1) {
//            fw.write(cbuf,0,18);
//        }
//      }
//      
//      
//     catch(FileNotFoundException e) {
//        e.getMessage();
//        e.printStackTrace();
//     }
//    catch(IOException e) {
//       e.getMessage();
//       e.printStackTrace();
//    }
//    finally {
//      try {
//         fr.close();
//         fw.close();
//      } catch(IOException e) {
//            e.getMessage();
//            e.printStackTrace();
//        }
//    }
      
      
      InputStream is = null;
      FileWriter fw = null;
      try {
          is = new FileInputStream("F:\\old.txt");
          fw = new FileWriter("F:\\new.txt");//2.txt保存的位置
          BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
          // 读取一行，存储于字符串列表中
          for (String line = reader.readLine(); line != null; line = reader.readLine()) {
              line = line.trim();
              String linea=line+"1";
              String lineb=line+"0";
//              System.out.println(line);
              
              fw.write(linea);
              fw.write("\r\n");
              fw.write(lineb);
              fw.write("\r\n");
              // do something here
          }

      }catch (FileNotFoundException fnfe){
          fnfe.printStackTrace();
      }catch (IOException ioe){
          ioe.printStackTrace();
      } finally {
    	  try {
			fw.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
          try {
              if (is != null) {
                  is.close();
                  is = null;
              }
          } catch (IOException e) {
              e.printStackTrace();
          }
      }

  }
}