import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.*;
import java.util.Vector;

/**
 * Created by Mina_Yousry on 02/12/2017.
 */
public class Files {
    File source;
    File Header = new File("Header.txt");
    File compressed = new File("compressed.txt");
    File other = new File("Other.txt");
    File outPut;

    Files()
    {

    }

    Files(String s, String o)
    {
        source = new File(s);
        outPut = new File(o);
    }

//    int[][] ImageTo2d() throws IOException
//    {
//        BufferedImage img = ImageIO.read(new File("my_image1.bmp"));
//        System.out.println(img.getColorModel());
//        //img.get
//        Raster raster = img.getData();
//        Raster raster = new WritableRaster(new SampleModel(10,))
//        int w = img.getWidth();
//        int h = img.getHeight();
//        int[][] array = new int[w][h];
//        for (int j = 0; j < w; j++) {
//            for (int k = 0; k < h; k++) {
//                array[j][k] = raster.getSample(j,k,0);//*/img.getRGB(j,k); //.getSample(j, k, 0);
//            //    System.out.print(raster.getSample(j,k,0) + " ");
//
//            }
//         //   System.out.println();
//        }
//        return array;
//    }
//    void _2dToImage(int [][] array) throws IOException {
//        BufferedImage theImage = new BufferedImage(array.length, array[0].length, BufferedImage.TYPE_BYTE_GRAY);
//        for (int y = 0; y < array.length; y++) {
//            for (int x = 0; x < array[0].length; x++) {
//                theImage.setRGB(y, x, array[y][x]);
//            }
//        }
//        ImageIO.write(theImage,"png",new File("outimage.bmp"));
//    }

    public  int[][] readImage(String path){

        BufferedImage img;
        try {
            img = ImageIO.read(new File(path));

            int hieght=img.getHeight();
            int width=img.getWidth();

            int[][] imagePixels=new int[hieght][width];
            for(int x=0;x<width;x++){
                for(int y=0;y<hieght;y++){

                    int pixel=img.getRGB(x, y);

                    int red=(pixel  & 0x00ff0000) >> 16;
                    int grean=(pixel  & 0x0000ff00) >> 8;
                    int blue=pixel  & 0x000000ff;
                    int alpha=(pixel & 0xff000000) >> 24;
                    imagePixels[y][x]=red;
                }
            }

            return imagePixels;
        } catch (IOException e) {
            //TODO Auto-generated catch block
            return null;
        }

    }
    void WriteNode(Node n,DataOutputStream d) throws IOException
    {
        for (int i = 0; i < n.apixels.length;i++)
            for (int j = 0; j < n.apixels[0].length;j++)
                d.writeInt((int)n.apixels[i][j]);
    }

    Node ReadNode(int x, int y, DataInputStream d) throws IOException
    {
        Node n = new Node(x,y);
        for (int i = 0; i < x; i++)
            for (int j = 0; j < y;j++)
                n.apixels[i][j] = d.readInt();
        return n;
    }
    void WriteHeader(VQuant v) throws IOException
    {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(Header));
        dos.writeInt(v.h);
        dos.writeInt(v.w);

        for (int i = 0; i < v.tables.size();i++)
            WriteNode(v.tables.get(i),dos);

    }

    void ReadHeader(VQuant v) throws IOException
    {
        DataInputStream dos = new DataInputStream(new FileInputStream(Header));
        v.h = dos.readInt();
        v.w = dos.readInt();
        v.tables = new Vector<>();
        while (dos.available()>0)
        {
            v.tables.addElement(ReadNode(v.h,v.w,dos));
        }
    }

    void WriteCData(VQuant v) throws IOException
    {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(compressed));
        for (int i = 0; i < v.CompressedNodes.size();i++)
            dos.writeInt(v.CompressedNodes.get(i));
    }

    void ReadCData(VQuant v) throws IOException
    {
        DataInputStream dos = new DataInputStream(new FileInputStream(compressed));
        v.CompressedNodes = new Vector<>();
        while(dos.available()>0)
        {
            v.CompressedNodes.add(dos.readInt());
        }
    }

    void WriteInt(int x,boolean flag) throws IOException
    {
        DataOutputStream dos = new DataOutputStream(new FileOutputStream(other,flag));
        dos.writeInt(x);
    }
    int ReadInt() throws IOException
    {
        DataInputStream dos = new DataInputStream(new FileInputStream(other));
        return dos.readInt();
    }


    public void writeImage(int[][] imagePixels,String outPath){

        BufferedImage image = new BufferedImage(imagePixels[0].length, imagePixels.length,BufferedImage.TYPE_INT_RGB);
        for (int y= 0; y < imagePixels.length; y++) {
            for (int x = 0; x < imagePixels[y].length; x++) {
                int value =-1 << 24;
                value= 0xff000000 | (imagePixels[y][x]<<16) | (imagePixels[y][x]<<8) | (imagePixels[y][x]);
                image.setRGB(x, y, value);
            }
        }

        File ImageFile = new File(outPath);
        try {
            ImageIO.write(image, "jpg", ImageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
