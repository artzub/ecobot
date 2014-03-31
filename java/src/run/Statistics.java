package run;

import bot.IListener;

import java.util.LinkedList;

/**
 * Created by hp on 23.03.2014.
 */
public class Statistics implements IListener {

    private Statistics() {
        list = new LinkedList<String>();
    }

    private static Statistics self;
    public static Statistics getInstance() {
        if (self == null)
            self = new Statistics();
        return self;
    }

    private LinkedList<String> list;

    String curStr;

    @Override
    public void Read(String str) {
        if (str == null || str.isEmpty())
            return;
        String strt = str;
        int i = str.indexOf('$');
        if (i > -1) {
            curStr += str.substring(0, i);
            list.add(curStr);
            curStr = strt.substring(i, strt.length() - 1).replace("$\n", "");
        }
        else {
            curStr += str;
        }
        //String.format("{id:%d, data: ['%s]}", list.size(), str.replace("$", "'").replace(";", "','"))
        //list.add(str);
    }

    public String getLastFromTo(int from, int to) {
        StringBuilder str = new StringBuilder();
        int i = 0;//from == -1 ? 0 : from;
        int j = list.size(); //to == -1 ? list.size() : to;

        /*if (i > j) {
            i = j;
            j = from;
        }

        if (i > list.size() - 1)
            i = 0;
        if(j > list.size())
            j = list.size();*/

        /*for (; i < j; i++) {
            str.append(list.get(i));
            if (i < j - 1)
                str.append(",");
        }*/
        String s = "[]";
        if (list.size() > 0) {
            s = "[" + list.get(list.size() - 1) + "]";
            list.clear();
        }
        return s;
    }
}
