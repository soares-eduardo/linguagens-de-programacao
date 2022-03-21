import java.util.ArrayList;

public class App {

    static String input = "a := (aux - 2) * 200 / 19";

    public static void main(String[] args) {

        input = input.replaceAll("\\s+", "");

        ArrayList<String> lexemes = new ArrayList<>();

        int index = 0;

        while (index < input.length()) {
            switch (input.charAt(index)) {
                case ' ':
                    index++;
                    break;
                case '(':
                    lexemes.add(input.charAt(index) + ", LPAREN, 3");
                    index++;
                    break;
                case ')':
                    lexemes.add(input.charAt(index) + ", RPAREN, 4");
                    index++;
                    break;

                case '+':
                    lexemes.add(input.charAt(index) + ", ADD_OP, 5");
                    index++;
                    break;

                case '-':
                    lexemes.add(input.charAt(index) + ", SUB_OP, 6");
                    index++;
                    break;

                case '*':
                    lexemes.add(input.charAt(index) + ", MUL_OP, 7");
                    index++;
                    break;

                case '/':
                    lexemes.add(input.charAt(index) + ", DIV_OP, 8");
                    index++;
                    break;

                case '>':
                    lexemes.add(input.charAt(index) + ", GT_OP, 9");
                    index++;
                    break;

                case '<':
                    lexemes.add(input.charAt(index) + ", LT_OP, 10");
                    index++;
                    break;

                case '=':
                    if (input.charAt(index + 1) == '=') {
                        lexemes.add(input.charAt(index) + ", EQ_OP, 11");
                        index++;
                        break;
                    } else {
                        index++;
                        break;
                    }

                case ':':
                    if (input.charAt(index + 1) == '=') {
                        lexemes.add(input.charAt(index) + "=, ASSIGN_OP, 12");
                        index++;
                        break;
                    } else {
                        index++;
                        break;
                    }

                default:
                    try {
                        Integer.parseInt(String.valueOf(input.charAt(index)));
                        String numbers = "";
                        intLoop: for (int i = index; i < input.length(); i++) {
                            if (!Character.isDigit(input.charAt(i)) || input.charAt(i) == '\n'
                                    || input.charAt(i) == '\t'
                                    || input.charAt(i) == '\r') {
                                index = i;
                                break intLoop;
                            } else if (input.charAt(i) == input.charAt(input.length() - 1)) {
                                index = input.length();
                            }
                            numbers = numbers + input.charAt(i);
                        }
                        lexemes.add(numbers + ", INT_LIT, 2");
                    } catch (Exception e) {
                        String idents = "";
                        identLoop: for (int i = index; i < input.length(); i++) {
                            if (!Character.isAlphabetic(input.charAt(i)) || input.charAt(i) == '\n'
                                    || input.charAt(i) == '\t'
                                    || input.charAt(i) == '\r') {
                                index = i;
                                break identLoop;
                            } else if (input.charAt(i) == input.charAt(input.length() - 1)) {
                                index = input.length();
                            }
                            idents = idents + input.charAt(i);
                        }
                        lexemes.add(idents + ", IDENT, 1");
                    }
                    break;
            }
        }

        for (String lexeme : lexemes) {
            System.out.println(lexeme);
        }
    }
}