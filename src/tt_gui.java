import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class tt_gui {
    private JButton startTournamentButton;
    private JPanel mainPanel;
    private JTextField txtNumPlayers;
    private JLabel lblNumPlayersLabel;
    private JLabel lblMatchReport;

    public tt_gui() {
        startTournamentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String theLabel = "Number of players chosen: "+txtNumPlayers.getText();
                System.out.println(theLabel);

                lblMatchReport.setText(theLabel);
                try {
                    Main.doTournament();
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
            }
        });
        }
        public static void main (String[] args) {
            // write your code here
            JFrame frame = new JFrame("Table Tennis Tournament");
            frame.setContentPane(new tt_gui().mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setSize(400, 400);

            frame.setVisible(true);
        }

    }
