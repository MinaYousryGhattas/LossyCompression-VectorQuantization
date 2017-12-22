import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by Mina_Yousry on 02/12/2017.
 */
public class Node {
    double [][] apixels;
    Vector<Node> vchild;
    Node(int h,int w)
    {
        apixels = new double[h][w];
        vchild = new Vector<>();
    }
    Node(int h,int w,Vector<Node> vn) {
        apixels = new double[h][w];
        if (vn == null)
            return;
        for (int i = 0; i < vn.size(); i++) {
            for (int a = 0; a < h; a++)
                for (int b = 0; b < w; b++)
                    apixels[a][b] += (vn.get(i)).apixels[a][b];
        }
        for (int a = 0; a < h; a++)
            for (int b = 0; b < w; b++) {
                if (vn.size() == 0)
                    apixels[a][b] = 0;
                else
                    apixels[a][b] /= vn.size();
            }
        vchild = vn;
    }
    void Addnode(Node n)
    {
        vchild.addElement(n);
        int h = apixels.length;
        int w = apixels[0].length;
        apixels = new double[h][w];
        for (int i = 0; i < vchild.size(); i++) {
            for (int a = 0; a < h; a++)
                for (int b = 0; b < w; b++)
                    apixels[a][b] += (vchild.get(i)).apixels[a][b];
        }
        for (int a = 0; a < h; a++)
            for (int b = 0; b < w; b++) {
                apixels[a][b] /= vchild.size();
            }
    }
    Node getLaverage()
    {
        Node n = new Node(apixels.length,apixels[0].length,null);
        for (int i = 0; i < apixels.length;i++)
            for (int j = 0;j < apixels[i].length;j++)
                n.apixels[i][j] = Math.round(apixels[i][j])-1;
        return n;
    }
    Node gethaverage()
    {
        Node n = new Node(apixels.length,apixels[0].length,null);
        for (int i = 0; i < apixels.length;i++)
            for (int j = 0;j < apixels[i].length;j++)
                n.apixels[i][j] = Math.round(apixels[i][j])+1;
        return n;
    }

    public boolean equals(Node obj) {
        for (int i = 0; i < apixels.length;i++)
            for (int j = 0; j < apixels[i].length;j++)
                if (apixels[i][j] != obj.apixels[i][j])
                    return false;
        return true;
    }


    public void RoundNode()
    {
        for (int i = 0; i < apixels.length;i++)
            for (int j = 0; j < apixels[i].length;j++)
                apixels[i][j] = Math.round(apixels[i][j]);
    }

    public void CopyNode(Node b)
    {
        for (int i = 0; i < apixels.length;i++)
            for (int j = 0; j < apixels[0].length;j++)
                apixels[i][j] = b.apixels[i][j];
    }
}
