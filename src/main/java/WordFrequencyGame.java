import java.util.*;

public class WordFrequencyGame {

    public static final String SPLIT_REGEX = "\\s+";

    public String getResult(String inputStr) {


        if (inputStr.split(SPLIT_REGEX).length == 1) {
            String wordCount = "1";
            return inputStr + " " + wordCount;
        } else {
            try {
                //split the input string with 1 to n pieces of spaces
                List<Input> inputList = transformStringArrayToList(inputStr);
                //get the map for the next step of sizing the same word
                Map<String, List<Input>> map = getListMap(inputList);
                List<Input> list = new ArrayList<>();
                for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
                    Input input = new Input(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;
                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
                StringJoiner joiner = new StringJoiner("\n");
                for (Input w : inputList) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return "Calculate Error";
            }
        }
    }

    private List<Input> transformStringArrayToList(String inputStr) {
        String[] splitInputStr = inputStr.split(SPLIT_REGEX);
        List<Input> inputList = new ArrayList<>();
        for (String string : splitInputStr) {
            Input input = new Input(string, 1);
            inputList.add(input);
        }
        return inputList;
    }


    private Map<String, List<Input>> getListMap(List<Input> inputList) {
        Map<String, List<Input>> map = new HashMap<>();
        for (Input input : inputList) {
            map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
        }


        return map;
    }


}
