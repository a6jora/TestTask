import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class main {

    static class ListNode {

        public ListNode Prev;
        public ListNode Next;
        public ListNode Rand;
        public String data;
    }

    static class ListRand {
        public ListNode Head;
        public ListNode Tail;
        public int Count;

        public void Serialize(FileOutputStream s) {
            ArrayList<ListNode> listNodes = new ArrayList<>();
            HashMap<ListNode, Integer> mapWithIndex = new HashMap<>(); // new
            ListNode currentNode = Head;
            System.out.println(Head.data);
            for (int i = 0; i < Count; i++) {
                listNodes.add(currentNode);
                mapWithIndex.put(currentNode, i);  // new
                currentNode = currentNode.Next;
            }

            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(s));
            try {

                for (ListNode node :
                        listNodes) {
                    String indexOfRand = String.valueOf(mapWithIndex.get(node.Rand)); // new
                    writer.write(node.data + "/" + indexOfRand + "\n");

                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        public void Deserialize(FileInputStream s) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(s));
            ArrayList<ListNode> nodesList = new ArrayList<>();
            ArrayList<Integer> randIndexList = new ArrayList<>();

            String line;
            int indexOfRand;

            try {
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    indexOfRand = line.lastIndexOf("/");
                    randIndexList.add(Integer.parseInt(line.substring(indexOfRand + 1)));

                    ListNode currentNode = new ListNode();
                    currentNode.data = line.substring(0, indexOfRand);
                    nodesList.add(currentNode);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            Count = nodesList.size();
            for (int i = 0; i < Count; i++) {
                if (i == 0) {
                    nodesList.get(i).Prev = null;
                } else {
                    nodesList.get(i).Prev = nodesList.get(i - 1);
                }

                if (i == Count - 1) {
                    nodesList.get(i).Next = null;
                } else {
                    nodesList.get(i).Next = nodesList.get(i + 1);
                }
            }
            for (int i = 0; i < Count; i++) {
                nodesList.get(i).Rand = nodesList.get(randIndexList.get(i));
            }
            Head = nodesList.get(0);
            Tail = nodesList.get(Count - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<ListNode> testList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            testList.add(new ListNode());
            testList.get(i).data = "part " + i;
        }
        for (int i = 0; i < 6; i++) {
            testList.get(i).Next = testList.get(i + 1);
        }
        for (int i = 1; i < 7; i++) {
            testList.get(i).Prev = testList.get(i - 1);
        }
        for (int i = 0; i < 7; i++) {
            testList.get(i).Rand = testList.get((int) (Math.random() * 7));
        }
        ListRand listRand = new ListRand();
        listRand.Head = testList.get(0);
        listRand.Tail = testList.get(6);
        listRand.Count = 7;

        FileInputStream fileInputStream = new FileInputStream("serials.txt");
        listRand.Deserialize(fileInputStream);

        fileInputStream.close();
        ListNode currentNode = listRand.Head;
        while (true) {
            System.out.println("===");
            if (currentNode.Prev != null)
                System.out.println("prev:" + currentNode.Prev.data);
            else {
                System.out.println("null of head");
            }
            System.out.println("curr:" + currentNode.data);
            System.out.println(((currentNode.Next != null) ? "nex:" + currentNode.Next.data : "null of tail"));
            System.out.println("rand:" + currentNode.Rand.data);
            currentNode = currentNode.Next;
            if (currentNode == null) break;
        }
//
//        FileOutputStream fileOutputStream = new FileOutputStream("serials.txt");
//        listRand.Serialize(fileOutputStream);
//        fileOutputStream.close();

    }
}
