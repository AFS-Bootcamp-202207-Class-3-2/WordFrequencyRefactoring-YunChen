import java.util.*;

public class WordFrequencyGame {

    public static final String SPLIT_REGEX = "\\s+";
    public static final String CALCULATE_ERROR = "Calculate Error";
    public static final String SPACE = " ";

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
                List<Input> list = calculateMapInputCount(map);
                list.sort((firstWord, secondWord) -> secondWord.getWordCount() - firstWord.getWordCount());
                return stringDelimiteSeparator(list);
            } catch (Exception exception) {
                return CALCULATE_ERROR;
            }
        }
    }

    private String stringDelimiteSeparator(List<Input> list) {
        StringJoiner joiner = new StringJoiner("\n");
        for (Input word : list) {
            String dataString = word.getValue() + SPACE + word.getWordCount();
            joiner.add(dataString);
        }
        return joiner.toString();
    }

    private List<Input> calculateMapInputCount(Map<String, List<Input>> map) {
        List<Input> list = new ArrayList<>();
        for (Map.Entry<String, List<Input>> entry : map.entrySet()) {
            Input input = new Input(entry.getKey(), entry.getValue().size());
            list.add(input);
        }
        return list;
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
