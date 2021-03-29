package Class.Player;

import Class.Card.*;
import java.time.LocalTime;

public class Player {
    private String playerName;
    private int sumCard;
    private boolean hiji;
    private boolean playing;
    private PlayerCard handCard;

    // User defined constructor
    public Player(String name, PlayerCard pCard) {
        this.playerName = name;
        this.sumCard = 0;
        this.hiji = false;
        this.playing = false;
        this.handCard = pCard;
    }

    // Getter player's name
    public String getPlayerName() {
        return this.playerName;
    }

    // Getter player's sum card
    public int getPlayerSumCard() {
        return this.sumCard;
    }

    // Getter player's hiji status
    public boolean isHiji() {
        return this.hiji;
    }

    // Getter player's turn status
    public boolean isPlaying() {
        return this.playing;
    }

    // Getter to player's card list
    public PlayerCard getHandCard() {
        return handCard;
    }

    // Getter to print player's card
    public void listCard() {
        handCard.printPCard();
    }

    // Setter player's total card
    public void setSumCard() {
        this.sumCard = handCard.lengthPC();
    }

    // Setter player's hiji status
    public void setHiji(boolean p_hiji) {
        this.hiji = p_hiji;
    }

    // Setter player's playing status
    public void setPlaying(boolean p_play) {
        this.playing = p_play;
    }

    public void declareHiji() {
        // Cannot be declare due to timer availability
        // int currentTime = LocalTime.now().getSecond() + LocalTime.now().getHour()*3600 + LocalTime.now().getMinute()*60 ;

        // if (getPlayerSumCard() > 1) {
        //     // Draw
        // }
        // int Time = LocalTime.now().getSecond() + LocalTime.now().getHour()*3600 + LocalTime.now().getMinute()*60 ;

        // int TimeDiff = Time - currentTime;
        // else {
        //     // if (Timer < 3 detik &&) {
        //         setHiji(true);
        //     }
        //     System.out.println("Hiji!");
        //     int timeDiff;
        // }
    }
}