
import javax.swing.JOptionPane;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LUI implements ActionListener {
    public static void main(String[] args) {
        LUI gui = new LUI();
    }
    private JFrame frame;
    private JButton reserveSeatButton, cancelSeatButton, displaySeatButton, exitButton, routesButton,printTicketButton;
    private JButton viewAllSeatsButton;
    private JLabel busNumberLabel, seatNumberLabel, passengerNameLabel;
    private JTextField busNumberField, seatNumberField, passengerNameField;
    private int[][] seats = new int[10][35]; // 10 buses with 30 seats each

    public LUI() {
        // Create the frame and set its properties
        frame = new JFrame("Bus Reservation System");
        frame.setSize(430, 475);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(255, 255, 255));//white

        // Create the components
        busNumberLabel = new JLabel("Enter Bus Number:");
        busNumberLabel.setBounds(1, 0, 194, 60);
        busNumberLabel.setForeground(new Color(0, 0, 255));//blue
        busNumberField = new JTextField();
        busNumberField.setBounds(195, 0, 194, 60);
        seatNumberLabel = new JLabel("Enter Seat Number:");
        seatNumberLabel.setBounds(1, 61, 194, 60);
        seatNumberLabel.setForeground(new Color(0, 0, 255)); 
        seatNumberField = new JTextField();
        seatNumberField.setBounds(195, 61, 194, 60);
        passengerNameLabel = new JLabel("Enter Passenger Name:");
        passengerNameLabel.setBounds(1, 122, 194, 60);
        passengerNameLabel.setForeground(new Color(0, 0, 255)); 
        passengerNameField = new JTextField();
        passengerNameField.setBounds(195, 122, 194, 60);
        reserveSeatButton = new JButton("Reserve Seat");
        reserveSeatButton.setBounds(1, 180, 194, 60);
        reserveSeatButton.setBackground(new Color(0, 204, 0));
        cancelSeatButton = new JButton("Cancel Seat");
        cancelSeatButton.setBounds(195, 180, 194, 60);
        cancelSeatButton.setBackground(new Color(255, 102, 102));
        displaySeatButton = new JButton("Display Seat");
        displaySeatButton.setBounds(195, 242, 194, 60);
        displaySeatButton.setBackground(new Color(255, 204, 0));
        exitButton = new JButton("Exit");
        exitButton.setBounds(1, 358, 388, 60);
        exitButton.setBackground(new Color(255, 102, 102)); 
        routesButton = new JButton("Routes");
        routesButton.setBounds(1, 242, 194, 60);
        routesButton.setBackground(new Color(0, 204, 204));
        printTicketButton = new JButton("Print Ticket");
        printTicketButton.setBounds(1, 299, 194, 60);
        printTicketButton.setBackground(new Color(255, 204, 0));
        viewAllSeatsButton = new JButton("View All Seats");
        viewAllSeatsButton.setBounds(195, 299, 194, 60);
        viewAllSeatsButton.setBackground(new Color(204, 204, 204)); // Light Gray

        

        // Add action listeners to the buttons
        reserveSeatButton.addActionListener(this);
        cancelSeatButton.addActionListener(this);
        displaySeatButton.addActionListener(this);
        exitButton.addActionListener(this);
        routesButton.addActionListener(this);
        printTicketButton.addActionListener(this);
        viewAllSeatsButton.addActionListener(this);
        frame.getContentPane().setLayout(null);
        // Add the components to the frame
        frame.getContentPane().add(busNumberLabel);
        frame.getContentPane().add(busNumberField);
        frame.getContentPane().add(seatNumberLabel);
        frame.getContentPane().add(seatNumberField);
        frame.getContentPane().add(passengerNameLabel);
        frame.getContentPane().add(passengerNameField);
        frame.getContentPane().add(reserveSeatButton);
        frame.getContentPane().add(cancelSeatButton);
        frame.getContentPane().add(displaySeatButton);
        frame.getContentPane().add(exitButton);
        frame.getContentPane().add(routesButton);
        frame.getContentPane().add(printTicketButton);
        frame.getContentPane().add(viewAllSeatsButton);


        // Show the frame
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Handle button clicks
        if (e.getSource() == reserveSeatButton) {
            int busNumber = Integer.parseInt(busNumberField.getText());
            int seatNumber = Integer.parseInt(seatNumberField.getText());
            String passengerName = passengerNameField.getText();
            if (busNumber < 1 || busNumber > 10 || seatNumber < 1 || seatNumber > 35) {
                JOptionPane.showMessageDialog(frame, "Invalid bus number or seat number");
            } else if (seats[busNumber-1][seatNumber-1] == 1) {
                JOptionPane.showMessageDialog(frame, "Seat already reserved");
            } else {
                seats[busNumber-1][seatNumber-1] = 1;
                JOptionPane.showMessageDialog(frame, "Seat reserved for " + passengerName);
            }
        } else if (e.getSource() == cancelSeatButton) {
            int busNumber = Integer.parseInt(busNumberField.getText());
            int seatNumber = Integer.parseInt(seatNumberField.getText());
            String passengerName = passengerNameField.getText();
            if (busNumber < 1 || busNumber > 10 || seatNumber < 1 || seatNumber > 35) {
                JOptionPane.showMessageDialog(frame, "Invalid bus number or seat number");
            } else if (seats[busNumber-1][seatNumber-1] == 0) {
                JOptionPane.showMessageDialog(frame, "Seat not reserved");
            } else if (!passengerName.equals(getPassengerName(busNumber, seatNumber))) {
                JOptionPane.showMessageDialog(frame, "Passenger name does not match reservation");
            } else {
            	seats[busNumber-1][seatNumber-1] = 0;
            	passengerNameField.setText("");
            	JOptionPane.showMessageDialog(frame, "Reservation canceled for " + passengerName);
            }
        } else if (e.getSource() == displaySeatButton) {
            int busNumber = Integer.parseInt(busNumberField.getText());
            if (busNumber < 1 || busNumber > 10) {
                JOptionPane.showMessageDialog(frame, "Invalid bus number");
            } else {
                JPanel busPanel = new JPanel(new GridLayout(6, 5, 2, 2)); 
                busPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); 
                busPanel.add(new JLabel("Bus " + busNumber, SwingConstants.CENTER));
                for (int i = 0; i < 35; i++) {
                    JButton button = new JButton();
                    button.setPreferredSize(new Dimension(30, 30));
                    if (seats[busNumber-1][i] == 1) {
                        button.setBackground(Color.RED);
                        String passengerName = getPassengerName(busNumber, i+1);
                        button.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(frame, "Passenger name: " + passengerName);
                            }
                        });
                    } else {
                        button.setBackground(Color.GREEN);
                    }
                    busPanel.add(button);
                }
                JOptionPane.showMessageDialog(frame, busPanel);
            }
        }else if (e.getSource() == routesButton) {
// Display the routes for all 10 buses
String[] routes = {"<html><font color='Blue'>Bus1(Driver Name: Venkat Reddy, Mobile number: 6300800800): Vijayawada - Hyderabad(274.3KM's || Start time: 05:00PM 4h:35m  Travel Time || Bus Type: Tata Marcoplo AC Bus)<br></font></html>", 
		           "<html><font color='Olive'>Bus2(Driver Name: Sai Kumar, Mobile number: 6300900900): Vijayawada - Chennai(383KM's || Start time: 09:30AM 10h:35m  Travel Time || Bus Type: Ashok Leyland Lynx AC Bus)<br></font></html>", 
		           "<html><font color='Orange'>Bus3(Driver Name: Lakshman Goud, Mobile number: 6300700700): Vijayawada - Bangalore(640KM's || Start time:08:30AM 12h:02m  Travel Time || Bus Type: Mahindra Comfio AC Bus)<br></font></html>", 
		           "<html><font color='Red'>Bus4(Driver Name: Rajesh Naidu, Mobile number: 6300500500): Vijayawada - Kharagpur(1400Km's || Start time:01:30PM 29h:15m Travel Time || Bus Type: Eicher Skyline AC Bus)<br></font></html>",
		           "<html><font color='Brown'>Bus5(Driver Name: Ravi Shankar, Mobile number: 6300400400): Vijayawada - Pune(754Km's || Start time:09:30PM 14h:30m Travel Time || Bus Type: Force Traveller Monobus 4020 AC BUS)<br></font></html>", 
		           "<html><font color='Green'>Bus6(Driver Name: Srinivas Yadav, Mobile number: 6300200200): Vijayawada - Kolkata(1258Km's || Start time:10:30AM 22h:45m Travel Time || Bus Type:BharatBenz 914 AC BUS)<br></font></html>", 
		           "<html><font color='Gray'>Bus7(Driver Name: Ramesh Babu, Mobile number: 6300100100): Vijayawada - Delhi(1392Km's || Start time:08:00AM 41h:00m Travel Time || Bus Type:Volvo B7R AC Bus)<br></font></html>", 
		           "<html><font color='Purple'>Bus8(Driver Name: Anjan Gupta, Mobile number: 6300300300): Vijayawada - Ahmedabad(1400Km's || Start time:01:30PM 29h:15m Travel Time || Bus Type:Eicher Skyline AC Bus)<br></font></html>", 
		           "<html><font color='Orange'>Bus9(Driver Name: Nandan Nalla, Mobile number: 6300600600): Vijayawada - Jaipur(1400Km's || Start time:01:30PM 29h:15m Travel Time || Bus Type:Eicher Skyline AC Bus)<br></font></html>", 
		           "<html><font color='Maroon'>Bus10(Driver Name: Prasad Kulkarni, Mobile number: 6300350350): Vijayawada - Surat(1400Km's || Start time:01:30PM 29h:15m Travel Time || Bus Type:Eicher Skyline AC Bus)<br></font></html>"
		           + ""};
JOptionPane.showMessageDialog(frame, String.join("\n", routes));
} else if (e.getSource() == printTicketButton) {
    int busNumber = Integer.parseInt(busNumberField.getText());
    int seatNumber = Integer.parseInt(seatNumberField.getText());
    String passengerName = passengerNameField.getText();
    if (busNumber < 1 || busNumber > 10 || seatNumber < 1 || seatNumber > 35) {
        JOptionPane.showMessageDialog(frame, "<html><font color='red'>Invalid bus number or seat number</font></html>");
    } else if (seats[busNumber-1][seatNumber-1] == 0) {
        JOptionPane.showMessageDialog(frame, "<html><font color='red'>Seat not reserved</font></html>");
    } else if (!passengerName.equals(getPassengerName(busNumber, seatNumber))) {
        JOptionPane.showMessageDialog(frame, "<html><font color='red'>Passenger name does not match reservation</font></html>");
    } else {
		
		// Generate and print the ticket
        String ticket = "Bus Number: " + busNumber  + "\nSeat Number: " + seatNumber + "\nPassenger Name: " + passengerName;
        JTextArea textArea = new JTextArea(ticket);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JOptionPane.showMessageDialog(frame, scrollPane, "Print Ticket", JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog(frame, "<html><font color='green'>Happy Journey! &#128522;&#128522;<br></font></html>");
    }
}else if (e.getSource() == viewAllSeatsButton) {
    JPanel busesPanel = new JPanel(new GridLayout(2, 5, 10, 10)); // Adjust the grid size as needed
    busesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
    for (int bus = 0; bus < 10; bus++) {
        JPanel busPanel = new JPanel(new GridLayout(6, 5, 2, 2));
        busPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        busPanel.add(new JLabel("Bus " + (bus + 1), SwingConstants.CENTER));
        for (int seat = 0; seat < 35; seat++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(30, 30));
            if (seats[bus][seat] == 1) {
                button.setBackground(Color.RED);
            } else {
                button.setBackground(Color.GREEN);
            }
            busPanel.add(button);
        }
        busesPanel.add(busPanel);
    }
    JOptionPane.showMessageDialog(frame, busesPanel);
}else if (e.getSource() == exitButton) {
System.exit(0);
}
}

private String getPassengerName(int busNumber, int seatNumber) {
for (int i = 0; i < 35; i++) {
if (seats[busNumber-1][i] == 1 && i+1 == seatNumber) {
    return passengerNameField.getText();
}
}
return "";
}
}