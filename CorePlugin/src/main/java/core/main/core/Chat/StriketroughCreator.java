package core.main.core.Chat;

public class StriketroughCreator {

    public static String Striketrough(String msg){     /**Aici se face un string de marimea mesajului format numai din spatii, ca sa arate frumos striketroughul*/

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < msg.length(); i++){
            sb.append(" ");
        }

        String finalString = sb.toString();

        return finalString;

    }

}
