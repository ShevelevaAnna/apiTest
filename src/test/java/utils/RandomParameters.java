package utils;

import utils.object_utils.PostsObject;
import java.util.Random;

public class RandomParameters {
    private final static int NUMBER_LATIN_ALPHABET = 26;
    private final static int ASCII_LATIN_a = 97;

    static String randomText(int lengthWord){
        StringBuilder word = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < lengthWord; i++){
            word.append((char) (rand.nextInt(NUMBER_LATIN_ALPHABET) + ASCII_LATIN_a));
        }
        return word.toString();
    }

    public static PostsObject getRandomPost(int userId, int id){
        return new PostsObject(userId, id, RandomParameters.randomText(10), RandomParameters.randomText(20));
    }
}
