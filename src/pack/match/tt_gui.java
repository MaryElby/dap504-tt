package pack.match;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;


/**
 * tt_gui class.  Contains the objects that make up the GUI
 */
public class tt_gui {
    private JButton startTournamentButton;
    private JPanel mainPanel;
    private JTextField txtNumPlayers;
    private JLabel lblNumPlayersLabel;
    private JLabel lblMatchReport;
    private JButton cmdSetTournament;
    public  JTextArea txtAreaMatchReport;
    private JButton exitButton;
    private JTextField txtCashPrize;
    private JTextField txtNonCashPrize;
    private JLabel lblCash;
    private JLabel lblNonCash;
    private int numPlayers;
    private double cashPrize;
    private String nonCashPrize;

    /**
     addReport - Writes a line to the match report text area
     @param strText String - The string of text to write
     **/
    public void addReport(String strText){
        txtAreaMatchReport.append("\n" + strText);
    }

    /**
     constructor
     **/

    public tt_gui() {

        //startTournamentButton.setEnabled(false);
        cmdSetTournament.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {

                   try {
                       numPlayers = Integer.parseInt(txtNumPlayers.getText());
                        if (numPlayers>128){

                            txtAreaMatchReport.setText("Too many players.  Max=128");
                            startTournamentButton.setEnabled(false);
                            txtNumPlayers.requestFocusInWindow();
                        }
                        else {

                            startTournamentButton.setEnabled(true);
                            txtAreaMatchReport.setText("Tournament parameters successfully validated. \n Press 'Start Tournament' to run the tournament.");
                        }
                       nonCashPrize = txtNonCashPrize.getText();
                        try{
                            cashPrize = Double.parseDouble(txtCashPrize.getText());

                        }
                        catch (Exception e2){
                            if(e2.getMessage().equals("empty String")){
                                cashPrize=0;
                            }
                            else {
                                System.out.println("Cannot convert " + txtCashPrize.getText() + " to a number.");
                                txtAreaMatchReport.setText("Cannot convert " + txtCashPrize.getText() + " to a number");
                                txtAreaMatchReport.append("\n" + " Please try again.");
                            }
                        }
                   } catch (NumberFormatException e1) {
                       System.out.println("Cannot convert " + txtNumPlayers.getText() + " to a number.");
                       txtAreaMatchReport.setText("Cannot convert " + txtNumPlayers.getText() + " to a number");
                       txtAreaMatchReport.append( "\n" + " Please try again.");
                       //e1.getMessage();

                       //lblMatchReport.setText(e1.getMessage());
                       startTournamentButton.setEnabled(false);
                   }
               }
           }
        );

        startTournamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    //String theLabel = "Number of players chosen: " + numPlayers;
                    //System.out.println(theLabel);
                    txtAreaMatchReport.setText("");

                    //lblMatchReport.setText(theLabel);
                    try {
                        tRunner theRunner = new tRunner();
                        theRunner.doTournament(tt_gui.this,numPlayers,cashPrize,nonCashPrize);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
        public static void main (String[] args) {
            // write your code here
            JFrame frame = new JFrame("Table Tennis Tournament");
            tt_gui theGui = new tt_gui();
            frame.setContentPane(theGui.mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(600, 600);

            frame.setVisible(true);
        }

    }
