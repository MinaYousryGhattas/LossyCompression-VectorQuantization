import javax.swing.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

/**
 * Created by Mina_Yousry on 02/12/2017.
 */
public class Main {


    public static void main(String[] args) throws IOException {
//        Controller.Compress(2,2,10 ,"200200.jpg");
//        Controller.Decompress(2,2,"outimage.jpg");
        JFrame frame = new JFrame("Vector Quantization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUI());
        frame.pack();
        frame.setVisible(true);
    }



}
/*
6 6
1 2 7 9 4 11
3 4 6 6 12 12
4 9 15 14 9 9
10 10 20 18  8 8
4 3 17 16 1 4
4 5 18 18 5 6

 */
//        for (int i = 0; i < v.length;i++) {
//            for (int j = 0; j < v[i].length; j++)
//                System.out.print(v[i][j] + " ");
//            System.out.println();
//        }
//        System.out.println("XXXX");
//        //     Node n = new Node(3,3);
//        //      n.Fill(0,0,10);
//        Node n = new Node(2, 2, null);
//        Vector<Node> vn = new Vector<>();
//        Scanner scan = new Scanner(System.in);
//        int[][] v;
//        int[][] v1;
//        int l, w;
////        l = scan.nextInt();
////        w = scan.nextInt();
////        v = new int[l][w];
//        Files f = new Files();
//        v = f.readImage("cameraMan.jpg");
////        for (int i = 0; i < l;i++)
////            for (int j = 0; j < w;j++)
////                v[i][j] = scan.nextInt();
//
//
//        vn = pixelsToNode(2, 2, v);
//        v1 = NodesToPixel((int)Math.ceil((double)v.length/2),(int)Math.ceil((double)v[0].length/2),vn);
//        System.out.println(v.length + " " + v1.length);
//        System.out.println(v[0].length + " " + v1[0].length);
//        Check2Darrays(v,v1);
//
//        VQuant q = new VQuant(2, 2, vn);
//        VQuant qq = new VQuant(2,2);
//        q.Compress(1);
////        for (int i : q.CompressedNodes)
////            System.out.print(i + " ");
////        System.out.println("XX");
//        f.WriteHeader(q);
//        System.out.println(q.tables.size());
//        f.ReadHeader(qq);
//        System.out.println(qq.tables.size());
//
//
//        qq.Decompress();
////        for (Node no : q.nodes) {
////            for (int i = 0; i < no.apixels.length; i++) {
////                for (int j = 0; j < no.apixels[i].length; j++) {
////                    System.out.print(no.apixels[i][j]+ " ");
////                }
////                System.out.println();
////            }
////            System.out.println();
////        }
//        v1 = NodesToPixel(63,63,qq.nodes);
//        f.writeImage(v1,"outimage.jpg");


//        Files f = new Files();
//        f.writeImage(f.readImage("cameraMan.jpg"),"outimage.jpg");
