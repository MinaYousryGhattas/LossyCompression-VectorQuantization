import javax.xml.bind.ValidationEvent;
import java.util.Vector;

/**
 * Created by Mina_Yousry on 02/12/2017.
 */
public class VQuant {
    int h,w;
    Vector<Node> nodes;
    Vector<Integer> CompressedNodes;
    Vector<Node> tables;

    VQuant(int th, int tw)
    {
        h = th;
        w = tw;
        nodes = new Vector<>();
        tables = new Vector<>();
        CompressedNodes = new Vector<>();
    }
    VQuant(int th,int tw,Vector<Node> vn)
    {
        h = th;
        w = tw;
        nodes = vn;
        tables = new Vector<>();
    }

    double getDiff(Node n1,Node n2)
    {
        double value = 0;
        for (int i = 0; i < n1.apixels.length;i++)
            for (int j = 0; j < n1.apixels[i].length;j++)
                value+= Math.pow(Math.abs(n1.apixels[i][j] - n2.apixels[i][j]),2);
        return value;
    }
    void buildTable(int level)
    {
        Node n = new Node(h,w,nodes);
        Vector<Node> table = new Vector<>();
        table.addElement(n);

        while (level >= 0) {

            Vector<Node> averages = new Vector<>();
            Vector<Node> temptable = new Vector<>();
            if (level != 0) {
                for (int i = 0; i < table.size(); i++) {
                    averages.addElement(table.get(i).getLaverage());
                    averages.addElement(table.get(i).gethaverage());
                    temptable.addElement(new Node(h,w));
                    temptable.addElement(new Node(h,w));
                }
            }
            else {
                for (int i = 0; i < table.size(); i++) {
                    averages.addElement(table.get(i));
                    temptable.addElement(new Node(h,w));
                }
            }
            for (int i = 0;i < nodes.size();i++)
            {
                Node leastnode = temptable.get(0);
                double leastvalue = getDiff(averages.get(0),nodes.get(i));
                for (int j = 1;j<averages.size();j++)
                {
                    double value = getDiff(averages.get(j),nodes.get(i));
                    if (value < leastvalue)
                    {
                        leastnode = temptable.get(j);
                        leastvalue = value;
                    }
                }
                leastnode.Addnode(nodes.get(i));
            }
            if (level == 0)
            {
                for (int i = 0; i < table.size();i++)
                {
                    if (!table.get(i).equals(temptable.get(i)))
                    {
                        level++;
                        break;
                    }
                }
                level--;
            }
            else
                level--;
            table = temptable;
        }
        tables = table;
        for (Node i : tables)
            i.RoundNode();
    }




    void Compress(int w)
    {
        buildTable(w);
        CompressedNodes = new Vector<>();
        for (int i = 0; i < nodes.size();i++)
        {
            int li = 0;
            double average = getDiff(nodes.get(i),tables.get(0));
            for (int j = 0; j< tables.size();j++)
            {
                double va = getDiff(nodes.get(i),tables.get(j));
                if (average > va)
                {
                    average = va;
                    li =j;
                }
            }
            CompressedNodes.addElement(li);
        }
    }


    void Decompress()
    {
        nodes = new Vector<>();

        for (int i = 0; i < CompressedNodes.size();i++)
            nodes.addElement(tables.get(CompressedNodes.get(i)));

    }
}
