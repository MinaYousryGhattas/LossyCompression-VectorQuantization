import java.io.IOException;
import java.util.Vector;

/**
 * Created by Mina_Yousry on 03/12/2017.
 */
public class Controller {
    static Vector<Node> pixelsToNode(int nx, int ny, int[][] v)
    {
        Vector<Vector<Node>> vn = new Vector<>();
        int h = (int)Math.ceil((double)v.length / nx);
        int w = (int)Math.ceil((double)v[0].length / ny);
        for (int i = 0; i < h; i++)
        {
            vn.addElement(new Vector<>());
            for (int j = 0; j < w;j++)
                vn.get(i).addElement(new Node(nx,ny,null));
        }

        for (int i = 0;i < v.length;i++) {
            for (int j = 0; j < v[i].length; j++) {
                int x = (i) / nx;
                int y = (j) / ny;
                vn.get(x).get(y).apixels[i % nx][j % ny] = v[i][j];
            }
        }

        Vector<Node> vnodes = new Vector<>();


        for (int i = 0; i < vn.size();i++)
            for (int j = 0; j < vn.get(0).size();j++)
            {
                vnodes.addElement(vn.get(i).get(j));
            }

        return vnodes;
    }

    static int[][] NodesToPixel(int x,int y,Vector<Node> v1d)
    {
        Node[][] v2d = new Node[x][y];

        for (int i = 0; i < x; i++)
            for (int j = 0; j < y;j++)
                v2d[i][j] = new Node(v1d.get(0).apixels.length,
                        v1d.get(0).apixels[0].length);

        int xn = 0,yn = 0;
        for (int i = 0; i < v1d.size();i++)
        {
            xn = i / y;
            yn = i % y;
            v2d[xn][yn].CopyNode(v1d.get(i));
            i = i;
        }
        int xl = v2d.length * v1d.get(0).apixels.length;
        int yl = v2d[0].length * v1d.get(0).apixels[0].length;

        int[][] pixels = new int[xl][yl];

        for (int i = 0; i < v2d.length;i++)
            for (int j = 0; j < v2d[0].length; j++)
            {
                int xt = i * v1d.get(0).apixels.length;
                int yt = j * v1d.get(0).apixels[0].length;

                for (int ii = 0; ii < v1d.get(0).apixels.length; ii++)
                    for (int jj = 0; jj < v1d.get(0).apixels[0].length;jj++)
                    {
                        pixels[xt+ii][yt+jj] = (int)v2d[i][j].apixels[ii][jj];
                    }
            }
        return pixels;
    }

    static void Check2Darrays(int[][]a, int[][]b)
    {
        for (int i = 0; i < a.length;i++)
            for (int j = 0; j < a[0].length;j++)
                if (a[i][j] != b[i][j])
                {
                    System.out.println("2 arrays are not equal at "+i + " " + j);
                    System.out.println(a[i][j] + " " + b[i][j]);
                    return;
                }
        System.out.println("2 arrays are equal");
    }

    static void Compress(int nx, int ny, int level, String path) throws IOException
    {
        Vector<Node> vn;
        int [][] v;
        Files f = new Files();
        v = f.readImage(path);
        int x = (int)Math.ceil((double)v.length/nx);
        int y = (int)Math.ceil((double)v[0].length/nx);
        f.WriteInt(x,false);
        f.WriteInt(y,true);
        vn = pixelsToNode(nx,ny,v);
        VQuant com = new VQuant(nx,ny,vn);
        com.Compress(level);
        f.WriteHeader(com);
        f.WriteCData(com);
    }

    static void Decompress(int nx,int ny,String path) throws IOException
    {
        VQuant dec = new VQuant(nx,ny);
        Files f = new Files();
        int x = f.ReadInt();
        int y = f.ReadInt();
        f.ReadHeader(dec);
        f.ReadCData(dec);
        dec.Decompress();
        int[][]v1 = NodesToPixel(x,y,dec.nodes);
        f.writeImage(v1,path);
    }

}
