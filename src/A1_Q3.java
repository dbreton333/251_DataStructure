import java.util.*;

public class A1_Q3 {


    public static ArrayList<String> Discussion_Board(String[] posts){

        HashMap<String, Integer> wordsMap = new HashMap<String,Integer>();
        HashMap<String, HashSet<String>> peopleWordMap = new HashMap<String,HashSet<String>>();
        int nbrPl = 0;
        HashSet<String> peopleMap = new HashSet<String>();

        for(String str: posts){
            String[] tokens = str.split(" ");

            peopleMap.add(tokens[0]);
            for (int i = 1; i < tokens.length; i++) {
                Integer fre = wordsMap.get(tokens[i]);

                if(fre != null){

                    wordsMap.put(tokens[i], fre + 1);
                    HashSet<String> pl = peopleWordMap.get(tokens[i]);
                    boolean Exist = pl.add(tokens[0]); //if exist == false then already there

                    if(Exist) { //else add person
                        peopleWordMap.replace(tokens[i], pl);
                    }

                }else{
                    wordsMap.put(tokens[i], 1);
                    HashSet<String> pl = new HashSet<String>();
                    pl.add(tokens[0]);
                    peopleWordMap.put(tokens[i], pl);
                }

            }
        }

        nbrPl = peopleMap.size();


        TreeSet<Map.Entry<String,Integer>> sorted = new TreeSet<Map.Entry<String,Integer>>(
                new Comparator<Map.Entry<String,Integer>>() {
                    @Override
                    public int compare(Map.Entry<String,Integer> o1, Map.Entry<String,Integer> o2) {
                        //Write code here for same string and diff value
                        int resp = o1.getValue().compareTo(o2.getValue());
                        if(resp == 0){
                            resp = o2.getKey().compareTo(o1.getKey());
                        }
                        return resp;
                    }
                } );

        ArrayList<String> answer = new ArrayList<String>();
        for (Map.Entry <String, HashSet<String>> entry : peopleWordMap.entrySet()) {

            if(entry.getValue().size()!=nbrPl){
                wordsMap.remove(entry.getKey());
            }
        }

        sorted.addAll(wordsMap.entrySet());

        for (Map.Entry<String,Integer> e:sorted.descendingSet()) {
            answer.add(e.getKey());
        }
        return answer;
    }

}
