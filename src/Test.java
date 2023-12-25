import queryHandlers.*;

public class Test {
    public static void main(String[] args) {
        ParseQuery parseQuery = new ParseQuery();
        String[] stmts = {
                "glob is I",
                "prok is V",
                "pish is X",
                "tegj is L",
                "glob glob Silver is 34 Credits",
                "glob prok Gold is 57800 Credits",
                "pish pish Iron is 3910 Credits",
                "how much is pish tegj glob glob ?",
                "how many Credits is glob prok Silver ?",
                "how many Credits is glob glob Gold ?",
                "how many Credits is glob glob glob glob glob glob Gold ?",
                "how many Credits is pish tegj glob Iron ?",
                "Does pish tegj glob glob Iron has more Credits than glob glob Gold ?",
                "Does glob glob Gold has less Credits than pish tegj glob glob Iron ?",
                "Is glob prok larger than pish pish ?",
                "Is tegj glob glob smaller than glob prok ?",
                "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?"
        };
        parseQuery.execute(stmts);
    }
}