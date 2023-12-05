/*
 * File: MainController.java
 * Author: Nagy János
 * Copyright: 2023, Nagy János
 * Refactor: Vinkovits László
 * Date: 2023-12-05
 * Licenc: MIT
 * Az esetlegesen elofordulo hibakert nem all modomban felelosseget vallalni
 */

package controllers;

import java.util.Random;
import views.MainWindow;

public class MainController {

  enum Round {
    PREFLOP,
    FLOP,
    TURN,
    RIVER,
    SHOW,
  }

  private final MainWindow mainWindow;
  private final String[] cards = {
    "2",
    "3",
    "4",
    "5",
    "6",
    "7",
    "8",
    "9",
    "10",
    "B",
    "D",
    "K",
    "A",
  };

  private Round round = Round.PREFLOP;

  public MainController(MainWindow mainWindow) {
    this.mainWindow = mainWindow;
    this.initEvent();
  }

  private int getRandomCardIndex() {
    Random random = new Random();
    return random.nextInt(cards.length);
  }

  private void updateHumanCards(int card1Index, int card2Index) {
    this.mainWindow.humanCard1Btn.setText(cards[card1Index]);
    this.mainWindow.humanCard2Btn.setText(cards[card2Index]);
  }

  private void updateFlopCards(int flop1Index, int flop2Index, int flop3Index) {
    this.mainWindow.flop1Btn.setText("♦" + cards[flop1Index]);
    this.mainWindow.flop2Btn.setText(cards[flop2Index]);
    this.mainWindow.flop3Btn.setText(cards[flop3Index]);
    this.mainWindow.flop1Btn.setVisible(true);
    this.mainWindow.flop2Btn.setVisible(true);
    this.mainWindow.flop3Btn.setVisible(true);
  }

  private void updateTurnCard(int turnIndex) {
    this.mainWindow.turnButton.setText(cards[turnIndex]);
    this.mainWindow.turnButton.setVisible(true);
  }

  private void updateRiverCard(int riverIndex) {
    this.mainWindow.riverButton.setText(cards[riverIndex]);
    this.mainWindow.riverButton.setVisible(true);
  }

  private void initEvent() {
    this.mainWindow.startBtn.addActionListener(event -> {
        int hcard1 = getRandomCardIndex();
        int hcard2 = getRandomCardIndex();
        updateHumanCards(hcard1, hcard2);
        System.out.printf("%d %d\n", hcard1, hcard2);
      });

    this.mainWindow.stopBtn.addActionListener(event -> {
        System.out.println("Állj");
      });

    this.mainWindow.nextBtn.addActionListener(event -> {
        if (this.round == Round.PREFLOP) {
          int flop1 = getRandomCardIndex();
          int flop2 = getRandomCardIndex();
          int flop3 = getRandomCardIndex();
          updateFlopCards(flop1, flop2, flop3);
          this.round = Round.FLOP;
        } else if (this.round == Round.FLOP) {
          int turn = getRandomCardIndex();
          updateTurnCard(turn);
          this.round = Round.TURN;
        } else if (this.round == Round.TURN) {
          int river = getRandomCardIndex();
          updateRiverCard(river);
          this.round = Round.RIVER;
        }
      });
  }
}
