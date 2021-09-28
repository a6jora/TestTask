Техническое задание:
Реализуйте функции сериализации и десериализации двусвязного списка,
заданного следующим образом:


class ListNode {

        public ListNode Prev;
        public ListNode Next;
        public ListNode Rand;
        public String data;
    }

class ListRand {
        public ListNode Head;
        public ListNode Tail;
        public int Count;

        public void Serialize(FileOutputStream s) {
          
        }
			  public void Deserialize(FileInputStream s) {
			  }
			}

Реализация на языке Java представлена в файле main.java
