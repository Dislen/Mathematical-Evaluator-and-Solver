import java.util.Scanner;
import java.io.*;

public class Project3
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        LexicalAnalyzer lex = new LexicalAnalyzer();


        System.out.print("Please enter your path: ");//file.txt must already exist
        String path = scan.nextLine();

        File file = new File(path);

        if(!file.exists())//primitive error handling
        {
            System.out.println("File does not exist please enter valid file path");
            System.exit(0);
        }

        System.out.print("Please enter your expression: ");
        String usrEx = scan.nextLine();

        if(!lex.inputsValid(usrEx))
        {
            System.out.println("Please enter a valid expression");
            System.exit(0);
        }


        try(FileWriter writer = new FileWriter(path))
        {
            writer.write(usrEx);
        }
        catch(IOException e)
        {
            System.out.println("Error writing to file");
        }


        StringBuilder contents = new StringBuilder();
        try(BufferedReader reader = new BufferedReader(new FileReader(path)))
        {
            String expression;

            while((expression = reader.readLine()) != null)
            {
                contents.append(expression).append("\n");
            }
        }
        catch(IOException e)
        {
            System.out.println("Error reading file");
        }


        String exFromFile = contents.toString().trim();
        exFromFile = exFromFile.replace(" ", "");
        exFromFile = exFromFile.replace("$", "");
        TokenType[] box = lex.fillTokens(exFromFile);


        System.out.println("Here is your statement tokenized: ");
        for(int i=0; i<box.length; i++)
        {
            if(i == box.length - 1)
            {
                System.out.print(box[i]);
            }
            else
            {
                System.out.print(box[i] + ", ");
            }
        }



        Parser parser = new Parser(box);//i added this ontop of the last project in the main to show it worked

        try
        {
            MathNode root = parser.expression();
            int result = root.evaluate();
            System.out.println('\n'+ "Valid Expression!");
            System.out.println("Result: " + result);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());//prints out whatever error msg and whichever point the parsing failed at
        }

        


    }
}


enum Token 
{
    INT,PLUS,MINUS,MULTIPLY,DIVIDE,EQUALS,NOTVALID,EOS,LEFTPARENTHESES, RIGHTPARENTHESES
}

class TokenType
{
    private final Token type;
    private final String lexeme;

    public TokenType(Token type, String lexeme)
    {
        this.type = type;
        this.lexeme = lexeme;
    }

    public Token getType()
    {
        return type;
    }

    public String getLexeme()
    {
        return lexeme;
    }

    @Override
    public String toString()
    {
        return lexeme;
    }
}
