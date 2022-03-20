import java.util.ArrayList;

public class App {

    static String input = "a := (aux - 2) * 200 / 19 ";

    // "a:=(aux-2)*200/19"
    // |
    public static void main(String[] args) {

        // input = input.replaceAll("\\s+", "");

        ArrayList<String> lexemes = new ArrayList<>();

        input = input.replaceAll("\\(", "\\( ");
        input = input.replaceAll("\\)", " \\)");

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
                            if (input.charAt(i) == ' ' || input.charAt(i) == '\n' || input.charAt(i) == '\t'
                                    || input.charAt(i) == '\r') {
                                index = i + 1;
                                break intLoop;
                            }
                            numbers = numbers + input.charAt(i);
                        }
                        lexemes.add(numbers + ", INT_LIT, 2");
                    } catch (Exception e) {
                        String idents = "";
                        identLoop: for (int i = index; i < input.length(); i++) {
                            if (input.charAt(i) == ' ' || input.charAt(i) == '\n' || input.charAt(i) == '\t'
                                    || input.charAt(i) == '\r') {
                                index = i + 1;
                                break identLoop;
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

    public static String findLexeme(int i) {
        switch (input.charAt(i)) {
            case '(':
                return "LPAREN, 3";

            case ')':
                return "RPAREN, 4";

            case '+':
                return "ADD_OP, 5";

            case '-':
                return "SUB_OP, 6";

            case '*':
                return "MUL_OP, 7";

            case '/':
                return "DIV_OP, 8";

            case '>':
                return "GT_OP, 9";

            case '<':
                return "LT_OP, 10";

            case '=':
                if (input.charAt(i + 1) == '=') {
                    return "EQ_OP, 11";
                }

            case ':':
                if (input.charAt(i + 1) == '=') {
                    return "ASSIGN_OP, 12";
                }
            default:
                return null;
        }
    }
}

// Identificar se corresponde a algum token que não número ou variavel
// Se corresponder, guardar (o problema é se for um EQ_OP ou um ASSIGN_OP)

// Se não corresponderb

// Identificar se for número (é um INT_LIT, o problema é descobrir o tamanho)

// Tentar converter até

// Identificar se for um digito (é um IDENT, o problema é descobrir o nome
// completo)