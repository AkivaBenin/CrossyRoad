package Game_Crossing_Road.Screens;

public class Score {
    private int score;
    private int tempScore;

    public Score() {
        score = 0;
    }

    public void increaseScore(int amount) {
        tempScore += amount;
    }

    public void decreaseScore(int amount) {
        tempScore -= amount;
    }

    public int getScore() {
        return score;
    }

    public void updateScore (){
        if (tempScore > score) {
            score = tempScore;
        }
    }
}

