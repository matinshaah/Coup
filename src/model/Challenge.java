package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Challenge {
    public static Player ChallengePlayer(Player player,CardType type){
        ArrayList<Integer> randomOrder = getRandomOrder();
        for (int i = 0; i < 4; i++) {
            int k = randomOrder.get(i);
            if(k!= player.id) {
                Player challengePlayer = Player.get(k);
                if (challengePlayer.isAlive()) {
                    if (challengePlayer.challenge(player, type))
                        return challengePlayer;
                }
            }
        }
        return null;
    }
    public static Player ChallengePlayer(Player player,CardType type,CardType secondType){
        ArrayList<Integer> randomOrder = getRandomOrder();
        for (int i = 0; i < 4; i++) {
            int k = randomOrder.get(i);
            if(k!= player.id) {
                Player challengePlayer = Player.get(k);
                if (challengePlayer.isAlive()) {
                    if (challengePlayer.challenge(player, type,secondType))
                        return challengePlayer;
                }
            }
        }
        return null;
    }


    static ArrayList<Integer> getRandomOrder(){
        ArrayList<Integer> result = new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(0,1,2,3));
        while (list.size()>0){
            int i = new Random().nextInt(4);
            if(list.contains(i)){
                result.add(i);
                list.remove(Integer.valueOf(i));
            }
        }
        return result;

    }
}
