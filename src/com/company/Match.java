package com.company;

public class Match {
    Player player1;
    Player player2;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

        public Player setWinner() {

            Player winner;
            //return winner;

            return player1;
        }

        public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }


    public Player setLoser() {
        return player2;
    }
}
