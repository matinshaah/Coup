package resources;

import com.google.gson.Gson;
import model.BotPlayer;
import model.HumanPlayer;
import model.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GsonAndFile {
    String playerFilePath="src/resources/playerFile.txt";
    private static GsonAndFile instance;
    Gson gson;
    public static GsonAndFile getInstance(){
        if(instance==null) instance = new GsonAndFile();
        return instance;
    }

    private GsonAndFile(){
        gson = new Gson();
    }

    public ArrayList<Player> getPlayersList(){
        int i=1;
        ArrayList<Player> players = new ArrayList<>();
        File playerFile = new File(playerFilePath);
        Scanner sc = null;
        try {
            sc = new Scanner(playerFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (true) {
            assert sc != null;
            if (!(sc.hasNextLine()&&i<5)) break;
            String detail = sc.nextLine();
            Player player;
            if(i==1){
                player = gson.fromJson(detail, HumanPlayer.class);
            }else {
                player = gson.fromJson(detail, BotPlayer.class);
            }
            i++;
            players.add(player);
        }

        return players;
    }

    public void writePlayer(Player player){
        File playerFile = new File(playerFilePath);
        try {
            FileWriter fileWriter = new FileWriter(playerFile,true);
            String str = gson.toJson(player);
            fileWriter.write(str+"\n");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
