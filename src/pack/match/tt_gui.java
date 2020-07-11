package pack.match;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;

import static java.lang.Integer.parseInt;


/**
 * tt_gui class.  Contains the objects that make up the GUI
 */
public class tt_gui {
    private JButton cmdStartTournament;
    private JPanel mainPanel;
    private JTextField txtNumPlayers;
    private JLabel lblNumPlayersLabel;
    private JLabel lblMatchReport;
    private JButton cmdSetTournament;
    private JTextArea txtAreaMatchReport;
    private JButton cmdExit;
    private JTextField txtCashPrize;
    private JTextField txtNonCashPrize;
    private JLabel lblCash;
    private JLabel lblNonCash;

    private int numPlayers;
    private double cashPrize;
    private String nonCashPrize;

    /**
     addReport - Writes a line to the match report text area
     This is called from everywhere hence needs to be public
     @param strText String - The string of text to write
     **/
    public void addReport(String strText){
        txtAreaMatchReport.append("\n" + strText);
    }

    /**
     startReport - Writes a line to the match report text area without a carriage return first.
     This is called from the tournament so package-private
     @param strText String - The string of text to write
     **/
    void startReport(String strText){
        txtAreaMatchReport.setText(strText);
    }

    /**
     constructor
     **/

    private tt_gui() {

        //cmdStartTournament.setEnabled(false);
        cmdSetTournament.addActionListener(new ActionListener()
       {
           @Override
           public void actionPerformed(ActionEvent e) {

               try {
                   numPlayers =  parseInt(txtNumPlayers.getText());

                   //the limit is the number of players in the json file
                   if (numPlayers > 1000) {

                       txtAreaMatchReport.setText("Too many players.  Max=1000");
                       cmdStartTournament.setEnabled(false);
                       txtNumPlayers.requestFocusInWindow();
                   } else {
                       nonCashPrize = txtNonCashPrize.getText();
                       try {
                           cashPrize = Double.parseDouble(txtCashPrize.getText());
                           cmdStartTournament.setEnabled(true);
                           txtAreaMatchReport.setText("Tournament parameters successfully validated. \n Press 'Start Tournament' to run the tournament.");

                       } catch (Exception e2) {
                           if (e2.getMessage().equals("empty String")) {
                               cashPrize = 0;
                               cmdStartTournament.setEnabled(true);
                               txtAreaMatchReport.setText("Tournament parameters successfully validated. \n Press 'Start Tournament' to run the tournament.");

                           } else {
                               System.out.println("Cannot convert " + txtCashPrize.getText() + " to a number.");
                               txtAreaMatchReport.setText("Cannot convert " + txtCashPrize.getText() + " to a number");
                               txtAreaMatchReport.append("\n" + " Please try again.");
                               cmdStartTournament.setEnabled(false);
                           }
                       }
                   }

               } catch (NumberFormatException e3) {
                    if (txtNumPlayers.getText().equals("")) {
                        //set default to 64 and run the validation again for other input fields
                        //if they are ok we can go ahead with the tournament
                        numPlayers = 64;
                        txtNumPlayers.setText("64");
                        cmdSetTournament.doClick();
                    }
                    else {
                           System.out.println("Cannot convert " + txtNumPlayers.getText() + " to a number.");
                           txtAreaMatchReport.setText("Cannot convert " + txtNumPlayers.getText() + " to a number");
                           txtAreaMatchReport.append("\n" + " Please try again.");
                       }
               }

           }
       }
        );
/*
  action listener for the Start Tournament button
 */
        cmdStartTournament.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    //clear the match report field
                    txtAreaMatchReport.setText("");

                    try {
                        //create a runner and run the tournament
                        tRunner theRunner = new tRunner();
                        theRunner.doTournament(tt_gui.this,numPlayers,cashPrize,nonCashPrize);
                        //reset tournament start button to false so user has to re-validate before running another
                        cmdStartTournament.setEnabled(false);
                    } catch (FileNotFoundException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
            }
        });
        /*
          action listener to close the application
         */
        cmdExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        /*
          key listener to detect a keypress and disable the tournament start
          so that the user has to re-validate if they've changed a parameter
         */
        txtNumPlayers.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                cmdStartTournament.setEnabled(false);
            }
        });
        /*
          key listener to detect a keypress and disable the tournament start
          so that the user has to re-validate if they've changed a parameter
         */
        txtCashPrize.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                cmdStartTournament.setEnabled(false);
            }
        });
        /*
          key listener to detect a keypress and disable the tournament start
          so that the user has to re-validate if they've changed a parameter
         */
        txtNonCashPrize.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                cmdStartTournament.setEnabled(false);
            }
        });
    }

    /**
     * main entry point
     * @param args no arguments (required)
     */
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
